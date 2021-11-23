(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "listFrm";
	
	movePage = function(url) {
		//FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	/**
	 * 1.개요 : SG설명 삭젱 이벤트
	 * 2.처리내용 : 
	 */
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/info/infoApprlineList.do");
	};
	
	/**
	 * 1.팝업에서 가져온 유저정보 셋팅.
	 */
	pageObj.setUserFn = function(dept,pos,name) {
		var tr = $("#copyTr").clone();
		tr.css("display","");
		tr.find("input[name='dept']").val(dept);
		tr.find("input[name='pos']").val(pos);
		tr.find("input[name='name']").val(name);
		$("#userFrame").append(tr);
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : chargerInqirePopup
	 * @date : 2018. 02. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 02. 26.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerInqirePopup = function(searchGbnId) {
		
		$("#popupFrm input[name='searchGbnId']").val(searchGbnId);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "userPopup", "width=740px,height=650px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=250");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'userPopup';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 내부결재자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : apprInqirePopup
	 * @date : 2018. 02. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 02. 26.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.apprInqirePopup = function(searchGbnId) {
		
		//$("#popupFrm input[name='searchGbnId']").val(searchGbnId);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "apprUserPopup", "width=740px,height=650px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=250");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/inApprList.do';
	        this.method = 'POST';
	        this.target = 'apprUserPopup';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 정보 설정
	 * 2. 처리내용 : 
	 * 		- 팝업화면에서 선택된 담당자 정보를 설정한다.
	 * </pre>
	 * @Function Name : chargerListAdd
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */	
	chargerListAdd = function(orgCd, orgNm, userId, userNm, mail, comTelNo, posiNm) {
		var cnt = $('#userFrame').find('tr').length;
		
		var $tr = $('<tr>').css({'text-align':'center'});
		var $td_number = $('<td>').css({'text-align':'center'}).text(cnt+1);
		var $td_name = $('<td>').css({'text-align':'center'}).text(userNm);
		var $td_org = $('<td>').css({'text-align':'center'}).text(orgNm);
		var $td_posi = $('<td>').css({'text-align':'center'}).text(posiNm);
		var $td_flag = $('<td>').css({'text-align':'center'});
		var $td_btn = $('<td>').css({'text-align':'center'});
		
		var $input_num = $('<input type="hidden">').attr({'name':'P_TR_NUM'}).css({'width':'95%'}).val(cnt+1);
		var $input_name = $('<input type="hidden">').attr({'name':'P_EMPL_NM'}).css({'width':'95%'}).val(userNm);
		var $input_id = $('<input type="hidden">').attr({'name':'P_EMPL_NO'}).val(userId);;
		var $input_org = $('<input type="hidden">').attr({'name':'P_ORG_NAME'}).css({'width':'95%'}).val(orgNm);
		var $input_posi = $('<input type="hidden">').attr({'name':'P_POSI_NAME'}).css({'width':'95%'}).val(posiNm);
		var $input_sn = $('<input type="hidden">').attr({'name':'P_APRP_SN'}).css({'width':'95%'}).val('');
		var $input_del_yn = $('<input type="hidden">').attr({'name':'P_DEL_YN'}).css({'width':'95%'}).val('');
		var $select = $('<select>').attr({'name':'P_APPR_AUCD'});
		var $btn = $('<button>').attr({'id':'deleteBtn'}).addClass('btn btn_s2 btn_c2').text('삭제');
		var $option1 = $('<option value="C">').text('승인');
		var $option2 = $('<option value="A">').text('합의');
		
		$select.append($option1).append($option2);
		
		$td_name.append($input_name).append($input_id).append($input_num).append($input_sn);
		$td_org.append($input_org);
		$td_posi.append($input_posi);
		$td_flag.append($select);
		$td_btn.append($btn).append($input_del_yn);
		$tr.append($td_number).append($td_name).append($td_org).append($td_posi).append($td_flag).append($td_btn);
		$('#userFrame').append($tr);
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 정보 행 삭제
	 * 2. 처리내용 : 
	 * 		- 팝업화면에서 선택된 담당자 정보를 삭제한다.
	 * </pre>
	 * @Function Name : chargerTrRemove
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */		
	pageObj.chargerTrRemove = function(obj) {
		var that = obj;
		$(that).closest('tr').remove();
		var $tr_lst = $('#userFrame').find('tr');
		$.each($tr_lst, function(idx, item) {
			$(item).find('input[name=P_TR_NUM]').val(idx+1);
			$(item).find('td').eq(0).text(idx+1)
		});
	};	
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 정보 행 삭제
	 * 2. 처리내용 : 
	 * 		- 팝업화면에서 선택된 담당자 정보를 삭제대기한다
	 * </pre>
	 * @Function Name : chargerTrRemove
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */		
	chargerTrRemoveWait = function(obj) {
		var that = obj;
		$(that).text("삭제취소").attr({'onclick':'chargerTrRemoveCancel(this);'});
		$(that).closest('td').find('input[name=P_DEL_YN]').val("Y");
		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 정보 행 삭제 취소
	 * 2. 처리내용 : 
	 * 		- 팝업화면에서 선택된 담당자 정보를 삭제 취소한다
	 * </pre>
	 * @Function Name : chargerTrRemoveCancle
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */		
	chargerTrRemoveCancel = function(obj) {
		var that = obj;
		$(that).text("삭제").attr({'onclick':'chargerTrRemoveWait(this);'});
		$(that).closest('td').find('input[name=P_DEL_YN]').val("");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 결재선정보 등록
	 * 2. 처리내용 : 
	 * 		- 결재선 정보를 등록한다.
	 * </pre>
	 * @Function Name : saveApplLine
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */		
	
	pageObj.saveApplLine = function() {
		var jsonData = $("#dataFrm").serializeObject();  
		var actionUrl = "/info/infoApprlineUpdate.do";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {	
				FwkCmmnUtil.submitForm("searchFrm", "/info/infoApprlineList.do"); 
		});
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 결재선정보 삭제
	 * 2. 처리내용 : 
	 * 		- 결재선 정보를 삭제한다.
	 * </pre>
	 * @Function Name : applLineDelete
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 09.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */		
	
	pageObj.applLineDelete = function() {
		var jsonData = $("#dataFrm").serializeObject();  
		var actionUrl = "/info/infoApprlineDelete.do";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {	
			FwkCmmnUtil.submitForm("searchFrm", "/info/infoApprlineList.do"); 
		});
	};
	
	pageObj.setEventHandler = function() {
		
		//사용자 조회 팝업
		$("#popupBtn").on("click", function() {
			pageObj.chargerInqirePopup();
		});
		
		//내부결재자 조회 팝업
		$("#inPopupBtn").on("click", function() {
			pageObj.apprInqirePopup();
		});
		
		//결재선정보 등록
		$("#saveBtn").on("click", function() {
			var $tr_lst_length = $('#userFrame').find('tr').length;
			if($tr_lst_length == undefined || $tr_lst_length == 0) {
				alert(FwkMssageUtil.getMessage("COM.INF.022", "결재자"));
				return false;
			}
			
			if(confirm(FwkMssageUtil.getMessage("COM.CON.001", "저장"))){
				pageObj.saveApplLine();
			}
		});
		
		$("#cancelBtn").on("click", function() {
			movePage("/info/infoApprlineList.do");
		});

		//행삭제버튼
		$(document).on('click', '#deleteBtn', function() {
			pageObj.chargerTrRemove(this);
		});
		
		//전체삭제버튼
		$('#delBtn').on('click', function() {
			pageObj.applLineDelete();
		});
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.listInqire();
		});
		
	};

	/**
	 * window load
	 *
	 */ 
	$(function() {		
		pageObj.setEventHandler();
	});
	
})();