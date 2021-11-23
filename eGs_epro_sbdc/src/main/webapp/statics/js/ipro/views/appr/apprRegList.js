(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "listFrm";
	
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
	 * 1. 개요 : 결재자정보 삭제
	 * 2. 처리내용 : 
	 */		
	applAprpDel = function(obj){
		var that = obj;
		if(that.checked){
	    	  $(that).closest('td').find('input[name=P_DEL_AT]').val("Y");
	      }else{
	    	  $(that).closest('td').find('input[name=P_DEL_AT]').val("N");
	      }
	 };
		

	pageObj.updateApplAprp = function() {
		var jsonData = $("#listFrm").serializeObject();
		 
		var actionUrl = "/appr/updateApplAprp.do";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {
			alert("저장되었습니다.");
				FwkCmmnUtil.submitForm("listFrm", "/appr/apprRegList.do"); 
		});
	};
	
	// 결재선 - 결재자정보
	applMstDetail = function (applNo, applNm, applSe){
		$("#listFrm input[name='P_PAGE_NO']").val(1);
		$("#listFrm input[name='P_APPL_NO']").val(applNo);
		$("#listFrm input[name='P_APPL_NM']").val(applNm);
		$("#listFrm input[name='P_APPL_SE']").val(applSe);

		FwkCmmnUtil.submitForm(defaultFrm, "/appr/apprRegList.do");
		
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
	chargerListAdd = function(ccpyCnt, arr_emplNo, arr_userId, arr_usrNm, arr_mail, arr_deptNm, arr_bizNm) {
	
		$(arr_userId).each(function(inx, item){
			// 중복체크
			var flag=true;
			$("input[name='P_APRP_USER_ID']").each(function(){
				if($(this).val() == arr_userId[inx]){
					flag = false;
				}
			});
			
			if(flag == true){
				if(arr_userId[inx] != ""){
					
					var cnt = $('#userFrame').find('tr').length;
					var $tr = $('<tr>').css({'text-align':'center'});
					var $td_number = $('<td>').css({'text-align':'center'}).text(cnt+1);
					var $td_name = $('<td>').css({'text-align':'center'}).text(arr_usrNm[inx]);
					var $td_org = $('<td>').css({'text-align':'left'}).text(arr_deptNm[inx]);
					var $td_flag = $('<td>').css({'text-align':'center'});
					var $td_btn = $('<td>').css({'text-align':'center'});
					var $td_gbn = $('<td>').css({'text-align':'center'});
	 
					var $input_num = $('<input type="hidden">').attr({'name':'P_TR_NUM'}).css({'width':'95%'}).val(cnt+1);
					var $input_name = $('<input type="hidden">').attr({'name':'P_APPR_USER_NM'}).css({'width':'95%'}).val(arr_usrNm[inx]);
					var $input_id = $('<input type="hidden">').attr({'name':'P_APPR_USER_ID'}).val(arr_userId[inx]);;
					var $input_org = $('<input type="hidden">').attr({'name':'P_ORG_NM'}).css({'width':'95%'}).val(arr_deptNm[inx]);
					
					var $input_del_yn = $('<input type="hidden">').attr({'name':'P_DEL_AT'}).css({'width':'95%'}).val('N');
					var $input_aprp_yn = $('<input type="hidden">').attr({'name':'P_APRP_SN'}).css({'width':'95%'}).val('');
					var $input_ord = $('<input type="hidden">').attr({'name':'P_APRP_ORD_SN'}).css({'width':'95%'}).val(cnt+1);
					var $select = $('<select>').attr({'name':'P_APPR_AUCD'}).addClass('component-select w70');
					var $btn = $('<button>').attr({'id':'deleteBtn'}).addClass('component-button-s type-line').text('삭제');
					var $option1 = $('<option value="">').text('선택');
					var $option2 = $('<option value="S">').text('결재');
					var $option3 = $('<option value="A">').text('합의'); 
					var $option4 = $('<option value="C">').text('참조');
					
					$select.append($option1).append($option2).append($option3).append($option4);
					$td_gbn.append($select); 
				
					$td_name.append($input_name).append($input_id).append($input_num).append($input_aprp_yn).append($input_ord);
					$td_org.append($input_org); 
					$td_btn.append($btn).append($input_del_yn);
					
					
					$tr.append($td_number).append($td_gbn).append($td_name).append($td_org).append($td_btn);
					$('#userFrame').append($tr);
				}	
			}
		});
		
	};
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : chargerInqirePopup
	 * @date : 2018. 11. 06.
	 * @author : 은우소프트 
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 11. 6.       은우소프트                     최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerInqirePopup = function() {
		
		if($("#P_APPL_NM").val() == ""){
			alert("결재선명을 선택해주세요.");
			return false;
		}
		
		$("#popupFrm input[name='P_APPL_SE']").val($("#listFrm input[name='P_APPL_SE']").val());
		
		$("#popupFrm").one("submit", function() {
			window.open("", "userPopup", "width=1100px,height=830px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'userPopup';
	    }).trigger("submit");
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
			$(item).find('input[name=P_APRP_ORD_SN]').val(idx+1);
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
		$(that).closest('td').find('input[name=P_DEL_AT]').val("Y");
		
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
		$(that).closest('td').find('input[name=P_DEL_AT]').val("");
	};
	
	
	pageObj.applMstDelete =  function(applNo){
		
		if(!confirm("결재선정보를 삭제하시겠습니까?")){
			return false; 
		}

		pageObj.applMstDelete(applNo);

	};
	 
	pageObj.applMstDelete = function(applNo){
		$("#deleteFrm input[name='P_APPL_NO']").val(applNo);
		var jsonData = $("#deleteFrm").serializeObject();
		var actionUrl = "/appr/applMstDelete.do";
		 
		FwkCmmnUtil.submitAjaxNoLoading(actionUrl, jsonData, function(res) {
			alert("결재선정보가 삭제되었습니다."); 
			$("#listFrm input[name='P_FIRST']").val("Y");
			FwkCmmnUtil.submitForm(defaultFrm, "/appr/apprRegList.do"); 
		});
		
	};
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 내부결재자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : apprInqirePopup
	 * @date : 2018. 11. 06.
	 * @author : 은우소프트 
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 11. 6.       은우소프트                     최초 작성 
	 *  =======================================================   
	 */
	pageObj.apprInqirePopup = function(applSe) {
		
		$("#popupFrm input[name='P_APPL_SE']").val(applSe);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "apprUserPopup", "width=1040px,height=600px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/inApprList.do';
	        this.method = 'POST';
	        this.target = 'apprUserPopup';
	    }).trigger("submit");
	};
	
	//결재선 관리 등록 페이지 이동
	pageObj.apprRegistForm = function(appl_se) {
		$("#detailFrm input[name='P_APPL_SE']").val(appl_se);
		FwkCmmnUtil.submitForm("detailFrm", "/appr/apprRegistForm.do");	
	};
	
	pageObj.apprRegList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/appr/apprRegList.do");	
	};
	
	pageObj.setEventHandler = function() {
		
		// 결재선등록
		$("#registBtn").on("click", function() {
			pageObj.apprRegistForm("I");
			return false;
		});
		
		// 결재자 팝업
		$("#popupBtn").on("click", function() {
			pageObj.chargerInqirePopup();
		});
		
		// 결재자정보 저장
		$("#saveBtn").on("click", function() {
			var flag = true;
			
			if($('#P_APPL_NM').val() == undefined || $('#P_APPL_NM').val() == '' ) {
				alert(FwkMssageUtil.getMessage("COM.INF.008", "결재선명"));
				flag = false;
				$("#P_APPL_NM").focus();
				return false;
			}
			
			if($("input[name='P_APPR_USER_NM']").val() == "" || $("input[name='P_APPR_USER_NM']").val() == undefined) {
				flag = false;
				alert(FwkMssageUtil.getMessage("COM.INF.022", "결재자"));
				return false;
			}
			
			$("select[name='P_APPR_AUCD']").each(function(){
				if($(this).val() == ""){ //결재권한구분
					flag = false;
					alert("결재권한구분을 선택해 주세요.");
					$(this).focus();
					return false;
				}
			});
			
			if(!flag){
				return false;
			}
			
			if(confirm("결재자정보를 저장하시겠습니까?")){
				pageObj.updateApplAprp();
			}
			
		});
			
		//결재선 리스트 조회
		$("#searchBtn").on("click", function() {
			pageObj.apprRegList();		
			return false;
		});
		
		//행삭제버튼
		$(document).on('click', '#deleteBtn', function() {
			pageObj.chargerTrRemove(this);
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {	
		$(document).ready(function(){
			$("#aprp_tb tbody").sortable({
				update : function (enent, ui){
					$(this).children().each(function(index){
						$(this).find('td').first().html(index+1);
						$(this).find('input[name=P_APRP_ORD_SN]').val(index+1);
					});
				}
			});
		});
		
		pageObj.setEventHandler();
	});
	
})();