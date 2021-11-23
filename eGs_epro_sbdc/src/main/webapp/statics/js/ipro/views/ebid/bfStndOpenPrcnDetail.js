/**
 * 입찰관리 > 사전규격공개 상세
 * 
 * <pre>
 * ebid | _
 * bfStndOpenPrcnDetail.js
 * </pre>
 * 
 * @date : 2018. 02. 19.
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "registFrm";

	// 목록
	pageObj.bfStndOpenPrcnList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bfStndOpenPrcnList.do");
	};

	// 수정양식
	pageObj.bfStndOpenupdtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bfStndOpenUpdtForm.do");
	};

	// 공고요청
	pageObj.bfStndRqstProgStat = function() {
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/ebid/bidBfanStatUpdt";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {

			if (res.stateUpdt == "succ") {
				if(res.param.P_BFAN_PSCD =="B008"){
					FwkCmmnUtil.submitForm("updtFrm","/ebid/bfStndOpenPrcnList.do");
				}else{
					FwkCmmnUtil.submitForm("updtFrm","/ebid/bfStndOpenPrcnDetail.do");
				}
			}
		});
	};

	// 진행이력 팝업
	pageObj.bfStndOpenHistPopup = function() {
		$("#popupFrm").one("submit",function() {
			window.open("", "bfanProgPopup",	"width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath'] + '/comm/popup/bfanProgPopup.do';
			this.method = 'POST';
			this.target = 'bfanProgPopup';
		}).trigger("submit");
	};

	// 반려 팝업
	pageObj.bfStndRtnPopup = function() {
		$("#popupFrm").one("submit",function() {
			window.open("", "bfanProgPopup",	"width=750px,height=360px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']	+ '/ebid/popup/bfanRtnRsnPopup.do';
			this.method = 'POST';
			this.target = 'bfanProgPopup';
		}).trigger("submit");
	};

	pageObj.chgContSecd = function(obj) {
		var contSecdCd = $(obj).val();

		if (contSecdCd == 0) {
			$("#dlgdPlcNm").text("납품장소");
		} else if (contSecdCd == 1) {
			$("#dlgdPlcNm").text("용역현장");
		} else if (contSecdCd == 2) {
			$("#dlgdPlcNm").text("공사현장");
		}
	};

	pageObj.chgContMtcd = function(obj) {
		var contMtcd = $(obj).val();

		if (contMtcd == 10000 || contMtcd == 10001 || contMtcd == 10002) {
			$("#contMtcdGbn").css("display", "");
		} else {
			$("#contMtcdGbn").css("display", "none");
		}
	};

	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do");
	};

	reDetail = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bfStndOpenPrcnDetail.do");
	}

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다. 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다. -
	 * 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다. 2. 저장버튼(#saveBtn) 의 click
	 * 이벤트를 binding 한다. - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */
	pageObj.setEventHandler = function() {

		// 취소버튼
		$("#listBtn").on("click", function() {
			if (!confirm("목록화면으로 이동하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndOpenPrcnList();
		});

		// 수정버튼
		$("#updtFormBtn").on("click", function() {
			if (!confirm("수정화면으로 이동하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndOpenupdtForm();
		});

		// 진행이력버튼
		$("#progHistBtn").on("click", function() {
			pageObj.bfStndOpenHistPopup();
		});

		// 요청반려
		$("#rqstRtnBtn").on("click", function() {
			$("#popupFrm input[name='P_BFAN_PSCD']").val("B005");
			pageObj.bfStndRtnPopup();
		});

		// 요청접수
		$("#rqstRecBtn").on("click", function() {
			$("#updtFrm input[name='P_BFAN_PSCD']").val("B004");
			if (!confirm("사전규격공고요청접수를 하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndRqstProgStat();
		});

		// 접수반려
		$("#recRtnBtn").on("click", function() {
			$("#popupFrm input[name='P_BFAN_PSCD']").val("B007");
			pageObj.bfStndRtnPopup();
		});
		

		// 접수승인
		$("#recApplyBtn").on("click", function() {
			$("#updtFrm input[name='P_BFAN_PSCD']").val("B006");
			if (!confirm("사전규격접수승인을 하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndRqstProgStat();
		});

		// 단순반려
		$("#rtnBtn").on("click", function() {
			$("#popupFrm input[name='P_BFAN_PSCD']").val("B013");
			pageObj.bfStndRtnPopup();
		});
		
		// 공고
		$("#annoBtn").on("click", function() {
			$("#updtFrm input[name='P_BFAN_PSCD']").val("B008");
			if (!confirm("공고 하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndRqstProgStat();
		});

		// 공고
		$("#annoComplBtn").on("click", function() {
			$("#updtFrm input[name='P_BFAN_PSCD']").val("B009");
			if (!confirm("공고완료 하시겠습니까?")) {
				return false;
			}
			pageObj.bfStndRqstProgStat();
		});
	};

	/**
	 * window load
	 * 
	 */
	$(function() {
		pageObj.setEventHandler();

		pageObj.chgContSecd($("#contSecd"));
		pageObj.chgContMtcd($("#contMtcd"));
		fileEtcView($("#P_FILE_GRP_NO_ETC").val());
	});
})();