import {
  app,
  BrowserWindow,
  Menu,
  Tray,
  globalShortcut,
  dialog
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
  //Menu.setApplicationMenu(null)

  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 563,
    useContentSize: true,
    width: 1000,
    frame: false
  })

  mainWindow.loadURL(winURL)

  mainWindow.on('closed', () => {
    mainWindow = null
  })

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
    createCommandWindow()
    // mainWindow.loadURL('http://www.baidu.com')
  })

  // 托盘右键菜单
  let trayMenuTemplate = [{
    label: '退出',
    click: function () {
      mainWindow = null
      app.quit()
    }
  }]

  let trayIcon = path.join(__dirname, 'tray')
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
}

// 命令行窗口
function createCommandWindow() {
  if (commandWindowShow) {
    return;
  } else {
    commandWindow = new BrowserWindow({
      height: 60,
      width: 600,
      frame: false,
      backgroundColor: '#fff',
    })
    commandWindow.setSkipTaskbar(true)
    commandWindow.loadURL(commandWinURL)
    commandWindow.on('close', function () {
      commandWindow = null
    })
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
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-electron-builder.html#auto-updating
 */

/*
import { autoUpdater } from 'electron-updater'

autoUpdater.on('update-downloaded', () => {
  autoUpdater.quitAndInstall()
})

app.on('ready', () => {
  if (process.env.NODE_ENV === 'production') autoUpdater.checkForUpdates()
})
 */