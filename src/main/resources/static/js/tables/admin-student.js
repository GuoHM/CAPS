$(document).ready(function() {

	var oTableInit = new TableInit();
	oTableInit.Init();

	var oButtonInit = new ButtonInit();
	oButtonInit.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$('#student-table').bootstrapTable({
			method : 'get', 
			url : "/admin/api/liststudents",
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
			//showExport: true,                     
			exportDataType: "basic",              
			showColumns : true,
			columns : [ {
				align : "center",
				title : 'Student ID',
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
				title : 'Date Of Birth',
				sortable : true,
				sortable : true,
				field : 'dob'
			},{
				align : "center",
				title : 'Email',
				sortable : true,
				sortable : true,
				field : 'email'
			},{
				align : "center",
				title : 'Phone Number',
				sortable : true,
				sortable : true,
				field : 'phoneNumber'
			},{
				align : "center",
				title : 'Address',
				sortable : true,
				sortable : true,
				field : 'address'
			},{
				align : "center",
				title : 'password',
				visible : false,
				sortable : true,
				sortable : true,
				field : 'password'
			},{
				align : "center",
				title : 'Is Active User',
				visible : true,
				sortable : true,
				sortable : true,
				field : 'enabled'
			},{
				align : "center",
				title : 'Type of User',
				sortable : true,
				visible : true,
				sortable : true,
				field : 'type'
					
			},{
				align : "center",
				title : 'Edit Options',
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
				$("#editStudentModal").modal('show');
				$("#studentIDEdit").val(row.userid);
				$("#studentNameEdit").val(row.name);
				$("#dobIDEdit").val(row.dob);
				$("#emailEdit").val(row.email);
				$("#phoneNumberEdit").val(row.phoneNumber);
				$("#addressEdit").val(row.address);
				$("#passwordEdit").val(row.password);
				$("#enabledEdit").val(row.enabled);
				$("#typeEdit").val(row.type);
			},
			'click .remove': function (e, value, row, index) {
				$("#deleteStudentModal").modal('show');
				var url = 'deleteStudent/' + row.userid;
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
			$("#student-table").bootstrapTable('destroy');
			var oTable = new TableInit();
			oTable.Init();
		})


	};

	return oInit;
};
