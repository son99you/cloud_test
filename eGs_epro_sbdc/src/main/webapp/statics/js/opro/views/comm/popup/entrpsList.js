/**
 * 공통 > 업체팝업 조회(팝업)
 *
 * <pre>
 * comm
 *    |_ entrpsList.js
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

	pageObj.entrpsList = function() {
		
		if($("#P_VEND_NM_S").val() != "" || $("#P_BIZRNO_S").val() != ""|| $("#P_VEND_REG_NO_S").val() != ""){
			FwkCmmnUtil.submitForm(defaultFrm, "/opro/comm/popup/entrpsList.do");
		}else{
			alert("조회조건을 입력해주세요.");
		}
	};	
	
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 */
	//setEntrpsInfo('${data.ENTRPS_REGIST_NO}','${data.BIZRNO }', '${data.ENTRPS_NM}', '${data.RPRSNTV_NM}', '${data.ADDRESS}', '${data.BIZCND_NM}','${data.INDUTY_NM}','${data.TELNO}','${data.EMAIL}','${data.CHARGER_NM}');
	setEntrpsInfo = function(vendRegNo,bizrno, vendNm, rprsNm, addrNm, telNo, emalAddr) {
		try {
			window.opener.entrpsListAdd(vendRegNo,bizrno, vendNm, rprsNm, addrNm, telNo, emalAddr);	
		}catch(exception) {
			//console.log("부모객체에 function 없으면 통과(별다른 문제 없음)");
		}
		self.close();
		
	};
	
	pageObj.ccpyListRegist = function() {
	
		var arr_vend_reg_no = $([]);	//업체등록번호
		var arr_bizrno = $([]);			//업체명
		var arr_vend_nm = $([]);		//거래처명
		var arr_rprs_nm = $([]);			//대표자명
		var arr_addr_nm = $([]);			//주소명
		var arr_tel_no = $([]);			//전화번호
		var arr_emal_addr = $([]);			//이메일	
		var isChoice = false;			//선택구분
		var ccpyCnt = 0;					//선택업체수
		
		$("tbody input[name='entrpsCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_vend_reg_no.push($("input[name='P_VEND_REG_NO']").eq(index).val());
				arr_bizrno.push($("input[name='P_BIZRNO']").eq(index).val());
				arr_vend_nm.push($("input[name='P_VEND_NM']").eq(index).val());
				arr_rprs_nm.push($("input[name='P_RPRS_NM']").eq(index).val());
				arr_addr_nm.push($("input[name='P_ADDR_NM']").eq(index).val());
				arr_tel_no.push($("input[name='P_TEL_NO']").eq(index).val());
				arr_emal_addr.push($("input[name='P_EMAL_ADDR']").eq(index).val());
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}
		
		//선택업체수, 업체등록번호, 사업자번호, 업체명, 담당자명, 이메일, 전화번호, 휴대폰번호, 대표자
		window.opener.entrpsListAdd(ccpyCnt, arr_vend_reg_no, arr_bizrno, arr_vend_nm, arr_rprs_nm,arr_chrgr_nm,arr_tel_no,arr_emal_addr);
		
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
		pageObj.entrpsList();
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
			
			pageObj.entrpsList();
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
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_VEND_NM_S","P_BIZRNO_S","P_VEND_REG_NO_S"], function() {
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