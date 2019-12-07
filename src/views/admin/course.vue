<template>
	<div class="bg-white h-100p p-25 noscroll">
		<el-divider content-position="left"><h4>课程管理</h4></el-divider>
		<div class="row h-100p">
			<div class="col-md-4 p-25">
				<el-table
					ref="singleTable"
					:data="tableData"
					height="75%"
					highlight-current-row
					@current-change="handleSelectChange"
					style="width: 100%"
				>
					<el-table-column type="index"> </el-table-column>
					<el-table-column property="name" label="课程名称">
					</el-table-column>
					<el-table-column
						sortable
						property="category"
						label="课程类别"
					>
					</el-table-column>
				</el-table>
				<el-pagination
					class="m-t-25"
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
			<div class="col-md-8">
				<h4 style="margin-top:25px">创建/编辑课程</h4>
				<el-form
					class="p-25"
					ref="form"
					:model="form"
					label-width="80px"
				>
					<el-form-item label="课程名称">
						<el-input v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="课程类别">
						<el-select
							style="width:100%"
							v-model="form.category"
							allow-create
							filterable
							placeholder="请选择课程类别"
						>
							<el-option label="A" value="A"></el-option>
							<el-option label="B" value="B"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="课程学分">
						<el-input v-model="form.credit"></el-input>
					</el-form-item>
					<el-form-item label="课程学时">
						<el-input v-model="form.period"></el-input>
					</el-form-item>
					<el-form-item label="课程人数">
						<el-input v-model="form.capacity"></el-input>
					</el-form-item>
					<el-form-item label="课程教师">
						<el-select
							style="width:100%"
							default-first-option
							placeholder="选择教师"
							v-model="form.teacher_id"
						>
							<el-option
								v-for="item in teacher"
								:key="item.id"
								:label="item.name"
								:value="item.id"
							>
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="课程描述">
						<el-input
							type="textarea"
							v-model="form.description"
						></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="onSubmit"
							>保存</el-button
						>
						<el-button type="danger" @click="handleDelete"
							>删除</el-button
						>
						<el-button @click="onSubmit">取消</el-button>
						<el-button @click="newCourse">新建</el-button>
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
				coures_id: "",
				name: "",
				category: "",
				period: "",
				credit: "",
				capacity: "",
				teacher_id: "",
				description: ""
			},
			tableData: [],
			count: 0,
			page: 1,
			size: 10,
			teacher: []
		};
	},
	mounted() {
		this.getTeacher();
		this.getData();
	},
	methods: {
		handleSelectChange(val) {
			this.form = this.Clone(val);
		},
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
			this.Post("admin/course", self.form, function(data) {
				if (data.success) {
					self.page = 1;
					self.tableData = data.data;
					self.count = data.count;
				} else {
					alert(data.msg);
				}
			});
		},
		getData() {
			let self = this;
			console.log(self.size);
			this.Get(
				"admin/course",
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
		handleDelete() {
			let self = this;
			this.Delete(
				"admin/course",
				{ coures_id: self.form.coures_id },
				function(data) {
					if (data.success) {
						self.page = 1;
						console.log(data);
						self.tableData = data.data;
						self.count = data.count;
						self.newCourse();
					} else {
						alert(data.msg);
					}
				}
			);
		},
		getTeacher() {
			let self = this;
			this.Get("admin/teacher", { page: 0, size: 10000000 }, function(
				data
			) {
				if (data.success) {
					self.teacher = data.data;
				} else {
					alert(data.msg);
				}
			});
		},
		newCourse() {
			let self = this;
			self.form = {
				coures_id: "",
				name: "",
				category: "",
				period: "",
				credit: "",
				capacity: "",
				teacher_id: "",
				description: ""
			};
		}
	}
};
</script>

<style></style>
