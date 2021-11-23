package com.eunwoosoft.ipro.sytm.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.pki.ewLicensePki;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmEstmFrmDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmCodeMngeService;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmFrmService;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmContFormServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 26.
 *
 */
@Service(value="iproSytmEstmFrmService")
public class IproSytmEstmFrmServiceImpl extends AbstractFwkService implements IproSytmEstmFrmService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmEstmFrmServiceImpl.class);
	
	@Resource(name="iproSytmEstmFrmDao") 
	private IproSytmEstmFrmDao iproSytmEstmFrmDao;
	
	@Resource(name = "comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	static final String contextPath = "form";
	
	
	/**
	 * 평가서식 목록
	 */
	public FwkTransferObject estmFrmListWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("estmFrmList", iproSytmEstmFrmDao.selectEstmFrmList(parameterMap));
		trans.put("estmFrmListTotCnt", iproSytmEstmFrmDao.selectEstmFrmListTotCnt(parameterMap));
		
		return trans;
	}
	
	public FwkTransferObject licenseListWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("licenseList", iproSytmEstmFrmDao.selectLicenseList(parameterMap));
		trans.put("licenseListTotCnt", iproSytmEstmFrmDao.selectLicenseListTotCnt(parameterMap));
		
		return trans;
	}
	
	
	/**
	 * 라이센스 저장
	 */
	public FwkTransferObject licenseRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		// 서식번호 생성
		String pLicenseNo = iproSytmEstmFrmDao.selectpLicenseNoCreat();
		parameterMap.put("P_LICENSE_NO", pLicenseNo);
		parameterMap.put("P_DEL_AT", "N");
		
		// 라이센스 등록
		iproSytmEstmFrmDao.insertLicenseMstRegist(parameterMap);
		
		
		trans.put("P_LICENSE_NO", parameterMap.get("P_LICENSE_NO"));
		
		return trans;
	}
	
	/**
	 * 라이센스 상세
	 */
	public FwkTransferObject licenseDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity detail = iproSytmEstmFrmDao.selectLicenseDetail(parameterMap);
		
		
		trans.put("licenseDetail", detail);
		
		if(detail.getString("FILE_GRP_NO").length() > 0) {
			String FILE_GRP_NO = detail.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", FILE_GRP_NO);
			trans.put("licenseFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * 라이센스 수정
	 */
	public FwkTransferObject licenseUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 *  LICENSE_PSCD	A	라이센스진행상태	작성
		 *	LICENSE_PSCD	B	라이센스진행상태	수정
		 *	LICENSE_PSCD	C	라이센스진행상태	발급
		 */
		parameterMap.put("P_LICENSE_PSCD", "B");
		
		// 라이센스 수정
		iproSytmEstmFrmDao.updateLicenseMstUpdt(parameterMap);
		
		trans.put("P_LICENSE_NO", parameterMap.get("P_LICENSE_NO"));
		
		return trans;
	}
	
	
	/**
	 * 라이센스 삭제
	 */
	public FwkTransferObject licenseDelete(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "Y");
		
		//평가서식마스터 삭제
		iproSytmEstmFrmDao.updateLicenseMstDelt(parameterMap);
			
		return trans;
		
	}
	
	/**
	 * 라이센스 발급
	 */
	public FwkTransferObject licenseIssue(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String publicKeySpec =   "1293243615482487846213910607804254606773995627305438107"
								+"3068466088162756209343417256075647838236951286404264832"
								+"6490104582772378490412787229281925620032032403146098808"
								+"3697925988492784559428825173119540868252923127816956131"
								+"4865272293421689139527326968144124118298975147786008868"
								+"8083764074863895802068188409970733/65537";
		
		String privateKeySpec =  "12932436154824878462139106078042546067739956273054381073"
								+"06846608816275620934341725607564783823695128640426483264"
								+"90104582772378490412787229281925620032032403146098808369"
								+"79259884927845594288251731195408682529231278169561314865"
								+"27229342168913952732696814412411829897514778600886880837"
								+"64074863895802068188409970733/47004078491223065591673940"
								+"94617902060417256792714884067938582208829162959954837710"
								+"60197951549207592111554676463218379108234746119235331461"
								+"84528865666158220508976070870860023665918744503905504555"
								+"56548917501796845384212632573167995073130313618965900163"
								+"4410410883459208102675762321410987922158381252100921931473";
		
		
		FwkDataEntity detail = iproSytmEstmFrmDao.selectLicenseDetail(parameterMap);
		
		/**
		 * BIZRNO : 사업자번호
		 * VEND_NM : 업체명
		 * VERSION_NM : 버전명
		 * VEND_IP_ADDR : IP주소
		 * LICENSE_END_DATE : 라이센스 종료 일자
		 * LICENSE_PROCD_SECD : 발급구분
		 */
		
		String originalTxt = detail.getString("BIZRNO") + "|||" + detail.getString("VEND_NM") + "|||" + detail.getString("VERSION_NM") + "|||" + detail.getString("VEND_IP_ADDR") + "|||" + detail.getString("LICENSE_END_DATE") +"|||" + detail.getString("LICENSE_PROCD_SECD");
		
		
		
		// 클라이언트단 공통키로 암호화
		ewLicensePki clientRSA	= new ewLicensePki();
		clientRSA.setPublicKeySpecStr(publicKeySpec);
		String encodeTxt	= clientRSA.encode(originalTxt);

		// 서버에서 새 자원 생성해서 개인키로 복호화
		ewLicensePki serverRSARe	= new ewLicensePki();
		serverRSARe.setPrivateKeySpecStr(privateKeySpec);
		String decodeTxtRe	= serverRSARe.decode(encodeTxt);


////////파일 생성 시작 ////////
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");

        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());

        System.out.println("Today=" + strToday);
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년MM월dd일 HH:mm:ss");

        Calendar c12 = Calendar.getInstance();

        String strToday2 = sdf2.format(c12.getTime());

        System.out.println("Today2=" + strToday2);

		

		String path = "D:\\ew_license\\" + strToday + "\\" + detail.getString("VEND_NM"); //폴더 경로
		File Folder = new File(path);

		System.out.println("path=" + path);
		
		// 폴더가 없으면 생성하는 로직
    	File fileDir = new File(path);
		boolean mkdirSuccess = true;
		if(!fileDir.isDirectory()){
			fileDir.setExecutable(true);
			fileDir.setReadable(true);
			fileDir.setWritable(true);
			mkdirSuccess = fileDir.mkdirs();
		}
		
		
		String filePath = path + "\\license.pem";
		File file = new File(filePath); // File객체 생성 
		if(!file.exists()){ // 파일이 존재하지 않으면 
			file.createNewFile(); // 신규생성 
		}else { // 파일이 존재하면 기존에 파일을 지우고 파일을 신규생성한다.
			file.delete();
			file.createNewFile(); // 신규생성
		}
		
		// BufferedWriter 생성 
		BufferedWriter writer = new	BufferedWriter(new FileWriter(file, true)); // 파일에 쓰기 
		writer.write("/* 라이센스발급정보 ");
		writer.newLine();
		writer.write(" * 업체명 : " + detail.getString("VEND_NM")); 
		writer.newLine();
		writer.write(" * 사업자번호 : " + detail.getString("BIZRNO")); 
		writer.newLine();
		writer.write(" * 발급일시 : " + strToday2);
		writer.newLine();
		writer.write(" * 발급버전 : " + detail.getString("VERSION_NM"));
		writer.newLine();
		writer.write(" * 발급IP주소 : " + detail.getString("VEND_IP_ADDR"));
		writer.newLine();
		String licenseEndDate = detail.getString("LICENSE_END_DATE").substring(0, 4) +"년" + detail.getString("LICENSE_END_DATE").substring(4, 6) + "월" + detail.getString("LICENSE_END_DATE").substring(6) + "일";
		writer.write(" * 라이센스 종료일자 : " + licenseEndDate);
		writer.newLine();
		writer.write(" * 라이센스 발급구분 : " + detail.getString("LICENSE_PROCD_NM"));
		writer.newLine();
		writer.write(" */");
		// 버퍼 및 스트림 뒷정리 
		
		writer.newLine();
		writer.newLine();
		
		writer.write("<-- license msg -->");
		writer.newLine();
		writer.write(encodeTxt);
		
		
		writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기 
		writer.close(); // 스트림 종료

////////파일 생성 종료 ////////

		
// 첨부파일 그룹번호 생성 및 첨부파일경로 DB등록 시작 ///

		String FILE_GRP_NO = "";
		//첨부파일 그룹번호가 존재하지 않으면 생성
		if(detail.getString("FILE_GRP_NO").length() == 0) {
			FILE_GRP_NO = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}else {
			FILE_GRP_NO = detail.getString("FILE_GRP_NO").toString();
		}
		
		
		FwkParameterMap fileMap = new FwkParameterMap();
		
		fileMap.put("P_FILE_GRP_NO", FILE_GRP_NO);
		fileMap.put("P_FILE_SN", "1"); // 파일이 1개뿐이라 강제코딩
		fileMap.put("P_SV_FILE_NM", "license.pem"); // 파일난독화는 사용하지 않았음.
		fileMap.put("P_SYS_FILE_NM", "license.pem"); // 파일명은 사용자가 변경하지 않고 이용가능하도록 통일함.
		String filePathInDB = "D:/ew_license/" + strToday + "/" + detail.getString("VEND_NM");
		fileMap.put("P_FILE_LCTN", filePathInDB); 
		fileMap.put("P_FILE_DOC_NM", "라이센스파일");
		fileMap.put("P_FILE_DOC_SECD", "LICENSE");
		fileMap.put("USR_ID", parameterMap.get("USR_ID").toString());
		fileMap.put("USR_NM", parameterMap.get("USR_NM").toString());
		fileMap.put("CONN_IP", parameterMap.get("CONN_IP").toString());
		
		// 파일정보 DB등록
		iproSytmEstmFrmDao.mergeFileMstRegist(fileMap);
		
// 첨부파일 그룹번호 생성 및 첨부파일경로 DB등록 종료 ///		


		
		/**
		 *  LICENSE_PSCD	A	라이센스진행상태	작성
		 *	LICENSE_PSCD	B	라이센스진행상태	수정
		 *	LICENSE_PSCD	C	라이센스진행상태	발급
		 */
		parameterMap.put("P_LICENSE_PSCD", "C");
		parameterMap.put("P_FILE_GRP_NO", FILE_GRP_NO);
		
		
		// 라이센스 수정
		iproSytmEstmFrmDao.updateLicensePscdUpdt(parameterMap);
		
		
		return trans;
		
	}
	
	
	
	public FwkTransferObject licenseListExcelDwld(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("dataList", iproSytmEstmFrmDao.selectLicenseExcelList(parameterMap));
		
		return trans;
	}
	

	public FwkTransferObject estmFrmListExcelDwld(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("dataList", iproSytmEstmFrmDao.selectEstmFrmExcelList(parameterMap));
		
		return trans;
	}

	
	/**
	 * DB생성샘플 저장
	 */
	public FwkTransferObject dataBaseRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가서식 마스터 등록
		iproSytmEstmFrmDao.insertDataBaseRegist(parameterMap);
		
		return trans;
	}

	
	
	
	/**
	 * 평가서식 저장
	 */
	public FwkTransferObject estmFrmRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 서식번호 생성
		String pFrmNo = iproSytmEstmFrmDao.selectpFrmNoCreat();
		parameterMap.put("P_ESTM_FRM_NO", pFrmNo);
		parameterMap.put("P_DEL_AT", "N");

		if (parameterMap.get("P_ESTM_FRM_NO") != null) {
			// 평가서식 마스터 등록
			iproSytmEstmFrmDao.insertEstmFrmMstRegist(parameterMap);
			
			// 평가서식항목 등록
			if(parameterMap.get("P_ESTM_DTL_ITEM_NO") != null) {
				
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가항목명
				Object P_ESTM_DTL_ITEM_NM = parameterMap.get("P_ESTM_DTL_ITEM_NM");
				//평가항목내용
				Object P_ESTM_ITEM_DTL_CNTN = parameterMap.get("P_ESTM_ITEM_DTL_CNTN");
				//배점
				Object P_ESTM_DTL_ITEM_SCR = parameterMap.get("P_ESTM_DTL_ITEM_SCR");
				//평가방법구분코드				
				Object P_ESTM_DTL_ITEM_SCR_SECD = parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
						parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
						parameterMap.put("P_ESTM_ITEM_NM", parameterMap.get("P_ESTM_DTL_ITEM_NM"));
						parameterMap.put("P_ESTM_ITEM_DESC", parameterMap.get("P_ESTM_ITEM_DTL_CNTN"));
						parameterMap.put("P_ESTM_ITEM_DSMK", parameterMap.get("P_ESTM_DTL_ITEM_SCR"));
						parameterMap.put("P_ESTM_MTHD_SECD", parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD"));
						
						// 평가서식항목 등록
						iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가항목번호
						ArrayList<String> estmDtlItemNoList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가항목명
						ArrayList<String> estmDtlItemNmList = (ArrayList<String>) P_ESTM_DTL_ITEM_NM;
						//평가항목내용
						ArrayList<String> estmItemDtlCntnList = (ArrayList<String>) P_ESTM_ITEM_DTL_CNTN;
						//배점
						ArrayList<String> estmDtlItemScrList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR;
						//평가방법구분코드				
						ArrayList<String> estmDtlItemScrSecdList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR_SECD;
						
							for(int i =0; i < estmDtlItemNoList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", estmDtlItemNoList.get(i));
								parameterMap.put("P_ESTM_ITEM_NM", estmDtlItemNmList.get(i));
								parameterMap.put("P_ESTM_ITEM_DESC", estmItemDtlCntnList.get(i));
								parameterMap.put("P_ESTM_ITEM_DSMK", estmDtlItemScrList.get(i));
								parameterMap.put("P_ESTM_MTHD_SECD", estmDtlItemScrSecdList.get(i));
								
								// 평가서식항목 등록
								iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						String[] P_ESTM_DTL_ITEM_NMList = (String[]) P_ESTM_DTL_ITEM_NM;
						String[] P_ESTM_ITEM_DTL_CNTNList = (String[]) P_ESTM_ITEM_DTL_CNTN;
						String[] P_ESTM_DTL_ITEM_SCRList = (String[]) P_ESTM_DTL_ITEM_SCR;
						String[] P_ESTM_DTL_ITEM_SCR_SECDList = (String[]) P_ESTM_DTL_ITEM_SCR_SECD;
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_ITEM_NM", P_ESTM_DTL_ITEM_NMList[idx]);
							parameterMap.put("P_ESTM_ITEM_DESC", P_ESTM_ITEM_DTL_CNTNList[idx]);
							parameterMap.put("P_ESTM_ITEM_DSMK", P_ESTM_DTL_ITEM_SCRList[idx]);
							parameterMap.put("P_ESTM_MTHD_SECD", P_ESTM_DTL_ITEM_SCR_SECDList[idx]);

							// 평가서식항목 등록
							iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);
						}
					}
					
				}//P_ESTM_DTL_ITEM_NO != null
				
			}//P_ESTM_DTL_ITEM_NO != null
		}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		return trans;
	}
	
	
	/**
	 * 평가서식 상세
	 */
	public FwkTransferObject estmFrmDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmFrmDetail", iproSytmEstmFrmDao.selectEstmFrmDetail(parameterMap));
		trans.put("estmFrmItemList", iproSytmEstmFrmDao.selectEstmFrmItemList(parameterMap));
		
		return trans;
	}

	
	/**
	 * 평가서식 저장
	 */
	public FwkTransferObject estmFrmUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 서식번호 생성
		//String pFrmNo = iproSytmEstmFrmDao.selectpFrmNoCreat();
		//parameterMap.put("P_ESTM_FRM_NO", pFrmNo);
		//parameterMap.put("P_DEL_AT", "N");

		if (parameterMap.get("P_ESTM_FRM_NO") != null) {
			// 평가서식 마스터 등록
			iproSytmEstmFrmDao.updateEstmFrmMstUpdt(parameterMap);
			
			// 평가서식항목 삭제
			iproSytmEstmFrmDao.deleteEstmFrmItemDelt(parameterMap);
			
			// 평가서식항목 등록
			if(parameterMap.get("P_ESTM_DTL_ITEM_NO") != null) {
				
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가항목명
				Object P_ESTM_DTL_ITEM_NM = parameterMap.get("P_ESTM_DTL_ITEM_NM");
				//평가항목내용
				Object P_ESTM_ITEM_DTL_CNTN = parameterMap.get("P_ESTM_ITEM_DTL_CNTN");
				//배점
				Object P_ESTM_DTL_ITEM_SCR = parameterMap.get("P_ESTM_DTL_ITEM_SCR");
				//평가방법구분코드				
				Object P_ESTM_DTL_ITEM_SCR_SECD = parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
						
						parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
						parameterMap.put("P_ESTM_ITEM_NM", parameterMap.get("P_ESTM_DTL_ITEM_NM"));
						parameterMap.put("P_ESTM_ITEM_DESC", parameterMap.get("P_ESTM_ITEM_DTL_CNTN"));
						parameterMap.put("P_ESTM_ITEM_DSMK", parameterMap.get("P_ESTM_DTL_ITEM_SCR"));
						parameterMap.put("P_ESTM_MTHD_SECD", parameterMap.get("P_ESTM_DTL_ITEM_SCR_SECD"));
						
						// 평가서식항목 등록
						iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가항목번호
						ArrayList<String> estmDtlItemNoList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가항목명
						ArrayList<String> estmDtlItemNmList = (ArrayList<String>) P_ESTM_DTL_ITEM_NM;
						//평가항목내용
						ArrayList<String> estmItemDtlCntnList = (ArrayList<String>) P_ESTM_ITEM_DTL_CNTN;
						//배점
						ArrayList<String> estmDtlItemScrList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR;
						//평가방법구분코드				
						ArrayList<String> estmDtlItemScrSecdList = (ArrayList<String>) P_ESTM_DTL_ITEM_SCR_SECD;
						
							for(int i =0; i < estmDtlItemNoList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", estmDtlItemNoList.get(i));
								parameterMap.put("P_ESTM_ITEM_NM", estmDtlItemNmList.get(i));
								parameterMap.put("P_ESTM_ITEM_DESC", estmItemDtlCntnList.get(i));
								parameterMap.put("P_ESTM_ITEM_DSMK", estmDtlItemScrList.get(i));
								parameterMap.put("P_ESTM_MTHD_SECD", estmDtlItemScrSecdList.get(i));
								
								// 평가서식항목 등록
								iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						String[] P_ESTM_DTL_ITEM_NMList = (String[]) P_ESTM_DTL_ITEM_NM;
						String[] P_ESTM_ITEM_DTL_CNTNList = (String[]) P_ESTM_ITEM_DTL_CNTN;
						String[] P_ESTM_DTL_ITEM_SCRList = (String[]) P_ESTM_DTL_ITEM_SCR;
						String[] P_ESTM_DTL_ITEM_SCR_SECDList = (String[]) P_ESTM_DTL_ITEM_SCR_SECD;
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_ITEM_NM", P_ESTM_DTL_ITEM_NMList[idx]);
							parameterMap.put("P_ESTM_ITEM_DESC", P_ESTM_ITEM_DTL_CNTNList[idx]);
							parameterMap.put("P_ESTM_ITEM_DSMK", P_ESTM_DTL_ITEM_SCRList[idx]);
							parameterMap.put("P_ESTM_MTHD_SECD", P_ESTM_DTL_ITEM_SCR_SECDList[idx]);

							// 평가서식항목 등록
							iproSytmEstmFrmDao.insertEstmFrmMstItemRegist(parameterMap);
						}
					}
					
				}//P_ESTM_DTL_ITEM_NO != null
				
			}//P_ESTM_DTL_ITEM_NO != null
		}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		return trans;
	}
	
	/**
	 * 평가서식 삭제
	 */
	public FwkTransferObject estmFrmDelete(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
			//평가서식마스터 삭제
			iproSytmEstmFrmDao.deleteEstmFrmMstDelt(parameterMap);
			
			//평가서식항목 삭제
			iproSytmEstmFrmDao.deleteEstmFrmItemDelt(parameterMap);
		
		
		return trans;
		
	}
	


}
