'use strict';

App
		.factory(
				'AdminFeedbackViewService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {

								fetchAllTopicNames : function() {
									return $http
											.get('fetchalltopicnamesforadmin/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching main Topic Names....');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllTrainerNames : function(id) {
									return $http
											.get(
													'fetchalltrainernamesforadmin/'
															+ id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Trainer names....');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllAttendees : function(topicid, trinerid) {
									return $http
											.get(
													'fetchallattendeesforadminfeedback/'
															+ topicid + '/'
															+ trinerid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching All Attendees....');
														return $q
																.reject(errResponse);
													});
								},

								viewfeedbacklist : function(attendeeid,
										topicid, trainerid) {
									return $http
											.get(
													'fetchfeedbackforadmin/'
															+ attendeeid + '/'
															+ topicid + '/'
															+ trainerid)
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

							}

						} ]);
