'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App.factory('companyService', [ '$http', '$q', function($http, $q) {

	return {

		saveCompanyProfile : function(file, profile, uploadUrl) {

			var fd = new FormData();

			fd.append('file', file);
			fd.append('data', angular.toJson(profile));

			return $http.post(uploadUrl, fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			}).then(function(response) {
				return response.data;
			}, function(errResponses) {
				console.error('Error while Save New Material');
				return $q.reject(errResponse);
			})
		},
		
		fetchCompanyDetails : function() {
			return $http.get('fetchcompanydetails/').then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					});
		},
	}
} ]);