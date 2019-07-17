'use strict';

App
		.controller(
				'QuestionController',
				[
						'$window',
						'$location',
						'$anchorScroll',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'QuestionService',
						function($window, $location, $anchorScroll, $scope, $rootScope, $mdDialog,
								$timeout, QuestionService) {
							var self = this;
							self.questions = null;

							self.totalQuestions = 0;
							self.criteriaTestId = '0';
							self.question = {
								id : null,
								question : '',
								choice1 : '',
								choice2 : '',
								choice3 : '',
								choice4 : '',
								answer : '',
								testId : '',
								noOfChoices : '4'
							};
							self.questionsCompleted = false;
							self.saveAndCreate = false;
							self.isTestSubmitted = false;

							self.optionCRequired = true;
							self.optionDRequired = true;

							self.showaddwindow = false;
							self.showlistwindow = true;

							self.showerrors = false;
							self.questionsover = false;

							self.goBack = function() {
								self.question = {
									id : null,
									question : '',
									choice1 : '',
									choice2 : '',
									choice3 : '',
									choice4 : '',
									answer : '',
									testId : '',
									noOfChoices : '4'
								};
								self.questionsCompleted = false;

								self.showaddwindow = false;
								self.showlistwindow = true;

								self.showerrors = false;
								self.questionsover = false;

								self.fetchAllQuestions();
							};

							self.questions = [];

							self.load = function() {
								
								self.fetchAllTests();

								if ($rootScope.addQuestionTestId != null) {
									self.criteriaTestId=$rootScope.addQuestionTestId+'';
									$rootScope.addQuestionTestId = null;
								}
								self.fetchAllQuestions();
								
							};

							self.fetchAllQuestions = function() {
								if (self.criteriaTestId != null
										&& self.criteriaTestId != 0) {
									QuestionService
											.fetchAllQuestions(
													self.criteriaTestId)
											.then(
													function(response) {
														self.isTestSubmitted = false;
														self.questions = response.questions;
														self.totalQuestions = response.totalQuestions;
														if (response.submitStatus == "submitable") {
															self.questionsCompleted = true;
														} else {
															self.questionsCompleted = false;
															if (response.submitStatus == "submitted"
																	|| response.submitStatus == "published")
																self.isTestSubmitted = true;
														}

													},
													function(errResponse) {
														console
																.error('Error while fetching');
													});
								} else {
									self.questionsCompleted = false;
									self.questions = null;
								}

							};

							self.fetchAllTests = function() {
								QuestionService
										.fetchAllTests()
										.then(
												function(response) {
													self.tests = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching');
												});
							};

							self.checkQuestionsOver = function() {
								self.questionsover = false;
								if (self.question.testId != '') {
									QuestionService
											.checkQuestionsOver(
													self.question.testId)
											.then(
													function(response) {
														self.questionsover = response;

													},
													function(errResponse) {
														console
																.error('Error while fetching');
													});
								}
							};
							
							self.hideandshow = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
							};

							self.showandhide = function() {
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.fetchAllQuestions();
							};

							self.deleteQuestion = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Delete!').textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													QuestionService
															.deleteQuestion(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllQuestions();
																		$rootScope
																				.showNotification(
																						"Deleted successfully",
																						true);
																	},
																	function(
																			errResponse) {
																		$rootScope
																				.showNotification(
																						"Deletion failed",
																						false);
																		console
																				.error('Error while deleting'
																						+ errResponse);

																	});

												}, function() {
												});

							};
							self.submitTest = function() {

								if (self.criteriaTestId != null
										&& self.criteriaTestId != 0) {
									var testId = self.criteriaTestId;

									var confirm = $mdDialog.confirm().title(
											'Submit!').textContent(
											'Are you sure?').ariaLabel('').ok(
											'Yes').cancel('No');
									$mdDialog
											.show(confirm)
											.then(
													function() {
														QuestionService
																.submitTest(
																		testId)
																.then(
																		function(
																				response) {
																			self
																					.fetchAllQuestions();
																			$rootScope
																					.showNotification(
																							"Submitted successfully",
																							true);
																		},
																		function(
																				errResponse) {
																			$rootScope
																					.showNotification(
																							"Submition failed",
																							false);
																			console
																					.error('Error while submitting'
																							+ errResponse);

																		});

													}, function() {
													});

								}

							};

							self.addNewQuestion = function() {
								self.showerrors = false;
								self.reset();
								self.hideandshow();
								if (self.criteriaTestId != null
										&& self.criteriaTestId != 0) {
									self.question.testId = self.criteriaTestId;
									self.checkQuestionsOver();
								}
							};

							self.backToTest = function() {
								$window.location = "./#/manageonlinetests";
							};

							self.edit = function(id) {

								self.showerrors = false;

								console.log('id to be edited:', id);
								QuestionService
										.fetchQuestionById(id)
										.then(
												function(response) {
													self.question = response;
													self.question.testId = response.testId
															+ '';
													self.question.noOfChoices = response.noOfChoices
															+ '';

													if (self.question.noOfChoices == '2') {
														self.question.choice3 = '';
														self.question.choice4 = '';
														self.optionCRequired = false;
														self.optionDRequired = false;
													} else if (self.question.noOfChoices == '3') {
														self.question.choice4 = '';
														self.optionCRequired = true;
														self.optionDRequired = false;
													} else if (self.question.noOfChoices == '4') {
														self.optionCRequired = true;
														self.optionDRequired = true;
													}

												},
												function(errResponse) {
													console
															.error('Error while fetching');
												});
								self.hideandshow();

							};

							self.submit = function($event) {
								if ($scope.addQuestionForm.$valid
										&& !self.questionsover) {
									self.saveOrUpdateQuestion(self.question);

								} else {
									self.showerrors = true;
								}
								$event.preventDefault();
							};

							self.saveOrUpdateQuestion = function(question) {
								QuestionService
										.saveOrUpdateQuestion(question)
										.then(
												function(response) {
													if (response.errorCode == 1) {

														if (!self.question.id) {
															$rootScope
																	.showNotification(
																			"Saved successfully",
																			true);
														} else {
															$rootScope
																	.showNotification(
																			"Updated successfully",
																			true);
														}

														self.showandhide();
														self.questionsover = false;
														return true;
													} else {
														self.questionsover = true;
														return false;
													}

												},
												function(errResponse) {
													if (!self.question.id) {
														$rootScope
																.showNotification(
																		"Saving failed",
																		false);
													} else {
														$rootScope
																.showNotification(
																		"Updation failed",
																		false);
													}

													console
															.error('Error while updating');

												});
							};

							self.saveAndCreateNew = function($event) {
								if ($scope.addQuestionForm.$valid
										&& !self.questionsover) {

									var question = self.question;
									QuestionService
											.saveOrUpdateQuestion(question)
											.then(
													function(response) {
														if (response.errorCode == 1) {

															if (!self.question.id) {
																$rootScope
																		.showNotification(
																				"Saved successfully",
																				true);
															} else {
																$rootScope
																		.showNotification(
																				"Updated successfully",
																				true);
															}
															var tid = self.question.testId;

															self.question = {
																id : null,
																question : '',
																choice1 : '',
																choice2 : '',
																choice3 : '',
																choice4 : '',
																answer : '',
																testId : tid,
																noOfChoices : '4'
															};

															self.showerrors = false;

															self.optionCRequired = true;
															self.optionDRequired = true;

															self.questionsover = false;
															
															$location.hash('addQuestionDiv');
															$anchorScroll();

															$timeout(
																	function() {
																		self
																				.checkQuestionsOver();
																	}, 1000);

															return true;
														} else {
															self.questionsover = true;
															return false;
														}

													},
													function(errResponse) {
														if (!self.question.id) {
															$rootScope
																	.showNotification(
																			"Saving failed",
																			false);
														} else {
															$rootScope
																	.showNotification(
																			"Updation failed",
																			false);
														}

														console
																.error('Error while updating');

													});

								} else {
									self.showerrors = true;
								}
								// self.reset();
								$event.preventDefault();
							};

							self.noOfQnsChangeEvent = function() {
								if (self.question.noOfChoices == '2') {
									self.question.choice3 = '';
									self.question.choice4 = '';
									self.optionCRequired = false;
									self.optionDRequired = false;
								} else if (self.question.noOfChoices == '3') {
									self.question.choice4 = '';
									self.optionCRequired = true;
									self.optionDRequired = false;
								} else if (self.question.noOfChoices == '4') {
									self.optionCRequired = true;
									self.optionDRequired = true;
								}
								self.question.answer = '';
							};

							self.reset = function() {
								self.question.question = '';
								self.question.choice1 = '';
								self.question.choice2 = '';
								self.question.choice3 = '';
								self.question.choice4 = '';
								self.question.answer = '';
								self.question.testId = '';
								self.question.noOfChoices = '4';
								self.question.id = '';
								self.showerrors = false;
							};
							
							self.load();

						} ]);
