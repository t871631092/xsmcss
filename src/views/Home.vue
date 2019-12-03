<template>
	<div class="height-100p bg-white">
		<div class="jumbotron jumbotron-fluid bg-white">
			<div class="container">
				<h1 class="display-4">在线选课系统</h1>
				<p class="lead">
					Course Selection System
				</p>
			</div>
		</div>
		<div class="width-100 height-100 flex-box">
			<div
				class=" nav flex-column justify-content-center flex-item"
				style="width: 500px;"
				v-if="user.type==0"
			>
				<div class="card-body item-center">
					<h5 class="card-title">用户登陆</h5>
					<hr />
					<el-form
						class="padding-r-30"
						:model="form"
						label-width="80px"
					>
						<el-form-item label="用户名">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="密码" prop="pass">
							<el-input
								type="password"
								v-model="form.pass"
								autocomplete="off"
							></el-input>
						</el-form-item>
					</el-form>
					<el-button type="primary" @click="onSubmit">登陆</el-button>
				</div>
			</div>
			<div v-if="user.type!=0">
				<h1 v-if="user.type==1">学生角色</h1>
				<h1 v-if="user.type==2">教师角色</h1>
				<h1 v-if="user.type==3">管理角色</h1>
			</div>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {
			form: {
				name: "",
				pass: ""
			}
		};
	},
	computed:{
		user(){
			return this.$store.state.user
		}
	},
	methods: {
		index() {
			this.$router.push({ path: "/" });
		},
		register() {
			this.$router.push({ path: "/register" });
		},
		onSubmit() {
			let self = this;
			this.axios
				.post("user/login", {
					username: this.form.name,
					password: this.form.pass
				})
				.then(function(req) {
					if (req.data.success) {
						self.$store.state.user = {
							isLogin : req.data.data.isLogin,
							username:req.data.data.username,
							type:req.data.data.type
						};
						self.index();
					} else {
						alert(req.data.msg);
					}
				})
				.catch(function(error) {
					console.log(error);
				});
		}
	}
};
</script>
<style lang="scss" scoped>
.flex-box {
	display: flex;
	align-items: center;
	justify-content: center;
}
.flex-item {
	margin-bottom: 100px;
}
.width-100 {
	width: 100%;
}
.padding-r-30 {
	padding-right: 30px;
}
</style>
