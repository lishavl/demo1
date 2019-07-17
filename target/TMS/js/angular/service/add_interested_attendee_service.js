'use strict';
/**
 * @Author JOSSINA JOSE.
 */

App
		.factory(
				'AddAttendeeService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {
								fetchAllInterestedAttendees : function(userid,
										topicid, trainerid) {
									return $http
											.get(
													'fetchAllInterestedAttendees/'
															+ userid + '/'
															+ topicid + '/'
															+ trainerid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Interested Attendee list  list');
														return $q
																.reject(errResponse);
													});

								},

								allowUser : function(topicid, userid) {

									return $http
											.post(
													'addinterestedattendee/'
															+ topicid + '/'
															+ userid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Interested Attendee list  list');
														return $q
																.reject(errResponse);
													});

								}

							};

						} ]);
