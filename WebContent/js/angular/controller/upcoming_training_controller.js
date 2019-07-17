'use strict';
/**
 * @Author JOSSINA JOSE.
 */

App
		.controller(
				'UpcomingTrainingController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$timeout',
						'$mdDialog',
						'UpcomingTrngSrvs',
						'testreportservice',
						function($window, $scope, $rootScope, $timeout,
								$mdDialog, UpcomingTrngSrvs,testreportservice) {

							var self = this;

							self.upcomingtrainings = null;
							self.testresult=null;
							self.shownotification = false;
							self.testresultlist=true;
							self.resultview=false;
							self.succmsg = '';
							self.errmsg = '';
							self.showsuccmsg = false;
							self.showerrmsg = false;
							self.showUpcomingTab = true;
							self.trainings='';

							self.notification = "";
							self.showNotification = function(message) {
								self.notification = message;
								self.shownotification = true;

								$timeout(function() {
									self.notification = '';
									self.shownotification = false;
								}, 2000);

							};
							$scope.openUpcomingTab = function() {
								self.showUpcomingTab = true;
							};

							$scope.openCompletedTab = function() {
								self.showUpcomingTab = false;
							};

							self.fetchAllFeedbackList = function() {
								UpcomingTrngSrvs
										.fetchAllTrainingList()
										.then(
												function(response) {
                                               
													self.upcomingtrainings = response;
													angular
															.forEach(
																	self.upcomingtrainings,
																	function(
																			value,
																			index) {
																		value.startDateTime = new Date(
																				(value.startDateTime));
																		value.endDateTime = new Date(
																				(value.endDateTime));
																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});
							};
							self.fetchAllCompletedTraining = function(){
								UpcomingTrngSrvs
								.fetchAllCompletedTrainingList()
								.then(
										function(response) {

											self.completedtrainings = response;

											angular
													.forEach(
															self.completedtrainings,
															function(
																	value,
																	index) {
																value.startDateTime = new Date(
																		(value.startDateTime));
																value.endDateTime = new Date(
																		(value.endDateTime));
															});

										},
										function(errResponse) {
											console
													.error('Error while fetching Currencies');
										});
								
							}
							self.viewResult=function(testid){
								self.testresultlist=false;
								self.resultview=true;
								self.fetchAnswerAndQestn(testid);
							}
							self.fetchAnswerAndQestn=function(testid){
								testreportservice
								.fetchAnswerAndQestn(testid)
								.then(
										function(response) {
											
											self.answersheet = response;
										},
										function(errResponse) {
											console
													.error('Error while fetching Currencies');
										});
								
							}
							self.fetchAttendeeTestResult = function() {

								UpcomingTrngSrvs
										.fetchAttendeeTestResult()
										.then(
												function(response) {

													self.testresult = response;
													self.drawChart(self.testresult);
												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});
							};

							self.load = function() {
								self.fetchAllFeedbackList();
								self.fetchAttendeeTestResult();								
								self.fetchAllCompletedTraining();
                                
							}

							self.sendMail = function(topicid) {
								var confirm = $mdDialog.confirm().title(
								'Send Mail!!')
								.textContent('Are you sure?')
								.ariaLabel('Lucky day').ok('Yes')
								.cancel('No');
					    	  $mdDialog
								.show(confirm)
								.then(
										function() {
											UpcomingTrngSrvs
											.sendMail(topicid)
												
											.then(
													function(response) {

														if (response.errorCode == 1) {
															$rootScope
															.showNotification(
																	"" +
																	"Mail has been sent  successfully!",
																	true);
															self.fetchAllFeedbackList();
														} else {
															$rootScope
															.showNotification(
																	"" +
																	"Mail sending failed!!!",
																	false);
														}

													},
													function(errResponse) {
														console
																.error('Error while fetching Currencies');

													});

										}, function() {
										});
							
							}
							
							self.drawChart = function(testresult) {

								$scope.data = [ {
									key : "Total Passed",
									y : testresult.nopass

								}, {
									key : "Total Failed",
									y : testresult.nofail
								}

								];

								$scope.options = {
									chart : {
										type : 'pieChart',
										height : 500,
										width : 500,
										x : function(d) {
											return d.key;
										},
										y : function(d) {
											return d.y;
										},
										showLabels : true,
										color : [ '#3C6082', '#5CCCEA' ],
										duration : 500,
										labelThreshold : 0.01,
										labelSunbeamLayout : true,
										legend : {
											margin : {
												top : 5,
												right : 35,
												bottom : 5,
												left : 0
											}
										}
									}
								};

							};

							self.backToHome = function() {
								$window.location.href = '#/home'
							},
                            
							self.backtoresultlist = function(){
							
								self.testresultlist=true;
								self.resultview=false;
							}
							
							self.load();

						} ]);
