<template>
	<div class="bg-white h-100p p-25 noscroll">
		<el-divider content-position="left"><h4>课程管理</h4></el-divider>
		<div class="row h-100p">
			<div class="col-md-4">
				<el-table
					ref="singleTable"
					:data="tableData"
					height="75%"
					highlight-current-row
					@current-change="handleCurrentChange"
					style="width: 100%"
				>
					<el-table-column type="index"> </el-table-column>
					<el-table-column property="date" label="课程名称">
					</el-table-column>
					<el-table-column property="name" label="课程类别">
					</el-table-column>
				</el-table>
			</div>
			<div class="col-md-8">
        <h4 style="margin-top:25px">创建/编辑课程</h4>
				<el-form
					class="p-25"
					ref="form"
					:model="form"
					label-width="80px"
				>
					<el-form-item label="课程ID">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程名称">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程类别">
						<el-select
            style="width:100%"
							v-model="form.region"
							placeholder="请选择课程类别"
						>
							<el-option label="A" value="A"></el-option>
							<el-option label="B" value="B"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="课程学分">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程学时">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程人数">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="教师ID">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程描述">
						<el-input
							type="textarea"
							v-model="form.desc"
						></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="onSubmit"
							>保存</el-button
						>
						<el-button type="danger">删除</el-button>
						<el-button>取消</el-button>
					</el-form-item>
				</el-form>
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
				region: "",
				date1: "",
				date2: "",
				delivery: false,
				type: [],
				resource: "",
				desc: ""
			},
			formInline: {
				user: "",
				region: ""
			},
			tableData: [
				{
					date: "2016-05-02",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-04",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-01",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				},
				{
					date: "2016-05-03",
					name: "王小虎",
					address: "80"
				}
			]
		};
	},
	methods: {
		onSubmit() {
			console.log("submit!");
		}
	}
};
</script>

<style></style>
