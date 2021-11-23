// 웹큐브 Non-ActiveX, No-Plugin 버전
var WebCubeAgentVersion = '1.1.7.8';
// 웹큐브 ActiveX 버전
var WebCubeVersion = '4,2,2,2';
// 웹큐브 설치후 접속할 페이지를 설정합니다.
//var WebCube_NextPage = "./WebCube/Customer.htm";
var WebCube_NextPage = "/opro/main/emgncLoginForm.do";

function InputPolicyInfo(){

	// 웹큐브 정책을 설정합니다.
	var arrSetProtect = new Array();
	arrSetProtect[0] = "G";		// CheckSum ("G"값 고정)
	arrSetProtect[1] = "1";		// VmWare, Terminal 기능 차단(0) / 허용(1)
	arrSetProtect[2] = "0";		// Print 기능 차단(0) / 허용(1)
	arrSetProtect[3] = "0";		// SaveAs 기능 차단(0) / 허용(1)
	arrSetProtect[4] = "0";		// Mouse 기능 차단(0) / 허용(1)
	arrSetProtect[5] = "0";		// ScreenCapture 기능 차단(0) / 허용(1)
	arrSetProtect[6] = "0";		// SourceView 기능 차단(0) / 허용(1)
	arrSetProtect[7] = "0";		// Word Editor 기능 차단(0) / 허용(1)
	arrSetProtect[8] = "0";		// Mail로 보내기 기능 차단(0) / 허용(1)
	arrSetProtect[9] = "1";		// ClipBoard(Ctrl+C)기능 차단(0) / 허용 (1)
	arrSetProtect[10] = "1";	// ClipBoard(Ctrl+V) 기능 차단(0) / 허용 (1)	

	// CmdMethod필요 시
	WebCubeObj.CmdMethod(999,"0",0,"0",0,0,"0");	//CmdMethod 기본값
	//WebCubeObj.CmdMethod(1018,"0",0,"0",0,0,"0");  //개발자도구 열기	
	WebCubeObj.CmdMethod(1050,"piece",5,"0",0,0,"0");  //부분캡쳐
		
	//SetPolicy 필요시 작성 TPMSAVWLcv+
	//WebCubeObj.SetPolicy("#basic#,ta:TMcv-");

	//SetProtect를 호출하면 문자열을 모두 합침(SetProtect를 마지막에 써야 함)
	WebCubeObj.SetProtect(arrSetProtect.join(""));
}

// 설치파일 경로('/'까지 적어야 함)
var WebCube_SetupFilePath = "/WebCube/components/";
// 이미지 파일 경로('/'까지 적어야 함)
var WebCube_ImageFilePath = "/WebCube/img/";
// Setup UI 선택 (0, 1, 2, 3)
var WebCube_SetupUI = 3;
//설치 확인 후 화면에 띄울 div id(사용 안할 시 주석처리)
//var WebCube_DivID = "div_ShowPage";
//설치 확인 후 실행할 함수명(default.htm 에서 함수 추가해야 함) //	ex) fName = fn_test;
//var WebCube_StartFunctionName = fn_test;

//////WebCube 사용 모드 입력///////////////////////////////////////////////////////////////////////////////
var WebCube_MODE_IE = "N";	//A : ActiveX, N : Non-ActiveX, X : IE 사용 안함
var WebCube_MODE_M = "N";	//N : No-Plugin, X : 멀티브라우저 사용 안함
var WebCube_MODE_MAC = "X";	//M : MAC OS, X : NOT MAC OS, P : MAC OS by pass;
var WebCube_MODE_MOBILE = "X";	//X : NOT MOBILE, P : MOBILE by pass;
var WebCube_MacOS = "/WebCube/MAC.htm";	//MAC용 WEBCUBE 호출 페이지로 리다이렉션
var WebCube_UseAjaxCommand = true;	// Ajax 사용 여부(true : Ajax 방식으로 정책 호출, false : URI 스키마 방식으로 정책 호출)

//////관리자 페이지 사용 시 셋팅///////////////////////////////////////////////////////////////////////////////
//관리자 페이지 사용 여부
var WebCube_UseAdminPage = false;
//사용자 정보 셋팅
var WebCube_AdminPageObj = {};
//관리자 서버 URL 입력
WebCube_AdminPageObj.AdminPageUrl = "";
// module code 는 WC(WebCube) 로 고정
WebCube_AdminPageObj.module = "WC";	

//관리자 페이지 연동 실패 시 기본정책 적용 여부
// InputPolicyInfo() 함수의 arrSetProtect 변수의 값을 기본정책으로 설정함
var WebCube_UseDefaultPolicy = false;
// 기본정책을 적용할 경우 알림 메시지 출력 여부
var WebCube_ShowDefaultPolicyNotify = false;
// 기본정책을 적용할 경우 알림 메시지
var WebCube_DefaultPolicyNotifyMessage = "";
// 기본정책을 적용할 경우 알림 메시지 표시 시간
var WebCube_DefaultPolicyNotifyMessageShowTime = 15000;
// 기본정책을 적용할 경우 알림 메시지 가로 크기
var WebCube_DefaultPolicyNotifyMessageWidth = 650;
// 기본정책을 적용할 경우 알림 메시지 세로 크기
var WebCube_DefaultPolicyNotifyMessageHeight = 90;
//관리자 페이지 연동 시 파라메터 암호화 여부
var WebCube_EncryptParam = false; //id, domain, company 암호화 여부 (true : 암호화 함, false : 암호화 안함)
// 관리자 서버 타임아웃 시간 설정
var WebCube_ServerWaitTime = 5000;
///////////////////////////////////////////////////////////////////////////////////////////////////////