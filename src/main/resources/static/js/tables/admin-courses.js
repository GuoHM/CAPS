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
			url : "/admin/api/listcourses",
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
				field : 'courseName'
			},{
				align : "center",
				title : 'Lecture Name',
				sortable : true,
				sortable : true,
				field : 'account.name'
				
			},{
				align : "center",
				title : 'Class Size',
				sortable : true,
				sortable : true,
				field : 'classSize'
			},{
				align : "center",
				title : 'Start Date',
				sortable : true,
				sortable : true,
				field : 'startDate'
			},{
				align : "center",
				title : 'Course Duration',
				sortable : true,
				sortable : true,
				field : 'duration'
			},{
				align : "center",
				title : 'Course Status',
				sortable : true,
				sortable : true,
				field : 'courseStatus'
			},{
				align : "center",
				title : 'Credit',
				sortable : true,
				sortable : true,
				field : 'credit'
			},{
				align : "center",
				title : 'option',
				visible : true,
				sortable : false,
				events: operateEvents,
				formatter: operateFormatter
			
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
	function operateFormatter(value, row, index) {
		return [
		        '<a class="like" href="javascript:void(0)" title="Edit">',
		        '<span class="glyphicon glyphicon-cog"></span>',
		        '</a>',
		        '<a class="remove" href="javascript:void(0)" title="Remove">',
		        '<span class="glyphicon glyphicon-remove"></span>',
		        '</a>'
		        ].join('');
	}
	operateEvents = {
			'click .like': function (e, value, row, index) {
				$("#editCourseModal").modal('show');
				$("#courserIDEdit").val(row.courseid);
				$("#courseNameEdit").val(row.courseName);
				$("#lectureIDEdit").val(row.account.userid);
				$("#classSizeEdit").val(row.classSize);
				$("#startDateEdit").val(row.startDate);
				$("#durationEdit").val(row.duration);
				$("#courseStatusEdit").val(row.courseStatus);
				$("#creditEdit").val(row.credit);
			},
			'click .remove': function (e, value, row, index) {
				$("#deleteCourseModal").modal('show');
				var url = 'deleteCourse/' + row.courseid;
				$("#deleteForm").attr('action',url);
			}
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
