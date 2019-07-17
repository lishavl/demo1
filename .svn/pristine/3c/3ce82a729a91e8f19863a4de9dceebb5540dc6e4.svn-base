'use strict';

App
		.controller(
				'UpdateProfileController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'UpdateProfileService',
						'departmentService',
						function($window, $scope, $rootScope, $mdDialog,
								$timeout, UpdateProfileService,
								departmentService) {

							var self = this;
							self.showerrors = false;
							self.showerr = false;

							self.showNotification = false;
							self.showsuccmsg = false;
							self.showerrmsg = false;

							$scope.tags = [];
							self.skillSet = [];

							self.profile = [];
							self.imagesrc = '';

							self.succmsg = '';
							self.errmsg = '';

							self.userid = 0;
							
							self.profile.imageExist = false;

							self.load = function() {
								self.fetchDepartmentDetails();
								self.profile.gender = "M";
								self.fetchUser();
								console.log('Successfully Fetch the User');
							};

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

							self.fetchUser = function() {
								self.x = new Date().getMilliseconds();
								UpdateProfileService
										.fetchUser()
										.then(
												function(response) {
													self.profile = response;
													if (response.dob === ""
															|| response.dob === null
															|| typeof response.dob === "undefined") {

														self.profile.dob = new Date();
													} else {
														self.profile.dob = new Date(
																self.profile.dob);
													}
													self.userid = response.user_id;
													if (response.departmentname != null) {
														self.profile.department = response.department
																+ '';
													}
													self.profile.firstName = response.firstName
															+ " "
															+ response.lastName

													angular
															.forEach(
																	self.profile.skills,
																	function(
																			value,
																			index) {
																		$scope.tags
																				.push({
																					text : value
																				});
																	})
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});
							}
							
							self.deleteUserImage = function() {
								UpdateProfileService
										.deleteUserImage()
										.then(
												function(response) {

													if (response) {
														$("#profileimage")
																.fadeIn("fast")
																.attr('src',
																		"./images/defaultuser.png");

														self.profile.imageExist = false;
													}

												}, function(errResponse) {
													console.error('Error');
												});

							}

							self.submit = function($event) {
								if ($scope.editProfileForm.$valid) {

									var values = self.profile.firstName
											.split(" ");

									self.profile.firstName = values[0];
									self.profile.lastName = values[1];

									self.profile.dob = $rootScope
											.toUTCDate(self.profile.dob);

									self.updateUser(self.profile,
											self.profile.user_id);
									console.log('User updated with id ',
											self.profile.user_id);
									$event.preventDefault();
								} else {
									self.showerrors = true;
									self.showerr = true;
								}

							};

							self.updateUser = function(profile, user_id) {
								self.profile.user_id = user_id;
								self.showerrors = false;
								self.profile.skillsets = [];
								angular.forEach($scope.tags,
										function(value, index) {
											if (value != null
													&& value.text != null)
												self.profile.skillsets
														.push(value.text);
										});

								var file = $scope.myFile;

								console.log('file is ');
								console.dir(file);

								var uploadUrl = "updateuserprofile/";

								UpdateProfileService
										.updateUser(self.profile, file,
												uploadUrl)
										.then(
												function(response) {

													$rootScope
															.showNotification(
																	"Profile Updated Successfully",
																	true);

													$timeout(function() {
														$window.location
																.reload();
													}, 1000);
												},
												function(errResponse) {

													$rootScope
															.showNotification(
																	"Profile Updated Failed",
																	false);
													console
															.error('Error while updating Profile.');

												});
							};

							self.fetchDepartmentDetails = function() {
								departmentService.fetchDepartmentDetails()
										.then(function(response) {
											self.departmentlist = response;
										}, function(errResponse) {
											console.error('Error');
										});

							}

							$scope.loadTags = function(query) {
								UpdateProfileService
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
							self.load();

							self.reset = function() {
								self.showerrors = false;
								self.showerr = false;
								self.profile.imageExist = false;
								$scope.tags = [];

								self.profile = {
									user_id : self.userid,
									firstName : '',
									lastName : '',
									email : '',
									password : '',
									confirmPassword : '',
									gender : "M",
									phoneNumber : '',
									address : '',
									blockingReason : '',
									dob : '',
									department : '',
									skillsets : [],
								};

							}
						} ]);
