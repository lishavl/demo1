'use strict';

App
		.factory(
				'TrainingService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {

								fetchCategory : function() {
									return $http
											.get('fetchcategory/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching categories....');
														return $q
																.reject(errResponse);
													});
								},

								addTrainingCategory : function(training) {

									return $http
											.post('addtrainingcategory/',
													training)
											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {

														console
																.error('Error while adding new category');
														return $q
																.reject(errResponse);

													});

								},

								addSubCategory : function(training, cat) {
									return $http
											.post('addsubcategory/' + cat,
													training)
											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {

														console
																.error('Error while adding new category');
														return $q
																.reject(errResponse);

													});

								},

								fetchAllCategory : function() {
									return $http
											.get('fetchtrainingcategories/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching categories');
														return $q
																.reject(errResponse);
													});
								},

								fetchTrainingById : function(id) {
									return $http
											.get('fetchsinglecategory/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching training');
														return $q
																.reject(errResponse);
													});
								},

								fetchMainCategoryById : function(id) {
									return $http
											.get('fetchmaincategorybyid/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Main Category');
														return $q
																.reject(errResponse);
													});
								},

								updateCategory : function(training, id,
										category) {
									return $http
											.put(
													'updatecategory/' + id
															+ '/' + category,
													training)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while updating category');
														return $q
																.reject(errResponse);
													});
								},

								deleteCategory : function(id) {
									return $http
											.get('deletecategory/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting category');
														return $q
																.reject(errResponse);
													});
								},

							}

						} ]);
