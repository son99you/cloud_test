/**
 * 공통 > 담당자 조회 (팝업)
 *
 * <pre>
 * comm 
 *  |_ popup
 *   |_ chargerList.js
 * 
 * </pre>
 * @date : 2018. 02. 20. 오후 02:36:51
 * @version : 1.0
 * @author : 은우소프트
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";

	/**  
     * <pre>
     * 1. 개요 : 담당자조회 Submit
     * 2. 처리내용 : 
     * 		담당자 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     */
	pageObj.chargerListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/chargerList.do");
	};

	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 */
	setchargerInfo = function(usrId, emplNo,usrNm, deptCd, deptNm, ofpsCd, ofpsNm, telNo, emalAddr) {
		var chargerGbn = $("input[name='setChargerGbn']").val() ;						//담당자 상태
		try {
			window.opener.chargerListAdd(usrId, emplNo,usrNm, deptCd, deptNm, ofpsCd, ofpsNm, telNo, emalAddr, chargerGbn);	
		}catch(exception) {
			//console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
		}
		self.close();
	}
	
	
	pageObj.chargerListRegist = function(){
		
		var arr_usr_id = $([]);		
		var arr_empl_no = $([]);		
		var arr_usr_nm = $([]);			
		var arr_dept_cd = $([]);			
		var arr_dept_nm = $([]);			
		var arr_ofps_cd = $([]);			
		var arr_ofps_nm = $([]);			
		var arr_tel_no = $([]);			
		var arr_emal_addr = $([]);			
		var isChoice = false;					//선택구분
		var chargerCnt = 0;						//선택담당자수
		var chargerGbn = $("input[name='setChargerGbn']").val() ;						//담당자 상태
		
		$("tbody input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_usr_id.push($("input[name='P_USR_ID']").eq(index).val());
				arr_empl_no.push($("input[name='P_EMPL_NO']").eq(index).val());
				arr_usr_nm.push($("input[name='P_USR_NM']").eq(index).val());
				arr_dept_cd.push($("input[name='P_DEPT_NO']").eq(index).val());
				arr_dept_nm.push($("input[name='P_DEPT_NM']").eq(index).val());
				arr_ofps_cd.push($("input[name='P_OFPS_CD']").eq(index).val());
				arr_ofps_nm.push($("input[name='P_OFPS_NM']").eq(index).val());
				arr_tel_no.push($("input[name='P_TEL_NO']").eq(index).val());
				arr_emal_addr.push($("input[name='P_EMAL_ADDR']").eq(index).val());
				chargerCnt++;
				isChoice = true;
			}
		});
	
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}
		
		//선택담당자수, 부서명, 부서코드, 직책명, 직책코드, 담당자명, 담당자ID, 전화번호, 이메일
		try {
			window.opener.chargerListAdd(chargerCnt, arr_usr_id,  arr_empl_no, arr_usr_nm, arr_dept_cd, arr_dept_nm, arr_ofps_cd, arr_ofps_nm, arr_tel_no, arr_emal_addr, chargerGbn);
		}catch(exception) {
			//console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
		}
		self.close();
	}
	
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.chargerListInqire();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.chargerListInqire();			
		});
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.chargerListRegist();
		});
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, [ "P_USR_NM_S", "P_EMPL_NO_S" ], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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