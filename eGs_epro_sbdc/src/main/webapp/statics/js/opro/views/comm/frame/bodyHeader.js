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
	clickLeftMenuMove = function(goUrl, resourceName, resourceDesc) {
		
		$("#menuLeftMoveFrm input[name='resourceName']").val(resourceName);
		$("#menuLeftMoveFrm input[name='resourceDesc']").val(resourceDesc);
		
		$("#menuLeftMoveFrm").attr("action", $("#menuLeftMoveFrm input[name='contextPath']").val()+goUrl);
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
		
		//window.close();
		//var hiddenParam = [{"id":"returnUrl", "name":"returnUrl", "value":"/buyer/masc/emgncLoginForm.do"}];
		//FwkCmmnUtil.submitForm("logOutFrm","/buyer/masc/emgncLoginForm.do", hiddenParam);
		
		
		//FwkCmmnUtil.submitForm("logOutFrm","/com/acau/emplyrLogout.do", hiddenParam);
	};
