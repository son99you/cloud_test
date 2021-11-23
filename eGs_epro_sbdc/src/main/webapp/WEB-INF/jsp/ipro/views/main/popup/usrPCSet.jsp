<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 메인 > 사용자 PC 환경설정(팝업)
 *
 * <pre>
 * main
 *  |_popup
 *   |_usrPCSet.jsp
 * 
--%>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">사용자 PC 환경설정</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
	   		 <!-- Data List -->
			<div class="pop_list_conts">
				<table class="table_top">
					<caption>사용자 PC 환경설정</caption>
					<colgroup>
						<col style="width: 100%;">
					</colgroup>
					<thead>
						<tr>
							<th>사용자PC환경설정</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<h3>우리연구원 전자조달시스템 이용 PC 환경 설정 가이드 입니다.</h3><br/>
								- 현재 보안패치가 종료된 윈도우XP 및 익스플로러 8 이하 사용자는 시스템 이용이 어려울 수 있습니다.<br/>
								가급적 윈도우7 서비스팩 1 이상의 OS 버전을 설치하고<br/>
								인터넷 익스플로러(IE, Internet Explorer)를 10이상 버전으로 설치해 주시기 바랍니다.<br/>
								<br/>
								[IE 이전 버전 지원종료-2016.1.12]<br/>				
								<img src="${imagePath}/comm/helpimg-201601-01.png" width="557" height="256" alt="ie버전별종료안내"><br/>
								- 안전하고 향상된 사용자 환경을 보장하기 위하여 <h3>우리연구원 입찰시스템은 <span>IE11 버젼에 최적화</span>되어 있습니다. IE8 이하 버젼 사용 시 정상적으로 동작하지 않을 수 있습니다. 사용OS 및 IE 버젼을 업그레이드 하여 주십시요.</h3>														
							</td>
						</tr>
						<tr>
							<td>
								<h3>1. IE9 이전 버젼 사용시 업그레이드하여 주십시요</h3><br/>
								<br/>
								인터넷 익스플로러 버전 확인 : 메뉴 - 도움말 - 'Internet Explorer 정보' 클릭<br/>						
								<img src="${imagePath}/comm/user_201108.gif" width="557" height="256" alt="ie버전확인안내1"><br>
								<img src="${imagePath}/comm/user_201108-1.gif" width="396" height="315" alt="ie버전확인안내2"><br>
								- IE 버젼 업그레이드하기<br/>
								<span>설치파일 다운로드 하기 : <a href="https://www.microsoft.com/ko-kr/download" target="_blank" rel="noopener noreferrer">[설치파일 다운로드]</a></span><br/> 
							</td>
						</tr>
						<tr>
							<td>
								<h3>2. IE 실행 시 관리자권한으로 실행하고 있는지 확인하여 주십시오.</h3>
								<br/>
								실행-모든프로그램-IE 아이콘에서 마우스 우클릭하여 <span>관리자권한으로 실행</span><br/>						
								<img src="${imagePath}/comm/user_20150402.gif" width="300" height="130" alt="관리자권한실행안내"><br>			
							</td>
						</tr>
						<tr>
							<td>
								<h3>3. 팝업 차단이 해제되어 있는지 확인하여 주십시오.</h3>
								<br/>
								브라우져 도구-팝업 차단에서 <span>“팝업 차단 사용 안함”</span> 을 선택<br/>						
								<img src="${imagePath}/comm/user_011.png" width="600" height="231" alt="팝업차단사용안함안내"><br>			
							</td>
						</tr>
						<tr>
							<td>
								<h3>4. 신뢰할 수 있는 사이트로 등록하여 주십시오.</h3>
								<br/>
								인터넷옵션-보안-신뢰할 수 있는 사이트 선택-[사이트] 버튼 누르 기 후 <span>*.keri.re.kr</span> 을 추가<br/>						
								<img src="${imagePath}/comm/user2009-1.gif" alt="ie신뢰할수있는사이트추가안내1"><br>
								<img src="${imagePath}/comm/user2010-05.gif" alt="ie신뢰할수있는사이트추가안내2"><br>			
							</td>
						</tr>
						<tr>
							<td>
								<h3>5. 임시인터넷 파일 새버전은 <span>"페이지를 열 때마다"</span> 로 설정되어 있는지 확인하여 주십시오.</h3>
								<br/>
								브라우저의 도구-인터넷옵션-일반탭 [설정]에서 “페이지를 열때마다"” 로 선택<br/>						
								<img src="${imagePath}/comm/user_11.gif" width="431" height="437" alt="ie캐쉬설정안내"><br><br>			
							</td>
						</tr>
						<tr>
							<td>
								<h3>6. 출력시 배경색 및 이미지 출력되지 않을 경우 설정이 필요합니다.</h3>
								<br/>
								브라우져 도구-인터넷옵션-고급-인쇄 에서 <span>배경색 및 이미지인쇄</span>으로 설정<br/>
								<br/>
								브라우져 인쇄-페이지 설정 에서 <span>배경색 및 이미지인쇄</span>으로 CHECK-[확인] 설정<br/>						
								<img src="${imagePath}/comm/user2010-print2.jpg" alt="ie인쇄설정안내1"><br>
								<img src="${imagePath}/comm/user2010-print1.jpg" alt="ie인쇄설정안내2"><br>			
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
	$('span').css({'color':'red'});
});
</script>