'use strict';

// ----- CONTROLLER FOR TRAINING CATEGORY MANAGEMENT-----
// ------------------------------------------------------
App
		.controller(
				'TrainingController',
				[
						'$window',
						'$timeout',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'TrainingService',
						function($window, $timeout, $scope, $rootScope,
								$mdDialog, TrainingService) {

							var self = this;
							self.showerrors = false;
							self.showNotification = false;
							self.showMore = false;

							self.cat = 0;
							self.flag = 0;

							self.training = {
								id : 0,
								category : '',
								description : '',
								subCategory : '',
								mainCategory : '',
								parentId : '',
							    istrainingcategory : 1,
							    isforumcategory : 1
							};

							self.showaddwindow = false;
							self.showlistwindow = true;

							self.showmaincategory = false;

							self.maincategory = [];
							self.category = [];

							self.showerrorforcatogory = false;
							self.catagoryexist = false;

							self.load = function() {
								self.fetchAllCategory();
								self.loadCategory();
								console
										.log('Successfully Fetch All Categories');
							};

							self.loadCategory = function() {
								TrainingService
										.fetchCategory()
										.then(
												function(response) {
													self.maincategory = response;
													if (self.maincategory == "") {
														self.flag = 1;
													}

												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});
							}

							self.add = function() {
								self.training.id = 0;
								self.showaddwindow = true;
								self.showlistwindow = false;
							}

							self.deleteCategory = function(id) {
								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													TrainingService
															.deleteCategory(id)
															.then(
																	function(
																			response) {
																		if (response.errorCode == 1) {
																			self
																					.fetchAllCategory();
																			$rootScope
																					.showNotification(
																							"Category Deleted Successfully",
																							true);

																		} else if (response.errorCode == 2) {

																			self
																					.fetchAllCategory();
																			$rootScope
																					.showNotification(
																							"Category Deleted Successfully",
																							true);
																		} else {

																			self
																					.fetchAllCategory();
																			$rootScope
																					.showNotification(
																							"Delete Category Failed",
																							false);
																		}
																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while deleting Category.'
																						+ errResponse);

																		self
																				.fetchAllCategory();
																		$rootScope
																				.showNotification(
																						"Delete Category Failed",
																						false);
																	});
												}, function() {
												});
							};

							self.edit = function(id) {
								console.log('id to be edited:', id);
								TrainingService
										.fetchTrainingById(id)
										.then(
												function(response) {
													self.training = response;
													if (self.training.parentId != 0) {
														self.training.subCategory = 1;
														self.showmaincategory = true;
														self.training.mainCategory = response.parentId
																+ '';
														// alert(self.training.parentId);
													} else {
														self.training.subCategory = 0;
														self.showmaincategory = false;
													}
												},
												function(errResponse) {
													console
															.error('Error while fetching User');
												});
								self.hideandshow();
							};
							self.hideandshow = function() {
								self.showaddwindow = true;
								self.showlistwindow = false;
							}
							self.fetchAllCategory = function() {
								TrainingService
										.fetchAllCategory()
										.then(
												function(response) {
													self.category = response;
													self.loadCategory();
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});
							}

							self.load();

							self.checkCatogory = function() {
								angular
										.forEach(
												self.maincategory,
												function(value, index) {
													if (value.id == self.training.mainCategory) {
														if (value.category == self.training.category) {
															self.showerrorforcatogory = true;
														}

													}
												});
							}

							self.submit = function($event) {

								if ($scope.trainingForm.$valid) {
									if (self.training.id == 0) {
										self.cat = self.training.mainCategory
										if (self.training.mainCategory) {
											angular
													.forEach(
															self.maincategory,
															function(value,
																	index) {
																if (value.id == self.training.mainCategory) {
																	if (value.category == self.training.category) {
																		self.showerrorforcatogory = true;
																	}

																	else {
																		self
																				.addSubCategory(
																						self.training,
																						self.cat);

																		console
																				.log(
																						'Add Category Successfully!!!',
																						self.training);
																	}
																}
															});

										} else {
											self
													.addTrainingCategory(self.training);
											console
													.log(
															'Add Category Successfully!!!',
															self.training);
										}

									} else {
										if (self.training.subCategory == 1) {
											self.cat = self.training.mainCategory
										} else {
											self.cat = 0;
										}
										if (self.cat == 0) {

											self.updateCategory(self.training,
													self.training.id, self.cat);

										}

										else {
											angular
													.forEach(
															self.maincategory,
															function(value,
																	index) {

																if (value.id == self.training.mainCategory) {
																	if (value.category == self.training.category) {
																		self.showerrorforcatogory = true;
																	}

																	else {
																		self
																				.updateCategory(
																						self.training,
																						self.training.id,
																						self.cat);
																		console
																				.log(
																						'Category updated with id ',
																						self.training.id);
																	}
																}
															});

										}
									}
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}
							};

							self.updateCategory = function(training, id,
									category) {
								self.showerrors = false;
								// alert("Category:" + category);
								TrainingService
										.updateCategory(training, id, category)
										.then(
												function(response) {
													// alert(response);
													self.showandhide();
													$rootScope
															.showNotification(
																	"Category Updated Successfully",
																	true);
													self.reset();
												},
												function(errResponse) {
													console
															.error('Error while updating Category.');
													self.showandhide()
													$rootScope
															.showNotification(
																	"Category Updation Failed",
																	false);
													
												});

							};

							self.showandhide = function() {
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.fetchAllCategory();
							}

							self.addTrainingCategory = function(training) {
								self.showerrors = false;
								TrainingService
										.addTrainingCategory(training)
										.then(
												function(response) {
													if (response.errorCode == 1) {
														
														self.reset();
														self.showandhide();
														$rootScope
																.showNotification(
																		"New Category Added Successfully",
																		true);

													} else if (response.errorCode == 2) {
														self.reset();
														self.showaddwindow = true;
														self.showlistwindow = false;
														self.catagoryexist = true;
														
													}
												},
												function(errResponse) {
													self.reset();
													self.showandhide()
													$rootScope
															.showNotification(
																	"Add Category Failed",
																	false);
													
												});

							};

							self.addSubCategory = function(training, cat) {
								self.showerrors = false;
								TrainingService
										.addSubCategory(training, cat)
										.then(
												function(response) {
													if (response.errorCode == 1) {
														self.reset();
														self.showandhide();
														$rootScope
																.showNotification(
																		"Category Added Successfully",
																		true);

													} else if (response.errorCode == 2) {
														self.reset();
														self.showaddwindow = true;
														self.showlistwindow = false;
														self.catagoryexist = true;
														
														
													}
												},
												function(errResponse) {
													self.reset();
													self.showandhide()
													$rootScope
															.showNotification(
																	"Add Category Failed",
																	false);
													
												});

							};
							
							self.showDetails = function(catid){
								
								self.showMore = true;
								self.catdetails = [];
								
								angular
								.forEach(
										self.category,
										function(value,
												index) {
											
											if(value.id == catid ){
												
												self.catdetails.id = value.id;
												self.catdetails.description = value.description;
												self.catdetails.category = value.category;
												self.catdetails.mainCategory = value.mainCategory;
											}
											
											return self.catdetails;
											
										})
								
							}
							
							self.backToList = function() {
								self.showMore = false;
							}

							$scope.showMainCategories = function() {
								if (self.training.subCategory == 1) {
									self.showmaincategory = true;
								} else {
									self.showmaincategory = false;
								}
							}

							self.reset = function() {
								self.showaddwindow = false;
								self.showlistwindow = true;
								self.showNotification = false;
								self.showMore = false;

								self.showmaincategory = false;
								self.showerrors = false;
								
								self.catagoryexist = false;

								self.cat = 0;
								self.flag = 0;

								self.training = {
									category : '',
									description : '',
									subCategory : '',
									mainCategory : '',
									istrainingcategory:1,
								    isforumcategory:1
								};

								$scope.trainingForm.$setPristine(); // reset
								// Form
							};

							self.cancel = function() {

								self.reset();

							}

						} ]);

// -----END OF TRAINING CATEGORY MANAGEMENT CONTROLLER-----
// -------------------------------------------------------

