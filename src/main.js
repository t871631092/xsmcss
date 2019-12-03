import Vue from "vue";
import App from "./App.vue";
import router from "./router/route";
import store from "./store";
import "bootstrap/dist/css/bootstrap.min.css";
import "jquery";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "bootstrap/dist/js/bootstrap.min.js";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import globe from "./globe";

import "./assets/main.scss";

Vue.config.productionTip = false;

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.axios = axios;
Vue.use(globe);
axios.defaults.withCredentials = true;
axios.defaults.baseURL = "http://localhost:8080/SCC/";

router.beforeEach((to, from, next) => {
	if (
		(store.state.user.type && to.meta.permission == 0) ||
		to.meta.permission.some(i => i == store.state.user.type)
	) {
		next();
	} else {
		next("/");
	}
});

new Vue({
	router,
	store,
	render: h => h(App),
	beforeCreate: function() {
		let self = this;
		this.getLogin(function(data) {
      if(data){
        self.$store.state.user = {
          username: data.username,
          isLogin: data.isLogin,
          type: data.type
        };
      }else{
        self.$router.push({ path: "/" });
      }
		});
	}
}).$mount("#app");
