App
		.controller(
				'ScheduleTrainingController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'$filter',
						'ScheduleTrainingService',

						function($window, $scope, $rootScope, $mdDialog,
								$timeout, $filter, ScheduleTrainingService) {

							var self = this;
							self.showerrors = false;
							self.showattendee = false;

							self.showaddwindow = false;
							self.showlistwindow = true;

							self.showaddbutton = true;

							self.startdateerror = false;
							self.dateerror = false;
							self.starttimeerror = false;

							self.enddateerror = false;
							self.endtimeerror = false;

							self.showNotification = false;
							self.err = false;

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

							self.category = [];
							self.subcategory = [];

							$scope.trainers = [];
							$scope.attendees = [];
							self.trainer = {
								user_id : 0,
								firstName : '',
								lastName : '',
							}

							self.showmaincategory = false;
							self.showsubcategory = false;

							self.load = function() {
								self.loadMainCategory();
								self.fetchScheduledTrainings();
								console
										.log('Successfully Fetch All Main Categories');
							};

							self.showAttendees = function() {
								self.showattendee = true;
							}

							self.loadMainCategory = function() {
								ScheduleTrainingService
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

							self.fetchScheduledTrainings = function() {
								ScheduleTrainingService
										.fetchScheduledTrainings()
										.then(
												function(response) {
													self.trainingSchedules = response;

													angular
															.forEach(
																	self.trainingSchedules,
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
															.error('Error while fetching Training Programs');
												});
							}

							self.load();

							$scope.chooseSubCategory = function() {
								self.maincat = self.tschedule.maincategory;

								if (self.maincat == 0) {
									self.showmaincategory = true;
									self.subcategory = null;
								} else {
									self.showmaincategory = false;
									self.fetchSubCategory(self.maincat);

								}
							}

							self.add = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
								self.showaddbutton = true;
								self.showattendee = false;
								self.tschedule.id = 0;

							}

							$scope.showSubCategory = function() {
								if (self.tschedule.subCategory == 0) {
									self.showsubcategory = true;
								} else {
									self.showsubcategory = false;
								}
							}

							self.fetchSubCategory = function(maincat) {
								ScheduleTrainingService
										.fetchSubCategory(maincat)
										.then(
												function(response) {
													self.subcategory = response;
													// alert(subcategory);
												},
												function(errResponse) {
													console
															.error("Error while fetching SubCategories.....!!!!");
												});

							};
							$scope.loadTrainers = function(query) {

								ScheduleTrainingService
										.fetchTrainers(query)
										.then(
												function(response) {

													if (response == ' ') {
														self.err = true;

													}

													else {

														self.trainerdetails = response;
													}

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

							$scope.loadAttendees = function(query) {
								ScheduleTrainingService
										.fetchAttendees(query)
										.then(
												function(response) {
													self.attendees = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching attendee');
												});
								$scope.attender = [];
								angular.forEach(self.attendees, function(value,
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

								if ($scope.trainingForm.$valid) {

									if (self.tschedule.id == 0) {

										if (edate != '' && sdate != '') {

											if (sdate < curDate) {

												self.startdateerror = false;
												self.enddateerror = false;
												self.endtimeerror = false;
												self.starttimeerror = false;

												self.dateerror = true;

											} else if (edate == sdate) {

												if (etime < stime
														|| etime == stime) {

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

												self
														.saveTraining(self.tschedule);
											}

										}
									} else {

										if (edate != '' && sdate != '') {

											if (edate == sdate) {

												if (etime < stime
														|| etime == stime) {

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

												self.updateTraining(
														self.tschedule,
														self.tschedule.id);
											}

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
									// alert(tschedule.trainerlist);
								});

								angular.forEach($scope.attendees, function(
										value, index) {
									tschedule.attendeelist.push(value.user_id);
									// alert(tschedule.attendeelist);
								});

								ScheduleTrainingService
										.scheduleTraining(tschedule)
										.then(

												function(response) {

													if (response.errorCode == 1) {

														$rootScope
																.showNotification(
																		"Schedule Training Successfully",
																		true);
														self.reset();
													} else if (response.errorCode == 2) {

														$rootScope
																.showNotification(
																		"This Training Program is Already Scheduled",
																		false);
													} else {

														$rootScope
																.showNotification(
																		"Schedule New Training Failed",
																		false);
													}
													self
															.fetchScheduledTrainings();
													self.showaddwindow = false;
													self.showlistwindow = true;

												},
												function(errResponse) {

													$rootScope
															.showNotification(
																	"Schedule New Training Failed",
																	false);

													console
															.error("Error while schedule Training.....!!!!");
												})

							}

							self.updateTraining = function(tschedule, id) {
								self.showerrors = false;

								self.tschedule.trainerlist = [];
								self.tschedule.attendeelist = [],

								angular.forEach($scope.trainers, function(
										value, index) {
									tschedule.trainerlist.push(value.user_id);
									// alert(tschedule.trainerlist);
								});

								angular.forEach($scope.attendees, function(
										value, index) {
									tschedule.attendeelist.push(value.user_id);
									// alert(tschedule.attendeelist);
								});

								ScheduleTrainingService
										.updateTraining(tschedule, id)
										.then(
												function(response) {
													$rootScope
															.showNotification(
																	"Update Training Successfully",
																	true);
													self
															.fetchScheduledTrainings();
													self.reset();

												},
												function(errResponse) {

													$rootScope
															.showNotification(
																	"Update Training Schedule Failed",
																	false);

													self
															.fetchScheduledTrainings();
													self.reset();

												});
							};

							self.edit = function(id) {
								self.loadMainCategory();
								self.showaddbutton = false;
								self.showerrors = false;
								self.showattendee = true;
								console.log('id to be edited:', id);
								ScheduleTrainingService
										.fetchTrainingScheduleById(id)
										.then(
												function(response) {
													self.tschedule = response;
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
													ScheduleTrainingService
															.deleteSchedule(id)
															.then(
																	function(
																			response) {
																		if (response.errorCode == 1) {
																			self
																					.fetchScheduledTrainings();

																			$rootScope
																					.showNotification(
																							"Training Schedule Deleted Successfully",
																							true);
																		} else {
																			self
																					.fetchScheduledTrainings();

																			$rootScope
																					.showNotification(
																							"Delete Training Schedule Failed",
																							false);
																		}
																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while deleting Training Schedule.'
																						+ errResponse);
																	});
												}, function() {
												});
							};

							self.reset = function() {

								self.showerrors = false;
								self.showattendee = false;

								self.showaddwindow = false;
								self.showlistwindow = true;

								self.showaddbutton = true;

								self.showmaincategory = false;
								self.showsubcategory = false;
								self.showNotification = false;

								$scope.trainers = [];
								$scope.attendees = [];

								self.tschedule = {
									topic : '',
									maincategory : '',
									categoryName : '',
									subCategory : '',
									subCategoryName : '',
									description : '',
									startDateTime : '',
									endDateTime : '',
								};

							}

							self.cancel = function() {

								$scope.trainers = [];
								$scope.attendees = [];

								self.showerrors = false;
								self.showattendee = false;

								self.showaddwindow = false;
								self.showlistwindow = true;

								self.showaddbutton = true;

								self.startdateerror = false;
								self.dateerror = false;
								self.starttimeerror = false;

								self.enddateerror = false;
								self.endtimeerror = false;
								self.showNotification = false;

								self.tschedule = {
									topic : '',
									maincategory : '',
									categoryName : '',
									subCategory : '',
									subCategoryName : '',
									description : '',
									startDateTime : '',
									endDateTime : '',
								};

							}
						} ]);
