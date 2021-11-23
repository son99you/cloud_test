(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";
	
	// 등록
	pageObj.compInfoMngeRegist = function () {
		//FwkCmmnUtil.submitForm(defaultFrm, "/sytm/compInfoMngeRegist.do");
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/sytm/compInfoMngeRegist";

		 FwkCmmnUtil.submitAjax (actionUrl, jsonData
		 	, function(res) {
			 // alert(JSON.stringify(res));
			 $("#viewFrm input[name='P_ENTRPS_REGIST_NO']").val(res.trans.P_ENTRPS_REGIST_NO);
			 FwkCmmnUtil.submitForm("viewFrm", "/sytm/compInfoMngeDetail.do");
		 });
	};

	// 우편번호 팝업
	pageObj.zipListPopup = function() {
		$("#zipPopupFrm").one("submit", function() {
			window.open("", "zipListPopup", "width=750px, height=650px, toolbar=no, status=no, resizeable=no, scrollbars=no, menubar=no, left="+x(740)+",top="+y(600));
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/zipList.do';
			this.method = 'POST';
			this.target = 'zipListPopup';
		}).trigger("submit");
	};
	
	jusoCallBack = function(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#P_ZIP").val(zipNo);
		$("#P_RN_ADRES").val(roadAddrPart1);
		$("#P_DETAIL_ADRES").val(addrDetail);
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		// 저장 버튼
		$("#saveBtn").on("click", function() {
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "저장"))){
				return false;
			}
			
			pageObj.compInfoMngeRegist();
		});
		
		// 우편번호 버튼 클릭 이벤트
		$("#zipBtn").on("click", function() {
			pageObj.zipListPopup();
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


