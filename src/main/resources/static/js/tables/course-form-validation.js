$(document).ready(function() {
	$('#course-form').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {/*rules*/
			courseid: {
				message: 'Invalid ID',
				validators: {
					notEmpty: {
						message: 'Cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 3,
						message: 'The length should between 0 to 3'
					}

				}
			},
			courseName: {
				message:'Course  name',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 100,
						message: 'The length should between 0 to 100'
					},
					regexp: {
						regexp: /^(?![\s.]+$)[a-zA-Z\s.]*$/,
						message: 'Should only contain alphabets'
					}
				}
			},
			
			classSize: {
				message:'Invalid Class Size',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30,
						message: 'Invalid number'
					}
					
				}
			},
			startDate: {
				message:'Invalid Date',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 10,
						message: 'Invalid Date'
					},
					regexp: {
						regexp:/^\d{4}\-\d{1,2}\-\d{1,2}$/,
						message:'Invalid Date format.Valid format is YYYY-MM-DD'
					}
				}
			},
			duration: {
				message:'Invalid duration',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30,
						message: 'The length should between 0 to 30'
					}
				}
			},
			courseStatus: {
				message:'Invalid Course Status',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30

					},
					regexp: {
						regexp: /^(?![\s.]+$)[a-zA-Z\s.]*$/,
						message: 'Should only contain alphabets'
					}
				},
				credit: {
					message:'Invalid credit',
					validators: {
						notEmpty: {
							message: 'cannot be empty'
						},
						stringLength: {
							min: 0,
							max: 4,
							message: 'The length should between 0 to 2'
						}
					}
				}		
			}
		}
	});
});