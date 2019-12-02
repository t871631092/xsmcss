<template>
	<div class="height-100p noscroll">
		<div class="bg-white padding-25 height-100p">
			<div class="height-50p">
				<el-divider content-position="left"
					><h4>选择课程</h4></el-divider
				>
				<el-table
					ref="singleTable"
					:data="tableData"
					highlight-current-row
					@current-change="handleCurrentChange"
					height="85%"
					style="width: 100%;"
				>
					<el-table-column type="index" width="50"> </el-table-column>
					<el-table-column type="expand">
						<template slot-scope="props">
							<el-form
								label-position="left"
								inline
								class="demo-table-expand"
							>
								<el-form-item label="商品名称">
									<span>{{ props.row.name }}</span>
								</el-form-item>
								<el-form-item label="所属店铺">
									<span>{{ props.row.shop }}</span>
								</el-form-item>
								<el-form-item label="商品 ID">
									<span>{{ props.row.id }}</span>
								</el-form-item>
								<el-form-item label="店铺 ID">
									<span>{{ props.row.shopId }}</span>
								</el-form-item>
								<el-form-item label="商品分类">
									<span>{{ props.row.category }}</span>
								</el-form-item>
								<el-form-item label="店铺地址">
									<span>{{ props.row.address }}</span>
								</el-form-item>
								<el-form-item label="商品描述">
									<span>{{ props.row.desc }}</span>
								</el-form-item>
							</el-form>
						</template>
					</el-table-column>
					<el-table-column property="date" label="名称">
					</el-table-column>
					<el-table-column property="name" label="主讲教师">
					</el-table-column>
					<el-table-column property="address" label="学分">
					</el-table-column>
					<el-table-column property="address" label="学时">
					</el-table-column>
					<el-table-column label="操作" width="150">
						<template slot-scope="scope">
							<el-button
								size="mini"
								@click="handleDelete(scope.$index, scope.row)"
								>添加</el-button
							>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<div class="height-40p">
				<el-divider content-position="left"><h4>已选</h4></el-divider>
				<el-table
					ref="singleTable"
					:data="tableData"
					highlight-current-row
					@current-change="handleCurrentChange"
					height="75%"
					style="width: 100%"
				>
					<el-table-column type="index" width="50"> </el-table-column>
					<el-table-column property="date" label="名称">
					</el-table-column>
					<el-table-column property="name" label="主讲教师">
					</el-table-column>
					<el-table-column property="address" label="学分">
					</el-table-column>
					<el-table-column property="address" label="学时">
					</el-table-column>
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
			</div>
			<div class="height-10p">
				<el-button type="success" round>提交选修课程</el-button>
			</div>
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

<style lang="scss">

.noscroll {
	overflow: hidden;
}
.demo-table-expand {
	font-size: 0;
}
.demo-table-expand label {
	width: 90px;
	color: #99a9bf;
}
.demo-table-expand .el-form-item {
	margin-right: 0;
	margin-bottom: 0;
	width: 50%;
}
</style>
