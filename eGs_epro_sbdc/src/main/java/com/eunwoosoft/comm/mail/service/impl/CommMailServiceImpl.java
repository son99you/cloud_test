package com.eunwoosoft.comm.mail.service.impl; 

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.atfi.controller.ComAtfiAtchFileController;
import com.eunwoosoft.comm.mail.dao.CommMailDao;
import com.eunwoosoft.comm.mail.service.CommMailService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * SMS, mail 관련
 */
@Service("commMailService")
public class CommMailServiceImpl extends AbstractFwkService implements CommMailService {
	private static final Logger LOG = LoggerFactory.getLogger(ComAtfiAtchFileController.class);
	
	@Resource(name="commMailDao")
	private CommMailDao commMailDao; 

	/**
	 * 	
	 * 
	 * 	<pre>
	 *  1. 개요 : MESSENGER 전송
	 * 	2. 처리내용 : M_NOTICE_MSG INSERT 한다. 
	 *	</pre>
	 *
	 * @method Name : insertSendSms
	 * @Author : joo
	 * @Date   : 2020. 10. 15.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 15.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 * 
	 */
	//메일전송
	// SMS
	@Override
	public FwkTransferObject insertSendSms(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		System.err.println("=============================== insertSendSms START =====================================");
		/*MSG_TTL
		 MSG_CNTN*/
		String tCode = FwkMessageUtil.getMessage("MSG.TCODE");
		String bCode = FwkMessageUtil.getMessage("MSG.BCODE");
		String msgCntn = "";
		String msgTtl = "";
		String sender = "";
		String senderName = ""; 
		String receiver = "";

		List<FwkDataEntity> list = (List<FwkDataEntity>) parameterMap.get("list"); 
		List<FwkDataEntity> recList = (List<FwkDataEntity>) parameterMap.get("listReceiver"); 
		//입찰의 경우 송신자가 지정되어있지 않음 
		//parameterMap.put("pSenderid", bidReqPrcnDetail.getString("RQR_CHRGR_ID"));
		//parameterMap.put("pSenderNm", bidReqPrcnDetail.getString("RQR_CHRGR_NM"));
		//parameterMap.put("pReceiverid", bidReqPrcnDetail.getString(""));
	
		parameterMap.put("P_MSG_SECD","MESSENGER");
		parameterMap.put("P_MSG_OBJ_ID",parameterMap.getString("P_MSG_OBJ_ID"));
		FwkDataEntity smsMsg = commMailDao.getContents(parameterMap); 
		
		msgCntn = smsMsg.getString("MSG_CNTN"); 
		msgTtl = smsMsg.getString("MSG_TTL");
		 
		//발신자가 정해져있지 않은 경우에는
		//프로퍼티에 정해져있는 발신자에게 전송
		if("N".equals(parameterMap.getString("P_SENDERYN"))){
			sender = FwkMessageUtil.getMessage("MSG.SENDER");
			senderName = FwkMessageUtil.getMessage("MSG.SENDERNAME");
		}else{
			for(int i=0; i<list.size(); i++){	//발신하는 사람은 한명
				FwkDataEntity data = list.get(i);
				sender = data.getString("pSenderid");
				senderName = data.getString("pSenderNm");
			}
		}
		
		//수신자가 정해져있지 않은 경우에는
		//프로퍼티에 정해진 수신자에게 발송
		List<Map<String, Object>> receiverList = new ArrayList<Map<String,Object>>();
		if("N".equals(parameterMap.getString("P_RECEIVERYN"))){
			receiver = FwkMessageUtil.getMessage("MSG.RECEIVER");	//여러명 일 경우는 ,구분
			if("3".equals(parameterMap.get("P_AUTH_ID"))){
				receiver = FwkMessageUtil.getMessage("MSG.LAWRECEIVER");	
			}
		}else{
			for(int j=0; j<recList.size(); j++){	//수신하는 사람은 여러명일 경우도 있음
				FwkParameterMap dataMap = new FwkParameterMap();
				
				FwkDataEntity listMap = recList.get(j);
				
				dataMap.put("receiver", listMap.getString("pReceiverid"));// 송신자 받아오도록
				receiverList.add(dataMap);
			}
		}
		System.err.println(" = = = receiver = = " + receiver);
		System.err.println(" = = = receiverList = = " + receiverList);
		for(int i=0; i<list.size(); i++){	
			System.err.println(" list.get(0).getStringCONT_NM = > " + list.get(0).getString("CONT_NM"));
			msgTtl = msgTtl.replace("#계약명#", list.get(0).getString("CONT_NM"));
			msgCntn = msgCntn.replace("#계약명#", list.get(0).getString("CONT_NM"));
		}
		
		parameterMap.put("P_T_CODE",tCode);
		parameterMap.put("P_B_CODE",bCode); 
		parameterMap.put("P_SENDER",sender); 
		parameterMap.put("P_SENDERNAME",senderName);
		parameterMap.put("P_TITLE", msgTtl);
		parameterMap.put("P_CONTENTS",msgCntn);
		System.out.println("0000");
		if("N".equals(parameterMap.getString("P_RECEIVERYN"))){	//프로퍼티에서 가져온경우
			
				//,가 포함되어 있는지 확인 
				if(receiver.indexOf(",") > -1){	//","포함되어 있으면 
					String[] receiverArray = receiver.split(",");
					for (int i = 0; i < receiverArray.length; i++) {
						parameterMap.put("P_RECEIVER", receiverArray[i]);
						//M_NOTICE_MSG 테이블에 INSERT
						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
							commMailDao.insertSendSms(parameterMap);	 
						}
					}
				}else{	//,포함되어있지않으면 한명
					System.err.println("receiver ,포함되어있지않으면");
					parameterMap.put("P_RECEIVER", receiver);
					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
						commMailDao.insertSendSms(parameterMap);
					}
				}
			
		}else{	//송신자를 파라미터로 받은경우
			for(int k=0; k<receiverList.size(); k++){
				parameterMap.put("P_RECEIVER", receiverList.get(k).get("receiver"));
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					commMailDao.insertSendSms(parameterMap);
				}
			}
			
		}

		return trans;
	}
	
	/**
	 * smtp 메일 전송
	 * 
	 */
	public void sendMail(FwkParameterMap parameterMap) throws Exception {

		String FROM = URLEncoder.encode(FwkMessageUtil.getMessage("smtp.mail.from"),"UTF-8"); 
		String FROMNAME = URLEncoder.encode(FwkMessageUtil.getMessage("EW.EPRO.ENTRPS_NM"),"UTF-8");  // "중소기업유통센터";
				
		String TO =	URLEncoder.encode(parameterMap.getString("P_TO_MAIL"),"UTF-8");	//받는사람 emal_addr
		String CONT_NM = parameterMap.getString("P_CONT_NM");	//계약명
		
		String SMTP_USERNAME = URLEncoder.encode(FwkMessageUtil.getMessage("smtp.mail.username"),"UTF-8");	// 사용자 ID (mail.knto.or.kr에서 사용하는 계정)
		String SMTP_PASSWORD = URLEncoder.encode(FwkMessageUtil.getMessage("smtp.mail.password"),"UTF-8"); // 보내는 사용자 비밀번호 

		String HOST = URLEncoder.encode(FwkMessageUtil.getMessage("smtp.mail.server.ip"),"UTF-8");
		String PORT = URLEncoder.encode(FwkMessageUtil.getMessage("smtp.mail.server.port"),"UTF-8");
 
		parameterMap.put("P_MSG_SECD","MAIL");
		//진행상태
		parameterMap.put("P_MSG_OBJ_ID",parameterMap.getString("P_MSG_OBJ_ID"));
		FwkDataEntity smsMsg = commMailDao.getContents(parameterMap); 
		
		String SUBJECT = URLEncoder.encode(smsMsg.getString("MSG_TTL").replace("#계약명#", CONT_NM),"UTF-8");
		String BODY = URLEncoder.encode(smsMsg.getString("MSG_CNTN").replace("#계약명#", CONT_NM),"UTF-8");	// 내용
 
		 try {

			 System.err.println("  = = = =  reqUrl  start = = = =");
				URL reqUrl = new URL(FwkMessageUtil.getMessage("SMTP.URL")+"?from="+FROM
						+"&fromName="+FROMNAME
						+"&to="+TO
						+"&subject="+SUBJECT
						+"&body="+BODY
						+"&host="+HOST
						+"&port="+PORT
						+"&userName="+SMTP_USERNAME
						+"&password="+SMTP_PASSWORD);
				
		       HttpURLConnection con; 
		       System.err.println("  = = = =  HttpURLConnection  start = = = =" + reqUrl);
		       con = (HttpURLConnection)reqUrl.openConnection();
			 
	          System.err.println("<==  응답코드  ==> "+ con.getResponseCode());
	          System.err.println("<== 응답메시지 ==> "+ con.getResponseMessage());
		       System.err.println("  = = = =  HttpURLConnection  start ing = = = =");
		       
		       //con.disconnect();
		       
		    } catch (MalformedURLException e) {
		       e.printStackTrace();
		       throw e;
		    }
		 
		
	} 
	
	

	public void commSend(FwkParameterMap parameterMap, String gbn) throws Exception {//commSend 시작

		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		if("contReqPscdUpdt".equals(gbn)){
			
			if(parameterMap.get("P_CONT_PSCD").equals("C003")){
				
				
				/**
				 * SMS 발송 시작
				 */
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","N");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				parameterMap.put("P_AUTH_ID", "3");	//법무팀
//				List<FwkDataEntity> receiverList = iproPrpoContReqDao.selectReceiverList(parameterMap);
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				for(int i=0; i<receiverList.size(); i++){	// 
//					FwkDataEntity dataMap = receiverList.get(i);
//					dataMap.put("pReceiverid", dataMap.getString("USR_ID"));
//					listReceiver.add(dataMap);
//				} 
//				for(int i=0; i<contReqDetail.size(); i++){	// 
//					
//					FwkParameterMap contMap = new FwkParameterMap();
//					FwkDataEntity listMap = contReqDetail.get(i);
//	
//					listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					
//				} 
				parameterMap.put("listReceiver", listReceiver);
				parameterMap.put("list", list);
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C004")){
				
				/**
				 * SMS 발송 시작
				 */
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				parameterMap.put("P_PRE_CONT_PSCD", "C003");	//법무팀검토요청
//				FwkDataEntity receiverList = iproPrpoContReqDao.selectReceiver(parameterMap);
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String pReceiverid = "";
				
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//					listReceiver.add(receiverList);
//	
//					for(int i=0; i<contReqDetail.size(); i++){	// 
//						
//						FwkDataEntity listMap = contReqDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//						list.add(listMap); 
//						
//						
//					} 
//					parameterMap.put("listReceiver", listReceiver);
//					parameterMap.put("list", list);
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
			}else if(parameterMap.get("P_CONT_PSCD").equals("C006")){
	
				/**
					 * parameter로 
					 * P_TO_MAIL = 받는사람 
					 * P_MSG_OBJ_ID = 진행상태 
					 * 
				*/  
				//데이터 select
//				FwkDataEntity contReqDetail =  iproPrpoContReqDao.selectContReqDetail(parameterMap);
				parameterMap.put("P_ORDR_AGNC_YN","N"); 
//				List<FwkDataEntity> contReqVendList = iproPrpoContReqDao.selectContReqEntrpsList(parameterMap);
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
//				parameterMap.put("P_CONT_NM", contReqDetail.getString("CONT_NM"));
//				for(int i=0; i < contReqVendList.size(); i++){
//					FwkDataEntity contReqVend= contReqVendList.get(i);
//					
//					parameterMap.put("P_TO_MAIL",contReqVend.getString("CHRGR_EMAL"));
//					if("true".equals(FwkMessageUtil.getMessage("email.send"))){
//						if(!"N".equals(parameterMap.getString("P_TO_MAIL"))){
//							sendMail(parameterMap);
//						}
//					}
//				}
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C009")){
				/**
				 * SMS 발송 시작
				 */
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","N");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				parameterMap.put("PRCH_DEPT_NO", parameterMap.get("P_PRCH_DEPT_NO"));	//계약담담자
//				List<FwkDataEntity> receiverList = iproPrpoContReqDao.selectReceiverList(parameterMap);
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				for(int i=0; i<receiverList.size(); i++){	// 
//					FwkDataEntity dataMap = receiverList.get(i);
//					dataMap.put("pReceiverid", dataMap.getString("USR_ID"));
//					listReceiver.add(dataMap);
//					
//				} 
//				for(int i=0; i<contReqDetail.size(); i++){	// 
//					
//					FwkParameterMap contMap = new FwkParameterMap();
//					
//					
//					FwkDataEntity listMap = contReqDetail.get(i);
//	
//					listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					
//				} 
				parameterMap.put("listReceiver", listReceiver);
				parameterMap.put("list", list);
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap); 
				}
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C012")){
				
				//알림
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID","C031");
				
				//파라미터로 받아올경우
				parameterMap.put("P_PRE_CONT_PSCD", "C030");	 //작성완료 요청자
//				FwkDataEntity receiverList = iproPrpoContReqDao.selectReceiver(parameterMap);
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
				
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String pReceiverid = "";
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//					listReceiver.add(receiverList);
//					
//					for(int i=0; i<contReqDetail.size(); i++){	// 
//						
//						FwkDataEntity listMap = contReqDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//						list.add(listMap); 
//						
//					} 
//				}
				parameterMap.put("listReceiver", listReceiver);
				parameterMap.put("list", list);
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
				
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","N");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				//parameterMap.put("PRCH_DEPT_NO", parameterMap.get("P_PRCH_DEPT_NO"));	//계약담담자
				//List<FwkDataEntity> receiverList = iproPrpoContReqDao.selectReceiverList(parameterMap);
				//List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
				//List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				//List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				/*for(int i=0; i<receiverList.size(); i++){	// 
					FwkDataEntity dataMap = receiverList.get(i);
					dataMap.put("pReceiverid", dataMap.getString("USR_ID"));
					listReceiver.add(dataMap);
					
				} */
//				if("B".equals(contReqDetail.get(0).getString("PRCH_CHRG_SECD"))){
//					for(int i=0; i<contReqDetail.size(); i++){	// 
//						
//						FwkParameterMap contMap = new FwkParameterMap();
//						
//						
//						FwkDataEntity listMap = contReqDetail.get(i);
//	
//						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//						list.add(listMap); 
//						
//					} 
//					parameterMap.put("listReceiver", listReceiver);
//					parameterMap.put("list", list);
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
				

			}else if(parameterMap.get("P_CONT_PSCD").equals("C013")){
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				/*parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				parameterMap.put("PRCH_DEPT_NO", parameterMap.get("P_PRCH_DEPT_NO"));	//계약담담자
				List<FwkDataEntity> receiverList = iproPrpoContReqDao.selectReceiverList(parameterMap);
				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				for(int i=0; i<receiverList.size(); i++){	// 
					FwkDataEntity dataMap = receiverList.get(i);
					dataMap.put("pReceiverid", dataMap.getString("USR_ID"));
					listReceiver.add(dataMap);
					
				} 
				for(int i=0; i<contReqDetail.size(); i++){	// 
					
					FwkParameterMap contMap = new FwkParameterMap();
					
					
					FwkDataEntity listMap = contReqDetail.get(i);
	
					listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
					list.add(listMap); 
					
				} 
				parameterMap.put("listReceiver", listReceiver);
				parameterMap.put("list", list);
				commMailService.insertSendSms(parameterMap); */
			}else if(parameterMap.get("P_CONT_PSCD").equals("C030")){
				//작성완료 시 부서장에게 알림메시지
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
				//의뢰계약일경우
//				if("B".equals(contReqDetail.get(0).get("PRCH_CHRG_SECD"))){
//					//부서장일 경우 
//					List<FwkDataEntity> receiverList = iproPrpoContReqDao.selectApprReceiver(parameterMap);
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					if(receiverList != null){
//						for(int i=0; i<receiverList.size(); i++){	// 
//							FwkDataEntity receiverMap = receiverList.get(i);
//							receiverMap.put("pReceiverid", receiverMap.getString("USR_ID"));
//							listReceiver.add(receiverMap);
//						}
//						
//						for(int i=0; i<contReqDetail.size(); i++){	// 
//							
//							FwkDataEntity listMap = contReqDetail.get(i);
//							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//							list.add(listMap); 
//							
//							
//						} 
//						parameterMap.put("listReceiver", listReceiver);
//						parameterMap.put("list", list);
//						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//							insertSendSms(parameterMap);
//						}
//					}
//				}
			}else{
				/*if("C031".equals(parameterMap.get("P_CONT_PSCD"))){
					//알림
					//sms 발송
					//"N" 일 경우 프로퍼티에서 받아오도록
					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
					parameterMap.put("P_SENDERYN","N");
					parameterMap.put("P_RECEIVERYN","Y");
					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
					 
					//파라미터로 받아올경우
					parameterMap.put("P_PRE_CONT_PSCD", "C030");	 //작성완료 요청자
					FwkDataEntity receiverList = iproPrpoContReqDao.selectReceiver(parameterMap);
					List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					String pReceiverid = "";
					if(receiverList != null){
						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
						listReceiver.add(receiverList);
		
						for(int i=0; i<contReqDetail.size(); i++){	// 
							
							FwkDataEntity listMap = contReqDetail.get(i);
							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
							list.add(listMap); 
							
						} 
					}
					parameterMap.put("listReceiver", listReceiver);
					parameterMap.put("list", list);
						
					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
						insertSendSms(parameterMap);
					}
				}*/
				if(parameterMap.get("P_CONT_PSCD").equals("C005")){		//법무팀검토반려
					/**
					 * SMS 발송 시작
					 */
					//sms 발송
					//"N" 일 경우 프로퍼티에서 받아오도록
					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
					parameterMap.put("P_SENDERYN","N");
					parameterMap.put("P_RECEIVERYN","Y");
					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
					 
					//파라미터로 받아올경우
					parameterMap.put("P_PRE_CONT_PSCD", "C003");	//법무팀검토요청
//					FwkDataEntity receiverList = iproPrpoContReqDao.selectReceiver(parameterMap);
//					List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
	
					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					String pReceiverid = "";
//					if(receiverList != null){
//						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//						listReceiver.add(receiverList);
//		
//						for(int i=0; i<contReqDetail.size(); i++){	// 
//							
//							FwkDataEntity listMap = contReqDetail.get(i);
//							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//							list.add(listMap); 
//							
//							
//						} 
//						parameterMap.put("listReceiver", listReceiver);
//						parameterMap.put("list", list);
//						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//							insertSendSms(parameterMap);
//						}
//					}
				}
				
			}
		}else if("cntrctRcept".equals(gbn)){
			/**
			 * parameter로 
			 * P_TO_MAIL = 받는사람 
			 * P_MSG_OBJ_ID = 진행상태 
			 * 
			*/  
			//데이터 select
//			FwkDataEntity contDetail =  iproContProgrDao.selectCtrtcWritngDetail(parameterMap);
			
//			if("C027".equals(contDetail.getString("CONT_PSCD"))){
//					parameterMap.put("P_ORDR_AGNC_YN","N"); 
//					List<FwkDataEntity> contVendList = iproContDao.selectCntrctEntrpsInqire(parameterMap);
//					parameterMap.put("P_MSG_OBJ_ID",contDetail.getString("CONT_PSCD"));
//					parameterMap.put("P_CONT_NM", contDetail.getString("CONT_NM"));
//					for(int i=0; i < contVendList.size(); i++){
//						FwkDataEntity contVend= contVendList.get(i);
//						
//						parameterMap.put("P_TO_MAIL",contVend.getString("CHRGR_EMAL"));
//						if("true".equals(FwkMessageUtil.getMessage("email.send"))){
//							if(!"N".equals(parameterMap.getString("P_TO_MAIL"))){
//								sendMail(parameterMap); 
//							}
//						}
//					}
//					
//					//sms 발송
//					//"N" 일 경우 프로퍼티에서 받아오도록
//					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
//					parameterMap.put("P_SENDERYN","N");
//					parameterMap.put("P_RECEIVERYN","Y");
//					parameterMap.put("P_MSG_OBJ_ID",contDetail.getString("CONT_PSCD"));
//					
//					//파라미터로 받아올경우
//					//계약요청자
////					FwkDataEntity contReqDetail = iproContProgrDao.selectCtrtcWritngDetail(parameterMap);
//					
//					parameterMap.put("P_PRE_CONT_PSCD", "C012");	//계약요청자
////					FwkDataEntity receiverList = iproContProgrDao.selectReceiver(parameterMap);
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					String pReceiverid = "";
////					if(receiverList != null){
////						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
////						listReceiver.add(receiverList);
////						
////						FwkDataEntity listMap = contReqDetail;
////						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
////						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
////						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
////						list.add(listMap); 
////						
////						parameterMap.put("listReceiver", listReceiver);
////						parameterMap.put("list", list);
////						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
////							insertSendSms(parameterMap);
////						}
////					}
//			}
		}else if("contPscdUpdt".equals(gbn)) {
			
			if(parameterMap.get("P_CONT_PSCD").equals("C010")){
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
				parameterMap.put("P_PRE_CONT_PSCD", "C009");	//경영지원팀검토요청
//				FwkDataEntity receiverList = iproContProgrDao.selectReceiver(parameterMap);
//				List<FwkDataEntity>contDetail = iproContProgrDao.selectContDetailList(parameterMap);

				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String pReceiverid = "";
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//					listReceiver.add(receiverList);
//	
//					for(int i=0; i<contDetail.size(); i++){	// 
//						
//						FwkDataEntity listMap = contDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//						list.add(listMap); 
//						
//						
//					} 
//					parameterMap.put("listReceiver", listReceiver);
//					parameterMap.put("list", list);
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
				
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C018")){
				
				/**
					 * parameter로 
					 * P_TO_MAIL = 받는사람 
					 * P_MSG_OBJ_ID = 진행상태 
					 * 
				*/  
				//데이터 select
//				List<FwkDataEntity>contDetail = iproContProgrDao.selectContDetailList(parameterMap);
				
//				FwkDataEntity contReqDetail =  iproPrpoContReqDao.selectContReqDetail(parameterMap);
				
				parameterMap.put("P_ORDR_AGNC_YN","N"); 
				
//				List<FwkDataEntity> contReqVendList = iproPrpoContReqDao.selectContReqEntrpsList(parameterMap);
				
//				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				parameterMap.put("P_MSG_OBJ_ID","C006");
//				parameterMap.put("P_CONT_NM", contReqDetail.getString("CONT_NM"));
//				for(int i=0; i < contReqVendList.size(); i++){
//					
//					FwkDataEntity contReqVend= contReqVendList.get(i);
//					
//					
//					parameterMap.put("P_TO_MAIL",contReqVend.getString("CHRGR_EMAL"));
//					
//					if("true".equals(FwkMessageUtil.getMessage("email.send"))){
//						
//						if(!"N".equals(parameterMap.getString("P_TO_MAIL"))){
//							
//							sendMail(parameterMap);
//							
//						}
//					}
//				}
				
				
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C021")){
				/**
				 * 	 * 계약쪽 mail 보내는 쪽 참고 
					 * parameter로 
					 * P_TO_MAIL = 받는사람 
					 * P_MSG_OBJ_ID = 진행상태 
					 * 
				*/  
				//데이터 select
//				FwkDataEntity contDetail =  iproContProgrDao.selectCtrtcWritngDetail(parameterMap);
//				parameterMap.put("P_ORDR_AGNC_YN","N"); 
//				List<FwkDataEntity> contVendList = iproContProgrDao.selectCntrctEntrpsInqire(parameterMap);
//				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
//				parameterMap.put("P_CONT_NM", contDetail.getString("CONT_NM"));
//				for(int i=0; i < contVendList.size(); i++){
//					FwkDataEntity contVend= contVendList.get(i);
//					
//					parameterMap.put("P_TO_MAIL",contVend.getString("CHRGR_EMAL"));
//					if("true".equals(FwkMessageUtil.getMessage("email.send"))){
//						if(!"N".equals(parameterMap.getString("P_TO_MAIL"))){
//							sendMail(parameterMap);
//						}
//					}
//				}
				
			}else if(parameterMap.get("P_CONT_PSCD").equals("C027")){
				/**
				 * parameter로 
				 * P_TO_MAIL = 받는사람 
				 * P_MSG_OBJ_ID = 진행상태 
				 * 
				*/  
				//데이터 select
//				FwkDataEntity contDetail =  iproContProgrDao.selectCtrtcWritngDetail(parameterMap);
				parameterMap.put("P_ORDR_AGNC_YN","N"); 
//				List<FwkDataEntity> contVendList = iproContProgrDao.selectCntrctEntrpsInqire(parameterMap);
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
//				parameterMap.put("P_CONT_NM", contDetail.getString("CONT_NM"));
			
//				for(int i=0; i < contVendList.size(); i++){
//					FwkDataEntity contVend= contVendList.get(i);
//					
//					parameterMap.put("P_TO_MAIL",contVend.getString("CHRGR_EMAL"));
//					if("true".equals(FwkMessageUtil.getMessage("email.send"))){
//						if(!"N".equals(parameterMap.getString("P_TO_MAIL"))){
//							sendMail(parameterMap); 
//						}
//					}
//				}
				
				
				//완료시 서명요청자에게 메시지 보내기
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_REGR_ID"		, user.get("USR_ID"));
				parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				 
				//파라미터로 받아올경우
//				FwkDataEntity contReqDetail = iproContProgrDao.selectCtrtcWritngDetail(parameterMap);
				
				//의뢰계약일경우만 알림메시지
//				if("B".equals(contReqDetail.getString("PRCH_CHRG_SECD"))){
//					parameterMap.put("P_PRE_CONT_PSCD", "C012");	//계약요청자
//					FwkDataEntity receiverList = iproContProgrDao.selectReceiver(parameterMap);
//					
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					String pReceiverid = "";
//					if(receiverList != null){
//						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//						listReceiver.add(receiverList);
//						
//						FwkDataEntity listMap = contReqDetail;
//						listMap.put("CONT_NM", contReqDetail.getString("CONT_NM"));	//계약명 뽑아오기
//						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//						list.add(listMap); 
//						
//						parameterMap.put("listReceiver", listReceiver);
//						parameterMap.put("list", list);
//						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//							insertSendSms(parameterMap);
//						}
//					}
//				}
			}else if("C032".equals(parameterMap.get("P_CONT_PSCD"))){
				//알림
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
				
				//파라미터로 받아올경우
				parameterMap.put("P_PRE_CONT_PSCD", "C030");	 //작성완료 요청자
//				FwkDataEntity receiverList = iproPrpoContReqDao.selectReceiver(parameterMap);
//				List<FwkDataEntity>contReqDetail = iproPrpoContReqDao.selectContReqDetailList(parameterMap);
				
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				String pReceiverid = "";
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//					listReceiver.add(receiverList);
//					
//					for(int i=0; i<contReqDetail.size(); i++){	// 
//						
//						FwkDataEntity listMap = contReqDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//						list.add(listMap); 
//						
//					} 
//				}
				parameterMap.put("listReceiver", listReceiver);
				parameterMap.put("list", list);
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
			}else{
				if(parameterMap.get("P_CONT_PSCD").equals("C011")){		//경영지원팀 검토반려
					//sms 발송
					//"N" 일 경우 프로퍼티에서 받아오도록
					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
					parameterMap.put("P_SENDERYN","N");
					parameterMap.put("P_RECEIVERYN","Y");
					//파라미터로 받아올경우
					parameterMap.put("P_PRE_CONT_PSCD", "C009");	//경영지원팀검토요청
					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
					
//					FwkDataEntity receiverList = iproContProgrDao.selectReceiver(parameterMap);
//					List<FwkDataEntity>contDetail = iproContProgrDao.selectContDetailList(parameterMap);
					
					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					String pReceiverid = "";
//					if(receiverList != null){
//						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//						listReceiver.add(receiverList);
//		
//						for(int i=0; i<contDetail.size(); i++){	// 
//							
//							FwkDataEntity listMap = contDetail.get(i);
//							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//							list.add(listMap); 
//							
//							
//						} 
//						parameterMap.put("listReceiver", listReceiver);
//						parameterMap.put("list", list);
//						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//							insertSendSms(parameterMap);
//						}
//					}
				}else if(parameterMap.get("P_CONT_PSCD").equals("C014")){		//계약체결요청반려
					//sms 발송
					//"N" 일 경우 프로퍼티에서 받아오도록
					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
					parameterMap.put("P_SENDERYN","N");
					parameterMap.put("P_RECEIVERYN","Y");
					//파라미터로 받아올경우
					parameterMap.put("P_PRE_CONT_PSCD", "C012");	//계약체결요청
					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
					
//					FwkDataEntity receiverList = iproContProgrDao.selectReceiver(parameterMap);
//					List<FwkDataEntity>contDetail = iproContProgrDao.selectContDetailList(parameterMap);
					
					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
					String pReceiverid = "";
//					if(receiverList != null){
//						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//						listReceiver.add(receiverList);
//		
//						for(int i=0; i<contDetail.size(); i++){	// 
//							
//							FwkDataEntity listMap = contDetail.get(i);
//							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//							list.add(listMap); 
//							
//							
//						} 
//						parameterMap.put("listReceiver", listReceiver);
//						parameterMap.put("list", list);
//						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//							insertSendSms(parameterMap);
//						}
//					}
				}	
				
			}
		}else if("bidBfanStatUpdt".equals(gbn)){
			//내부메신저(반려-B005)(공고-B008)
			//sms 발송
			if(parameterMap.get("P_BFAN_PSCD").equals("B005") || parameterMap.get("P_BFAN_PSCD").equals("B008")){
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BFAN_PSCD"));
				//파라미터로 받아올경우
				//List로 받아올게 있을경우 테이블 내용 SELECT 
				//List<FwkDataEntity> bidReqPrcnList = iproPrpoBidReqDao.selectBidReqPrcnList(parameterMap);
				//parameterMap.put("bidReqPrcnList", bidReqPrcnList);
				
//				FwkDataEntity bfanOpenDetail= iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnDetail(parameterMap);

				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				FwkDataEntity listMap = bfanOpenDetail;
//				listMap.put("CONT_NM", listMap.getString("BFAN_NM"));	//계약명 뽑아오기
//				list.add(listMap); 
				parameterMap.put("list", list);
				
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//				FwkDataEntity receiverList = bfanOpenDetail;
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//					
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//					
//					
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
			}else if(parameterMap.get("P_BFAN_PSCD").equals("B012")){
				//반려일때
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BFAN_PSCD"));
				//파라미터로 받아올경우
				//List로 받아올게 있을경우 테이블 내용 SELECT 
				//List<FwkDataEntity> bidReqPrcnList = iproPrpoBidReqDao.selectBidReqPrcnList(parameterMap);
				//parameterMap.put("bidReqPrcnList", bidReqPrcnList);
				
//				FwkDataEntity bfanOpenDetail= iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnDetail(parameterMap);
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				FwkDataEntity listMap = bfanOpenDetail;
//				listMap.put("CONT_NM", listMap.getString("BFAN_NM"));	//계약명 뽑아오기
//				list.add(listMap); 
				parameterMap.put("list", list);
				
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//				FwkDataEntity receiverList = bfanOpenDetail;
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//					
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//					
//					
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
				
			}
		}else if("bidReqRtnStatUpdt".equals(gbn)){
			if("A005".equals(parameterMap.get("P_BID_PSCD")) || "A023".equals(parameterMap.get("P_BID_PSCD"))|| "A016".equals(parameterMap.get("P_BID_PSCD")) || "A027".equals(parameterMap.get("P_BID_PSCD"))){	//공고요청반려, 입찰취소
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BID_PSCD"));
				//파라미터로 받아올경우
//				List<FwkDataEntity> bidReqPrcnListDetail = iproEbidPblancPrcnDao.selectBidReqPrcnListDetail(parameterMap);
				
//				for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//					listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					parameterMap.put("list", list);
//					
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					FwkDataEntity receiverList = bidReqPrcnListDetail.get(i);
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//입찰요청한사람(메시지 받는사람)
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//				} 
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
			}
		}else if("bfStndRqstProgStat".equals(gbn)){
			if("B003".equals(parameterMap.get("P_BFAN_PSCD"))){
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID", "B011");
				//파라미터로 받아올경우
				//List로 받아올게 있을경우 테이블 내용 SELECT 
				//List<FwkDataEntity> bidReqPrcnList = iproPrpoBidReqDao.selectBidReqPrcnList(parameterMap);
				//parameterMap.put("bidReqPrcnList", bidReqPrcnList);
				
//				FwkDataEntity bfanOpenDetail= iproEbidBfStndOpenPrcnDao.selectBfStndOpenPrcnDetail(parameterMap);
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				FwkDataEntity listMap = bfanOpenDetail;
//				listMap.put("CONT_NM", listMap.getString("BFAN_NM"));	//계약명 뽑아오기
//				list.add(listMap); 
				parameterMap.put("list", list);
				
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//				FwkDataEntity receiverList = bfanOpenDetail;
//				if(receiverList != null){
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//					
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//					
//					
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
				
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","N");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BFAN_PSCD"));
				//파라미터로 받아올경우
				//List로 받아올게 있을경우 테이블 내용 SELECT 
				//FwkDataEntity bfanReqDetail= iproPrpoBfStndRqstDao.selectBfStndRqstDetail(parameterMap);
				//List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				//FwkDataEntity listMap = bfanReqDetail;
//				listMap.put("CONT_NM", listMap.getString("BFAN_NM"));	//계약명 뽑아오기
//				list.add(listMap); 
				parameterMap.put("list", list);
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
			}else if("B010".equals(parameterMap.get("P_BFAN_PSCD"))){
				//작성완료일때 부서장에게 알림
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BFAN_PSCD"));
				 
				//파라미터로 받아올경우
//				FwkDataEntity bfanReqDetail= iproPrpoBfStndRqstDao.selectBfStndRqstDetail(parameterMap);
				//부서장일 경우 
//				List<FwkDataEntity> receiverList = iproPrpoBfStndRqstDao.selectBfanApprReceiver(parameterMap);
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				if(receiverList != null){
//					for(int i=0; i<receiverList.size(); i++){	// 
//						FwkDataEntity receiverMap = receiverList.get(i);
//						receiverMap.put("pReceiverid", receiverMap.getString("USR_ID"));
//						listReceiver.add(receiverMap);
//					}
//					
//					FwkDataEntity listMap = bfanReqDetail;
//					listMap.put("CONT_NM", listMap.getString("BFAN_NM"));	//계약명 뽑아오기
//					listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//					listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//					list.add(listMap); 
//						
//					parameterMap.put("listReceiver", listReceiver);
//					parameterMap.put("list", list);
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
			}
		}else if("bidReqProgStat".equals(gbn)){
			
			if("A003".equals(parameterMap.getString("P_BID_PSCD"))){
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID", "A026");
				//파라미터로 받아올경우
//				List<FwkDataEntity> bidReqPrcnListDetail = iproEbidPblancPrcnDao.selectBidReqPrcnListDetail(parameterMap);
				
//				for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//					listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					parameterMap.put("list", list);
//					
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					FwkDataEntity receiverList = bidReqPrcnListDetail.get(i);
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//입찰요청한사람(메시지 받는사람)
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//				} 
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
			}
			
			
			if("A003".equals(parameterMap.getString("P_BID_PSCD"))
					|| "A005".equals(parameterMap.getString("P_BID_PSCD"))
					|| "A015".equals(parameterMap.getString("P_BID_PSCD")) 
					|| "A019".equals(parameterMap.getString("P_BID_PSCD"))
					|| "A021".equals(parameterMap.getString("P_BID_PSCD"))
					|| "A022".equals(parameterMap.getString("P_BID_PSCD"))
					){	

					/**
					 * SMS 발송 시작
						입찰(재)공고요청 A003
						입찰(재)공고요청반려 A005
						기술평가완료 및 개찰결과 등록요청 A015
						개찰결과 등록 완료 및 기술협상결과등록요청 A019
						유찰 재공고진행 요청 A22
						유찰 수의계약진행요청
						낙찰안내 및 계약진행요청 A021
					 */
				
					//sms 발송
					//"N" 일 경우 프로퍼티에서 받아오도록
					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
					parameterMap.put("P_SENDERYN","N"); 
					parameterMap.put("P_RECEIVERYN","N"); 
					/*if("A022".equals(parameterMap.getString("P_BID_PSCD"))){	//유찰일경우
						parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_ARAM_GBN"));
					}else{*/
					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BID_PSCD"));
					/*}*/
					 
					//파라미터로 받아올경우
//					List<FwkDataEntity> bidReqPrcnListDetail = iproPrpoBidReqDao.selectBidReqPrcnListDetail(parameterMap);
					
//					for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//						List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//						FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//						list.add(listMap); 
//						parameterMap.put("list", list);
//						
//						List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//						FwkDataEntity receiverList = bidReqPrcnListDetail.get(i);
//						receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//						listReceiver.add(receiverList); 
//						parameterMap.put("listReceiver", listReceiver);
//					} 
					
					//요청한사람 뽑아내기
					if("A019".equals(parameterMap.getString("P_BID_PSCD")) || "A022".equals(parameterMap.getString("P_BID_PSCD"))){
						parameterMap.put("P_RECEIVERYN","Y"); 
					}
					
					
					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
						insertSendSms(parameterMap);
					}
			}else if("A025".equals(parameterMap.getString("P_BID_PSCD"))){
				//작성완료일때 부서장에게 알림
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N");
				parameterMap.put("P_RECEIVERYN","Y");
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BID_PSCD"));
				 
				//파라미터로 받아올경우
//				List<FwkDataEntity> bidReqPrcnListDetail = iproPrpoBidReqDao.selectBidReqPrcnListDetail(parameterMap);
				//부서장일 경우 
//				List<FwkDataEntity> receiverList = iproPrpoBidReqDao.selectBidApprReceiver(parameterMap);
				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				if(receiverList != null){
//					for(int i=0; i<receiverList.size(); i++){	// 
//						FwkDataEntity receiverMap = receiverList.get(i);
//						receiverMap.put("pReceiverid", receiverMap.getString("USR_ID"));
//						listReceiver.add(receiverMap);
//					}
//					
//					for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//						FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//						listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//						list.add(listMap); 
//						parameterMap.put("list", list);
//					} 
//						
//					parameterMap.put("listReceiver", listReceiver);
//					parameterMap.put("list", list);
//					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//						insertSendSms(parameterMap);
//					}
//				}
			}
		}else if("vendRegist".equals(gbn)){
			if("A019".equals(parameterMap.getString("P_BID_PSCD"))&& "Y".equals(parameterMap.getString("tchnNegoSaveYn"))){   //기술평가 저장했을때
				
				/**
				 * SMS 발송 시작
					입찰(재)공고요청 A003 
					입찰(재)공고요청반려 A005
					기술평가완료 및 개찰결과 등록요청 A015
					개찰결과 등록 완료 및 기술협상결과등록요청 A019
					유찰 재공고진행 요청 A22N
					유찰 수의계약진행요청A22Y
					낙찰안내 및 계약진행요청 A021
				 */
			
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N"); 
				parameterMap.put("P_RECEIVERYN","Y"); 
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BID_PSCD")+"C");
						 
				//파라미터로 받아올경우
//				List<FwkDataEntity> bidReqPrcnListDetail = iproPrpoBidReqDao.selectBidReqPrcnListDetail(parameterMap);
				
//				for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//					listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					parameterMap.put("list", list);
//					
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					FwkDataEntity receiverList = bidReqPrcnListDetail.get(i);
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//				} 
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
				
			}else if("A021".equals(parameterMap.getString("P_BID_PSCD"))){	//낙찰일떄
				/**
				 * SMS 발송 시작
					입찰(재)공고요청 A003 
					입찰(재)공고요청반려 A005
					기술평가완료 및 개찰결과 등록요청 A015
					개찰결과 등록 완료 및 기술협상결과등록요청 A019
					유찰 재공고진행 요청 A22N
					유찰 수의계약진행요청A22Y
					낙찰안내 및 계약진행요청 A021
				 */
			
				//sms 발송
				//"N" 일 경우 프로퍼티에서 받아오도록
				//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
				parameterMap.put("P_SENDERYN","N"); 
				parameterMap.put("P_RECEIVERYN","Y"); 
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_BID_PSCD"));
						 
				//파라미터로 받아올경우
//				List<FwkDataEntity> bidReqPrcnListDetail = iproPrpoBidReqDao.selectBidReqPrcnListDetail(parameterMap);
				
//				for(int i=0; i<bidReqPrcnListDetail.size(); i++){	// 
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					FwkDataEntity listMap = bidReqPrcnListDetail.get(i);
//					listMap.put("CONT_NM", listMap.getString("BID_NM"));	//계약명 뽑아오기
//					list.add(listMap); 
//					parameterMap.put("list", list);
//					
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					FwkDataEntity receiverList = bidReqPrcnListDetail.get(i);
//					receiverList.put("pReceiverid", receiverList.get("REGR_ID"));	//사전규격요청한사람(메시지 받는사람)
//					listReceiver.add(receiverList); 
//					parameterMap.put("listReceiver", listReceiver);
//				} 
				
				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
					insertSendSms(parameterMap);
				}
				
			}
		}else if("contSignRegist".equals(gbn)){
			/**** 계약 전체 서명 여부 ****/
			// 계약업체조회 - 전체
			int signCnt = 0;
			//계약업체만 가져오기
			parameterMap.put("P_ORDR_AGNC_YN", "N");
//			List<FwkDataEntity> cntrcEntrpsList =  oproContDao.selectCntrctEntrpsInqire(parameterMap);
			
//			if(cntrcEntrpsList.size() > 0){
//				for(int i=0; i < cntrcEntrpsList.size(); i++){
//					FwkDataEntity cntrcEntrps = cntrcEntrpsList.get(i);
//					if("Y".equals(cntrcEntrps.get("SIGN_YN"))){
//						signCnt ++;
//					}
//				}
//				
//				// 모든업체가 서명했을 때
//				if(cntrcEntrpsList.size() == signCnt){
//					//sms 발송
//					//"N" 일 경우 프로퍼티에서 받아오도록
//					//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
//					parameterMap.put("P_REGR_ID"		, user.get("USR_ID"));
//					parameterMap.put("P_REGR_NM"		, user.get("USR_NM"));
//					parameterMap.put("P_SENDERYN","N");
//					parameterMap.put("P_RECEIVERYN","Y");
//					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
//					 
//					//파라미터로 받아올경우
//					parameterMap.put("P_PRE_CONT_PSCD", "C021");	//서명요청자
////					FwkDataEntity receiverList = oproContDao.selectReceiver(parameterMap);
////					List<FwkDataEntity>contReqDetail = oproContDao.selectContReqDetailList(parameterMap);
//
//					List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//					List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//					String pReceiverid = "";
////					if(receiverList != null){
////						receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
////						listReceiver.add(receiverList);
////	
////						for(int i=0; i<contReqDetail.size(); i++){	// 
////							
////							FwkDataEntity listMap = contReqDetail.get(i);
////							listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
////							listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
////							listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
////							list.add(listMap); 
////							
////							
////						} 
////						parameterMap.put("listReceiver", listReceiver);
////						parameterMap.put("list", list);
////						if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
////							insertSendSms(parameterMap);
////						}
////					}
//				}
//			}
			
		}else if("cntrcReturnRegist".equals(gbn)){
			/**
			 * SMS 발송 시작
			 */
			if("C008".equals(parameterMap.getString("P_CONT_PSCD"))){	//거래처검토반려
				parameterMap.put("P_PRE_CONT_PSCD", "C006");		//거래처검토요청자
			}else if("C020".equals(parameterMap.getString("P_CONT_PSCD"))){	//거래처검토반려(계약)
				parameterMap.put("P_PRE_CONT_PSCD", "C018");		//거래처검토요청자(계약)
			}else if("C023".equals(parameterMap.get("P_CONT_PSCD"))){	//업체반려
				parameterMap.put("P_PRE_CONT_PSCD", "C021");		//서명요청자
			}
			//sms 발송
			//"N" 일 경우 프로퍼티에서 받아오도록
			//"Y"로 넘길 경우 알림 발송시 지정된 요청자, 접수자를 뽑아서 파라미터에서 받아오도록  
			parameterMap.put("P_SENDERYN","N");
			parameterMap.put("P_RECEIVERYN","Y");
			if("C020".equals(parameterMap.getString("P_CONT_PSCD"))){
				parameterMap.put("P_MSG_OBJ_ID","C008");
			}else{
				parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
			}
			 
			//파라미터로 받아올경우
//			FwkDataEntity receiverList = oproContDao.selectReceiver(parameterMap);
//			List<FwkDataEntity>contReqDetail = oproContDao.selectContReqDetailList(parameterMap);
	
			List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			String pReceiverid = "";
//			if(receiverList != null){
//				receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
//				listReceiver.add(receiverList);
//		
//				for(int i=0; i<contReqDetail.size(); i++){	// 
//					
//					FwkDataEntity listMap = contReqDetail.get(i);
//					listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
//					listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
//					listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
//					list.add(listMap); 
//					
//					
//				} 
//				parameterMap.put("listReceiver", listReceiver);
//				parameterMap.put("list", list);
//				if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
//					insertSendSms(parameterMap);
//				}
//			}
		}else if("contCustApproval".equals(gbn)){
			int apprCnt = 0;
			parameterMap.put("P_ORDR_AGNC_YN", "N");
//			List<FwkDataEntity> cntrcEntrpsList =  oproContDao.selectCntrctEntrpsAll(parameterMap);

//			if(cntrcEntrpsList.size() > 0){
//				for(int i=0; i < cntrcEntrpsList.size(); i++){
//					FwkDataEntity cntrcEntrps = cntrcEntrpsList.get(i);
//					if("Y".equals(cntrcEntrps.get("REVW_YN"))){
//						apprCnt ++;
//					}
//				}
//			}
			
			
//			if(cntrcEntrpsList.size() == apprCnt){
//			
//				if("C007".equals(parameterMap.get("P_CONT_PSCD"))){
//			
//					parameterMap.put("P_CONT_PSCD", "C007");		//거래처검토완료
//				}else{
//			
//					parameterMap.put("P_CONT_PSCD", "C019");		//거래처검토_계약완료
//				}
//				//SMS
//				parameterMap.put("P_SENDERYN","N");
//				parameterMap.put("P_RECEIVERYN","Y");
//				if("C019".equals(parameterMap.get("P_CONT_PSCD"))){
//				
//					parameterMap.put("P_MSG_OBJ_ID", "C007");
//					parameterMap.put("P_PRE_CONT_PSCD", "C018");	//거래처검토_계약요청자
//				}else{
//				
//					parameterMap.put("P_MSG_OBJ_ID",parameterMap.get("P_CONT_PSCD"));
//					parameterMap.put("P_PRE_CONT_PSCD", "C006");	//거래처검토요청자
//				}
//				 
//				//파라미터로 받아올경우
//				
////				FwkDataEntity receiverList = oproContDao.selectReceiver(parameterMap);
//				
////				List<FwkDataEntity>contReqDetail = oproContDao.selectContReqDetailList(parameterMap);
//				
//
//				List<Map<String, Object>> listReceiver = new ArrayList<Map<String,Object>>();
//				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//				String pReceiverid = "";
////				if(receiverList != null){
////					receiverList.put("pReceiverid", receiverList.getString("REGR_ID"));
////					listReceiver.add(receiverList);
////	
////					for(int i=0; i<contReqDetail.size(); i++){	// 
////						FwkDataEntity listMap = contReqDetail.get(i);
////						listMap.put("CONT_NM", listMap.getString("CONT_NM"));	//계약명 뽑아오기
////						listMap.put("pSenderid", parameterMap.get("P_REGR_ID"));
////						listMap.put("pSenderNm",  parameterMap.get("P_REGR_NM"));
////						list.add(listMap); 
////						System.err.println("pSenderNmpSenderNmpSenderNm");
////						
////					} 
////					parameterMap.put("listReceiver", listReceiver);
////					
////					parameterMap.put("list", list);
////					if("true".equals(FwkMessageUtil.getMessage("sms.send"))){
////					
////						insertSendSms(parameterMap);
////					
////					}
////				}
//				
//			}
		}
			
		
	}//commSend 끝
}