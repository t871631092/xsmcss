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
					height="80%"
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
								<el-form-item label="课程描述">
									<span>{{ props.row.description }}</span>
								</el-form-item>
							</el-form>
						</template>
					</el-table-column>
					<el-table-column property="name" label="名称">
					</el-table-column>
					<el-table-column property="teacher_name" label="主讲教师">
					</el-table-column>
					<el-table-column property="category" label="分类">
					</el-table-column>
					<el-table-column property="credit" label="学分">
					</el-table-column>
					<el-table-column property="period" label="学时">
					</el-table-column>
					<el-table-column label="操作" width="150">
						<template slot-scope="scope">
							<el-button
								size="mini"
								@click="handleadd(scope.$index, scope.row)"
								>添加</el-button
							>
						</template>
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
			<div class="height-40p">
				<el-divider content-position="left"><h4>已选</h4></el-divider>
				<el-table
					ref="singleTable"
					:data="selectData"
					highlight-current-row
					height="70%"
					style="width: 100%"
				>
					<el-table-column type="index" width="50"> </el-table-column>
					<el-table-column property="name" label="名称">
					</el-table-column>
					<el-table-column property="teacher_name" label="主讲教师">
					</el-table-column>
					<el-table-column property="category" label="分类">
					</el-table-column>
					<el-table-column property="credit" label="学分">
					</el-table-column>
					<el-table-column property="period" label="学时">
					</el-table-column>
					<el-table-column label="操作" width="150">
						<template slot-scope="scope">
							<el-button
								size="mini"
								type="danger"
								:disabled="scope.row.status==1"
								@click="handleDelete(scope.$index, scope.row)"

								>删除</el-button
							>
						</template>
					</el-table-column>
				</el-table>
								<el-pagination
					class="m-t-25"
					@size-change="handleSizeChange1"
					@current-change="handleCurrentChange1"
					:current-page="sPage"
					:page-sizes="[10, 20, 50, 100]"
					:page-size="sSize"
					layout="total, sizes, prev, pager, next, jumper"
					:total="count1"
				>
				</el-pagination>
			</div>
			<div class="height-10p">
				<el-button type="success" round @click="comfirm">提交选修课程</el-button>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			tableData: [
			],
			count:0,
			page:1,
			size:10,
			selectData:[],
			sPage:1,
			sSize:10,
			count1:0,
		};
	},
	computed: {},
	mounted() {
		this.getCouresData();
		this.getSelectData();
	},
	methods: {
		comfirm(){
			let self=this;
			this.Get("course/comfirm",{},function(data){
				if (data.success) {
					alert("确认成功");
					self.getCouresData();
				}else{
					alert("确认失败");
				}
			})
		}	,
		handleSizeChange(val) {
			this.size = val;
			this.getCouresData();
		},
		handleCurrentChange(val) {
			this.page = val;
			this.getCouresData();
		},
		handleSizeChange1(val) {
			this.sSize = val;
			this.getCouresData();
		},
		handleCurrentChange1(val) {
			this.sPage = val;
			this.getCouresData();
		},
		getCouresData() {
			let self = this;
			this.Get("admin/course", {page:self.page-1,size:self.size}, function(data) {
				if (data.success) {
					self.tableData = data.data;
					self.count=data.count;
				} else {
					alert("失败：" + data.msg);
				}
			});
		},
		handleadd(index,data){
			let self = this;
			this.Post("course/select", { id: data.coures_id }, function(data) {
				if (data.success) {
					self.page = 1;
					self.selectData = data.data;
					self.count = data.count;
				} else {
					alert(data.msg);
				}
			});
		},
		handleDelete(index, data1) {
			let self = this;
			this.Delete("course/select", { id: data1.coures_id }, function(data) {
				if (data.success) {
					self.getSelectData();
				} else {
					alert(data.msg);
				}
			});
		},
		getSelectData(){
			let self = this;
			this.Post("course/query", {page:self.page-1,size:self.size}, function(data) {
				if (data.success) {
					self.selectData = data.data;
					self.count1=data.count;
				} else {
					alert("失败：" + data.msg);
				}
			});
		}
	}
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
