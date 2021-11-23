/**
 * 메인 > 로그인
 *
 * <pre>
 * main
 *    |_ emgncLoginForm.js
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 9:57:00
 * @version : 1.0
 * @author : 은우소프트 김봉수 
 */

(function() {
	
	pageObj = {};
	
	gotoEstmAnncList = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncList.do");
	};
	
	// 평가공고 상세 이동
	recrAnncDetail = function(estmNo){
		$("#detailFrm input[name='P_ESTM_NO']").val(estmNo);
		FwkCmmnUtil.submitForm("detailFrm", "/opro/recr/recrAnncDetail.do");
	}
	
	// 평가구분에 따른 평가공고 조회
	estmAnncDetail = function(estmSecd){
		
		$("#detailFrm input[name='P_ESTM_SECD_S']").val(estmSecd);
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/opro/main/estmAnncList";
		
		var temp = "";
		
		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			
			$("#estmAnncDiv").empty();
			
			if(res.estmAnncListTotCnt > 0){
				
				for(var i = 0; i < res.estmAnncList.length; i++){
			
					var row = res.estmAnncList[i];
					
					if(row != null){
						temp += "<div class='area-noti' id='' style='cursor: pointer;' onclick='recrAnncDetail(\"" + row.ESTM_NO +"\")'>";
					        temp += "<span class='txt-day'>" + row.REG_DT_F + "</span>";
					        temp += "<div class='txt-title'>" + row.ESTM_NM + "</div>";
					        
					        temp += "<div class='txt-date'>";
					        	temp += "<div class='txt-start'>";
					        		temp += "<span class='txt-date-tag'>시작일</span>";
					        		temp += "<span class='txt-date'>" + row.TOTL_ESTM_ST_DT_F + "</span>";
					        	temp += "</div>";
					        	temp += "<div class='txt-end'>";
				        			temp += "<span class='txt-date-tag'>시작일</span>";
				        			temp += "<span class='txt-date'>" + row.TOTL_ESTM_END_DT_F + "</span>";
				        		temp += "</div>";
				        	temp += "</div>";
				        temp += "</div>";
					}
				}
				$("#estmAnncDiv").append(temp);
				
			}else{
				temp += "<div class='area-noti'>";
					temp += "<span class='txt-day'>&nbsp;</span>";
					temp += "<div class='txt-title'>평가공고가 없습니다.</div>";
				temp += "</div>";
			
				$("#estmAnncDiv").append(temp);
			}
		});
		
	};

	detailInqire = function(bbsSecd, bbsSn){
		$("#notiDetailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#notiDetailFrm input[name='P_BBS_SN']").val(bbsSn);
		$("#notiDetailFrm").one("submit", function() {
			window.open("", "noticePopup", "width=950px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/noticePopup.do';
	        this.method = 'POST';
	        this.target = 'noticePopup';
	    }).trigger("submit");
	};
	
	notiList = function(bbsSecd){
		$("#notiDetailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#notiDetailFrm").one("submit", function() {
			window.open("", "noticeListPopup", "width=950px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/noticeListPopup.do';
			this.method = 'POST';
			this.target = 'noticeListPopup';
		}).trigger("submit");
	};
	
	//메뉴얼 다운로드
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/opro/main/userManual.do");
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
		
//		window.close();
		if(!confirm("로그아웃 하시겠습니까?")){
			return false;
		}
		
		var actionUrl = "/opro/main/logout.do";
		var jsonParam = $("#logOutFrm").serializeObject();
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
			, function(res) {
				FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
			}
		);
		//FwkCmmnUtil.submitForm("logOutFrm","/main/emgncEmplyrLogout.do");
	};
	
	emplyrLogin = function() {
		FwkCmmnUtil.submitForm("loginFrm", "/opro/main/loginPage.do");  
	};
	
	pageObj.login = function(first){
		if( $("#P_LOGIN_ID_VIEW").val() == "" ){
			alert("사업자번호를 입력해 주세요.");
			$("#P_LOGIN_ID_VIEW").focus();
			return;
		}
	};
	
	// 주민등록번호 로그인
	pageObj.rsdn_no_login = function(){
		if( $("#P_RSDN_NO_1").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_1").focus();
			return;
		}
		if( $("#P_RSDN_NO_2").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_2").focus();
			return;
		}
		
		var rsdn_no_1 = $("#P_RSDN_NO_1").val();
		var rsdn_no_2 = $("#P_RSDN_NO_2").val();
		
		$("#P_LOGIN_ID").val(rsdn_no_1 + "" + rsdn_no_2);
		
		var actionUrl = "/opro/main/loginByRsdnNo.do";
        var jsonParam = $("#loginFrm").serializeObject(); 
        FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(data) {
        	if(data.code != "success") {
        		alert("["+data.code+"]"+data.msg);
    		} else {
    			
    			// 쿠키 저장할지 체크			
    			pageObj.emplyrLogin(); 
    		}
        });
        
	};
	
	// 브라우저인증서 로그인
	pageObj.rsdn_no_login_browser = function(){
		if( $("#P_RSDN_NO_1").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_1").focus();
			return;
		}
		if( $("#P_RSDN_NO_2").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_2").focus();
			return;
		}
		
		pageObj.loginDataBrower();
	};
	
	// 브라우저인증서 로그인데이터 생성
	pageObj.loginDataBrower = function(){
		var RSDN_NO;
		
		var rsdn_no_1 = $("#P_RSDN_NO_1").val();
		var rsdn_no_2 = $("#P_RSDN_NO_2").val();
		
		RSDN_NO = rsdn_no_1 + "" + rsdn_no_2;
		$("#P_LOGIN_ID").val(RSDN_NO);
		
		
		var verifyVID = $("#verifyVID").is(":checked");
        var options = {};

        var loginDataKmCert = $("#loginDataKmCert").val();
        options.loginDataKmCert = loginDataKmCert;

        var sessionId = RSDN_NO;
		var ssn = RSDN_NO;
		var userInfo = RSDN_NO;

        //if(verifyVID == true) {
            if(ssn != "0") {
                options.ssn = ssn;
            }
            //else {
            //    alert("ssn에 입력된 값이 알맞지 않아 신원확인을 수행하지 않습니다.");
            //}
        //}
        tsHTML5PKI.loginData(sessionId, ssn, userInfo, options, pageObj.loginData_complete_callback);
		
		
	};
	
	pageObj.loginData_complete_callback = function(res){
		
		//alert("res.data.certInfo.issuerDN ==> " + res.data.certInfo.issuerDN);
		//alert("res.data.certInfo.certPolicy ==> " + res.data.certInfo.certPolicy);
		//alert("res.data.certInfo.subjectDN ==> " + res.data.certInfo.subjectDN);
		
        if (res.code == 0) {
        	$("#loginData").val(res.data.loginData);
        	$("#P_LOGIN_DN").val(res.data.certInfo.subjectDN);
        	$("#P_CERT_POLICY").val(res.data.certInfo.certPolicy);
        	
//        	$("#signCertInfo").append(objectToTable("",res.data.certInfo));
        	
        	var jsonData = $("#loginFrm").serializeObject(); 
            var actionUrl = "/opro/main/rsdnNoLoginCeck";
        	
            FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(data) {
              	
              	//alert("data ==> " + JSON.stringify(data));
              	//alert("data.resultCode ==> " + data.resultCode);
              	
              	if(data.resultCode == "success"){
              		alert("신원확인에 성공하였습니다.");
              		var jsonData = $("#loginFrm").serializeObject(); 
                      var actionUrl = "/opro/main/loginByRsdnNo.do";
                      
                      FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(data) {
                      	if(data.code != "success") {
                      		alert("["+data.code+"]"+data.msg);
                  		} else {
                  			pageObj.emplyrLogin(); 
                  		}
                      });
                      
              	}else{
              		alert("신원확인에 실패하였습니다.");
              		return false;
              		//FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
              	}
              });
        	
         }
        else{
        	alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
	};
	
	// 공동인증서 로그인
	pageObj.rsdn_no_login_asso = function(){
		
		if( $("#P_RSDN_NO_1").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_1").focus();
			return;
		}
		if( $("#P_RSDN_NO_2").val() == "" ){
			alert("주민등록번호를 입력해 주세요.");
			$("#P_RSDN_NO_2").focus();
			return;
		}
		
		pageObj.loginDataAsso();
		
	};
	
	// 공동인증서 로그인데이터 생성
	pageObj.loginDataAsso = function(){
		
		var RSDN_NO;
		
		var rsdn_no_1 = $("#P_RSDN_NO_1").val();
		var rsdn_no_2 = $("#P_RSDN_NO_2").val();
		
		RSDN_NO = rsdn_no_1 + "" + rsdn_no_2;
		$("#P_LOGIN_ID").val(RSDN_NO);
		
		var verifyVID = $("#verifyVID").is(":checked");
		var options = {};

		var sessionId = RSDN_NO;
		var ssn = RSDN_NO;
		var userInfo = RSDN_NO;

		if (verifyVID == true) {
			if (ssn != "0") {
				options.ssn = ssn;
			} else {
				alert("ssn에 입력된 값이 알맞지 않아 신원확인을 수행하지 않습니다.");
			}
		}
		
		nxTSPKIConfig.options.loginDataKmCert = $("#loginDataKmCert").val();
		nxTSPKIConfig.options.initPolicies = "1 2 410 200005 1 1 4:은행 개인|1 2 410 200004 5 2 1 2:1등급(개인)|1 2 410 200004 5 1 1 5:개인범용|1 2 410 200005 1 1 1:개인범용|1 2 410 200004 5 4 1 1:개인범용|1 2 410 200012 1 1 1:개인범용|1 2 410 200004 5 5 1 1:범용개인|";
		
		nxTSPKI.loginData(sessionId, ssn, userInfo, options, pageObj.login_complete_callback);
	};
	
	
	pageObj.login_complete_callback = function(res){
		//alert("res.data.loginData ==> " + res.data.loginData);
		//alert("res.data.cert ==> " + res.data.certInfo.cert);
		//alert("res.data.certPath ==> " + res.data.certInfo.certPath);
		//alert("res.data.issuerDN ==> " + res.data.certInfo.issuerDN);
		//alert("res.data.certPolicy ==> " + res.data.certInfo.certPolicy);
		//alert("res.data.subjectDN ==> " + res.data.certInfo.subjectDN);
        if (res.code == 0) {
        	$("#loginData").val(res.data.loginData);
        	$("#P_LOGIN_DN").val(res.data.certInfo.subjectDN);
        	$("#P_CERT_POLICY").val(res.data.certInfo.certPolicy);
//        	$("#signCertInfo").append(objectToTable("",res.data.certInfo));
            
        	var jsonData = $("#loginFrm").serializeObject(); 
            var actionUrl = "/opro/main/rsdnNoLoginCeck"; 
            
            FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(data) {
            	
            	//alert("data ==> " + JSON.stringify(data));
            	//alert("data.resultCode ==> " + data.resultCode);
            	
            	if(data.resultCode == "success"){
            		alert("신원확인에 성공하였습니다.");
            		var jsonData = $("#loginFrm").serializeObject(); 
                    var actionUrl = "/opro/main/loginByRsdnNo.do";
                    
                    FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(data) {
                    	if(data.code != "success") {
                    		alert("["+data.code+"]"+data.msg);
                		} else {
                			pageObj.emplyrLogin(); 
                		}
                    });
                    
            	}else{
            		alert("신원확인에 실패하였습니다.");
            		FwkCmmnUtil.submitForm("logOutFrm","/opro/main/emgncLoginForm.do");
            	}
            });
         }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }
	
	
	pageObj.login_data_complete_callback = function(res) {
		if (res.signature != "") {
			$("#loginData").val(res.signature);
			
	        var actionUrl = "/opro/main/login.do";
	        var jsonParam = $("#loginFrm").serializeObject(); 
	        FwkCmmnUtil.submitAjax(actionUrl, jsonParam, function(data) {
	        	if(data.code != "success") {
	        		alert("["+data.code+"]"+data.msg);
	    		} else {
	    			
	    			// 쿠키 저장할지 체크
	    			//pageObj.cookiCheck();	    			
	    			pageObj.emplyrLogin(); 
	    		}
	        });			
		}else {
//	        alert("error code = " + res.code + ", message = " + res.errorMessage);
			alert("서명에 실패했습니다.");
			return ;
	    }
	};
	
	pageObj.success_callback = function(res) {
		alert("에러");
	};
	
	pageObj.error_callback = function(error) {
		if(error.code != -9999){ // 취소 버튼 이벤트 error.code: -9999
			alert(error.msg);
		}  
	};
	
	pageObj.cookiCheck = function() {
		if($("#P_LOGIN_ID_COO").is(":checked")){
			if(getCookie("userInputId") != $("#P_LOGIN_ID").val()){
				setCookie("userInputId", $("#P_LOGIN_ID").val(), 7);
			}
		}else{
			setCookie("userInputId", $("#P_LOGIN_ID").val(), -1);
		}
	};
	
	setCookie = function(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	};
	
	getCookie = function(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	};	
	
	// 로그인 성공 후 화면 호출
	pageObj.emplyrLogin = function() {		
		$("#loginFrm input[name='resourceName']").val("OEP15001");
		FwkCmmnUtil.submitForm("loginFrm", "/opro/estm/estmCmtmProgList.do");
	};
	
	// 2021-04-14 추가 : WebCube 설치여부
	pageObj.isWebCubeInstall = function() {
	
	        var result = false;
	        try
	        { 
	               var WebCube_URL = "http://127.0.0.1:4567/";
	               if(location.protocol.substring(4,5) == "s")
	              WebCube_URL = "https://127.0.0.1:4568/";   

	               var SetupCheck = new XMLHttpRequest();
	               SetupCheck.open("POST",WebCube_URL + "WebCube/Version" ,false);
	               SetupCheck.onreadystatechange = function()
	               {
	                       if (SetupCheck.readyState == 4 && SetupCheck.status == 200)
	                       {
	                              if('1.1.7.8' <= SetupCheck.responseText)
	                              {
	                                      result = true;
	                              }
	                              else
	                              {
	                                      result = false;
	                              }
	                        } 
	                        else if (SetupCheck.readyState == 4 && (SetupCheck.status == 0 || SetupCheck.status >= 200)) 
	                        {
	                              result = false;
	                       }
	               };
	               SetupCheck.send();
	        }
	        catch(e)
	        {              
	        }
	        
	        if(result){
	        	//ActiveX 가 설치되어 있다는 뜻임.
	        }else{
	        	//ActiveX 가 설치필요
	        	setBlock("", 3);
	        }
	}
	
	
	pageObj.setEventHandler = function() { 
		
		// 주민등록번호 로그인
		$("#rsdnNoLoginBtn").on("click", function() {
			$("#P_LOGIN_GBN").val("CMTM");
			$("#P_LOGIN_MTHD").val("RSDNNO");
			pageObj.rsdn_no_login();
			return false;
		});
		
		// 공동인증서 로그인
		$("#assoLoginBtn").on("click", function() {
			$("#P_LOGIN_GBN").val("CMTM");
			$("#P_LOGIN_MTHD").val("ASSO");
			pageObj.rsdn_no_login_asso();
			return false;
		});
		
		// 브라우저인증서 로그인
		$("#browserLoginBtn").on("click", function() {
			$("#P_LOGIN_GBN").val("CMTM");
			$("#P_LOGIN_MTHD").val("BROWSER");
			pageObj.rsdn_no_login_browser();
			return false;
		});
		
		
		FwkCmmnUtil.setEnterKeyBinding("loginFrm", ["P_LOGIN_ID_VIEW"], function(res) {
			pageObj.login("H");
		});
		
	};
	
	helpPopup = function() {
		$("#notiDetailFrm").one("submit", function() {
			window.open("", "helpPopup", "width=600px,height=350px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/main/popup/helpPopup.do';
	        this.method = 'POST';
	        this.target = 'helpPopup';
	    }).trigger("submit");
	};
	

	/**
	 * window load
	 *
	 */
	$(function() {
		
		helpPopup();
		
		pageObj.setEventHandler();
		
			$('.slider-nav').slick({
		        dots: false,
		        infinite: false,
		        centerMode: false,
		        slidesToShow: 1,
		        slidesToScroll: 1,
		        variableWidth: true
			});
		
		   $('.slick-track .txt-rolling').click(function(){
		       var $rollingIdx = $(this).index();
		       $(this).addClass('is-selected');
		       $(this).siblings().removeClass('is-selected');
		       $('.ui-noti').eq($rollingIdx).show();
		       $('.ui-noti').eq($rollingIdx).siblings().hide();
		   });
		   
		   $('.list-login-tab li').click(function(){
		       var $tabIdx = $(this).index();
		       $(this).addClass('is-selected');
		       $(this).siblings().removeClass('is-selected');
		       $('.tab-contents').eq($tabIdx).show();
		       $('.tab-contents').eq($tabIdx).siblings().hide();
		});

		   // WebCube 설치여부 확인
		   // 캡처방지 개발을 위해서 임시주석처리
//		   pageObj.isWebCubeInstall();
		   
		 //초기화 함수 필수
	    $(document).ready(function(){
	        nxTSPKI.onInit(function(){ 
				//nxTSPKI.init 함수 완료 후 실행해야 하는 함수나 기능 작성 위치
				//alert("Init 완료");
			});

			nxTSPKI.init(true);
			tsHTML5PKI.init();
	    });
	    
       
	});
	
	
	
})();