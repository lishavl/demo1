'use strict';

App
		.factory(
				'ForomService',
				[
						'$http',
						'$q',
						function($http, $q) {

							return {

								fetchAllCategories : function() {
									return $http
											.get('fetchallcategoriesforforum/')
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

								saveNewThread : function(file, forum, uploadUrl) {
									var fd = new FormData();
									fd.append('file', file);
									fd.append('data', angular.toJson(forum));

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
													})
								},

								fetchThread : function(categoryid) {
									return $http
											.get('fetchthread/' + categoryid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching threads');
														return $q
																.reject(errResponse);
													});
								},
								setLikes : function(replyid) {
									return $http
											.put('likereplies/' + replyid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching threads');
														return $q
																.reject(errResponse);
													});
								},
								setDislikes : function(replyid) {
									return $http
											.put('dislikereplies/' + replyid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching threads');
														return $q
																.reject(errResponse);
													});
								},
								

								fetchAllThreadAndReplyList : function(parentid,
										editcategoryid) {

									return $http
											.get(
													'fetchreplylilst/'
															+ parentid + '/'
															+ editcategoryid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while blocking user');
														return $q
																.reject(errResponse);
													});
								},

								editReply : function(replyid) {
									return $http
											.get('editreply/' + replyid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching user');
														return $q
																.reject(errResponse);
													});
								},

								saveUser : function(file, user, uploadUrl) {

									var fd = new FormData();
									fd.append('file', file);
									fd.append('data', angular.toJson(user));

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
																.error('Error while Save New User');
														return $q
																.reject(errResponse);
													});
								},

								setViews : function(parentid, editcategoryid) {
									return $http
											.put(
													'setviewstatus/' + parentid
															+ '/'
															+ editcategoryid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting user');
														return $q
																.reject(errResponse);
													});
								},
								deleteReply : function(replyid) {
									return $http.delete('deletereply/' + replyid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting user');
														return $q
																.reject(errResponse);
													});
								},
								deleteThread : function(threadid) {
									return $http.delete('deletethread/' + threadid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting user');
														return $q
																.reject(errResponse);
													});
								},
								
								getUserId : function() {
									return $http
											.get('getuserid/')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting user');
														return $q
																.reject(errResponse);
													});
								},
								
								
								fetchdetailsfordashboard:function(forumThreadId) {
									return $http
									.get('fetchdetailsfordashboard/'+ forumThreadId)
									.then(
											function(response) {
												return response.data;
											},
											function(errResponse) {
												console
														.error('Error while deleting user');
												return $q
														.reject(errResponse);
						  					});
						     },
						     
						     forumsearch:function(searchtext){
						    	 return $http
									.get('forumsearch/'+ searchtext)
									.then(
											function(response) {
												return response.data;
											},
											function(errResponse) {
												console
														.error('Error while deleting user');
												return $q
														.reject(errResponse);
						  					});
						    	 
						     }

							};

						} ]);