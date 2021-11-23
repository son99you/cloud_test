/**
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";

	pageObj.estmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/estm/estmCmtmCmplPrcnList.do"); 
	};
	

	/**
	 * 평가위원이 평가대상을 평가한 점수 확인 팝업
	 */
	detailInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmEstmFrm", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmEstmFrmRegForm.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmProgList(); 
	};
	
	
	tabEvent = function(tab_no){
		
		if(tab_no == "1"){   // 기본정보 
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplPrcnDetail.do");
		}else if(tab_no == "2"){   // 서류평가
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplProcdADetail.do?P_ESTM_PROCD_SEQ=1");
		}else if(tab_no == "3"){   // 품평회
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplProcdADetail.do?P_ESTM_PROCD_SEQ=2");
		}else if(tab_no == "4"){   // 평가결과
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplResultDetail.do");
		}else if(tab_no == "5"){   // 화상회의
			FwkCmmnUtil.submitForm("detailFrm", "/opro/estm/estmCmtmCmplVidoMtngDetail.do");
		}
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmProgList();
		});
		
		$('.list-tab li').click(function(){
            var $tabLineIdx = $(this).index();
            $(this).addClass('is-selected');
            $(this).siblings().removeClass('is-selected');
            $('.tab-contents-in').eq($tabLineIdx).show();
            $('.tab-contents-in').eq($tabLineIdx).siblings().hide();
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