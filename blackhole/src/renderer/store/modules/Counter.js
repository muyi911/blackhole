const state = {
  isLogin: false
}

const getters = {
  isLogin(state) {
    return state.isLogin
  }
}

const mutations = {
  changeLogin(state) {
    state.isLogin = true
  }
}

const actions = {
  changeLogin(context) {
    context.commit("changeLogin")
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}