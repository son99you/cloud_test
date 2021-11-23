(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "searchFrm";
	
	setContSelListInfo = function(cntrctNo, cntrctNm, cntrctAmount, cntrctDe, cntrctpdBeginDe, cntrctpdEndDe, entrpsNm, entrpsRprsntvNm, entrpsAdres, cntrctSeNm) {
		
		var btnId = $("#btnId").val();
		
		if(btnId ==""){
			
			if($("#cntrctNo",opener.document)){
				$("#cntrctNo",opener.document).val(cntrctNo);
			};
			if($("#cntrctNoText",opener.document)){
				$("#cntrctNoText",opener.document).text(cntrctNo);
			};
			
			if($("#cntrctNm",opener.document)){
				$("#cntrctNm",opener.document).val(cntrctNm);
			};
			if($("#cntrctNmText",opener.document)){
				$("#cntrctNmText",opener.document).text(cntrctNm);
			};
			
			if($("#cntrctAmount",opener.document)){
				$("#cntrctAmount",opener.document).val(cntrctAmount);
			};
			if($("#cntrctAmountText",opener.document)){
				$("#cntrctAmountText",opener.document).text(cntrctAmount);
			};
			
			if($("#cntrctDe",opener.document)){
				$("#cntrctDe",opener.document).val(cntrctDe);
			};
			if($("#cntrctDeText",opener.document)){
				$("#cntrctDeText",opener.document).text(cntrctDe);
			};
			
			if($("#cntrctpdBeginDe",opener.document)){
				$("#cntrctpdBeginDe",opener.document).val(cntrctpdBeginDe);
			};
			if($("#cntrctpdBeginDeText",opener.document)){
				$("#cntrctpdBeginDeText",opener.document).text(cntrctpdBeginDe);
			};
			
			if($("#cntrctpdEndDe",opener.document)){
				$("#cntrctpdEndDe",opener.document).val(cntrctpdEndDe);
			};
			if($("#cntrctpdEndDeText",opener.document)){
				$("#cntrctpdEndDeText",opener.document).text(cntrctpdEndDe);
			};
			
			if($("#entrpsNm",opener.document)){
				$("#entrpsNm",opener.document).val(entrpsNm);
			};
			if($("#entrpsNmText",opener.document)){
				$("#entrpsNmText",opener.document).text(entrpsNm);
			};
			
			if($("#entrpsRprsntvNm",opener.document)){
				$("#entrpsRprsntvNm",opener.document).val(entrpsRprsntvNm);
			};
			if($("#entrpsRprsntvNmText",opener.document)){
				$("#entrpsRprsntvNmText",opener.document).text(entrpsRprsntvNm);
			};
			
			if($("#entrpsAdres",opener.document)){
				$("#entrpsAdres",opener.document).val(entrpsAdres);
			};
			if($("#entrpsAdresText",opener.document)){
				$("#entrpsAdresText",opener.document).text(entrpsAdres);
			};
			
			if($("#cntrctSeNm",opener.document)){
				$("#cntrctSeNm",opener.document).val(cntrctSeNm);
			};
			if($("#cntrctSeNmText",opener.document)){
				$("#cntrctSeNmText",opener.document).text(cntrctSeNm);
			};
			
		}
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