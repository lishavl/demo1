'use strict';

App.factory('UpdateProfileService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchUser : function() {
					return $http.get('updateprofile/').then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching user');
						return $q.reject(errResponse);
					});
				},

				updateUser : function(profile, file, uploadUrl) {
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
					}, function(errResponse) {
						console.error('Error while updating user');
						return $q.reject(errResponse);
					});
				},

				fetchSkills : function(text) {
					return $http.get('fetchskillsforuser/' + text).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching Skills');
								return $q.reject(errResponse);
							});
				},

				deleteUserImage : function() {
					return $http.get('deleteprofileimage/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting');
								return $q.reject(errResponse);
							});
				},

			};

		} ]);