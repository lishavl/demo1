'use strict';

App
		.controller(
				'ChangePasswordController',
				[
						'$window',
						'$scope',
						'$rootScope',
						'$timeout',
						'ChangePasswordService',
						function($window, $scope, $rootScope, $timeout,
								ChangePasswordService) {

							var self = this;
							self.showerrors = false;
							self.showerrmsg = false;
							self.showsuccmsg = false;
							self.showerrmsg = false;
							self.user = {

								newPassword : '',
								oldPassword : '',
								confirmPassword : ''
							}
							self.succmsg = '';
							self.errmsg = '';
							self.submit = function($event) {
								if ($scope.changePasswordForm.$valid) {
									self.changePassword(self.user);
									console.log('Change Password Success!!!');
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}

							};

							self.changePassword = function(user) {
								self.showerrors = false;

								ChangePasswordService
										.changePassword(user)
										.then(
												function(response) {
													if (response.errorCode == 1) {
														$rootScope
														.showNotification(
																"Password Changed Successfully",
																true);
														self.reset();
													} else if (response.errorCode == 2) {
														$rootScope
														.showNotification(
																"Password Missmatch",
																false);
													} else if (response.errorCode == 3) {
														$rootScope
														.showNotification(
																"Old Password is Wrong",
																false);
													} else {
														$rootScope
														.showNotification(
																"Change Password Failed",
																false);
													}

												},
												function(errResponse) {
													console
															.error('Change Password Failed.');

												});
							};

							self.reset = function() {
								self.showerrors = false;
								self.user = {

									newPassword : '',
									oldPassword : '',
									confirmPassword : ''
								}

							}

						} ]);