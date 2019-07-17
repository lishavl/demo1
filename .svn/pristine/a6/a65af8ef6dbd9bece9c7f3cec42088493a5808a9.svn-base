'use strict';

App.factory('LoginService', [ '$http', '$q', function($http, $q) {

	return {
		userLogin : function(login) {
			return $http.post('login/', login).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while user login');
				return $q.reject(errResponse);
			});

		},
		requestForChangePassword : function(emailId) {
			return $http.post('requestforresetpassword/', emailId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while user login');
				return $q.reject(errResponse);
			});
			
		},

	};

} ]);
