/**
 * 조달공통 > 내부 담당자 조회
 *
 * <pre>
 * elbi
 *    |_ iepElbiBidPlanList.js
 * 
 * </pre>
 * @date : 2018. 02. 20. 오후 02:36:51
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
	var defaultFrm = "searchFrm";

	/**  
     * <pre>
     * 1. 개요 : 담당자조회 Submit
     * 2. 처리내용 : 
     * 		담당자 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     * @Function Name : chargerListInqire
     * @date : 2018. 02. 20.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 14.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
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
	 * @Function Name : setchargerInfo
	 * @date : 2018. 02. 20
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 02. 20.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	setchargerInfo = function(deptCd, deptNm, usrId, usrNm, mail, telNo, ofpsNm) {
		var btnId = $("#btnId").val();
		
		if($("#P_DLGD_YN").val() =="Y") {
			if($("#P_CONT_DLGT_REGR_NM"+btnId,opener.document)){
				$("#P_CONT_DLGT_REGR_NM"+btnId,opener.document).val(usrNm);
			};
			if($("#P_CONT_DLGT_REGR_ID"+btnId,opener.document)){
				$("#P_CONT_DLGT_REGR_ID"+btnId,opener.document).val(usrId);
			};			
			if($("#P_CONT_DLGT_DEPT_NM"+btnId,opener.document)){
				$("#P_CONT_DLGT_DEPT_NM"+btnId,opener.document).val(deptNm);
			};
			if($("#P_CONT_DLGT_DEPT_ID"+btnId,opener.document)){
				$("#P_CONT_DLGT_DEPT_ID"+btnId,opener.document).val(deptCd);
			};					
		}else {
			if(btnId ==""){
				if($('#tchnChangerYn').val() == "Y") {	//기술 담당자 조회
					if($("#tchnUsrId",opener.document)){
						$("#tchnUsrId",opener.document).val(usrId);
					};
					if($("#tchnUsrNm",opener.document)){
						$("#tchnUsrNm",opener.document).val(usrNm);
					};
					if($("#tchnUsrTel",opener.document)){
						$("#tchnUsrTel",opener.document).val(telNo);
					};
					if($("#tchnTelNo",opener.document)){
						$("#tchnTelNo",opener.document).val(telNo);
					};	
				}else {	//일반 담당자 조회
					if($("#usrId",opener.document)){
						$("#usrId",opener.document).val(usrId);
					};
					if($("#usrNm",opener.document)){
						$("#usrNm",opener.document).val(usrNm);
					};
					if($("#usrTel",opener.document)){
						$("#usrTel",opener.document).val(telNo);
					};
					if($("#telNo",opener.document)){
						$("#telNo",opener.document).val(telNo);
					};
					if($("#deptCd",opener.document)){
						$("#deptCd",opener.document).val(deptCd);
					};
					if($("#deptNm",opener.document)){
						$("#deptNm",opener.document).val(deptNm);
					};
					if($("#mail",opener.document)){
						$("#mail",opener.document).val(mail);
					};
					if($("#ofpsNm",opener.document)){
						$("#ofpsNm",opener.document).val(ofpsNm);
					};							
				}
				
				//계약서 작성시  부서에 따른 값 받아오기 위해서 코딩
				/* 원조조달계약팀 : 611211 
				 * 경영관리팀 : 611217
				 */
				
				if($("#P_CTRTC").val() == "Y" && deptCd == "713161"){
					$("#P_PRCURE_KWAN_NM" ,opener.document).val("조달계약팀장");
				};
				if($("#P_CTRTC").val() =="Y" && deptCd == "711150"){
					$("#P_PRCURE_KWAN_NM" ,opener.document).val("운영지원실장");
				};
				
			}else{
				if($("#deptCd"+btnId,opener.document)){
					$("#deptCd"+btnId,opener.document).val(deptCd);
				};
				if($("#deptNm"+btnId,opener.document)){
					$("#deptNm"+btnId,opener.document).val(deptNm);
				};
				if($("#usrId"+btnId,opener.document)){
					$("#usrId"+btnId,opener.document).val(usrId);
				};
				if($("#usrNm"+btnId,opener.document)){
					$("#usrNm"+btnId,opener.document).val(usrNm);
				};
				if($("#telNo"+btnId,opener.document)){
					$("#telNo"+btnId,opener.document).val(telNo);
				};
				if($("#mail"+btnId,opener.document)){
					$("#mail"+btnId,opener.document).val(mail);
				};
				if($("#"+btnId+" #mail"+btnId,opener.document)){
					$("#"+btnId+" #mail"+btnId,opener.document).val(mail);
				};
				if($("#ofpsNm",opener.document)){
					$("#ofpsNm",opener.document).val(ofpsNm);
				};			
			}			
		}
		
		pageObj.chargerListRegist(deptCd, deptNm, usrId, usrNm, mail, telNo, ofpsNm);
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 체크 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 체크 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 * @Function Name : chargerListRegist
	 * @date : 2015. 05. 21.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 05. 21.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerListRegist = function(deptCd, deptNm, usrId, usrNm, mail, telNo, ofpsNm) {
		/*
		var arr_org_nm = $([]);				//부서명
		var arr_org_cd = $([]);					//부서코드
		var arr_posi_nm = $([]);				//직책명
		var arr_posi_cd = $([]);				//직책코드
		var arr_user_nm = $([]);				//담당자명
		var arr_user_id = $([]);				//담당자id
		var arr_com_tel_no = $([]);			//전화번호
		var arr_mail = $([]);					//이메일
		var isChoice = false;					//선택구분
		var chargerCnt = 0;						//선택담당자수
		
		$("tbody input[name='chargerCbx']").each(function(index) {
			
			if($(this).prop("checked")){
				arr_org_nm.push($("input[name='P_ORG_NM']").eq(index).val());
				arr_org_cd.push($("input[name='P_ORG_CD']").eq(index).val());
				arr_posi_nm.push($("input[name='P_POSI_NM']").eq(index).val());
				arr_posi_cd.push($("input[name='P_POSI_CD']").eq(index).val());
				arr_user_nm.push($("input[name='P_USER_NM']").eq(index).val());
				arr_user_id.push($("input[name='P_USER_ID']").eq(index).val());
				arr_com_tel_no.push($("input[name='P_COM_TEL_NO']").eq(index).val());
				arr_mail.push($("input[name='P_MAIL']").eq(index).val());
				
				chargerCnt++;
				isChoice = true;
			}
		});
		*/

		//선택담당자수, 부서명, 부서코드, 직책명, 직책코드, 담당자명, 담당자ID, 전화번호, 이메일
		//window.opener.chargerListAdd(chargerCnt, arr_org_nm,  arr_org_cd, arr_posi_nm, arr_posi_cd, arr_user_nm, arr_user_id, arr_com_tel_no, arr_mail);
		try {
			window.opener.chargerListAdd(deptCd, deptNm, usrId, usrNm, mail, telNo, ofpsNm);	
		}catch(exception) {
			console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
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
     * @date : 2018. 02. 20
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 02. 20.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
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
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, [ "P_USR_NM", "P_DEPT_NM" ], function() {
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