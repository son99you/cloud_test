/**
 * 전자입찰 > 유찰 등록
 *
 * <pre>
 *    |_ estiMngeRegstrRegistForm.js
 * 
 * </pre>
 * @date : 2018. 03. 13.
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */

	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";

	download = function(sn, grpNo) {
		$("#P_FILE_SN").val(sn);
		$("#P_FILE_GRP_NO").val(grpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	pageObj.compGroupRegist = function() {
		if($("#P_GRP_ID").val() == null || $("#P_GRP_ID").val() == "") {
			alert("그룹ID를 입력해주세요.");
			return false;
		}
		
		if(!confirm("수정 하시겠습니까? ")){
			return false;
		}
		var jsonData = $("#saveFrm").serializeObject();
		var actionUrl = "/comm/popup/compGroupUpdate"; 
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			if(res.result == "fail"){
				alert("이미 존재하는 그룹ID입니다.");
				$("#P_GRP_ID").val("");
				$("#P_GRP_ID").focus();
			}else{
				alert("업체그룹이 저장되었습니다.");
				window.opener.pageObj.compGroupMgrListInqire();
				window.open('', '_self').close();
			};
		});
	};
	

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰참가업체목록조회 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		$("#saveBtn").on("click", function() {
			pageObj.compGroupRegist();
		});

		$("#closeBtn").on("click", function() {
			window.close();
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