'use strict';

App.factory('UserActivationService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				activateUser : function(secretKey) {
					return $http.get('activateuser/' + secretKey).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching user');
								return $q.reject(errResponse);
							});
				},

			};

		} ]);