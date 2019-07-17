'use strict';
/**
 * @Author JOSSINA JOSE.
 * @Date 20/10/2016
 */

App
		.controller(
				'DepartmentController',
				[
						'$window',
						'$scope',
						'$timeout',
						'$rootScope',
						'$mdDialog',
						'$route',
						'departmentService',
						function($window, $scope, $timeout, $rootScope,
								$mdDialog,$route, departmentService) {

							var self = this;
							self.showerrors = false;
							self.departmentlistview=true;
							self.departmenteditview=false;
							self.department = {
								id : null,
								department : '',
							};
							self.load = function() {
								self.fetchDepartmentDetails();
							}
							self.listview =function(){
								self.departmentlistview=true;
								self.departmenteditview=false;	
								self.fetchDepartmentDetails();
							}
							self.departmentview=function(){
								self.departmentlistview=false;
								self.departmenteditview=true;	
							}

							self.submit = function($event) {
								if ($scope.deprtform.$valid) {
									self.saveDepartmentDetails();
								} else {
									self.showerrors = true;
								}
							}

							self.fetchDepartmentDetails = function() {
								departmentService.fetchDepartmentDetails().then(function(response) {
									self.departmentlist = response;
								}, function(errResponse) {
									console.error('Error');
								});
							}

							self.saveDepartmentDetails = function() {
								var uploadUrl = "savedepartmentdetails/";

								departmentService
										.saveDepartmentDetails(self.department,
												uploadUrl)
										.then(
												function(response) {
													$rootScope
															.showNotification(
																	"Department Details Updated Successfully",
																	true);
													self.listview();
													self.reset();
												},
												function(errResponse) {
													$rootScope
															.showNotification(
																	"Department Updatation Failed",
																	false);
												});
								

							}
							self.edit=function(id){
								departmentService.editDepartment(id).then(function(response) {
									self.department = response;
									self.departmentview();
								}, function(errResponse) {
									console.error('Error');
								});
							}
							
							self.deletedepartment = function(id) {

								var confirm = $mdDialog.confirm().title(
										'Delete!!')
										.textContent('Are you sure?')
										.ariaLabel('Lucky day').ok('Yes')
										.cancel('No');
								$mdDialog
										.show(confirm)
										.then(
												function() {
													departmentService
															.deleteDepartment(id)
															.then(
																	function(
																			response) {
																		self.fetchDepartmentDetails();
																		if(response.errorCode==1){
																			$rootScope
																			.showNotification(
																					"Department Deleted Successfully",
																					true);
																		}
																		else{
																			
																			$rootScope
																			.showNotification(
																					"Users assigned therefore cannot delete department",
																					false);
																		}
																	},
																	function(
																			errResponse) {

																		self.fetchDepartmentDetails();

																	});

												}, function() {
												});

							};

							self.back=function(){
								self.reset();
								self.listview();
							}
							self.reset = function(){
								self.department.id =null;
								self.department.department = '';
								self.showerrors ='';
							}
							
							self.load();
						} ]);
