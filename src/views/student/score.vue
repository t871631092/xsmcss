<template>
	<div class="height-100p noscroll">
		<div class="bg-white padding-25 height-100p">
			<el-divider content-position="left"><h4>选修课成绩</h4></el-divider>
			<el-table
				ref="singleTable"
				:data="tableData"
				highlight-current-row
				@current-change="handleCurrentChange"
				height="90%"
				style="width: 100%;"
			>
				<el-table-column type="index" width="50"> </el-table-column>
				<el-table-column property="date" label="名称">
				</el-table-column>
				<el-table-column property="name" label="主讲教师">
				</el-table-column>
				<el-table-column property="address" label="成绩" width="150">
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			tableData: [
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{},
				{}
			]
		};
	},
	computed: {}
};
</script>

<style lang="scss"></style>
