'use strict';

App.factory('UserService', [ '$http', '$q', function($http, $q) {

	return {

		fetchAllUsers : function() {
			return $http.get('user/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		
		
		fetchSkills : function(text) {
			return $http.get('fetchuserskills/' + text).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching Skills');
				return $q.reject(errResponse);
			});
		},
		
		BlockUserById: function(id,message){
			
				return $http.get('blockuser/'+id+'/'+message)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while blocking user');
									return $q.reject(errResponse);
								}
						);
		},
		unBlockUser: function(id){
			
			return $http.get('unblockuser/'+id)
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
		
		fetchUserById: function(id){
			return $http.get('fetchuser/'+id)
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
	updateUser: function(file, user, uploadUrl){
		
		var fd = new FormData();
		fd.append('file', file);
		fd.append('data', angular.toJson(user));
		
		
		return $http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(response) {
			return response.data;
		},function(errResponses) {
			console.error('Error while Update User');
			return $q.reject(errResponse);
		});
	},
	
	saveUser : function(file, user, uploadUrl) {

		var fd = new FormData();
		fd.append('file', file);
		fd.append('data', angular.toJson(user));

		return $http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(response) {
			return response.data;
		},function(errResponses) {
			console.error('Error while Save New User');
			return $q.reject(errResponse);
		});
	},
	
		deleteUser: function(id){
			return $http.delete('deleteuser/'+id)
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
	
deleteUserImage: function(id){
		
		return $http.get('deleteuserimage/'+id)
		.then(
				function(response){
					return response.data;
				}, 
				function(errResponse){
					console.error('Error while deleting');
					return $q.reject(errResponse);
				}
		);
	},
	

};


} ]);