/**
 * 메인 > 회원 가입 폼
 *
 * <pre>
 * main
 *    |_ mberSbscrbForm.js
 * 
 * </pre>
 * @date : 2015. 06. 08. 오후 12:15:00
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Form Name
	 */
	var defaultFrm = "joinFrm";
	
	var chk1 = 0;
	var chk2 = 0;
	
	/**  
	 * <pre>
	 * 1. 개요 : 숫자만 입력가능
	 * 2. 처리내용 : 
	 * 		사업자번호 입력시 숫자인지 체크한다.
	 * </pre>
	 * @Function Name : numCeck
	 * @date : 2015. 06. 08.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 06. 08.       은우소프트 전상훈                    최초 작성 
	 *  =======================================================   
	 */
	isNumber = function(str) {
		var re=/^[0-9]*$/gi;
		return re.test(str);
	};
	
	
	pageObj.numCeck = function() {
		if($("#P_BIZRNO").val() != "" ){
			if( isNumber($("#P_BIZRNO").val()) != true){
				alert("숫자만 입력 가능합니다.");
				$("#P_BIZRNO").val( $("#P_BIZRNO").val().replace(/[^0-9]/gi,""));
			}
		}
	};
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 회원가입 확인 버튼 클릭
	 * 2. 처리내용 : 	
	 *  	- 회원가입 여부를 확인하고 회원가입한다.
	 * </pre>
	 * @Function Name : joinFormPage
	 * @date : 2015. 06. 08.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 06. 08.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	pageObj.joinFormPage = function() {
		
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/main/joinFormPage.do");
		
		/*if($("#P_JOIN_AGRE").is(":checked") == false){
			alert("약관 동의를 해주시기 바랍니다.");
			return false;
		}
		
		if($("#P_BIZRNO").val() == ''){
			alert("사업자번호를 입력해 주세요.");
			return false;
		}
		
		if($("#P_BIZRNO").val().length != 10){
			alert("사업자번호를 잘못 입력하셨습니다.");
			return false;
		}
		
		var jsonData = $("#"+defaultFrm).serializeObject();
		var actionUrl = "/opro/main/joinEnpaCheck";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			if(res.joinCheck=='join'){
				alert("이미 가입 되어있는 사업자 번호입니다.");
			}else if(res.joinCheck!='join'){
				FwkCmmnUtil.submitForm(defaultFrm, "/opro/main/joinFormPage.do");
			}
		});
		return;*/
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 동의
		$("#agreeBtn").on("click", function() {
			
			if(chk1 != 1){
				alert("개인정보수집 및 이용에 관해 동의하셔야 합니다.");
				return false;
			}
			
			if(chk2 != 1){
				alert("개인정보 처리 방침에 동의하셔야 합니다.");
				return false;
			}
			
			//회원가입 폼으로 이동 
			pageObj.joinFormPage();
		});
		
		// 비동의
		$("#disagreeBtn").on("click", function() {
			//회원가입 폼으로 이동 
			FwkCmmnUtil.submitForm("mainFrm","/opro/main/emgncLoginForm.do");
		});
		
		// 사업자번호 숫자만 입력가능
		$("#P_BIZRNO").on("keyup",function() {
			pageObj.numCeck();
		});
		
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BIZRNO"], function(res) {
			pageObj.joinFormPage(); 
		});
		
		
		$("#chk1").change(function(){
			if($("#chk1").is(":checked")){
				chk1++;
			}else{
				chk1--;
			}
		});
		
		
		$("#chk2").change(function(){
			if($("#chk2").is(":checked")){
				chk2++;
			}else{
				chk2--;
			}
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


