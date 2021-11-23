<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="${jsPath}/bodyHeader.js"></script> 
<script type="text/javascript" src="${jsPath}/serverTime.js"></script>
<input type="hidden" id="sevrTime" value="${serverTime}">

        <!--sta 메뉴-->
        <div class="staWrap">
            <div class="sta">
                <div class="staLeftBtns">
		            <a href="${contextPath }/masc/mainPageForm.do" title="home" class="home on">Home</a>	
                </div>
                <div class="staRightBtns">
					<p class="staTimeLayer">
                   		현재 서버시간 : <span id="serverTime"></span>
                    <ul>
                    	<li>
                        	<a href="javascript:entrpsLoginForm();" title="로그인">로그인</a>
                        </li>	
                        <li class="last">
                            <a href="javascript:;" title="도움말">도움말</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="gnbWrap">
            <div class="gnb">
            	<ul>
					<li>
	                     <a href="javascript:clickLeftMenuMove('/orpl/orderPlanList.do', '입찰준비', 'oep1010');" class="btn_gnbMenu bg_non" title="입찰준비">입찰준비</a>
	                 </li>
	                 <li>
	                     <a href="javascript:clickLeftMenuMove('/elbi/inProgrsBidPblancList.do', '입찰관리', 'oep2010');" class="btn_gnbMenu" title="입찰관리">입찰관리</a>
	                 </li>
	                 <li>
	                     <a href="javascript:clickLeftMenuMove('/elcn/ctrtcList.do', '계약관리', 'oep4010');" class="btn_gnbMenu" title="계약관리">계약관리</a>
	                 </li>
	                 <li>
	                     <a href="javascript:clickLeftMenuMove('/entr/ccpyManageDetail.do', '협력업체관리상세', 'oep6010');" class="btn_gnbMenu" title="자사정보관리">자사정보관리</a>
	                 </li>
	                 <li>
	                     <a href="javascript:clickLeftMenuMove('/unbb/bidNoticeList.do', '고객센터', 'oep7010');" class="btn_gnbMenu" title="고객센터">고객센터</a>
	                 </li>
	         	</ul>        
            </div>
        </div>
        
        <div class="gnbSubWrap">
            <div class="gnbSub">
                <p><img src="${imagePath}/opro/login/headSubBtn1.png" alt="메뉴바로가기 리스트"></p>
                <ul>
                    <li>
                        <a href="javascript:;" title="주요도움말 바로가기">
                            <img src="${imagePath}/opro/login/headSubBtn2.png" alt="주요도움말">
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="clickLeftMenuMove('/acpr/acmsltProofReqstList.do', '계약관리', 'oep4030');" title="실적증명서발급 바로가기">
                            <img src="${imagePath}/opro/login/headSubBtn3.png" alt="실적증명서발급">
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="clickLeftMenuMove('/unbb/faqList.do', '고객센터', 'oep7050');" title="FAQ 바로가기">
                            <img src="${imagePath}/opro/login/headSubBtn4.png" alt="FAQ">
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;" onclick="clickLeftMenuMove('/unbb/bidNoticeList.do', '고객센터', 'oep7010');" title="공지사항 바로가기">
                            <img src="${imagePath}/opro/login/headSubBtn5.png" alt="공지사항">
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;" title="이용약관 바로가기">
                            <img src="${imagePath}/opro/login/headSubBtn7.png" alt="이용약관">
                        </a>
                    </li>
                </ul>
            </div>
        </div>
		
<form id="menuLeftMoveFrm" method="post" action="${contextPath}/iep">
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="resourceName" >
	<input type="hidden" name="resourceDesc" >
</form>
<%-- DEFAULT FORM --%>
<form id="defaultFrm" method="POST" action="${contextPath}/iep">

</form>  
<form id="logOutFrm" method="post" action="${contextPath }">

</form>
