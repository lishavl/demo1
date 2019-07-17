'use strict';

App.factory('ManageTestService', [ '$http', '$q', function($http, $q) {
	
	return {
		
		fetchAllTests : function() {
			return $http.get('getalltests/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		fetchAllTrainings : function() {
			return $http.get('fetchalltrainings/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		saveOrUpdateTest: function(test) {
				return $http.put('saveonlinetest/', test)
						.then(
								function(response){
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating user');
									return $q.reject(errResponse);
								}
						);
		},
		
		fetchTestById: function(id){
			return $http.get('fetchonlinetest/'+id)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while fetching user');
								return $q.reject(errResponse);
							}
					);
		},
		
		deleteTest: function(id) {
			return $http.delete('deletetest/'+id)
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
	
	publishTest: function(id){
		return $http.get('publishtest/'+id)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							return $q.reject(errResponse);
						}
				);
	},
	
	closeTest: function(id){
		return $http.get('closetest/'+id)
		.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					return $q.reject(errResponse);
				}
		);
	},

};


} ]);