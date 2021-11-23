<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 계약관리 > 계약대기현황 > 작성
 *
 * <pre>
 * cont 
 *    |_ contWaitPrcnRegForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/mssgeSend.js"></script>
 
<div class="content">
	<form id="registFrm" method="POST">
		<h3>메세지전송</h3>
		<div class="subscrip_info">
			<div class="T_btnLayer fr">
		    	<button type="button" class="blueBtn L saveBtn">발송</button>
		    	<button type="button" class="blueBtn L listBtn">취소</button>
		    </div>
			
			<jsp:useBean id="random" class="java.util.Random" scope="application"/>
			
			<div class="contentTitle" style="">발송내용</div>
			<table>
		    	<colgroup>
		    		<col width="25%">
		    		<col width="25%">
		    		<col width="25%">
		    		<col width="25%">
		        </colgroup>
		    	<tr>
		        	<th style="text-align: center;">발송자</th>
		            <td>
		            	<div id="div_a">
		                	<select>
		                		<option>010</option>
		                		<option>011</option>
		                		<option>017</option>
		                		<option>018</option>
		                		<option>019</option>
		                	</select> 
		                	-
			            	<input type="text" style="width:10%;" value="">
			            	-
			            	<input type="text" style="width:10%;" value="">
		            	</div>
		            	<div id="div_b" style="display:none;">
		            		<input type="text" style="width:40%;" value="">
		            		@
		                	<select>
		                		<option>naver.com</option>
		                		<option>hanmail.net</option>
		                		<option>empas.com</option>
		                		<option>lycos.co.kr</option>
		                		<option>yahoo.com</option>
		                	</select>		            		
		            	</div>
		            </td>
		            <th style="text-align: center;">발송예정일자</th>
		            <td><input type="text" style="width:65%;" value="" date></td>
		        </tr>
		        <tr>
		        	<th style="text-align: center;">수신자</th>
		        	<td colspan="3" id="send_list_td">
		        		<button type="button" id="pop_btn" class="grayBtn ico popBtn_item"><img src="${imagePath}/ipro/icon/ico_search.png" alt="업체 조회 버튼"></button>
		        	</td>
		        </tr>
		        <tr>
					<th style="text-align: center;">수신구분</th>
		        	<td colspan="3">
			            <div>
		                	<select id="send_select">
		                		<option value="A" >핸드폰</option>
		                		<option value="B" >이메일</option>
		                	</select> 
						</div>		        	
		        	</td>		        	
		        </tr>
		    	<tr>
		        	<th style="text-align: center;">발송내용</th>
		            <td colspan="3">
		            	<textarea style="width:97%; height:100px;">이메일 발송 시 HTML 코드를 여기에 복사해주세요.</textarea>
		            </td>
		        </tr>
		    </table>			
			
			<br/>
			
			<div class="T_btnLayer fr">
		    	<button type="button" class="blueBtn L saveBtn">발송</button>
		    	<button type="button" class="blueBtn L listBtn">취소</button>
		    </div>
		</div>
	</form>
	
	<form id="listFrm" method="POST">
		<input type="hidden" name="resourceName" value="${ param.resourceName }" >
	</form>

</div> <!--// content E-->
