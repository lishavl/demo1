'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App.factory('attendanceService', [ '$http', '$q', function($http, $q) {

	return {

		fetchAllTopicNames : function() {
			return $http.get('fetchAllSessions/').then(function(response) {

				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},

		fetchUsers : function(id) {
			return $http.get('fetchallUsers/' + id).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching user');
				return $q.reject(errResponse);
			});
		},
		
		checkDate : function(trainingid) {
			return $http.get('checkdateforattendance/' +trainingid).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching user');
				return $q.reject(errResponse);
			});
		},


		addAttendance : function(uploadUrl, user) {
			var fd = new FormData();
			fd.append('data', angular.toJson(user));

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