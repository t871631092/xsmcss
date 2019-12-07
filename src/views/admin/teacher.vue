<template>
	<div class="bg-white h-100p p-25 container">
		<el-divider content-position="left"><h4>教师管理</h4></el-divider>
		<el-form :inline="true" :model="form" class="demo-form-inline">
			<el-form-item label="用户名">
				<el-input v-model="form.id" placeholder="输入教师Id"></el-input>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input
					v-model="form.name"
					placeholder="输入教师姓名"
				></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input
					v-model="form.password"
					placeholder="输入教师密码"
				></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">添加</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="tableData" height="80%" style="width: 100%">
			<el-table-column prop="id" label="Id"> </el-table-column>
			<el-table-column prop="name" label="姓名"> </el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button
						size="mini"
						type="danger"
						@click="handleDelete(scope.$index, scope.row)"
						>删除</el-button
					>
				</template>
			</el-table-column>
		</el-table>
		<div class="block m-t-25">
			<el-pagination
				@size-change="handleSizeChange"
				@current-change="handleCurrentChange"
				:current-page="page"
				:page-sizes="[10, 20, 50, 100]"
				:page-size="size"
				layout="total, sizes, prev, pager, next, jumper"
				:total="count"
			>
			</el-pagination>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			form: {
				id: "",
				name: "",
				password: ""
			},
			tableData: [],
			count: 0,
			page: 1,
			size: 10
		};
	},
	mounted() {
		this.getData();
	},
	methods: {
		handleSizeChange(val) {
			this.size = val;
			this.getData();
		},
		handleCurrentChange(val) {
			this.page = val;
			this.getData();
		},
		onSubmit() {
			let self = this;
			this.Post(
				"admin/teacher",
				{
					id: self.form.id,
					name: self.form.name,
					password: self.form.password
				},
				function(data) {
					if (data.success) {
						self.page = 1;
						self.tableData = data.data;
						self.count = data.count;
					} else {
						alert(data.msg);
					}
				}
			);
		},
		getData() {
			let self = this;
			console.log(self.size);
			this.Get(
				"admin/teacher",
				{ page: self.page - 1, size: self.size },
				function(data) {
					if (data.success) {
						self.tableData = data.data;
						self.count = data.count;
					} else {
						alert(data.msg);
					}
				}
			);
		},
		handleDelete(index, data1) {
			let self = this;
			this.Delete("admin/teacher", { id: data1.id }, function(data) {
				if (data.success) {
					self.page = 1;
					self.tableData = data.data;
					self.count = data.count;
				} else {
					alert(data.msg);
				}
			});
		}
	}
};
</script>

<style></style>
