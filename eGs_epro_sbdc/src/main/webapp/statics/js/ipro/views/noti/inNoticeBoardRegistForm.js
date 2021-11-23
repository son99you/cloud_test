/**
 * 알림마당 > 내부공지사항 등록
 *  
 *   <pre>
 * noti
 *    |_ inNoticeBoardRegistForm.js
 * 
 * </pre>
 * @date : 2017.06.14.
 * @version : 1.0
 * @author : 은우소프트 이주연
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var file = null;
	
	rowAdd = function( fileDiv, fileRow, inputFile, fileName ){
		//if($("#"+ fileDiv +" input").length == 0 || $(inputFile).val()){
			var row = $("#"+fileRow).clone();
			row.css("display",""); 
			row.attr("id","");
			row.find("input").eq(0).attr("name",fileName); 
			//row.find("input").eq(1).removeAttr("disabled");  
			//inputFile = row.find("input"); 
			//inputFile.click();
			
			/*if($(inputFile).val() == "" || $(inputFile).val() == null) {
				
			}else {*/
				$("#"+fileDiv).append(row);
			//}

		//}else{
			//inputFile.click();
		//}
		//return inputFile;
	};
	
	rowDel = function(obj){
		$(obj).parent().parent().remove();
	};
	
	fileDel = function(obj,sn){
		if($("#P_DELETE_FILE_SN").val()){
			$("#P_DELETE_FILE_SN").val($("#P_DELETE_FILE_SN").val()+","+sn);
		}else{
			$("#P_DELETE_FILE_SN").val(sn);
		}
		$(obj).parent().parent().remove();
	};
	
	fileChange = function(obj){
		//$(obj).parent().append($(obj).val().substring($(obj).val().lastIndexOf("\\")+1));
	};
	
	/**  
     * <pre>
     * 1. 개요 : 목록조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 목록 조회
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13      은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.inNoticeBoardList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/noti/inNoticeBoardList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 수정 폼 조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 수정 폼 조회 
     *  	
     * </pre>
     * @Function Name : updtFrmInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연           최초 작성 
     *  =======================================================    
     */
	pageObj.inNoticeBoardRegist = function() {
		$("#P_CN").val(DEXT5.getBodyValue('editor1'));
		FwkCmmnUtil.submitForm("registFrm", "/noti/inNoticeBoardRegist.do");
	};
	
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
//		editorStart();

		// 취소버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록으로 이동하시겠습니까?")){
				return false; 
			}
			pageObj.inNoticeBoardList();
		});
		
		// 등록버튼
		$("#saveBtn").on("click", function() {
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			pageObj.inNoticeBoardRegist();
		}); 
		
		$("#fileBtn").on("click", function(){
			file = rowAdd("fileDiv", "fileRow", file, "P_FILE");
		});
	
		
	};  

	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		
	});
})();