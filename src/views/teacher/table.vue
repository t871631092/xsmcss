<template>
	<div class="bg-white height-100p padding-25 noscroll">
		<el-divider content-position="left"><h4>课程查询</h4></el-divider>
		<el-row type="flex" class="height-100p" :gutter="10" justify="center">
			<el-col :md="12">
				<el-table :data="tableData" height="85%" style="width: 100%">
					<el-table-column type="index" width="50"> </el-table-column>
					<el-table-column prop="name" label="名称">
					</el-table-column>
					<el-table-column prop="category" label="类别">
					</el-table-column>
					<el-table-column prop="credit" label="学分">
					</el-table-column>
					<el-table-column prop="period" label="学时">
					</el-table-column>
					<el-table-column prop="capacity" label="容纳人数">
					</el-table-column>
					<el-table-column prop="unsurplus" label="确认人数">
					</el-table-column>
					<el-table-column prop="avgscore" label="平均成绩">
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
				</div> </el-col
		></el-row>
	</div>
</template>

<script>
export default {
	data() {
		return {
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
			console.log(data.name, self.activeName);
			this.getData(data.name);
		},
		getCourseData() {
			let self = this;
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
