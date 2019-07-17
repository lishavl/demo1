'use strict';
/**
 * @Author JOSSINA JOSE.
 */

App
		.factory(
				'UpcomingTrngSrvs',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {
								fetchAllTrainingList : function() {
									return $http
											.get(
													'fetchAllUpcomingTrainingList/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching upcoming training list');
														return $q
																.reject(errResponse);
													});
								},
								fetchAllCompletedTrainingList:function() {
									return $http
									.get(
											'fetchAllCompleted/')
									.then(
											function(response) {
												return response.data;
											},
											function(errResponse) {
												console
														.error('Error while fetching upcoming training list');
												return $q
														.reject(errResponse);
											});
						},
									
								
								fetchAttendeeTestResult : function() {
									return $http
											.get(
													'fetchAttendeeTestResult/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching upcoming training list');
														return $q
																.reject(errResponse);
													});
								},

								sendMail : function( topicid
										) {
									return $http
											.post(
													'showrequestfortraining/'+ topicid )
															
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
