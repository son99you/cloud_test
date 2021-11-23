/**
 * 메인 > bodyHeader
 *
 */
	
	/**  
     * <pre>
     * 1. 개요 : 로그아웃 클릭시 페이지 이동
     * 2. 처리내용 : 
     * 		로그아웃 클릭시 해당 세션의 정보를 지우고
     * 		로그인 페이지 화면으로 이동한다.
     *  
     */

	logout = function(){
		FwkCmmnUtil.submitForm("logOutFrm","/supplier/masc/loginForm.do");
	};
	
	
	
