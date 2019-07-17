'use strict';

App.factory('QuestionService', [ '$http', '$q', function($http, $q) {
	
	return {
		
		fetchAllQuestions : function(criteriaTestId) {
			return $http.get('getallquestions/'+criteriaTestId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching');
				return $q.reject(errResponse);
			});
		},
		
		fetchAllTests : function() {
			return $http.get('fetchalltestnames/').then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching');
				return $q.reject(errResponse);
			});
		},
		
		checkQuestionsOver : function(testId) {
			return $http.get('checkquestionsover/'+testId).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching');
				return $q.reject(errResponse);
			});
		},
		
		saveOrUpdateQuestion: function(question) {
				return $http.put('savequestion/', question)
						.then(
								function(response){
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating');
									return $q.reject(errResponse);
								}
						);
		},
		
		fetchQuestionById: function(id){
			return $http.get('fetchquestion/'+id)
					.then(
							function(response){
								return response.data;
							},
							function(errResponse){
								console.error('Error while fetching');
								return $q.reject(errResponse);
							}
					);
		},
		
		submitTest: function(testId){
			return $http.get('submittest/'+testId)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while submiting');
						return $q.reject(errResponse);
					}
			);
		},
		
		deleteQuestion: function(id) {
			return $http.delete('deletequestion/'+id)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while deleting');
								return $q.reject(errResponse);
							}
					);
	}

};


} ]);