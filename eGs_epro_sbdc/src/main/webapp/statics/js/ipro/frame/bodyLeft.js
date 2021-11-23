
clickMenuMove = function(resourcePattern, resourceName, resourceDesc) {
	
	if(resourceName == "IEP15007"){
		$("#menuMoveFrm input[name='resourceName']").val("IEP1500701");
	}else{
		$("#menuMoveFrm input[name='resourceName']").val(resourceName);		
	}
	
	$("#menuMoveFrm").attr("action", resourcePattern);
	$("#menuMoveFrm").submit();

};

downloadManual = function(){
	FwkCmmnUtil.submitForm("manualFrm", "/comm/userManual.do");
};


$(function(){
	
	/*$('.list-sub-lnb > li > a').click(function(){
		
		if ($(this).parent().children().hasClass('sub-list-in') == true) {
			
			$(this).parent().toggleClass('is-show');
			$(this).parent().siblings().removeClass('is-show');
			
		} else {
			
			$(this).parent().addClass('is-selected');
			$(this).parent().siblings().removeClass('is-selected');
		}
	});*/
	
});
