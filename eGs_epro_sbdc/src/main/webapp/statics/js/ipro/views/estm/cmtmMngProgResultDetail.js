/**
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";

	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmProgResultSearch(); 
	};
	
	pageObj.estmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngProgList.do"); 
	};
	
	pageObj.estmProgResultSearch = function() {
		
		$("input[name='P_SEARCH_CHECK_YN']").val("Y");
		
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgResultDetail.do"); 
	};
	
	/**
	 * 평가위원이 평가대상을 평가한 점수 확인 팝업
	 */
	detailInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmEstmFrm", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmEstmFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	// 합산식 저장
	pageObj.estmSeMngClcRulSave = function(){
		
		var CLC_RUL = "";
		$(".sansool").each(function (i) {
			var str = "";
			if($(this).attr("name") == "ESTM_PROCD_SEQ" ) {
				str = "ESTM_PROCD_SEQ" + $(this).val();
			}else {
				str = $(this).val();
			}
			
			if(i== 0) {
				CLC_RUL += str; 
			}else {
				CLC_RUL += "," +str;
			}
			
		});
		
		console.log("CLC_RUL  :: " + CLC_RUL);
		
		$("input[name='P_CLC_RUL']").val(CLC_RUL);
		
		FwkCmmnUtil.submitForm("detailFrm" , "/estm/estmCmtmMngMstClcRulSave.do");
	};
	
	//산술추가로직
	pageObj.addSanSoolStrLogic = function(){
		var sansoolArray = new Array(1);
		var procdSeqArray = new Array(1);
		var sansoolLength = 0;
		$("input[name='SANSOOL_STR']").each(function(i) {
			sansoolArray[i] = $(this).val();
			sansoolLength++;
		});
		
		var procdSeqLength = 0;
		$("select[name='ESTM_PROCD_SEQ']").each(function(i) {
			procdSeqArray[i] = $(this).val();
			procdSeqLength++;
		});
		
		
		
		var strHtml = $("#sansool").html();
		
		if( (Number(sansoolLength)+Number(procdSeqLength)+Number(1)) % 6 == 0) {
			strHtml +="<br><br>";
		}
		
		strHtml  += "<input type=\"text\" name=\"SANSOOL_STR\" class=\"component-input sansool\" style=\"width:100px; float:left;\" >";
		strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
		$("#sansool").html(strHtml);
		
		for(var i = 0; i < sansoolLength; i++) {
			$("#sansool input[name='SANSOOL_STR']").eq(i).val(sansoolArray[i]);
		}
		
		for(var i = 0; i < procdSeqLength; i++) {
			$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(procdSeqArray[i]);
		}
	};
	
	//절차추가로직
	pageObj.addProcdLogic = function(){

		var sansoolArray = new Array(1);
		var procdSeqArray = new Array(1);
		var sansoolLength = 0;
		$("input[name='SANSOOL_STR']").each(function(i) {
			sansoolArray[i] = $(this).val();
			sansoolLength++;
		});
		
		var procdSeqLength = 0;
		$("select[name='ESTM_PROCD_SEQ']").each(function(i) {
			procdSeqArray[i] = $(this).val();
			procdSeqLength++;
		});
		
		var strHtml = $("#sansool").html();
		
		
		if( (Number(sansoolLength)+Number(procdSeqLength)+Number(1)) % 6 == 0) {
			strHtml +="<br><br>";
		}
		strHtml  += "<select class=\"component-select  sansool\" name=\"ESTM_PROCD_SEQ\" style=\"float:left; width:100px;\">";
			$("input[name='D_ESTM_PROCD_SEQ']").each(function(i) {
				strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\">" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
				
			});
		strHtml  += "</select>";
		strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
		$("#sansool").html(strHtml);
		
		for(var i = 0; i < sansoolLength; i++) {
			$("#sansool input[name='SANSOOL_STR']").eq(i).val(sansoolArray[i]);
		}
		
		for(var i = 0; i < procdSeqLength; i++) {
			$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(procdSeqArray[i]);
		}
	};
	
	
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		$("#delSanSoolBtn").on("click", function() {
			$("#sansool").html("");
		});
		
		$("#sansoolSaveBtn").on("click", function() {
			pageObj.estmSeMngClcRulSave();
		});

		$("#addSanSoolStrBtn").on("click", function() {
			
			pageObj.addSanSoolStrLogic();
			
		});
		
		$("#addProcdBtn").on("click", function() {
			pageObj.addProcdLogic();
		});
		
		$(document).on("click","a[name='delBtn']", function() {
			$(this).prev().remove();
			$(this).remove();
		});
		
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			pageObj.estmProgResultSearch();
		});
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmProgList();
		});
		
		$('.list-tab li').click(function(){
            var $tabLineIdx = $(this).index();
            $(this).addClass('is-selected');
            $(this).siblings().removeClass('is-selected');
            $('.tab-contents-in').eq($tabLineIdx).show();
            $('.tab-contents-in').eq($tabLineIdx).siblings().hide();
        });

		// 상세검색필터
		$(".filter_sch .filter_btn").on("click",function(e){
			e.preventDefault();
			if($(this).parent().is(".view")){
				$("#filter_schDiv").css("display","none");
				$(this).text("상세검색필터 보기");
				$(this).parent().removeClass("view");
			} else {
				$("#filter_schDiv").css("display","");
				$(this).text("상세검색필터 닫기");
				$(this).parent().addClass("view");
			}
		});
		
		
		
		// 최종점수합계적용여부 선택시 비율 input 활성화 
		$("input[name*='P_SEARCH_ITEM_TOTSUMAT']").on("click", function() {
			if(this.checked) {
				$(this).parent().next().css("display","");
				$(this).next().next().next().css("display","");
			}else {
				$(this).parent().next().css("display","none");
				$(this).next().next().next().css("display","none");
				$(this).parent().next().val("");
			}
		});
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		if($("#resultCode").val() == "Success"){
			$("#resultCode").val("");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmProgResultDetail.do");
		}
		
		if($("input[name='P_SEARCH_CHECK_YN']").val() == "Y" ){
		}else {
			$("input[name='P_SEARCH_ITEM']").prop({'checked':true});
		}
		
		// 엑셀다운로드 공통
		excelDownSetting("detailFrm");
		
		
		// 평가결과 산술식
		var pClcRul = $("#P_CLC_RUL").val();
		var pClcRulSplit = pClcRul.split(",");
		var strHtml = $("#sansool").html();
		
		for( var i in pClcRulSplit) {
			if(pClcRulSplit[i].match("ESTM_PROCD_SEQ") == "ESTM_PROCD_SEQ") {
				var replaceVal = pClcRulSplit[i];
				replaceVal = replaceVal.replace("ESTM_PROCD_SEQ","");
				
				if( (i != 0) && (i % 6 == 0) ) {
					strHtml +="<br><br>";
				}
				strHtml  += "<select class=\"component-select  sansool\" name=\"ESTM_PROCD_SEQ\" style=\"float:left; width:100px;\">";
					$("input[name='D_ESTM_PROCD_SEQ']").each(function(i) {
						
						if (replaceVal == $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val()) {
							strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\" selected >" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
						}else {
							strHtml  += "<option value=\""+ $("input[name='D_ESTM_PROCD_SEQ']").eq(i).val() +"\">" + $("input[name='D_ESTM_PROCD_NM']").eq(i).val() + "</option>";
						}
						
					});
				strHtml  += "</select>";
				strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
				$("#sansool").html(strHtml);
				
				//$("#sansool select[name='ESTM_PROCD_SEQ']").eq(i).val(replaceVal);
				
			}else {
				var replaceVal = pClcRulSplit[i];
				var strHtml = $("#sansool").html();
				
				if( (i != 0) && (i % 6 == 0)) {
					strHtml +="<br><br>";
				}
				
				strHtml  += "<input type=\"text\" name=\"SANSOOL_STR\" class=\"component-input sansool\" style=\"width:100px; float:left;\" value=\"" + replaceVal + "\" >";
				strHtml  += "<a href=\"javascript:\" type=\"button\" class=\"btn-search-close\" name=\"delBtn\"></a>";
				$("#sansool").html(strHtml);
				
				//$("#sansool input[name='SANSOOL_STR']").eq(i).val(replaceVal);
			}
		}
		
		
	});
})();