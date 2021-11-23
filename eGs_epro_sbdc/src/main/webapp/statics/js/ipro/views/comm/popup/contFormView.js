(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var myEditor;
	
	dext_editor_loaded_event = function() {
        var html = $("#P_FRM_CNTN").val();
        // id가 editor1인 에디터 디자인 영역에 body 태그 내부 소스를 입력합니다.
        DEXT5.setBodyValue(html, 'editor1'); 
    };
	
	pageObj.setEventHandler = function() {
		
		$("#ubiBtn").on("click", function() {
			var jrf = "test2.jrf";
			var arg = $("#ubiPopupFrm input[name='P_ARG']").val();
			ubiPopup(jrf, arg);
		}); 
		
	};

	
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
	
	
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		/*myEditor = new dhtmlXEditor ( {
		    parent : "editorObj" , 		// parent 매개변수는 필수항목
		    content : "초기 컨텐츠 설정02",
		    toolbar : true, 
		   // iconsPath : "/dhtmlxSuite_v51_pro/codebase/imgs/" ,
		    iconsPath : "/dhtmlxSuite_v51_pro/skins/terrace/imgs/" ,  
		    skin : "dhx_terrace"
		   // readonly : true
		   // contenteditable : true 
		});  
		   
		myEditor.setReadonly(true);
		myEditor.setContent($("#P_FRM_CNTN").val());  */
		
		dext_editor_loaded_event();
		
	});
	
})();