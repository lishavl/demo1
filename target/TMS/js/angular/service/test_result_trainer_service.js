/**
 * @Author JOSSINA JOSE.
 * @Date 22/07/2016
 */

'use strict';

App.factory('testreportservice', [ '$http', '$q', function($http, $q) {
	
	return {
		
		fetchAllTestNames : function() {
			return $http.get('getallTestNames/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching Test Names');
				return $q.reject(errResponse);
			});
		},
		
		
		fetchAllResults: function(id){
			return $http.get('fetchalltestresult/'+id)
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
		
		fetchAnswerAndQestn: function(testid){
			return $http.get('fetchanswersheet/'+testid)
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
		
		fetchAnswerAndQestnByAttendeeId: function(attendeeid,testid){
			return $http.get('fetchanswersheetbyattendeeid/'+ attendeeid +'/'+testid)
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
	}

};


} ]);