'use strict';

App.factory('ResetPasswordService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				isValidReset : function(secretKey) {
					return $http.get('isvalidresetpassword/'+secretKey).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching user');
						return $q.reject(errResponse);
					});
				},
				
				resetPassword : function(user) {
					return $http.post('resetpassword/',user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching user');
						return $q.reject(errResponse);
					});
				},

			};

		} ]);