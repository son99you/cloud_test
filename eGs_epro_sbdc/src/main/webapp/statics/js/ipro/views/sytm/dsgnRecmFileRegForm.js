(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "registFrm";
	
	pageObj.dsgnRecmFileList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/sytm/dsgnRecmFileList.do");
	};
	
	pageObj.dsgnRecmFileRegist = function() { 
		FwkCmmnUtil.submitForm("registFrm", "/sytm/dsgnRecmFileRegist.do");
	}; 
	 
	
	rowAdd = function(){
		var row = $("#copyArea").children().clone();
		row.show();
		row.find("input[type=file]").eq(0).attr("name","P_FILE"); 
		row.find("input[type=text]").eq(0).attr("name","P_FILE_DOC_NM"); 
		row.find("input[name=P_FILE_DOC_NM]").prop("disabled", false); 
		$("#pasteArea").append(row);
	};
	
	rowDel = function(obj){
		$("input:checkbox[class='fileDel']").each(function(idx){ 
			if($(this).prop("checked")){
				$(this).parent().parent().remove();
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
		};
	};
	
	pageObj.setEventHandler = function() {
		$("#saveBtn").on("click", function() {
			
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
			
			pageObj.dsgnRecmFileRegist(); 
		});
		
		$("#listBtn").on("click", function() {
			pageObj.dsgnRecmFileList();
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