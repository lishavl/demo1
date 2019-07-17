App
		.controller(
				'TrainingMaterialController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'$timeout',
						'TrainingMaterialService',

						function($window, $scope, $rootScope, $mdDialog,
								$timeout, TrainingMaterialService) {

							var self = this;

							self.showerrors = false;

							self.showaddwindow = false;
							self.showlistwindow = false;
							self.showtopiclistwindow = true;

							self.showerrmsg = false;
							self.showsuccmsg = false;

							self.showfileerror = false;

							self.showtitleerror = false;

							self.material = {
								material_id : 0,
								topic : '',
								description : '',
								reference : '',
							}

							self.materials = {
								filepath : '',
								id : 0,
								extn : '',
								reference : '',
								description : '',
							}

							self.topics = [];

							self.load = function() {
								self.loadTopics();
								console
										.log('Successfully Fetch All Scheduled Training Topics');

							};

							self.loadTopics = function() {
								TrainingMaterialService
										.fetchTrainingTopics()
										.then(
												function(response) {
													self.topics = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching training topics');
												});
							}

							self.loadMaterials = function(topic) {

								TrainingMaterialService
										.fetchTrainingMaterials(topic)
										.then(
												function(response) {
													self.materials = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching training materials');
												});

							}

							self.load();

							self.submit = function($event) {

								if ($scope.addTrainingMaterialForm.$valid) {

									if (self.material.material_id == 0) {
										self.saveMaterials(self.material);
										console
												.log("Save New Training Material");
									} else {

										self.updateMaterials(self.material,
												self.material.material_id);
										console
												.log("Update Exsisting Training Material");

									}
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}
							}

							self.updateMaterials = function(material,
									materialid) {

								self.topic = self.material.topic;
								self.showerrors = false;
								self.material.material_id = materialid;

								var file = $scope.myFile;

								console.log('file is ');
								console.dir(file);

								var uploadUrl = "updatetrainingmaterial/";

								TrainingMaterialService
										.updateMaterial(file, self.material,
												uploadUrl)
										.then(
												function(response) {
													self.showaddwindow = false;
													self.showlistwindow = true;
													self.showtopiclistwindow = false;
													self.material.topic = self.topic;
													self
															.loadMaterials(self.material.topic);

													$rootScope
															.showNotification(
																	"Material Updated Successfully",
																	true);
												},
												function(errResponse) {
													seself.showaddwindow = false;
													self.showlistwindow = true;
													self.showtopiclistwindow = false;

													self.material.topic = self.topic;
													self
															.loadMaterials(self.material.topic);

													$rootScope
															.showNotification(
																	"Material Updatation Failed",
																	false);
												});

								self.reset();

							};

							self.viewMaterial = function(topic) {

								self.showaddwindow = false;
								self.showlistwindow = true;
								self.showtopiclistwindow = false;
								self.material.topic = topic;
								self.loadMaterials(topic);
							}

							self.saveMaterials = function(material) {
								
							 self.topic = 	self.material.topic;

								self.showerrors = false;

								var file = $scope.myFile;

								if (file == null) {
									self.showfileerror = true;
								}

								else {

									console.log('file is ');
									console.dir(file);

									var uploadUrl = "saveTrainingMaterial/";

									TrainingMaterialService
											.saveMaterial(file, self.material,
													uploadUrl)
											.then(
													function(response) {

														self.showaddwindow = false;
														self.showlistwindow = true;
														self.showtopiclistwindow = false;
														
														self.material.topic = self.topic ;

														$rootScope
																.showNotification(
																		"Material Added Successfully",
																		true);
														
														self.loadMaterials(self.material.topic);

													},
													function(errResponse) {
														$rootScope
																.showNotification(
																		"Add Material Failed",
																		false);
													});

									self.reset();

								}

							};

							self.add = function() {

								self.showaddwindow = true;
								self.showlistwindow = false;
								self.showtopiclistwindow = false;
								self.material.material_id = 0

							}

							self.edit = function(materialid) {
								self.showerrors = false;

								TrainingMaterialService
										.fetchMaterialById(materialid)
										.then(
												function(response) {
													self.material = response[0];
												},
												function(errResponse) {
													console
															.error('Error while fetching Training Materials');
												});
								self.add();
							}

							self.deleteMaterial = function(materialid) {

								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													TrainingMaterialService
															.deleteMaterial(
																	materialid)
															.then(
																	function(
																			response) {

																		if (response.errorCode == 1) {

																			// self.showNotification("Training
																			// Material
																			// Deleted");
																			self
																					.loadMaterials(self.material.topic);

																			$rootScope
																					.showNotification(
																							"Material Deleted Successfully",
																							true);
																		}
																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while deleting training material.'
																						+ errResponse);
																		$rootScope
																				.showNotification(
																						"Material Deletion Failed",
																						false);
																	});
												}, function() {
												});

							}

							self.reset = function() {

								angular.element("input[type='file']").val(null)

								self.showerrors = false;

								self.showerrmsg = false;
								self.showsuccmsg = false;

								self.showfileerror = false;
								self.material = {
									topic : '',
									description : '',
									reference : ''
								}

							}

							self.cancel = function() {
								self.showerrors = false;

								self.showaddwindow = false;
								self.showlistwindow = true;
								self.showtopiclistwindow = false;

								self.showfileerror = false;

								self.showtitleerror = false;

							}

							self.back = function() {

								self.showaddwindow = false;
								self.showlistwindow = false;
								self.showtopiclistwindow = true;

								self.showfileerror = false;
								self.showtitleerror = false;

								self.material = {
									material_id : 0,
									topic : '',
									description : '',
									reference : ''
								}

							}

						} ]);