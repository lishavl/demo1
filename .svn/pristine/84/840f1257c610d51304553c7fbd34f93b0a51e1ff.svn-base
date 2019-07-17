'use strict';

App
		.factory(
				'ScheduleTrainingService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {
								
								fetchMainCategory : function() {
									return $http
											.get('fetchmaincategory/')
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
											.get('fetchtrainers/' + text)
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
								
								fetchAttendees : function(text) {
									return $http
											.get('fetchattendees/' + text)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching attendees....');
														return $q
																.reject(errResponse);
													});
								},


								scheduleTraining : function(tschedule) {
									return $http.post('trainingschedules', tschedule)
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
								
								
								fetchScheduledTrainings : function() {
									return $http.get('fetchscheduledtrainings/').then(function(response) {
										return response.data;
									}, function(errResponse) {
										console.error('Error while fetching Training schedules');
										return $q.reject(errResponse);
									});
								},
								
								
								fetchTrainingScheduleById : function(id){
									return $http.get('fetchtrainingschedulesbyid/'+id)
											.then(
													function(response){
														return response.data;
													}, 
													function(errResponse){
														console.error('Error while fetching training schedules');
														return $q.reject(errResponse);
													}
											);
							},
							
							deleteSchedule : function(id) {
								return $http
										.get('deleteSchedule/' + id)
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
							
							updateTraining: function(tschedule, id){
									return $http.put('updatetrainingschedules/'+id, tschedule)
											.then(
													function(response){
														return response.data;
													}, 
													function(errResponse){
														console.error('Error while updating training schedule');
														return $q.reject(errResponse);
													}
											);
							},
							
							}
								
								
						} ]);
							