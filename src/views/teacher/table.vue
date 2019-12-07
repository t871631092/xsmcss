<template>
	<div class="bg-white height-100p padding-25 noscroll">
		<el-divider content-position="left"><h4>课程查询</h4></el-divider>

		<el-form :inline="true" :model="form" class="demo-form-inline">
			<el-form-item label="条件">
				<el-switch
					v-model="form.select"
					active-color="#13ce66"
					inactive-color="#ff4949"
					active-text="大于"
					inactive-text="小于"
				>
				</el-switch>
			</el-form-item>
			<el-form-item>
				<el-input
					v-model="form.period"
					placeholder="学时数"
				></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">查询</el-button>
			</el-form-item>
		</el-form>

		<el-table :data="tableData" height="75%" style="width: 100%">
			<el-table-column type="index" width="50"> </el-table-column>
			<el-table-column prop="name" label="名称"> </el-table-column>
			<el-table-column prop="category" label="类别"> </el-table-column>
			<el-table-column prop="credit" label="学分"> </el-table-column>
			<el-table-column prop="period" label="学时"> </el-table-column>
			<el-table-column prop="capacity" label="容纳人数">
			</el-table-column>
			<el-table-column prop="unsurplus" label="确认人数">
			</el-table-column>
			<el-table-column prop="avgScore" label="平均成绩">
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
				period: "",
				select:true
			},
			tableData: [],
			courseData: [],
			count: 0,
			page: 1,
			size: 10,
			activeName: "",
			cid: null
		};
	},
	mounted() {
		this.getCourseData();
	},
	methods: {
		onSubmit(){
			this.getCourseData();
		},
		handleEdit(index, data) {
			this.$set(this.tableData[index], "isSave", 1);
			this.Save(this.cid, data.student_id, data.score, index);
		},
		handleSave(index, data) {
			this.$set(this.tableData[index], "isSave", 1);
			this.Save(this.cid, data.student_id, data.score, index);
		},
		Save(cid, sid, s, index) {
			let self = this;
			this.Post(
				"course/addscore",
				{
					course_id: cid,
					student_id: sid,
					score: s
				},
				function(data) {
					if (data.success) {
						self.tableData[index].isSave = 0;
					} else {
						alert(data.msg);
					}
				}
			);
		},
		handleSizeChange(val) {
			this.size = val;
			this.getData();
		},
		handleCurrentChange(val) {
			this.page = val;
			this.getData();
		},
		handleClick(data) {
			this.getData(data.name);
		},
		getCourseData() {
			let self = this;
			let param;
			if (self.form.period!=null&/^\d+$/.test(self.form.period)) {
				param={ page: self.page - 1, size: self.size ,period : `${self.form.select?">=":"<="} ${self.form.period}`};
			}else{
				param={ page: self.page - 1, size: self.size };
			}
			this.Get(
				"admin/course",
				param,
				function(data) {
					if (data.success) {
						self.tableData = data.data;
						self.count = data.count;
					} else {
						alert(data.msg);
					}
				}
			);
		}
	}
};
</script>

<style lang="scss">
.tb-edit .el-input {
	display: none;
}
.tb-edit .current-row .el-input {
	display: block;
}
.tb-edit .current-row .el-input + span {
	display: none;
}
.height-100p {
	height: 100%;
}
</style>
