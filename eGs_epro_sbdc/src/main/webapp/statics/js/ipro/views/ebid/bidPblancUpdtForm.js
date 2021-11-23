/**
 * 입찰현황 > 정정공고 수정 폼
 *
 * <pre>
 * ebid 
 *    |_ bidPblancUpdtForm.js
 *    
 * 
 * </pre>
 * @date : 2017.06.19
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "bidPlanUpdtFrm";

	/**  
     * <pre>
     * 1. 개요 : 입찰계획목록 페이지 이동
     * 2. 처리내용 : 
     * 		입찰계획목록 조회 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : bidPlanList
     * @date : 2015. 01. 30.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 30.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPlanList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPlanList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 입찰계획목록 페이지 이동
     * 2. 처리내용 : 
     * 		입찰계획목록 조회 페이지로 이동한다.
     *  	
     * </pre>
     * @Function Name : bidPlanList
     * @date : 2015. 02. 02.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 02.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPlanDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPblancDetail.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰계획수정 저장 submit
	 * 2. 처리내용 : 
	 * 		입찰계획 Form 을 Sumit 한다.
	 *  	
	 * </pre>
	 * @Function Name : bidPlanUpdt
	 * @date : 2019. 01. 30.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 01. 30.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidPlanUpdt = function() {
		var jsonData = $("#bidPblancUpdtFrm").serializeObject();
		var actionUrl = "/ebid/updtPblancRegist.do";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			$('#P_ANNC_NO').val(res.rtnAnncInfo.ANNC_NO);
			$('#P_ANNC_NGR').val(res.rtnAnncInfo.ANNC_NGR);
			$('#P_ROUND_NO').val(res.rtnAnncInfo.ROUND_NO);
			
			pageObj.fileUploadStart();
		});		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 첨부파일 업로드 시작 
	 * 2. 처리내용 : 
	 *     
	 * </pre>
	 * @Function Name : fileUploadStart
	 * @date : 2019. 01. 30.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 01. 30.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	pageObj.fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (DEXT5UPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPblancList.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
            DEXT5UPLOAD.Transfer("upload");
        }
	};		

	/**  
	 * <pre>
	 * 1. 개요 : 일괄공고 가능 품목 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Function Name : prdlstListPopup
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 03. 08.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.prdlstListPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "uniAnncItemListPopup", "width=1024px,height=780px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/uniAnncItemList.do';
			this.method = 'POST';
			this.target = 'uniAnncItemListPopup';
		}).trigger("submit");
		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : chargerInqirePopup
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 01. 07.       은우소프트 전상훈                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerListPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'chargerListPopup';
	    }).trigger("submit");
	};		
	
	/**
	 * 지명업체 찾기 이벤트 발생시
	 * 1. 지명업체선택 팝업 화면으로 이동한다.
	 * 2. 팝업화면에서 선택된 업체의 정보를 화면에 보여준다.
	 */
	pageObj.nmenSearchEvent = function () {
		
		// 팝업처리
		$("#ccpyPopupFrm").one("submit", function() {
			window.open("", "commCcpyFormPopup", "width=740px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/entrpsList.do';
	        this.method = 'POST';
	        this.target = 'commCcpyFormPopup';
	    }).trigger("submit");
	};
	
	/**
	 * 지명업체 찾기 팝업 에서 업체 선택 시 Event
	 * 1. 지명업체선택 팝업 화면에서 받아 온 업체 정보를 부모창에 적용한다.
	 */
	ccpyListAdd = function (ccpyCnt, arr_entrps_regist_no, arr_bizr_no, arr_entrps_nm, arr_chrgr_nm, arr_email, arr_telno, arr_cpno, arr_rprs_nm) {
		//선택업체수, 업체등록번호, 사업자번호, 업체명, 담당자명, 이메일, 전화번호, 휴대폰번호, 대표자
		$(arr_entrps_regist_no).each(function(inx, item){
			// 중복체크
			var flag=true;
			$("input[name='P_VEND_REG_NO']").each(function(){
				if($(this).val() == arr_entrps_regist_no[inx]){
					flag = false;
					return false;
				}
			});
			
			if(flag == true){
				// 팝업에서 선택된 정보 화면에 보여주기
				var copyRow = $("#nmenChoiseHiddTbdy").children().clone(true);
				
				copyRow.find("label[for='nmenChoiseCbx']").attr("for","nmenChoiseCbx"+arr_entrps_regist_no[inx]);
				copyRow.find("input[name='nmenChoiseCbx']").attr("id","nmenChoiseCbx"+arr_entrps_regist_no[inx]);
				copyRow.find("input[name='P_VEND_REG_NO']").val(arr_entrps_regist_no[inx]);
				copyRow.find("input[name='P_VEND_REG_NO']").attr("disabled", false);
				copyRow.find("[bizrNo]").text(arr_bizr_no[inx]);
				copyRow.find("[entrpsNm]").text(arr_entrps_nm[inx]);
				copyRow.find("[rprsntvNm]").text(arr_rprs_nm[inx]);
				
				copyRow.css({"display" : ""});
				
				$("#nmenChoiseEmpty").css({"display" : "none"}); // [선택된 업체가 없습니다.] 문구 감추기
				
				$("#nmenChoiseShowTbdy").append(copyRow);
			}
		});

	};
	
	/**
	 * 지명업체 삭제 이벤트 발생시
	 * 1. 체크된 지명업체 Row를 삭제한다.
	 */
	pageObj.nmenDeleteEvent = function () {
		
		$("#nmenChoiseShowTbdy input[name='nmenChoiseCbx']").each(function(){
			if(this.checked){
				$(this).parent().parent().remove();
			}
		});
		
		// 선택된 업체가 없을 경우 화면에 보여지는 문구 보여주기
		if($("#nmenChoiseShowTbdy tr").length == 0){
			$("#nmenChoiseEmpty").css({"display" : ""});
		}
		
	};
	
	/**
	 * 입찰품목 찾기 팝업 에서 업체 선택 시 Event
	 * 1. 입찰품목선택 팝업 화면에서 받아 온 품목 정보를 부모창에 적용한다.
	 */
	prdlstListAdd = function (itemCnt, arr_bid_wait_no) {
		//선택품목수, 품목코드, 대분류코드, 중분류코드, 소분류코드, 품목명, 규격명 , 단위코드, 단위명
		$(arr_bid_wait_no).each(function(inx, item){
			
			// 중복체크
			var flag=true;
//			$("#bidPlanRegistFrm input[name='P_BID_WAIT_NO']").each(function(){
//				if($(this).val() == arr_bid_wait_no[inx]){
//					flag = false;
//					return false;
//				}
//			});
			

			if(flag == true){

				$('#ajaxItemForm input[name=P_BID_WAIT_NO]').val(arr_bid_wait_no[inx]);
				var jsonData = $("#ajaxItemForm").serializeObject();
				var actionUrl = "/ebid/paItemList";

				FwkCmmnUtil.submitAjax (actionUrl, jsonData
						, function(res) {
					if(res.paItemList != undefined && res.paItemList.length > 0) {
						for(var idx = 0; idx < res.paItemList.length; idx++) {
							var itemInfo = res.paItemList[idx];
							
							// 중복품목 체크
							var itemFlag =false;
							$("#bidPlanRegistFrm #biprInfoShowTbdy input[name='P_BID_WAIT_NO']").each(function(){
								if($(this).val() == itemInfo.BID_WAIT_NO && $(this).next().val() == itemInfo.ITEM_SN){
									itemFlag = true;
									//break;
								}
							});
							
							if(!itemFlag){  
								
								$("#biprInfoShowTbdy")
								.append($('<tr>').addClass('row')
										.append($('<td>').addClass("txtc")
												.append($('<input>').attr({'type':'checkbox', 'name':'biprInfoCbx'}))
												.append($('<input>').attr({'type':'hidden', 'name':'P_BID_WAIT_NO'}).val(itemInfo.BID_WAIT_NO))
												.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_SN'}).val(itemInfo.ITEM_SN))
												.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_NO'}).val(itemInfo.ITEM_NO))
												.append($('<input>').attr({'type':'hidden', 'name':'P_PCRQ_NO'}).val(itemInfo.PCRQ_NO))
												.append($('<input>').attr({'type':'hidden', 'name':'P_PCRQ_ITEM_SN'}).val(itemInfo.ITEM_SN))
										)
										.append($('<td>').addClass("txtc").text(itemInfo.PCRQ_NO))
										.append($('<td>').addClass("txtc").text(itemInfo.ITEM_NO))
										.append($('<td>').addClass("txtl").text(itemInfo.ITEM_NM)
												.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_NM'}).val(itemInfo.ITEM_NM))
										)
										.append($('<td>').addClass("txtc")
												.append($('<input>').attr({'type':'text', 'name':'P_STND_NM'}).val(itemInfo.STND_NM))
										)
										.append($('<td>').addClass("txtc").text(itemInfo.UNIT)
												.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_UNNM'}).val(itemInfo.UNIT))
										)
										.append($('<td>').addClass("txtr pr5").text(itemInfo.ITEM_QTY)
												.append($('<input>').attr({'type':'hidden', 'name':'P_ITEM_QTY'}).val(itemInfo.ITEM_QTY))
										)
										.append($('<td>').addClass("txtr pr5").text(FwkCmmnUtil.addComma(itemInfo.ESTT_UPRC))
												.append($('<input>').attr({'type':'hidden', 'name':'P_RQST_UNIT'}).val(itemInfo.ESTT_UPRC))
										)
										.append($('<td>').addClass("txtr pr5").text(FwkCmmnUtil.addComma(Number(itemInfo.ESTT_UPRC)*Number(itemInfo.ITEM_QTY)))
												.append($('<input>').attr({'type':'hidden', 'name':'P_SCH_UNIT'}).val(Number(itemInfo.ESTT_UPRC)*Number(itemInfo.ITEM_QTY)))
										)
								);
								
							}
						}
						$("#biprInfoEmpty").css({"display" : "none"}); 
						
						
						
					}
				});		
				
			}
			
			// 추정금액, 가격 자동계산
			esttCal();
			
		});

	};
	
	/**
	 * 추정금액, 가격 자동계산
	 */
	esttCal = function(){

		if($("#P_ESTT_AMT").val()){
			
			var esttAmt = 0;
			$("#biprInfoShowTbdy input[name='P_SCH_UNIT']").each(function(){
				esttAmt = esttAmt + Number(FwkCmmnUtil.deleteComma($(this).val()));
			});
			
			// 추정금액 계산
			$("#P_ESTT_AMT").val(FwkCmmnUtil.addComma(esttAmt));
			
			// 추정가격 계산
			$("#P_ESTT_PRCE").val(FwkCmmnUtil.addComma(Math.round(esttAmt/1.1)));
			
			//기초금액 계산
			$("#P_BASE_AMT").val(FwkCmmnUtil.addComma(esttAmt));
		}
	};
	
	/**
	 * 입찰품목 삭제 이벤트 발생시
	 * 1. 체크된 입찰품목 Row를 삭제한다.
	 */
	pageObj.biprInfoDeleteEvent = function () {
		
		$("#biprInfoShowTbdy input[name='biprInfoCbx']").each(function(){
			if(this.checked){
				$(this).parent().parent().remove();
			}
		});
		
		// 선택된 업체가 없을 경우 화면에 보여지는 문구 보여주기
		if($("#biprInfoShowTbdy tr").length == 0){
			$("#biprInfoEmpty").css({"display" : ""});
		}
		
		// 추정금액, 가격 자동계산
		esttCal();
		
		
	};

	/**
	 * 입찰구분 선택에 따른 이벤트 발생시
	 * 1. 구매일 경우 기자재포함 여부 보여주기
	 * 2. 공사일 경우 공사범위. 공사기간 보여주기
	 * 3. 용역일 경우 용역범위, 용역기간 보여주기
	 * 4. 입찰구분이 공사, 용역 일 경우 코드ID가 'C00026' 인 코드값목록으로 입찰구분상세 콤보박스를 재생성한다.
	 */
	pageObj.contSecdEvent = function (obj) {
		if($(obj).val()  == "0"){
			$(".svcDiv").css("display", "none");
			$(".ctrcDiv").css("display", "none");
			$(".buyDiv").css("display", ""); 
			
			$("#P_ITEM_DRCD").val("99");

			$("#P_SITE_BRFS_DT").val("");
			$("#P_SITE_BRFS_DT_HH").val("");
			$("#P_SITE_BRFS_DT_MM").val("");
			$("#P_SITE_BRFS_PLC").val("");
			
			$("#P_DFFL_GTAMT_RT").val(2);
			
			$(".FILE14").remove();
			delRedFile("FILE14");
			$(".FILE15").remove();
			delRedFile("FILE15");
			$(".FILE16").remove();
			delRedFile("FILE16");
			$(".fileDiv").remove();

			if($(".FILE13").length ==0 ){
				var html = "<tr class='fileDiv'>"
									+"<td>규격서<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE13' class='fileView'></td>"
									+"<td><input type='file' name='P_RED_BID_FILE' class='fileView'></td>"
									+"<td>" 
										+"<button type='button' class='btn btn_m btn_del fileView' onclick='rowRegDel(this)'>삭제</button>"
										+"<button type='button' class='btn btn_m btn_del fileDel' onclick='rowCnclDel(this)' style='display: none;'>취소</button>"
									+"</td>"
						    	+"</tr>";
		    	$("#redFileShowTbdy").append(html);
			}
			
		}else if($(obj).val()  == "1"){
//			if($("#P_SBID_MTCD").val() == "31"){
//				alert("낙찰자선정방법이\n적격심사일때는\n용역을 선택하실수 없습니다.");
//				$(obj).val("");
//				pageObj.prcureSeEvent(obj);
//				return;
//			}
			
			$(".ctrcDiv").css("display", "none");
			$(".buyDiv").css("display", "none");
			$(".svcDiv").css("display", "");
			$(".svcKdDiv").css("display","none");
			
			$("#P_ITEM_DRCD").val("");
			$("#P_DLGD_PLC_NM").val("");
			$("#P_DLGD_TE_CNTN").val("");
			$("#P_ISTL_LMT").val("");
			$("#biprInfoShowTbdy").children().remove();
			if($("#biprInfoShowTbdy tr").length == 0){
				$("#biprInfoEmpty").css({"display" : ""});
			}
			
			$("#P_SITE_BRFS_DT").val("");
			$("#P_SITE_BRFS_DT_HH").val("");
			$("#P_SITE_BRFS_DT_MM").val("");
			$("#P_SITE_BRFS_PLC").val("");
			
			$("#P_DFFL_GTAMT_RT").val(2);
			
			$(".FILE13").remove();
			delRedFile("FILE13");
			$(".FILE14").remove();
			delRedFile("FILE14");
			$(".FILE15").remove();
			delRedFile("FILE15");
			$(".fileDiv").remove();
			
			if($(".FILE16").length ==0 ){
				var html = "<tr class='fileDiv'>"
									+"<td>용역유의서<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE16' class='fileView'></td>"
									+"<td><input type='file' name='P_RED_BID_FILE' class='fileView'></td>"
									+"<td>" 
										+"<button type='button' class='btn btn_02 btn_sch fileView' onclick='rowRegDel(this)' >삭제</button>"
										+"<button type='button' class='btn btn_02 btn_sch fileDel' onclick='rowCnclDel(this)' style='display: none;'>취소</button>"
									+"</td>"
						    	+"</tr>";
		    	$("#redFileShowTbdy").append(html);
			}
			
		}else if($(obj).val()  == "2"){
			$(".buyDiv").css("display", "none");
			$(".svcDiv").css("display", "none");
			$(".ctrcDiv").css("display", "");
			
			$("#P_ITEM_DRCD").val("");
			$("#P_DLGD_PLC_NM").val("");
			$("#P_DLGD_TE_CNTN").val("");
			$("#P_ISTL_LMT").val("");
			$("#biprInfoShowTbdy").children().remove();
			if($("#biprInfoShowTbdy tr").length == 0){
				$("#biprInfoEmpty").css({"display" : ""});
			}
			
			$("#P_DFFL_GTAMT_RT").val(5);
			
			$(".FILE13").remove();
			delRedFile("FILE13");
			$(".FILE16").remove();
			delRedFile("FILE16");
			$(".fileDiv").remove();
			
			if($(".FILE14").length ==0 ){
				var html = "<tr class='fileDiv'>"
									+"<td>시방서<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE14'></td>"
									+"<td><input type='file' name='P_RED_BID_FILE'></td>"
									+"<td>" 
										+"<button type='button' class='btn btn_02 btn_sch fileView' onclick='rowRegDel(this)' >삭제</button>"
										+"<button type='button' class='btn btn_02 btn_sch fileDel' onclick='rowCnclDel(this)' style='display: none;'>취소</button>"
									+"</td>"
						    	+"</tr>";
		    	$("#redFileShowTbdy").append(html);
			}
			
			if($(".FILE15").length ==0 ){
				var html = "<tr class='fileDiv'>"
									+"<td>도면<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE15'></td>"
									+"<td><input type='file' name='P_RED_BID_FILE'>"
									+"<td>" 
										+"<button type='button' class='btn btn_02 btn_sch fileView' onclick='rowRegDel(this)'>삭제</button>"
										+"<button type='button' class='btn btn_02 btn_sch fileDel' onclick='rowCnclDel(this)' style='display: none;'>취소</button>"
									+"</td>"
								+"</tr>";
				$("#redFileShowTbdy").append(html);
			}
			
		}else if($(obj).val()  == "4"){
			$(".svcDiv").css("display", "none");
			$(".ctrcDiv").css("display", "none");
			$(".buyDiv").css("display", ""); 

			$("#P_SITE_BRFS_DT").val("");
			$("#P_SITE_BRFS_DT_HH").val("");
			$("#P_SITE_BRFS_DT_MM").val("");
			$("#P_SITE_BRFS_PLC").val("");

			$("#P_DFFL_GTAMT_RT").val(2);
			
			$(".fileDiv").remove();
			
			var html = "<tr class='fileDiv'>"
								+"<td>규격서<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE13' class='fileView'></td>"
								+"<td>"
									+"<input type='file' name='P_RED_BID_FILE' class='fileView'>"
									+"<button type='button' class='btn btn_02 btn_sch fileView' onclick='rowRegDel(this)' style='float: right;'>삭제</button>"
									+"<button type='button' class='btn btn_02 btn_sch fileDel' onclick='rowCnclDel(this)' style='float: right; display: none;'>취소</button>"
								+"</td>"
					    	+"</tr>";
	    	$("#redFileShowTbdy").append(html);
			
			
		}else{
			$(".buyDiv").css("display", "none");
			$(".svcDiv").css("display", "none");
			$(".ctrcDiv").css("display", "");
			
			$("#P_ITEM_DRCD").val("99");
			
			$("#P_ITEM_DRCD").val("");
			$("#P_DLGD_PLC_NM").val("");
			$("#P_DLGD_TE_CNTN").val("");
			$("#P_ISTL_LMT").val("");
			$("#biprInfoShowTbdy").children().remove();
			if($("#biprInfoShowTbdy tr").length == 0){
				$("#biprInfoEmpty").css({"display" : ""});
			}
			
			$("#P_SITE_BRFS_DT").val("");
			$("#P_SITE_BRFS_DT_HH").val("");
			$("#P_SITE_BRFS_DT_MM").val("");
			$("#P_SITE_BRFS_PLC").val("");
			
			$(".FILE13").remove();
			delRedFile("FILE13");
			$(".FILE14").remove();
			delRedFile("FILE14");
			$(".FILE15").remove();
			delRedFile("FILE15");
			$(".FILE16").remove();
			delRedFile("FILE16");
			$(".fileDiv").remove();
			
			//매각 별도 처리
			if($(obj).val()  == "5"){
				$('#P_SBID_MTCD').append($('<option>').text("최고가").val("60"));
				$('#P_SBID_MTCD').val("60");
				pageObj.sbidMtcdEvent($("#P_SBID_MTCD"));
				$('#P_SBID_MTCD').attr({"disabled":true});
			}
		}
	};
	
	/**
	 * 긴급입찰여부 이벤트
	 */
	pageObj.emrgEvent = function (obj) {
		if($(obj).val() == "Y"){
			$(".emrgDiv").css({"display" : ""});
		}else{
			$(".emrgDiv").css({"display" : "none"});
			$("#P_EMRG_BID_RSN").val();
		}
	};
	
	/**
	 * 계약방법에 따른 이벤트 발생시
	 */
	pageObj.bidMtcdEvent = function (obj) {
		if($(obj).val() == "10000"){
			$(".slctDiv").css({"display" : "none"});
			$(".lmtDiv").css({"display" : "none"});
			$("#nmenChoiseShowTbdy").children().remove();
			if($("#nmenChoiseShowTbdy tr").length == 0){
				$("#nmenChoiseEmpty").css({"display" : ""});
			}
			$("#P_ARA_LMT_CD").val("");
			$("#P_BTP_LMT_CD").val("");
		}else if($(obj).val() == "10001"){
			$(".lmtDiv").css({"display" : "none"});
			$(".slctDiv").css({"display" : ""});
			$("#P_ARA_LMT_CD").val("");
			$("#P_BTP_LMT_CD").val("");
		}else if($(obj).val() == "10002"){
			$(".slctDiv").css({"display" : "none"});
			$(".lmtDiv").css({"display" : ""});
			$("#nmenChoiseShowTbdy").children().remove();
			if($("#nmenChoiseShowTbdy tr").length == 0){
				$("#nmenChoiseEmpty").css({"display" : ""});
			}
		}else{
			$(".slctDiv").css({"display" : "none"});
			$(".lmtDiv").css({"display" : "none"});
			$("#nmenChoiseShowTbdy").children().remove();
			if($("#nmenChoiseShowTbdy tr").length == 0){
				$("#nmenChoiseEmpty").css({"display" : ""});
			}
			$("#P_ARA_LMT_CD").val("");
			$("#P_BTP_LMT_CD").val("");
		}
	};
	
	/**
	 * 낙찰자 선정 방식 선택에 따른 이벤트 발생시
	 * 1. 적격심사일 경우 합산비율을 낙찰하햔율로 변경
	 */
	pageObj.sbidMtcdEvent = function (obj) {
		if($(obj).val() == "31"){ 							// 적격심사
//			if($("#P_CONT_SECD").val() == "1"){
//				alert("입찰구분이 용역일때는\n적격심사를\n선택하실수 없습니다.");
//				$(obj).val("");
//				pageObj.sbidMtcdEvent(obj);
//				return;
//			}
			
			$(".grlDiv").css({"display" : ""});
			$(".negoDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$("input[name='P_BID_BRFS_YN']:checked").each(function(){
				pageObj.bidBrfsEvent(this);
			});
			pageObj.exmtYnEvent();
			pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
			$(".elgbDiv").css({"display" : ""});
			
			$("#P_TCHN_SCR_RT").val("");
			$("#P_PRCE_SCR_RT").val("");
			
			$(".sbmtDiv").css({"display" : ""});
			
			pageObj.prpdcEvent("N");
		}else if($(obj).val() == '40'){	//협상에 의한 계약
			$(".grlDiv").css({"display" : ""});
			$(".elgbDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$("input[name='P_BID_BRFS_YN']:checked").each(function(){
				pageObj.bidBrfsEvent(this);
			});
			pageObj.exmtYnEvent();
			pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
			$(".negoDiv").css({"display" : ""});
			$(".negoNotDiv").css({"display" : "none"});
			
			$("#P_ELGB_LMT_SCR").val("");
			$("select[name='P_ELGB_ESTM_KDCD_VIEW']").val("");
			$("#P_ELGB_ESTM_KDCD").val("");
			
			$(".sbmtDiv").css({"display" : ""});
			
			pageObj.prpdcEvent("Y");
		}else if($(obj).val() == '20'){	//제한적 최저가
			$(".grlDiv").css({"display" : ""});
			$(".elgbDiv").css({"display" : "none"});
			$(".negoDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$(".lwstDiv").css({"display" : ""});
			
			$("#P_TCHN_SCR_RT").val("");
			$("#P_PRCE_SCR_RT").val("");
			$("#P_ELGB_LMT_SCR").val("");
			$("select[name='P_ELGB_ESTM_KDCD_VIEW']").val("");
			$("#P_ELGB_ESTM_KDCD").val("");
			
			$(".sbmtDiv").css({"display" : ""});
			
			pageObj.prpdcEvent("N");
		}else if($(obj).val() == '33'){	//규격가격 동시
			$(".grlDiv").css({"display" : ""});
			$(".elgbDiv").css({"display" : "none"});
			$(".negoDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$("input[name='P_BID_BRFS_YN']:checked").each(function(){
				pageObj.bidBrfsEvent(this);
			});
			pageObj.exmtYnEvent();
			pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
			
			$("#P_TCHN_SCR_RT").val("");
			$("#P_PRCE_SCR_RT").val("");
			$("#P_ELGB_LMT_SCR").val("");
			$("select[name='P_ELGB_ESTM_KDCD_VIEW']").val("");
			$("#P_ELGB_ESTM_KDCD").val("");
			
			$(".sbmtDiv").css({"display" : ""});
			
			pageObj.prpdcEvent("Y");
		}else if($(obj).val() == '34'){	//2단계 평가
			$(".grlDiv").css({"display" : ""});
			$(".elgbDiv").css({"display" : "none"});
			$(".negoDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$("input[name='P_BID_BRFS_YN']:checked").each(function(){
				pageObj.bidBrfsEvent(this);
			});
			pageObj.exmtYnEvent();
			pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
			
			$("#P_TCHN_SCR_RT").val("");
			$("#P_PRCE_SCR_RT").val("");
			$("#P_ELGB_LMT_SCR").val("");
			$("select[name='P_ELGB_ESTM_KDCD_VIEW']").val("");
			$("#P_ELGB_ESTM_KDCD").val("");
			
			$(".sbmtDiv").css({"display" : "none"});
			
			pageObj.prpdcEvent("Y");
		}else {		//최저가
			$(".grlDiv").css({"display" : ""});
			$(".elgbDiv").css({"display" : "none"});
			$(".negoDiv").css({"display" : "none"});
			$(".datpDiv").css({"display" : "none"});
			$("input[name='P_BID_BRFS_YN']:checked").each(function(){
				pageObj.bidBrfsEvent(this);
			});
			pageObj.exmtYnEvent();
			pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
			
			$("#P_TCHN_SCR_RT").val("");
			$("#P_PRCE_SCR_RT").val("");
			$("#P_ELGB_LMT_SCR").val("");
			$("select[name='P_ELGB_ESTM_KDCD_VIEW']").val("");
			$("#P_ELGB_ESTM_KDCD").val("");
			
			$(".sbmtDiv").css({"display" : ""});
			
			pageObj.prpdcEvent("N");
		}
		
		if($(obj).val() == '40'){
			$("#negoTH").addClass("bullet_orange");
		}else{
			$("#negoTH").removeClass("bullet_orange");
		}
		
	};
	
	/**
	 * 예가 이벤트
	 */
	pageObj.estpcSecdEvent = function (obj) {
		if($(obj).val() != "180002"){
			$(".estpcDiv").css({"display" : ""});
		}else{
			$(".estpcDiv").css({"display" : "none"});
			$("#P_BASE_AMT").val("");
		}
		
		if($(obj).val() == "180000"){
			$(".prepDiv").css({"display" : ""});
			$('#P_PLR_ESTPC_RNG_CD').val("NTN");
		}else{
			$('#P_PLR_ESTPC_RNG_CD').val("");
			$(".prepDiv").css({"display" : "none"});
			$("#P_PLR_ESTPC_RNG_CD").val("");
			$("#P_ESTPC_UP_CNT").val("");
		}
	};
	
	
	/**
	 * 입찰설명회 여부에 따른 이벤트 발생시
	 * 1. 입찰설명회 여부가 '예(의무)', 예(비의무) 일때 입찰설명회 장소, 일시 보이기
	 */
	pageObj.bidBrfsEvent = function (obj) {
		if($(obj).val() == "Y"){
			$(".brfsDiv").css({"display" : ""});
		}else{
			$(".brfsDiv").css({"display" : "none"});
			$("#P_BRFS_PLC_NM").val("");
			$("#P_BRFS_DT").val("");
			$("#P_BRFS_DT_HH").val("");
			$("#P_BRFS_DT_MM").val("");
		}
	};
	
	/**
	 * 현장설명회 여부에 따른 이벤트 발생시
	 */
	pageObj.siteBrfsEvent = function (obj) {
		if($(obj).val() == "Y"){
			$(".siteBrfsDiv").css({"display" : ""});
		}else{
			$(".siteBrfsDiv").css({"display" : "none"});
			$("#P_SITE_BRFS_DT").val("");
			$("#P_SITE_BRFS_DT_HH").val("");
			$("#P_SITE_BRFS_DT_MM").val("");
			$("#P_SITE_BRFS_PLC").val("");
		}
	};
	
	/**  
     * <pre>
     * 1. 개요 : 제안서 여부 변경 이벤트
     * 2. 처리내용 : 
     * </pre>
     * @Function Name : prpdcEvent
     * @date : 2019. 02. 14.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2019. 02. 14.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */	
	pageObj.prpdcEvent = function ($val) {
		//var $val = $(obj).val();
		if($val == "Y") {
			$('.prpdcDiv').css({'display':''});
			$('input[name=P_PRPDC_ESS_YN]').val("Y");
			$($('input:radio[name=P_PRPDC_ESS_YN_DISP]:radio[value=Y]')).prop({'checked':true});
			$($('input:radio[name=P_PRPDC_ESS_YN_DISP]:radio[value=N]')).prop({'checked':false});
		}else {
			$('.prpdcDiv').css({'display':'none'});
			$('input[name=P_PRPDC_ESS_YN]').val("N");
			$($('input:radio[name=P_PRPDC_ESS_YN_DISP]:radio[value=Y]')).prop({'checked':false});
			$($('input:radio[name=P_PRPDC_ESS_YN_DISP]:radio[value=N]')).prop({'checked':true});
		}
	};		
	
	/**
	 * 시간 텍스트 입력에 따른 이벤트
	 * 1. 0~23시까지 입력 가능
	 * 2. 한자리 숫자 입력 했을시 앞에 0 추가 ex) 01
	 */
	pageObj.hourEvent = function (obj) {
		if(parseInt(obj.value) >= 24){
			alert("0 ~ 23 까지 입력하실 수 있습니다.");
			obj.value = "";
			obj.focus();
			return false;
		}
		if( obj.value.length == 1){
			obj.value = "0"+obj.value;
		}
	};
	
	/**
	 * 분 텍스트 입력에 따른 이벤트
	 * 1. 0~59분까지 입력 가능
	 * 2. 한자리 숫자 입력 했을시 앞에 0 추가 ex) 01
	 */
	pageObj.minuteEvent = function (obj) {
		if(parseInt(obj.value) >= 60){
			alert("0 ~ 59 까지 입력하실 수 있습니다.");
			obj.value = "";
			obj.focus();
			return false;
		}
		if( obj.value.length == 1){
			obj.value = "0"+obj.value;
		}
	};

	/**  
	 * <pre>
	 * 1. 개요 : 하이픈 제거
	 * 2. 처리내용 : 
	 * 		하이픈을 제거한 값을 반환한다.
	 * </pre>
	 * @Function Name : hyphenDelete
	 * @date : 2015. 04. 28.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 04. 28.       은우소프트 전상훈                    최초 작성 
	 *  =======================================================   
	 */
	hyphenDelete = function (str) {
		return str.replace(/-/gi,"");
	};
	
	pageObj.download = function(fileSn, fileGrpNo){
		$("#downFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downFrm", "/comm/download.do");
	};
	
	/**
	 *  파일 삭제
	 */
	// 처음 일반파일 삭제
	rowDel = function(obj){
		$(obj).closest('tr').remove();
	};
	
	// 처음 필수파일 삭제
	rowRegDel = function(obj){
		$(obj).closest('tr').css("text-decoration","line-through");
		$(obj).closest('tr').find(".fileView").attr("disabled", true);
		$(obj).closest('tr').find(".fileView").css("display","none");
		$(obj).closest('tr').find(".fileDel").css("display","");
	};
	
	// 처음 필수파일 취소
	rowCnclDel = function(obj){
		$(obj).closest('tr').css("text-decoration","");
		$(obj).closest('tr').find(".fileView").attr("disabled", false);
		$(obj).closest('tr').find(".fileView").css("display","");
		$(obj).closest('tr').find(".fileDel").css("display","none");
	};
	
	/**
	 *  파일 추가
	 */
	rowAdd = function( fileDiv, fileRow, fileName, fileDocName ){
		var row = $("#"+fileRow).children().clone();
		row.find("input[type='text']").attr("name",fileDocName);
		row.find("input[type='file']").attr("name",fileName); 
		$("#"+fileDiv).append(row);
	};
	
	//기존 일반 파일 삭제
	rowFileDel = function(obj, sn){
		if($("#P_DEL_SN").val() == ""){
			$("#P_DEL_SN").val(sn);
		}else{
			$("#P_DEL_SN").val($("#P_DEL_SN").val()+","+sn);
		}
		$(obj).closest('tr').css("text-decoration","line-through");
		$(obj).closest('tr').find(".fileView").attr("disabled", true);
		$(obj).closest('tr').find(".fileViewBtn").css("display","none");
		$(obj).closest('tr').find(".fileDelBtn").css("display","");
	};
	
	//기존 일반 파일 취소
	rowFileCncl = function(obj, sn){
		var P_DEL_SN = $("#P_DEL_SN").val();
		$("#P_DEL_SN").val(P_DEL_SN.replace(sn,""));
		$(obj).closest('tr').css("text-decoration","");
		$(obj).closest('tr').find(".fileView").attr("disabled", true);
		$(obj).closest('tr').find(".fileViewBtn").css("display","");
		$(obj).closest('tr').find(".fileDelBtn").css("display","none");
	};
	
	// 기존 필수파일 삭제
	rowRedFileDel = function(obj, sn){
		if($("#P_RED_DEL_SN").val() == ""){
			$("#P_RED_DEL_SN").val(sn);
		}else{
			$("#P_RED_DEL_SN").val($("#P_RED_DEL_SN").val()+","+sn);
		}
		$(obj).closest('tr').css("text-decoration","line-through");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","none");
		$(obj).closest('tr').find(".redDelBtn").css("display","");
	};
	
	// 기존 필수파일 취소
	rowRedFileCncl = function(obj, sn){
		var P_RED_DEL_SN = $("#P_RED_DEL_SN").val();
		$("#P_RED_DEL_SN").val(P_RED_DEL_SN.replace(sn,""));
		$(obj).closest('tr').css("text-decoration","");
		$(obj).closest('tr').find(".redDelBtn").css("display","none");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","");
	};
	
	// 기존 필수파일 변경
	rowRedFileChng = function(obj, sn){
		if($("#P_RED_DEL_SN").val() == ""){
			$("#P_RED_DEL_SN").val(sn);
		}else{
			$("#P_RED_DEL_SN").val($("#P_RED_DEL_SN").val()+","+sn);
		}
		$(obj).closest('tr').find(".redFileView").css("display","none");
		$(obj).closest('tr').find(".redFile").css("display","");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","none");
		$(obj).closest('tr').find(".redFileBtn").css("display","");
		$(obj).closest('tr').find("input[name='P_RED_FILE_DOC_SECD']").attr("disabled",false);
		$(obj).closest('tr').find("input[type='file']").attr("disabled",false);
	};
	
	// 기존 필수파일 변경 취소
	rowRedFileChngCncl = function(obj, sn){
		var P_RED_DEL_SN = $("#P_RED_DEL_SN").val();
		$("#P_RED_DEL_SN").val(P_RED_DEL_SN.replace(sn,""));
		$(obj).closest('tr').find(".redFile").css("display","none");
		$(obj).closest('tr').find(".redFileView").css("display","");
		$(obj).closest('tr').find(".redFileBtn").css("display","none");
		$(obj).closest('tr').find(".redFileViewBtn").css("display","");
		$(obj).closest('tr').find("input[name='P_RED_FILE_DOC_SECD']").attr("disabled",true);
		$(obj).closest('tr').find("input[type='file']").attr("disabled",true);
	};
	
	
	bidcSbmtEndtOpngDt = function(obj){
		var result = true;
		if($("#P_BIDC_SBMT_ENDT").val() != "" && $("#P_BIDC_SBMT_ENDT_HH").val() != "" && $("#P_BIDC_SBMT_ENDT_MM").val() != "" 
			&& $("#P_OPNG_DT").val() != "" && $("#P_OPNG_DT_HH").val() != "" && $("#P_OPNG_DT_MM").val() != ""){
			var bidcSbmtEndt = hyphenDelete($("#P_BIDC_SBMT_ENDT").val())+$("#P_BIDC_SBMT_ENDT_HH").val()+$("#P_BIDC_SBMT_ENDT_MM").val();
			var opngDt = hyphenDelete($("#P_OPNG_DT").val())+$("#P_OPNG_DT_HH").val()+$("#P_OPNG_DT_MM").val();
			if(Number(bidcSbmtEndt) > Number(opngDt)){
				alert(FwkMssageUtil.getMessage("EW.INF.002", "개찰일시", "입찰서제출마감일시"));
				$(obj).val("");
				$("#P_ANNC_DT").focus();
				$(obj).focus();
				result = false;
			}
		}
		return result;
	};
	
	bidcSbmtEndtOpngDt2 = function(obj){
		var result = true;
		if($("#P_BIDC_SBMT_ENDT2").val() != "" && $("#P_BIDC_SBMT_ENDT_HH2").val() != "" && $("#P_BIDC_SBMT_ENDT_MM2").val() != "" 
			&& $("#P_OPNG_DT2").val() != "" && $("#P_OPNG_DT_HH2").val() != "" && $("#P_OPNG_DT_MM2").val() != ""){
			var bidcSbmtEndt = hyphenDelete($("#P_BIDC_SBMT_ENDT2").val())+$("#P_BIDC_SBMT_ENDT_HH2").val()+$("#P_BIDC_SBMT_ENDT_MM2").val();
			var opngDt = hyphenDelete($("#P_OPNG_DT2").val())+$("#P_OPNG_DT_HH2").val()+$("#P_OPNG_DT_MM2").val();
			if(Number(bidcSbmtEndt) > Number(opngDt)){
				alert(FwkMssageUtil.getMessage("EW.INF.002", "개찰일시", "역경매마감일시"));
				$(obj).val("");
				$("#P_ANNC_DT").focus();
				$(obj).focus();
				result = false;
			}
		}
		return result;
	};
	
	pageObj.exmtYnEvent = function(){
		if($("input[name='P_EXMT_YN']:checked").val() == "Y"){
			$(".exmtNDiv").css("display","none");
			$("#P_BIDGR_SECD_VIEW").val("");
			$("#P_BIDGR_SECD").val("PAY_MMRD");
			$("#P_BID_GTAMT_RT").val("");
			$("#P_BIDGR_AMT").val("");
		}else{
			$(".exmtNDiv").css("display","");
			$("#P_BIDGR_SECD").val("");
		}
	};
	
	delRedFile = function(str) {
		if($("#"+str).val() != undefined ){
			$("#P_RED_DEL_SN").val($("#P_RED_DEL_SN").val()+","+$("#"+str).val());
		}
	};
	
	
	/**
	 * 2019-07-19 은잔디 추가
	 * 계약기간(TEXT) 입력 시 납품기한에 해당 값 자동세팅
	 * @param obj
	 */
	contTeEvent = function(obj){
		var contTeText = $(obj).val();
		$("#P_DLGD_TE_CNTN").val(contTeText);
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰계획목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		//구매담당자 버튼
		$("#chargerBtn").on("click", function() {
			$("#popupFrm input[name='P_USER_NM_S']").val($("#userNm").val());
			$("#popupFrm input[name='setMulti']").val("N");
			$("#popupFrm input[name='tchnChangerYn']").val("N");
			pageObj.chargerInqirePopup();
		});
		
		//기술담당자 버튼
		$("#tchnChargerBtn").on("click", function() {
			$("#popupFrm input[name='P_USER_NM_S']").val($("#userNm").val());
			$("#popupFrm input[name='setMulti']").val("N");
			$("#popupFrm input[name='tchnChangerYn']").val("Y");
			pageObj.chargerInqirePopup();
		});		

		// 입찰구분 선택에 따른 체인지 이벤트
		$("#P_CONT_SECD").on("change", function() {
			pageObj.contSecdEvent(this);
		});
		
		//긴급 입찰 이벤트
		$("input[name='P_EMRG_YN']").on("click", function() {
			pageObj.emrgEvent(this);
		});
		
		//  계약방법에 따른 클릭 이벤트
		$("#P_CONT_MTCD").on("click", function() {
			pageObj.bidMtcdEvent(this);
		}).on("change", function(){
			pageObj.bidMtcdEvent(this);
		});
		
		// 낙찰자 선정 방식 선택에 따른 클릭/체인지 이벤트
		$("#P_SBID_MTCD").on("click", function() {
			pageObj.sbidMtcdEvent(this);
		}).on("change", function(){
			pageObj.sbidMtcdEvent(this);
		});
		
		//예가 이벤트
		$("#P_ESTPC_SECD").on("click", function() {
			pageObj.estpcSecdEvent(this);
		}).on("change", function(){
			pageObj.estpcSecdEvent(this);
		});

		//  입찰설명회 여부에 따른 클릭 이벤트
		$("input[name='P_BID_BRFS_YN']").on("click", function() {
			pageObj.bidBrfsEvent(this);
		});
		
		// 현장설명회 이벤트
		$("input[name='P_SITE_BRFS_YN']").on("click", function() {
			pageObj.siteBrfsEvent(this);
		});
		
		//  지명업체 찾기에 따른 클릭 이벤트
		$("#nmenSearchBtn").on("click", function() {
			pageObj.nmenSearchEvent();
		});
		
		//  지명업체 삭제에 따른 클릭 이벤트
		$("#nmenDeleteBtn").on("click", function() {
			pageObj.nmenDeleteEvent();
		});
		
		//  입찰품목 찾기에 따른 클릭 이벤트
		$("#biprInfoSearchBtn").on("click", function() {
			pageObj.prdlstListPopup();
		});
		
		//  입찰품목 삭제에 따른 클릭 이벤트
		$("#biprInfoDeleteBtn").on("click", function() {
			pageObj.biprInfoDeleteEvent();정
		});
		
		//제안서 제출 필수여부 관련 영역 선택
		$('input[name=P_PRPDC_ESS_YN]').on('change', function() {
			//pageObj.prpdcEvent(this);
		});		
		
		//  시/분 텍스트박스에 대한 입력 이벤트
		$("input[name*='_HH']").on("blur", function() {
			pageObj.hourEvent(this);
			hhCheckToday(this);
		});
		$("input[name*='_MM']").on("blur", function() {
			pageObj.minuteEvent(this);
			mmCheckToday(this);
		});
		
		$("[date]").on("blur", function() {
			inputCheckToday(this);
		}).on("change", function() {
			inputCheckToday(this);
		});
		
		$(".edOpDt").on("blur", function() {
			bidcSbmtEndtOpngDt(this);
		}).on("change", function() {
			bidcSbmtEndtOpngDt(this);
		});
		
		$(".edOpDt2").on("blur", function() {
			bidcSbmtEndtOpngDt2(this);
		}).on("change", function() {
			bidcSbmtEndtOpngDt2(this);
		});
		
		// 목록버튼
		$("#cancelBtn").on("click", function() {
			if($("#P_ANNC_NO").val() == "" && $("#P_BID_PSCD").val() == "PE10"){
				if(!confirm(FwkMssageUtil.getMessage("EW.CON.001", "목록"))){
					return false;
				}
				
				pageObj.bidPlanList();
			}else{
				if(!confirm(FwkMssageUtil.getMessage("EW.CON.001", "상세화면"))){
					return false;
				}
				
				pageObj.bidPlanDetail();
			}
		});
		
		// 입찰계획 수정 버튼
		$("#UpdateBtn").on("click", function() {
			// 필수체크
			if($("#P_SBID_MTCD").val() != "70" ){
				if(!required_ew()){
					return false;
				}
			}else{
				if(!required_ew2()){
					return false;
				}
			}
	        
			if($("#P_CONT_MTCD").val() == "10001"){
				if($("#nmenChoiseShowTbdy tr").length == 0){
					alert("[지명업체] 항목은 필수선택 입니다.");
					return false;
				}
			}
			
			if($("#P_SBID_MTCD").val() != "70" ){
				var date = false;
				$(".grlDiv [date]").each(function(){
					if(!inputCheckToday(this)){
						data = true;
						return false;
					}
				});
				if(date){
					return false;
				}
				
				var hh = false;
				$(".grlDiv input[name*='_HH']").each(function(){
					if(!hhCheckToday(this)){
						hh = true;
						return false;
					}
				});
				if(hh){
					return false;
				}
				
				var mm = false;
				$(".grlDiv input[name*='_MM']").each(function(){
					if(!mmCheckToday(this)){
						mm = true;
						return false;
					}
				});
				if(mm){
					return false;
				}
				
				if(!bidcSbmtEndtOpngDt($("#P_OPNG_DT_MM"))){
					return false;
				}
			}else{
				var date = false;
				$(".datpDiv [date]").each(function(){
					if(!inputCheckToday(this)){
						data = true;
						return false;
					}
				});
				if(date){
					return false;
				}
				
				var hh = false;
				$(".datpDiv input[name*='_HH']").each(function(){
					if(!hhCheckToday(this)){
						hh = true;
						return false;
					}
				});
				if(hh){
					return false;
				}
				
				var mm = false;
				$(".datpDiv input[name*='_MM']").each(function(){
					if(!mmCheckToday(this)){
						mm = true;
						return false;
					}
				});
				if(mm){
					return false;
				}
				
				if(!bidcSbmtEndtOpngDt2($("#P_OPNG_DT_MM2"))){
					return false;
				}
			}
	        
			if($("#P_CONT_SECD").val() == "0"){	// 구매(0)
				if($("#biprInfoShowTbdy tr").length == 0){
					alert("[입찰품목] 항목은 필수선택 입니다.");
					return false ;
				}
				var qyChk = false;
				$("input[name='P_ITEM_QTY']").each(function(index) {
					if(index > 0){
						if($(this).val() == ""){
							qyChk = true;
						}
					}
				});
				
				if(qyChk){
					alert("[수량] 항목은 필수입력 입니다.");
					return false;
				}
			}
			
			var fileBool = false;
			$("#redFileShowTbdy input[name='P_RED_BID_FILE']").each(function(){
				if($(this).css("display") != "none"){
					if($(this).val() == ""){
						alert("[필수첨부파일] 항목은 필수입니다.");
						fileBool = true;
						return false;
					}
				}
			});
				
			if(fileBool){
				return false;
			}
			
			var fileDocSecdAt = true;
			$("input[name='P_FILE_DOC_NM']").each(function(){
				if($(this).val() == ""){
					fileDocSecdAt = false;
					return false;
				}
			});
			if(fileDocSecdAt == false){
				alert("[문서구분] 항목은 필수입니다");
				return false;
			}
			
			var bidFileAt = true;
			$("input[name='P_BID_FILE']").each(function(){
				if($(this).val() == ""){
					bidFileAt = false;
					return false;
				}
			});
			if(bidFileAt == false){
				alert("[기타첨부파일] 항목은 필수입니다.");
				return false;
			}
			
			var orgFileDocSecdAt = true;
			$("select[name='P_ORG_FILE_DOC_SECD']").each(function(){
				if($(this).val() == ""){
					orgFileDocSecdAt = false;
					return false;
				}
			});
			if(orgFileDocSecdAt == false){
				alert("[문서구분] 항목은 필수입니다");
				return false;
			}
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.003", "입찰계획", "수정"))){
				return false;
			}
			
			removeComma();
			
			if($("#P_SBID_MTCD").val() != "70" ){
				$(".datpDiv").remove();
			}else{
				$(".grlDiv").remove();
			}
			
			if($("#P_CONT_SECD").val() == "0"){
				$("#P_CONT_DECD1").remove();
			}else if($("#P_CONT_SECD").val() == "1"){
				$("#P_CONT_DECD0").remove();
			}else if($("#P_CONT_SECD").val() == "2"){
				$("#P_CONT_DECD0").remove();
				$("#P_CONT_DECD1").remove();
			}
			
			pageObj.bidPlanUpdt();
		});
		
		$("#bidFileBtn").on("click", function(){
			rowAdd("fileShowTbdy", "fileHideTbdy", "P_BID_FILE", "P_FILE_DOC_NM");
		});
		
		$("#P_ESTT_AMT").on("blur",function(){
			if($("#P_ESTT_AMT").val()){
				var esttAmt = Number(FwkCmmnUtil.deleteComma($("#P_ESTT_AMT").val()));
				$("#P_ESTT_PRCE").val(FwkCmmnUtil.addComma(Math.round(esttAmt/1.1)));
			}
		});
		
		$("#P_ANNC_SECD").on("click", function(){
			if($("#P_ANNC_SECD").val() == "G2B_BID"){
				$("#P_OPNG_SYS_SECD").val("G2B");	
			}else{
				$("#P_OPNG_SYS_SECD").val("EBS");
			}
			if($("#P_ANNC_SECD").val() == "HNDW_BID"){
				$(".hndwDiv").css("display","");
			}else{
				$(".hndwDiv").css("display","none");
			}
		});
		
		$("#P_CONT_STDE").on("blur", function(){
			if($("#P_CONT_STDE").val()+$("#P_CONT_ENDE").val() == "" ){
				$(".cdtDiv").css("display","");
			}else{
				$(".cdtDiv").css("display","none");
			}
		}).on("change", function(){
			if($("#P_CONT_STDE").val()+$("#P_CONT_ENDE").val() == "" ){
				$(".cdtDiv").css("display","");
			}else{
				$(".cdtDiv").css("display","none");
			}
		});
		
		$("#P_CONT_ENDE").on("blur", function(){
			if($("#P_CONT_STDE").val()+$("#P_CONT_ENDE").val() == "" ){
				$(".cdtDiv").css("display","");
			}else{
				$(".cdtDiv").css("display","none");
			}
		}).on("change", function(){
			if($("#P_CONT_STDE").val()+$("#P_CONT_ENDE").val() == "" ){
				$(".cdtDiv").css("display","");
			}else{
				$(".cdtDiv").css("display","none");
			}
		});
		
		$("#P_CONT_TE").on("blur", function(){
			if($("#P_CONT_TE").val() == "" ){
				$(".cddDiv").css("display","");
			}else{
				$(".cddDiv").css("display","none");
			}
		});
		
		$("input[name='P_EXMT_YN']").on("click", function() {
			pageObj.exmtYnEvent();
		});
		
		$("#P_BIDGR_SECD_VIEW").on("change", function() {
			$("#P_BIDGR_SECD").val($("#P_BIDGR_SECD_VIEW").val());
		});
		
		$("#P_BID_GTAMT_RT").on("blur", function(){
			if($("#P_BID_GTAMT_RT").val() == "" ){
				$(".bidgrAmtDiv").css("display","");
			}else{
				$(".bidgrAmtDiv").css("display","none");
			}
		});
		
		$("#P_BIDGR_AMT").on("blur", function(){
			if($("#P_BIDGR_AMT").val() == "" ){
				$(".bidgrRtDiv").css("display","");
			}else{
				$(".bidgrRtDiv").css("display","none");
			}
		});
		
		$("#P_TCHN_SCR_RT").on("blur", function(){
			if($("#P_TCHN_SCR_RT").val() != ""){
				if(Number($("#P_TCHN_SCR_RT").val()) == 0){
					alert("기술점수율은 0이 될 수 업습니다.");
					$("#P_TCHN_SCR_RT").val("");
					$("#P_TCHN_SCR_RT").focus();
					return false;
				}
				if($("#P_PRCE_SCR_RT").val() != ""){
					if((Number($("#P_TCHN_SCR_RT").val()) + Number($("#P_PRCE_SCR_RT").val())) != 10){
						alert("기술점수율과 가격점수율의 합은 10이 되야 합니다.");
						$("#P_PRCE_SCR_RT").val("");
						$("#P_TCHN_SCR_RT").focus();
						return false;
					}
				}
			}
		});
		
		$("#P_PRCE_SCR_RT").on("blur", function(){
			if($("#P_PRCE_SCR_RT").val() != ""){
				if(Number($("#P_PRCE_SCR_RT").val()) == 0){
					alert("가격점수율은 0이 될 수 업습니다.");
					$("#P_PRCE_SCR_RT").val("");
					$("#P_PRCE_SCR_RT").focus();
					return false;
				}
				if($("#P_TCHN_SCR_RT").val() != ""){
					if((Number($("#P_TCHN_SCR_RT").val()) + Number($("#P_PRCE_SCR_RT").val())) != 10){
						alert("기술점수율과 가격점수율의 합은 10이 되야 합니다.");
						$("#P_PRCE_SCR_RT").val("");
						$("#P_PRCE_SCR_RT").focus();
						return false;
					}
				}
			}
		});
		
		$("#P_ESTPC_UP_CNT").on("blur", function(){
			if($("#P_ESTPC_UP_CNT").val() != ""){
				if(Number($("#P_ESTPC_UP_CNT").val()) < 1 || Number($("#P_ESTPC_UP_CNT").val()) > 14){
					alert("1 ~ 14 까지 입력하실 수 있습니다.");
					$("#P_ESTPC_UP_CNT").val("");
					$("#P_ESTPC_UP_CNT").focus();
					return false;
				}
			}
		});
		
		$("select[name='P_ELGB_ESTM_KDCD_VIEW']").on("change", function(){
			$("#elgbEstmKdcdFrm input[name='P_ELGB_ESTM_KDCD']").val($(this).val());
			
			var jsonData = $("#elgbEstmKdcdFrm").serializeObject();
			var actionUrl = "/ebid/elgbEstmKd";

			FwkCmmnUtil.submitAjax (actionUrl, jsonData
					, function(res) {
						$("#P_SBID_LWST_RT").val(res.elgbEstmKd.SBID_LWST_RT);
						$("#P_ELGB_LMT_SCR").val(res.elgbEstmKd.ELGB_LMT_SCR);
						$("#P_ELGB_ESTM_KDCD").val(res.elgbEstmKd.ELGB_ESTM_KDCD);
			});
		});
		
		$("#P_BASE_AMT").on("blur",function(){
			if($("#P_BASE_AMT").val() != ""){
				if($(".baseAmtFile").length == 0){
					var html = "<tr class='baseAmtFile'>"
										+"<td>기초금액조서<input type='hidden' name='P_RED_FILE_DOC_SECD' value='FILE12'></td>"
										+"<td><input type='file' name='P_RED_BID_FILE'></td>"
//										+"<td><button type='button' class='btn btn_02 btn_sch' onclick='rowDel(this)'>삭제</button></td>"
									+"</tr>";
					$("#redFileShowTbdy").append(html);
				}
			}else{
				delRedFile("FILE12");
				$(".FILE12").remove();
				$(".baseAmtFile").remove();
			}
		});
		
		$("#P_CONT_DECD1").on("change", function(){
			if($("#P_CONT_DECD1").val() == "TCHN"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcTchnDiv").css("display","");
			}else if($("#P_CONT_DECD1").val() == "ACDM"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcAcdmDiv").css("display","");
			}else if($("#P_CONT_DECD1").val() == "FCLT"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcFcltDiv").css("display","");
			}else if($("#P_CONT_DECD1").val() == "INFO"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcInfoDiv").css("display","");
			}else if($("#P_CONT_DECD1").val() == "WST"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcWstDiv").css("display","");
			}else if($("#P_CONT_DECD1").val() == "LAND"){
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
				$(".svcLandDiv").css("display","");
			}else{
				$(".svcKdDiv").val("");
				$(".svcKdDiv").css("display","none");
			}
		});
		
		$("input[name*='P_BIDC_SBMT_ENDT']").on("blur", function() {
			if($("#P_BIDC_SBMT_ENDT").val() != "" && $("#P_BIDC_SBMT_ENDT_HH").val() != "" && $("#P_BIDC_SBMT_ENDT_MM").val() != ""){
				var ymd = $("#P_BIDC_SBMT_ENDT").val();
				var hh = $("#P_BIDC_SBMT_ENDT_HH").val();
				if(hh == "23"){
					hh = "00";
					$("#P_BIDC_SBMT_ENDT").val() + 1;
					ymd = FwkDateUtil.addDate(ymd, 1);
				}else{
					hh = Number(hh)+1;
					if(Number(hh) < 10){
						hh = "0"+hh;
					}
				}
				$("#P_OPNG_DT").val(ymd);
				$("#P_OPNG_DT_HH").val(hh);
				$("#P_OPNG_DT_MM").val($("#P_BIDC_SBMT_ENDT_MM").val());
			}
		});
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
	
		pageObj.contSecdEvent($("#P_CONT_SECD"));
		$("input[name='P_EMRG_YN']:checked").each(function(){
			pageObj.emrgEvent(this);
		});
		pageObj.bidMtcdEvent($("#P_CONT_MTCD"));
		pageObj.estpcSecdEvent($("#P_ESTPC_SECD"));
		$("input[name='P_BID_BRFS_YN']:checked").each(function(){
			pageObj.bidBrfsEvent(this);
		});
		if($("#P_CONT_SECD").val() == '2'){
			$("input[name='P_SITE_BRFS_YN']:checked").each(function(){
				pageObj.siteBrfsEvent(this);
			});
		}
		
		if($("#P_CONT_STDE").val()+$("#P_CONT_ENDE").val() == "" ){
			$(".cdtDiv").css("display","");
		}else{
			$(".cdtDiv").css("display","none");
		}
		if($("#P_CONT_TE").val() == "" ){
			$(".cddDiv").css("display","");
		}else{
			$(".cddDiv").css("display","none");
		}
		if($("#P_BID_GTAMT_RT").val() == "" ){
			$(".bidgrAmtDiv").css("display","");
		}else{
			$(".bidgrAmtDiv").css("display","none");
		}
		if($("#P_BIDGR_AMT").val() == "" ){
			$(".bidgrRtDiv").css("display","");
		}else{
			$(".bidgrRtDiv").css("display","none");
		}
		if($("#P_ANNC_SECD").val() == "G2B_BID"){
			$("#P_OPNG_SYS_SECD").val("G2B");
		}else{
			$("#P_OPNG_SYS_SECD").val("EBS");
		}
		if($("#P_ANNC_SECD").val() == "HNDW_BID"){
			$(".hndwDiv").css("display","");
		}else{
			$(".hndwDiv").css("display","none");
		}
		if($("#P_CONT_DECD1").val() == "TCHN"){
			$(".svcKdDiv").css("display","none");
			$(".svcTchnDiv").css("display","");
		}else if($("#P_CONT_DECD1").val() == "ACDM"){
			$(".svcKdDiv").css("display","none");
			$(".svcAcdmDiv").css("display","");
		}else if($("#P_CONT_DECD1").val() == "FCLT"){
			$(".svcKdDiv").css("display","none");
			$(".svcFcltDiv").css("display","");
		}else if($("#P_CONT_DECD1").val() == "INFO"){
			$(".svcKdDiv").css("display","none");
			$(".svcInfoDiv").css("display","");
		}else if($("#P_CONT_DECD1").val() == "WST"){
			$(".svcKdDiv").css("display","none");
			$(".svcWstDiv").css("display","");
		}else if($("#P_CONT_DECD1").val() == "LAND"){
			$(".svcKdDiv").css("display","none");
			$(".svcLandDiv").css("display","");
		}else{
			$(".svcKdDiv").css("display","none");
		}
		pageObj.sbidMtcdEvent($("#P_SBID_MTCD"));
		
		fileView($("#P_FILE_GRP_NO").val());
		noViewFileModify("bidPblancUpdtFrm", "P_FILE_GRP_NO", "P_ATCHMNFL_GROUP_NEW", "/ebid/updtPblancRegist.do","menuMoveFrm", "/ebid/bidPblancList.do");		
		
		//P_PRPDC_ESS_YN 선택 불가하도록
		$('input[name=P_PRPDC_ESS_YN_DISP]').attr({'disabled':true});		
	});
})();