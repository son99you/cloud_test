/**
 * 메인 > bodyLeft
 *
 * <pre>
 * masc
 *    |_ bodyLeft.js
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 9:57:00
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */

	/**  
     * <pre>
     * 1. 개요 : 입력되는 화면에서 저장하지 않고 메뉴를 이동하는 경우 체크
     * 2. 처리내용 : 
     * 		inputChk 값을 비교하여 Regist, Updt인 경우 메시지 창으로 알림을 준다.
     *  	확인 인 경우 클릭한 메뉴로 이동하며 취소인 경우 현재 화면에 위치한다.
     *  
     * </pre>
     * @Function Name : clickMenuMove
     * @date : 2015. 1. 22.
     * @author : 은우소프트 김봉수
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 1. 22.       은우소프트 김봉수              최초 작성 
     *  =======================================================   
     */
	clickMenuMove = function(resourcePattern, resourceName, resourceDesc) {
		$("#menuMoveFrm input[name='returnUrl']").val(resourcePattern);
		$("#menuMoveFrm input[name='resourceName']").val(resourceName);
		$("#menuMoveFrm input[name='resourceDesc']").val(resourceDesc);
		
		$("#menuMoveFrm").attr("action", $("#menuMoveFrm input[name='contextPath']").val()+resourcePattern);
		$("#menuMoveFrm").submit();
		
	};
	
	$(function(){
		$("#"+$("#resourceName").val()).css("background-color","#f3f3f3");
	});
