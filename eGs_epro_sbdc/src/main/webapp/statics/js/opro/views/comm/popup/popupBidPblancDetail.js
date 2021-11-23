/**
 * 입찰관리 > 나의 입찰공고 상세(구매)
 *
 * <pre>
 * ebid
 *    |_ myBidPblancDetail.js
 * 
 * </pre>
 * @date : 2017.06.19
 * @version : 1.0 
 * @author : 은우소프트 이주연
 */


(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var file = null;
	
	/**  
     * <pre>
     * 1. 개요 : 목록조회 
     * 2. 처리내용 : 
     * 		나의 입찰공고 목록으로 이동 
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2017.06.20
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.20     은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/ebid/myBidPblancList.do"); 
	};
	 
	/**  
     * <pre>
     * 1. 개요 : 작성페이지 
     * 2. 처리내용 : 
     * 		참가신청서 작성 페이지로 이동 
     *  	
     * </pre>
     * @Function Name : reqstdocWritng
     * @date : 2017.06.19
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.19     은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.reqstdocWritng = function() { 
		FwkCmmnUtil.submitForm("RegistFrm", "/opro/ebid/myPartcptReqstdocWritngForm.do"); 
	};
	

	rowDel = function(obj){
		$(obj).parent().remove();
	};
	
	rowAdd = function(fileDiv,fileRow,inputFile,fileName ){
		if($("#"+ fileDiv +" input").length == 0 || $(inputFile).val()){
			var row = $("#"+fileRow).clone();
			row.css("display","");
			row.attr("id","");
			row.find("input").attr("name",fileName);
			inputFile = row.find("input");
			$("#"+fileDiv).append(row);
			inputFile.click();
		}else{
			inputFile.click();
		}
		return inputFile;
	};
	

	fileChange = function(obj){
		$(obj).parent().append($(obj).val().substring($(obj).val().lastIndexOf("\\")+1));
	};
	
	fnInRealFileNameClick = function(obj){
		var oValue= obj.value;
		var realFName= oValue.substring(oValue.lastIndexOf("\\")+1, oValue.length);
		if(oValue != null && oValue != ""){
			$("#fName").val(realFName);
		}
	};
	
	fnInRealFileName = function(obj){
		   var oValue = obj.value;
		   var realFName = oValue.substring(oValue.lastIndexOf("\\")+1, oValue.length);
		   $("#fName").val(realFName);
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 의견정보 toggle
	 * 2. 처리내용 : 
	 * 		+ 버튼 누를시 의견정보 보여주기
	 *  	- 버튼 누를시 의견정보 숨기기
	 * </pre>
	 * @Function Name : opinionInfoToggle
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 03. 10.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.opinionInfoToggle = function(gbn) {
		if(gbn == "show"){
			$("#opinionInfoToggle").css("display", "");
		}else if(gbn == "hide"){
			$("#opinionInfoToggle").css("display", "none");
		}
	};
	
	/**
	  * 1. 개요 : 입찰참가신청서 보기 팝업
	 * 2. 처리내용 : 
	 * 		입찰참가신청서 View
	 * </pre>
	 * @Function Name : popupbidPareView
	 * @date : 2017. 06.20
	 * @author : 은우소프트 이주연
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2017. 06. 20     은우소프트 이주연              최초 작성 
	 *  =======================================================   
	
	 */
	pageObj.popupbidPareView = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "popupbidPareView", "width=800px,height=680px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=220");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/bidPartcptReqstdocPopupInqire.do';
	        this.method = 'POST';
	        this.target = 'popupbidPareView'; 
	    }).trigger("submit");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 공동수급협정서 작성 폼 이동
     * 2. 처리내용 : 
     * 		공동수급작성 폼으로 이동 한다.
     *  	 
     * </pre>
     * @Function Name : copertnSpldmdTrtyOfeWritngForm
     * @date : 2017. 06.21
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017. 06. 21       은우소프트 이주연             최초 작성 
     *  =======================================================   
     */
	pageObj.copertnSpldmdTrtyOfeWritngForm = function() { 
		
		FwkCmmnUtil.submitForm("RegistFrm", "/opro/ebid/myCopertnSpldmdTrtyOfeWritngForm.do");
	};
	
	/**
	  * 1. 개요 : 공동수급협정서 보기 팝업
	 * 2. 처리내용 : 
	 * 		공동수급협정서 View
	 * </pre>
	 * @Function Name : popupbidPareView
	 * @date : 2017. 06.20
	 * @author : 은우소프트 이주연
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2017. 06. 20     은우소프트 이주연              최초 작성 
	 *  =======================================================   
	
	 */
	pageObj.popupcospView = function() {
		$("#cospViewFrm").one("submit", function() {
			window.open("", "cospView", "width=800px,height=580px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=220");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/copertnSpldmdTrtyOfePopupInqire.do';
	        this.method = 'POST';
	        this.target = 'cospView'; 
	    }).trigger("submit");
	};
	
	/**
	  * 1. 개요 : 입찰포기팝업
	 * 2. 처리내용 : 
	 * 	 입찰포기팝업
	 * </pre>
	 * @Function Name : popupBidPartcptAbandn
	 * @date : 2017. 06.20
	 * @author : 은우소프트 이주연
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2017. 06. 20     은우소프트 이주연              최초 작성 
	 *  =======================================================   
	
	 */
	pageObj.popupBidPartcptAbandn = function() {
		$("#bidPartcptAbandnFrm").one("submit", function() {
			window.open("", "bidPartcptAbandnRegist", "width=800px,height=580px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=220");
	        this.method = 'POST'; 
	        this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/bidPartcptAbandnReqstdocRegistForm.do';
	        this.target = 'bidPartcptAbandnRegist'; 
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰공고 의견 등록 팝업 화면 이동
	 * 2. 처리내용 : 
	 * 		입찰공고 의견 등록 팝업 화면으로 이동
	 *  	
	 * </pre>
	 * @Function Name : bidPblancOpinionRegistForm
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 03. 10.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	
	bidPblancOpinionRegistForm = function(bidOpinionNo) {
		$("#opinionPopupFrm").one("submit", function() {
			window.open("", "bidPblancOpinionRegistForm", "width=740px, height=320px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/bidPblancOpinionRegistForm.do';
	        this.method = 'POST';
	        this.target = 'bidPblancOpinionRegistForm';
	    }).trigger("submit");
	};
	
	/**   
	 * <pre>
	 * 1. 개요 : 입찰서 제출 폼 이동
	 * 2. 처리내용 : 
	 * 		입찰서를 제출 폼으로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bipaPresentn
	 * @date : 2017.06.20 
	 * @author : 은우소프트 이주연
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2017. 06 . 20       은우소프트 이주연              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bipaPresentn = function() {
		FwkCmmnUtil.submitForm("bipaPresentnFrm", "/opro/ebid/myBipaPresentnForm.do");
	};
	
	
	pageObj.setEventHandler = function() { 
		
		//첨부파일  
		$("#fileBtn").on("click", function(){
			file = rowAdd("fileDiv","fileRow",file,"P_FILE");
		});

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.listInqire();
		});
		
		//입찰참가신청서 보기
		$("#bidPareViewBtn").on("click", function(){
			pageObj.popupbidPareView();
		});	
		
		// 공동수급협정서제출 버튼
		$("#cospBtn").on("click", function() {
			pageObj.copertnSpldmdTrtyOfeWritngForm();
		});
		
		//공동수급협정서 보기
		$("#cospViewBtn").on("click", function(){
			pageObj.popupcospView();
		});	
		
		//입찰포기신청
		$("#bidPartcptAbandnBtn").on("click", function(){
			pageObj.popupBidPartcptAbandn();
		});	
		
		// 입찰서 제출버튼 클릭
		$("#bipaPresentnBtn").on("click", function(){
			pageObj.bipaPresentn();
		});	
		
		// 참가신청서 작성 
		$("#reqstdocWritngBtn").on("click", function() {
			pageObj.reqstdocWritng();
		});
		
		// 의견정보 보여주기
		$("#showBtn").on("click", function() {
			pageObj.opinionInfoToggle("show");
		});
		// 의견정보 숨기기
		$("#hideBtn").on("click", function() {
			pageObj.opinionInfoToggle("hide");
		});
	};

	/**
	 * window load
	 *
	 */ 
	$(function() {
		if($("#state").val() == '공고중'){
			$("#biddingId").addClass("on");
			
			$("#bidPareViewBtn").css("display","none");
			$("#cospBtn").css("display","none");
			$("#cospViewBtn").css("display","none");
			$("#bipaPresentnBtn").css("display","none");
			$("#bidPartcptAbandnBtn").css("display","none");
		}else if($("#state").val() == '입찰설명회'){
			$("#dcPeoId").addClass("on");
			
			$("#bidPareViewBtn").css("display","none");
			$("#cospBtn").css("display","none");
			$("#cospViewBtn").css("display","none");
			$("#bipaPresentnBtn").css("display","none");
			$("#bidPartcptAbandnBtn").css("display","none");
		}else if($("#state").val() == '참가신청'){
			$("#pareTxtId").addClass("on");
			$("#reqstdocWritngBtn").removeAttr("disabled");
			
			$("#bidPareViewBtn").css("display","none");
			$("#cospBtn").css("display","none");
			$("#cospViewBtn").css("display","none");
			$("#bipaPresentnBtn").css("display","none");
			$("#bidPartcptAbandnBtn").css("display","none");
		}else if($("#state").val() == '입찰서제출'){
			$("#pcOfePresentnTxtId").addClass("on");
			$("#reqstdocWritngBtn").css("display","none");
			
		}else if($("#state").val() == '개찰'){
			$("#opengComptId").addClass("on");
			$("#cospBtn").css("display","none"); 
			$("#bipaPresentnBtn").css("display","none");
			$("#bidPartcptAbandnBtn").css("display","none");
			$("#reqstdocWritngBtn").css("display","none");
		}
		
		pageObj.setEventHandler();
	});
	
})();