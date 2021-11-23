/**
 * 조달공통 > 업체 조회
 *
 * <pre>
 * elbi
 *    |_ oepPrcmPopupCcpyList.js
 * 
 * </pre>
 * @date : 2015. 02. 26. 오후 02:36:51
 * @version : 1.0
 * @author : 은우소프트 임동일
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
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/comm/popup/ccpyPopupList.do");
	};	
	
	pageObj.addDept = function() {
		var temp ="";
		$("input[name='entrpsChoiseCbx']").each(function(){
			if($(this).is(":checked")){
				var tempArray = $(this).val().split(','); 
				
				temp += "<tr class='row'><td><input type='checkbox' id='' name='entrpsChoiseCbx' value=''></td>";
				temp += "<td>대표자</td>";
				temp += "<td class='left_T'>"+tempArray[0]+"</td>";
				temp += "<td>"+tempArray[2]+"</td>"; 
				temp += "<td>"+tempArray[1]+"</td>";
				temp += "<td><input type='text' value=''>% </td></tr>";
				 
			}
		});
		if($("#trAddTbdy",opener.document)){
			$("#trAddTbdy",opener.document).html(temp);
		};
		window.close();
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
		
		$("input[name='entrpsChoiseCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_entrps_regist_no.push($("input[name='P_ENTRPS_REGIST_NO']").eq(index).val());
				arr_entrps_se_nm.push($("input[name='P_ENTRPS_SE_NM']").eq(index).val());
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
		//$(opener.location).attr("href","javascript:ccpyListAdd(ccpyCnt, arr_entrps_regist_no, arr_entrps_se_nm, arr_entrps_nm, arr_rprsntv_nm, arr_bizr_no);");
	
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
			pageObj.ccpyListInqire();
		});
		//선택버튼
		$("#choiceBtn").on("click", function() {
			//pageObj.ccpyListRegist();
			pageObj.addDept();
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