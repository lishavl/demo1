/**
 * @Author JOSSINA JOSE.
 * @Date 22/07/2016
 */
'use strict';

App.controller('TestResultTrainerController', [
		'$window',
		'$scope',
		'$log',
		'$rootScope',
		'$mdDialog',
		'testreportservice',
		function($window, $scope, $log, $rootScope,$mdDialog, testreportservice) {

			var self = this;
			self.testresultlist=true;
			self.resultview=false;
			self.testnames = null;
			self.allresults = null;
			
			self.load = function() {
				self.getAllTestNames();
			};
			
			self.viewResult=function(attendeeid,testid,attendeename,testname){
				self.testname =testname;
				self.attendeename =attendeename;
				self.testresultlist=false;
				self.resultview=true;
				self.fetchAnswerAndQestn(attendeeid,testid);
			}
			
			self.fetchAnswerAndQestn=function(attendeeid,testid){
				testreportservice
				.fetchAnswerAndQestnByAttendeeId(attendeeid,testid)
				.then(
						function(response) {
							
							self.answersheet = response;
						},
						function(errResponse) {
							console
									.error('Error while fetching Currencies');
						});
				
			}

			self.getAllTestNames = function() {
				testreportservice.fetchAllTestNames().then(function(response) {
					self.testnames = response;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			
			self.getAllResuls =function(){
				testreportservice.fetchAllResults(self.testid).then(
						function(response) {
							self.allresults = response;
							self.drawChart(self.allresults);
						}, function(errResponse) {
							console.error('Error while fetching Currencies');
						});
				
			}
			
//			------------For auto dropdown------------------------

//			$scope.getAllResuls = function(selectedItem) {
//				testreportservice.fetchAllResults(selectedItem).then(
//						function(response) {
//							self.allresults = response;
//							self.drawChart(self.allresults);
//						}, function(errResponse) {
//							console.error('Error while fetching Currencies');
//						});
//
//			};
			
			

			self.backToHome = function() {
				$window.location.href = '#/home'
			}

			self.drawChart = function(allresults) {

				$scope.data = [ {
					key : "Total Passed",
					y : allresults.nopass

				}, {
					key : "Total Failed",
					y : allresults.nofail
				}

				];

				$scope.options = {
						
						chart : {
							type : 'pieChart',
							height : 350,
							width : 350,
							x : function(d) {
								return d.key;
							},
							y : function(d) {
								return d.y;
							},
							showLabels : false,
							donut: true,
							donutRatio : 0.3,
							color : [ '#109618', '#DC3912'],
							duration : 500,
							labelThreshold : 0.01,
							labelSunbeamLayout : true,
							margin : {
								top : 0,
								right : 0,
								bottom : 0,
								left : 0
							},
							padding : {
								top : 0,
								right : 0,
								bottom : 0,
								left : 0
							},
							showLegend : true,
						}
				};

			};
			self.backtoresultlist = function(){
				
				self.testresultlist=true;
				self.resultview=false;
			}

			self.reset = function() {
				self.user = {};
			},

			self.load();

		} ]);
