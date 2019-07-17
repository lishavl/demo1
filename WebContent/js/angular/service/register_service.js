'use strict';

App.factory('RegisterService', [ '$http', '$q', function($http, $q) {

	return {
		
		registerUser : function(register, uploadUrl) {
			

			var fd = new FormData();
			fd.append('data', angular.toJson(register));

			return $http.post(uploadUrl, fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			}).then(function(response) {
				return response.data;
			},function(errResponses) {
				console.error('Error while User Registration');
				return $q.reject(errResponse);
			});

		},
	};

} ]);
