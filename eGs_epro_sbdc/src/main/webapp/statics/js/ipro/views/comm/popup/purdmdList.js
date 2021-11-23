(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	setPurdmdListInfo = function(cntrctNm, bsnsNm,cntrctMthCd,ctrctSecd,bidScale, rqstPe, bidChrgEmail, rqstPeEmail){
		/**
		 * cntrctNm, bsnsNm, cntrctMthCd, ctrctSecd, bidScale, rqstPe, bidChrgEmail, rqstPeEmail
		 */  
		
		if($("#cntrctNm",opener.document)){
			$("#cntrctNm",opener.document).val(cntrctNm);
		};
		
		if($("#bsnsNm",opener.document)){
			$("#bsnsNm",opener.document).val(bsnsNm);
		};
		
		if($("#cntrctMthCd",opener.document)){
			$("#cntrctMthCd",opener.document).val(cntrctMthCd);
		};
		
		if($("#ctrctSecd",opener.document)){
			$("#ctrctSecd",opener.document).val(ctrctSecd);
		};
		
		if($("#bidScale",opener.document)){
			$("#bidScale",opener.document).val(bidScale);
		};
		if($("#rqstPe",opener.document)){
			$("#rqstPe",opener.document).val(rqstPe);
		};
		
		if($("#bidChrgEmail",opener.document)){
			$("#bidChrgEmail",opener.document).val(bidChrgEmail);
		};
		if($("#rqstPeEmail",opener.document)){
			$("#rqstPeEmail",opener.document).val(rqstPeEmail);
		};
		self.close(); 
	};
	
	pageObj.setEventHandler = function() {
		
		$("#closeBtn").on("click", function() {
			window.close();
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