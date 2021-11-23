/**
 * 공통 > 예산팝업 조회(팝업)
 *
 * <pre>
 * comm
 *    |_ budgetList.js
 * 
 * </pre>
 * @date : 2015. 02. 26. 오후 02:36:51
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
	var defaultFrm = "searchFrm";

	pageObj.budgetList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/budgetList.do");
	};	
	
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 */
	//조직명, 조직코드, 프로젝트명, 프로젝트코드,계정코드,계정명,세목코드,결의서잔액, 당초예산,지출결의,사영예산,예산년도, 예산월, 
	setbudgetInfo = function(deptNm, deptNo, bsnsCd, bsnsNm, acntCd, acntNm, acntItemCd,rqstBlncAmt, bdgAmt, expsRqstAmt, useBdgAmt,bdgYr, bdgMm, ddtlbzId) {
		//선택담당자수, 부서명, 부서코드, 직책명, 직책코드, 담당자명, 담당자ID, 전화번호, 이메일
		try {
			window.opener.budgetListAdd(deptNm, deptNo, bsnsCd, bsnsNm, acntCd, acntNm, acntItemCd,rqstBlncAmt, bdgAmt, expsRqstAmt, useBdgAmt,bdgYr, bdgMm, ddtlbzId);	
		}catch(exception) {
			//console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
		}
		self.close();
		
	};
	
	//조직명, 조직코드, 프로젝트명, 프로젝트코드,계정코드,계정명,세목코드,결의서잔액, 당초예산,지출결의,사영예산,예산년도, 예산월, 
	pageObj.ccpyListRegist = function() {
	
		var arr_dept_nm = $([]);	
		var arr_dept_no = $([]);	
		var arr_bsns_cd = $([]);	
		var arr_bsns_nm = $([]);	
		var arr_acnt_cd = $([]);	
		var arr_acnt_nm = $([]);	
		var arr_acnt_item_cd = $([]);	
		var arr_rqst_blnc_amt = $([]);	
		var arr_bdg_amt = $([]);		
		var arr_exps_rqst_amt = $([]);		
		var arr_use_bdg_amt = $([]);		
		var arr_bdg_yr = $([]);		
		var arr_bdg_mm = $([]);		
		var arr_ddtlbz_id = $([]);		
		var isChoice = false;				//선택구분
		var ccpyCnt = 0;					//선택업체수
		
		$("tbody input[name='budgetCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_dept_nm.push($("input[name='P_DEPT_NM']").eq(index).val());
				arr_dept_no.push($("input[name='P_DEPT_NO']").eq(index).val());
				arr_bsns_cd.push($("input[name='P_BSNS_CD']").eq(index).val());
				arr_bsns_nm.push($("input[name='P_BSNS_NM']").eq(index).val());
				arr_acnt_cd.push($("input[name='P_ACNT_CD']").eq(index).val());
				arr_acnt_nm.push($("input[name='P_ACNT_NM']").eq(index).val());
				arr_acnt_item_cd.push($("input[name='P_ACNT_ITEM_CD']").eq(index).val());
				arr_rqst_blnc_amt.push($("input[name='P_RQST_BLNC_AMT']").eq(index).val());
				arr_bdg_amt.push($("input[name='P_BDG_AMT']").eq(index).val());
				arr_exps_rqst_amt.push($("input[name='P_EXPS_RQST_AMT']").eq(index).val());
				arr_use_bdg_amt.push($("input[name='P_USE_BDG_AMT']").eq(index).val());
				arr_bdg_yr.push($("input[name='P_BDG_YR']").eq(index).val());
				arr_bdg_mm.push($("input[name='P_BDG_MM']").eq(index).val());
				arr_ddtlbz_id.push($("input[name='P_DDTLBZ_ID']").eq(index).val());
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("예산을 선택해 주시기 바랍니다.") ;
			return ;
		}
		//선택업체수, 업체등록번호, 사업자번호, 업체명, 담당자명, 이메일, 전화번호, 휴대폰번호, 대표자
		window.opener.budgetListAdd(ccpyCnt, arr_dept_nm, arr_dept_no, arr_bsns_cd, arr_bsns_nm,arr_acnt_cd,arr_acnt_nm,arr_acnt_item_cd,arr_rqst_blnc_amt,arr_bdg_amt, arr_exps_rqst_amt, arr_use_bdg_amt, arr_bdg_yr, arr_bdg_mm, arr_ddtlbz_id);
		
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
		pageObj.budgetList();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 선택버튼(#choiceBtn) 의 click 이벤트를 binding 한다.
	 *   - 선택 버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 4. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.budgetList();
		});
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.ccpyListRegist();
		});
		
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BDG_YR_S","P_BSNS_NM","P_ACNT_NM","P_DEPT_NM_S"], function() {
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