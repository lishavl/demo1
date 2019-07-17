'use strict';

App.controller('RegisterController', [
		'$scope',
		'$rootScope',
		'$window',
		'$mdDialog',
		'RegisterService',
		function($scope, $rootScope, $window, $mdDialog, RegisterService) {

			var self = this;
			self.showerrors = false;
			self.showsuccesswindow = false;
			self.commonimg = true;
			self.succimg = false;
			self.registerwindow = true;
			self.showpassworderrors = false;
			self.showemailerrors = false;
			self.registrationfailed = false;
			self.showthrobber = false;
			self.isDisabled = false;
			self.register = {
				firstName : '',
				lastName : '',
				email : '',
				password : '',
				confirmPassword : '',
			};

			$rootScope.$on('clearRegisterForm', function(event) {
				self.register = {
					firstName : '',
					lastName : '',
					email : '',
					password : '',
					confirmPassword : '',
				};
				self.showerrors = false;
				self.showsuccesswindow = false;
				self.registerwindow = true;
				self.showpassworderrors = false;
				self.showemailerrors = false;
				self.registrationfailed = false;
				self.showthrobber = false;
				self.isDisabled = false;
			});

			self.submit = function($event) {
				if ($scope.regForm.$valid) {
					self.registerUser(self.register);
					$event.preventDefault();
				} else {
					self.showerrors = true;

				}
			};

			self.registerUser = function(register) {
				
				 var values = self.register.firstName.split(" ");

				 self.register.firstName = values[0];
				 self.register.lastName = values[1];
				    
				if (self.register.password == self.register.confirmPassword) {
					
					self.showthrobber = true;
					self.showerrors = false;
					$scope.isDisabled = true;
					var uploadUrl = "register/";
					RegisterService.registerUser(self.register, uploadUrl)
							.then(function(response) {
								self.showthrobber = false;
								self.isDisabled = false;
								if (response.errorCode == 1) {
									self.showsuccesswindow = true;
									self.registerwindow = false;
									self.reset();
								} else if (response.errorCode == 2) {
									self.showemailerrors = true;
								} else {
									self.registrationfailed = true;
								}

							}, function(errResponse) {
								$scope.serverErrors = errResponse.data;
								self.showthrobber = false;
								self.isDisabled = false;
								self.registrationfailed = true;
								self.reset();
							});
				} else {
					self.showpassworderrors = true;
				}

			};

			$scope.cancel = function() {
				$mdDialog.cancel();
			};

			self.reset = function() {
				self.showerrors = false;
				self.showpassworderrors = false;
				self.showemailerrors = false;
				self.register = {
					firstName : '',
					lastName : '',
					email : '',
					password : '',
					confirmPassword : '',
					skillsets : []
				};

				$scope.regForm.$setPristine(); // reset Form
			};

		} ]);