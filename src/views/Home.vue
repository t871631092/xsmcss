<template>
	<div class="width-100 height-100 flex-box">
		<div
			class="card nav flex-column justify-content-center flex-item"
			style="width: 500px;"
		>
			<div class="card-body item-center">
				<h5 class="card-title">用户登陆</h5>
				<hr />
				<el-form class="padding-r-30" :model="form" label-width="80px">
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
				<el-button type="primary" @click="register">注册</el-button>
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
	methods: {		
		index() {
			this.$router.push({ path: "/" });
		},
		register(){
			this.$router.push({ path: "/register" });
		},
		onSubmit() {
			let self = this;
            this.axios.post('Login',{
                username:this.form.name,
                password:this.form.pass
            }).then(function(req) {
				if(req.data.success){
					self.$store.state.isLogin = req.data.data.isLogin;
					self.$store.state.username = req.data.data.username;
					self.index();
				}else{
					alert(req.data.msg)
				}
			}).catch(function(error) {
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
