/**
 * 조달공통 > 업체 조회
 *
 * <pre>
 * elbi
 *    |_ iepPrcmPopupCcpyList.js
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

	pageObj.ccpyListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/entrpsInqireNotInList.do");
	};	
	
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 */
	//setEntrpsInfo('${data.ENTRPS_REGIST_NO}','${data.BIZRNO }', '${data.ENTRPS_NM}', '${data.RPRSNTV_NM}', '${data.ADDRESS}', '${data.BIZCND_NM}','${data.INDUTY_NM}','${data.TELNO}','${data.EMAIL}','${data.CHARGER_NM}');
	setEntrpsInfo = function(entrpsRegistNo,bizrno, entrpsNm, rprsntvNm, address, bizcndNm, indutyNm, telno, email, chargerNm) {
		var btnId = $("#btnId").val()
			, over = $("#over").val()
			, textGbn = $("#P_TEXT_GBN").val();
		
		if(btnId ==""){ 
			
			if($("#entrpsRegistNo",opener.document)){
				$("#entrpsRegistNo",opener.document).val(entrpsRegistNo);
			};
			if($("#bizrno",opener.document)){
				$("#bizrno",opener.document).val(bizrno.substr(0,3)+'-'+bizrno.substr(3,2)+'-'+bizrno.substr(5));
			};
			if($("#entrpsNm",opener.document)){
				$("#entrpsNm",opener.document).val(entrpsNm);
			};
			if($("#rprsntvNm",opener.document)){
				$("#rprsntvNm",opener.document).val(rprsntvNm);
			};
			if($("#address",opener.document)){
				$("#address",opener.document).val(address);
			};
			if($("#bizcndNm",opener.document)){
				$("#bizcndNm",opener.document).val(bizcndNm);
			};
			if($("#indutyNm",opener.document)){
				$("#indutyNm",opener.document).val(indutyNm);
			};
			if($("#telno",opener.document)){
				$("#telno",opener.document).val(telno);
			};
			if($("#email",opener.document)){
				$("#email",opener.document).val(email);
			};
			if($("#telno",opener.document)){
				$("#telno",opener.document).val(telno);
			};
			if($("#chargerNm",opener.document)){
				$("#chargerNm",opener.document).val(chargerNm);
			};
			
			/*//계약서 작성시  부서에 따른 값 받아오기 위해서 코딩
			 원조조달계약팀 : 611211 
			 * 경영관리팀 : 611217
			 
			if($("#P_CTRTC").val() == "Y" && orgCd == "611211"){
				$("#P_PRCURE_KWAN_NM" ,opener.document).val("조달팀장");
			};
			if($("#P_CTRTC").val() =="Y" && orgCd == "611217"){
				$("#P_PRCURE_KWAN_NM" ,opener.document).val("경영관리팀장");
			};*/
			
		}else{
			
			var orgP_ENTRPS_REGIST_NO = $("#orgP_ENTRPS_REGIST_NO").val();
			var CNTRCT_MTH_CD = $("#CNTRCT_MTH_CD").val();
			// 업체중복가능
			if(over == "Y"){
				$("#overEntrpsRegistNo"+btnId,opener.document).val(entrpsRegistNo);
				$("#overEntrpsNm"+btnId,opener.document).val(entrpsNm);
				
			// 업체중복 불가능
			}else{
				if($("#entrpsRegistNo"+btnId,opener.document)){
					//이미 추가된 업체인지 확인
					var flag = true;
					$("input[id^=entrpsRegistNo]",opener.document).each(function(){
						
						if(CNTRCT_MTH_CD == "33"){
							
						}else{
							if($(this).val() == entrpsRegistNo){
								alert("이미 추가된 업체입니다. ");
								flag = false;
								return false;
							}
							
							// 2017-01-06 은잔디 추가
							if(entrpsRegistNo == orgP_ENTRPS_REGIST_NO){
								alert("이미 추가된 업체입니다.");
								flag = false;
								return false;
							}
						}
						
					}); 
					if(!flag) {
						return false;
					}else{
						$("#entrpsRegistNo"+btnId,opener.document).val(entrpsRegistNo);
					}
				};
			}
			if($("#bizrno"+btnId,opener.document)){
				$("#bizrno"+btnId,opener.document).val(bizrno.substr(0,3)+'-'+bizrno.substr(3,2)+'-'+bizrno.substr(5));
			};
			if($("#entrpsNm"+btnId,opener.document)){
				$("#entrpsNm"+btnId,opener.document).val(entrpsNm);
			};
			if($("#rprsntvNm"+btnId,opener.document)){
				$("#rprsntvNm"+btnId,opener.document).val(rprsntvNm);
			};
			if($("#address"+btnId,opener.document)){
				$("#address"+btnId,opener.document).val(address);
			};
			if($("#bizcndNm"+btnId,opener.document)){
				$("#bizcndNm"+btnId,opener.document).val(bizcndNm);
			};
			if($("#indutyNm"+btnId,opener.document)){
				$("#indutyNm"+btnId,opener.document).val(indutyNm);
			};
			if($("#telno"+btnId,opener.document)){
				$("#telno"+btnId,opener.document).val(telno);
			};
			if($("#email"+btnId,opener.document)){
				$("#email"+btnId,opener.document).val(email);
			};
			if($("#telno"+btnId,opener.document)){
				$("#telno"+btnId,opener.document).val(telno);
			};
			if($("#chargerNm"+btnId,opener.document)){
				$("#chargerNm"+btnId,opener.document).val(chargerNm);
			};
			
			/*
			 * 조달수수류 때문에 추가
			 * 다른 곳에서는 해당 id 가 없어서
			 * 영향은 없음. 
			 */ 
			if($("#depositBtnG2B", opener.document)){
				$("#depositBtnG2B", opener.document).val(entrpsRegistNo);
			};
			
			// 2017-11-22 은잔디 : 가행광산 도급인 추가
			if($("#entrpsNmY"+btnId,opener.document)){
				$("#entrpsNmY"+btnId,opener.document).val(entrpsNm);
			};
			
			if($("#entrpsRegistNoY"+btnId,opener.document)){
				$("#entrpsRegistNoY"+btnId,opener.document).val(entrpsRegistNo);
			};
			
			if($("#bizrnoY"+btnId,opener.document)){
				//$("#bizrnoY"+btnId,opener.document).val(bizrno);
				$("#bizrnoY"+btnId,opener.document).val(bizrno.substr(0,3)+'-'+bizrno.substr(3,2)+'-'+bizrno.substr(5));
			};
			
			if($("#rprsntvNmY"+btnId,opener.document)){
				$("#rprsntvNmY"+btnId,opener.document).val(rprsntvNm);
			};
			
			if($("#chargerNmY"+btnId,opener.document)){
				$("#chargerNmY"+btnId,opener.document).val(chargerNm);
			};
			
			if($("#emailY"+btnId,opener.document)){
				$("#emailY"+btnId,opener.document).val(email);
			};
			
			if($("#telnoY"+btnId,opener.document)){
				$("#telnoY"+btnId,opener.document).val(telno);
			};
			
			if($("#addressY"+btnId,opener.document)){
				$("#addressY"+btnId,opener.document).val(address);
			};
			
		}
		
		/**
		 * 받아지는 곳이 input 타입이 아닌 td에 id가 걸려있는 경우에 사용
		 */
		if(textGbn == "Y") {
			
			
			if($("#P_ENTRPS_REGIST_NO",opener.document)){
				$("#P_ENTRPS_REGIST_NO",opener.document).val(entrpsRegistNo);
			};
			
			if($("#P_ENTRPS_REGIST_NO",opener.document)){
				$("#P_ENTRPS_REGIST_NO",opener.document).text(entrpsRegistNo);
			};
			
			if($("#BIZRNO",opener.document)){
				$("#BIZRNO",opener.document).text(bizrno.substr(0,3)+'-'+bizrno.substr(3,2)+'-'+bizrno.substr(5));
			};
			if($("#ENTRPS_NM",opener.document)){
				$("#ENTRPS_NM",opener.document).val(entrpsNm);
			};
			
			/*if($("#ENTRPS_NM",opener.document)){
				$("#ENTRPS_NM",opener.document).text(entrpsNm);
			};*/
			
			if($("#ENTRPS_RPRSNTV_NM",opener.document)){
				$("#ENTRPS_RPRSNTV_NM",opener.document).text(rprsntvNm);
			};
			if($("#ENTRPS_ADRES",opener.document)){
				$("#ENTRPS_ADRES",opener.document).text(address);
			};
			/*if($("#bizcndNm",opener.document)){
				$("#bizcndNm",opener.document).val(bizcndNm);
			};
			if($("#indutyNm",opener.document)){
				$("#indutyNm",opener.document).val(indutyNm);
			};*/
			if($("#TELNO",opener.document)){
				$("#TELNO",opener.document).text(telno);
			};
			/*if($("#email",opener.document)){
				$("#email",opener.document).val(email);
			};
			if($("#telno",opener.document)){
				$("#telno",opener.document).val(telno);
			};
			if($("#chargerNm",opener.document)){
				$("#chargerNm",opener.document).val(chargerNm);
			};*/
			
		}
	    
		//선택담당자수, 부서명, 부서코드, 직책명, 직책코드, 담당자명, 담당자ID, 전화번호, 이메일
		//window.opener.chargerListAdd(chargerCnt, arr_org_nm,  arr_org_cd, arr_posi_nm, arr_posi_cd, arr_user_nm, arr_user_id, arr_com_tel_no, arr_mail);
		
		if($("#returnGbn").val() == "Y") {
			window.opener.jdgmnReturnData(btnId,entrpsRegistNo);
			self.close();
		}else{
			self.close();
		}
			
//		window.opener.jdgmnReturnData();
//		self.close();
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	pageObj.ccpyListRegist = function() {
	
		var arr_entrps_regist_no = $([]);	//업체등록번호
		var arr_entrps_se_nm = $([]);		//업체구분명
		var arr_entrps_nm = $([]);			//업체명
		var arr_rprsntv_nm = $([]);			//대표자명
		var arr_bizr_no = $([]);				//사업자번호
		var isChoice = false;				//선택구분
		var ccpyCnt = 0;						//선택업체수
		
		var arr_telno = $([]);				//업체전화번호
		
		$("input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_entrps_regist_no.push($("input[name='P_ENTRPS_REGIST_NO']").eq(index).val());
				arr_entrps_nm.push($("input[name='P_ENTRPS_NM']").eq(index).val());
				arr_rprsntv_nm.push($("input[name='P_RPRSNTV_NM']").eq(index).val());
				arr_bizr_no.push($("input[name='P_BIZRNO']").eq(index).val());
				arr_telno.push($("input[name='P_TELNO']").eq(index).val());
				
				ccpyCnt+1;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}

		
		//선택업체수, 업체등록번호, 업체구분명, 업체명, 대표자명, 사업자번호, 전화번호
		window.opener.ccpyListAdd(ccpyCnt, arr_entrps_regist_no,  arr_entrps_se_nm, arr_entrps_nm, arr_rprsntv_nm, arr_bizr_no, arr_telno);
	
		self.close();
	};
	
	ccpyRegist = function(entrps_regist_no, entrps_nm, rprsntv_nm, bizr_no, telno, adres, bizr_no_F, detailAdres ) {
		// 업체등록번호, 업체명, 대표자명, 사업자번호, 업체전화번호, 주소, 사업자번호 포멧
		window.opener.ccpyAdd(entrps_regist_no, entrps_nm, rprsntv_nm, bizr_no, telno, adres, bizr_no_F, detailAdres);
		
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
		pageObj.ccpyListInqire();
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
			pageObj.ccpyListInqire();
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
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ENTRPS_NM_S","P_BIZRNO_S"], function() {
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