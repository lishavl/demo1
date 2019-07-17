'use strict';

App.controller('ResetPasswordController', [
		'$window',
		'$scope',
		'$rootScope',
		'$timeout',
		'$routeParams',
		'ResetPasswordService',
		function($window, $scope, $rootScope, $timeout, $routeParams,
				ResetPasswordService) {

			var self = this;
			self.showerrors = false;
			self.showerrmsg = false;
			self.errormsg = '';
			self.confirmError = false;
			self.showSuccess = false;
			self.user = {
				user_id : 0,
				newPassword : '',
				confirmPassword : ''
			}

			self.load = function(user) {

				var secretKey = $routeParams.secretKey
						.replace('secretKey=', '');

				ResetPasswordService.isValidReset(secretKey).then(
						function(response) {
							if (response == 0) {
								self.showerrmsg = true;
								self.errormsg = 'invalid_key';
							} else {
								self.showSuccess = false;
								self.user.user_id = response;
							}

						}, function(errResponse) {
							console.error('Change Password Failed.');

						});
			};

			self.resetPassword = function() {

				self.showerrors = false;
				self.showerrmsg = false;
				self.confirmError = false;
				self.showSuccess = false;

				if ($scope.resetPasswordForm.$valid) {
					if (self.user.newPassword != self.user.confirmPassword) {
						self.confirmError = true;
						self.showerrors = true;
					} else {
						ResetPasswordService.resetPassword(self.user).then(
								function(response) {
									if (response.errorCode == 1) {
										self.showSuccess = true;
									} else if (response.errorCode == 2) {
										self.showerrmsg = true;
										self.errormsg = 'failed';
									}

								}, function(errResponse) {
									console.error('Change Password Failed.');

								});
					}

				} else {
					self.showerrors = true;
				}

			};

			self.reset = function() {
				self.showerrors = false;
				self.confirmError = false;
				self.user = {

					newPassword : '',
					oldPassword : '',
					confirmPassword : ''
				}

			}
			self.load();

		} ]);