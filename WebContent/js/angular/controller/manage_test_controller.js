'use strict';
App
		.controller(
				'ManageTestController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'ManageTestService',
						'Notification',
						function($window, $scope, $rootScope, $mdDialog,
								$timeout, ManageTestService) {
							var self = this;
							self.trainings = null;
							self.test = {
								testId : null,
								title : '',
								description : '',
								trainingId : '',
								numberOfQuestions : '',
								timeMinutes : '',
								passMark : '',
								correctAnswerMark : '1',
								wrongAnswerMark : '0',
								notAttendedMark : '0',
							};

							self.createQuestion = false;
							self.showaddwindow = false;
							self.showlistwindow = true;

							self.showerrors = false;

							self.tests = [];

							self.load = function() {
								self.fetchAllTests();
								console.log('List All Users Successfully',
										self.test);
							};

							self.goBack = function() {
								self.test = {
									testId : null,
									title : '',
									description : '',
									trainingId : '',
									numberOfQuestions : '',
									timeMinutes : '',
									passMark : '',
									correctAnswerMark : '1',
									wrongAnswerMark : '0',
									notAttendedMark : '0',
								};

								self.showaddwindow = false;
								self.showlistwindow = true;

								self.showerrors = false;

								self.fetchAllTests();
							};

							self.fetchAllTests = function() {
								ManageTestService
										.fetchAllTests()
										.then(
												function(response) {
													self.tests = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};

							self.fetchAllTrainings = function() {
								ManageTestService
										.fetchAllTrainings()
										.then(
												function(response) {
													self.trainings = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							self.load();

							self.hideandshow = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
							};

							self.showandhide = function() {
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.fetchAllTests();
							};

							self.showQuestions = function(testId) {
								$rootScope.addQuestionTestId = testId;
								$window.location = "./#/createquestions";
							};

							self.deleteTest = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													ManageTestService
															.deleteTest(id)
															.then(
																	function(
																			response) {
																		if (response.errorCode == 1) {
																			self
																					.fetchAllTests();
																			$rootScope
																					.showNotification(
																							"Deleted successfully",
																							true);
																		} else {
																			$rootScope
																					.showNotification(
																							"Deletion failed, please delete questions under this test first",
																							false);
																		}

																	},
																	function(
																			errResponse) {
																		$rootScope
																				.showNotification(
																						"Deletion failed",
																						false);
																		console
																				.error('Error while deleting Test.'
																						+ errResponse);

																	});

												}, function() {
												});

							};

							self.publishTest = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Publish Test').textContent(
										'Are you sure?').ariaLabel('')
										.ok('Yes').cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													ManageTestService
															.publishTest(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllTests();
																		$rootScope
																				.showNotification(
																						"Published successfully",
																						true);
																	},
																	function(
																			errResponse) {
																		$rootScope
																				.showNotification(
																						"Publishing failed",
																						false);
																		console
																				.error('Error while publishing. Msg: '
																						+ errResponse);

																	});

												}, function() {
												});

							};

							self.closeTest = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Close Test').textContent(
										'Are you sure?').ariaLabel('')
										.ok('Yes').cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													ManageTestService
															.closeTest(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllTests();
																		$rootScope
																				.showNotification(
																						"Closed successfully",
																						true)
																	},
																	function(
																			errResponse) {
																		$rootScope
																				.showNotification(
																						"Closing failed",
																						false)
																		console
																				.error('Error while closing. Msg : '
																						+ errResponse);

																	});

												}, function() {
												});

							};

							self.addNewTest = function() {
								self.showerrors = false;
								self.reset();
								self.fetchAllTrainings();
								self.hideandshow();

							};

							self.edit = function(id) {

								self.showerrors = false;

								console.log('id to be edited:', id);
								ManageTestService
										.fetchTestById(id)
										.then(
												function(response) {
													self.test = response;
													self.test.trainingId = response.trainingId
															+ "";
												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});
								self.fetchAllTrainings();
								self.hideandshow();

							};

							self.submit = function($event) {
								
								self.createQuestion = false;

								if ($scope.addTestForm.$valid) {

									if (!self.test.testId) {
										console.log('Saving New User',
												self.test);
										self.saveOrUpdateTest(self.test);
										$rootScope.showNotification(
												"Saved successfully", true);

									} else {
										self.saveOrUpdateTest(self.test);
										$rootScope.showNotification(
												"Updated successfully", true);
										console.log('User updated with id ',
												self.test.testId);
									}

								} else {
									self.showerrors = true;
								}
								// self.reset();
								$event.preventDefault();
							};

							self.saveAndCreateQuestion = function() {

								if ($scope.addTestForm.$valid) {
									self.createQuestion = true;
									if (!self.test.testId) {
										console.log('Saving New User',
												self.test);
										self.saveOrUpdateTest(self.test);
										$rootScope.showNotification(
												"Saved successfully", true);

									} else {
										self.saveOrUpdateTest(self.test);
										$rootScope.showNotification(
												"Updated successfully", true);
										console.log('User updated with id ',
												self.test.testId);
									}
								} else {
									self.showerrors = true;
								}
							};

							self.saveOrUpdateTest = function(test) {
								ManageTestService
										.saveOrUpdateTest(test)
										.then(
												function(response) {
													if (self.createQuestion) {
														self
																.showQuestions(response.data);
													}
													self.showandhide();
												},
												function(errResponse) {
													console
															.error('Error while updating User.');

												});
							};

							self.reset = function() {
								self.test.testId = null;
								self.test.title = '';
								self.test.description = '';
								self.test.trainingId = '';
								self.test.numberOfQuestions = '';
								self.test.timeMinutes = '';
								self.test.passMark = '';
								self.test.correctAnswerMark = '1';
								self.test.wrongAnswerMark = '0';
								self.test.notAttendedMark = '0';
								self.showerrors = false;
							};

						} ]);
