'use strict';

App.controller('UserActivationController', [
		'$scope',
		'$rootScope',
		'$window',
		'$mdDialog',
		'$routeParams',
		'UserActivationService',
		function($scope, $rootScope, $window, $mdDialog, $routeParams,
				UserActivationService) {

			var self = this;
			self.activationSuccess = false;

			self.load = function(user) {

				var secretKey = $routeParams.secretKey
						.replace('secretKey=', '');

				UserActivationService.activateUser(secretKey).then(
						function(response) {
							if (response) {
								self.activationSuccess = true;
							}

							$mdDialog.show({
								clickOutsideToClose : false,
								scope : $rootScope,
								preserveScope : true,
								contentElement : '#accountActivatedDialoge',
								controller : function HomeController($scope,
										$mdDialog) {
									$scope.closeDialog = function() {
										$mdDialog.hide();
									}
								}
							});

						}, function(errResponse) {
							console.error('Account Activation Failed.');

						});
			};

			self.closeWindow = function() {
				$mdDialog.hide();
				$window.location = "./#";
			};

			self.doLogin = function() {
				$mdDialog.hide();
				$window.location = "./#";
				$rootScope.openLoginWindow();
			};
			self.load();

		} ]);