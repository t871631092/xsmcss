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
          <el-menu-item id="headerIcon">
            <span slot="title" v-if="!isCollapse">
              <h4 style="line-height: 60px; margin-left: 18px">
                选课系统
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
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-menu-item
            index="/student/course"
            v-if="user.type && user.type == 1"
          >
            <i class="el-icon-menu"></i>
            <span slot="title">选课信息</span>
          </el-menu-item>
          <el-menu-item
            index="/student/score"
            v-if="user.type && user.type == 1"
          >
            <i class="el-icon-s-order"></i>
            <span slot="title">成绩查询</span>
          </el-menu-item>
          <el-menu-item index="/coursetable" v-if="user.type && user.type == 2">
            <i class="el-icon-document"></i>
            <span slot="title">课程查询</span>
          </el-menu-item>
          <el-menu-item index="/coursemark" v-if="user.type && user.type == 2">
            <i class="el-icon-s-claim"></i>
            <span slot="title">学生评分</span>
          </el-menu-item>
          <el-menu-item index="/account" v-if="user.type && user.type != 3">
            <i class="el-icon-user-solid"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item
            index="/admin/student"
            v-if="user.type && user.type == 3"
          >
            <i class="el-icon-setting"></i>
            <span slot="title">学生管理</span>
          </el-menu-item>
          <el-menu-item
            index="/admin/teacher"
            v-if="user.type && user.type == 3"
          >
            <i class="el-icon-setting"></i>
            <span slot="title">教师管理</span>
          </el-menu-item>
          <el-menu-item
            index="/admin/course"
            v-if="user.type && user.type == 3"
          >
            <i class="el-icon-setting"></i>
            <span slot="title">课程管理</span>
          </el-menu-item>
          <!-- <el-menu-item v-if="user.type && user.type != 0" v-on:click="logout">
            <i class="el-icon-setting"></i>
            <span slot="title">注销</span>
          </el-menu-item> -->
        </el-menu>
        <!-- 菜单end -->
      </el-aside>
      <el-container>
        <el-header id="header" height="60px">
          <!-- <h4 style="line-height:60px;">
          {{$route.name}}ffd04b
          </h4>  -->
          <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#42b983"
            text-color="#ffffff"
            active-text-color="#ffffff"
            style="float: right"
          >
            <el-submenu index="222" v-if="user.type && user.type != 0">
              <template slot="title">
                <el-avatar size="large" style="font-size: 23px">{{
                  NAME
                }}</el-avatar>
                {{ user.nickname }}
              </template>
              <el-menu-item index="111" @click="logout">注销</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-header>
        <el-main class="bg-light">
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
      isCollapse: true,
    };
  },
  created: function () {
    document.title = "在线选课系统 Course Selection System";
  },
  computed: {
    user() {
      return this.$store.state.user;
    },
    NAME() {
      return this.$store.state.user.nickname
        ? this.$store.state.user.nickname[0]
        : "我";
    },
  },
  methods: {
    toggleMenu() {
      this.isCollapse = !this.isCollapse;
    },
    handleOpen(key, keyPath) {
      //console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      //console.log(key, keyPath);
    },
    logout() {
      let self = this;
      this.Post("user/logout", {}, function (data) {
        if (data.success) {
          self.$store.state.user = {
            username: "",
            isLogin: false,
            type: 0,
          };
          if (self.$router.currentRoute.fullPath != "/") {
            self.$router.push({ path: "/" });
          }
        }
      });
    },
  },
  watch: {
    $route(to, from) {
      document.title = "A101在线选课系统—" + this.$route.name;
    },
  },
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
.margin-0 {
  margin: 0 !important;
}
.white {
  color: white !important;
}
</style>
