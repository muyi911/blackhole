const http = {
  handleUrl(url) {
    if (url && url.substring(0, 1) == "/") {
      return "/api" + url;
    } else {
      return "/api/" + url
    }
  },
  apiGet(url, data) {
    return new Promise((resolve, reject) => {
      axios.get(url, data).then((response) => {
        resolve(response.data)
      }, (response) => {
        reject(response)
        bus.$message({
          message: '请求超时，请检查网络！',
          type: 'warning'
        })
      })
    })
  },
  apiPost(url, data) {
    return new Promise((resolve, reject) => {
      axios.post(url, data).then((response) => {
        resolve(response.data)
      }).catch((response) => {
        resolve(response)
        bus.$message({
          message: '请求超时，请检查网络',
          type: 'warning'
        })
      })
    })
  },
  apiDelete(url, id) {
    return new Promise((resolve, reject) => {
      axios.delete(url + id).then((response) => {
        resolve(response.data)
      }, (response) => {
        reject(response)
        bus.$message({
          message: '请求超时，请检查网络',
          type: 'warning'
        })
      })
    })
  },
  apiPut(url, id, obj) {
    return new Promise((resolve, reject) => {
      axios.put(url + id, obj).then((response) => {
        resolve(response.data)
      }, (response) => {
        bus.$message({
          message: '请求超时，请检查网络',
          type: 'warning'
        })
        reject(response)
      })
    })
  },
  handleResponse(res, cb, errCb) {
    if (res.code == 200) {
      cb(res.data)
    } else {
      if (typeof errCb == 'function') {
        errCb(res)
      }
      this.handleError(res)
    }
  },
  handleError(res) {
    if (res.code) {
      switch (res.code) {
        // case 101:
        //   console.log('cookie = ', Cookies.get('rememberPwd'))
        //   if (Cookies.get('rememberPwd')) {
        //     let data = {
        //       rememberKey: Lockr.get('rememberKey')
        //     }
        //     this.reAjax('admin/base/relogin', data).then((res) => {
        //       this.handelResponse(res, (data) => {
        //         this.resetCommonData(data)
        //       })
        //     })
        //   } else {
        //     _g.toastMsg('error', res.message)
        //     setTimeout(() => {
        //       router.replace('/')
        //     }, 1500)
        //   }
        //   break
        // case 103:
        //   _g.toastMsg('error', res.message)
        //   setTimeout(() => {
        //     router.replace('/')
        //   }, 1500)
        //   break
        default:
          _g.message('error', res.message)
      }
    } else {
      console.log('default error')
    }
  }

}

export default http