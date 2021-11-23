/**
 * 공통 > 견적의뢰업체추가
 *
 * <pre>
 * comm
 *    |_popup
 *        |_prpoEntrpsList.js
 * 
 * </pre>
 * @date : 2017. 07.12
 * @version : 1.0
 * @author : 은우소프트 홍찬일
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	
	defaultFrm = "searchFrm";
	pageObj.selectPrpoEntrpsInqireList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/prpoEntrpsList.do");
		
	};
	/**  
     * <pre>
     * 1. 개요 : 대 중 소 셀렉트박스 셋팅함수.
     * 2. 처리내용 :
     *  	
     * </pre>
     * @Function Name : typeSet
     * @date : 2017. 07.12
     * @author : 은우소프트 
     * @history : 
     *  ===================================================================
     *  변경일             		작성자                     		변경내용  
     *  ===================================================================
     *  2017. 07.12        	    홍찬일	                        최초 작성 
     *  ===================================================================
     */
	typeSet = function(gbn){
		var temp = $("#"+gbn).val().split(",");
		var temp2 = "<option>선택</option>";
		if(gbn.length == '2'){
			$("#type2").empty();
			$("#type3").empty();
			$("#type3").append(temp2);
			$(temp).each(function(index, item){
				temp2 += "<option onclick='typeSet(\""+gbn+"B"+(index+1)+"\")'>"+item+"</option>";
			});
			$("#type2").append(temp2);
		}else{
			$("#type3").empty();
			$(temp).each(function(index, item){
				temp2 += "<option>"+item+"</option>";
			});
			$("#type3").append(temp2);
		}
	};
	check = function(obj){
		if($(obj).children().eq(0).children().is(':checked') == true){
			$(obj).children().eq(0).children().prop("checked", false);
		}else{
			$(obj).children().eq(0).children().prop("checked", true);
		}
	};
	/**  
     * <pre>
     * 1. 개요 : 사용자선택
     * 2. 처리내용 : 
     *  	- 선택된 사용자를 지정업체로 이동한다.
     *  
     * </pre>
     * @Function Name : userSeleced
     * @date : 2017. 07. 12
     * @author : 은우소프트 홍찬일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017. 07. 12       은우소프트 홍찬일              최초 작성 
     *  =======================================================   
     */
	pageObj.userSelected = function() {
		$("input[name='Cbx']").each(function(index) {
			if($(this).prop("checked")){
				var flag = true;
				var tempDeptCode  = $(this).parent().next().text();
				var tempDeptNo    = $(this).parent().next().next().text();
				var tempDeptNm    = $(this).parent().next().next().next().text();
				var tempUserNm    = $(this).parent().next().next().next().next().text();
				var tempUserEmail = $(this).parent().next().next().next().next().next().text();
				var tempUserTel   = $(this).parent().next().next().next().next().next().next().text();
				$("#showTr input[name='deptNo']").each(function(index) {
					if($(this).val() == tempDeptNo){
						flag = false;
					};
				});
				if(flag){
					var tr = $("#hideTr").clone();
					
					tr.find("input[name='Cbx2Temp']").attr("name","Cbx2");
					tr.find("input[name='deptNo']").val(tempDeptNo);
					tr.find("input[name='deptNm']").val(tempDeptNm);
					tr.find("input[name='deptCode']").val(tempDeptCode);
					tr.find("input[name='userNm']").val(tempUserNm);
					tr.find("input[name='userEmail']").val(tempUserEmail);
					tr.find("input[name='userTel']").val(tempUserTel);
					tr.css("display","");
					
					$("#showTr").append(tr);
					
					$(this).removeAttr("checked");
				}
			}
		});
	};
	
	/**  
     * <pre>
     * 1. 개요 : 사용자선택삭제
     * 2. 처리내용 : 
     *  	- 지정업체에 있는 사용자를 삭제한다.
     *  
     * </pre>
     * @Function Name : userUnSelected
    * @date : 2017. 07. 12
     * @author : 은우소프트 홍찬일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017. 07. 12       은우소프트 홍찬일              최초 작성 
     *  =======================================================   
     */
	pageObj.userUnSelected = function() {
		//선택된 만큼 Row삭제
		$($("input[name='Cbx2']")).each(function(index) {
			if($(this).prop("checked")){
				$(this).parent().parent().remove();
			}
		});
	};
	/**  
	 * <pre>
	 * 1. 개요 : 사용자선택삭제
	 * 2. 처리내용 : 
	 *  	- 지정업체에 있는 사용자를 삭제한다.
	 *  
	 * </pre>
	 * @Function Name : userUnSelected
	 * @date : 2017. 07. 12
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2017. 07. 12       은우소프트 홍찬일              최초 작성 
	 *  =======================================================   
	 */
	pageObj.deptSet = function() {
		var temp = "";
		var flag = true;
		var entrpsNoArray = $("#ENTRPS_REGIST_NO_CHK_LIST").val().split(",");
		$("#showTr tr").each(function(index) {
			flag = true;
			for(var idx = 0; idx < entrpsNoArray.length; idx++) {
				if(entrpsNoArray[idx] == $(this).children().eq(3).children().val()){
					flag = false; 
				}
			};
			if(flag){
				temp += "<tr class='row'>";
				temp += "<td style='text-align: center;'><input type='checkbox' name='itemChoiseCbx'></td>";
				temp += "<td style='text-align: center;'>"+$(this).children().eq(1).children().val()+"<input type='hidden' name='P_BIZRNO' value='"+$(this).children().eq(1).children().val()+"'/></td>";
				temp += "<td style='text-align: left;'>"+$(this).children().eq(2).children().val()+"<input type='hidden' name='P_ENTRPS_NM' value='"+$(this).children().eq(2).children().val()+"'/></td>";
				temp += "<td style='text-align: center;'>"+$(this).children().eq(4).children().val()+"<input type='hidden' name='P_CHARGER_NM' value='"+$(this).children().eq(4).children().val()+"'/></td>";
				temp += "<td style='text-align: left;'>"+$(this).children().eq(5).children().val()+"<input type='hidden' name='P_CHARGER_EMAIL_ADRES' value='"+$(this).children().eq(5).children().val()+"'/></td>";
				temp += "<td style='text-align: center;'>"+$(this).children().eq(6).children().val()+"<input type='hidden' name='P_CHARGER_TELNO' value='"+$(this).children().eq(6).children().val()+"'/></td>";
				temp += "</tr>";
			}
		});
		$(opener.document).find("#deptAddArea").append(temp);
		window.close(); 
	};
	/**
	 * window load
	 */
	$(function(){
		$("#addBtn").on("click", function() {
			pageObj.userSelected();
		});
		$("#delBtn").on("click", function() {
			pageObj.userUnSelected();
		});
		$("#setBtn").on("click", function() {
			pageObj.deptSet();
		});
		$('#table1 tr').on("click", function(event) {
			if(event.target.nodeName.toLowerCase() == 'td') {
				var checkbox = $(this).find('td:first-child :checkbox');
				if(checkbox.is(':checked') == true){
					checkbox.prop("checked", false);
				}else{
					checkbox.prop("checked", true);
				}
			}
		});
		$("#searchBtn").on("click", function() {
			pageObj.selectPrpoEntrpsInqireList();
		});
			FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ENTRPS_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.selectPrpoEntrpsInqireList(1);
		});
		//pageObj.setEventHandler();
	});
})();