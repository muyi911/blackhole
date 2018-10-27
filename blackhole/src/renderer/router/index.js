import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      name: 'Loading',
      component: require('@/components/Loading').default
    },
    {
      path: '*',
      redirect: '/'
    },
    {
      path: '/apps',
      name: 'apps',
      component: require('@/components/app').default
    },
    {
      path: '/music',
      name: 'music',
      component: require('@/components/Music').default
    },
    {
      path: '/clock',
      name: 'clock',
      component: require('@/components/Clock').default
    }
  ]
})