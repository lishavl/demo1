'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App.controller('FeedbackController', [
		'$window',
		'$scope',
		'$log',
		'$rootScope',
		'$timeout',
		'feedbackService',
		function($window, $scope, $log, $rootScope, $timeout, feedbackService) {

			var self = this;
			self.feedbacklist = null;

			self.showfeedbackwindow = false;
			self.showlistwindow = true;
			self.showfeedbacks=false;
			self.feedbackview = true;
			self.viewbutton = false;

			self.showerrors = false;

			self.submittbutton = true;
			self.backbutton = false;

			self.succmsg = '';
			self.errmsg = '';
			self.showsuccmsg = false;
			self.showerrmsg = false;

			self.feedback = {
				id : '',
				topicid : '',
				trainerid : '',
				topicname : '',
				trainername : '',
				lastname : '',
				report :'',
				data : '',
				comment : '',
				followup : '',
				demo : '',
				status : '',
				count : ''
			};

			self.add = function() {
				self.showfeedbacks=true;
				self.showfeedbackwindow = false;
				self.showlistwindow = false;
			}
			self.edit = function() {
				self.showfeedbacks=false;
				self.showfeedbackwindow = true;
				self.showlistwindow = false;
			}
			self.showAndHide = function() {
				self.showfeedbackwindow = false;
				self.showlistwindow = true;
				self.showfeedbacks=false;
				self.fetchAllFeedbackList();

			}
			self.viewButton = function() {
				self.submittbutton = true;
				self.backbutton = false;

			}
			self.hideButton = function() {
				self.submittbutton = false;
				self.backbutton = true;
			}

			self.notification = "";
			self.shownotification = false;
			self.showNotification = function(message) {
				self.notification = message;
				self.shownotification = true;

				$timeout(function() {
					self.notification = '';
					self.shownotification = false;
				}, 2000);

			};

			self.load = function() {
				self.showAndHide();
				console.log('List All Users Successfully', self.test);
			};
			self.fetchAllFeedbackList = function() {
				feedbackService.fetchAllFeedBackList().then(function(response) {
					self.feedbacklist = response;
					angular.forEach(self.feedbacklist, function(value, index) {
						value.startDateTime = new Date((value.startDateTime));
					});

				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			self.getSelectedData = function(id) {
				self.edit();
				self.viewButton();
				feedbackService.fetchTrainerName(id).then(function(response) {
					self.feedback = response;
					self.feedback.report=5;
				}, function(errResponse) {
					console.error('Error');
				});

			};

			self.submit = function($event) {

				if ($scope.feedbackform.$valid) {
					self.updateFeedback(self.feedback)
				} else {
					self.showerrors = true;
				}
				$event.preventDefault();
			};

			self.updateFeedback = function(feedback) {
				self.showerrors = false;
				var uploadUrl = "updatefeedback/";
				feedbackService.updateFeedback(uploadUrl, self.feedback).then(
						function(response) {
							if (response.errorCode == 1) {
								self.showAndHide();
								$rootScope
								.showNotification(
										"Updated successfully",
										true);
							} else {
								self.showAndHide();
								$rootScope
								.showNotification(
										"Updation failed!!!",
										false);
							}

						}, function(errResponse) {
							console.error('Error while saving feedback');
						});
			}
			self.getToViewFeedBack = function(topicid, trainerid,topic,firstName,lastName) {
				self.viewtopic = topic;
				self.viewtrainerfirst = firstName;
				self.viewtrainerlast = lastName;
				self.add();
				feedbackService.viewFeedback(topicid, trainerid).then(
						function(response) {
							self.feedback = response;
							self.hideButton();

						}, function(errResponse) {
							console.error('Error');
						});

			}
			self.getToEdit = function(topicid, trainerid) {
				self.edit();
				feedbackService.viewFeedback(topicid, trainerid).then(
						function(response) {
							self.feedback = response;
							self.hideButton();

						}, function(errResponse) {
							console.error('Error');
						});

			}


			self.backToFeedback = function() {
				self.showAndHide();
			}
			self.backToHome = function() {
				$window.location = "./#";
			}

			self.load();
			self.reset = function() {
				self.feedback.report = '';
				self.feedback.data = '';
				self.feedback.comment = '';
				self.feedback.followup = '';
				self.feedback.demo = '';

			}

		} ]);
