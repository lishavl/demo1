App
		.controller(
				'HomeController',
				[
						'$window',
						'$location',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'HomeService',
						'Notification',
						'LoginService',
						'$cookieStore',
						'$filter',
						function($window, $location, $scope, $rootScope,
								$mdDialog, $timeout, HomeService, Notification,
								LoginService, $cookieStore, $filter) {

							$rootScope.fullyLoaded = false;

							$rootScope.programStatus = null;

							var self = this;

							$scope.stars = [ 0, 1, 2, 3, 4 ];

							$rootScope.timezone = 'GMT+05:30';
							$rootScope.userId = null;
							$rootScope.roleId = null;
							$rootScope.userDetails = null;

							$scope.showThrobber = false;

							$rootScope.emited = false;

							self.upcomingTrainingPrograms = [];

							self.completedTrainingPrograms = [];

							self.menus = [];

							self.allnotifications = [];

							self.statistics = null;
							self.forumThreads = null;
							self.testsForCriteria = null;

							self.showUpcomingTab = true;
							self.selectedTest = "";

							$rootScope.toUTCDate = function(date) {
								return new Date(date.getTime()
										- date.getTimezoneOffset() * 60000);
							};

							$scope.hoverIn = function() {
								this.hoverEdit = true;
							};

							$scope.hoverOut = function() {
								this.hoverEdit = false;
							};

							self.attendTest = function(tst) {
								var test = {
									id : tst.testId,
									title : tst.title,
									description : tst.description,
								};

								$rootScope.test = test;
								$window.location.href = '#/attendtest';
							};

							self.openForumThread = function(threadId) {
								$rootScope.forumThreadId = threadId;
								$window.location.href = '#/forum';
							};

							self.openPrograms = function(status) {
								$rootScope.programStatus = status;
								$window.location.href = '#/mytraininglistfortrainer';
							};

							$window.onfocus = function() {
								if ($rootScope.userId != null
										&& $rootScope.userId != 0) {
									HomeService
											.isSessionExist($rootScope.roleId)
											.then(
													function(response) {
														if (!response) {
															$window.location = "./";
														}
													},
													function(errResponse) {
														console
																.error('Error while fetching Users');
													});
								}
							};

							self.loadDetails = function() {

								$scope.showThrobber = true;
								HomeService
										.loadDetails()
										.then(
												function(response) {

													self.showTestChart = false;

													$rootScope.userId = response.userId;
													$rootScope.roleId = response.roleId;
													$rootScope.userDetails = response.userDetails;

													$rootScope.notificationListToShow = response.allnotifications;

													self.upcomingTrainingPrograms = response.upcomingPgms;
													self.completedTrainingPrograms = response.completedPgms;

													self.statistics = response.statistics;
													self.forumThreads = response.forumThreads;

													self.pendingTests = response.pendingTests;

													self.testsForCriteria = response.testsForCriteria;

													self
															.loadMenu(response.menus);
													self.loadAttendedChart();

													self.topTrainers = response.topTrainers;

													$rootScope.fullyLoaded = true;
													if (self.testsForCriteria != null
															&& self.testsForCriteria.length > 0) {

														$timeout(
																function() {

																	self.showTestChart = true;

																	self.selectedTest = response.testsForCriteria[0].testId
																			+ '';

																	self
																			.drowTestChart();
																}, 1000);

													}

													$timeout(
															function() {
																$scope.showThrobber = false;
															}, 1000);

												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});

								if ($location.path() == ''
										|| $location.path() == '/') {
									openOption('home');
								}

							};

							$rootScope.showNotification = function(message,
									isSuccess) {
								if (isSuccess) {
									Notification.success({
										message : message,
										positionY : 'top',
										positionX : 'center'
									});
								} else {
									Notification.error({
										message : message,
										positionY : 'top',
										positionX : 'center'
									});
								}
							};

							$rootScope.showInvalidFocusWarning = function() {
								$mdDialog
										.show({
											clickOutsideToClose : true,
											scope : $rootScope,
											preserveScope : true,
											contentElement : '#invalidFocusOnTestDialoge',
											controller : function HomeController(
													$scope, $mdDialog) {
												$scope.closeDialog = function() {
													$mdDialog.hide();
												}
											}
										});
							};

							$scope.openUpcomingTab = function() {
								self.showUpcomingTab = true;
							};

							$scope.openCompletedTab = function() {
								self.showUpcomingTab = false;
							};

							self.showAttendanceChart = false;

							self.loadAttendedChart = function() {

								var rows = [];
								var data;

								if (self.completedTrainingPrograms.length > 0)
									self.showAttendanceChart = true;
								else
									self.showAttendanceChart = false;

								for (var i = self.completedTrainingPrograms.length; i > 0; i--) {
									data = self.completedTrainingPrograms[i - 1];

									var topic = "";

									if (data.topic.length > 10) {
										topic = data.topic.substring(0, 8)
												+ '...';
									} else {
										topic = data.topic;
									}

									rows.push({
										c : [
												{
													v : topic,
													f : data.topic + ' on '
															+ data.month + ' '
															+ data.day
												},
												{
													v : data.attendedCount,
													f : data.attendedCount + ''
												},
												{
													v : data.notAttendedCount,
													f : data.notAttendedCount
															+ ''
												} ]
									});

								}

								var chart1 = {};
								chart1.type = "google.charts.Bar";
								chart1.displayed = false;
								chart1.data = {
									"cols" : [ {
										id : "training",
										label : "Programs",
										type : "string"
									}, {
										id : "attended-id",
										label : "Attendees",
										type : "number"
									}, {
										id : "not-attended-id",
										label : "Absentees",
										type : "number"
									} ],
									"rows" : rows
								};

								chart1.options = {
									"title" : "Attended Status",
									"isStacked" : "true",
									"colors" : [ '#59c2e6', '#E26868' ],
									"defaultColors" : [ '#59c2e6', '#E26868' ],
									"fill" : 20,
									"displayExactValues" : true,
									"vAxis" : {
										"title" : "Sales unit",
										"gridlines" : {
											"count" : 10
										}
									},
									"hAxis" : {
										"title" : "Date"
									}
								};
								$scope.myChart = chart1;

							};

							self.testChartOptions = {
								chart : {
									type : 'pieChart',
									height : 250,
									x : function(d) {
										return d.key;
									},
									y : function(d) {
										return d.y;
									},
									showLabels : true,
									duration : 500,
									labelThreshold : 0.01,
									labelSunbeamLayout : false,
									color : [ '#109618', '#dc3912', '#ff9900',
											'#109618', '#990099' ],
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
									showLegend : false,
								}
							};

							self.showTestChart = false;
							self.testChartData = [];
							self.drowTestChart = function() {

								if (self.selectedTest != null
										&& self.selectedTest != "") {
									HomeService
											.getTestResult(self.selectedTest)
											.then(
													function(response) {

														self.showTestChart = true;
														$timeout(
																function() {
																	self
																			.drowPieChart(response);
																}, 20);
													},
													function(errResponse) {
														console
																.error('Error while fetching Users');
													});
								} else {
									self.data = [];
									self.showTestChart = false;
								}
							}

							self.drowPieChart = function(result) {
								self.testChartData = [ {
									key : "Passed",
									y : result.passnumber,
								}, {
									key : "Failed",
									y : result.failednumber,
								}, {
									key : "Not attended",
									y : result.notAttendedNumber,
								}, ];
							}

							self.menuGroup1 = [];
							self.menuGroup2 = [];
							self.menuGroup3 = [];
							self.menuGroup4 = [];
							self.menuGroup5 = [];

							self.loadMenu = function(menus) {
								self.menuGroup1 = [];
								self.menuGroup2 = [];
								self.menuGroup3 = [];
								self.menuGroup4 = [];
								self.menuGroup5 = [];

								if (menus != null) {
									for (var i = 0; i < menus.length; i++) {
										if (menus[i].menuGroup == 1) {
											self.menuGroup1.push(menus[i]);
										} else if (menus[i].menuGroup == 2) {
											self.menuGroup2.push(menus[i]);
										} else if (menus[i].menuGroup == 3) {
											self.menuGroup3.push(menus[i]);
										} else if (menus[i].menuGroup == 4) {
											self.menuGroup4.push(menus[i]);
										} else if (menus[i].menuGroup == 5) {
											self.menuGroup5.push(menus[i]);
										}
									}
								}
							}

							$rootScope.openLoginWindow = function() {

								$rootScope.$emit('clearLoginForm');

								$mdDialog.show({
									clickOutsideToClose : false,
									scope : $scope,
									preserveScope : true,
									contentElement : '#myStaticLogin',
									controller : function LoginController(
											$scope, $mdDialog) {
										$scope.closeDialog = function() {
											$mdDialog.hide();
										}
									}
								});
							};

							$rootScope.$on('openLoginWindow', function(event) {
								if (!$rootScope.emited)
									$scope.openLoginWindow();
								$rootScope.emited = true;
								$timeout(function() {
									$rootScope.emited = false
								}, 2000);

							});

							$rootScope.openRegisterWindow = function() {

								$rootScope.$emit('clearRegisterForm');

								$mdDialog.show({
									clickOutsideToClose : false,
									scope : $scope,
									preserveScope : true,
									contentElement : '#myStaticRegister',
									controller : function RegisterController(
											$scope, $mdDialog) {
										$scope.closeDialog = function() {
											$mdDialog.hide();
										}
									}
								});
							};
							
							$rootScope.dateToSqlDateString = function(date) {
								return $filter('date')(date, "yyyy-MM-dd");
								;
							};
							
							$rootScope.closeInvalidAccessWindow = function() {
								$mdDialog.cancel();
								$window.location = "./";
							};

							self.loadDetails();

							self.changeStatus = function(notificationid) {
								HomeService
										.updateStatus(notificationid)
										.then(
												function(response) {
													$rootScope.notificationListToShow = response;

												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});

							}

						} ]);
