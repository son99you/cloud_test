<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.yettiesoft.vestsign.external.*" %>
<%@ page import="com.yettiesoft.vestsign.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="UTF-8">
  <title>VestSign Sample</title>
</head>

<body>
	<form method='post' name='signVid' id="signVid" autocomplete="off" action='./signVerify.jsp' >
		
		<input type='text' name='idn' id="idn" value="">
		<input type="hidden" name="signedMsg" id="signedMsg" value="">
		<input type="hidden" name="vidMsg" id="vidMsg" value="">
		<input type="hidden" name="getRealName" id="getRealName" value="">
		<button type="button" id="signBtnVid" onClick="do_sign()">signature</button>
	</form>
	
<script type="text/javascript" src="/vestsign/vestsign.js"></script>
<script type="text/javascript" src="/vestsign/library/json3.min.js"></script>
 
<script>
  <%
      VidVerifier vid = new VidVerifier();  
      out.println(vid.writeServerCertScript("s"));		// 식별번호 암호화를 위한 서버 인증서
  %>
  	
  // 인증서 허용 리스트
  function showCertType() {
	    var type;
	    var temp = document.getElementsByName( 'certType' );

	    for (var i = 0, ii = temp.length; i < ii; ++i) {
	        if (temp[i].checked)  type = temp[i].value;
	      }
	/*    
		if(type == 'personal'){  	// 개인
			certOID = '1.2.410.200004.5.2.1.2;1.2.410.200004.5.1.1.5;1.2.410.200005.1.1.1;1.2.410.200004.5.4.1.1;1.2.410.200012.1.1.1;1.2.410.200004.5.2.1.7.1;1.2.410.200004.5.2.1.7.2;1.2.410.200004.5.2.1.7.3;1.2.410.200004.5.1.1.9;1.2.410.200005.1.1.4;1.2.410.200005.1.1.6.2;1.2.410.200004.5.4.1.101;1.2.410.200004.5.4.1.102;1.2.410.200004.5.4.1.103;1.2.410.200004.5.4.1.104;1.2.410.200004.5.4.1.105;1.2.410.200004.5.4.1.10;1.2.410.200004.5.4.1.107;1.2.410.200004.5.4.1.108;1.2.410.200004.5.4.1.109;1.2.410.200012.11.31;1.2.410.200012.11.35;1.2.410.200012.11.39;1.2.410.200012.11.43;';
			return certOID; 
		}else{         				//법인
			certOID = '1.2.410.200004.5.2.1.1;1.2.410.200004.5.1.1.7;1.2.410.200005.1.1.5;1.2.410.200004.5.4.1.2;1.2.410.200012.1.1.3;';
			return certOID;
		}
		
		*/
	  };
  
		  function langType() {
			    var type;
			    var temp = document.getElementsByName( 'language' );

			    for (var i = 0, ii = temp.length; i < ii; ++i) {
			        if (temp[i].checked)  type = temp[i].value;
			      }
			    
				if(type == 'kor'){			// 한국어
					return 0; 
				}else if(type == 'eng'){	// 영어
					return 1;
				}else if(type == 'jap'){	// 일본어
					return 2;
				}else{
					return 3;				//중국어
				}
			};

	var callbackVid = function (result) {
		signVid.signedMsg.value = result.signature;			// 전자서명문
		signVid.vidMsg.value = result.vidMessage;			// 식별번호 검증 메시지
		signVid.getRealName.value = result.extentions.realName;		// 서명자 실명(DN값에서 추출하는 것 아님)
		
		if(signVid.signedMsg.value == "" || signVid.vidMsg.value == "" ){
			alert("전자서명문 또는 식별번호 검증 메시지가 올바르지 않습니다.");
			return ;
		}
		document.signVid.submit();
	};


	var errorcallback = function (error) {
		console.error(error);
	};

	function do_sign() {
		/* 필수 설정 */
		var option = {
				encoding: 'Hex',
				charset: 'utf-8' 
			};

		var config = {
			certificateClass: '0',	// 0 – 없음,  1 – 개인 범용,  16 – 법인 범용,  256 – 증권 전용 개인
//			OID: '0'				// ex) '0' / '1.2.410.200004.5.1.1.5'/'1.2.410.200004.5.1.1.5;1.2.410.200004.5.1.1.7;1.2.410.200004.5.1.1.9'
		};
	
		var vid =  {
//				idn: '',
	          	recipientCertificate: s 
		};
			
	        
		option.vid = vid;
//		config.OID = showCertType();
		config.langIndex = langType();
			
		yettie.init(config);
				
		plain = "test 1234";
		yettie.sign(plain, option, callbackVid, errorcallback);
	}
  
 </script>

</body>
</html>
