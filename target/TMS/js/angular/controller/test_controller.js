'use strict';

App.controller('ShowMyTestController', [ '$window', '$scope', '$rootScope',
		'MyTestService', function($window, $scope, $rootScope, testService) {
			var self = this;
			self.tests = [];

			self.fetchAllTests = function() {
				testService.fetchAllTests().then(function(d) {
					self.tests = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			self.attendtest = function(test) {
				$rootScope.test = test;
				$window.location.href = '#/attendtest';
			};

			self.fetchAllTests();

		} ]);

App.controller('AttendTestController', [
		'$window',
		'$scope',
		'$rootScope',
		'$timeout',
		'MyTestService',
		function($window, $scope, $rootScope, $timeout, testService) {
			var self = this;

			self.answeringbean = null;

			self.counter = 0;
			self.remainingTime = "00:00";
			var countDounTimer;
			self.showingResult = false;
			self.showTimeOut = false;

			self.countdown = function() {
				countDounTimer = $timeout(function() {
					// console.log(self.counter);
					if (self.counter == 0) {
						self.remainingTime = "00:00";
						self.showTimeOut = true;
						self.closeTest();

						$timeout(function() {
							self.showTimeOut = false;
						}, 2000);

						self.stop();
					} else {
						self.remainingTime = secondsToTime(self.counter);

						self.counter--;
						self.countdown();
					}
				}, 1000);
			};

			self.stop = function() {
				$timeout.cancel(countDounTimer);
			}

			$rootScope.$on('removeTestTimer', function(event) {
				self.stop();
			});

			self.loadPage = function() {
				if ($rootScope.test) {
					self.test = $rootScope.test;
					$rootScope.test = null;

					testService.isTestAttended(self.test.id).then(function(d) {
						self.isattented = d;

					}, function(errResponse) {
						console.error('Error while fetching Currencies');
					});

				} else {
					$window.location.href = '#/onlinetest';
				}
			};

			self.startTest = function() {

				if (self.test) {

					testService.startTest(self.test.id).then(
							function(data) {
								self.isstarted = true;
								self.answeringbean = data;
								blockBlur($rootScope);
								$rootScope.showmenu = false;

								self.counter = data.remainingSeconds;
								self.countdown();
							},
							function(errResponse) {
								// self.isstarted =
								// true;
								console.error('Error while fetching Currencies'
										+ errResponse);
							});

				} else {
					$window.location.href = '#/onlinetest';
				}
			};

			self.nextQuestion = function(questionId) {
				if (self.test) {
					var answer = 0;

					if (self.selectedAnswer)
						answer = self.selectedAnswer;
					self.stop();

					testService.nextQuestion(self.test.id, questionId, answer,
							self.counter).then(
							function(data) {
								self.answeringbean = data;
								self.selectedAnswer = 0;
								self.counter = data.remainingSeconds;
								self.countdown();
							},
							function(errResponse) {
								console.error('Error while fetching Currencies'
										+ errResponse);
							});

				} else {
					$window.location.href = '#/onlinetest';
				}
			};

			self.finishTest = function(questionId) {
				if (self.test) {
					var answer = 0;

					if (self.selectedAnswer)
						answer = self.selectedAnswer;

					testService.finishTest(self.test.id, questionId, answer,
							self.counter).then(
							function(data) {
								self.stop();
								self.showingResult = false;
								self.answeringbean = data;
								unBlockBlur();
								$rootScope.showmenu = true;
								self.drawChart(self.answeringbean);
							},
							function(errResponse) {
								console.error('Error while fetching Currencies'
										+ errResponse);
							});

				} else {
					$window.location.href = '#/onlinetest';
				}
			};

			self.showResult = function() {

				if (self.test) {

					testService.showResult(self.test.id).then(
							function(data) {
								self.showingResult = true;
								self.answeringbean = data;
								self.drawChart(self.answeringbean);
							},
							function(errResponse) {
								// self.isstarted =
								// true;
								console.error('Error while fetching Currencies'
										+ errResponse);
							});

				} else {
					$window.location.href = '#/onlinetest';
				}
			};
			self.gotoTestsPage = function() {
				$window.location.href = '#/mytests';
			}

			self.closeTest = function() {
				if (self.test) {
					var answer = 0;

					if (self.selectedAnswer)
						answer = self.selectedAnswer;

					testService.closeTest(self.test.id,
							self.answeringbean.question.id, answer,
							self.counter).then(
							function(data) {
								self.answeringbean = data;
								unBlockBlur();
								self.drawChart(self.answeringbean);
							},
							function(errResponse) {
								console.error('Error while fetching Currencies'
										+ errResponse);
							});

				} else {
					$window.location.href = '#/onlinetest';
				}
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

			self.loadPage();
			openOption('mytests');

		} ]);
