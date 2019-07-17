'use strict';

App
		.factory(
				'MyTrainingListService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {

								fetchMyTrainingList : function() {
									return $http
											.get('fetchmytraininglist/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},
								
								fetchAllCompletedTrainingByDate : function(startDate,endDate) {
									return $http
											.get('searchtopicbydateforcompletedtraining/'+startDate +'/'+ endDate)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},
								fetchAllUpcomingTrainingByDate : function(startDate,endDate) {
									return $http
											.get('searchtopicbydateforupcomingtraining/'+startDate +'/'+ endDate)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},	
								fetchTrainingListForCalendar : function() {
									return $http
											.get(
													'fetchalltrainingprogramsforcalendar/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},

								fetchCompletedTrainingList : function() {
									return $http
											.get('fetchcompletedtraininglist/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllAttendees : function(topicid) {
									return $http
											.get(
													'fetchattendeesbytrainer/'
															+ topicid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														// alert("Fetch
														// Attendees Failed");
														console
																.error('Error while fetching Attendees');
														return $q
																.reject(errResponse);
													});
								},

								addAttendee : function(attendeelist, topicid) {
									return $http
											.post(
													'addattedeesbytrainer/'
															+ attendeelist
															+ '/' + topicid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while adding new attendees');
														return $q
																.reject(errResponse);

													});

								},

								fetchAttendeeDetails : function(id) {
									return $http
											.get(
													'fetchparticipatedattendees/'
															+ id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Attendee Details');
														return $q
																.reject(errResponse);
													});
								},

								fetchAttendee : function(id) {
									return $http
											.get('fetchexpectedattedees/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Attendee Details');
														return $q
																.reject(errResponse);
													});
								},

								scheduleTraining : function(tschedule) {
									return $http
											.post('trainingschedules/',
													tschedule)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while schedule training');
														return $q
																.reject(errResponse);

													});

								},

								fetchMainCategory : function() {
									return $http
											.get('fetchmaincategorybytrainer/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching main categories....');
														return $q
																.reject(errResponse);
													});
								},

								fetchSubCategory : function(maincat) {
									return $http
											.get('fetchsubcategory/' + maincat)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching main categories....');
														return $q
																.reject(errResponse);
													});
								},

								fetchTrainers : function(text) {
									return $http
											.get(
													'fetchtrainersbytrainer/'
															+ text)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching trainers....');
														return $q
																.reject(errResponse);
													});
								},

								fetchAttendees : function(text, deptid) {
									return $http
											.get(
													'fetchattendeebytrainers/'
															+ text + '/'
															+ deptid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching trainers....');
														return $q
																.reject(errResponse);
													});
								},

								viewfeedbacklist : function(attendeeid, topicid) {
									return $http
											.get(
													'feedbacklistfortrainer/'
															+ attendeeid + '/'
															+ topicid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Feedback Details');
														return $q
																.reject(errResponse);
													});
								},

								deleteSchedule : function(id) {
									return $http
											.get('deletetrainingschedule/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting training schedule');
														return $q
																.reject(errResponse);
													});
								},

								fetchTrainingScheduleById : function(id) {
									return $http
											.get(
													'fetchtrainingschedulesbyid/'
															+ id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching training schedules');
														return $q
																.reject(errResponse);
													});
								},

								updateTraining : function(tschedule, id) {
									return $http
											.put(
													'updatetrainingschedules/'
															+ id, tschedule)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while updating training schedule');
														return $q
																.reject(errResponse);
													});
								},

								fetchUserRoles : function(id) {
									return $http
											.get('fetchuserroles/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching user Roles');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllTrainers : function(topicid) {
									return $http
											.get(
													'fetchalltrainersforfeedback/'
															+ topicid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														// alert("Fetch
														// Attendees Failed");
														console
																.error('Error while fetching Attendees');
														return $q
																.reject(errResponse);
													});
								},

								sendMail : function(topicid) {
									return $http
											.post(
													'sendingmailforattendees/'
															+ topicid)

											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while sending mail');
														return $q
																.reject(errResponse);
													});
								},

							};

						} ]);