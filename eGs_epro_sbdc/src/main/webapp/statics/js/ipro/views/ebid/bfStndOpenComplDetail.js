/**
 * 구매접수 > 사전공고현황 등록
 *
 * <pre>
 * prpo 
 *    |_ bereNotiMngeRegistForm.js
 * 
 * </pre>
 * @date : 2018. 02. 19.
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";

	
	// 목록
	pageObj.bfStndRqstPrcnList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bfStndOpenComplList.do");
	};
	
	
	// 진행이력 팝업
	pageObj.bfStndRqstHistPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "bfanProgPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bfanProgPopup.do';
	        this.method = 'POST';
	        this.target = 'bfanProgPopup';
	    }).trigger("submit");
	};
	
	pageObj.chgContSecd = function(obj){
		var contSecdCd = $(obj).val();
		
		if(contSecdCd==0){
			$("#dlgdPlcNm").text("납품장소");
		}else if(contSecdCd == 1){
			$("#dlgdPlcNm").text("용역현장");
		}else if(contSecdCd == 2){
			$("#dlgdPlcNm").text("공사현장");
		}
	};
	pageObj.chgContMtcd = function(obj){
		var contMtcd = $(obj).val();
		
		if(contMtcd==10000 || contMtcd==10001 || contMtcd==10002){
			$("#contMtcdGbn").css("display","");
		}else {
			$("#contMtcdGbn").css("display","none");
		}
	};
	
	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다.
	 * 2. 저장버튼(#saveBtn) 의 click 이벤트를 binding 한다.
	 *   - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 취소버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록화면으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.bfStndRqstPrcnList();
		});
		
		// 진행이력버튼
		$("#progHistBtn").on("click", function() {
			pageObj.bfStndRqstHistPopup();
		});
		
	};
	
	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		pageObj.chgContSecd($("#contSecd"));
		pageObj.chgContMtcd($("#contMtcd"));
		fileEtcView($("#P_FILE_GRP_NO_ETC").val());
	});
})();