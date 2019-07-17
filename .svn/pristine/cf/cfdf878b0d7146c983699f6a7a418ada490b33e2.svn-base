'use strict';

App
		.controller(
				'LoginController',
				[
						'$mdDialog',
						'$window',
						'$scope',
						'$rootScope',
						'$timeout',
						'LoginService',
						'$cookieStore',
						function($mdDialog, $window, $scope, $rootScope,
								$timeout, LoginService, $cookieStore) {

							var self = this;
							self.showloginoption = true;
							self.showerrorforforgotpassword = false;
							self.changerequestsubmitted = false;
							self.forgotPwdErrorMsg = '';
							self.email = '';
							self.showerrors = false;
							self.staysignin = false;
							self.showthrobber = false;
							self.login = {
								email : '',
								password : '',
							};

							$rootScope
									.$on(
											'clearLoginForm',
											function(event) {
												self.login = {
													email : '',
													password : '',
												};
												self.changerequestsubmitted = false;
												self.showloginoption = true;
												self.showerrorforforgotpassword = false;
												self.forgotPwdErrorMsg = '';
												self.email = '';

												self.showerrors = false;
												self.loginfailed = false;
												self.inactive = false;
												self.blocked = false;
												self.err = false;
												self.showloginerrors = false;
												self.showthrobber = false;

												if ($cookieStore
														.get('savedLogin') != null) {
													self.login = $cookieStore
															.get('savedLogin');
													self.staysignin = true;
												}

											});

							self.submit = function($event) {
								if ($scope.loginForm.$valid) {
									self.userLogin(self.login);
									console.log('User Login Successfully!!!',
											self.login);
									$event.preventDefault();
								} else {
									self.showerrors = true;
								}
							};

							self.requestForChangePassword = function($event) {
								if ($scope.forgotPasswordForm.$valid) {
									// self.userLogin(self.login);

									self.showthrobber = true;
									LoginService
											.requestForChangePassword(
													self.email)
											.then(
													function(response) {
														self.showthrobber = false;
														if (response) {
															self.changerequestsubmitted = true;
														} else {
															self.forgotPwdErrorMsg = "This email is not registered";
															self.showerrorforforgotpassword = true;
														}
													},
													function(errResponse) {
														self.forgotPwdErrorMsg = "Error happened when requesting";
														self.showerrorforforgotpassword = true;
														self.showthrobber = false;
													});

								} else {
									self.forgotPwdErrorMsg = "Enter a valid email";
									self.showerrorforforgotpassword = true;
								}
								$event.preventDefault();
							};

							self.userLogin = function(login) {

								self.loginfailed = false;
								self.inactive = false;
								self.blocked = false;
								self.err = false;
								self.showthrobber=true;

								LoginService
										.userLogin(login)
										.then(
												function(response) {
													self.showthrobber=false;
													if (response.errorCode == 1) {
														if (self.staysignin) {
															$cookieStore
																	.put(
																			'savedLogin',
																			login);
														} else {
															$cookieStore
																	.remove('savedLogin');
														}
														$window.location.href = '';

													} else if (response.errorCode == 0) {
														self.loginfailed = true;

													} else if (response.errorCode == 2) {
														self.blocked = true;

													} else {
														self.err = true;
													}

												},
												function(errResponse) {
													self.showthrobber = false;
													$scope.serverErrors = errResponse.data;
													self.err = true;
												});
							};

							$scope.cancel = function() {
								$mdDialog.cancel();
								$window.location = "./";
							};

						} ]);