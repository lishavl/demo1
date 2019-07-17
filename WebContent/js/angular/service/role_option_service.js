'use strict';
/**
 * @Author Jinshad P.T.
 * @Date 25/08/2016
 */

App.factory('RoleOptionService', [ '$http', '$q', function($http, $q) {

	return {

		fetchAllOptions : function(roleId) {
			return $http.get('fetchalloptions/' + roleId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching user');
				return $q.reject(errResponse);
			});
		},

		saveUserAccess : function(roleOption) {
			var fd = new FormData();
			fd.append('data', angular.toJson(roleOption));

			return $http.post('saveuseraccess/', fd, {
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