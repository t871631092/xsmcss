import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "在线选课系统 Course Selection System",
		meta: {
			permission: 0
		},
		component: Home
	},
	{
		path: "/about",
		name: "about",
		meta: {
			permission: 0
		},
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
			import(/* webpackChunkName: "about" */ "../views/About.vue")
	},
	{
		path: "/student",
		children: [
			{
				path: "course",
				name: "学生选课",
				meta: {
					permission: 1
				},
				component: () => import("../views/student/course.vue")
			},
			{
				path: "score",
				name: "学生成绩",
				meta: {
					permission: 1
				},
				component: () => import("../views/student/score.vue")
			}
		]
	},
	{
		path: "/account",
		redirect: "/",
		children: [
			{
				path: "student",
				name: "学生",
				meta: {
					permission: 1
				},
				component: () => import("../views/account.vue")
			},
			{
				path: "teacher",
				name: "教师",
				meta: {
					permission: 2
				},
				component: () => import("../views/account.vue")
			}
		]
	},
	{
		path: "/coursemark",
		name: "评分",
		meta: {
			permission: 2
		},
		component: () => import("../views/teacher/mark.vue")
	},
	{
		path: "/admin",
		name: "管理",
		children: [
			{
				path: "student",
				name: "学生管理",
				meta: {
					permission: 3
				},
				component: () => import("../views/admin/student.vue")
			},
			{
				path: "/teacher",
				name: "教师管理",
				meta: {
					permission: 3
				},
				component: () => import("../views/admin/teacher.vue")
			},
			{
				path: "/course",
				name: "课程管理",
				meta: {
					permission: 3
				},
				component: () => import("../views/admin/course.vue")
			}
		]
	}
];

const router = new VueRouter({
	routes
});

export default router;
