import {
  app,
  BrowserWindow,
  Menu,
  Tray,
  globalShortcut,
  dialog,
  ipcMain
} from 'electron'

const path = require('path')
const url = require('url')

/**
 * Set `__static` path to static files in production
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-static-assets.html
 */
if (process.env.NODE_ENV !== 'development') {
  global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
let mainWindowShow = true
let appTray = null
const winURL = process.env.NODE_ENV === 'development' ?
  `http://localhost:9080` :
  `file://${__dirname}/index.html`

// 独立的调用窗口
let commandWindow
let commandWindowShow = false
const commandWinURL = process.env.NODE_ENV === 'development' ?
  `http://localhost:9080/#/commandwindow` :
  `file://${__dirname}/index.html`

function createWindow() {
  Menu.setApplicationMenu(null)
  mainWindow = new BrowserWindow({
    height: 563,
    useContentSize: true,
    width: 1000,
    webPreferences: {webSecurity: false}
  })

  mainWindow.loadURL(winURL)

  // 绑定全局快捷键
  globalShortcut.register('Alt+P', function () {
    if (mainWindowShow) {
      mainWindowShow = false
      mainWindow.hide()
    } else {
      mainWindowShow = true
      mainWindow.show()
    }
  })

  globalShortcut.register('Alt+B', function () {
    if (commandWindow) {
      if (commandWindowShow) {
        commandWindow.hide()
      } else {
        commandWindow.show()
      }
    } else {
      createCommandWindow()
    }
  })

  // 托盘右键菜单
  let trayMenuTemplate = [{
    label: '退出',
    click: function () {
      mainWindow = null
      app.quit()
    }
  }]

  let trayRootPath = process.env.NODE_ENV !== 'development' ? global.__static : path.join(__dirname, '../../static/')
  let trayIcon = path.join(trayRootPath, 'tray')
  const contextMenu = Menu.buildFromTemplate(trayMenuTemplate)
  appTray = new Tray(path.join(trayIcon, 'icon.ico'))
  appTray.setToolTip('black hole')
  appTray.setContextMenu(contextMenu)

  appTray.on('click', function () {
    mainWindow.show()
  })

  mainWindow.on('closed', (e) => {
    mainWindow = null
  })

  mainWindow.on('close', (e) => {
    // if (mainWindow.isMinimized()) {
    //   mainWindow = null
    // } else {
    mainWindow.hide()
    mainWindow.setSkipTaskbar(true)
    e.preventDefault()
    // }
  })

  mainWindow.on('show', () => {
    mainWindow.setSkipTaskbar(false)
    appTray.setHighlightMode('always')
  })

  mainWindow.on('hide', () => {
    mainWindow.setSkipTaskbar(true)
    appTray.setHighlightMode('never')
  })

  createCommandWindow()
}

// 命令行窗口
function createCommandWindow() {
  if (commandWindowShow) {
    if (commandWindow) {
      commandWindow.hide()
    }
  } else {
    if (!commandWindow) {
      commandWindow = new BrowserWindow({
        minHeight: 60,
        width: 600,
        y:0,
        useContentSize: true,
        frame: false,
        transparent: true,
        show: false,
        alwaysOnTop: true,
        resizable: false,
        movable: false,
        webPreferences: {webSecurity: false}
      })
      commandWindow.setSkipTaskbar(true)
      commandWindow.loadURL(commandWinURL)
      commandWindow.on('close', function () {
        commandWindowShow = false
        commandWindow = null
      })

      commandWindow.on('hide', function () {
        commandWindowShow = false
        commandWindow.webContents.send('hideCommandWindow')
      })

      commandWindow.on('show', function () {
        commandWindowShow = true
        commandWindow.webContents.send('showCommandWindow')
      })

      ipcMain.on('hideCommandWindow', () => {
        if (commandWindow) {
          commandWindow.hide()
        }
      })

      ipcMain.on('showCommandWindow', () => {
        if (commandWindow) {
          commandWindow.show()
        }
      })
    } else {
      commandWindow.show()
    }
  }
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})




/**
 * Auto Updater
 *
 * Uncomment the following code below and install `electron-updater` to
 * support auto updating. Code Signing with a valid certificate is required.
 * 
 */
import {
  autoUpdater
} from 'electron-updater'
const feedUrl = 'http://127.0.0.1:60/UPDATE/'; // 更新包位置

// autoUpdater.on('update-downloaded', () => {
//   autoUpdater.quitAndInstall()
// })

app.on('ready', () => {
  //if (process.env.NODE_ENV === 'production') autoUpdater.checkForUpdates()
  if (process.env.NODE_ENV === 'production') checkForUpdates()
})

let checkForUpdates = () => {
  let tips = {
    error: '检查更新出错',
    checking: '正在检查更新……',
    updateAva: '检测到新版本，正在下载……',
    updateNotAva: '现在使用的就是最新版本，不用更新',
  }

  // 配置安装包远程服务器
  autoUpdater.setFeedURL(feedUrl)

  // 下面是自动更新的整个生命周期所发生的事件
  autoUpdater.on('error', (message) => {
    sendUpdateMessage(JSON.stringify(message))
  })

  autoUpdater.on('checking-for-update', (message) => {
    sendUpdateMessage(tips.checking)
  })

  autoUpdater.on('update-available', function (message) {
    sendUpdateMessage(tips.updateAva)
  })

  autoUpdater.on('update-not-available', function (message) {
    sendUpdateMessage(tips.updateNotAva)
  })

  // 更新下载进度事件
  autoUpdater.on('download-progress', function (progressObj) {
    sendUpdateMessage('downloadProgress', progressObj)
  })

  // 更新下载完成事件
  autoUpdater.on('update-downloaded', function (event, releaseNotes, releaseName, releaseDate, updateUrl, quitAndUpdate) {
    ipcMain.on('updateNow', (e, arg) => {
      autoUpdater.quitAndInstall()
    })
    mainWindow.webContents.send('isUpdateNow')
  })

  ipcMain.on('checkForUpdate', () => {
    //执行自动更新检查
    autoUpdater.checkForUpdates()
  })
}

// 主进程主动发送消息给渲染进程函数
function sendUpdateMessage(text) {
  mainWindow.webContents.send('message', text)
}