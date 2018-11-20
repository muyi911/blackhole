import Vue from 'vue'
import axios from 'axios'
import Lockr from 'lockr'
import Cookies from 'js-cookie'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import jQuery from 'jquery'
import Global from '../../static/utils/global'
import http from '../../static/utils/http'

import router from './router'
import store from './store'
import App from './App'

import '@/assets/css/common.css'

if (!process.env.IS_WEB) Vue.use(require('vue-electron'))

axios.defaults.baseURL = 'http://localhost:8081/api'
axios.defaults.timeout = 1000 * 15
axios.defaults.headers.authKey = Lockr.get('authKey')
axios.defaults.headers.sessionId = Lockr.get('sessionId')
axios.defaults.headers['Content-Type'] = 'application/json'

Vue.http = Vue.prototype.$http = axios
Vue.config.productionTip = false

Vue.use(ElementUI);

const bus = new Vue();

window.axios = axios;
window.$ = jQuery;
window.bus = bus;
window.http = http;
window._g = Global;
window.Cookies = Cookies;

/* eslint-disable no-new */
new Vue({
  components: {
    App
  },
  router,
  store,
  template: '<App/>'
}).$mount('#app')