(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("menuMoveFrm", url);  
	};
	pageObj.codeMngeReg = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/sytm/codeMngeList.do");
	};
	
	pageObj.plusEvent = function() {
		var copyRow = $("#codeList #addRow").children().clone(true);
		
		copyRow.find("#P_DELETE_NO").removeAttr("disabled");
		copyRow.find("#P_CD_ID").removeAttr("disabled");
		copyRow.find("#P_CD_ID").val($("#P_CD_ID1").val());
		copyRow.find("#P_CD_ID").attr("readonly",true);
		copyRow.find("#P_CD_VALUE").removeAttr("disabled");
		copyRow.find("#P_CD_VALUE_NM").removeAttr("disabled");
		copyRow.find("#P_EXTRA1_CN").removeAttr("disabled");
		copyRow.find("#P_EXTRA3_CN").removeAttr("disabled");
		copyRow.find("#P_USE_AT").removeAttr("disabled");
		
		copyRow.css({"display" : ""});
		
		$("#contentRow").append(copyRow);
		copyRow.find("#rowNum").text($("#contentRow tr").length);
	};
	
	pageObj.deleteEvent = function() {
		$("#P_DELETE_CODE").val($("#P_CD_ID1").val());
		$("#menuMoveFrm #P_CD_ID").val($("#P_CD_ID1").val());
		$("#codeList input[name='P_DELETE_NO']").each(function(){
			if(this.checked) {
				$(this).parent().parent().remove();
			}
		});
	};
	
	checkValSet = function(obj) {
		if (obj.checked == true ){
			$($(obj).parent().children().eq(1)).val("Y");
		}else{
			$($(obj).parent().children().eq(1)).val("N");
		}
	};
	
	pageObj.setEventHandler = function() {
		
		$("#plusBtn").on("click", function() {
			pageObj.plusEvent();
		});
		
		$("#deleteBtn").on("click", function() {
			pageObj.deleteEvent();
		});
		
		$("#saveBtn").on("click", function() {
			pageObj.codeMngeReg();
		});
		$("#cancelBtn").on("click", function() {
			movePage('/sytm/codeMngeList.do');
		});
		$("#allCheck").click(function(){
	        if($("#allCheck").prop("checked")){
	            $("input[name=P_DELETE_NO]").prop("checked",true);
	            $("input[name=P_ROW_CHECK]").val("Y");
	        }else{
	            $("input[name=P_DELETE_NO]").prop("checked",false);
	            $("input[name=P_ROW_CHECK]").val("N");
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