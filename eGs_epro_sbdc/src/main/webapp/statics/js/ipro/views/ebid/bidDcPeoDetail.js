/**
 * 입찰관리 > 현장설명회 상세
 *
 * <pre>
 * ebid
 *    |_ bidDcPeoDetail.js
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "menuMoveFrm";
	
	
	/**
	 * 지명업체 찾기 이벤트 발생시
	 * 1. 지명업체선택 팝업 화면으로 이동한다.
	 * 2. 팝업화면에서 선택된 업체의 정보를 화면에 보여준다.
	 */
	pageObj.nmenSearchEvent = function () {
		
		// 팝업처리
		$("#popupFrm").one("submit", function() {
			window.open("", "commCcpyFormPopup", "width=740px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/entrpsList.do';
	        this.method = 'POST';
	        this.target = 'commCcpyFormPopup';
	    }).trigger("submit");
	};
	
	/**
	 * 지명업체 찾기 팝업 에서 업체 선택 시 Event
	 * 1. 지명업체선택 팝업 화면에서 받아 온 업체 정보를 부모창에 적용한다.
	 */
	ccpyListAdd = function (ccpyCnt, arr_entrps_regist_no, arr_bizr_no, arr_entrps_nm, arr_chrgr_nm, arr_email, arr_telno, arr_cpno, arr_rprs_nm) {
		//선택업체수, 업체등록번호, 사업자번호, 업체명, 담당자명, 이메일, 전화번호, 휴대폰번호, 대표자
		$(arr_entrps_regist_no).each(function(inx, item){
			// 중복체크
			var flag=true;
			$("input[name='P_VEND_REG_NO']").each(function(){
				if($(this).val() == arr_entrps_regist_no[inx]){
					flag = false;
					return false;
				}
			});
			
			if(flag == true){
				// 팝업에서 선택된 정보 화면에 보여주기
				var copyRow = $("#nmenChoiseHiddTbdy").children().clone(true);
				
				copyRow.find("label[for='nmenChoiseCbx']").attr("for","nmenChoiseCbx"+arr_entrps_regist_no[inx]);
				copyRow.find("input[name='nmenChoiseCbx']").attr("id","nmenChoiseCbx"+arr_entrps_regist_no[inx]);
				copyRow.find("input[name='P_VEND_REG_NO']").val(arr_entrps_regist_no[inx]);
				copyRow.find("input[name='P_VEND_REG_NO']").attr("disabled", false);
				copyRow.find("[bizrNo]").text(arr_bizr_no[inx]);
				copyRow.find("[entrpsNm]").text(arr_entrps_nm[inx]);
				copyRow.find("[rprsntvNm]").text(arr_rprs_nm[inx]);
				
				copyRow.find("[chrgrNm]").val(arr_chrgr_nm[inx]);
				copyRow.find("[tel]").val(arr_telno[inx]);
				copyRow.find("[eMail]").val(arr_email[inx]);
				
				copyRow.find("[regDe]").text(FwkDateUtil.getCurrentDate('yyyy-MM-dd'));
				
				copyRow.css({"display" : ""});
				
				$("#nmenChoiseEmpty").css({"display" : "none"}); // [선택된 업체가 없습니다.] 문구 감추기
				
				$("#nmenChoiseShowTbdy").append(copyRow);
			}
		});
	};
	
	/**
	 * 지명업체 삭제 이벤트 발생시
	 * 1. 체크된 지명업체 Row를 삭제한다.
	 */
	pageObj.nmenDeleteEvent = function () {
		
		$("#nmenChoiseShowTbdy input[name='nmenChoiseCbx']").each(function(){
			if(this.checked){
				$(this).parent().parent().remove();
			}
		});
		
		// 선택된 업체가 없을 경우 화면에 보여지는 문구 보여주기
		if($("#nmenChoiseShowTbdy tr").length == 0){
			$("#nmenChoiseEmpty").css({"display" : ""});
		}
		
	};
	

	/**  
     * <pre>
     * 1. 개요 : 현장설명회목록 페이지 이동
     * 2. 처리내용 : 
     * 		- 현장설명회목록 조회 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : bidDcPeoList
     * @date : 2015. 02. 23.
     * @author : 은우소프트 손연우
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 23.       은우소프트 손연우              최초 작성 
     *  =======================================================   
     */
	pageObj.bidDcPeoList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidDcPeoList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰공고문 상세 팝업
	 * 2. 처리내용 : 
	 * 		- 입찰공고문 상세 팝업 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bidWrtancDetailPopup
	 * @date : 2015. 06. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 06. 05.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidWrtancDetailPopup = function() {
		$("#bidWrtancDetailPopupFrm").one("submit", function() {
			window.open("", "bidWrtancDetailPopup", "width=1060px, height=780px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidWrtancDetail.do';
	        this.method = 'POST';
	        this.target = 'bidWrtancDetailPopup';
	    }).trigger("submit");
	};
	
	// 저장
	pageObj.bidDcPeoRegist = function(){
		var jsonParam = $("#registFrm").serializeObject();
		var actionUrl = "/ebid/bidPartcptEntrpsRegist";
		FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(res){
			pageObj.bidDcPeoList();
		});
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록버튼에 click 이벤트 발생시 입찰계획목록조회 함수를 호출한다.
	 * 2. 유찰버튼(#fibBtn) 의 click 이벤트를 binding 한다.
	 *   - 유찰버튼에 click 이벤트 발생시 유찰 사유 등록 팝업조회 함수를 호출한다.
	 * 3. 현장설명회 참가업체 등록버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 현장설명회 참가업체 등록버튼에 click 이벤트 발생시 현장설명회 참가업체 찾기 팝업조회 함수를 호출한다.
	 * 4. 현장설명회 참가업체 삭제버튼(#delBtn) 의 click 이벤트를 binding 한다.
	 *   - 현장설명회 참가업체 삭제버튼에 click 이벤트 발생시 현장설명회 참가업체 삭제 함수를 호출한다. 
	 */	
	pageObj.setEventHandler = function() {

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.bidDcPeoList();
		});
		
		//  지명업체 찾기에 따른 클릭 이벤트
		$("#nmenSearchBtn").on("click", function() {
			pageObj.nmenSearchEvent();
		});
		
		//  지명업체 삭제에 따른 클릭 이벤트
		$("#nmenDeleteBtn").on("click", function() {
			pageObj.nmenDeleteEvent();
		});
		
		//입찰공고문 팝업 버튼
		$("#detailBtn").on("click", function() {
			pageObj.bidWrtancDetailPopup();
		});
		
		// 저장버튼
		$("#saveBtn").on("click", function() {
			if(!confirm("참석업체를 등록 하시겠습니까?")){
				return false;
			}
			
			$("#nmenChoiseHiddTbdy").remove();
			
			pageObj.bidDcPeoRegist();
		});

	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	});
})();