<template>
	<div class="height-100p noscroll">
		<div class="bg-white padding-25 height-100p">
			<el-divider content-position="left"><h4>个人信息</h4></el-divider>
			<el-row type="flex" :gutter="10" justify="center">
				<el-col :md="8">
					<el-form ref="form" :model="form" label-width="80px">
						<el-form-item label="姓名" v-if="user.type == 1">
							<el-input v-model="form.student_name"></el-input>
						</el-form-item>
						<el-form-item label="姓名" v-if="user.type != 1">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="专业" v-if="user.type == 1">
							<el-input v-model="form.student_major"></el-input>
						</el-form-item>
						<el-form-item label="级别" v-if="user.type == 2">
							<el-input v-model="form.grade"></el-input>
						</el-form-item>
						<el-form-item label="类型" v-if="user.type == 2">
							<el-input v-model="form.category"></el-input>
						</el-form-item>
						<el-form-item label="系部" v-if="user.type == 2">
							<el-input v-model="form.department"></el-input>
						</el-form-item>
						<el-form-item label="个人描述" v-if="user.type == 1">
							<el-input
								type="textarea"
								v-model="form.student_description"
							></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="onSubmit"
								>保存</el-button
							>
						</el-form-item>
					</el-form>
				</el-col>
			</el-row>
			<el-row type="flex" :gutter="10" justify="center">
				<el-col :md="8">
					<el-form
						:model="ruleForm"
						status-icon
						:rules="rules"
						ref="ruleForm"
						label-width="100px"
						class="demo-ruleForm"
					>
						<el-form-item label="密码" prop="pass">
							<el-input
								type="password"
								v-model="ruleForm.pass"
								autocomplete="off"
							></el-input>
						</el-form-item>
						<el-form-item label="确认密码" prop="checkPass">
							<el-input
								type="password"
								v-model="ruleForm.checkPass"
								autocomplete="off"
							></el-input>
						</el-form-item>
						<el-form-item label="旧密码" prop="oldpass">
							<el-input v-model="ruleForm.oldpass"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button
								type="primary"
								@click="submitForm('ruleForm')"
								>提交</el-button
							>
							<el-button @click="resetForm('ruleForm')"
								>重置</el-button
							>
						</el-form-item>
					</el-form>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		var checkOldpass = (rule, value, callback) => {
			if (value === "") {
				callback(new Error("请输入密码"));
			} else {
				callback();
			}
		};
		var validatePass = (rule, value, callback) => {
			if (value === "") {
				callback(new Error("请输入密码"));
			} else {
				if (this.ruleForm.checkPass !== "") {
					this.$refs.ruleForm.validateField("checkPass");
				}
				callback();
			}
		};
		var validatePass2 = (rule, value, callback) => {
			if (value === "") {
				callback(new Error("请再次输入密码"));
			} else if (value !== this.ruleForm.pass) {
				callback(new Error("两次输入密码不一致!"));
			} else {
				callback();
			}
		};
		return {
			form: {
				name: "",
				major: "",
				grade: "",
				category: "",
				department: "",
				description: ""
			},
			ruleForm: {
				pass: "",
				checkPass: "",
				oldpass: ""
			},
			rules: {
				pass: [{ validator: validatePass, trigger: "blur" }],
				checkPass: [{ validator: validatePass2, trigger: "blur" }],
				oldpass: [{ validator: checkOldpass, trigger: "blur" }]
			}
		};
	},
	computed: {
		user() {
			return this.$store.state.user;
		}
	},
	mounted() {
		this.getData();
	},
	methods: {
		onSubmit() {
			let self = this;
			this.Post("user/info", self.form, function(data) {
				if (data.success) {
					alert("修改成功");
					self.form = data.data[0];
				} else {
					alert("修改失败：" + data.msg);
				}
			});
		},
		submitForm(formName) {
			let self = this;
			this.$refs[formName].validate(valid => {
				if (valid) {
					self.Post(
						"user/password",
						{
							password: self.ruleForm.pass,
							oldPassword: self.ruleForm.oldpass
						},
						function(data) {
							if (data.success) {
								alert("修改成功");
							} else {
								alert("失败：" + data.msg);
							}
						}
					);
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
		getData() {
			let self = this;
			this.Get("user/info", {}, function(data) {
				if (data.success) {
					self.form = data.data[0];
				} else {
					alert("失败：" + data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss"></style>
