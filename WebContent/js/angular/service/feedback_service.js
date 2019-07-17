'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App.factory('feedbackService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllFeedBackList : function() {
					return $http.get('fetchAllFeedBackList/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},

				fetchTrainerName : function(id) {
					return $http.get('fetchTrainerName/' + id).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching user');
								return $q.reject(errResponse);
							});
				},

				viewFeedback : function(topicid, trainerid) {
					return $http.get(
							'viewfeedback/' + topicid + '/' + trainerid).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching user');
								return $q.reject(errResponse);
							});
				},
				updateFeedback : function(uploadUrl, feedback) {
					var fd = new FormData();
					fd.append('data', angular.toJson(feedback));

					return $http.post(uploadUrl, fd, {
						transformRequest : angular.identity,
						headers : {
							'Content-Type' : undefined
						}
					}).then(function(response) {
						return response.data;
					}, function(errResponses) {
						console.error('Error while User Registration');
						return $q.reject(errResponse);

					});
				},

			}
		} ]);