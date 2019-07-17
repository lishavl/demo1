App
		.controller(
				'AdminFeedbackViewController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$mdDialog',
						'AdminFeedbackViewService',
						

						function($window, $scope, $rootScope, $mdDialog,
								AdminFeedbackViewService) {

							var self = this;
							self.showerrors = false;
							self.topicnames = null;
							self.trainernames = null;
							self.attendees = null;
							self.showfeedbacks = false;
							self.showAttendeeList = true;
							self.selecttopic = false;
							self.load = function() {
								self.fetchAllTopicNames();
								console
										.log('Successfully Fetch All Main Categories');
							};

							self.fetchAllTopicNames = function() {
								AdminFeedbackViewService
										.fetchAllTopicNames()
										.then(
												function(response) {
													self.topicnames = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});
							}

							self.getAllTrainers = function() {
								self.attendees=null;
								self.trainernames = '';
								AdminFeedbackViewService
										.fetchAllTrainerNames(self.topicid)
										.then(
												function(response) {
													
													self.trainerid='';
													
													self.trainernames = response;
													
													if(self.trainernames.length==1) {
														self.trainerid=self.trainernames[0].trainer.userId+'';
														self.getAllAttendees();
													}
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}

							self.getAllAttendees = function() {
								AdminFeedbackViewService
										.fetchAllAttendees(self.topicid,
												self.trainerid)
										.then(
												function(response) {
													self.attendees = response;
												},
												function(errResponse) {
													console
															.error('Error while fetching Categories');
												});

							}

							self.viewFeedback = function(id,topic,trainernames,attendeefn,attendeeln) {
								self.topicview =topic;
								self.trainernames = trainernames;
								self.attndeename = attendeefn+''+attendeeln;
								self.attendeeid = id;
								self.showfeedbacks = true;
								self.showAttendeeList = false;
								self.viewfeedbacklist(self.attendeeid,
										self.topicid, self.trainerid);
							}

							self.viewfeedbacklist = function(attendeeid,
									topicid, trainerid) {
								self.feedback = '';

								AdminFeedbackViewService
										.viewfeedbacklist(attendeeid, topicid,
												trainerid)
										.then(
												function(response) {
													self.feedback = response;
													self.feedback.attendeename = response.trainername
															+ ' '
															+ response.lastname;

												},
												function(errResponse) {
													console
															.error('Error while fetching attendee details');
												});

							}
							self.back = function() {
								self.showfeedbacks = false;
								self.showAttendeeList = true;
								/* $window.location.href = '#/home' */
							}

							self.backToHome = function() {
								$window.location = "./#";
							},

							self.load();

						} ]);
