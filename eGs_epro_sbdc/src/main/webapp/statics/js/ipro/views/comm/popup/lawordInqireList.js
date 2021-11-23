
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
	pageObj.bsnsDeptListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/lawordInqireList.do");
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
	//"setLawordInfo('${data.LAWORD_CD}', '${data.LAWORD_CD_NM}', '${data.LAWORD_CD_DETAIL}');"
	setLawordInfo = function(lawordCd, lawordCdNm, lawordCdDetail) {
		var btnId = $("#btnId").val();
		 
		if(btnId ==""){
			if($("#lawordCd",opener.document)){
				$("#lawordCd",opener.document).val(lawordCd);
			};
			if($("#lawordCdNm",opener.document)){
				$("#lawordCdNm",opener.document).val(lawordCdNm);
			};
			if($("#lawordCdDetail",opener.document)){
				$("#lawordCdDetail",opener.document).val(lawordCdDetail);
			};
		}else{
			if($("#lawordCd"+btnId,opener.document)){
				$("#lawordCd"+btnId,opener.document).val(lawordCd);
			};
			if($("#lawordCdNm"+btnId,opener.document)){
				$("#lawordCdNm"+btnId,opener.document).val(lawordCdNm);
			};
			if($("#lawordCdDetail"+btnId,opener.document)){
				$("#lawordCdDetail"+btnId,opener.document).val(lawordCdDetail);
			};
			/*if($("#"+btnId+" #orgCd",opener.document)){
				$("#"+btnId+" #orgCd",opener.document).val(orgCd);
			};
			if($("#"+btnId+" #orgNm",opener.document)){
				$("#"+btnId+" #orgNm",opener.document).val(orgNm);
			};*/
		}
	    
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
		pageObj.bsnsDeptListInqire();
	};
	
	
	pageObj.bsnsListRegist = function() {
		
		var lawordCdNm =$("#LAWORD_CD_NM").val();				//근거법령
		var lawordCdDetail =$("#LAWORD_CD_DETAIL").val();			//근거법령상세
		
		var isChoice = false;				//선택구분
		var bsnsCnt = 0;						//근거법령선택수
		
		$("#chargerAllCbx").remove();
		
		$("input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				/*ARR_LAWORD_CD_NM.push($("input[name='P_LAWORD_CD_NM']").eq(index).val());
				ARR_LAWORD_CD_DETAIL.push($("input[name='P_LAWORD_CD_DETAIL']").eq(index).val());*/
				
				if((lawordCdNm == "" || lawordCdNm == null) && bsnsCnt == 0) { // 근거법령 처음 체크 일때 콤마 안찍게하기
					lawordCdNm = $("input[name='P_LAWORD_CD_NM']").eq(index).val();
				}else { 
					lawordCdNm = lawordCdNm + "," + $("input[name='P_LAWORD_CD_NM']").eq(index).val();
				}
				
				if((lawordCdDetail == "" || lawordCdDetail == null) && bsnsCnt == 0) { // 근거법령 처음 체크 일때 콤마 안찍게하기
					lawordCdDetail = $("input[name='P_LAWORD_CD_NM']").eq(index).val() + " : " + $("input[name='P_LAWORD_CD_DETAIL']").eq(index).val();
				}else { 
					lawordCdDetail = lawordCdDetail + "\n" + $("input[name='P_LAWORD_CD_NM']").eq(index).val() + " : " + $("input[name='P_LAWORD_CD_DETAIL']").eq(index).val();
				}
				
				bsnsCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("사업을 선택해 주시기 바랍니다.") ;
			return ;
		}
		/*//선택사업수, 사업번호, 사업명
		window.opener.bsnsListAdd(bsnsCnt, arr_bsns_no, arr_bsns_nm);
		//$(opener.location).attr("href","javascript:ccpyListAdd(ccpyCnt, arr_entrps_regist_no, arr_entrps_se_nm, arr_entrps_nm, arr_rprsntv_nm, arr_bizr_no);");
		 */
		
		//여러개를 받아와서 한곳에 집어넣기 떄문에 아래 코딩 사용
		var btnId = $("#btnId").val();
		
		if(btnId ==""){
			if($("#lawordCd",opener.document)){
				$("#lawordCd",opener.document).val("1");
			};
			if($("#lawordCdNm",opener.document)){
				$("#lawordCdNm",opener.document).val(lawordCdNm);
			};
			if($("#lawordCdDetail",opener.document)){
				$("#lawordCdDetail",opener.document).val(lawordCdDetail);
			};
		}else{
			if($("#lawordCd"+btnId,opener.document)){
				$("#lawordCd"+btnId,opener.document).val("1");
			};
			if($("#lawordCdNm"+btnId,opener.document)){
				$("#lawordCdNm"+btnId,opener.document).val(lawordCdNm);
			};
			if($("#lawordCdDetail"+btnId,opener.document)){
				$("#lawordCdDetail"+btnId,opener.document).val(lawordCdDetail);
			};
		}
		
		self.close();
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
			pageObj.bsnsDeptListInqire();			
		});		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.bsnsListRegist();
		});
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_LAWORD_CD_NM_S","select2"], function() {
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