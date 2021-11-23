(function() {
	
	pageObj = {};

/**
 * 메인 > bodyHeader
 *
 * <pre>
 * masc
 *    |_ bodyHeader.js
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 9:57:00
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */

	helpPopup = function(){
		$("#helpPopFrm").one("submit", function() {
			window.open("", "helpPopFrm", "width=1000px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/helpPopDetail.do';
	        this.method = 'POST';
	        this.target = 'helpPopFrm';
	    }).trigger("submit");
		
	};

	
	mainPage = function(){
		$("#loginFrm input[name='resourceName']").val("OEP15001");
		FwkCmmnUtil.submitForm("menuLeftMoveFrm", "/opro/estm/estmCmtmProgList.do");
	};
	
    /**  
     * <pre>
     * 1. 개요 : TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리
     * 2. 처리내용 : 
     * 		TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리한다.
     *  	@@@@@ 계정관리에서 정보를 받아서 추후 자동 처리해야함.
     *  
     * </pre>
     * @Function Name : clickLeftMenuMove
     * @date : 2015. 03. 12.
     * @author : 은우소프트 김봉수
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 03. 12.       은우소프트 김봉수              최초 작성 
     *  =======================================================   
     */
	clickLeftMenuMove = function(goUrl,urlId,menuNm) {
		$("#menuLeftMoveFrm input[name='resourceName']").val(urlId);
		
		$("#menuLeftMoveFrm").attr("action", goUrl);
		$("#menuLeftMoveFrm").submit();
	};
	
	/**  
     * <pre>
     * 1. 개요 : 로그아웃 클릭시 페이지 이동
     * 2. 처리내용 : 
     * 		로그아웃 클릭시 해당 세션의 정보를 지우고
     * 		로그인 페이지 화면으로 이동한다.
     *  
     * </pre>
     * @Function Name : logout
     * @date : 2015. 05. 04.
     * @author : 은우소프트 손연우
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 05. 04.       은우소프트 손연우              최초 작성 
     *  =======================================================   
     */
	
	logout = function(){
		
		if(!confirm("로그아웃 하시겠습니까?")){
			return false;
		}
		
		var actionUrl = "/opro/main/logout.do";
		var jsonParam = $("#logOutFrm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
			, function(res) {
//				if($("#P_AUTH_ID").val() == ""){
//					FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
//				}else{
//					FwkCmmnUtil.submitForm("logOutFrm","/opro/main/innerLoginForm.do");
//				}
				
				FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
			}
		);
	};
	
	
	beforeLoginPage = function() {
		FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
	};
	
	//추가 
	detailInqire = function(estmCmtm){ 
		$("#detailFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtm);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/user/estmCmtmInfoDetail.do");
	};
	
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/comm/userManual.do");
	};
	
	
	$(function(){
	});
})();