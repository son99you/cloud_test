/**
 * 공통 > 사업 조회
 *
 * <pre>
 * cmmn
 *    |_ cmmnPopupBsnsInqireList.js
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
     * @Function Name : bsnsInqireList
     * @date : 2015. 02. 26.
     * @author : 은우소프트 임동일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 14.       은우소프트 임동일              최초 작성 
     *  =======================================================   
     */
	pageObj.orderPlanList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/yearList.do");
	};
	
	
	/*mmrnAtCheck = function() {
		if($("#P_MMRN_AT_S").prop("checked")) {
			$("#P_MMRN_AT_S").val("Y");
		}else{
			$("#P_MMRN_AT_S").val("N");
		}
	};*/
	
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
	//setOrderPlanInfo('${data.ORPL_NO}', '${data.CNTRCT_BPLC_CD}', '${data.PRCURE_MTHD }','${data.ORDER_PLAN_NM}','${data.ORDER_GVSL_AMOUNT}','${data.PURCHS_PLNPRC}');
	//setOrderPlanInfo('${data.ORPL_NO}', '${data.CNTRCT_BPLC_CD}', '${data.PRCURE_MTHD }','${data.ORDER_PLAN_NM}','${data.ORDER_GVSL_AMOUNT}','${data.ORPR_ERA_YM }');" style="cursor: pointer;">
	setOrderPlanInfo = function(ORPL_NO, PRCURE_MTHD, ORDER_PLAN_NM, ORDER_GVSL_AMOUNT, PURCHS_PLNPRC, ORDER_OUTSRC_AMOUNT,ORPR_ERA_YM,ORPR_ERA_YM_FOM, BSNS_NM) {
		var toDay = $("#P_TODAY").val();
		var toDayFom = $("#P_TODAY_FOM").val();
		var btnId = $("#btnId").val();
 
		var flag = "T";	// 발주시기비교 플래그
		
		if( toDay == ORPR_ERA_YM){	// 발주시기가 현재 년월과 같으면
			if(btnId ==""){
				if($("#P_ORPL_NO",opener.document)){
					$("#P_ORPL_NO",opener.document).val(ORPL_NO);
				};
				if($("#P_PRCURE_MTHD",opener.document)){
					$("#P_PRCURE_MTHD",opener.document).val(PRCURE_MTHD);
				};
				if($("#P_CNTRCT_NM",opener.document)){
					$("#P_CNTRCT_NM",opener.document).val(ORDER_PLAN_NM);
				};
				if($("#P_GVSL_MTRIL_AM",opener.document)){
					$("#P_GVSL_MTRIL_AM",opener.document).val(ORDER_GVSL_AMOUNT);
				};
				// 용역 총사업비
				if($("#P_TOT_WCT",opener.document)){
					$("#P_TOT_WCT",opener.document).val(PURCHS_PLNPRC);
				};
				
				//발주도급금액
				if($("#P_PRSMPPC",opener.document)){
					$("#P_PRSMPPC",opener.document).val(ORDER_OUTSRC_AMOUNT);
				};
				
				if($("#P_PRPO_ORDER_PLAN_NM",opener.document)){
					$("#P_PRPO_ORDER_PLAN_NM",opener.document).val(ORDER_PLAN_NM);
				};
				 
				if($("#P_BSNS_NM",opener.document)){
					$("#P_BSNS_NM",opener.document).val(BSNS_NM);
				};
				
				/*if($("#fsseCd",opener.document)){
					$("#fsseCd",opener.document).val(fsseCd);
				};
				if($("#taxYn",opener.document)){
					$("#taxYn",opener.document).val(taxYn);
				};
				if($("#prgmCd1",opener.document)){
					$("#prgmCd1",opener.document).val(prgmCd1);
				};*/
			}else{
				if($("#P_ORPL_NO"+btnId,opener.document)){
					$("#P_ORPL_NO"+btnId,opener.document).val(ORPL_NO);
				};
				if($("#P_CNTRCT_BPLC_CD"+btnId,opener.document)){
					$("#P_CNTRCT_BPLC_CD"+btnId,opener.document).val(CNTRCT_BPLC_CD);
				};
				if($("#P_PRCURE_MTHD"+btnId,opener.document)){
					$("#P_PRCURE_MTHD"+btnId,opener.document).val(PRCURE_MTHD);
				};
				if($("#P_CNTRCT_NM"+btnId,opener.document)){
					$("#P_CNTRCT_NM"+btnId,opener.document).val(ORDER_PLAN_NM);
				};
				if($("#P_GVSL_MTRIL_AM"+btnId,opener.document)){
					$("#P_GVSL_MTRIL_AM"+btnId,opener.document).val(ORDER_GVSL_AMOUNT);
				};
				// 용역 총사업비
				if($("#P_TOT_WCT"+btnId,opener.document)){
					$("#P_TOT_WCT"+btnId,opener.document).val(PURCHS_PLNPRC);
				};
				
				//발주도급금액
				if($("#P_PRSMPPC"+btnId,opener.document)){
					$("#P_PRSMPPC"+btnId,opener.document).val(ORDER_OUTSRC_AMOUNT);
				};
				
				if($("#P_PRPO_ORDER_PLAN_NM"+btnId,opener.document)){
					$("#P_PRPO_ORDER_PLAN_NM"+btnId,opener.document).val(ORDER_PLAN_NM);
				};
				
				if($("#P_BSNS_NM",opener.document)){
					$("#P_BSNS_NM",opener.document).val(BSNS_NM);
				};
				
				/*if($("#bsnsNm"+btnId,opener.document)){
					$("#bsnsNm"+btnId,opener.document).val(bsnsNm);
				};
				if($("#bsnsNo"+btnId,opener.document)){
					$("#bsnsNo"+btnId,opener.document).val(bsnsNo);
				};
				if($("#mmrnAt"+btnId,opener.document)){
					$("#mmrnAt"+btnId,opener.document).val(mmrnAt);
				};
				if($("#budgetDeptNo"+btnId,opener.document)){
					$("#budgetDeptNo"+btnId,opener.document).val(budgetDeptNo);
				};
				if($("#fsyr"+btnId,opener.document)){
					$("#fsyr"+btnId,opener.document).val(fsyr);
				};
				if($("#fsseCd"+btnId,opener.document)){
					$("#fsseCd"+btnId,opener.document).val(fsseCd);
				};
				if($("#taxYn"+btnId,opener.document)){
					$("#taxYn"+btnId,opener.document).val(taxYn);
				};
				if($("#prgmCd1"+btnId,opener.document)){
					$("#prgmCd1"+btnId,opener.document).val(prgmCd1);
				};*/
				
				/*if($("#"+btnId+" #orgCd",opener.document)){
					$("#"+btnId+" #orgCd",opener.document).val(orgCd);
				};
				if($("#"+btnId+" #orgNm",opener.document)){
					$("#"+btnId+" #orgNm",opener.document).val(orgNm);
				};*/
			}
		
		
		
		window.opener.orderPlanReturnData();
		
		self.close();
		} else {	// 발주시기가 현재년월과 다르면
			flag = "F";
		}
		
		//alert("발주시기가 현재년월과 맞지 않습니다. \n해당 건의 발주시기를 수정하시겠습니까?");
		if(flag == "F" && !confirm("발주시기(" + ORPR_ERA_YM_FOM + ")가 현재년월("+ toDayFom +")과 맞지 않습니다. \n해당 건의 발주시기를 수정하시겠습니까?")){
			return false;
		}else if(flag == "F"){
			window.opener.moveOrderPlanDetail(ORPL_NO);
			self.close();
		}
			
		
		
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
		pageObj.bsnsInqireList();
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
			removeComma();
			pageObj.orderPlanList();			
		});		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 발주계획등록버튼
		$("#moveOrderPlanBtn").on("click", function() {
			window.opener.moveOrderPlan();
			self.close();		
		});
		
		
		if($("#P_MY_AUTHOR_CD").val() =="C" || $("#P_MY_AUTHOR_CD").val() =="D") {
			
	    }else{
	         $("#P_CNTRCT_BPLC_CD_X").attr("disabled","disabled");
	    } 
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BSNS_NM_S","select2"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		
		selectCal2('P_ORPR_ERA_BEGIN_M_S','P_ORPR_ERA_BEGIN_Y_S','01');
		selectCal2('P_ORPR_ERA_END_M_S','P_ORPR_ERA_END_Y_S','12'); 
		 
		if($("#P_ORPR_ERA_BEGIN_Y").val() != ""){
			$("#P_ORPR_ERA_BEGIN_Y_S").val($("#P_ORPR_ERA_BEGIN_Y").val());
		}
		if($("#P_ORPR_ERA_BEGIN_M").val() != ""){
			$("#P_ORPR_ERA_BEGIN_M_S").val($("#P_ORPR_ERA_BEGIN_M").val());
		}
		if($("#P_ORPR_ERA_END_Y").val() != ""){
			$("#P_ORPR_ERA_END_Y_S").val($("#P_ORPR_ERA_END_Y").val());
		}
		if($("#P_ORPR_ERA_END_M").val() != ""){
			$("#P_ORPR_ERA_END_M_S").val($("#P_ORPR_ERA_END_M").val());
		}
		
		pageObj.setEventHandler();
		
		
		
	});
})();