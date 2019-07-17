'use strict';
/**
 * @Author JOSSINA JOSE.
 */

App.controller('AddInterestedAttendeeController', [
		'$window',
		'$scope',
		'$rootScope',
		'$timeout',
		'$mdDialog',
		'$location',
		'$routeParams',
		'AddAttendeeService',
		function($window, $scope, $rootScope, $timeout, $mdDialog, $location,
				$routeParams, AddAttendeeService) {

			var self = this;

			self.interestedAttendees = '';

			self.notification = "";
			self.showNotification = false;

			self.succmsg = '';
			self.errmsg = '';
			self.showsuccmsg = false;
			self.showerrmsg = false;

			self.showNotification = function(message) {
				self.notification = message;
				self.shownotification = true;

				$timeout(function() {
					self.notification = '';
					self.shownotification = false;
				}, 2000);

			};

			self.load = function() {
				self.fetchAllInterestedAttendees();
			}

			self.fetchAllInterestedAttendees = function() {

				var absUrl = $location.absUrl();
				$scope.order_id = $routeParams.secretKey;
				$scope.topicid = $routeParams.topicid;
				$scope.trainerid = $routeParams.trainerid;
				AddAttendeeService.fetchAllInterestedAttendees($scope.order_id,
						$scope.topicid, $scope.trainerid).then(
						function(response) {

							self.interestedAttendees = response;

							self.interestedAttendees.startDateTime = new Date(
									(self.interestedAttendees.startDateTime));
						}, function(errResponse) {

						});
			}

			self.load();

			self.allowUser = function(topicid, userid) {
				AddAttendeeService.allowUser(topicid, userid).then(
              
				function(response) {
					if (response.errorCode == 1){
//						self.succmsg = "Updated successfully!!!";
//						self.showsuccmsg = true;
//						$timeout(
//								function() {
//									self.succmsg = '';
//									self.showsuccmsg = false;
//								}, 5000);
						$rootScope
						.showNotification(
								"Updated successfully",
								true);
					}
					else{
//						self.succmsg = "Attendee is been added already !!!";
//						self.showsuccmsg = true;
//						$timeout(
//								function() {
//									self.succmsg = '';
//									self.showsuccmsg = false;
//								}, 5000);
						$rootScope
						.showNotification(
								"Attendee is been added already !!!",
								true);
					}
					
					/* $window.location.href = '#/home' */

				}, function(errResponse) {
//					self.errmsg = "Failed to add attendee";
//					self.showerrmsg = true;
//					$timeout(
//							function() {
//								self.errmsg = '';
//								self.showerrmsg = false;
//							}, 5000)
					$rootScope
					.showNotification(
							"Failed to add attendee",
							false);
					
				});

			}

			self.backtohome = function() {

				$window.location = "./#";

			}

		} ]);
