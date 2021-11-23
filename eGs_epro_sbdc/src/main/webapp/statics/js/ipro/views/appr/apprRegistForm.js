(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	// 목록
	pageObj.apprRegList = function(){
		FwkCmmnUtil.submitForm("listFrm", "/appr/apprRegList.do");
	};
	

	/**
	 * 1.개요 : SG설명 삭젱 이벤트
	 * 2.처리내용 :  
	 */
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
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
			window.open("", "userPopup", "width=1100px,height=830px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'userPopup';
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
	chargerListAdd2 = function(deptNm, usrId, usrNm, mail, telNo) {
		var cnt = $('#userFrame').find('tr').length;
		
		var $tr = $('<tr>').css({'text-align':'center'});
		var $td_number = $('<td>').css({'text-align':'center'}).text(cnt+1);
		var $td_name = $('<td>').css({'text-align':'center'}).text(usrNm);
		var $td_org = $('<td>').css({'text-align':'center'}).text(deptNm);

		var $td_flag = $('<td>').css({'text-align':'center'});
		var $td_btn = $('<td>').css({'text-align':'left'});

		
		var $input_num = $('<input type="hidden">').attr({'name':'P_TR_NUM'}).css({'width':'95%'}).val(cnt+1);
		var $input_name = $('<input type="hidden">').attr({'name':'P_APPR_USER_NM'}).css({'width':'95%'}).val(usrNm);
		var $input_id = $('<input type="hidden">').attr({'name':'P_APPR_USER_ID'}).val(usrId);;
		var $input_org = $('<input type="hidden">').attr({'name':'P_DEPT_NM'}).css({'width':'95%'}).val(deptNm);
		var $input_ord = $('<input type="hidden">').attr({'name':'P_APRP_ORD_SN'}).css({'width':'95%'}).val(cnt+1);
		var $btn = $('<button>').attr({'id':'deleteBtn'}).addClass('component-button-s type-line').text('삭제');
		
		$td_name.append($input_name).append($input_id).append($input_num).append($input_ord);
		$td_org.append($input_org);

		$td_btn.append($btn);

		$tr.append($td_number).append($td_name).append($td_org).append($td_btn);
		$('#userFrame').append($tr);
	};
	
	chargerListAdd = function(ccpyCnt, arr_usr_id,  arr_userId, arr_usrNm, arr_dept_cd, arr_deptNm, arr_ofps_cd, arr_ofps_nm, arr_telNo, arr_mail, chargerGbn){
		
		$(arr_userId).each(function(inx, item){
			// 중복체크
			var flag=true;
			$("input[name='P_APPR_USER_ID']").each(function(){
				if($(this).val() == arr_userId[inx]){
					flag = false;
				}
			});

			if(flag == true){
				if(arr_userId[inx] != ""){
					
					var cnt = $('#userFrame').find('tr').length;
					var $tr = $('<tr>').css({'text-align':'center'}).css({'cursor':'move'});
					var $td_number = $('<td>').addClass('no').css({'text-align':'center'}).text(cnt+1);
					var $td_name = $('<td>').css({'text-align':'center'}).text(arr_usrNm[inx]);
					var $td_org = $('<td>').css({'text-align':'left'}).text(arr_deptNm[inx]);
					var $td_flag = $('<td>').css({'text-align':'center'});
					var $td_btn = $('<td>').css({'text-align':'center'});   // 버튼
					var $td_gbn = $('<td>').css({'text-align':'center'});
	
					var $input_num = $('<input type="hidden">').attr({'name':'P_TR_NUM'}).css({'width':'95%'}).val(cnt+1);
					var $input_name = $('<input type="hidden">').attr({'name':'P_APPR_USER_NM'}).css({'width':'95%'}).val(arr_usrNm[inx]);
					var $input_id = $('<input type="hidden">').attr({'name':'P_APPR_USER_ID'}).val(arr_userId[inx]);;
					var $input_org = $('<input type="hidden">').attr({'name':'P_ORG_NM'}).css({'width':'95%'}).val(arr_deptNm[inx]);
					var $input_ord = $('<input type="hidden">').attr({'name':'P_APRP_ORD_SN'}).css({'width':'95%'}).val(cnt+1);
					var $select = $('<select>').attr({'name':'P_APPR_AUCD'}).addClass('component-select');
					var $btn = $('<button>').attr({'id':'deleteBtn'}).addClass('component-button-s type-line').text('삭제');
					var $option1 = $('<option value="">').text('선택');
					var $option2 = $('<option value="S">').text('결재');
					var $option3 = $('<option value="A">').text('합의');
					var $option4 = $('<option value="C">').text('참조');
					
					$select.append($option1).append($option2).append($option3).append($option4);
					$td_gbn.append($select);
					
					$td_name.append($input_name).append($input_id).append($input_num).append($input_ord);
					$td_org.append($input_org);
					$td_btn.append($btn);
					
					
					$tr.append($td_number).append($td_gbn).append($td_name).append($td_org).append($td_btn);
					$('#userFrame').append($tr);
				}	
			}
		});
		
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
	
	pageObj.chargerTrMove = function(obj){
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
	 * 1. 개요 : 결재선정보 등록
	 * 2. 처리내용 : 
	 * 		- 결재선 정보를 등록한다.
	 * </pre>
	 */		
	pageObj.apprRegist = function() {
		var jsonData = $("#registFrm").serializeObject();  
		var actionUrl = "/appr/apprRegist";
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function() {	
				FwkCmmnUtil.submitForm("listFrm", "/appr/apprRegList.do"); 
		});
	};
	
	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록으로 이동하시겠습니까?")){ 
				return false;
			}
			pageObj.apprRegList();
		});

		//사용자 조회 팝업
		$("#popupBtn").on("click", function() {
			pageObj.chargerInqirePopup();
		});
		
		//결재선정보 등록
		$("#saveBtn").on("click", function() {
			var flag = true;
			
			if($('#P_APPL_NM').val() == undefined || $('#P_APPL_NM').val() == '' ) {
				alert(FwkMssageUtil.getMessage("COM.INF.008", "결재선명"));
				flag = false;
				$("#P_APPL_NM").focus();
				return false;
			}
			var $tr_lst_length = $('#userFrame').find('tr').length;
			if($tr_lst_length == undefined || $tr_lst_length == 0) {
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
			
			
			if(confirm("결재선을 등록합니다.")){
				pageObj.apprRegist();
			}
		});

		//행삭제버튼
		$(document).on('click', '#deleteBtn', function() {
			pageObj.chargerTrRemove(this);
		});
		
		//행삭제버튼
		$(document).on('click', '#upBtn', function() {
			pageObj.chargerTrMove(this);
			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding("dataFrm", ["P_APPL_NM"], function() {
			var flag = true;
			
			if($('#P_APPL_NM').val() == undefined || $('#P_APPL_NM').val() == '' ) {
				alert(FwkMssageUtil.getMessage("COM.INF.008", "결재선명"));
				flag = false;
				$("#P_APPL_NM").focus();
				return false;
			}
			var $tr_lst_length = $('#userFrame').find('tr').length;
			if($tr_lst_length == undefined || $tr_lst_length == 0) {
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
			
			
			if(confirm("결재선을 등록합니다.")){
				pageObj.apprRegist();
			}
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