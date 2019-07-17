'use strict';

App.factory('PerformanceAnalysisService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				loadAllAttendees : function() {
					return $http.get('getallattendees/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching');
								return $q.reject(errResponse);
							});
				},

				loadPerformanceAnalysis : function(attendeeId) {
					return $http.get('loadperformanceanalysis/' + attendeeId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching');
								return $q.reject(errResponse);
							});
				},
				showResult : function(testId, attendeeId) {
					return $http.get(
							'showattendeetestresult/' + testId + '/'
									+ attendeeId).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					});
				},
				viewFeedback : function(topicId, attendeeId) {
					return $http.get(
							'getattendeefeedbackfromtopic/' + attendeeId + '/'
									+ topicId).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching users');
						return $q.reject(errResponse);
					});
				},
			};

		} ]);