'use strict';

App
		.controller(
				'UserController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$timeout',
						'$mdDialog',
						'UserService',
						'departmentService',
						function($window, $scope, $rootScope, $timeout,
								$mdDialog, UserService, departmentService) {
							var self = this;
							self.user = {
								user_id : 0,
								firstName : '',
								lastName : '',
								email : '',
								password : '',
								confirmPassword : '',
								gender : '',
								address : '',
								blockingReason : '',
								dob : '',
								skillsets : [],
								role1 : 0,
								role2 : 0,
								department : ''
							};

							self.showerrors = false;
							self.showaddwindow = false;
							self.showlistwindow = true;
							self.userroleerror = false;
							self.showsuccmsg = false;
							self.showerrmsg = false;
							self.showNotification = false;

							self.emailerr = false;
							self.pwderr = false;

							self.val = false;

							self.succmsg = '';
							self.errmsg = '';
							self.users = [];

							self.load = function() {

								self.showlistwindow = true;

								self.userroleerror = false;
								self.user.gender = "M";
								self.fetchDepartmentDetails();

								self.fetchAllUsers(self.user);
								console.log('List All Users Successfully',
										self.user);

							};
							self.fetchDepartmentDetails = function() {
								departmentService.fetchDepartmentDetails()
										.then(function(response) {
											self.departmentlist = response;
										}, function(errResponse) {
											console.error('Error');
										});

							}
							self.fetchAllUsers = function() {
								UserService
										.fetchAllUsers()
										.then(
												function(response) {
													self.users = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							self.load();

							angular
									.element(document)
									.ready(
											function() {
												$('#i_file')
														.change(
																function(event) {
																	var tmppath = URL
																			.createObjectURL(event.target.files[0]);
																	$(
																			"#profileimage")
																			.fadeIn(
																					"fast")
																			.attr(
																					'src',
																					URL
																							.createObjectURL(event.target.files[0]));

																	$(
																			"#disp_tmp_path")
																			.html(
																					"Temporary Path(Copy it and try pasting it in browser address bar) --> <strong>["
																							+ tmppath
																							+ "]</strong>");
																});
											});

							self.add = function() {
								self.user.user_id = 0;
								self.showaddwindow = true;
								self.showlistwindow = false;
								self.user.dob = new Date();

							}

							self.hideandshow = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
							}

							self.showandhide = function() {
								self.reset();
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.fetchAllUsers();

							}

							self.deleteUser = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													UserService
															.deleteUser(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllUsers();
																		$rootScope
																				.showNotification(
																						"User Deleted Successfully",
																						true);
																	},
																	function(
																			errResponse) {

																		self
																				.fetchAllUsers();
																		$rootScope
																				.showNotification(
																						"Delete User Failed",
																						false);

																	});

												}, function() {
												});

							};

							self.edit = function(id) {
								
								self.x = new Date().getMilliseconds();
								self.user.department = '';
								self.val = true;
								self.user.user_id = id;
								self.showerrors = false;
								console.log('id to be edited:', id);
								UserService
										.fetchUserById(id)
										.then(
												function(response) {
													self.user = response;
													if (response.departmentname != null) {
														self.user.department = response.department
																+ '';
													}

													if (response.dob === ""
															|| response.dob === null
															|| typeof response.dob === "undefined") {

														self.user.dob = new Date();
													} else {

														self.user.dob = new Date(
																self.user.dob);
													}
													self.user.firstName = response.firstName
															+ " "
															+ response.lastName
													$scope.tags = [];
													angular
															.forEach(
																	self.user.skills,
																	function(
																			value,
																			index) {
																		$scope.tags
																				.push({
																					text : value
																				});
																	})

													self.user.skillsets;
												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});
								self.hideandshow();

							};
							self.block = function(id) {
								// Appending dialog to document.body to cover
								// sidenav in docs app
								var confirm = $mdDialog.prompt().title(
										'Block User').textContent(
										'What is the Blocking Reason?')
										.placeholder('Blocking Reason').ok(
												'Yes').cancel('No');

								$mdDialog
										.show(confirm)
										.then(
												function() {
													UserService
															.BlockUserById(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllUsers();
																		$rootScope
																				.showNotification(
																						"User blocked successfully",
																						true);
																	},
																	function(
																			errResponse) {

																		self
																				.fetchAllUsers();
																		console
																				.error('Error while unblocking User.'
																						+ errResponse);

																		$rootScope
																				.showNotification(
																						"Block user failed",
																						false);

																	});

												}, function() {
												});

							};

							self.unBlock = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Unblock User!!').textContent(
										'Are you sure?').ariaLabel('Lucky day')
										.ok('Yes').cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													UserService
															.unBlockUser(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllUsers();
																		$rootScope
																				.showNotification(
																						"User unblocked successfully",
																						true);
																	},
																	function(
																			errResponse) {

																		self
																				.fetchAllUsers();
																		console
																				.error('Error while unblocking User.'
																						+ errResponse);

																		$rootScope
																				.showNotification(
																						"Unblock User Failed",
																						false);

																	});

												}, function() {
												});

							};

							self.showlist = function() {
								self.showlistwindow = true;
								self.showaddwindow = false;
								self.fetchAllUsers();

							}

							self.checkValidation = function() {
								if (self.user.role1 == "0"
										&& self.user.role2 == "0") {
									self.userroleerror = true;
								} else {
									self.userroleerror = false;
								}

							}

							self.submit = function($event) {
								self.checkValidation();
								if ($scope.addUserForm.$valid) {

									var values = self.user.firstName.split(" ");

									self.user.firstName = values[0];
									self.user.lastName = values[1];

									if (self.user.user_id == 0) {
										console.log('Saving New User',
												self.user);
										self.saveUser();
									} else {
										self.updateUser(self.user,
												self.user.user_id);

										console.log('User updated with id ',
												self.user.user_id);
									}
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}

							};

							self.updateUser = function(user, user_id) {
								
								
								self.showerrors = false;
								self.user.skillsets = [];
								angular.forEach($scope.tags, function(value,
										index) {
									if (value != null && value.text != null)
										self.user.skillsets.push(value.text);
								});

								var file = $scope.myFile;

								console.log('file is ');
								console.dir(file);

								var uploadUrl = "updateuser/";

								UserService
										.updateUser(file, user, uploadUrl,
												user_id)
										.then(
												function(response) {
													self.showandhide();
													$rootScope
															.showNotification(
																	"User updated successfuly",
																	true);
												},

												function(errResponse) {
													console
															.error('Error while updating User.');

													$rootScope
															.showNotification(
																	"User Updation Failed",
																	false);
												});
							};

							self.saveUser = function(user) {
								self.showerrors = false;
								self.user.skillsets = [];

								angular.forEach($scope.tags, function(value,
										index) {
									self.user.skillsets.push(value.text);
								});

								var file = $scope.myFile;

								console.log('file is ');
								console.dir(file);

								var uploadUrl = "saveUser/";

								UserService
										.saveUser(file, self.user, uploadUrl)
										.then(
												function(response) {
													if (response.errorCode == 1) {
														self.showandhide();
														$rootScope
																.showNotification(
																		"New User Added Successfully",
																		true);
													} else if (response.errorCode == 2) {
														self.emailerr = true;

													} else if (response.errorCode == 3) {
														self.pwderr = true;
													} else {
														self.showandhide();
														$rootScope
																.showNotification(
																		"Add New User Failed",
																		false);
													}
												},
												function(errResponse) {
													self.showandhide();
													$rootScope
															.showNotification(
																	"Add New User Failed",
																	false);
												});

							};

							$scope.loadTags = function(query) {
								UserService
										.fetchSkills(query)
										.then(
												function(response) {
													self.skillSet = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Skills');
												});
								$scope.skills = [];
								angular.forEach(self.skillSet, function(value,
										index) {

									$scope.skills.push({
										text : value
									});

								})
								return $scope.skills;

							};

							self.deleteUserImage = function() {
								UserService
										.deleteUserImage(self.user.user_id)
										.then(
												function(response) {

													if (response) {
														$("#profileimage")
																.fadeIn("fast")
																.attr('src',
																		"./images/defaultuser.png");

														self.user.imageExist = false;
													}

												}, function(errResponse) {
													console.error('Error');
												});

							}
							self.reset = function() {

								$scope.myFile = null;
								
								$("#profileimage").fadeIn("fast").attr('src',
										"./images/defaultuser.png");

								self.user.imageExist = false;

								self.val = false;
								self.emailerr = false;
								self.pwderr = false;
								self.userroleerror = false;
								$scope.tags = [];
								self.user.firstName = '';
								self.user.lastName = '';
								self.user.email = '';
								self.user.password = '';
								self.user.phoneNumber = '';
								self.user.confirmPassword = '';
								self.user.address = '';
								self.user.gender = "M";
								self.user.blockingReason = '';
								self.user.dob = '';
								self.user.skillsets = [];
								self.user.role1 = 0;
								self.user.role2 = 0;
								self.showerrors = false;
								self.showsuccmsg = false;
								self.user.department = '';
								angular.element("input[type='file']").val(null);

							}

							self.cancel = function() {

								self.reset();

								$scope.myFile = null;
								$("#profileimage").fadeIn("fast").attr('src',
										"./images/defaultuser.png");

								self.user.imageExist = false;

								self.showerrors = false;
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.userroleerror = false;
								self.showsuccmsg = false;
								self.showerrmsg = false;
								self.showNotification = false;
								self.fetchAllUsers(self.user);
								self.val = false;
								angular.element("input[type='file']").val(null);

							}

						} ]);
