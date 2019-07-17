'use strict';

App
		.controller(
				'ForumController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$timeout',
						'$mdDialog',
						'ForomService',
						function($window, $scope, $rootScope, $timeout,
								$mdDialog, ForomService) {
							var self = this;
							self.categoryview = true;
							self.threadview = false;
							self.newthreadview = false;
							self.editview = false;
							self.threadsearchview=false;
							self.showerrors = false;
							self.threadandreplaylistview = false;
							self.role='';

							self.forum = {
								id : null,
								subject : '',
								description : '',
								categoryid : '',
								file : '',
								parentid : null,
								postedon : null

							}
							self.load = function() {
								self.getUserid();
								if ($rootScope.forumThreadId != null) {
									self.fetchdetailsfordashboard($rootScope.forumThreadId);
								} else {
									self.fetchAllCategories();
								}
							};
							self.getUserid = function(){
								ForomService
								.getUserId()
								.then(
										function(response) {
											self.roleid = response;
										},
										function(errResponse) {
											console
													.error('Error while fetching forum search details');
										});
							}
							self.threadsearch = function(){
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = false;
								self.editview = false;
								self.categoryname = '';
								self.threadsearchview=true;
								self.threadandreplaylistview = false;
								ForomService
								.forumsearch(self.searchtext)
								.then(
										function(response) {
											self.searchresult = response;
											angular
											.forEach(
													self.searchresult,
													function(
															value,
															index) {
														value.postbydetails.postedon = new Date(
																(value.postbydetails.postedon));
													});
										},
										function(errResponse) {
											console
													.error('Error while fetching forum search details');
										});
								self.searchtext = null;
								self.searchresult =null;
							}
                            self.fetchdetailsfordashboard=function(forumThreadId){
                            	
                            	ForomService
								.fetchdetailsfordashboard(forumThreadId)
								.then(
										function(response) {
											self.threaddetails = response;
											self.subject = response.subject;
											self.editcategoryid=response.categoryid;
											self.categoryid = response.categoryid;
											self.categoryname = response.categoryname;
											self.parentid = $rootScope.forumThreadId;
											self.viewthreadandreplaylist(self.parentid, self.subject, self.editcategoryid,self.categoryname);
											$rootScope.forumThreadId = null;
											
										},
										function(errResponse) {
											console
													.error('Error while fetching thread details');
										});
                            	
                            }
                        	self.viewthreadandreplaylist = function(parentid,
									subject, categoryid,categoryname) {
                        		self.searchtext = '';
                        		self.categoryname = categoryname;
								self.parentid = parentid;
								self.editcategoryid = categoryid;
								self.subject = subject;
								self.editview = false;
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = false;
								self.threadandreplaylistview = true;
								self.threadsearchview=false;
								self.setViews(self.parentid,
										self.editcategoryid);
								self.fetchAllThreadAndReplyList();

							}
							self.openCategoryList = function() {
								self.searchtext = '';
								self.categoryview = true;
								self.threadview = false;
								self.newthreadview = false;
								self.editview = false;
								self.threadandreplaylistview = false;
								self.threadsearchview=false;
								self.fetchAllCategories();
							}
							self.viewthreadList = function() {
								self.searchtext = '';
								self.reset();
								self.categoryview = false;
								self.threadview = true;
								self.newthreadview = false;
								self.editview = false;
								self.threadandreplaylistview = false;
								self.threadsearchview=false;
								self.fetchThread();
							}
							self.viewThreadReplyList = function() {
								self.searchtext = '';
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = false;
								self.editview = false;
								self.threadandreplaylistview = true;
								self.threadsearchview=false;
								self.fetchAllThreadAndReplyList();
							}

							self.submit = function($event) {

								if (self.forum.id == null
										&& self.parentid == null) {
									if ($scope.forumthread.$valid) {
										self.saveThread(self.forum);
									} else {
										self.showerrors = true;
									}
								} else if (self.forum.id == null
										&& self.parentid != null) {
									if ($scope.forumreply.$valid) {
										self.forum.subject = self.subject;
										self.forum.categoryid = self.editcategoryid;
										self.forum.parentid = self.parentid;
										self.saveorUpdateReply(self.forum);
									} else {
										self.showerrors = true;
									}

								} else if (self.forum.id != null
										&& self.forum.parentid == 0) {
									if ($scope.forumreply.$valid) {
										self.forum.subject = self.subject;
										self.forum.categoryid = self.editcategoryid;
										self.forum.parentid = 0;
										self.saveorUpdateReply(self.forum);
									} else {
										self.showerrors = true;
									}

								} else {
									if ($scope.forumreply.$valid) {
										self.forum.subject = self.subject;
										self.forum.categoryid = self.editcategoryid;
										self.forum.parentid = self.parentid;
										self.saveorUpdateReply(self.forum);
									} else {
										self.showerrors = true;
									}
								}

								$event.preventDefault();
							}
							self.saveorUpdateReply = function(forum) {

								var file = $scope.forumFile;
								var uploadUrl = "saveNewThread/";
								ForomService.saveNewThread(file, self.forum,
										uploadUrl).then(function(response) {
											
									self.viewThreadReplyList();
									self.succmsg = "Save Success";
									self.showsuccmsg = true;
									$timeout(function() {
										self.succmsg = '';
										self.showsuccmsg = false;
									}, 3000)
								}, function(errResponse) {
									self.errmsg = "Save Failed!!!";
									self.showerrmsg = true;
									$timeout(function() {
										self.errmsg = '';
										self.showerrmsg = false;
									}, 3000)
								});
								angular.element("input[type='file']").val(null);

								// self.reset();

							}

							self.saveThread = function(forum) {

								var file = $scope.forumFile;
								var uploadUrl = "saveNewThread/";
								ForomService.saveNewThread(file, self.forum,
										uploadUrl).then(function(response) {
									self.viewthreadList();
									
									self.succmsg = "Save Success";
									self.showsuccmsg = true;
									$timeout(function() {
										self.succmsg = '';
										self.showsuccmsg = false;
									}, 3000)
								}, function(errResponse) {
									self.errmsg = "Save Failed!!!";
									self.showerrmsg = true;
									$timeout(function() {
										self.errmsg = '';
										self.showerrmsg = false;
									}, 3000)
								});
								angular.element("input[type='file']").val(null);

							}
							self.fetchThread = function() {
								ForomService
										.fetchThread(self.categoryid)
										.then(
												function(response) {
													self.threadlist = response;
													angular
															.forEach(
																	self.threadlist,
																	function(
																			value,
																			index) {
																		value.postbydetails.postedon = new Date(
																				(value.postbydetails.postedon));
																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}

							self.viewThreadview = function(id, categoryname,
									postby, postedon) {
								self.searchtext = '';
								self.categoryid = id;
								self.forum.categoryid = id;
								self.categoryname = categoryname;
								self.postby = postby;
								self.postedon = postedon;
								self.categoryview = false;
								self.threadview = true;
								self.newthreadview = false;
								self.threadsearchview=false;
								self.fetchThread();
							}
							self.openNewThread = function() {
								self.searchtext = '';
								$scope.forumFile ='';
								self.reset();
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = true;
								self.threadsearchview=false;

							}
							self.deleteReply = function(id) {
								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {

													ForomService
															.deleteReply(id)
															.then(
																	function(
																			response) {
																		self
																				.fetchAllThreadAndReplyList();
																		self.response = response;
																	},
																	function(
																			errResponse) {
																		console
																				.error('Error while fetching Categories');
																	});

												}, function() {
												});

							}
							self.deleteThread = function(id) {
								
								var confirm = $mdDialog.confirm().title(
								'Delete!!')
								.textContent('Are you sure?')
								.ariaLabel('Lucky day').ok('Yes')
								.cancel('No');
					    	$mdDialog
								.show(confirm)
								.then(
										function() {

											ForomService
											.deleteThread(id)
											.then(
													function(response) {
														self.fetchThread();
														self.response = response;
													},
													function(errResponse) {
														console
																.error('Error while fetching Categories');
													});

										}, function() {
										});
								
								
							
							}

							self.fetchAllCategories = function() {
								ForomService
										.fetchAllCategories()
										.then(
												function(response) {
													self.feedbacklist = response;

													angular
															.forEach(
																	self.feedbacklist,
																	function(
																			value,
																			index) {
																		value.postbydetails.postedon = new Date(
																				(value.postbydetails.postedon));
																	});

													angular
															.forEach(
																	self.feedbacklist,
																	function(
																			value,
																			index) {
																		angular
																				.forEach(
																						self.feedbacklist.subcategory,
																						function(
																								value,
																								index) {
																							value.postbydetails.postedon = new Date(
																									(value.postbydetails.postedon));
																						});

																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}
						
							self.setViews = function() {
								ForomService
										.setViews(self.parentid,
												self.editcategoryid)
										.then(
												function(response) {
													self.status = response;

												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}
							self.likeReply = function(replyid) {
								ForomService
										.setLikes(replyid)
										.then(
												function(response) {
													self.likes = response;
													self.fetchAllThreadAndReplyList();

												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}
							self.dislikeReply= function(replyid) {
								ForomService
								.setDislikes(replyid)
								.then(
										function(response) {
											self.dislike = response;
											self.fetchAllThreadAndReplyList();

										},
										function(errResponse) {
											console
													.error('Error while fetching Categories');
										});
							}
							self.fetchAllThreadAndReplyList = function() {
								self.filename ='';
								ForomService
										.fetchAllThreadAndReplyList(
												self.parentid,
												self.editcategoryid)
										.then(
												function(response) {
													self.replylist = response;
													angular
															.forEach(
																	self.replylist,
																	function(
																			value,
																			index) {
																		value.postedon = new Date(
																				(value.postedon));
																		self.filename= value.id + "_"
																		+ value.filepath
																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching replies');
												});
							}
							self.viewReplayView = function() {
								$scope.forumFile ='';
								angular.element("fileedit[type='file']").val(null);
								self.resetforreply();
								self.editview = true;
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = false;
								self.threadandreplaylistview = false;
								self.threadsearchview=false;
								self.searchtext = '';
							}
							self.editReply = function(replyid) {
								self.editview = true;
								self.categoryview = false;
								self.threadview = false;
								self.newthreadview = false;
								self.threadsearchview=false;
								self.threadandreplaylistview = false;
								ForomService
										.editReply(replyid)
										.then(
												function(response) {
													self.forum = response;
													angular
															.forEach(
																	self.forum,
																	function(
																			value,
																			index) {
																		value.postedon = new Date(
																				(value.postedon));

																	});

												},
												function(errResponse) {
													console
															.error('Error while fetching replies');
												});
							}
							self.reset = function() {
										self.forum.id = null, 
										self.forum.subject = '',
										self.searchtext = '',
										self.forum.description = '',
										self.parentid = null,
										// self.forum.categoryid = '',
										self.forum.file = ''
								self.forum.parentid = null
								// self.forum.postedon=null
							}, self.resetforreply = function() {
								self.forum.description = '';
								self.forum.file = '';
								self.forum.id = null;
								angular.element("input[type='file']").val(null);
							}
							
							self.load();

						} ]);
