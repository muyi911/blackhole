const state = {
  main: 0,
  isLogin: true
}

const mutations = {
  DECREMENT_MAIN_COUNTER(state) {
    state.main--
  },
  INCREMENT_MAIN_COUNTER(state) {
    state.main++
  },
  changeLogin(state, data) {
    state.isLogin = data
  }
}

const actions = {
  someAsyncTask({
    commit
  }) {
    // do something async
    commit('INCREMENT_MAIN_COUNTER')
  }
}



export default {
  state,
  mutations,
  actions
}