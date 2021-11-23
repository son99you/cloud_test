/**
 * 알림마당 > 내부공지사항 상세
 *  
 *   <pre>
 * noti
 *    |_ inNoticeBoardDetail.js
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

	ubiPopup = function(jrf, arg) {
		$("#ubiPopupFrm input[name='P_JRF']").val(jrf);
		$("#ubiPopupFrm input[name='P_ARG']").val(arg);
		
		$("#ubiPopupFrm").one("submit", function() {
			window.open("", "ubiPopup", "width=1020px,height=980px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=250,top=0");
			this.action = FwkCmmnUtil['contextPath']+'/ubi4/ubihtml.jsp';
			this.method = 'POST';
			this.target = 'ubiPopup';
		}).trigger("submit");
	};
	
	dext_editor_loaded_event = function() {
        var html = $("#P_CN").val();
        // id가 editor1인 에디터 디자인 영역에 body 태그 내부 소스를 입력합니다.
        DEXT5.setBodyValue(html, 'editor1');
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
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/noti/inNoticeBoardList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 파일 다운로드
	 * 2. 처리내용 : 
	 * 		공고게시  
	 *  	
	 * </pre>
	 */
	download = function(sn) {
		$("#downloadFrm input[name='P_ATCHMNFL_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
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
	pageObj.updtFrmInqire = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/noti/inNoticeBoardUpdtForm.do");
	};
	
	
	/**  
     * <pre>
     * 1. 개요 : 통합게시판 삭제
     * 2. 처리내용 : 
     * 		통합게시판 삭제
     *  	
     * </pre>
     * @Function Name : unityNttDelete
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연             최초 작성 
     *  =======================================================   
     */
	pageObj.unityNttDelete = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/noti/inNoticeBoardList.do");
		
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/noti/inNoticeBoardDelete";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData 
			, function(res) { 
				alert(FwkMssageUtil.getMessage("COM.INF.002", "삭제"));
				pageObj.listInqire();
			}
		);
	
	};
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		// 목록버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록으로 이동하시겠습니까?")){ 
				return false;
			}
			pageObj.listInqire();
		});
		
		// 수정버튼 
		$("#modifyBtn").on("click", function() {
			if(!confirm("수정화면으로 이동하시겠습니까?")){ 
				return false; 
			}
			pageObj.updtFrmInqire();
		}); 

		// 삭제버튼
		$("#deleteBtn").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "삭제"))){
				return false;
			}
			pageObj.unityNttDelete();
		});
		
		$("#ubiBtn").on("click", function() {
			var jrf = "test.jrf";
			var arg = $("#ubiPopupFrm input[name='P_ARG']").val();
			ubiPopup(jrf, arg);
		});
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		dext_editor_loaded_event();
	});
})();