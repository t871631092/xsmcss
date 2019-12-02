<template>
	<div class="bg-white height-100p padding-25 noscroll">
		<el-divider content-position="left"><h4>学生成绩录入</h4></el-divider>
		<div class="container">
			<el-tabs v-model="activeName" @tab-click="handleClick">
				<el-tab-pane label="高等数学" name="1"></el-tab-pane>
				<el-tab-pane label="计算机" name="2"></el-tab-pane>
			</el-tabs>
		</div>
		<el-row type="flex" class="height-100p" :gutter="10" justify="center">
			<el-col :md="12"> <router-view /></el-col
		></el-row>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">

</style>
