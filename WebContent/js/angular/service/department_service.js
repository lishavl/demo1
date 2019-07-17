'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App.factory('departmentService', [ '$http', '$q', function($http, $q) {

	return {

		saveDepartmentDetails : function(company, uploadUrl) {

			var fd = new FormData();
			fd.append('data', angular.toJson(company));

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
		fetchDepartmentDetails : function() {
			return $http.get('fetchdepartmentdetails/').then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					});
		},
		
		editDepartment: function(id){
			
			return $http.get('fetchforedit/'+id)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while unblocking user');
								return $q.reject(errResponse);
							}
					);
	   },
	   
	   fetchAttendeesByDepartment: function(deprtid){
			return $http.get('fetchattendeesbydeptid/'+deprtid)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while unblocking user');
								return $q.reject(errResponse);
							}
					);
	   },
	   
		deleteDepartment: function(id){
			return $http.delete('deletedepartment/'+id)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while deleting user');
								return $q.reject(errResponse);
							}
					);
	},
	}
} ]);