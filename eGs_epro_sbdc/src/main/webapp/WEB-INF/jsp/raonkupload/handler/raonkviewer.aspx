<%@ Page Language="C#" %>
<%
    using (Com.Raonwiz.KUpload.RAONKHandler upload = new Com.Raonwiz.KUpload.RAONKHandler())
    {
        //디버깅
        //2번째 파라미터의 Log Mode 설명
        //- C : 일반로그 출력, 3번째 파라미터 로그파일 경로 설정
        //- L : Log4Net 모듈에 의한 로그 출력, 3번째 파라미터 로그파일에 Log4Net.xml파일의 경로 설정 (기본적으로 배포되는 /handler/NET의 물리적 경로 설정)
        //upload.settingVo.SetDebugMode(true, "C", @"로그파일 경로");
        //upload.settingVo.SetDebugMode(true, "L", System.IO.Path.Combine(strRootFolder, "handler" + cPathChar + "NET" + cPathChar);
        
        upload.Viewer(this.Context);
    }
%>