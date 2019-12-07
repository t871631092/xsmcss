<template>
	<div class="bg-white height-100p padding-25 noscroll">
		<el-divider content-position="left"><h4>学生成绩录入</h4></el-divider>
		<div class="container">
			<el-tabs v-model="activeName" @tab-click="handleClick">
				<el-tab-pane
					v-for="(item, index) in courseData"
					v-bind:key="index"
					:label="item.name"
					:name="item.coures_id.toString()"
				></el-tab-pane>
			</el-tabs>
		</div>
		<el-row type="flex" class="height-100p" :gutter="10" justify="center">
			<el-col :md="12">
				<el-table :data="tableData" height="80%" style="width: 100%" empty-text="选择录入的课程">
					<el-table-column prop="student_name" label="姓名">
					</el-table-column>
					<el-table-column prop="score" label="成绩">
						<template scope="scope">
							<el-input
								size="small"
								v-model="scope.row.score"
								placeholder="请输入内容"
								@change="handleEdit(scope.$index, scope.row)"
							></el-input>
							<!-- <span>{{ scope.row.score }}</span> -->
						</template>
					</el-table-column>
					<el-table-column prop="isSave" label="已保存">
						<template scope="scope">
							<span>
								<i class="el-icon-check" v-if="scope.row.isSave == null ||
								scope.row.isSave == 0"></i>
								<i class="el-icon-close" v-if="!(scope.row.isSave == null ||
								scope.row.isSave == 0)"></i>
								</span>
						</template>
					</el-table-column>
					<el-table-column label="操作" width="150">
						<template slot-scope="scope">
							<el-button
								size="mini"
								type="success"
								@click="handleSave(scope.$index, scope.row)"
								>保存</el-button
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
			this.$set(this.tableData[index],"isSave",1);
			this.Save(this.cid, data.student_id, data.score, index);
		},
		handleSave(index, data) {
			this.$set(this.tableData[index],"isSave",1);
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
						self.courseData = data.data;
						self.count = data.count;
					} else {
						alert(data.msg);
					}
				}
			);
		},
		getData(data1) {
			let self = this;
			self.tableData = [];
			this.Get(
				"course/list",
				{ id: data1, page: self.page - 1, size: self.size },
				function(data) {
					if (data.success) {
						self.cid = data1;
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
