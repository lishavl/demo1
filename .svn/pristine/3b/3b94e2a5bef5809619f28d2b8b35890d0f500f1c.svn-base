'use strict';

App.factory('MyTestService', [ '$http', '$q', function($http, $q) {

	return {

		fetchAllTests : function() {
			return $http.get('getallmytests/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		isTestAttended : function(id) {
			return $http.get('istestattended/'+id).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		startTest : function(id) {
			return $http.get('starttest/'+id).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		nextQuestion : function(testId, questionId, answer,remainingSeconds) {
			return $http.get('answerquestionandgetnext/'+testId+'/'+questionId+'/'+answer+'/'+remainingSeconds).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},

		finishTest : function(testId, questionId, answer,remainingSeconds) {
			return $http.get('finishtest/'+testId+'/'+questionId+'/'+answer+'/'+remainingSeconds).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		
		showResult : function(testId) {
			return $http.get('showresult/'+testId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},
		

		closeTest : function(testId, questionId, answer,remainingSeconds) {
			return $http.get('closetest/'+testId+'/'+questionId+'/'+answer+'/'+remainingSeconds).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching users');
				return $q.reject(errResponse);
			});
		},

	};

} ]);
