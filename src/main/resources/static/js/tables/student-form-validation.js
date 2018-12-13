$(document).ready(function() {
	$('#student-form').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {/*rules*/
			userid: {
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
			name: {
				message:'Invalid  name',
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
			dob: {

				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30,
						message: 'The length should between 0 to 30'
					},
					regexp: {
						regexp: /^\d{4}\-\d{1,2}\-\d{1,2}$/,
						message: 'Invalid Date format.Valid format is YYYY-MM-DD'
					}

				}
			},
			email: {
				message:'Invalid email',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30,
						message: 'The length should between 0 to 30'
					},
					regexp: {
						regexp: /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/,
						message: 'Invalid email'
					}
				}
			},
			phoneNumber: {
				message:'Invalid company name',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 10,
						message: 'Invalid number'
					},
					regexp: {
						regexp: /^((\\+[1-9]{1,4}[ \\-])|(\\([0-9]{2,3}\\)[ \\-])|([0-9]{2,4})[ \\-])?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/,
						message: 'Invalid number'
					}
				}
			},
			address: {
				message:'Invalid company name',
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
			password: {
				message:'Invalid company name',
				validators: {
					notEmpty: {
						message: 'cannot be empty'
					},
					stringLength: {
						min: 0,
						max: 30

					}
				},
				enabled: {
					message:'Invalid company name',
					validators: {
						notEmpty: {
							message: 'cannot be empty'
						},
						stringLength: {
							min: 0,
							max: 4,
							message: 'The length should between 0 to 4'
						},
						regexp: {
							regexp: /^[a-zA-Z_]+$/,
							message: 'Should only contain alphabets'
						}
					}
				},
				type: {
					message:'Invalid company name',
					validators: {
						notEmpty: {
							message: 'cannot be empty'
						},
						stringLength: {
							min: 0,
							max: 30,
							message: 'The length should between 0 to 30'
						},
						regexp: {
							regexp: /^[a-zA-Z_]+$/,
							message: 'Should only contain alphabets'
						}
					}
				}			
			}
		}
	});
});