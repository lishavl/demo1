'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 19/07/2016
 */

App
		.controller(
				'CompanyController',
				[
						'$window',
						'$scope',
						'$timeout',
						'$rootScope',
						'$mdDialog',
						'$route',
						'companyService',
						function($window, $scope, $timeout, $rootScope,
								$mdDialog,$route, companyService) {

							var self = this;
							self.showerrors = false;
							self.profile = {
								id : null,
								companyname : '',
								address : '',
								contactperson : '',
								mobile : '',
								phn2 : '',
								email : '',
								website : '',
								logo : '',
							};
							self.load = function() {
								self.fetchCompanyDetails();

							}

							self.submit = function($event) {

								if ($scope.companyform.$valid) {

									self.saveCompanyProfile();
								} else {
									self.showerrors = true;
								}
							}

							self.fetchCompanyDetails = function() {
								
								companyService.fetchCompanyDetails().then(function(response) {
									self.profile = response;
								}, function(errResponse) {
									console.error('Error');
								});
							}

							self.saveCompanyProfile = function(profile) {
								var file = $scope.logo;

								console.log('file is :');
								console.dir(file);

								var uploadUrl = "saveCompanyProfile/";

								companyService
										.saveCompanyProfile(file, self.profile,
												uploadUrl)
										.then(
												function(response) {
													self.succmsg = "Save Success";

													$rootScope
															.showNotification(
																	"Company Details Updated Successfully",
																	true);
													self.fetchCompanyDetails();
													self.reset();
													$window.location.reload();
												},
												function(errResponse) {
													$rootScope
															.showNotification(
																	"Company Updation Failed",
																	false);
												});
								

							}
							self.back=function(){
								self.reset();
								$window.location = "./#";
							}
							self.reset=function(){
								self.profile = {
										id : null,
										companyname : '',
										address : '',
										contactperson : '',
										mobile : '',
										phn2 : '',
										email : '',
										website : '',
										logo : '',
									};
								self.showerrors ='';
							}
							self.load();
						} ]);
