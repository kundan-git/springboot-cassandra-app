var app = angular.module('queryApp', []);

app.controller('mainCtrl', function($scope, $rootScope,$http,$sce) {
	//$scope.request_url_base="/phil_crime_data/";
	$scope.request_url_base="/";
	
	$scope.hello = "Hello From Angular";
	
	$scope.q1_dc_key="200919045006";
	
	$scope.q2_year="2009";
	$scope.q2_month="2009-05";
	$scope.q2_district="19";
	
	$scope.q3_year="2009";
	$scope.q3_month="2009-05";
	$scope.q3_district="19";
	$scope.q3_ucr_general="800";
	
	$scope.q4_year="2009";
	$scope.q4_district="19";
	$scope.q4_psa="A";
	
	$scope.executeQuery1 = function() {
		 var t1 = new Date().getTime();
		$scope.q1_err_message = "";
		$scope.q1_request_url = $scope.request_url_base+"records/"+$scope.q1_dc_key;
		
		console.log("calling "+$scope.q1_request_url);
		$http.get($scope.q1_request_url).
			then(function(data) {
				console.log(data.data);
				if(data.data.status === "200"){
					$scope.q1_resp = data.data.result;
				}else{
					$scope.q1_resp = undefined;
					$scope.q1_err_message = data.data.message;
					$scope.q1_status = data.status;
				}
				$scope.q1_cass_time = data.data.cassandra_response_time_msec;
				var t2 = new Date(  ).getTime();
				$scope.q1_total_time = t2 - t1;
			},function(result) {
				$scope.q1_err_message = "GET failed for: "+$scope.q1_request_url;
			});
    };
	
	$scope.executeQuery2 = function() {
		 var t1 = new Date().getTime();
		$scope.q2_err_message = "";
		
		//http://localhost:8080/phil_crime_data/records/year/2009/month/2009-05/district/19
		$scope.q2_request_url = $scope.request_url_base+"records/year/"+$scope.q2_year+"/month/"+$scope.q2_month+"/district/"+$scope.q2_district;
		
		console.log("Caling:"+$scope.q2_request_url);
		
		$http.get($scope.q2_request_url).
			then(function(data) {
				console.log(data.data);
				
				if(data.data.status === "200"){
					$scope.q2_resp = data.data.result;
				}else{
					$scope.q2_resp = undefined;
					$scope.q2_err_message = data.data.message;
					$scope.q2_status = data.status;
				}
				$scope.q2_cass_time = data.data.cassandra_response_time_msec;
				var t2 = new Date(  ).getTime();
				$scope.q2_total_time = t2 - t1;
			},function(result) {
				$scope.q2_err_message = "GET failed for: "+$scope.q2_request_url;
			});

    };
	$scope.executeQuery3 = function() {
		 var t1 = new Date().getTime();
		$scope.q3_err_message = "";
		
		//http://localhost:8080/phil_crime_data/records/year/2009/month/2009-05/district/19/ucr_general/800
		$scope.q3_request_url = $scope.request_url_base+"records/year/"+$scope.q3_year+"/month/"+$scope.q3_month+"/district/"+$scope.q3_district+"/ucr_general/"+$scope.q3_ucr_general;
		console.log("Caling:"+$scope.q3_request_url);
		
		$http.get($scope.q3_request_url).
			then(function(data) {
				console.log(data.data);
				
				if(data.data.status === "200"){
					$scope.q3_resp = data.data.result;
				}else{
					$scope.q3_resp = undefined;
					$scope.q3_err_message = data.data.message;
					$scope.q3_status = data.status;
				}
				$scope.q3_cass_time = data.data.cassandra_response_time_msec;
				var t2 = new Date(  ).getTime();
				$scope.q3_total_time = t2 - t1;
			},function(result) {
				$scope.q3_err_message = "GET failed for: "+$scope.q3_request_url;
			});
    };
	
	$scope.executeQuery4 = function() {
		 var t1 = new Date().getTime();
		$scope.q4_err_message = "";
		//http://localhost:8080/phil_crime_data/records/year/2009/district/19/psa/A
		 
		$scope.q4_request_url = $scope.request_url_base+"records/year/"+$scope.q4_year+"/district/"+$scope.q4_district+"/psa/"+$scope.q4_psa;
		console.log("Caling:"+$scope.q4_request_url);
		
		$http.get($scope.q4_request_url).
			then(function(data) {
				console.log(data.data);
				
				if(data.data.status === "200"){
					$scope.q4_resp = data.data.result;
				}else{
					$scope.q4_resp = undefined;
					$scope.q4_err_message = data.data.message;
					$scope.q4_status = data.status;
				}
				$scope.q4_cass_time = data.data.cassandra_response_time_msec;
				var t2 = new Date(  ).getTime();
				$scope.q4_total_time = t2 - t1;
			},function(result) {
				$scope.q4_err_message = "GET failed for: "+$scope.q4_request_url;
			});

    };
	
	$scope.executeQuery1();
	$scope.executeQuery2();
	$scope.executeQuery3();
	$scope.executeQuery4();
});
