'use strict';

App
		.factory(
				'TrainingMaterialService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {

								fetchTrainingTopics : function() {
									return $http
											.get('listallscheduledtrainings/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														
														console
																.error('Error while fetching Training schedules');
														return $q
																.reject(errResponse);
													});
								},

								saveMaterial : function(file, material,
										uploadUrl) {

									var fd = new FormData();
									fd.append('file', file);
									fd.append('data', angular.toJson(material));

									return $http
											.post(
													uploadUrl,
													fd,
													{
														transformRequest : angular.identity,
														headers : {
															'Content-Type' : undefined
														}
													})
											.then(
													function(response) {
														return response.data;
													},
													function(errResponses) {
														console
																.error('Error while Save New Material');
														return $q
																.reject(errResponse);
													});
								},
								
								
								updateMaterial : function(file, material,
										uploadUrl) {

									var fd = new FormData();
									fd.append('file', file);
									fd.append('data', angular.toJson(material));

									return $http
											.post(
													uploadUrl,
													fd,
													{
														transformRequest : angular.identity,
														headers : {
															'Content-Type' : undefined
														}
													})
											.then(
													function(response) {
														return response.data;
													},
													function(errResponses) {
														console
																.error('Error while update  Material');
														return $q
																.reject(errResponse);
													});
								},


								fetchTrainingMaterials : function(topic) {
									return $http
											.get('fetchalltrainingmaterials/'+topic)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														
														console
																.error('Error while fetching Training Materials');
														return $q
																.reject(errResponse);
													});
								},

								fetchMaterialById : function(materialid) {
									return $http
											.get(
													'fetchtrainingmaterialsbyid/'
															+ materialid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching training materials');
														return $q
																.reject(errResponse);
													});
								},
								
								deleteMaterial : function(materialid) {
									return $http
											.get('deletematerial/' + materialid)
											.then(
													function(response) {
													
														return response.data;
													},
													function(errResponse) {
														
														console.error('Error while deleting training materials');
														return $q
																.reject(errResponse);
													});
								},

							}
						} ]);