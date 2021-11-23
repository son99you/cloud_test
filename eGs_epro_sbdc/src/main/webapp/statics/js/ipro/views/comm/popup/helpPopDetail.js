/**
 * 공통 > 부서 조회
 *
 * <pre>
 * cmmn
 *    |_ cmmnPopupDeptrInqireList.js
 * 
 * </pre>
 * @date : 2016. 10. 27.
 * @version : 1.0
 * @author : 은우소프트 손연우
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
     * 		담당자 조회 Form 을 Submit 한다.
     *  	
     * </pre>
     * @Function Name : chargerListInqire
     * @date : 2015. 02. 26.
     * @author : 은우소프트 임동일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 14.       은우소프트 임동일              최초 작성 
     *  =======================================================   
     */
	pageObj.deptList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/deptList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 * @Function Name : setchargerInfo
	 * @date : 2016. 10. 27
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 10. 27.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	setdeptInfo = function(deptNo, deptNm) {
		window.opener.deptListAdd(deptNo, deptNm);
	   self.close();
	};
	
	
	setdeptInfo2 = function(deptNo, deptNm) {
		window.opener.deptListAdd2(deptNo, deptNm);
	   self.close();
	};
	
	pageObj.deptListRegist = function() {
	
		var arr_dept_no = $([]);	
		var arr_dept_nm = $([]);		
		var isChoice = false;				//선택구분
		var ccpyCnt = 0;					//선택업체수
		
		$("tbody input[name='deptCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_dept_no.push($("input[name='P_DEPT_NO']").eq(index).val());
				arr_dept_nm.push($("input[name='P_DEPT_NM']").eq(index).val());
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}
		
		//선택업체수, 부서번호, 부서명
		window.opener.ccpyListAdd(ccpyCnt, arr_dept_no, arr_dept_nm);
		
		self.close();
	};
	
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2015. 02. 26
     * @author : 은우소프트 임동일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 26.       은우소프트 임동일              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.deptList();
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
			pageObj.deptList();			
		});		
		// 닫기버튼
		$("#colseBtn").on("click", function() {
			self.close();
			return false;
		});
		
		// 선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.deptListRegist();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_DEPT_NM_S","P_DEPT_NO_S"], function() {
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