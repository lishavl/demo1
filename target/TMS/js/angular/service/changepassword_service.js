'use strict';

App.factory('ChangePasswordService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				changePassword : function(user) {
					return $http.post('changepassword/',user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching user');
						return $q.reject(errResponse);
					});
				},

			};

		} ]);