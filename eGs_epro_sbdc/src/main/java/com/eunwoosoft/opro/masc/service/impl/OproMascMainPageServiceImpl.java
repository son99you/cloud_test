package com.eunwoosoft.opro.masc.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.masc.service.IproMascMainPageService;
import com.eunwoosoft.opro.masc.dao.OproMascMainPageDao;
import com.eunwoosoft.opro.masc.service.OproMascMainPageService;

/**
 * <pre>
 * com.eunwoosoft.opro.masc.service.impl 
 *    |_ OepMascMainPageServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 3. 23. 오후 4:01:42
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */

@Service(value="oproMascMainPageService")
public class OproMascMainPageServiceImpl extends AbstractFwkService implements OproMascMainPageService{
	
	@Resource(name="oproMascMainPageDao")
	private OproMascMainPageDao oproMascMainPageDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 메인화면 조회
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : mainPageInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject mainPageInqire(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("loginResult") != null){
			FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
			parameterMap.put("P_PRCURE_BSNS_SE_CD", session.get("PRCURE_BSNS_SE_CD"));
			parameterMap.put("P_USER_ID", session.get("USER_ID"));
		}
		
		// 공고 업무 카운트 조회
		trans.put(IproMascMainPageService.PBLANC_JOB_CNT_LIST, oproMascMainPageDao.selectPblancJobCntInqire(parameterMap));
		// 공고 전 업무 카운트 조회
		trans.put(IproMascMainPageService.PBLANC_BEF_JOB_CNT_LIST, oproMascMainPageDao.selectPblancBefJobCntInqire(parameterMap));
		// 개찰 후 업무 카운트 조회
		trans.put(IproMascMainPageService.OPENG_AT_JOB_CNT_LIST, oproMascMainPageDao.selectOpengAfJobCntInqire(parameterMap));
		// 계약 업무 카운트 조회
		trans.put(IproMascMainPageService.CNTRCT_JOB_CNT_LIST, oproMascMainPageDao.selectCntrctJobCntInqire(parameterMap));
		
		// 부서별 발주계획 카운트 조회
		trans.put(IproMascMainPageService.DEPT_ACCTO_ORDER_PLAN_CNT_DETAIL, oproMascMainPageDao.selectDeptAcctoOrderPlanCntInqire(parameterMap));
		// 부서별 입찰공고 카운트 조회
		trans.put(IproMascMainPageService.DEPT_ACCTO_BID_PBLANC_CNT_DETAIL, oproMascMainPageDao.selectDeptAcctoBidPblancCntInqire(parameterMap));
		// 일반행정 카운트 조회
		trans.put(IproMascMainPageService.GNRL_ADMINIST_CNT_DETAIL, oproMascMainPageDao.selectGnrlAdministCntInqire(parameterMap));
		// 기타공고 카운트 조회
		trans.put(IproMascMainPageService.ETC_PBLANC_CNT_DETAIL, oproMascMainPageDao.selectEtcPblancCntInqire(parameterMap));
		
		/*	게시판 분류 번호 	
 		EB0001	입찰공지사항
		EB0002	일반공지사항
		 */
		// 메인일반공지
		parameterMap.put("P_ELPR_BSNO", "EB0002");
		parameterMap.put("P_NOTICE_CNT", "7");
		trans.put(IproMascMainPageService.MAIN_GNRL_NOTICE_LIST, oproMascMainPageDao.selectMainNoticeList(parameterMap));
		// 메인입찰공지
		parameterMap.put("P_ELPR_BSNO", "EB0001");
		parameterMap.put("P_NOTICE_CNT", "7");
		trans.put(IproMascMainPageService.MAIN_BID_NOTICE_LIST, oproMascMainPageDao.selectMainNoticeList(parameterMap));

		// 메인TAB 입찰공고
		parameterMap.put("P_BID_PROGRS_STTUS_CD", "PF20");
		parameterMap.put("P_NOTICE_CNT", "4");
		trans.put(IproMascMainPageService.MAIN_PBLANC_LIST, oproMascMainPageDao.selectMainPblancTabList(parameterMap));
		// 메인TAB 사전공고
		parameterMap.put("P_BID_PROGRS_STTUS_CD", "BP94");
		parameterMap.put("P_NOTICE_CNT", "4");
		trans.put(IproMascMainPageService.MAIN_BEFFAT_PBLANC_LIST, oproMascMainPageDao.selectMainBeffatPblancTabList(parameterMap));

		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서별공고 목록
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : deptAcctoPblancList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject deptAcctoPblancList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		if("AP".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별공고 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoPblancList(parameterMap));
		}else if("AO".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별 발주계획 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoOrderPlanList(parameterMap));
		}else if("GP".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별 일반 공고 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoGnrlPblancList(parameterMap));
		}else if("GO".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별 일반 발주계획 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoGnrlOrderPlanList(parameterMap));
		}else if("EP".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별 기타 공고 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoEtcPblancList(parameterMap));
		}else if("EO".equals(parameterMap.get("PROSSESS_GUBUN"))){
			// 부서별 기타 발주계획 목록
			trans.put(IproMascMainPageService.DEPT_ACCTO_PBLANC_LIST, oproMascMainPageDao.selectDeptAcctoEtcOrderPlanList(parameterMap));
		}
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 달력 생성 HTML
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : cldrCreatHtml
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject cldrCreatHtml(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		int thisYear = Integer.parseInt((String)parameterMap.get("thisYear"));
		int thisMonth = Integer.parseInt((String)parameterMap.get("thisMonth"));
		
		Calendar cal = Calendar.getInstance();   // Calendar 객체 생성
		int nowDate = cal.get(Calendar.DATE);	// 오늘 일자

		if(thisMonth > 12){
			thisYear++;
			thisMonth = 1;
		}else if(thisMonth < 1){
			thisYear--;
			thisMonth = 12;
		}
		
		// 해당 달의 1일로 설정
		cal.set(thisYear, (thisMonth - 1), 1);   
		// 월의 첫 요일
		int nfirstweek = cal.get(Calendar.DAY_OF_WEEK);
		nfirstweek--;
		cal.set(thisYear, thisMonth, 0);   
		// 월의 마지막 일 해당 달의 날 수 얻기
		int nlastday = cal.getActualMaximum(Calendar.DATE);
		// 월의 총 주수
		int weekSeq = (nlastday + nfirstweek - 1) / 7 + 1;
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h5>입찰달력</h5>");
		sb.append("<div class='date_select'>");
		sb.append("		<button type='button' class='btn_leftYear' onclick='makeCalendar("+(thisYear - 1)+", "+thisMonth+");'>이전 연도 이동</button>");
		sb.append("		<button type='button' class='btn_left' onclick='makeCalendar("+thisYear+", "+(thisMonth - 1)+");'>이전 달 이동</button>");
		sb.append("		<span class='yearSelect'>"+thisYear+"년"+thisMonth+"월</span>");
		sb.append("		<button type='button' class='btn_right' onclick='makeCalendar("+thisYear+", "+(thisMonth + 1)+");'>다음 달 이동</button>");
		sb.append("		<button type='button' class='btn_rightYear' onclick='makeCalendar("+(thisYear + 1)+", "+thisMonth+");'>다음 연도 이동</button>");
		sb.append("</div>");
		
		sb.append("<table class='mini_calendar' summary='입찰달력 입니다.'>");
		sb.append("		<caption>입찰에 대한 일정을 표시해주는 달력</caption>");
		sb.append("		<colgroup>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("			<col width='42px'>");
		sb.append("		</colgroup>");
		sb.append("		<thead>");
		sb.append("			<tr>");
		sb.append("				<th scope='col' class='sun'>Sun</th>");
		sb.append("				<th scope='col'>Mon</th>");
		sb.append("				<th scope='col'>Tue</th>");
		sb.append("				<th scope='col'>Wed</th>");
		sb.append("				<th scope='col'>Thu</th>");
		sb.append("				<th scope='col'>Fri</th>");
		sb.append("				<th scope='col' class='sat'>Sat</th>");
		sb.append("			</tr>");
		sb.append("		</thead>");
		sb.append("		<tbody>");
		
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		//// 1. 달력 사전공고 목록
		////List<FwkDataEntity> cldrBeffatPblancList = oproMascMainPageDao.selectCldrBeffatPblancList(parameterMap);
		// 1. 달력 기술평가 목록
		List<FwkDataEntity> cldrTchqvlnList = oproMascMainPageDao.selectCldrTchqvlnList(parameterMap);
		// 2. 달력 입찰공고 목록
		List<FwkDataEntity> cldrBidPblancList = oproMascMainPageDao.selectCldrBidPblancList(parameterMap);
		// 3. 달력 입찰설명회 목록
		List<FwkDataEntity> cldrBidDcPeoList = oproMascMainPageDao.selectCldrBidDcPeoList(parameterMap);
		// 4. 달력 참가마감 목록
		List<FwkDataEntity> cldrPareEndList = oproMascMainPageDao.selectCldrPareEndList(parameterMap);
		// 5. 달력 입찰서제출 목록
		List<FwkDataEntity> cldrBidDocsuList = oproMascMainPageDao.selectCldrBidDocsuList(parameterMap);
		// 6. 달력 개찰 목록
		List<FwkDataEntity> cldrOpengList = oproMascMainPageDao.selectCldrOpengList(parameterMap);
		
		int selectDate = 0;
		
		// 달력에 사용될 td의 합계를 구합니다(이번달의 총 주수 * 7(일주일)).
		int ntdsum = weekSeq * 7;
		int date = 0;
		String sMonth = "";
		if(thisMonth < 10){
			sMonth = "0"+thisMonth;
		}else{
			sMonth = Integer.toString(thisMonth);
		}
		
		String sDate = "";
		// 입찰 달력 일자 부분	
		for(int idx = 0; idx < ntdsum; idx++) {
		    //달의 1일이 일요일이 아니라면 1일까지 빈 공간을 만들어 줍니다.
		    if(idx < nfirstweek || idx > (nlastday+nfirstweek-1) ) {
		    	sb.append("<td>");
		    	sb.append("		<span class='day_bgLayer'></span>");
		    	sb.append("		<span></span>");
		    	sb.append("</td>");
		    }else{
		    	//현재 달의 1일부터 마지막날까지 일요일과 평일(토요일)의 글자 색상만 바꾸어주며 차례대로 칸을 채워 넣습니다.
		    	date++;
		    	if(date < 10){
		    		sDate = "0"+date;
				}else{
					sDate = ""+date;
				}
		    	String classNm = "";
		    	if(((idx + 1) % 7) == 1) { 
		    		classNm = "sun"; 
		    	}
		    	if(nowDate == date){
		    		classNm = "on"; 
		    	}
		    	sb.append("<td class='"+classNm+"' onclick='makeToDoList("+thisYear+sMonth+sDate+");'>");
				sb.append("		<span class='day_bgLayer'></span>");
				sb.append("		<span>"+date+"</span>");
				sb.append("		<div class='listLayer'>");

				// 1. 해당월에 존재하는 기술평가
				for(int bdx=0; bdx < cldrTchqvlnList.size(); bdx++){
					hMap = (Map<String, Object>)cldrTchqvlnList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("TCHQVLN_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square1'>기술평가</p>");
					}
				}
				// 2. 해당월에 존재하는 입찰공고
				for(int bdx=0; bdx < cldrBidPblancList.size(); bdx++){
					hMap = (Map<String, Object>)cldrBidPblancList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("BID_PBLANC_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square2'>입찰공고</p>");
					}
				}
				// 3. 해당월에 존재하는 입찰설명회
				for(int bdx=0; bdx < cldrBidDcPeoList.size(); bdx++){
					hMap = (Map<String, Object>)cldrBidDcPeoList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("BID_DC_PEO_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square3'>입찰설명회</p>");
					}
				}
				// 4. 해당월에 존재하는 참가마감
				for(int bdx=0; bdx < cldrPareEndList.size(); bdx++){
					hMap = (Map<String, Object>)cldrPareEndList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("PARE_END_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square4'>참가마감</p>");
					}
				}
				// 5. 해당월에 존재하는 입찰서제출
				for(int bdx=0; bdx < cldrBidDocsuList.size(); bdx++){
					hMap = (Map<String, Object>)cldrBidDocsuList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("BID_DOCSU_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square5'>입찰서제출</p>");
					}
				}
				// 6. 해당월에 존재하는 개찰
				for(int bdx=0; bdx < cldrOpengList.size(); bdx++){
					hMap = (Map<String, Object>)cldrOpengList.get(bdx);
					selectDate = Integer.parseInt((String)hMap.get("OPENG_DATE"));
					
					if(date == selectDate){
						sb.append("	<p class='square6'>개찰</p>");
					}
				}
				sb.append("		</div>");
				sb.append("</td>");
		    }
		    if(idx < ntdsum - 1 && ((idx + 1) % 7) == 0) { 
		    	sb.append("</tr><tr>");
		    }
		}
		sb.append("		</tbody>");
		sb.append("</table>");
		
		trans.put(IproMascMainPageService.CLDR_CREAT_HTML, sb.toString());
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : TODOLIST 생성 HTML
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : toDoListCreatHtml
	 * @date : 2015. 3. 31.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 31.		은우소프트 김봉수			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject toDoListCreatHtml(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		Calendar cal = Calendar.getInstance();   // Calendar 객체 생성

		String toDay = (String)parameterMap.get("P_TODAY");
		String dayNm = "";
		
		// 해당 달의 1일로 설정
		cal.set(Integer.parseInt(toDay.substring(0, 4)), (Integer.parseInt(toDay.substring(4, 6)) - 1), Integer.parseInt(toDay.substring(6, 8)));   
		// 요일
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		
		switch(dayNum){
        case 1:
        	dayNm = "일";
            break ;
        case 2:
        	dayNm = "월";
            break ;
        case 3:
        	dayNm = "화";
            break ;
        case 4:
        	dayNm = "수";
            break ;
        case 5:
        	dayNm = "목";
            break ;
        case 6:
        	dayNm = "금";
            break ;
        case 7:
        	dayNm = "토";
            break ;
		}
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<p>"+FwkFormatUtil.formatDate((String)parameterMap.get("P_TODAY"), "yyyyMMdd", "yyyy-MM-dd")+"("+dayNm+")</p>");		
		sb.append("<ul>");
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		List<FwkDataEntity> toDoList = oproMascMainPageDao.selectToDoList(parameterMap);
		String classNm = "";
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		String orcCd = "";
		
		if(user != null){
			orcCd = (String)user.get("ORG_CD");
		}
		
		for(int idx=0; idx < toDoList.size(); idx++){
			hMap = (Map<String, Object>)toDoList.get(idx);
			classNm = "square"+(String)hMap.get("GUBUN_SN");
			
			// 원조조달계약팀, 경영관리팀의 경우만 입찰달력에 대한 상세 화면 이동이 가능하도록 수정
			if(orcCd.equals(FwkMessageUtil.getMessage("IEP.ABID.DEPT.NO")) || orcCd.equals(FwkMessageUtil.getMessage("IEP.GBID.DEPT.NO"))){
				sb.append("		<li style='cursor: pointer;'>");
				sb.append("			<span class="+classNm+"></span><a onclick=\"toDoListDetail('"+hMap.get("GUBUN_SN")+"', '"+hMap.get("TODO_NO")+"', '"+hMap.get("TODO_ODR")+"', '"+hMap.get("TODO_STTUS_CD")+"');\">"+hMap.get("TODO_NM")+"</a>");		
			}else{
				sb.append("		<li>");
				sb.append("			<span class="+classNm+"></span>"+hMap.get("TODO_NM"));		
			}
			
			sb.append("		</li>");	
		}

		sb.append("</ul>");		
		
		trans.put(IproMascMainPageService.TO_DO_LIST_CREAT_HTML, sb.toString());
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : db 시간
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : dbTime
	 * @date : 2015. 8. 28.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 28.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.masc.service.OepMascMainPageService#entrpsPrmanentLeavAtCeck(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	public String dbTime(final FwkParameterMap parameterMap) {
		return oproMascMainPageDao.selectDbTime(parameterMap);
	}
}
