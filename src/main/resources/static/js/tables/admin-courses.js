$(document).ready(function() {

	var oTableInit = new TableInit();
	oTableInit.Init();

	var oButtonInit = new ButtonInit();
	oButtonInit.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#course-table').bootstrapTable({
			method : 'get', 
			url : "/admin/api/listCourses",
			toolbar: '#toolbar',             
			striped : true, 
			cache : false, 
			pagination : true, 
			sortable : true, 
			sortOrder : "asc", 
			queryParams : oTableInit.queryParams,
			sidePagination : "client", 
			pageNumber : 1, 
			pageSize : 10, 
			pageList : [ 10, 25, 50, 100 ], 
			search: true, 
			strictSearch : true,
			queryParamsType : "",
			showRefresh : true, 
			minimumCountColumns : 2, 
			clickToSelect : false, 
			height : 500, 
			// uniqueId: "ID", 
			showToggle : true, 
			cardView : false, 
			detailView : false, 
			showExport: true,                     
			exportDataType: "basic",              
			showColumns : true,
			columns : [ {
				align : "center",
				title : 'Course ID',
				sortable : true,
				sortable : true,
				field : 'courseid'
			},{
				align : "center",
				title : 'Course Name',
				visible : true,
				sortable : true,
				field : 'course_name'
			},{
				align : "center",
				title : 'Lecture ID',
				sortable : true,
				sortable : true,
				field : 'lecture_id'
			}],
			formatLoadingMessage : function() {
				return "loading...";
			}
		});
	};

	// params
	oTableInit.queryParams = function(params) {

		var temp = {

		};
		return temp;
	};

	
	return oTableInit;
};

var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};

	oInit.Init = function() {
		// button
		$('#btnListCustomer').click(function() {
			$("#course-table").bootstrapTable('destroy');
			var oTable = new TableInit();
			oTable.Init();
		})


	};

	return oInit;
};
