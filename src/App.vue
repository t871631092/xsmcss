<template>
	<div id="app">
		<!-- <div id="nav">
			<router-link to="/">Home</router-link> |
			<router-link to="/about">About</router-link>
		</div>
		<router-view /> -->
		<el-container>
			<el-aside width="initial">
				<!-- 菜单start -->
				<el-menu
					default-active="1-4-1"
					class="navmenu el-menu-vertical-demo height-100p white"
					@open="handleOpen"
					@close="handleClose"
					:collapse="isCollapse"
          router 
				>
					<el-menu-item index="1" id="headerIcon">
						<span slot="title" v-if="!isCollapse">
							<h4 style="line-height:60px;margin-left:18px;">选课系统
                <i
									class="el-icon-s-fold white"
									v-if="!isCollapse"
									v-on:click="toggleMenu"
								></i>
              </h4>
						</span>
            <i
							class="el-icon-s-unfold white"
							v-if="isCollapse"
							v-on:click="toggleMenu"
						></i>
					</el-menu-item>
					<el-menu-item index="/student/course" v-if="user.type&& user.type==1">
						<i class="el-icon-menu"></i>
						<span slot="title">选课管理</span>
					</el-menu-item>
					<el-menu-item index="/student/score" v-if="user.type&&user.type==1">
						<i class="el-icon-menu"></i>
						<span slot="title">成绩管理</span>
					</el-menu-item>
					<el-menu-item index="3" v-if="user.type&&user.type==2">
						<i class="el-icon-document"></i>
						<span slot="title">学生评分</span>
					</el-menu-item>
					<el-menu-item index="4" v-if="user.type&&user.type!=3">
						<i class="el-icon-setting"></i>
						<span slot="title">个人信息</span>
					</el-menu-item>
					<el-menu-item index="5" v-if="user.type&&user.type==3">
						<i class="el-icon-setting"></i>
						<span slot="title">学生管理</span>
					</el-menu-item>
					<el-menu-item index="6" v-if="user.type&&user.type==3">
						<i class="el-icon-setting"></i>
						<span slot="title">教师管理</span>
					</el-menu-item>
					<el-menu-item index="7" v-if="user.type&&user.type==3">
						<i class="el-icon-setting"></i>
						<span slot="title">课程管理</span>
					</el-menu-item>
				</el-menu>
				<!-- 菜单end -->
			</el-aside>
			<el-container>
				<el-header id="header" height="60px">
          <h4 style="line-height:60px;">
          {{$route.name}}
          </h4>
				</el-header>
				<el-main>
					<router-view />
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>
<script>
export default {
	data() {
		return {
			isCollapse: true
		};
  },
  created:function(){
    document.title="在线选课系统 Course Selection System";
  },
  computed:{
		user() {
      console.log(this.$store.state.user);
			return this.$store.state.user;
		}
  },
	methods: {
		toggleMenu() {
			this.isCollapse = !this.isCollapse;
		},
		handleOpen(key, keyPath) {
			console.log(key, keyPath);
		},
		handleClose(key, keyPath) {
			console.log(key, keyPath);
		}
  },
  watch:{
    $route(to,from){
      document.title = "01谢诗敏在线选课系统—"+this.$route.name;
    }
  }
};
</script>
<style lang="scss">
#app {
	font-family: "Avenir", Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
}
#app,
html,
body,
.el-container {
	height: 100%;
}
#nav {
	padding: 30px;

	a {
		font-weight: bold;
		color: #2c3e50;

		&.router-link-exact-active {
			color: #42b983;
		}
	}
}
#header {
	background-color: #42b983;
}
.height-100p {
	height: 100%;
}

.navmenu {
	height: 100vh;
	&:not(.el-menu--collapse) {
		width: 180px;
	}
	overflow: hidden;
}
#headerIcon {
	height: 60px !important;
	background-color: #42b983;
	color: white;
}
.margin-0{
  margin: 0!important;
}
.white{
  color: white!important;
}
</style>
