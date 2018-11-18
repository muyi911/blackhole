import Vue from 'vue'
import Router from 'vue-router'
import http from '../../../static/utils/http';
import {
  getToken
} from '../../../static/utils/auth'

Vue.use(Router)

const router = new Router({
  routes: [{
      path: '*',
      redirect: '/'
    },
    {
      path: '/',
      name: 'loading',
      component: require('@/components/Loading').default
    },
    {
      path: '/login',
      name: 'login',
      component: require('@/components/Login').default
    },
    {
      path: '/apps',
      name: 'apps',
      component: require('@/components/app').default,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/music',
      name: 'music',
      component: require('@/components/Music').default,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/clock',
      name: 'clock',
      component: require('@/components/Clock').default,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/commandwindow',
      name: 'commandwindow',
      component: require('@/components/CommandWindow').default,
      meta: {
        requireAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireAuth)) {
    let token = getToken();
    if (token) {
      http.apiPost('/user/info', {}).then((res) => {
        http.handleResponse(res, function (data) {
          next()
        }, function (res) {
          next({
            path: '/login',
            query: {
              redirect: to.fullPath
            }
          })
        })
      }).catch(() => {
        next({
          path: '/login',
          query: {
            redirect: to.fullPath
          }
        })
      })
    } else {
      next({
        path: '/login',
        query: {
          redirect: to.fullPath
        }
      })
    }
  } else {
    next()
  }
})

export default router