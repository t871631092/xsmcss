<template>
	<div class="height-100p noscroll">
		<div class="bg-white padding-25 height-100p">
			<el-divider content-position="left"><h4>个人信息</h4></el-divider>
			<el-row type="flex" :gutter="10" justify="center">
				<el-col :md="8">
					<el-form ref="form" :model="form" label-width="80px">
						<el-form-item label="姓名">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="专业" v-if="user.type == 1">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="级别" v-if="user.type == 2">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="类型" v-if="user.type == 2">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="系部" v-if="user.type == 2">
							<el-input v-model="form.name"></el-input>
						</el-form-item>
						<el-form-item label="个人描述">
							<el-input
								type="textarea"
								v-model="form.desc"
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
						<el-form-item label="旧密码" prop="pass">
							<el-input v-model="ruleForm.checkOldpass"></el-input>
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
				region: "",
				date1: "",
				date2: "",
				delivery: false,
				type: [],
				resource: "",
				desc: ""
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
  computed:{
		user() {
			return this.$store.state.user;
		}
  },
	methods: {
		onSubmit() {
			console.log("submit!");
		},
		submitForm(formName) {
			this.$refs[formName].validate(valid => {
				if (valid) {
					alert("submit!");
				} else {
					console.log("error submit!!");
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		}
	}
};
</script>

<style lang="scss">

</style>
