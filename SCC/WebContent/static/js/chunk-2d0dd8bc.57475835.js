(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0dd8bc"],{"829e":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"height-100p noscroll container"},[a("div",{staticClass:"bg-white padding-25 height-100p"},[a("el-divider",{attrs:{"content-position":"left"}},[a("h4",[t._v("选修课成绩")])]),a("el-table",{ref:"singleTable",staticStyle:{width:"100%"},attrs:{data:t.tableData,"highlight-current-row":"",height:"85%"}},[a("el-table-column",{attrs:{type:"index",width:"50"}}),a("el-table-column",{attrs:{property:"name",label:"名称"}}),a("el-table-column",{attrs:{property:"score_teacher",label:"录入教师"}}),a("el-table-column",{attrs:{property:"score",label:"成绩",width:"150"}})],1),a("el-pagination",{staticClass:"m-t-25",attrs:{"current-page":t.page,"page-sizes":[10,20,50,100],"page-size":t.size,layout:"total, sizes, prev, pager, next, jumper",total:t.count},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)])},i=[],s={data:function(){return{tableData:[],count:0,page:1,size:10}},mounted:function(){this.getData()},computed:{},methods:{handleSizeChange:function(t){this.size=t,this.getData()},handleCurrentChange:function(t){this.page=t,this.getData()},getData:function(){var t=this;console.log(t.size),this.Get("course/query",{page:t.page-1,size:t.size},(function(e){e.success?(t.tableData=e.data,t.count=e.count):alert(e.msg)}))}}},l=s,r=a("2877"),o=Object(r["a"])(l,n,i,!1,null,null,null);e["default"]=o.exports}}]);
//# sourceMappingURL=chunk-2d0dd8bc.57475835.js.map