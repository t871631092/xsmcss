<template>
	<div class="bg-white h-100p p-25">
		<el-divider content-position="left"><h4>教师管理</h4></el-divider>
		<el-form :inline="true" :model="formInline" class="demo-form-inline">
			<el-form-item label="Id">
				<el-input
					v-model="formInline.user"
					placeholder="输入教师Id"
				></el-input>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input
					v-model="formInline.user"
					placeholder="输入教师姓名"
				></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input
					v-model="formInline.user"
					placeholder="输入教师密码"
				></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">查询</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="tableData" height="85%" style="width: 100%">
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
	</div>
</template>

<script>
export default {
    data() {
      return {
        formInline: {
          user: '',
          region: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>

<style></style>
