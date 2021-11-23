/**
 *  계약설계 > 입찰공고진행현황 목록
 *
 * <pre>
 * ebid
 *    |_ bidPblancResultList
 * 
 * </pre>
 * @date : 2020.09.04
 * @version : 1.0
 * @author : 은우소프트 joo
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
	pageObj.bidPblancResultList = function() {
		
		FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPblancResultList.do");
	};
	
	
	// 상세 PRCH_RQR_NO, ANNC_NO
	detailInqire =  function(pcrqRqrNo,anncNo,anncNgr,roundNo,bidPscd){
		$("#detailFrm input[name='P_ANNC_NO']").val(anncNo);
		$("#detailFrm input[name='P_ANNC_NGR']").val(anncNgr);
		$("#detailFrm input[name='P_ROUND_NO']").val(roundNo);
		$("#detailFrm input[name='P_PRCH_RQR_NO']").val(pcrqRqrNo);
		FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPblancResultDetail.do");
	};
	
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function() {
		$("#popupFrm input[name='P_ARA_DEPT_CD']").val($("select[name='P_ARA_DEPT_CD_S']").val());
		
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	
	//요구부서 삭제
	pageObj.deptInqireDelete = function() {
		$('#detpNm').val("");
		$('#deptId').val("");
	};

	deptListAdd = function(deptNo, deptNm){
		$('#detpNm').val(deptNm);
		$('#deptId').val(deptNo);
	}
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 사전공고현황 목록조회를 호출한다.
     * </pre>
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.bidPblancResultList();
	};
	
	pageObj.bidPblancResultExcelDwld = function(){
		$("select[name='P_ARA_DEPT_CD_S']").prop("disabled",false);
		FwkCmmnUtil.submitForm("searchFrm", "/ebid/bidPblancResultExcelDwld.do");
	};
	
	pageObj.setAraDeptCd = function(){
		if($("#P_AUTH_ID").val() == '1'){	//관리자일경우 전체조회
			$("select[name='P_ARA_DEPT_CD_S']").prop("disabled",false);
		}else if($("#P_AUTH_ID").val() == '4'){	//계약담당자일경우 전체조회
			$("select[name='P_ARA_DEPT_CD_S']").prop("disabled",false);
		}else{ 
			$("select[name='P_ARA_DEPT_CD_S']").prop("disabled",true);
		}
	};
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 견적의뢰목록조회 함수를 호출한다.
	 * 2. 사전공고등록버튼(#registBtn) 의 click 이벤트를 binding 한다.
	 *   - 신규등록버튼에 click 이벤트 발생시 신규등록버튼 함수를 호출한다.
	 * 3. 공고명 : 엔터키 이벤트 핸들러
	 *   - 공고명 입력후 엔터키 이벤트 발생시 페이지번호클릭 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 조회버튼 
		$("#searchBtn").on("click", function() {
			$("input[name=P_SEARCH]").val("Y");
			$("select[name='P_ARA_DEPT_CD_S']").prop("disabled",false);
			pageObj.clickPage(1); 
		});
		
		// 요구부서 검색버튼
		$("#searchDeptBtn").on("click", function() {
			pageObj.deptInqirePopup();
		});
		
		
		// 요구부서 삭제버튼
		$("#deptDelBtn").on("click", function() {
			pageObj.deptInqireDelete();
		});
		
		// 엑셀 다운로드버튼
		$("#excelBtn").on("click", function() {
			if(!confirm("조회된 입찰공고완료현황건에 대하여 엑셀 다운로드 하시겠습니까?")) {
				return false;
			}
			pageObj.bidPblancResultExcelDwld();
			pageObj.setAraDeptCd();
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_PRCH_RQR_NO_S","P_ANNC_NO_S","P_ARA_DEPT_CD_S","P_CONT_SECD_S","P_BID_NM_S","P_RQR_DEPT_NM_S","P_BID_PSCD_S","P_RQR_BEGIN_DT_S","P_RQR_END_DT_S"], function() {
			
			$("input[name='P_SEARCH']").val("Y");
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
	};
	
	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setAraDeptCd();
		pageObj.setEventHandler();
	}); 
})();