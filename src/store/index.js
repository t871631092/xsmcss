import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:{
      isLogin:false,
      type:3,
      name:"未登录"
    }
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
