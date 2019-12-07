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
				v-if="user.type == 0"
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
			<div v-if="user.type != 0" class="container">
				<h2>{{ user.nickname }}</h2>
				<el-divider content-position="right" v-if="user.type == 1"
					><h5>学生</h5></el-divider
				>
				<el-divider content-position="right" v-if="user.type == 2"
					><h5>教师</h5></el-divider
				>
				<el-divider content-position="right" v-if="user.type == 3"
					><h5>管理</h5></el-divider
				>
				<div v-if="user.type == 1">
					<p>专业:{{ data.student_major }}</p>
					<p>已修学分:{{ data.electives_credits }}</p>
				</div>
				<div v-if="user.type == 2" style="margin-top:100px">
					<div class="row">
						<div class="col-md-6">
							<el-divider content-position="left"
								><h4>平均课程容纳人数最大最小类别</h4></el-divider
							>
							<el-table
								ref="singleTable"
								:data="tdata2"
								highlight-current-row
								height="80%"
								style="width: 100%;"
							>
								<el-table-column property="category" label="类别">
								</el-table-column>
								<el-table-column property="avgScore" label="平均容纳人数">
								</el-table-column>
							</el-table>
						</div>
						<div class="col-md-6">
							<el-divider content-position="left"
								><h4>学生信息中超过平均人数的专业</h4></el-divider
							>
							<el-table
								ref="singleTable"
								:data="tdata1"
								highlight-current-row
								height="80%"
								style="width: 100%"
							>
								<el-table-column property="student_major" label="专业">
								</el-table-column>
								<el-table-column property="avgScore" label="平均人数">
								</el-table-column>
								
							</el-table>
						</div>
					</div>
				</div>
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
			},
			data: {},
			tdata1:[],
			tdata2:[]
		};
	},
	computed: {
		user() {
			return this.$store.state.user;
		}
	},
	mounted() {
		this.getInfo();
	},
	methods: {
		index() {
			this.$router.push({ path: "/" });
		},
		register() {
			this.$router.push({ path: "/register" });
		},
		getInfo() {
			let self = this;
			if (self.user.type == 1) {
				this.Get("user/info", {adv : true}, function(data) {
					if (data.success) {
						self.data = data.data[0];
					}
				});
			}
			if (self.user.type == 2) {
				
				this.Get("course/studep", { adv : true}, function(data) {
					if (data.success) {
						self.tdata1 = data.data;
					}
				});
				this.Get("course/couCate", { adv : true}, function(data) {
					if (data.success) {
						self.tdata2 = data.data;
					}
				});
			}
		},
		onSubmit() {
			let self = this;
			this.Post(
				"user/login",
				{
					username: this.form.name,
					password: this.form.pass
				},
				function(data) {
					if (data.success) {
						const d = data.data;
						self.$store.state.user = {
							isLogin: d.isLogin,
							username: d.username,
							nickname: d.nickname,
							type: d.type
						};
						self.getInfo();
					} else {
						alert(data.msg);
					}
				}
			);
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
