package com.eunwoosoft.ipro.stts.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.stts.dao.IproSttsEstmMngDao;
import com.eunwoosoft.ipro.stts.service.IproSttsEstmMngService;

/**
 * <pre>
 * com.eunwoosoft.ipro.vend.service.impl 
 *    |_ IproVendMngeServiceImpl.java
 * 
 * </pre>
 * 
 * @date : 2018. 2. 20.
 * @version : 1.0
 * @author : jandi_Eun
 */

@Transactional
@Service("iproSttsEstmMngService")
public class IproSttsEstmMngServiceImpl extends AbstractFwkService implements IproSttsEstmMngService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSttsEstmMngServiceImpl.class);

	@Resource(name = "iproSttsEstmMngDao")
	private IproSttsEstmMngDao iproSttsEstmMngDao;


	@Override
	public FwkTransferObject sttsList(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String[] sch_inqCondList = (String[])parameterMap.get("inqCond");
		String[] sch_inqTextList = (String[])parameterMap.get("inqText");
		
		trans.put("sch_inqCondList", sch_inqCondList);
		trans.put("sch_inqTextList", sch_inqTextList);
		
		//통계대상
		String P_STTS_OBJ_SECD_S = parameterMap.getString("P_STTS_OBJ_SECD_S");
		String P_STTS_VIEW_NAME = "";
		String P_STTS_ORDERBY = "";
		String P_STTS_KEY_COLUMN = "";
		
		if("".equals(P_STTS_OBJ_SECD_S)){
			P_STTS_OBJ_SECD_S = "A";
			
		}else if(parameterMap.get("P_STTS_OBJ_SECD_S") != null && !"".equals(parameterMap.get("P_STTS_OBJ_SECD_S"))){
			
			// 통계리스트구분
			parameterMap.put("P_STTS_OBJ_SECD_S", P_STTS_OBJ_SECD_S);
			FwkDataEntity sttsInfo = iproSttsEstmMngDao.selectSttsInfoDetail(parameterMap);
			
			P_STTS_VIEW_NAME = sttsInfo.getString("STTS_VIEW_NAME");
			P_STTS_ORDERBY = sttsInfo.getString("STTS_ORDERBY");
			P_STTS_KEY_COLUMN = sttsInfo.getString("STTS_KEY_COLUMN");
			
			/**
			 * 조회조건추가 리스트 받아오기
			 */
			String[] inqColList = (String[])parameterMap.get("inqCol");
			List rowInqData = new ArrayList();
			
			if(inqColList != null) {
				if(inqColList.length > 1) {
					//System.err.println("@@@ inqColList.length ==> " + inqColList.length);
					for(int i= 1; i< inqColList.length; i++) {
						//System.err.println("@@@ inqColList["+i+"] => " + inqColList[i]);
						
						List colInqData = new ArrayList();
						
						//System.err.println("@@@ inqColList[i].toString() ==> " + inqColList[i].toString());
						
						if(!"".equals(inqColList[i].toString())) {
							String inqColStr = inqColList[i].toString();
							
							//System.err.println("@@@ inqColStr ==> " + inqColStr);
							
							String inqColListSplit[] = inqColStr.split("\\|");
							
							System.err.println("@@@ inqColListSplit[0].toString() ==> " + inqColListSplit[0].toString());   // 조건컬럼
							System.err.println("@@@ inqColListSplit[1].toString() ==> " + inqColListSplit[1].toString());   // 조건구분
							System.err.println("@@@ inqColListSplit[2].toString() ==> " + inqColListSplit[2].toString());   // 조건값
							
							String WHERE = "";
							String COLUMN_NAME = inqColListSplit[0];
							String COLUMN_SCH = "";
							
							colInqData.add(inqColListSplit[0]);   // 조건컬럼
							
							
							if("A".equals(inqColListSplit[1].toString())) { // 이상
								colInqData.add(" >= " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " >= " + inqColListSplit[2];
							}
							else if("B".equals(inqColListSplit[1].toString())) { // 이하
								colInqData.add(" <= " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " <= " + inqColListSplit[2];
								
							}
							else if("C".equals(inqColListSplit[1].toString())) { // 초과
								colInqData.add(" > " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " > " + inqColListSplit[2];
								
							}
							else if("D".equals(inqColListSplit[1].toString())) { // 미만
								colInqData.add(" < " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " < " + inqColListSplit[2];
							}
							else if("E".equals(inqColListSplit[1].toString())) { // 동일
								colInqData.add(" = '" + inqColListSplit[2] + "'");
								colInqData.add(" ");
								
								COLUMN_SCH = " = '" + inqColListSplit[2] + "'";
							}
							else if("F".equals(inqColListSplit[1].toString())) { // 유사
								colInqData.add(" LIKE '%' || '" + inqColListSplit[2] + "' || '%' ");
								colInqData.add(" ");
								
								COLUMN_SCH = " LIKE '%' || '" + inqColListSplit[2] + "' || '%' ";
							}else if("G".equals(inqColListSplit[1].toString())) { // 날짜
								String stDate = inqColListSplit[2].toString().substring(0, 8);
								String endDate = inqColListSplit[2].toString().substring(8);
								colInqData.add(" BETWEEN TO_DATE('" + stDate + "000000','yyyyMMddHH24miss') AND TO_DATE('"+ endDate +"235959','yyyyMMddHH24miss')");
								colInqData.add("DATE");
								
								COLUMN_SCH = " BETWEEN TO_DATE('" + stDate + "000000','yyyyMMddHH24miss') AND TO_DATE('"+ endDate +"235959','yyyyMMddHH24miss')";
							}
							
							//System.err.println("@@@ colInqData ==> " + colInqData);
							
							WHERE = COLUMN_NAME + COLUMN_SCH;
									
									
							System.err.println("@@@ WHERE ==> " + WHERE);
							parameterMap.put("WHERE", WHERE);
							rowInqData.add(WHERE);
							//rowInqData.add(colInqData);
						}else{
							rowInqData.add("1=1");
						}
					}
					System.err.println("@@@ rowInqData ==> " + rowInqData);
					
				}
			}
			
			// 목록리스트명
			parameterMap.put("sttsList", P_STTS_VIEW_NAME);
			parameterMap.put("sttsListCol", P_STTS_VIEW_NAME + "_COL");
			parameterMap.put("sttsOrderby", P_STTS_ORDERBY);
			
			List<FwkDataEntity> colList = iproSttsEstmMngDao.getColList(parameterMap);
			trans.put("colList", colList);
			
			String[] selectColStrList = (String[]) parameterMap.get("selectCol");
			
			List sch_colList = new ArrayList();
			List rowData = new ArrayList();
			List rowAlign = new ArrayList();   // 컬럼 정렬
			List rowType = new ArrayList();   // 컬럼 type

			
			parameterMap.put("colYn", "N");
			// 목록리스트명
			
			parameterMap.put("sttsViewName", P_STTS_VIEW_NAME);
			
			String infoSttsAlign = "";
			String infoSttsType = "";
			List colAlign = new ArrayList();
			List colType = new ArrayList();
			
			if(selectColStrList != null) {
				if(selectColStrList.length > 1) {
					for(int i= 1; i< selectColStrList.length; i++) {
						System.err.println("selectColStrList["+i+"] => " + selectColStrList[i].toString());
						sch_colList.add(selectColStrList[i].toString());
						
						parameterMap.put("sch_colList", selectColStrList[i].toString());

						parameterMap.put("sch_colList_col", "'" +selectColStrList[i].toString() + "'");
						
						/**
						 * 동적 컬럼 리스트 들
						 */
						List colData = new ArrayList();
						
						
						// 조회조건 추가되는 부분
						System.err.println("@@@ rowInqData ==> " + rowInqData);
						parameterMap.put("rowInqData", rowInqData);
						
						// 목록불러오는 부분
						List sttsList = iproSttsEstmMngDao.selectSttsList(parameterMap);
						
						
						for(int j=0; j<sttsList.size(); j++) {
							FwkDataEntity infoStts = (FwkDataEntity) sttsList.get(j);
							//System.err.println("@@@ infoStts ==> " + infoStts);
							colData.add(infoStts.get("DATACOLUMN"));
							
							infoSttsAlign = infoStts.getString("ALIGN");
							infoSttsType = infoStts.getString("COLUMNTYPE");
							
						}
						
						colAlign.add(infoSttsAlign);
						colType.add(infoSttsType);
						rowAlign.add(colAlign);
						rowType.add(colType);
						rowData.add(colData);
					}
					
					trans.put("sttsListData", rowData);
					trans.put("sttsListAlign", colAlign);
					trans.put("sttsListType", colType);
					
					/**
					 * 키값이 되는 컬럼 리스트
					 */
					parameterMap.put("colYn", "Y");

					parameterMap.put("sch_colList", P_STTS_KEY_COLUMN);
					
					
					List<FwkDataEntity> sttsList = iproSttsEstmMngDao.selectSttsList(parameterMap);
					
					//System.err.println("@@@ sttsKeyList ==> " + sttsList);
					
					
					trans.put("sttsKeyList", sttsList);
					trans.put("sttsKeyListCnt", sttsList.size());
					
				}
			}
			
			parameterMap.put("sch_colList", sch_colList);
			
			/**
			 *  체크박스에 체크된 컬럼 리스트
			 */
			List<FwkDataEntity> selectColList = iproSttsEstmMngDao.getColList(parameterMap);
			
			trans.put("selectColList", selectColList);
			trans.put("selectColListCnt", selectColList.size());
		}
		
		trans.put("sttsYn", "Y");
		
		return trans;
		
	}
	
	
	@Override
	public FwkTransferObject estmSttsListExcelDwld(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String[] sch_inqCondList = (String[])parameterMap.get("inqCond");
		String[] sch_inqTextList = (String[])parameterMap.get("inqText");
		
		trans.put("sch_inqCondList", sch_inqCondList);
		trans.put("sch_inqTextList", sch_inqTextList);
		
		//통계대상
		String P_STTS_OBJ_SECD_S = parameterMap.getString("P_STTS_OBJ_SECD_S");
		String P_STTS_VIEW_NAME = "";
		String P_STTS_ORDERBY = "";
		String P_STTS_KEY_COLUMN = "";
		
		if("".equals(P_STTS_OBJ_SECD_S)){
			P_STTS_OBJ_SECD_S = "A";
			
		}else if(parameterMap.get("P_STTS_OBJ_SECD_S") != null && !"".equals(parameterMap.get("P_STTS_OBJ_SECD_S"))){
			
			// 통계리스트구분
			parameterMap.put("P_STTS_OBJ_SECD_S", P_STTS_OBJ_SECD_S);
			FwkDataEntity sttsInfo = iproSttsEstmMngDao.selectSttsInfoDetail(parameterMap);
			
			P_STTS_VIEW_NAME = sttsInfo.getString("STTS_VIEW_NAME");
			P_STTS_ORDERBY = sttsInfo.getString("STTS_ORDERBY");
			P_STTS_KEY_COLUMN = sttsInfo.getString("STTS_KEY_COLUMN");
			
			/**
			 * 조회조건추가 리스트 받아오기
			 */
			String[] inqColList = (String[])parameterMap.get("inqCol");
			List rowInqData = new ArrayList();
			
			if(inqColList != null) {
				if(inqColList.length > 1) {
					//System.err.println("@@@ inqColList.length ==> " + inqColList.length);
					for(int i= 1; i< inqColList.length; i++) {
						//System.err.println("@@@ inqColList["+i+"] => " + inqColList[i]);
						
						List colInqData = new ArrayList();
						
						//System.err.println("@@@ inqColList[i].toString() ==> " + inqColList[i].toString());
						
						if(!"".equals(inqColList[i].toString())) {
							String inqColStr = inqColList[i].toString();
							
							//System.err.println("@@@ inqColStr ==> " + inqColStr);
							
							String inqColListSplit[] = inqColStr.split("\\|");
							
							System.err.println("@@@ inqColListSplit[0].toString() ==> " + inqColListSplit[0].toString());   // 조건컬럼
							System.err.println("@@@ inqColListSplit[1].toString() ==> " + inqColListSplit[1].toString());   // 조건구분
							System.err.println("@@@ inqColListSplit[2].toString() ==> " + inqColListSplit[2].toString());   // 조건값
							
							String WHERE = "";
							String COLUMN_NAME = inqColListSplit[0];
							String COLUMN_SCH = "";
							
							colInqData.add(inqColListSplit[0]);   // 조건컬럼
							
							
							if("A".equals(inqColListSplit[1].toString())) { // 이상
								colInqData.add(" >= " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " >= " + inqColListSplit[2];
							}
							else if("B".equals(inqColListSplit[1].toString())) { // 이하
								colInqData.add(" <= " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " <= " + inqColListSplit[2];
								
							}
							else if("C".equals(inqColListSplit[1].toString())) { // 초과
								colInqData.add(" > " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " > " + inqColListSplit[2];
								
							}
							else if("D".equals(inqColListSplit[1].toString())) { // 미만
								colInqData.add(" < " + inqColListSplit[2]);
								colInqData.add(" ");
								
								COLUMN_SCH = " < " + inqColListSplit[2];
							}
							else if("E".equals(inqColListSplit[1].toString())) { // 동일
								colInqData.add(" = '" + inqColListSplit[2] + "'");
								colInqData.add(" ");
								
								COLUMN_SCH = " = '" + inqColListSplit[2] + "'";
							}
							else if("F".equals(inqColListSplit[1].toString())) { // 유사
								colInqData.add(" LIKE '%' || '" + inqColListSplit[2] + "' || '%' ");
								colInqData.add(" ");
								
								COLUMN_SCH = " LIKE '%' || '" + inqColListSplit[2] + "' || '%' ";
							}else if("G".equals(inqColListSplit[1].toString())) { // 날짜
								String stDate = inqColListSplit[2].toString().substring(0, 8);
								String endDate = inqColListSplit[2].toString().substring(8);
								colInqData.add(" BETWEEN TO_DATE('" + stDate + "','yyyyMMdd') AND TO_DATE('"+ endDate +"','yyyyMMdd')");
								colInqData.add("DATE");
							}
							
							//System.err.println("@@@ colInqData ==> " + colInqData);
							
							WHERE = COLUMN_NAME + COLUMN_SCH;
									
									
							System.err.println("@@@ WHERE ==> " + WHERE);
							parameterMap.put("WHERE", WHERE);
							rowInqData.add(WHERE);
							//rowInqData.add(colInqData);
						}else{
							rowInqData.add("1=1");
						}
					}
					System.err.println("@@@ rowInqData ==> " + rowInqData);
					
				}
			}
			
			// 목록리스트명
			parameterMap.put("sttsList", P_STTS_VIEW_NAME);
			parameterMap.put("sttsListCol", P_STTS_VIEW_NAME + "_COL");
			parameterMap.put("sttsOrderby", P_STTS_ORDERBY);
			
			List<FwkDataEntity> colList = iproSttsEstmMngDao.getColList(parameterMap);
			trans.put("colList", colList);
			
			String[] selectColStrList = (String[]) parameterMap.get("selectCol");
			
			List sch_colList = new ArrayList();
			List rowData = new ArrayList();
			List rowAlign = new ArrayList();   // 컬럼 정렬
			List rowType = new ArrayList();   // 컬럼 type

			
			parameterMap.put("colYn", "N");
			// 목록리스트명
			
			parameterMap.put("sttsViewName", P_STTS_VIEW_NAME);
			
			String infoSttsAlign = "";
			String infoSttsType = "";
			List colAlign = new ArrayList();
			List colType = new ArrayList();
			
			if(selectColStrList != null) {
				if(selectColStrList.length > 1) {
					for(int i= 1; i< selectColStrList.length; i++) {
						System.err.println("selectColStrList["+i+"] => " + selectColStrList[i].toString());
						sch_colList.add(selectColStrList[i].toString());
						
						parameterMap.put("sch_colList", selectColStrList[i].toString());

						parameterMap.put("sch_colList_col", "'" +selectColStrList[i].toString() + "'");
						
						/**
						 * 동적 컬럼 리스트 들
						 */
						List colData = new ArrayList();
						
						
						// 조회조건 추가되는 부분
						System.err.println("@@@ rowInqData ==> " + rowInqData);
						parameterMap.put("rowInqData", rowInqData);
						
						// 목록불러오는 부분
						List sttsList = iproSttsEstmMngDao.selectSttsList(parameterMap);
						
						for(int j=0; j<sttsList.size(); j++) {
							FwkDataEntity infoStts = (FwkDataEntity) sttsList.get(j);
							//System.err.println("@@@ infoStts ==> " + infoStts);
							colData.add(infoStts.get("DATACOLUMN"));
							
							infoSttsAlign = infoStts.getString("ALIGN");
							infoSttsType = infoStts.getString("COLUMNTYPE");
							
						}
						
						colAlign.add(infoSttsAlign);
						colType.add(infoSttsType);
						rowAlign.add(colAlign);
						rowType.add(colType);
						rowData.add(colData);
						
						
					}
					
					trans.put("sttsListData", rowData);
					trans.put("sttsListAlign", colAlign);
					trans.put("sttsListType", colType);
					
					/**
					 * 키값이 되는 컬럼 리스트
					 */
					parameterMap.put("colYn", "Y");

					parameterMap.put("sch_colList", P_STTS_KEY_COLUMN);
					
					
					List<FwkDataEntity> sttsList = iproSttsEstmMngDao.selectSttsList(parameterMap);
					
					//System.err.println("@@@ sttsKeyList ==> " + sttsList);
					
					
					trans.put("sttsKeyList", sttsList);
					trans.put("sttsKeyListCnt", sttsList.size());
					
				}
			}
			
			parameterMap.put("sch_colList", sch_colList);
			
			/**
			 *  체크박스에 체크된 컬럼 리스트
			 */
			List<FwkDataEntity> selectColList = iproSttsEstmMngDao.getColList(parameterMap);
			
			trans.put("selectColList", selectColList);
			trans.put("selectColListCnt", selectColList.size());
		}
		
		trans.put("sttsYn", "Y");
		
		return trans;
		
	}
	
	
	

}
