<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 메인 > 부서안내(팝업)
 *
 * <pre>
 * main
 *  |_popup
 *   |_deptGuidList.jsp
 * 
--%>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">부서안내</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
	   		 <!-- Data List -->
			<div class="pop_list_conts">
				<table class="table_top">
					<caption>부서안내</caption>
					<colgroup>
						<col style="width: 50%;">
						<col style="width: 50%;">
					</colgroup>
					<tbody>
						<tr height="35px">
							<th>정상협 (T1204)</th>
							<th>&nbsp;</th>
						</tr>
						<tr>
							<td>
								ㅇ 구매자산실 업무 총괄<br/> 
							</td>
							<td></td>
						</tr>
						<tr height="35px">
							<th>정평근 (T1227)</th>
							<th>김해곤 (T1237)</th>
						</tr>
						<tr>
							<td>
								ㅇ 내자 구매 계약<br/>
								- 대형장비, 부속품 , S/W 구매<br/>
								ㅇ 구매 및 계약관리<br/>
								- 접수대장, 계약대장, 입찰공고 대장 등<br/>
								- 계약관련 서류의 기록, 유지 및 관리<br/>
							</td>
							<td>
								ㅇ 내자 구매 계약<br/>
								- 중형장비, 부속품, 실험 가스 등<br/> 
								ㅇ 기성고 대금 지급 관리 (매월 정기적 지출)<br/>
								- 경비, 청소, 시설관리, 시험 용역비, 보안, 전산정보 <br/>
								ㅇ 시스템 등 유지보수 용역비<br/>
							</td>
						</tr>
						<tr height="35px">
							<th>노병욱 (T1228)</th>
							<th>김활란 (T1231)</th>
						</tr>
						<tr>
							<td>
								ㅇ 내자 구매 계약<br/>
								- 시설공사(건축, 토목, 전기, 통신, 소방공사 등)<br/>
								- 용역계약(청소, 시설관리, 경비, 전산시스템 등)<br/>
								- 중, 소형장비, 부속품 구매 및 관련업무<br/>

								ㅇ 전자조달시스템 운영관리자(조달청업무연계)<br/>
							</td>
							<td>
								ㅇ 물품 검수 및 전산 입력<br/>
								- 수량검사, 기술검사, 수령 및 납기관리<br/> 
								- 자산유무 확인, 검수사진 관리 등<br/> 
							</td>
						</tr>
						<tr height="35px">
							<th>진우성 (T1232)</th>
							<th>김가영 (T1234)</th>
						</tr>
						<tr>
							<td>
								ㅇ 외자 구매 계약<br/>
								- 구매접수, 계약, 통관업무<br/>
								- 외자 감세물품 사후관리 업무 <br/>
								ㅇ 내자 구매 계약<br/>
								- 중, 소형장비, 부속품 등<br/>
							</td>
							<td>
								ㅇ 내자계약 <br/>
								- 세금계산서 관리 <br/>
								- 전자조달 승인 및 업체정보 관리(신규등록 등)<br/>
								- 내자물품 검수업무 보조<br/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="pop_list_bottom">
	    		<div class="btn_wrap view_btn">
					<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
	    		</div>
			</div>
			
		</div>
	</div> 
</div>

<script type="text/javascript">
$(function() {
	$('table').css({'border':'1px solid #ddd'});
	$('th').css({'text-align':'left','padding-left':'20px','font-size':'15px'});
	$('td').css({'text-align':'left','line-height':'22px','padding':'10px 0px 10px 20px'});
	$('.btn-wrap').css({'margin-bottom':'20px'});
});
</script>