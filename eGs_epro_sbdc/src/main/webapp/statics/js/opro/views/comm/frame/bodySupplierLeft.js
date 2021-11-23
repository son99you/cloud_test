/**
 * 메인 > bodySupplierLeft
 *
 * <pre>
 * masc
 *    |_ bodySupplierLeft.js



  	/**  
     * <pre>
     * 1. 개요 : TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리
     * 2. 처리내용 : 
     * 		TOP메뉴 클릭시 해당 LEFT메뉴 셋팅 및 첫 메뉴 이동 처리한다.
     *  	@@@@@ 계정관리에서 정보를 받아서 추후 자동 처리해야함.
     */
	clickLeftMenuMove = function(goUrl, resourceName, resourceDesc) {
		
		$("#menuLeftMoveFrm input[name='resourceName']").val(resourceName);
		$("#menuLeftMoveFrm input[name='resourceDesc']").val(resourceDesc);
		
		$("#menuLeftMoveFrm").attr("action", goUrl);
		
		$("#menuLeftMoveFrm").submit();
	};
	
	
	/**  
     * <pre>
     * 1. 개요 : 업체 로그인 전 페이지 이동 방지
     * 2. 처리내용 :
     *  
     */
	clickLeftMenuNotMove = function() {
		
		/*$("#menuLeftMoveFrm input[name='resourceName']").val(resourceName);
		$("#menuLeftMoveFrm input[name='resourceDesc']").val(resourceDesc);
		
		$("#menuLeftMoveFrm").attr("action", goUrl);
		
		$("#menuLeftMoveFrm").submit();*/
		alert("로그인후 사용가능합니다.");
		
	};

	
	

	/**  
     * <pre>
     * 1. 개요 : 입력되는 화면에서 저장하지 않고 메뉴를 이동하는 경우 체크
     * 2. 처리내용 : 
     * 		inputChk 값을 비교하여 Regist, Updt인 경우 메시지 창으로 알림을 준다.
     *  	확인 인 경우 클릭한 메뉴로 이동하며 취소인 경우 현재 화면에 위치한다.
     */
	/*clickMenuMove = function(resourcePattern, resourceName, resourceDesc) {
		
		$("#menuMoveFrm input[name='returnUrl']").val(resourcePattern);
		$("#menuMoveFrm input[name='resourceName']").val(resourceName);
		$("#menuMoveFrm input[name='resourceDesc']").val(resourceDesc);
		
		$("#menuMoveFrm").attr("action", resourcePattern);
		$("#menuMoveFrm").submit();

		if(inputChk == "Regist" || inputChk == "Updt"){
			if(confirm(FwkMssageUtil.getMessage("EW.CON.001", resourceDesc+"목록"))){
				$("#menuMoveFrm").attr("action", $("#menuMoveFrm input[name='contextPath']").val()+resourcePattern);
				$("#menuMoveFrm").submit();
			}
		}else{

			if(resourcePattern.match("http://") ){
				window.open(resourcePattern, "ibm", "toolbar=yes,status=yes,scrollbars=yes,resizeable=yes,menubar=yes,left=0,top=0");
			}else{
				$("#menuMoveFrm").attr("action", $("#menuMoveFrm input[name='contextPath']").val()+resourcePattern);
				$("#menuMoveFrm").submit();
			}
		}
	};*/

