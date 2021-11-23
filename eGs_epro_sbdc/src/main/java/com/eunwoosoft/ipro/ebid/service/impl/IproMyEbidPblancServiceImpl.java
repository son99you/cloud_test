package com.eunwoosoft.ipro.ebid.service.impl; 

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproMyEbidPblancDao;
import com.eunwoosoft.ipro.ebid.service.IproMyEbidPblancService;

/**
 * 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproMyEbidPblancService")
public class IproMyEbidPblancServiceImpl extends AbstractFwkService implements IproMyEbidPblancService {
	private static final Logger LOG = LoggerFactory.getLogger(IproMyEbidPblancServiceImpl.class);
	
	@Resource(name="iproMyEbidPblancDao")
	private IproMyEbidPblancDao iproMyEbidPblancDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancListInqireWithPgng
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject myBidPblancListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(IproMyEbidPblancService.MY_BID_PBLANC_LIST, 
				iproMyEbidPblancDao.selectMyBidPblancListWithPgng(parameterMap));
		trans.put(IproMyEbidPblancService.MY_BID_PBLANC_LIST_TOTCNT, 
				iproMyEbidPblancDao.selectMyBidPblancListTotcnt(parameterMap));
		
		trans.put("TODAY", FwkDateUtil.getCurrentDateTimeAsString());
		
		return trans;
	}
	
}