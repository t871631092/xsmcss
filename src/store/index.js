import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:{
      isLogin:false,
      type:0,
      nickname:"未登录",
      username:"",
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
