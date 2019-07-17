'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App
		.controller(
				'MarkAttendanceController',
				[
						'$window',
						'$scope',
						'$timeout',
						'$rootScope',
						'$mdDialog',
						'attendanceService',
						function($window, $scope, $timeout, $rootScope,
								$mdDialog, attendanceService) {

							var self = this;
							self.trainings = null;
							self.attendane = null;
							self.master = 0;
							self.user = {
								userids : [],
								testid : '',
								chkvlue : []
							};
							self.succmsg = '';
							self.errmsg = '';
							self.showsuccmsg = false;
							self.showerrmsg = false;
							self.load = function() {
								self.fetchAllTests();
								console.log('List All Users Successfully',
										self.test);
							};

							self.fetchAllTests = function() {
								attendanceService
										.fetchAllTopicNames()
										.then(
												function(response) {
													self.trainings = response;

												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});
							};

							$scope.getUsers = function() {
								attendanceService
										.fetchUsers(self.user.testid)
										.then(
												function(response) {
													self.user.userids = [];
													self.attendane = response;
													self.chk = [];
													angular
															.forEach(
																	self.attendane,
																	function(
																			value,
																			index) {
																		self.chk
																				.push(value.id);
																	});

													angular
															.forEach(
																	self.attendane,
																	function(
																			value,
																			index) {
																		if (value.attendedStatus == 1) {
																			self.user.userids
																					.push(value.id);

																		}
																	});
													if($rootScope.roleId!=1)
													self.checkDate(self.user.testid);
													

												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});

							};
							self.submit = function($event) {
								self.saveAttendance(self.user);

							}
							self.checkDate = function(trainingid){
								attendanceService
								.checkDate(trainingid)
								.then(
										function(response) {
											self.status = response;
										},
										function(errResponse) {
											console
													.error('Error while fetching Currencies');
										});
							}
							
							self.saveAttendance = function(user) {
								var uploadUrl = "addattendance/";

								if (self.user.testid == '') {

									self.errmsg = "Please select topic!!!";
									self.showerrmsg = true;
									$timeout(function() {
										self.errmsg = '';
										self.showerrmsg = false;
									}, 5000)

								}

								else if (!self.attendane.length) {

									self.errmsg = "No attendees available!!!";
									self.showerrmsg = true;
									$timeout(function() {
										self.errmsg = '';
										self.showerrmsg = false;
									},5000)

								}

								else {
									attendanceService
											.addAttendance(uploadUrl, self.user)
											.then(
													function(response) {
														if(response.errorCode==1)
														$rootScope
														.showNotification(
																"" +
																"Attendance updated successfully",
																true);
														else{
															$rootScope
															.showNotification(
																	"" +
																	"Date for marking attendance has expired. Please contact administrator",
																	false);
														}
													},
													function(errResponse) {
														$rootScope
														.showNotification(
																"" +
																"Updation failed!!!",
																false);
													});
								}
							}

							self.checkAllElements = function() {
								if (self.master == 1) {
									self.checkAll();

								} else {

									self.unCheckAll();
								}

							}

							self.unCheckAll = function() {
								if (self.attendane.length) {

									self.user.userids = [];
								} else {
									alert("Please select topic");
								}
							}

							self.checkAll = function() {

								self.chk = [];
								angular.forEach(self.attendane, function(value,
										index) {
									self.chk.push(value.id);
								});
								if (self.user.userids.length <= self.chk.length) {
									self.user.userids = angular.copy(self.chk);
								} else {
									alert("can't");
								}
							};

							self.reset = function() {
								self.attendane = null;
								self.user = {};
							},

							self.back = function() {
								$window.location.href = '#/home'
							},

							self.load();

						} ]);
