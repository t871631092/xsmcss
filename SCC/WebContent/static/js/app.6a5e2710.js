(function(t){function e(e){for(var s,a,o=e[0],u=e[1],c=e[2],l=0,d=[];l<o.length;l++)a=o[l],Object.prototype.hasOwnProperty.call(r,a)&&r[a]&&d.push(r[a][0]),r[a]=0;for(s in u)Object.prototype.hasOwnProperty.call(u,s)&&(t[s]=u[s]);p&&p(e);while(d.length)d.shift()();return i.push.apply(i,c||[]),n()}function n(){for(var t,e=0;e<i.length;e++){for(var n=i[e],s=!0,a=1;a<n.length;a++){var o=n[a];0!==r[o]&&(s=!1)}s&&(i.splice(e--,1),t=u(u.s=n[0]))}return t}var s={},a={app:0},r={app:0},i=[];function o(t){return u.p+"js/"+({about:"about"}[t]||t)+"."+{about:"46195ddf","chunk-2d0c7c84":"4e0fe004","chunk-2d0dd8bc":"57475835","chunk-2d21007a":"5110b987","chunk-2d22500a":"dbe030a8","chunk-5c7aba56":"6178bcff","chunk-5dc07143":"86b8cdb4","chunk-70bbbbd2":"80e36568","chunk-fa23e8ea":"444c3417"}[t]+".js"}function u(e){if(s[e])return s[e].exports;var n=s[e]={i:e,l:!1,exports:{}};return t[e].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(t){var e=[],n={"chunk-5c7aba56":1,"chunk-5dc07143":1,"chunk-70bbbbd2":1};a[t]?e.push(a[t]):0!==a[t]&&n[t]&&e.push(a[t]=new Promise((function(e,n){for(var s="css/"+({about:"about"}[t]||t)+"."+{about:"31d6cfe0","chunk-2d0c7c84":"31d6cfe0","chunk-2d0dd8bc":"31d6cfe0","chunk-2d21007a":"31d6cfe0","chunk-2d22500a":"31d6cfe0","chunk-5c7aba56":"e3b75ed3","chunk-5dc07143":"e3b75ed3","chunk-70bbbbd2":"b65a4c41","chunk-fa23e8ea":"31d6cfe0"}[t]+".css",r=u.p+s,i=document.getElementsByTagName("link"),o=0;o<i.length;o++){var c=i[o],l=c.getAttribute("data-href")||c.getAttribute("href");if("stylesheet"===c.rel&&(l===s||l===r))return e()}var d=document.getElementsByTagName("style");for(o=0;o<d.length;o++){c=d[o],l=c.getAttribute("data-href");if(l===s||l===r)return e()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=e,p.onerror=function(e){var s=e&&e.target&&e.target.src||r,i=new Error("Loading CSS chunk "+t+" failed.\n("+s+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=s,delete a[t],p.parentNode.removeChild(p),n(i)},p.href=r;var f=document.getElementsByTagName("head")[0];f.appendChild(p)})).then((function(){a[t]=0})));var s=r[t];if(0!==s)if(s)e.push(s[2]);else{var i=new Promise((function(e,n){s=r[t]=[e,n]}));e.push(s[2]=i);var c,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=o(t);var d=new Error;c=function(e){l.onerror=l.onload=null,clearTimeout(p);var n=r[t];if(0!==n){if(n){var s=e&&("load"===e.type?"missing":e.type),a=e&&e.target&&e.target.src;d.message="Loading chunk "+t+" failed.\n("+s+": "+a+")",d.name="ChunkLoadError",d.type=s,d.request=a,n[1](d)}r[t]=void 0}};var p=setTimeout((function(){c({type:"timeout",target:l})}),12e4);l.onerror=l.onload=c,document.head.appendChild(l)}return Promise.all(e)},u.m=t,u.c=s,u.d=function(t,e,n){u.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:n})},u.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},u.t=function(t,e){if(1&e&&(t=u(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var s in t)u.d(n,s,function(e){return t[e]}.bind(null,s));return n},u.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return u.d(e,"a",e),e},u.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},u.p="/SCC/static/",u.oe=function(t){throw console.error(t),t};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],l=c.push.bind(c);c.push=e,c=c.slice();for(var d=0;d<c.length;d++)e(c[d]);var p=l;i.push([0,"chunk-vendors"]),n()})({0:function(t,e,n){t.exports=n("56d7")},"405a":function(t,e){e.install=function(t){t.prototype.getLogin=function(t){this.axios.post("user/isLogin",null).then((function(e){var n=e.data;e?t(n.data):alert("wrong")})).catch((function(t){}))},t.prototype.Logout=function(t){this.axios.post("user/logout",null).then((function(e){var n=e.data;n.success?t(n):alert("wrong")})).catch((function(t){}))},t.prototype.Post=function(t,e,n){this.axios.post(t,e).then((function(t){200==t.status?n(t.data):alert("wrong")})).catch((function(t){}))},t.prototype.Get=function(t,e,n){this.axios.get(t,{params:e}).then((function(t){200==t.status?n(t.data):alert("wrong")})).catch((function(t){}))},t.prototype.Delete=function(t,e,n){this.axios.delete(t,{data:e}).then((function(t){200==t.status?n(t.data):alert("wrong")})).catch((function(t){}))},t.prototype.Clone=function(t){return JSON.parse(JSON.stringify(t))}}},"56d7":function(t,e,n){"use strict";n.r(e);n("45fc"),n("e260"),n("e6cf"),n("cca6"),n("a79d");var s=n("2b0e"),a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"app"}},[n("el-container",[n("el-aside",{attrs:{width:"initial"}},[n("el-menu",{staticClass:"navmenu el-menu-vertical-demo height-100p white",attrs:{"default-active":"1-4-1",collapse:t.isCollapse,router:""},on:{open:t.handleOpen,close:t.handleClose}},[n("el-menu-item",{attrs:{id:"headerIcon"}},[t.isCollapse?t._e():n("span",{attrs:{slot:"title"},slot:"title"},[n("h4",{staticStyle:{"line-height":"60px","margin-left":"18px"}},[t._v(" 选课系统 "),t.isCollapse?t._e():n("i",{staticClass:"el-icon-s-fold white",on:{click:t.toggleMenu}})])]),t.isCollapse?n("i",{staticClass:"el-icon-s-unfold white",on:{click:t.toggleMenu}}):t._e()]),n("el-menu-item",{attrs:{index:"/"}},[n("i",{staticClass:"el-icon-s-home"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("首页")])]),t.user.type&&1==t.user.type?n("el-menu-item",{attrs:{index:"/student/course"}},[n("i",{staticClass:"el-icon-menu"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("选课信息")])]):t._e(),t.user.type&&1==t.user.type?n("el-menu-item",{attrs:{index:"/student/score"}},[n("i",{staticClass:"el-icon-s-order"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("成绩查询")])]):t._e(),t.user.type&&2==t.user.type?n("el-menu-item",{attrs:{index:"/coursetable"}},[n("i",{staticClass:"el-icon-document"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("课程查询")])]):t._e(),t.user.type&&2==t.user.type?n("el-menu-item",{attrs:{index:"/coursemark"}},[n("i",{staticClass:"el-icon-s-claim"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("学生评分")])]):t._e(),t.user.type&&3!=t.user.type?n("el-menu-item",{attrs:{index:"/account"}},[n("i",{staticClass:"el-icon-user-solid"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("个人信息")])]):t._e(),t.user.type&&3==t.user.type?n("el-menu-item",{attrs:{index:"/admin/student"}},[n("i",{staticClass:"el-icon-setting"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("学生管理")])]):t._e(),t.user.type&&3==t.user.type?n("el-menu-item",{attrs:{index:"/admin/teacher"}},[n("i",{staticClass:"el-icon-setting"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("教师管理")])]):t._e(),t.user.type&&3==t.user.type?n("el-menu-item",{attrs:{index:"/admin/course"}},[n("i",{staticClass:"el-icon-setting"}),n("span",{attrs:{slot:"title"},slot:"title"},[t._v("课程管理")])]):t._e()],1)],1),n("el-container",[n("el-header",{attrs:{id:"header",height:"60px"}},[n("el-menu",{staticClass:"el-menu-demo",staticStyle:{float:"right"},attrs:{mode:"horizontal","background-color":"#42b983","text-color":"#ffffff","active-text-color":"#ffffff"}},[t.user.type&&0!=t.user.type?n("el-submenu",{attrs:{index:"222"}},[n("template",{slot:"title"},[n("el-avatar",{staticStyle:{"font-size":"23px"},attrs:{size:"large"}},[t._v(t._s(t.NAME))]),t._v(" "+t._s(t.user.nickname)+" ")],1),n("el-menu-item",{attrs:{index:"111"},on:{click:t.logout}},[t._v("注销")])],2):t._e()],1)],1),n("el-main",{staticClass:"bg-light"},[n("router-view")],1)],1)],1)],1)},r=[],i=(n("b0c0"),{data:function(){return{isCollapse:!0}},created:function(){document.title="在线选课系统 Course Selection System"},computed:{user:function(){return this.$store.state.user},NAME:function(){return this.$store.state.user.nickname?this.$store.state.user.nickname[0]:"我"}},methods:{toggleMenu:function(){this.isCollapse=!this.isCollapse},handleOpen:function(t,e){},handleClose:function(t,e){},logout:function(){var t=this;this.Post("user/logout",{},(function(e){e.success&&(t.$store.state.user={username:"",isLogin:!1,type:0},"/"!=t.$router.currentRoute.fullPath&&t.$router.push({path:"/"}))}))}},watch:{$route:function(t,e){document.title="A101在线选课系统—"+this.$route.name}}}),o=i,u=(n("5c0b"),n("2877")),c=Object(u["a"])(o,a,r,!1,null,null,null),l=c.exports,d=(n("d3b7"),n("8c4f")),p=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"height-100p bg-white"},[t._m(0),n("div",{staticClass:"width-100 height-100 flex-box"},[0==t.user.type?n("div",{staticClass:" nav flex-column justify-content-center flex-item",staticStyle:{width:"500px"}},[n("div",{staticClass:"card-body item-center"},[n("h5",{staticClass:"card-title"},[t._v("用户登陆")]),n("hr"),n("el-form",{staticClass:"padding-r-30",attrs:{model:t.form,"label-width":"80px"}},[n("el-form-item",{attrs:{label:"用户名"}},[n("el-input",{model:{value:t.form.name,callback:function(e){t.$set(t.form,"name",e)},expression:"form.name"}})],1),n("el-form-item",{attrs:{label:"密码",prop:"pass"}},[n("el-input",{attrs:{type:"password",autocomplete:"off"},model:{value:t.form.pass,callback:function(e){t.$set(t.form,"pass",e)},expression:"form.pass"}})],1)],1),n("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("登陆")])],1)]):t._e(),0!=t.user.type?n("div",{staticClass:"container"},[n("h2",[t._v(t._s(t.user.nickname))]),1==t.user.type?n("el-divider",{attrs:{"content-position":"right"}},[n("h5",[t._v("学生")])]):t._e(),2==t.user.type?n("el-divider",{attrs:{"content-position":"right"}},[n("h5",[t._v("教师")])]):t._e(),3==t.user.type?n("el-divider",{attrs:{"content-position":"right"}},[n("h5",[t._v("管理")])]):t._e(),1==t.user.type?n("div",[n("p",[t._v("专业:"+t._s(t.data.student_major))]),n("p",[t._v("已修学分:"+t._s(t.data.electives_credits))])]):t._e(),2==t.user.type?n("div",{staticStyle:{"margin-top":"100px"}},[n("div",{staticClass:"row"},[n("div",{staticClass:"col-md-6"},[n("el-divider",{attrs:{"content-position":"left"}},[n("h4",[t._v("平均课程容纳人数最大最小类别")])]),n("el-table",{ref:"singleTable",staticStyle:{width:"100%"},attrs:{data:t.tdata2,"highlight-current-row":"",height:"80%"}},[n("el-table-column",{attrs:{property:"category",label:"类别"}}),n("el-table-column",{attrs:{property:"avgScore",label:"平均容纳人数"}})],1)],1),n("div",{staticClass:"col-md-6"},[n("el-divider",{attrs:{"content-position":"left"}},[n("h4",[t._v("学生信息中超过平均人数的专业")])]),n("el-table",{ref:"singleTable",staticStyle:{width:"100%"},attrs:{data:t.tdata1,"highlight-current-row":"",height:"80%"}},[n("el-table-column",{attrs:{property:"student_major",label:"专业"}}),n("el-table-column",{attrs:{property:"avgScore",label:"平均人数"}})],1)],1)])]):t._e()],1):t._e()])])},f=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"jumbotron jumbotron-fluid bg-white"},[n("div",{staticClass:"container"},[n("h1",{staticClass:"display-4"},[t._v("在线选课系统")]),n("p",{staticClass:"lead"},[t._v(" Course Selection System ")])])])}],m={data:function(){return{form:{name:"",pass:""},data:{},tdata1:[],tdata2:[]}},computed:{user:function(){return this.$store.state.user}},mounted:function(){this.getInfo()},methods:{index:function(){this.$router.push({path:"/"})},register:function(){this.$router.push({path:"/register"})},getInfo:function(){var t=this;1==t.user.type&&this.Get("user/info",{adv:!0},(function(e){e.success&&(t.data=e.data[0])})),2==t.user.type&&(this.Get("course/studep",{adv:!0},(function(e){e.success&&(t.tdata1=e.data)})),this.Get("course/couCate",{adv:!0},(function(e){e.success&&(t.tdata2=e.data)})))},onSubmit:function(){var t=this;this.Post("user/login",{username:this.form.name,password:this.form.pass},(function(e){if(e.success){var n=e.data;t.$store.state.user={isLogin:n.isLogin,username:n.username,nickname:n.nickname,type:n.type},t.getInfo()}else alert(e.msg)}))}}},h=m,b=(n("b699"),Object(u["a"])(h,p,f,!1,null,"59e00a6e",null)),v=b.exports,g=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"bg-white height-100p"},[n("router-view")],1)},y=[],_={},C=_,k=Object(u["a"])(C,g,y,!1,null,null,null);k.exports;s["default"].use(d["a"]);var w=[{path:"/",name:"在线选课系统 Course Selection System",meta:{permission:[0]},component:v},{path:"/about",name:"about",meta:{permission:[0]},component:function(){return n.e("about").then(n.bind(null,"f820"))}},{path:"/student/course",name:"学生选课",meta:{permission:[1]},component:function(){return n.e("chunk-70bbbbd2").then(n.bind(null,"28c0"))}},{path:"/student/score",name:"学生成绩",meta:{permission:[1]},component:function(){return n.e("chunk-2d0dd8bc").then(n.bind(null,"829e"))}},{path:"/account",name:"个人信息",meta:{permission:[1,2]},component:function(){return n.e("chunk-2d21007a").then(n.bind(null,"b5c4"))}},{path:"/coursemark",name:"评分",meta:{permission:[2]},component:function(){return n.e("chunk-5c7aba56").then(n.bind(null,"1417"))}},{path:"/coursetable",name:"课程查询",meta:{permission:[2]},component:function(){return n.e("chunk-5dc07143").then(n.bind(null,"1842"))}},{path:"/admin/student",name:"学生管理",meta:{permission:[3]},component:function(){return n.e("chunk-2d22500a").then(n.bind(null,"e317"))}},{path:"/admin/teacher",name:"教师管理",meta:{permission:[3]},component:function(){return n.e("chunk-2d0c7c84").then(n.bind(null,"51a3"))}},{path:"/admin/course",name:"课程管理",meta:{permission:[3]},component:function(){return n.e("chunk-fa23e8ea").then(n.bind(null,"2b46"))}}],x=new d["a"]({routes:w}),S=x,$=n("2f62");s["default"].use($["a"]);var j=new $["a"].Store({state:{user:{isLogin:!1,type:0,nickname:"未登录",username:"",name:"未登录"}},mutations:{},actions:{},modules:{}}),O=(n("ab8b"),n("1157"),n("0deb"),n("3e48"),n("bc3a")),E=n.n(O),L=n("5c96"),P=n.n(L),T=(n("0fae"),n("405a")),A=n.n(T);n("b383");s["default"].config.productionTip=!1,s["default"].use(P.a),s["default"].prototype.axios=E.a,s["default"].use(A.a),E.a.defaults.withCredentials=!0,S.beforeEach((function(t,e,n){j.state.user.type&&0==t.meta.permission||t.meta.permission.some((function(t){return t==j.state.user.type}))?n():n("/")})),new s["default"]({router:S,store:j,render:function(t){return t(l)},beforeCreate:function(){var t=this;this.Get("user/isLogin",{},(function(e){if(e.success){var n=e.data;t.$store.state.user={username:n.username,nickname:n.nickname,isLogin:n.isLogin,type:n.type}}}))}}).$mount("#app")},"5c0b":function(t,e,n){"use strict";var s=n("9c0c"),a=n.n(s);a.a},7219:function(t,e,n){},"9c0c":function(t,e,n){},b383:function(t,e,n){},b699:function(t,e,n){"use strict";var s=n("7219"),a=n.n(s);a.a}});
//# sourceMappingURL=app.6a5e2710.js.map