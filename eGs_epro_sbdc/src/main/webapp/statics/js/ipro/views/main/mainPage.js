(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	mainPage = function(){
		FwkCmmnUtil.submitForm("menuLeftMoveFrm", "/main/mainPage.do" );
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
		var actionUrl = "/main/emgncEmplyrLogout.do"; 
		var jsonParam = $("#logOutFrm").serializeObject();
		FwkCmmnUtil.submitAjax (actionUrl, jsonParam
			, function(res) {
				FwkCmmnUtil.submitForm("logOutFrm","/main/emgncLoginForm.do");
			}
		);   
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
	
	// 페이지 이동
	movePage = function(estmNo, estmPscd, estmSecd) {
		$("#estmDetailFrm input[name='P_ESTM_NO']").val(estmNo);
		if(estmSecd == "E006"){
			if(estmPscd == "C001"){		//완료 페이지
				$("#estmDetailFrm input[name='resourceName']").val("IEP1500702");
				FwkCmmnUtil.submitForm("estmDetailFrm", "/estm/cmtmMngCmplDetail.do" );
			}else{ 		//진행 페이지
				$("#estmDetailFrm input[name='resourceName']").val("IEP1500701");
				FwkCmmnUtil.submitForm("estmDetailFrm", "/estm/cmtmMngProgDetail.do" );
			}
		}else{
			if(estmPscd == "C001"){		//완료 페이지
				$("#estmDetailFrm input[name='resourceName']").val("IEP15002");
				FwkCmmnUtil.submitForm("estmDetailFrm", "/estm/estmCmplDetail.do" );
			}else{ 		//진행 페이지
				$("#estmDetailFrm input[name='resourceName']").val("IEP15001");
				FwkCmmnUtil.submitForm("estmDetailFrm", "/estm/estmProgDetail.do" );
			}
		}
	};
	
	//평가진행상세
	estmMainList = function(estmPscd){
		$("#searchFrm input[name='P_ESTM_PSCD']").val(estmPscd);
		
		FwkCmmnUtil.submitForm("searchFrm", "/main/mainPage.do" );
		/*var actionUrl = "/main/mainEstmList";
	
		FwkCmmnUtil.submitAjax (actionUrl, param
				, function(res) {
					var html = "";
					
					if(!FwkCmmnUtil.isNull(res.estmMainList)){
						
						var list = res.estmMainList;
						
						$.each(list, function(inx, item){
							html += "<h2>";
							html += item.ESTM_NM;
							html += "<span class='date'>등록일자 : " + item.REG_DT_F, + "</span>";
							html += "</h2>";	
							html += "</h2>";	
							html += "<ul class='flow'>";
							if(item.ESTM_PSCD == "A001"){
								html += "<li><span class='active' onclick='movePage(\"" + item.ESTM_NO + "\", \"" + item.ESTM_PSCD + "\")' style='cursor:pointer;'>평가작성</span></li>";
							}else{
								html+= "<li><span>평가작성</span></li>";
							}
							if(item.ESTM_PSCD == "A002"){
								html += "<li><span class='active' onclick='movePage(\"" + item.ESTM_NO + "\", \"" + item.ESTM_PSCD + "\")' style='cursor:pointer;'>평가공고</span></li>";
							}else{
								html+= "<li><span>평가공고</span></li>";
							}
							if(item.ESTM_PSCD == "A003" || item.ESTM_PSCD == "A0031" || item.ESTM_PSCD == "A004" || item.ESTM_PSCD == "A0041"){
								html += "<li><span class='active' onclick='movePage(\"" + item.ESTM_NO + "\", \"" + item.ESTM_PSCD + "\")' style='cursor:pointer;'>평가위원순위선정</span></li>";
							}else{
								html+= "<li><span>평가위원순위선정</span></li>";
							}
							if(item.ESTM_PSCD == "A005"){
								html += "<li><span class='active' onclick='movePage(\"" + item.ESTM_NO + "\", \"" + item.ESTM_PSCD + "\")' style='cursor:pointer;'>평가중</span></li>";
							}else{
								html+= "<li><span>평가중</span></li>";
							}
							if(item.ESTM_PSCD == "C001"){
								html += "<li><span class='active' onclick='movePage(\"" + item.ESTM_NO + "\", \"" + item.ESTM_PSCD + "\")' style='cursor:pointer;'>평가종료</span></li>";
							}else{
								html+= "<li><span>평가종료</span></li>";
							}
							html += "</ul>";
							html += "<ul class='flow_tip'>";
							html += "<li></li>";
							html += "<li>평가시작일시 : " + item.TOTL_ESTM_ST_DT_F + "</li>";
							html += "<li></li>";
							html += "<li>평가종료일시 : " + item.TOTL_ESTM_END_DT_F + "</li>";
							html += "<li></li>";
							html += "</ul>";
						});
					}else{
						html += "<h2>해당 상태의 평가진행정보가 없습니다.</h2>";
					}
					$("#estmList").html(html);
				}
			);	*/
		
	}
	
	//파일 다운로드
	download = function(fileSn, fileGrpNo) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	//목록화면
	notiList = function(bbsSecd){
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		if(bbsSecd == "NOTI"){
			$("#detailFrm input[name='resourceName']").val("IEP12001");
			FwkCmmnUtil.submitForm("detailFrm", "/noti/notiList.do");
		}else if(bbsSecd == "FAQ"){
			$("#detailFrm input[name='resourceName']").val("IEP12002");
			FwkCmmnUtil.submitForm("detailFrm", "/noti/faqList.do");
		}
	}
	
	//공지 상세화면
	detailInqire = function(bbsSecd, bbsSn){
		$("#detailFrm input[name='P_BBS_SECD']").val(bbsSecd);
		$("#detailFrm input[name='P_BBS_SN']").val(bbsSn);
		
		if(bbsSecd == "NOTI"){
			$("#detailFrm input[name='resourceName']").val("IEP12001");
			FwkCmmnUtil.submitForm("detailFrm", "/noti/notiDetail.do");
		}else if(bbsSecd == "FAQ"){
			$("#detailFrm input[name='resourceName']").val("IEP12002");
			FwkCmmnUtil.submitForm("detailFrm", "/noti/faqDetail.do");
		}
	}
	
	//manual 다운로드
	downloadManual = function(){
		FwkCmmnUtil.submitForm("manualFrm", "/comm/userManual.do");
	}
	
	pageObj.setEventHandler = function() {
		// 조회버튼
		$("#searchBtn").on("click", function() {
			FwkCmmnUtil.submitForm(defaultFrm, "/main/mainPage.do");
		});
	};  
	
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		$('.list-login-tab li').click(function(){
            var $tabIdx = $(this).index();
            $(this).addClass('is-selected');
            $(this).siblings().removeClass('is-selected');
            $('.tab-contents').eq($tabIdx).show();
            $('.tab-contents').eq($tabIdx).siblings().hide();
		})
		
	});
	
})();