/**
 * 공통 > 평가위원 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmCmtmList.js
 * 
 * </pre>
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
	
	
	// 목록
	pageObj.estmCmtmList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/estmCmtmList.do");
	};
	
	
	// 선택
	pageObj.cmtmSetRegist = function() {
		
		var arr_estm_cmtm_no = $([]);   // 평가위원번호
		var arr_estm_cmtm_nm = $([]);   // 평가위원명
		var arr_ino_cmtm_secd = $([]);   // 내외부위원구분코드
		var arr_ino_cmtm_secd_nm = $([]);   // 내외부위원구분코드
		var arr_cp_no = $([]);   // 휴대폰번호
		var arr_tel_no = $([]);   // 전화번호		
		var arr_emal = $([]);   // 이메일
		var arr_llf_secd = $([]);   // 대분류구분코드
		var arr_llf_secd_nm = $([]);   // 대분류구분코드
		var arr_cntn_secd = $([]);   // 내역구분코드
		var arr_cntn_secd_nm = $([]);   // 내역구분코드
		var arr_mlf_secd = $([]);   // 중분류구분코드
		var arr_mlf_secd_nm = $([]);   // 중분류구분코드
		var arr_slf_secd = $([]);   // 소분류구분코드
		var arr_slf_secd_nm = $([]);   // 소분류구분코드
		var isChoice = false;   //선택구분
		var slctCmtmCnt = 0;   //선택담당자수
		
		var tbdy_id = $("#tbdy_id").val();   // append 될 tbody id
		
		$("#cmtmTbdy input[name='estmCbk']").each(function(index) {
			if($(this).prop("checked")){
				arr_estm_cmtm_no.push($("input[name='P_ESTM_CMTM_NO']").eq(index).val());
				/*arr_estm_cmtm_nm.push($("input[name='P_ESTM_CMTM_NM']").eq(index).val());
				arr_ino_cmtm_secd.push($("input[name='P_INO_CMTM_SECD']").eq(index).val());
				arr_ino_cmtm_secd_nm.push($("input[name='P_INO_CMTM_SECD_NM']").eq(index).val());
				arr_cp_no.push($("input[name='P_CP_NO']").eq(index).val());
				arr_tel_no.push($("input[name='P_TEL_NO']").eq(index).val());
				arr_emal.push($("input[name='P_EMAL']").eq(index).val());
				arr_llf_secd.push($("input[name='P_LLF_SECD']").eq(index).val());
				arr_llf_secd_nm.push($("input[name='P_LLF_SECD_NM']").eq(index).val());
				arr_cntn_secd.push($("input[name='P_CNTN_SECD']").eq(index).val());
				arr_cntn_secd_nm.push($("input[name='P_CNTN_SECD_NM']").eq(index).val());
				arr_mlf_secd.push($("input[name='P_MLF_SECD']").eq(index).val());
				arr_mlf_secd_nm.push($("input[name='P_MLF_SECD_NM']").eq(index).val());
				arr_slf_secd.push($("input[name='P_SLF_SECD']").eq(index).val());
				arr_slf_secd_nm.push($("input[name='P_SLF_SECD_NM']").eq(index).val());*/
				slctCmtmCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("평가위원을 선택하세요.");
			return ;
		}
		
		try {
			//window.opener.cmtmListAdd(slctCmtmCnt, arr_estm_cmtm_no,  arr_estm_cmtm_nm, arr_ino_cmtm_secd, arr_ino_cmtm_secd_nm, arr_cp_no, arr_tel_no, arr_emal, arr_llf_secd, arr_llf_secd_nm, arr_cntn_secd, arr_mlf_secd, arr_mlf_secd_nm, arr_slf_secd, arr_slf_secd_nm);
			window.opener.cmtmListAdd(slctCmtmCnt, arr_estm_cmtm_no, tbdy_id);
		}catch(exception) {
		}
		self.close();
		
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			pageObj.estmCmtmList();			
		});

		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.cmtmSetRegist();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ESTM_CMTM_NM_S"], function() {
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