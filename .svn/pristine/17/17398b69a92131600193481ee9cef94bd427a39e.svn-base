App
		.controller(
				'MyTrainingListController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'$filter',
						'MyTrainingListService',
						'AdminFeedbackViewService',
						'attendanceService',
						'UpcomingTrngSrvs',
						'TrainingMaterialService',
						'feedbackService',
						'moment',
						'calendarConfig',
						'departmentService',
						function($window, $scope, $rootScope, $mdDialog,
								$timeout, $filter, MyTrainingListService,
								AdminFeedbackViewService, attendanceService,
								UpcomingTrngSrvs, TrainingMaterialService,
								feedbackService, moment, calendarConfig,
								departmentService) {

							var self = this;
							self.fromDate = new Date();
							self.toDate = new Date();

							self.tschedule = {
								id : 0,
								topic : '',
								maincategory : '',
								categoryName : '',
								subCategory : '',
								subCategoryName : '',
								description : '',
								startDateTime : '',
								endDateTime : '',
								trainerlist : [],
								attendeelist : [],
								trainers : ''
							};

							self.user = {
								userids : [],
								testid : null,
								chkvlue : []
							};
							self.profile = {
								department : 0,
							}
							self.listofattendee = null;
							$scope.attendees = [];

							self.attendane = null;
							self.master = 0;

							self.selectedattendees = [];
							self.attendeelist = [];
							self.training = [];
							self.role = 0;

							self.showCalendarTab = true;
							self.showUpcomingTab = false;

							self.showerrors = false;
							self.showaddattendeewindow = false;
							self.showlistwindow = true;
							self.showattendeelist = false;
							self.topicid = 0;
							self.attendeeid = 0;
							self.showaddwindow = false;
							self.showmaincategory = false;
							self.showsubcategory = false;
							self.showfeedbacks = false;
							self.showexpectedattendeelist = false;
							self.showemptyattendeelist = false;
							self.showAdminfeedback = false;
							self.showadminfeedbackview = false;
							self.showAttendees = false;
							self.showattendance = false;
							self.completedsearch = false;
							self.upcomingsearch = false;

							self.shownotification = false;
							self.trainingstatus = 0;
							self.traininglist = null;
							self.attendees = [];

							// -------------------------------------------------------------------------------------

							self.load = function() {
								self.getUserroles();
								self.loadMainCategory();
								self.fetchDepartmentDetails();

								if ($rootScope.programStatus != null) {
									if ($rootScope.programStatus == 'Completed') {
										$scope.openCompletedTab();
									} else if ($rootScope.programStatus == 'Upcoming') {
										$scope.openUpcomingTab();
									}
									$rootScope.programStatus = null;
								}

							}

							self.getUserroles = function() {

								MyTrainingListService
										.fetchUserRoles()
										.then(
												function(response) {
													self.role = response;
												},
												function(errResponse) {
													console
															.error('Error While Fetching User Roles');
												});
							}

							self.loadMainCategory = function() {
								MyTrainingListService
										.fetchMainCategory()
										.then(
												function(response) {
													self.category = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});
							}

							self.fetchDepartmentDetails = function() {
								departmentService
										.fetchDepartmentDetails()
										.then(
												function(response) {
													self.departmentlist = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching department details');
												});

							}

							// -------------------------------------------------------------------------------------

							$scope.openCalendarTab = function() {
								self.showCalendarTab = true;
								self.loadEvents();
								self.completedsearch = false;
								self.upcomingsearch = false;
							};

							// For Calendar
							self.calendarView = 'month';
							self.viewDate = new Date();
							var actions = [/*
											 * { label : '<i class=\'glyphicon
											 * glyphicon-pencil\'></i>',
											 * onClick : function(args) {
											 * alert.show('Edited',
											 * args.calendarEvent); } }, { label : '<i
											 * class=\'glyphicon
											 * glyphicon-remove\'></i>',
											 * onClick : function(args) {
											 * alert.show('Deleted',
											 * args.calendarEvent); } }
											 */];
							self.events = [];

							self.cellIsOpen = false;

							self.eventClicked = function(event) {
								alert.show('Clicked', event);
							};

							self.eventEdited = function(event) {
								alert.show('Edited', event);
							};

							self.eventDeleted = function(event) {
								alert.show('Deleted', event);
							};

							self.eventTimesChanged = function(event) {
								alert.show('Dropped or resized', event);
							};

							self.toggle = function($event, field, event) {
								$event.preventDefault();
								$event.stopPropagation();
								event[field] = !event[field];
							};

							self.timespanClicked = function(date, cell) {

								if (self.calendarView === 'month') {
									if ((self.cellIsOpen && moment(date)
											.startOf('day').isSame(
													moment(self.viewDate)
															.startOf('day')))
											|| cell.events.length === 0
											|| !cell.inMonth) {
										self.cellIsOpen = false;
									} else {
										self.cellIsOpen = true;
										self.viewDate = date;
									}
								} else if (self.calendarView === 'year') {
									if ((self.cellIsOpen && moment(date)
											.startOf('month').isSame(
													moment(self.viewDate)
															.startOf('month')))
											|| cell.events.length === 0) {
										self.cellIsOpen = false;
									} else {
										self.cellIsOpen = true;
										self.viewDate = date;
									}
								}

							};

							self.loadEvents = function() {

								self.events = [];

								MyTrainingListService
										.fetchTrainingListForCalendar()
										.then(
												function(response) {
													angular
															.forEach(
																	response,
																	function(
																			value,
																			index) {

																		if (new Date(
																				value.endDate)
																				.getTime() < new Date()
																				.getTime()
																				&& value.topicstatus == 1) {

																			self.events
																					.push({
																						title : value.topic,
																						color : calendarConfig.colorTypes.important,
																						startsAt : moment(value.startDate),
																						endsAt : moment(value.endDate),
																						draggable : true,
																						resizable : true,
																						actions : actions
																					});

																		} else if (value.topicstatus == 2) {
																			self.events
																					.push({
																						title : value.topic,
																						color : calendarConfig.colorTypes.warning,
																						startsAt : moment(value.startDate),
																						endsAt : moment(value.endDate),
																						draggable : true,
																						resizable : true,
																						actions : actions
																					});
																		} else if ((value.topicstatus) == 1) {
																			self.events
																					.push({
																						title : value.topic,
																						color : calendarConfig.colorTypes.info,
																						startsAt : moment((value.startDate)),
																						endsAt : moment((value.endDate)),
																						draggable : true,
																						resizable : true,
																						actions : actions
																					});
																		} else {
																			self.events
																					.push({
																						title : value.topic,
																						color : calendarConfig.colorTypes.important,
																						startsAt : moment((value.startDate)),
																						endsAt : moment((value.endDate)),
																						draggable : true,
																						resizable : true,
																						actions : actions
																					});
																		}

																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching Training Programs');
												});

							};

							self.loadEvents();

							// -------------------------------------------------------------------------------------

							$scope.openUpcomingTab = function() {
								self.trainingstatus = 0;
								self.showCalendarTab = false;
								self.showUpcomingTab = true;
								self.upcomingsearch = true;
								self.completedsearch = false;
								self.fetchMyTrainingList();
							};

							$scope.openCompletedTab = function() {
								self.trainingstatus = 1;
								self.showCalendarTab = false;
								self.showUpcomingTab = false;
								self.completedsearch = true;
								self.upcomingsearch = false;

								self.fetchCompletedTrainingList();
							};
							// ----------------------------Topic search for
							// completed
							// trainings---------------------------------------------------------------
							self.CompletedTopicsearch = function() {

								var formdt = "none";
								var todt = "none";

								if (self.fromDate != null
										&& self.fromDate != "") {
									formdt = $rootScope
											.dateToSqlDateString(self.fromDate);
								}
								if (self.toDate != null && self.toDate != "") {
									todt = $rootScope
											.dateToSqlDateString(self.toDate);
								}

								MyTrainingListService
										.fetchAllCompletedTrainingByDate(
												formdt, todt)
										.then(
												function(response) {
													self.traininglist = response;
													angular
															.forEach(
																	self.traininglist,
																	function(
																			value,
																			index) {
																		value.startdatetime = new Date(
																				(value.startdatetime));

																		value.enddatetime = new Date(
																				(value.enddatetime));

																		if ((value.topicstatus) == 2) {
																			value.topicstatus = "Completed";
																		} else if ((value.topicstatus) == 1) {
																			value.topicstatus = "Pending";
																		}
																	});
												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});

							}
							// --------------------------------Upcoming Topic
							// Search--------------------------------------------------
							self.UpcomingTopicSearch = function() {
								var formdt = "none";
								var todt = "none";

								if (self.fromDate != null
										&& self.fromDate != "") {
									formdt = $rootScope
											.dateToSqlDateString(self.fromDate);
								}
								if (self.toDate != null && self.toDate != "") {
									todt = $rootScope
											.dateToSqlDateString(self.toDate);
								}

								MyTrainingListService
										.fetchAllUpcomingTrainingByDate(formdt,
												todt)
										.then(
												function(response) {
													self.traininglist = response;
													angular
															.forEach(
																	self.traininglist,
																	function(
																			value,
																			index) {
																		value.startdatetime = new Date(
																				(value.startdatetime));

																		value.enddatetime = new Date(
																				(value.enddatetime));

																		if ((value.topicstatus) == 2) {
																			value.topicstatus = "Completed";
																		} else if ((value.topicstatus) == 1) {
																			value.topicstatus = "Pending";
																		}
																	});
												},
												function(errResponse) {
													console
															.error('Error while fetching Currencies');
												});

							}

							// ---------------------------End---------------------------------------------------------------------------
							self.fetchMyTrainingList = function() {
								MyTrainingListService
										.fetchMyTrainingList()
										.then(
												function(response) {
													self.traininglist = response;
													angular
															.forEach(
																	self.traininglist,
																	function(
																			value,
																			index) {
																		value.startdatetime = new Date(
																				(value.startdatetime));

																		value.enddatetime = new Date(
																				(value.enddatetime));

																		if ((value.topicstatus) == 2) {
																			value.topicstatus = "Completed";
																		} else if ((value.topicstatus) == 1) {
																			value.topicstatus = "Pending";
																		}
																	});
												},
												function(errResponse) {
													console
															.error('Error while fetching Training Programs');
												});
							}

							self.fetchCompletedTrainingList = function() {
								MyTrainingListService
										.fetchCompletedTrainingList()
										.then(
												function(response) {
													self.traininglist = response;
													angular
															.forEach(
																	self.traininglist,
																	function(
																			value,
																			index) {
																		value.startdatetime = new Date(
																				(value.startdatetime));

																		value.endDateTime = new Date(
																				(value.endDateTime));

																		if ((value.topicstatus) == 2) {
																			value.topicstatus = "Completed";
																		} else if ((value.topicstatus) == 1) {
																			value.topicstatus = "Pending";
																		}

																	});
												},
												function(errResponse) {
													console
															.error('Error while fetching Training Programs');
												});
							}

							// -------------------------------------------------------------------------------------

							self.add = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
							}

							$scope.showSubCategory = function() {
								if (self.tschedule.subCategory == 0) {
									self.showsubcategory = true;
								} else {
									self.showsubcategory = false;
								}
							}

							self.chooseSubCategory = function() {
								self.maincat = self.tschedule.maincategory

								if (self.maincat == 0) {
									self.showmaincategory = true;
									self.subcategory = null;
								} else {
									self.showmaincategory = false;
									self.fetchSubCategory(self.maincat);

								}
							}

							self.fetchSubCategory = function(maincat) {
								MyTrainingListService
										.fetchSubCategory(maincat)
										.then(
												function(response) {
													self.subcategory = response;
												},
												function(errResponse) {
													console
															.error("Error while fetching SubCategories.....!!!!");
												});

							};

							$scope.loadTags = function(query) {

								$scope.trainer = [];

								MyTrainingListService
										.fetchTrainers(query)
										.then(
												function(response) {
													self.trainerdetails = response;
												},

												function(errResponse) {
													console
															.error('Error while fetching Skills');
												});

								$scope.trainer = [];
								angular.forEach(self.trainerdetails, function(
										value, index) {

									$scope.trainer.push({
										user_id : value.user_id,
										text : value.firstName + " "
												+ value.lastName
									});

								})
								return $scope.trainer;

							};

							// ----------Jossina's code start----------
							self.getAllAttendeesbydeprt = function() {
								self.selectall = '';
								departmentService
										.fetchAttendeesByDepartment(
												self.profile.department)
										.then(
												function(response) {
													self.listofattendee = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching attendees');
												});
							}
							self.selectAllattendees = function() {
								if (self.selectall == 1) {
									angular.forEach(self.listofattendee,
											function(value, index) {
												$scope.attendees.push({
													user_id : value.user_id,
													text : value.firstName
												});
											})
								} else {
									$scope.attendees = [];

								}
							}

							// ----------Jossina's code end----------

							$scope.loadAttendees = function(query) {

								$scope.attender = [];

								self.attender = '';
								MyTrainingListService
										.fetchAttendees(query,
												self.profile.department)
										.then(
												function(response) {
													self.attenders = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching attendee');
												});
								$scope.attender = [];
								angular.forEach(self.attenders, function(value,
										index) {
									$scope.attender.push({
										user_id : value.user_id,
										text : value.firstName + " "
												+ value.lastName
									});
								})
								return $scope.attender;

							};

							self.submit = function($event) {

								self.tschedule.startDateTime = self.tschedule.startDateTime
										+ ":00.0";
								self.tschedule.endDateTime = self.tschedule.endDateTime
										+ ":00.0";

								if ($scope.trainingListForm.$valid) {

									var sdate = '';
									var edate = '';

									var curDate = $filter('date')(new Date(),
											'yyyy-MM-dd')

									var values = self.tschedule.startDateTime
											.split(" ");
									sdate = values[0];
									var stime = values[1];

									var value = self.tschedule.endDateTime
											.split(" ");
									edate = value[0];
									var etime = value[1];

									if (self.tschedule.id == 0) {

										if (sdate < curDate) {

											self.startdateerror = false;
											self.enddateerror = false;
											self.endtimeerror = false;
											self.starttimeerror = false;

											self.dateerror = true;

										} else if (edate == sdate) {

											if (etime < stime || etime == stime) {

												self.endtimeerror = true;
												self.starttimeerror = true;

												self.startdateerror = false;
												self.enddateerror = false;
												self.dateerror = false;
											} else {

												self.startdateerror = false;
												self.enddateerror = false;
												self.dateerror = false;
												self.endtimeerror = false;
												self.starttimeerror = false;

												self
														.saveTraining(self.tschedule);

											}

										} else if (edate < sdate) {

											self.dateerror = false;
											self.endtimeerror = false;
											self.starttimeerror = false;

											self.startdateerror = true;
											self.enddateerror = true;
										} else {

											self.startdateerror = false;
											self.enddateerror = false;
											self.dateerror = false;
											self.endtimeerror = false;
											self.starttimeerror = false;

											self.saveTraining(self.tschedule);
										}

									} else {

										if (edate == sdate) {

											if (etime < stime || etime == stime) {

												self.endtimeerror = true;
												self.starttimeerror = true;

												self.startdateerror = false;
												self.enddateerror = false;
												self.dateerror = false;

											} else {

												self.startdateerror = false;
												self.enddateerror = false;
												self.dateerror = false;
												self.endtimeerror = false;
												self.starttimeerror = false;

												self.updateTraining(
														self.tschedule,
														self.tschedule.id);
											}

										} else if (edate < sdate) {

											self.dateerror = false;
											self.endtimeerror = false;
											self.starttimeerror = false;

											self.startdateerror = true;
											self.enddateerror = true;
										} else {

											self.startdateerror = false;
											self.enddateerror = false;
											self.dateerror = false;
											self.endtimeerror = false;
											self.starttimeerror = false;

											self.updateTraining(self.tschedule,
													self.tschedule.id);
										}
									}

									$event.preventDefault();
								} else {
									self.showerrors = true;
								}
								;

							}

							self.saveTraining = function(tschedule) {

								self.showerrors = false;

								self.tschedule.trainerlist = [];
								self.tschedule.attendeelist = [],

								angular.forEach($scope.trainers, function(
										value, index) {
									tschedule.trainerlist.push(value.user_id);

								});

								angular.forEach($scope.attendees, function(
										value, index) {
									tschedule.attendeelist.push(value.user_id);
								});

								MyTrainingListService
										.scheduleTraining(tschedule)
										.then(

												function(response) {
													if (response.errorCode == 1) {

														$rootScope
																.showNotification(
																		"Training Scheduled successfully",
																		true);
														self.reset();
													} else if (response.errorCode == 2) {

														$rootScope
																.showNotification(
																		"This Training is Already Scheduled",
																		false);
														self.reset();
													} else {

														$rootScope
																.showNotification(
																		"Schedule Training Failed",
																		false);
														self.reset();
													}

													self.fetchMyTrainingList();
													self.showaddwindow = false;
													self.showlistwindow = true;

												},
												function(errResponse) {
													console
															.error("Error while schedule Training.....!!!!");
												})

							}

							self.cancel = function() {

								if (self.trainingstatus == 0)
									$scope.openUpcomingTab();
								else
									$scope.openCompletedTab();

								self.tschedule = {
									id : 0,
									topic : '',
									maincategory : '',
									categoryName : '',
									subCategory : '',
									subCategoryName : '',
									description : '',
									startDateTime : '',
									endDateTime : '',
									trainerlist : [],
									attendeelist : [],
									trainers : ''
								};

								self.profile = {
									department : 0,
								}

								$scope.trainers = [];
								$scope.attendees = [];
								self.selectall = 0;

								self.showerrors = false;
								self.showaddattendeewindow = false;
								self.showlistwindow = true;
								self.showattendeelist = false;
								self.topicid = 0;
								self.attendeeid = 0;
								self.showaddwindow = false;
								self.showmaincategory = false;
								self.showsubcategory = false;
								self.showfeedbacks = false;
								self.showexpectedattendeelist = false;
								self.showemptyattendeelist = false;
								self.showAdminfeedback = false;
								self.trainerid = '';
								self.attendees = [];

							}
							// -------------------------------------------------------------------------------------

							self.fetchAllAttendees = function(topicid) {
								MyTrainingListService
										.fetchAllAttendees(topicid)
										.then(
												function(response) {
													self.attendeelist = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching attendees');
												});
							}

							self.addAttendees = function(id, topic, sdate) {

								self.selectedattendees = [];

								self.showlistwindow = false;
								self.showexpectedattendeelist = true;

								self.topicid = id;
								self.training.topic = topic
								self.training.startDateTime = new Date(sdate);
								self.fetchAllAttendees(id);

							}

							self.fetchAttendee = function(id) {

								self.selectedattendees = [];

								MyTrainingListService
										.fetchAttendee(id)
										.then(
												function(response) {
													self.selectedattendees = response;

												},
												function(errResponse) {
													console
															.error("Error while fetching SubCategories.....!!!!");
												});

							}

							$scope.loadToNextList = function() {

								if (self.selectedattendees.length <= 0) {
									self.selectedattendees = [];
								}

								angular
										.forEach(
												self.attendees,
												function(values, indexs) {
													self.user_id = values;
													angular
															.forEach(
																	self.attendeelist,
																	function(a,
																			b) {

																		if (self.user_id == a.user_id) {

																			self.index = b;
																			self.selectedattendees
																					.push({
																						user_id : a.user_id,
																						firstName : a.firstName,
																						lastName : a.lastName

																					});

																			self.attendeelist
																					.splice(
																							self.index,
																							1);
																		}

																	});
												});
							}

							$scope.unLoadFromList = function() {

								if (self.attendeelist.length <= 0) {
									self.attendeelist = [];
								}

								angular
										.forEach(
												self.selectedattendeesid,
												function(values, indexs) {
													self.user_id = values;

													angular
															.forEach(
																	self.selectedattendees,
																	function(a,
																			b) {

																		if (self.user_id == a.user_id) {

																			self.index = b;
																			self.attendeelist
																					.push({
																						user_id : a.user_id,
																						firstName : a.firstName,
																						lastName : a.lastName
																					});

																			self.selectedattendees
																					.splice(
																							self.index,
																							1);
																		}

																	});
												});

							}

							self.addnewAttendees = function() {
								self.selectedattendeesid = [];

								if (!self.selectedattendees.length) {

									self.showemptyattendeelist = true;

									$timeout(function() {
										self.showemptyattendeelist = false;
									}, 3000);

								} else {
									angular.forEach(self.selectedattendees,
											function(a, b) {

												self.selectedattendeesid
														.push(a.user_id);

											});

									self.addAttendee(self.selectedattendeesid,
											self.topicid);
								}

							};

							self.addAttendee = function(selectedattendeesid,
									topicid) {

								MyTrainingListService
										.addAttendee(selectedattendeesid,
												topicid)
										.then(
												function(response) {
													self.showlistwindow = true;
													self.showaddattendeewindow = false;
													if(self.trainingstatus == 1){
														self.fetchMyTrainingList();
														
													}else{
														self.fetchCompletedTrainingList();
													}
													
													self.reset();

													/*
													 * self
													 * .showNotification("Attendees
													 * Added successfully");
													 */

													$rootScope
															.showNotification(
																	"Attendees Added successfully",
																	true);
													// $window.location.reload();
												},
												function(errResponse) {
													$rootScope
															.showNotification(
																	"Add Attendees Failed",
																	false);
												});

							};

							self.viewAttendedPeople = function(id, topic) {

								if (self.role == 1) {
									self.showAdminfeedback = true;
									self.showlistwindow = false;
									self.showAttendees = true;

									self.topicid = id;
									self.training.topic = topic;

									self.trainernames = '';
									AdminFeedbackViewService
											.fetchAllTrainerNames(self.topicid)
											.then(
													function(response) {
														self.trainernames = response;
													},
													function(errResponse) {
														console
																.error('Error while fetching Categories');
													});

								}

								if (self.role == 2) {
									self.topicid = id;
									self.showlistwindow = false;
									self.showattendeelist = true;
									self.fetchAttendeeDetails(id);
								}
							}

							self.getAllAttendees = function() {
								AdminFeedbackViewService
										.fetchAllAttendees(self.topicid,
												self.trainerid)
										.then(
												function(response) {
													self.attendees = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}

							self.viewFeedbacks = function(id, topic,
									trainernames) {

								self.showadminfeedbackview = true;
								self.showAttendees = false;

								self.topicview = topic;
								self.attendeeid = id;
								self.showAttendeeList = false;
								self.viewAdminfeedbacklist(self.attendeeid,
										self.topicid, self.trainerid);
							}

							self.viewAdminfeedbacklist = function(attendeeid,
									topicid, trainerid) {
								self.feedback = '';

								AdminFeedbackViewService
										.viewfeedbacklist(attendeeid, topicid,
												trainerid)
										.then(
												function(response) {
													self.feedback = response;

													self.feedback.trainername = response.trainername
															+ ' '
															+ response.lastname;

													self.feedback.attendeename = response.attendeefn
															+ ' '
															+ response.attendeeln;

												},
												function(errResponse) {
													console
															.error('Error while fetching attendee details');
												});

							}

							self.fetchAttendeeDetails = function(topicid) {

								MyTrainingListService
										.fetchAttendeeDetails(topicid)
										.then(
												function(response) {
													self.training = response;
													self.training.topic = response[0].topic
													self.training.topicid = response[0].topicid
													self.training.startDateTime = response[0].startdatetime;

													self
															.fetchAllAttendees(topicid);
													self.fetchAttendee(topicid);

												},
												function(errResponse) {
													console
															.error('Error while fetching attendee details');
												});

							}

							self.viewFeedbackbyTrainer = function(id) {

								self.attendeeid = id;
								self.showfeedbacks = true;
								self.showlistwindow = false;
								self.showattendeelist = false;

								self.viewfeedbacklist(self.attendeeid,
										self.topicid);

							}

							self.viewfeedbacklist = function(attendeeid,
									topicid) {

								self.feedback = '';
								MyTrainingListService
										.viewfeedbacklist(attendeeid, topicid)
										.then(
												function(response) {
													self.feedback = response;
													self.feedback.attendeename = response.attendeefn
															+ ' '
															+ response.attendeeln;

												},
												function(errResponse) {
													console
															.error('Error while fetching attendee details');
												});

							}

							self.expectedPeople = function(id) {
								self.topicid = id;
								self.showlistwindow = false;
								self.showexpectedattendeelist = true;
								self.fetchAttendeeDetails(id);
							}

							self.deleteSchedule = function(id) {
								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													MyTrainingListService
															.deleteSchedule(id)
															.then(
																	function(
																			response) {
																		if (response.errorCode == 1) {
																			self
																					.fetchMyTrainingList();

																			$rootScope
																					.showNotification(
																							"Training Schedule Deleted Successfully",
																							true);
																		} else {
																			self
																					.fetchMyTrainingList();

																			$rootScope
																					.showNotification(
																							"Delete Training Schedule Failed",
																							false);
																		}
																	},
																	function(
																			errResponse) {

																		self
																				.fetchMyTrainingList();

																		$rootScope
																				.showNotification(
																						"Delete Training Schedule Failed",
																						false);
																		console
																				.error('Error while deleting Training Schedule.'
																						+ errResponse);
																	});
												}, function() {
												});
							};

							self.edit = function(id) {

								// var curDate = $filter('date')(new Date(),
								// 'yyyy-MM-dd')
								//
								// var values = self.tschedule.startDateTime
								// .split(" ");
								// sdate = values[0];

								self.loadMainCategory();

								self.showerrors = false;

								MyTrainingListService
										.fetchTrainingScheduleById(id)
										.then(
												function(response) {
													self.tschedule = response;

													var values = self.tschedule.startDateTime
															.split(':');
													sdate = values[0];
													stime = values[1];
													self.tschedule.startDateTime = sdate
															+ ":" + stime;

													var endvalues = self.tschedule.endDateTime
															.split(':');
													edate = endvalues[0];
													etime = endvalues[1];
													self.tschedule.endDateTime = edate
															+ ":" + etime;

													if (self.tschedule.category.parentId == '0') {
														self.tschedule.maincategory = self.tschedule.category.id
																+ '';
													} else {
														self
																.fetchSubCategory(self.tschedule.category.parentId);
														self.tschedule.maincategory = self.tschedule.category.parentId
																+ '';
														self.tschedule.subCategory = self.tschedule.category.id
																+ '';
													}

													$scope.trainers = [];
													$scope.attendees = [];
													angular
															.forEach(
																	self.tschedule.trainerdetails,
																	function(
																			value,
																			index) {
																		$scope.trainers
																				.push({
																					user_id : value.user_id,
																					text : value.firstName
																							+ " "
																							+ value.lastName
																				});
																	})

													angular
															.forEach(
																	self.tschedule.attendeedetails,
																	function(
																			value,
																			index) {
																		$scope.attendees
																				.push({
																					user_id : value.user_id,
																					text : value.firstName
																							+ " "
																							+ value.lastName
																				});
																	})

													self.showaddwindow = true;
													self.showlistwindow = false;

												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});
							};

							self.updateTraining = function(tschedule, id) {

								self.showerrors = false;

								self.tschedule.trainerlist = [];
								self.tschedule.attendeelist = [],

								angular.forEach($scope.trainers, function(
										value, index) {
									tschedule.trainerlist.push(value.user_id);
								});

								angular.forEach($scope.attendees, function(
										value, index) {
									tschedule.attendeelist.push(value.user_id);
								});

								MyTrainingListService
										.updateTraining(tschedule, id)
										.then(
												function(response) {

													$rootScope
															.showNotification(
																	"Update Training Successfully",
																	true);
													if (self.trainingstatus == 0) {
														self
																.fetchMyTrainingList();

													} else {

														self
																.fetchCompletedTrainingList();
													}
													self.reset();

												},
												function(errResponse) {

													$rootScope
															.showNotification(
																	"Update Training Schedule Failed",
																	false);

													self
															.fetchCompletedTrainingList();
													self.fetchMyTrainingList();
													self.reset();

												});
							};

							self.reset = function() {
								self.showerrors = false;
								self.showaddattendeewindow = false;
								self.showlistwindow = true;
								self.showattendeelist = false;
								self.topicid = 0;
								self.attendeeid = 0;
								self.showaddwindow = false;
								self.showmaincategory = false;
								self.showsubcategory = false;
								self.showfeedbacks = false;
								self.showexpectedattendeelist = false;
								self.showNotification = false;
								self.showAdminfeedback = false;

								$scope.trainers = [];
								$scope.attendees = [];

								self.attendees = [];

								self.tschedule = {
									id : 0,
									topic : '',
									maincategory : '',
									categoryName : '',
									subCategory : '',
									subCategoryName : '',
									description : '',
									startDateTime : '',
									endDateTime : '',
									trainerlist : [],
									attendeelist : [],
									trainers : ''
								};

								self.profile = {
									department : 0,
								}
								self.selectall = 0;

							}

							self.back = function() {
								self.showfeedbacks = false;
								self.showattendeelist = true;
								self.showerrors = false;
								self.showaddattendeewindow = false;
								self.showlistwindow = false;
								self.showNotification = false;
								self.showAdminfeedback = false;

							}

							self.backtofeedback = function() {

								self.showadminfeedbackview = false;
								self.showAttendees = true;

							}

							/* START OF MARK ATTENDANCE */

							self.markAttendace = function(topicid, topic) {
								self.topicid = topicid;
								self.topicname = topic;

								self.showattendance = true;
								self.showlistwindow = false;

								self.getUsers(topicid);
							}

							self.getUsers = function(topicid) {
								attendanceService
										.fetchUsers(topicid)
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
													// if ($rootScope.userId !=
													// 1)
													// self
													// .checkDate(self.user.testid);

												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});

							};

							self.save = function($event) {
								self.saveAttendance(self.user);

							}

							// self.checkDate = function(trainingid){
							// attendanceService
							// .checkDate(trainingid)
							// .then(
							// function(response) {
							// self.status = response;
							// },
							// function(errResponse) {
							// console
							// .error('Error while fetching Currencies');
							// });
							// }
							//							
							self.saveAttendance = function(user) {
								self.user.testid = self.topicid;
								var uploadUrl = "addattendance/";

								if (!self.attendane.length) {

									$rootScope.showNotification(""
											+ "No Attendees available", false);

								} else {

									attendanceService
											.addAttendance(uploadUrl, self.user)
											.then(
													function(response) {
														if (response.errorCode == 1)
															$rootScope
																	.showNotification(
																			""
																					+ "Attendance updated successfully",
																			true);
														else {
															$rootScope
																	.showNotification(
																			""
																					+ "Date for marking attendance is expired. Please contact administrator",
																			false);
														}
													},
													function(errResponse) {
														$rootScope
																.showNotification(
																		""
																				+ "Updation failed!!!",
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
								}
							};

							self.sendMail = function(topicid) {
								var confirm = $mdDialog.confirm().title(
										'Send Mail!!').textContent(
										'Are you sure?').ariaLabel('Lucky day')
										.ok('Yes').cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													UpcomingTrngSrvs
															.sendMail(topicid)

															.then(
																	function(
																			response) {

																		if (response.errorCode == 1) {
																			$rootScope
																					.showNotification(
																							""
																									+ "Mail has been sent  successfully!",
																							true);
																			self
																					.fetchMyTrainingList();
																		} else {
																			$rootScope
																					.showNotification(
																							""
																									+ "Mail sending failed!!!",
																							false);
																			self
																					.fetchMyTrainingList();
																		}

																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while fetching trainings');

																	});

												}, function() {
												});

							}

							self.backFromAttendance = function() {
								self.showattendance = false;
								self.showlistwindow = true;

								self.fetchCompletedTrainingList();
							}

							/* END OF MARK ATTENDANCE */

							// For Training Materials
							self.showmateriallistwindow = false;
							self.showmaterialaddwindow = false;

							self.material = {
								material_id : 0,
								topic : '',
								description : '',
								reference : '',
							}

							self.materials = {
								filepath : '',
								id : 0,
								extn : '',
								reference : '',
								description : '',
							}

							self.manageMaterial = function(topicid, topic) {
								self.material.topic = topic;

								self.showmateriallistwindow = true;
								self.showlistwindow = false;

								self.loadMaterials(topic);

							}

							self.loadMaterials = function(topic) {

								TrainingMaterialService
										.fetchTrainingMaterials(topic)
										.then(
												function(response) {
													self.materials = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching training materials');
												});

							}

							self.backtomaterials = function() {

								self.reset();
								self.showmateriallistwindow = false;
								self.showlistwindow = true;

							}

							self.addMaterials = function() {
								self.showmaterialaddwindow = true;
								self.showlistwindow = false;
								self.showmateriallistwindow = false;
								self.material.material_id = 0;
							}

							self.MaterialManagement = function($event) {

								if ($scope.addTrainingMaterialForm.$valid) {

									if (self.material.material_id == 0) {
										self.saveMaterials(self.material);
										console
												.log("Save New Training Material");
									} else {

										self.updateMaterials(self.material,
												self.material.material_id);
										console
												.log("Update Exsisting Training Material");

									}
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}
							}

							self.saveMaterials = function(material) {

								self.topic = self.material.topic;

								self.showerrors = false;

								var file = $scope.myFile;

								if (file == null) {
									self.showfileerror = true;
								}

								else {

									console.log('file is ');
									console.dir(file);

									var uploadUrl = "saveTrainingMaterial/";

									TrainingMaterialService
											.saveMaterial(file, self.material,
													uploadUrl)
											.then(
													function(response) {

														self.showmaterialaddwindow = false;
														self.showlistwindow = false;
														self.showmateriallistwindow = true;

														self.material.topic = self.topic;

														$rootScope
																.showNotification(
																		"Material Added Successfully",
																		true);

														self
																.loadMaterials(self.material.topic);

													},
													function(errResponse) {

														self.showmaterialaddwindow = false;
														self.showlistwindow = false;
														self.showmateriallistwindow = true;

														self.material = {
															material_id : 0,
															description : '',
															reference : '',
														}
														self.materials = {
															filepath : '',
															id : 0,
															extn : '',
															reference : '',
															description : '',
														}

														self.material.topic = self.topic;
														$rootScope
																.showNotification(
																		"Add Material Failed",
																		false);
													});

									self.reset();

								}

							};

							self.editMaterial = function(materialid) {

								TrainingMaterialService
										.fetchMaterialById(materialid)
										.then(
												function(response) {
													self.material = response[0];
												},
												function(errResponse) {
													console
															.error('Error while fetching Training Materials');
												});
								self.addMaterials();
							}

							self.updateMaterials = function(material,
									materialid) {

								self.topic = self.material.topic;
								self.showerrors = false;
								self.material.material_id = materialid;

								var file = $scope.myFile;

								console.log('file is ');
								console.dir(file);

								var uploadUrl = "updatetrainingmaterial/";

								TrainingMaterialService
										.updateMaterial(file, self.material,
												uploadUrl)
										.then(
												function(response) {
													self.showmaterialaddwindow = false;
													self.showlistwindow = false;
													self.showmateriallistwindow = true;

													self.material = {
														material_id : 0,
														description : '',
														reference : '',
													}
													self.materials = {
														filepath : '',
														id : 0,
														extn : '',
														reference : '',
														description : '',
													}

													self.material.topic = self.topic;

													self
															.loadMaterials(self.material.topic);

													$rootScope
															.showNotification(
																	"Material Updated Successfully",
																	true);
												},
												function(errResponse) {
													self.showmaterialaddwindow = false;
													self.showlistwindow = false;
													self.showmateriallistwindow = true;

													self.material = {
														material_id : 0,
														description : '',
														reference : '',
													}
													self.materials = {
														filepath : '',
														id : 0,
														extn : '',
														reference : '',
														description : '',
													}

													self.material.topic = self.topic;

													self
															.loadMaterials(self.material.topic);

													$rootScope
															.showNotification(
																	"Material Updatation Failed",
																	false);
												});

								self.reset();

							};

							self.deleteMaterial = function(materialid) {

								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													TrainingMaterialService
															.deleteMaterial(
																	materialid)
															.then(
																	function(
																			response) {

																		if (response.errorCode == 1) {

																			// self.showNotification("Training
																			// Material
																			// Deleted");
																			self
																					.loadMaterials(self.material.topic);

																			$rootScope
																					.showNotification(
																							"Material Deleted Successfully",
																							true);
																		}
																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while deleting training material.'
																						+ errResponse);
																		$rootScope
																				.showNotification(
																						"Material Deletion Failed",
																						false);
																	});
												}, function() {
												});

							}

							self.cancelMaterial = function() {

								self.showerrors = false;
								self.showmaterialaddwindow = false;
								self.showmateriallistwindow = true;
								self.showlistwindow = false;
								self.showfileerror = false;
								self.showtitleerror = false;

							}

							/* **ATTENDEE ADD/VIEW FEEDBACK ** */

							self.showattendeefeedbacks = false;
							self.showtrainerlist = false;
							self.showaddfeedbackwindow = false;

							self.feedback = {
								id : null,
								topicid : '',
								trainerid : '',
								topicname : '',
								trainername : '',
								report : '',
								data : '',
								comment : '',
								followup : '',
								demo : '',
							};

							self.addViewFeedback = function(topicid, topic) {

								self.training.topic = topic;
								self.training.topicid = topicid;

								self.showlistwindow = false;
								self.showtrainerlist = true;

								self.fetchAllTrainers(topicid);
							}

							self.fetchAllTrainers = function(topicid) {
								MyTrainingListService
										.fetchAllTrainers(topicid)
										.then(
												function(response) {
													self.attendeelist = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching attendees');
												});
							}

							self.viewFeedback = function(topicid, user_id,
									firstName, lastName) {

								self.showlistwindow = false;
								self.showtrainerlist = false;
								self.showattendeefeedbacks = true;
								self.trainername = firstName + " " + lastName;

								self.fetchFeedbackFor(topicid, user_id);
							}

							self.fetchFeedbackFor = function(topicid, user_id) {

								feedbackService.viewFeedback(topicid, user_id)
										.then(function(response) {
											self.feedback = response;
											self.hideButton();

										}, function(errResponse) {
											console.error('Error');
										});

							}

							self.addFeedback = function(topicid, user_id,
									firstName, lastName, topicname) {

								self.showlistwindow = false;
								self.showtrainerlist = false;
								self.showaddfeedbackwindow = true;

								self.feedback.trainername = firstName + " "
										+ lastName;
								self.feedback.topicname = topicname;

								self.feedback.topicid = topicid;
								self.feedback.trainerid = user_id;

								self.feedback.report = 5;
								self.feedback.followup = 0;
								self.feedback.demo = 0;
							}

							self.giveFeedback = function($event) {

								if ($scope.feedbackform.$valid) {
									self.updateFeedback(self.feedback)
								} else {
									self.showerrors = true;
								}
								$event.preventDefault();
							};

							self.updateFeedback = function(feedback) {
								self.showerrors = false;
								var uploadUrl = "updatefeedback/";
								feedbackService
										.updateFeedback(uploadUrl,
												self.feedback)
										.then(
												function(response) {
													if (response.errorCode == 1) {

														self.showlistwindow = false;
														self.showtrainerlist = true;
														self.showaddfeedbackwindow = false;

														self.feedback = {
															id : null,
															topicid : '',
															trainerid : '',
															topicname : '',
															trainername : '',
															report : '',
															data : '',
															comment : '',
															followup : '',
															demo : '',
														};

														$rootScope
																.showNotification(
																		"Give Feedback successfully",
																		true);
													} else {
														self.showlistwindow = false;
														self.showtrainerlist = true;
														self.showaddfeedbackwindow = false;

														self.feedback = {
															id : null,
															topicid : '',
															trainerid : '',
															topicname : '',
															trainername : '',
															report : '',
															data : '',
															comment : '',
															followup : '',
															demo : '',
														};

														$rootScope
																.showNotification(
																		"Give Feedback failed!!!",
																		false);
													}

												},
												function(errResponse) {
													console
															.error('Error while saving feedback');
												});
							}

							self.backtoTrainerList = function() {
								self.reset();

								self.feedback = {
									id : null,
									topicid : '',
									trainerid : '',
									topicname : '',
									trainername : '',
									report : '',
									data : '',
									comment : '',
									followup : '',
									demo : '',
								};

								self.showlistwindow = false;
								self.showtrainerlist = true;
								self.showaddfeedbackwindow = false;
								self.showattendeefeedbacks = false;

							}

							self.backtoTopicList = function() {
								self.reset();

								self.feedback = {
									id : null,
									topicid : '',
									trainerid : '',
									topicname : '',
									trainername : '',
									report : '',
									data : '',
									comment : '',
									followup : '',
									demo : '',
								};

								self.showlistwindow = true;
								self.showtrainerlist = false;
								self.showaddfeedbackwindow = false;

							}

							/* **ATTENDEE ADD/VIEW FEEDBACK ** */

							/* Sending Mails for Attendees */

							self.sendMailforAttendees = function(topicid) {

								var confirm = $mdDialog.confirm().title(
										'Send Email!!').textContent(
										'Are you sure?').ariaLabel('Lucky day')
										.ok('Yes').cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													MyTrainingListService
															.sendMail(topicid)

															.then(
																	function(
																			response) {

																		if (response.errorCode == 1) {
																			$rootScope
																					.showNotification(
																							""
																									+ "Mail has been sent  successfully!",
																							true);
																			self
																					.fetchMyTrainingList();
																		}

																	},
																	function(
																			errResponse) {
																		$rootScope
																				.showNotification(
																						""
																								+ "Mail sending failed!!!",
																						false);

																		self
																				.fetchMyTrainingList();

																	});

												}, function() {
												});

							}

							/* Sending Mails for Attendees */

							self.load();

						} ]);