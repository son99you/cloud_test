/**
 * <pre>
 * 공통 > 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ estmObjImstarsMainView.js
 * 
 * </pre>
 * @date : 2021. 05. 21.
 * @version : 1.0
 * @author : 은우소프트
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";
	
	
	pageObj.objImstarsTabEvent = function(tab_no){
		if(tab_no == "1"){   // 기본정보 
			FwkCmmnUtil.submitForm("detailFrm", "/comm/popup/estmObjImstarsMainView.do");
		}
		else if(tab_no == "2"){   // 상품정보
			FwkCmmnUtil.submitForm("detailFrm", "/comm/popup/estmObjImstarsDetailView.do");
		}
		else if(tab_no == "3"){   // 첨부파일
			FwkCmmnUtil.submitForm("detailFrm", "/comm/popup/estmObjImstarsFileView.do");
		}
	};
	
	imstarsDownload = function(fileId, fileSn) {
		$("#downloadFrm input[name='P_FILE_ID']").val(fileId);
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/imstarsDetailDownload.do" );
	};
	
	
	pageObj.setEventHandler = function() {

		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
	};

	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();