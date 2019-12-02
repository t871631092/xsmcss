<template>
	<div class="height-100p">
		<el-table
			:data="tableData"
			class="tb-edit"
            height="85%"
			style="width: 100%"
			highlight-current-row
			@row-click="handleCurrentChange"
		>
			<el-table-column label="姓名">
				<template scope="scope">
					<span>{{ scope.row.name }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="address" label="成绩">
				<template scope="scope">
					<el-input
						size="small"
						v-model="scope.row.address"
						placeholder="请输入内容"
						@change="handleEdit(scope.$index, scope.row)"
					></el-input>
					<span>{{ scope.row.address }}</span>
				</template>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<!--<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
					<el-button
						size="small"
						@click="handleDelete(scope.$index, scope.row)"
						>保存</el-button
					>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
export default {
	data() {
		return {
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
		handleCurrentChange(row, event, column) {
			console.log(row, event, column, event.currentTarget);
		},
		handleEdit(index, row) {
			console.log(index, row);
		},
		handleDelete(index, row) {
			console.log(index, row);
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
.height-100p{
    height: 100%;
}
</style>
