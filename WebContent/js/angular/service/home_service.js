'use strict';

App.factory('HomeService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				isSessionExist : function(roleId) {
					return $http.get('issessionexist/'+roleId).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				loadDetails : function() {
					return $http.get('loadhomepagedata/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				getTestResult : function(testId) {
					return $http.get('gettestpassedresult/' + testId).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},

				updateStatus : function(notificationid) {
					return $http.post('updatestatus/' + notificationid).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},

			};

		} ]);