<template>
	<div class="height-100p noscroll container">
		<div class="bg-white padding-25 height-100p">
			<el-divider content-position="left"><h4>选修课成绩</h4></el-divider>
			<el-table
				ref="singleTable"
				:data="tableData"
				highlight-current-row
				@current-change="handleCurrentChange"
				height="85%"
				style="width: 100%;"
			>
				<el-table-column type="index" width="50"> </el-table-column>
				<el-table-column property="name" label="名称">
				</el-table-column>
				<el-table-column property="score_teacher" label="录入教师">
				</el-table-column>
				<el-table-column property="score" label="成绩" width="150">
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
	</div>
</template>

<script>
export default {
	data() {
		return {
			tableData: [],
			count: 0,
			page: 1,
			size: 10
		};
	},
	mounted() {
		this.getData();
	},
	computed: {},
	methods: {
		handleSizeChange(val) {
			this.size = val;
			this.getData();
		},
		handleCurrentChange(val) {
			this.page = val;
			this.getData();
		},
		getData() {
			let self = this;
			console.log(self.size);
			this.Get(
				"course/query",
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

<style lang="scss"></style>
