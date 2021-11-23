/**
 * 공통 > 입찰 조회 (팝업)
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ bidVendOpenList.jsp
 * 
 * </pre>
 * @date : 2018. 02. 20. 오후 02:36:51
 * @version : 1.0
 * @author : 은우소프트 맹경열
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

	/**  
     * <pre>
     * 1. 개요 : 담당자조회 Submit
     * 2. 처리내용 : 
     * 		담당자 조회 Form 을 Sumit 한다.
     * </pre>
     * @Function Name : chargerListInqire
     * @date : 2018. 02. 20.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 14.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.chargerListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/chargerList.do");
	};

	/**  
	 * <pre>
	 * 1. 개요 : 선택된 공고별 품목 업체 메일 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Function Name : selectAnncVendCheck
	 * @date : 2019. 05. 29
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 05. 29.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	pageObj.selectAnncVendCheck = function() {
		
		//팝업폼에 품목번호 제거
//		$('#sendFrm').find('input[name=P_ANNC_NO]').remove();
		
		var flag = true;
		var chkCnt = $("input[name='anncChk']:checked").length;
		
//		if(chkCnt < 1){
		if($("#choiceYn").val() == null || $("#choiceYn").val() == ""){
			alert("메일발송할 공고를 선택해주세요.");
			flag = false;
			return false;
		}else{
			
			if($("#P_TTL").val() == null || $("#P_TTL").val() == ""){
				alert("[제목]은 필수입니다.");
				$("#P_TTL").focus();
				flag = false;
				return false;
			}
			
			if($("#P_CNTN").val() == null || $("#P_CNTN").val() == ""){
				alert("[내용]은 필수입니다.");
				$("#P_CNTN").focus();
				flag = false;
				return false;
			}
			
			$("input[name='vendChk']").each(function(){
				
				if(this.checked) {
					var anncNo = $(this).closest('tr').find('input[name=P_ANNC_NO]').val();
					
					var $input = $('<input>').attr({'name':'P_ANNC_NO','type':'hidden'}).val(anncNo);
					
					$('#popupFrm').append($input);
				}else{
					
				}
			});
			
			if(!flag){
				return false;
			}
		}
		
		$("input[name='P_VEND_REG_NO']").each(function(){
			//alert("P_VEND_REG_NO ==> " + $(this).val());
		});
		
		
		var jsonData = $("#sendFrm").serializeObject();
		var actionUrl = "/vend/vendMailSend.do";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			
			alert("메일이 발송되었습니다.");
			window.opener.returnList();
			
			window.close();
		});
	};
	
	
	// 메일 제목, 내용 작성
	inputData = function(anncNo, bidNm){
		$("#choiceYn").val(anncNo);
		$("#sendDiv").css("display", "");
		$("#P_TTL").val("[공고번호] : " + anncNo + " [공고명] : " + bidNm);	
	};
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2018. 02. 20
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 02. 20.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.chargerListInqire();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.chargerListInqire();			
		});
		
		//발송버튼
		$("#sendBtn").on("click", function() {
			pageObj.selectAnncVendCheck();
		});
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
	};
	

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();

		
		function replaceAll(temp, org, replace){
		    return temp.split(org).join(replace);
		}
		
		var vend_reg_no_list = $("#P_VEND_REG_NO_LIST").val();
		
		var vend_reg_no_list_array = vend_reg_no_list.replace("[", "");
		vend_reg_no_list_array = vend_reg_no_list_array.replace("]", "");
		vend_reg_no_list_array = vend_reg_no_list_array.replace(/ /gi, "");
		
		var vend_reg_no_split = vend_reg_no_list_array.split(',');
		
		var temp = "";
		
		for (var i = 0; i < vend_reg_no_split.length; i++) {
			var real_vend_reg_no = vend_reg_no_split[i];
			
			temp += "<tr>";
	        temp += "<td class='conts01'><input type='text' name='P_VEND_REG_NO' value='" + real_vend_reg_no + "'></td>";
	        temp += "</tr>";
        
        
		}
		
		$("#sendVendRegNo").append(temp);
		
		
	});
})();