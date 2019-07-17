'use strict';

App
		.controller(
				'PerformanceAnalysisController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'PerformanceAnalysisService',
						function($window, $scope, $rootScope, $mdDialog,
								$timeout, PerformanceAnalysisService) {
							var self = this;

							self.showAttendeeList = false;
							self.attendeeList = [];
							self.attendeeId = "";
							self.attendeeName = "";

							self.trainingPrograms = [];
							self.feedbacklist = [];
							self.attendeeTests = [];
							self.userDetails = {};
							self.programDetails = {};

							self.answeringbean = {};

							self.view = 'analysis';

							self.topicName = '';

							self.selectedAttendee = null;

							self.selectUser = function(selected) {
								if (selected != null) {
									
									self.attendeeId = selected.userId;
									self.attendeeName = selected.firstName+' '+selected.lastName;
									self.showAttendeeList = false;
									
									self.selectedAttendee = selected;
									self.loadPerformanceAnalysis();
								} else {
									self.reset();
								}
							};

							self.reset = function() {
								
								self.attendeeId = "";
								self.attendeeName = "";
								self.showAttendeeList = false;

								self.trainingPrograms = [];
								self.attendeeTests = [];
								self.userDetails = {};
								self.programDetails = {};
								self.selectedAttendee = null;
								$scope
										.$broadcast('angucomplete-alt:clearInput');
							};

							self.showAttendees = function() {
								self.loadAttendees();
								self.showAttendeeList = true;
							};
							
							self.closeAttendeeList = function() {
								self.showAttendeeList = false;
							};

							self.loadAttendees = function() {
								PerformanceAnalysisService
										.loadAllAttendees()
										.then(
												function(response) {
													self.attendeeList = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching');
												});

							};

							self.loadPerformanceAnalysis = function(attendeeId) {
								if (self.selectedAttendee != null) {
									PerformanceAnalysisService
											.loadPerformanceAnalysis(
													self.selectedAttendee.userId)
											.then(
													function(response) {
														self.trainingPrograms = response.attendeePrograms;
														self.attendeeTests = response.attendeeTests;
														self.userDetails = response.userDetails;
														self.programDetails = response.programDetails;
													},
													function(errResponse) {
														console
																.error('Error while fetching');
													});
								}

							};

							self.showResult = function(testId) {

								PerformanceAnalysisService
										.showResult(testId,
												self.selectedAttendee.userId)
										.then(
												function(data) {
													self.view = 'testresult';
													self.answeringbean = data;
													self
															.drawChart(self.answeringbean);

												},
												function(errResponse) {
													// self.isstarted =
													// true;
													console
															.error('Error while fetching Currencies'
																	+ errResponse);
												});

							};

							self.viewFeedback = function(topicId, topicName) {
								self.feedbacklist = [];
								self.topicName = topicName;

								PerformanceAnalysisService
										.viewFeedback(topicId,
												self.selectedAttendee.userId)
										.then(
												function(data) {
													self.view = 'feedback';
													self.feedbacklist = data;

												},
												function(errResponse) {
													// self.isstarted =
													// true;
													console
															.error('Error while fetching Currencies'
																	+ errResponse);
												});

							};

							$scope.options = {
								chart : {
									type : 'pieChart',
									height : 300,
									width : 400,
									x : function(d) {
										return d.key;
									},
									y : function(d) {
										return d.y;
									},
									showLabels : false,
									donut : true,
									donutRatio : 0.3,
									color : [ '#109618', '#DC3912', '#FF9900' ],
									duration : 500,
									labelThreshold : 0.01,
									labelSunbeamLayout : true,
									margin : {
										top : 0,
										right : 0,
										bottom : 0,
										left : 0
									},
									padding : {
										top : 0,
										right : 0,
										bottom : 0,
										left : 0
									},
									showLegend : true,
								}
							};
							$scope.data = [];
							self.drawChart = function(answeringbean) {

								$scope.data = [
										{
											key : "Correct Answers",
											y : answeringbean.correctAnswers
										},
										{
											key : "Incorrect Answers",
											y : answeringbean.totalAnswered
													- answeringbean.correctAnswers
										},
										{
											key : "Not Attended",
											y : answeringbean.totalQuestions
													- answeringbean.totalAnswered
										} ];

							};

							self.loadAttendees();

						} ]);
