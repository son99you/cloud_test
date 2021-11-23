(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "modiFrm";
	
	function getRecentDate(){
	    var dt = new Date();
	 
	    var recentYear = dt.getFullYear();
	    var recentMonth = dt.getMonth() + 1;
	    var recentDay = dt.getDate();
	 
	    if(recentMonth < 10) recentMonth = "0" + recentMonth;
	    if(recentDay < 10) recentDay = "0" + recentDay;
	 
	    return recentYear + "-" + recentMonth + "-" + recentDay;
	}
	 
	function getPastDate(period){
	    var dt = new Date();
	 
	    dt.setMonth((dt.getMonth() + 1) + period);
	 
	    var year = dt.getFullYear();
	    var month = dt.getMonth();
	    var day = dt.getDate();
	    
	    if(month == 0) month="1";
	 
	    if(month < 10) month = "0" + month;
	    if(day < 10) day = "0" + day;
	 
	    return year + "-" + month + "-" + day;
	}
	
	
	//상세
	pageObj.licenseDetail = function(){
		FwkCmmnUtil.submitForm(defaultFrm, "/sytm/licenseDetail.do");
	};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("listFrm", url);  
	};
	
	
	
	pageObj.setEventHandler = function() {
		
		$("#cnclBtn").on("click", function(){
			/*if(!confirm("취소되었습니다.")){
				return false;
			}
			pageObj.licenseDetail();*/
			if(!confirm("취소 하시겠습니까?")){
				return false;
			}else {
				pageObj.licenseDetail();
			}
		});
		
		$("#saveBtn").on("click", function() {
			
			if(!confirm("저장 하시겠습니까?")){
				return false;
			}else {
				FwkCmmnUtil.submitForm("modiFrm" , "/sytm/licenseUpdt.do");	
			}
//			return false;
		});
		
		$("#P_LICENSE_PROCD_SECD").on("change", function() {
			
			if($("#P_LICENSE_PROCD_SECD").val() == "D") { //테스트
				var stDate = getRecentDate();
				var endDate = getPastDate(3);
				$("#P_LICENSE_ST_DATE").val(stDate);
				$("#P_LICENSE_END_DATE").val(endDate);
				
				
			}else if($("#P_LICENSE_PROCD_SECD").val() == "R"){ //운영
				$("#P_LICENSE_END_DATE").val("2999-12-31");
			}
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
	});
	
})();