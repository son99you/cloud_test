(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	// 절차구분count
	var procdCnt = 1;
	
	
	pageObj.estmSeMngRegist = function(){
		FwkCmmnUtil.submitForm("registFrm", "/sytm/estmSeMngRegist.do");
	};
	
	// addBtn
	pageObj.addRow = function() {
		var copyRow = $("#hiddenTbody").children().clone(true);
		
		copyRow.css({"display" : ""});
		
		$(" #hiddenTbody").css({"display" : "none"});
		
		copyRow.find("button[name='estmFrmPopup']").attr("id", procdCnt);
		copyRow.find("input[name='P_ESTM_FRM_NO_H']").attr("id", "P_ESTM_FRM_NO" + procdCnt);
		copyRow.find("input[name='P_ESTM_FRM_NM_H']").attr("id", "P_ESTM_FRM_NM" + procdCnt);
		
		/*copyRow.find("input[name='P_ESTM_FRM_NO_H']").attr({name : "P_ESTM_FRM_NO"});
		copyRow.find("input[name='P_ESTM_FRM_NM_H']").attr({name : "P_ESTM_FRM_NM"});
		copyRow.find("select[name='P_ESTM_PROCD_SECD_H']").attr({name : "P_ESTM_PROCD_SECD"});*/
		
		copyRow.find("select[name='P_ESTM_PROCD_SECD_H']").attr("name", "P_ESTM_PROCD_SECD");
		copyRow.find("input[name='P_ESTM_PROCD_NM_H']").attr("name", "P_ESTM_PROCD_NM");
		copyRow.find("input[name='P_ESTM_FRM_NO_H']").attr("name", "P_ESTM_FRM_NO");
		copyRow.find("input[name='P_ESTM_FRM_NM_H']").attr("name", "P_ESTM_FRM_NM");
		
		
		copyRow.find("#P_ESTM_FRM_NM_TEXT").attr("id", "P_ESTM_FRM_NM_TEXT" + procdCnt);
		
		
		$(" #showTbody").append(copyRow);
		
		procdCnt++;
	};
	
	
	
	
	// delRow
	pageObj.delRow = function() {
		$("#showTbody input[name='chck']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	
	// 심사위원 서명파일 추가 버튼
	fileRowAdd = function() {
		$("#signFileTbody")
			.append($('<tr>')
					.append($('<th>').css({'width':'20%'})
							.append($('<input>').attr({'type':'text', 'name':'P_FILE_DOC_NM'}).addClass('component-input w100'))
							.append($('<input>').attr({'type':'hidden', 'name':'P_FILE_DOC_SECD'}).val("SIGN"))
							.append($('<input>').attr({'type':'hidden', 'name':'P_TSK_VKEY1'}).val(""))
					)
					.append($('<td>')
								.append($('<input>').attr({'type':'file', 'name':'P_SIGN_FILE', 'onchange':'fileChange(this);'}).css({'width':'80%'}))
								.append($('<input>').attr({'type':'hidden', 'name':'ESTM_FSCD', 'disabled':'disabled'}).val("SIGN"))
								.append($('<button>').attr({'type':'button', 'onclick':'fileRowDel(this);'}).addClass('component-button-s type-del').css({'float':'right'}).text("삭제"))
							)
						);
	};
	
	fileRowDel = function(obj){
		$(obj).closest('tr').remove();	
	};
	
	fileChange = function(obj){
		var fullFile = $(obj).val().substring($(obj).val().lastIndexOf("\\")+1);
		var fileNameSplit = fullFile.split('.');
		var fileName = fileNameSplit[0];
		$(obj).parent().parent().find("input[name='P_FILE_DOC_NM']").val(fileName);
	};
	
	// 평가서식팝업 리턴
	estmFrmPopupAdd = function(ESTM_FRM_NO, ESTM_FRM_NM){
		var id = $("#popupFrm input[name='P_BUTTON_ID']").val();
		$("#P_ESTM_FRM_NO"+ id).val(ESTM_FRM_NO);
		$("#P_ESTM_FRM_NM"+ id).val(ESTM_FRM_NM);
		$("#P_ESTM_FRM_NM_TEXT"+ id).text(ESTM_FRM_NM);
		
	}
	
	
	// 부서팝업 스크립트 시작 //
	// 부서 조회 팝업
	pageObj.deptInqirePopup = function() {
		$("#popupFrm input[name='setMulti']").val("N");
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/deptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	//평가담당부서 삭제
	pageObj.deptInqireDelete = function() {
		$("#P_ESTM_CHRG_DEPT_NO").val("");
		$("#P_ESTM_CHRG_DEPT_NM").val("");
	};
	
	deptListAdd = function(deptNo, deptNm){
		
		$("#P_ESTM_CHRG_DEPT_NO").val(deptNo);
		$("#P_ESTM_CHRG_DEPT_NM").val(deptNm);
		
	}
 	// 부서팝업 스크립트 종료 //
	
	pageObj.setEventHandler = function() {
		
		// 평가담당부서 검색버튼
		$("#estmChrgDepSrchBtn").on("click", function() {
			pageObj.deptInqirePopup();
		});
		
		// 평가담당부서 삭제버튼
		$("#estmChrgDeptDelBtn").on("click", function() {
			pageObj.deptInqireDelete();
		});
		
		//평가서식표 팝업 
		$(document).on("click","button[name='estmFrmPopup']", function() {
			
			if($(this).parent().parent().find($("select[name='P_ESTM_PROCD_SECD']")).val() != "" && $(this).parent().parent().find($("select[name='P_ESTM_PROCD_SECD']")).val() != null) 
			{
				$("#popupFrm input[name='P_ESTM_PROCD_SECD']").val($(this).parent().parent().find($("select[name='P_ESTM_PROCD_SECD']")).val());
				$("#popupFrm input[name='P_BUTTON_ID']").val($(this).attr("id"));
				
				$("#popupFrm").one("submit", function() {
					window.open("", "estmFrmPopupList", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
					this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmFrmPopupList.do';
			        this.method = 'POST';
			        this.target = 'estmFrmPopupList';
			    }).trigger("submit");
				
			}else {
				alert("평가절차구분을 먼저 선택하세요.");
			}
			return false;
		});
		
		/**
		 * 수정버튼 클릭 이벤트
		 */
		$("#saveBtn").on("click", function() {
			//pageObj.registFrmInqire(); //1
			
			if($("input[name='P_MXMN_SCR_EXCP_YN']").val() == ''){
				alert("최고/최저점제외여부를 선택하여 주시기 바랍니다.");
				$("input[name='P_MXMN_SCR_EXCP_YN']").focus();
				return false;
			}
			
			if($("#P_ESTM_OBJ_SECD").val() == ''){
				alert("평가대상구분을 선택하여 주시기 바랍니다.");
				$("#P_ESTM_OBJ_SECD").focus();
				return false;
			}
			
			if($("#P_ESTM_CHRG_DEPT_NO").val() == ''){
				alert("평가부서를 선택하여 주시기 바랍니다.");
				$("#P_ESTM_CHRG_DEPT_NM").focus();
				return false;
			}
			
			var returnAt = "T";
			
			/*if($("#signFileTbody tr").length == 0){
				alert("심사위원 서명파일은 필수입니다.");
				return false;
			}else{
				$("input[name='P_SIGN_FILE']").each(function(inx){
					if($(this).val() == ""){
						alert("심사위원 서명파일은 필수입니다.");
						$(this).focus();
						returnAt = "F";
					}
				});

				if(returnAt == "F") {
					return false;
				}
			}*/
			
			// 평가절차구분
			$("select[name='P_ESTM_PROCD_SECD']").each(function(i) {
				//ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
				if($(this).val() == ''){
					alert("평가절차구분을 선택하여 주시기 바랍니다.");
					returnAt = "F";
					return false;
				}
			});
			
			if(returnAt == "F") {
				return false;
			}
			
			// 평가절차명
			$("input[name='P_ESTM_PROCD_NM']").each(function(i) {
				//ESTM_DTL_ITEM_ARR[i][0] = $(this).val();
				if($(this).val() == ''){
					alert("평가절차명을 입력하여 주시기 바랍니다.");
					returnAt = "F";
					return false;
				}
			});
			
			if(returnAt == "F") {
				return false;
			}
			
			// 평가서식
			$("input[name='P_ESTM_FRM_NO']").each(function(i) {

				if($(this).val() == ''){
					alert("평가서식을 선택하여 주시기 바랍니다.");
					returnAt = "F";
					return false;
				}
			});
			
			if(returnAt == "F") {
				return false;
			}
			
			if(!confirm("저장 하시겠습니까?")){
				return false;
			}
			
			pageObj.estmSeMngRegist();
			
		});
		
		
		/**
		 * 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/sytm/estmSeMngList.do");
			return false;
		});
		
		/**
		 * 평가추가버튼 클릭 이벤트
		 */
		$("#addBtn").on("click", function() {
			pageObj.addRow();
			return false;
		});
		
		/**
		 * 평가추가버튼 클릭 이벤트
		 */
		$("#delBtn").on("click", function() {
			pageObj.delRow();
			return false;
		});
		
		
		// 심사위원 서명파일 추가 버튼
		$(".fileAddBtn").on("click", function() {
			fileRowAdd();
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