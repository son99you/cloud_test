(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "updtFrm";
	var myEditor;
	
	
	pageObj.dsgnRecmFileList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/dsgnRecmFileList.do");
	};
	
	
	rowAdd = function(){
		var row = $("#copyArea").children().clone();
		row.show();
		row.find("input[type=file]").eq(0).attr("name","P_FILE");
		row.find("input[type=text]").eq(0).attr("name","P_FILE_DOC_NM");
		row.find("input[name=P_FILE_DOC_NM]").prop("disabled","");
		$("#pasteArea").append(row);
	};
	
	rowDel = function(){
		$("input:checkbox[class='fileDel']").each(function(idx){ 
			var row = $(this).parent().parent();
			if($(this).prop("checked")){
				row.find("input[name='P_FILE_SN']").prop("disabled","");
				row.find("input[type=file]").attr("name","");
				row.find("input[type=text]").prop("name","");
				row.find("input[name=P_FILE_DOC_NM]").prop("disabled","disabled");
				row.hide();
			}
		});
	}; 
	
	allChk = function(){
		// 전체 체크 시
		if($("#allChk").prop("checked")){
			$("input:checkbox[class='fileDel']").each(function(inx){
				if(inx != 0){ // 추가시 복사
					$(this).prop("checked", true);
				}
			});
			
		// 전체 체크 해제 시
		}else{
			$("input:checkbox[class='fileDel']").each(function(inx){
				$(this).prop("checked", false);
			});
		}
	};
	
	fileSet = function(obj){
		var fileInfo = fileInfoSet(obj);
		var node = $(obj).parent().parent();
		if($(obj).val() != ""){
			node.find("input[type=text]").empty().val(fileInfo[0]);
			node.find("input[type=file]").prop("name","P_FILE");
			node.find("input[name='P_FILE_SN']").prop("disabled","");
			node.find("input[name='P_FILE_DOC_NM']").prop("disabled","");
		};
	}; 

	fileMod = function(obj){
		$(obj).parent().parent().parent().hide(); 
		$(obj).parent().parent().parent().next("tr").show();
		$(obj).parent().parent().parent().next("tr").find($("input[type='file']")).prop("name","P_FILE");
	}
	
	fileCancelMod = function(obj){
		$(obj).parent().parent().parent().find($("input[name='P_FILE']")).prop("name","");
		$(obj).parent().parent().parent().hide(); 
		$(obj).parent().parent().parent().prev("tr").show(); 
	}
	
	 pageObj.dsgnRecmFileUpdate = function() {
		 FwkCmmnUtil.submitForm("updtFrm" , "/sytm/dsgnRecmFileUpdate.do");
	};

	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.dsgnRecmFileList();
		});
		
		
		// 저장
		$("#updtBtn").on("click", function() {  
			
			if($("#P_CD_DTL_NM").val() == null || $("#P_CD_DTL_NM").val() == ""){
				alert("설계파일명을 입력해 주십시요.");
				$('#P_CD_DTL_NM').focus();
				return false;
			}
			
			if($("#P_RECO_ID").val() ==""){
				alert("추천코드를 입력해 주십시요.");
				$("#P_RECO_ID").focus();
				return false;
			}
			
			var flag = true;
			$("#pasteArea input[name='P_FILE']").each(function(inx){
				if($(this).val() == ""){
					flag= false;
					return false;
				}else{
					flag=true;
				}
			});
			
			if(!flag){
				alert("첨부파일을 등록해주세요.");
				return false;
			};
			if(!confirm("설계추천파일을 수정하시겠습니까?")){
				return false; 
			};
			
			pageObj.dsgnRecmFileUpdate();
		});
		
		$("#addBtn").on("click", function(){
			rowAdd();
		});
		
		$("#delBtn").on("click", function(){
			rowDel();
		});
		
		$("#allChk").on("click", function(){
			allChk();
		});
		
		$(".fileDel").on("click", function(){
			$("#allChk").prop("checked", false);
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