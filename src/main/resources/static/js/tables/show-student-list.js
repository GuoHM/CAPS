$(document).ready(function() {
	$('#addEnrollmentModal').on('shown.bs.modal', function () {
		  // 执行一些动作...
			addTableInit = new myTableInit();
		    addTableInit.Init();
		    
		    
		})
	var addButtonInit = new myButtonInit();
	addButtonInit.Init();	

	
});

var myTableInit = function() {
	var addTableInit = new Object();
	addTableInit.Init = function() {
		var courseid = document.getElementById("courseid").value;
		$('#addenrollment-table').bootstrapTable({
			method : 'get', 
			url : '/admin/api/enrollment-stuList?courseid='+courseid,
			toolbar: '#addtoolbar',                //工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : addTableInit.queryParams,// 传递参数（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			queryParamsType : "",
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : false, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			// uniqueId: "ID", //每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			showExport: false,                     //是否显示导出
			exportDataType: "basic",              //basic', 'all', 'selected'.
			showColumns : true,
			columns : [{
				align : "center",
				title : 'Student Id',
				sortable : true,
				sortable : true,
				field : 'userid'
			},{
				align : "center",
				title : 'Student Name',
				visible : true,
				sortable : true,
				field : 'name'
			},{
				align : "center",
				title : 'Date of Birth',
				sortable : true,
				sortable : true,
				field : 'dob'
			},{
				align : "center",
				title : '',
				sortable : true,
				sortable : true,
				formatter: operateFormatter
			}],
			formatLoadingMessage : function() {
				return "loading...";
			}
		});
	};
   

	// params
	addTableInit.queryParams = function(params) {

		var temp = {
				//courseid : $("#course-list").val()
		};
		return temp;
	};
	
	function operateFormatter(value, row, index) {
		var courseid = document.getElementById("courseid").value;
		var url = 'addEnrollment/'+row.userid+'/'+courseid;
		return [
		        '<a class="like" href="'+url+'" title="Add">',
		        '<span class="glyphicon glyphicon-plus"></span>',
		        '</a>',
		        ].join('');
	}

	return addTableInit;
};



var myButtonInit = function() {
	var addInit = new Object();
	var postdata = {};

	addInit.Init = function() {
	}
	return addInit;
};