﻿<%@ WebHandler Language="C#" Class="raonkhandler" %>

using System;
using System.Web;
using Com.Raonwiz.KUpload;
using Com.Raonwiz.KUpload.Common;
using Com.Raonwiz.KUpload.Util;

public class raonkhandler : IHttpHandler
{
    RAONKHandler upload = null;

    public void ProcessRequest (HttpContext context) {

        upload = new RAONKHandler();

        char cPathChar = StaticVariables.cPathChar;
        string strRootFolder = context.Request.PhysicalPath;
        strRootFolder = strRootFolder.Substring(0, context.Request.PhysicalPath.LastIndexOf(cPathChar));
        strRootFolder = strRootFolder.Substring(0, strRootFolder.LastIndexOf(cPathChar));
        
        //디버깅
        //2번째 파라미터의 Log Mode 설명
        //- C : 일반로그 출력, 3번째 파라미터에 로그파일 경로 설정
        //- L : Log4Net 모듈에 의한 로그 출력, 3번째 파라미터에 Log4Net.xml파일의 경로 설정 (기본적으로 배포되는 /handler/NET의 물리적 경로 설정)
        //upload.settingVo.SetDebugMode(true, "C", @"D:\log\");
        //upload.settingVo.SetDebugMode(true, "L", System.IO.Path.Combine(strRootFolder, "handler" + cPathChar + "NET" + cPathChar));

        ///////////////////////////////
        //    이벤트를 등록 처리     //
        ///////////////////////////////
        //업로드 전 이벤트 처리기 등록
        //upload.BeforeUploadEvent += new BeforeUploadEventDelegate(BeforeUploadEvent);

        //업로드 후 이벤트 처리기 등록
        //upload.AfterUploadEvent += new AfterUploadEventDelegate(AfterUploadEvent);

        //다운로드 전 이벤트 처리기 등록
        upload.BeforeDownloadEvent += new BeforeDownloadEventDelegate(BeforeDownloadEvent); // Custom Download를 위하여 활성화

        //다운로드 파일을 서버에서 구해지는 Stream 다운로드로 처리될 경우 설정할 이벤트 처리기 등록
        //upload.ExecuteDownloadEvent += new ExecuteDownloadEventDelegate(ExecuteDownloadEvent);
        
        //다운로드 후 이벤트 처리기 등록
        //upload.AfterDownloadEvent += new AfterDownloadEventDelegate(AfterDownloadEvent);

        ///////////////////////////////
        //       서버모듈 설정       //
        ///////////////////////////////

        //실제 업로드 할 기본경로 설정 (가상경로와 물리적 경로로 설정 가능)
        //폴더명 제일 뒤에 .과 공백이 있다면 제거하시고 설정해 주세요.(운영체제에서 지원되지 않는 문자열입니다.)

        //-------------------- [설정방법1] 물리적 경로 설정 시작 --------------------//
        /*
        //해당 설정은 PhysicalPath를 RAONK Upload 제품폴더\raonkuploaddata\ 를 저장 Root 경로로 설정하는 내용입니다.
        upload.settingVo.SetPhysicalPath(System.IO.Path.Combine(strRootFolder, "raonkuploaddata"));

        //임시파일 물리적 경로설정 ( SetPhysicalPath에 설정된 경로 + raonktemp )
        upload.settingVo.SetTempPath(System.IO.Path.Combine(strRootFolder, "raonkuploaddata") + System.IO.Path.DirectorySeparatorChar + "raonktemp");
        */
        //-------------------- [설정방법1] 물리적 경로 설정 끝 --------------------//

        //-------------------- [설정방법2] 가상경로 설정 시작 --------------------//
        upload.settingVo.SetVirtualPath("/raonkuploaddata");

        //임시파일 물리적 경로설정 ( SetVirtualPath에 설정된 경로 + raonktemp )
        upload.settingVo.SetTempPath(context.Request.MapPath("/raonkuploaddata") + System.IO.Path.DirectorySeparatorChar + "raonktemp");
        //-------------------- [설정방법2] 가상경로 설정 끝 --------------------//

        //위에 설정된 임시파일 물리적 경로에 불필요한 파일을 삭제 처리하는 설정 (단위: 일)
        upload.settingVo.SetGarbageCleanDay(2);

        //upload.settingVo.SetNetworkCredentials("ID", "Password", @"\\Domain");
        
        //환경설정파일 물리적 폴더 (서버 환경설정 파일을 사용할 경우)
        //upload.settingVo.SetConfigPhysicalPath(@"D:\raonkuploaddata\config");

        // 실제 실행부
        upload.Process(context);
        
        // 메모리 해제
        upload.Dispose();
        
    }

    private void BeforeUploadEvent(EventVo eventVo)
    {
        //파일을 업로드하기 전에 발생하는 이벤트 입니다.
        //파일에 대한 저장 경로를 변경해야 하는 경우 사용합니다.
        
        //HttpContext context = eventVo.GetContext(); //Context Value
        //string strNewFileLocation = eventVo.GetNewFileLocation(); //NewFileLocation Value
        //string strCustomValue = eventVo.GetCustomValue(); //CustomValue
        //string strFileIndex = eventVo.GetFileIndex(); //FileIndex Value - 마지막 파일은 index 뒤에 z가 붙습니다.

        //string[] aryParameterValue = upload.GetParameterValue("ParameterName"); //클라이언트에서 AddFormData를 이용하여 추가된 파라미터를 얻습니다.
        
        //eventVo.SetNewFileLocation(strNewFileLocation); //Change NewFileLocation Value

        //eventVo.SetCustomError("사용자오류");
        //eventVo.SetCustomError("999", "사용자오류"); /* Error Code를 설정하실 때에는 900이상의 3자리로 설정 */
    }

    private void AfterUploadEvent(EventVo eventVo)
    {
        //파일을 업로드한 후에 발생하는 이벤트 입니다.
        //업로드된 파일을 변경하는 경우 사용됩니다.(DRM처리, Image API 처리)
        //경로가 변경된 경우 Response되는 파일경로도 변경해야 합니다.(ResponseFileServerPath)
        
        //HttpContext context = eventVo.GetContext(); //Context Value
        //string strNewFileLocation = eventVo.GetNewFileLocation(); //NewFileLocation Value
        //string strResponseFileServerPath = eventVo.GetResponseFileServerPath(); //ResponseFileServerPath Value
        //string strCustomValue = eventVo.GetCustomValue(); //CustomValue
        //string strFileIndex = eventVo.GetFileIndex(); //FileIndex Value - 마지막 파일은 index 뒤에 z가 붙습니다.

        //string[] aryParameterValue = upload.GetParameterValue("ParameterName"); //클라이언트에서 AddFormData를 이용하여 추가된 파라미터를 얻습니다.

        //eventVo.SetResponseFileServerPath(strResponseFileServerPath); //Change ResponseFileServerPath Value
        //eventVo.SetResponseCustomValue("ResponseCustomValue"); //Set ResponseCustomValue
        
        //eventVo.SetCustomError("사용자오류");
        //eventVo.SetCustomError("999", "사용자오류"); /* Error Code를 설정하실 때에는 900이상의 3자리로 설정 */

        //이미지 처리 관련 API
        try
        {
            //long lJpegQuality = 100; // JPG 품질 (1 ~ 100)

            //string strTempFilePath = string.Empty;
            //string strSourceFileFullPath = strNewFileLocation;

            //동일 폴더에 이미지 썸네일 생성하기
            //strTempFilePath = ImageApi.MakeThumbnail(strSourceFileFullPath, "", 200, 0, true, lJpegQuality);

            //특정위치에 이미지 썸네일 생성하기
            //string strTargetFileFullPath = @"c:\temp\test_thumb.jpg";
            //strTempFilePath = ImageApi.MakeThumbnailEX(strSourceFileFullPath, strTargetFileFullPath, 200, 0, false, lJpegQuality);

            //이미지 포멧 변경
            //strTempFilePath = ImageApi.ConvertImageFormat(strSourceFileFullPath, "", ImageFormatList.png, false, false, lJpegQuality);

            //이미지 크기 변환
            //ImageApi.ConvertImageSize(strSourceFileFullPath, 500, 30, lJpegQuality);

            //비율로 이미지 크기 변환
            //ImageApi.ConvertImageSizeByPercent(strSourceFileFullPath, 50, lJpegQuality);

            //이미지 회전
            //ImageApi.Rotate(strSourceFileFullPath, 90, lJpegQuality);

            //이미지 워터마크
            //string strWaterMarkFilePath = @"c:\temp\raonk_logo.png";
            //ImageApi.SetImageWaterMark(strSourceFileFullPath, strWaterMarkFilePath, "TOP", 10, "RIGHT", 10, 0, lJpegQuality);

            //텍스트 워터마크
            //TextWaterMarkVo txtWaterMarkVo = new TextWaterMarkVo("RAONK Upload", "굴림", 12, "#FF00FF");
            //ImageApi.SetTextWaterMark(strSourceFileFullPath, txtWaterMarkVo, "TOP", 10, "CENTER", 10, 0, 0, lJpegQuality);

            //이미지 크기
            //System.Drawing.Size size = ImageApi.GetImageSize(strSourceFileFullPath);
            //int _width = size.Width;
            //int _height = size.Height;

            //EXIF 추출 (Exif standard 2.2, JEITA CP-2451)
            //Com.Raonwiz.KUpload.Util.Exif.ExifEntity _exif = ImageApi.GetExifEntityData(strSourceFileFullPath);  
        }
        catch (Exception ex)
        {
            string errorMsg = ex.Message;
        }
    }

    private void BeforeDownloadEvent(EventVo eventVo)
    {
        //Download Custom Value를 이용하여 물리적 경로를 구해서 경로 설정해주는 Sample 입니다.//
        
        HttpContext context = eventVo.GetContext();
        string[] aryDownloadFilePath = eventVo.GetDownloadFilePath();
        string[] aryDownloadCustomValue = eventVo.GetDownloadCustomValue();
        
        char cPathChar = StaticVariables.cPathChar;
        string strTempPath = context.Request.PhysicalPath.Substring(0, context.Request.PhysicalPath.LastIndexOf(cPathChar));
        strTempPath = strTempPath.Substring(0, strTempPath.LastIndexOf(cPathChar));
        
        int iCustomValueLength = aryDownloadCustomValue.Length;
        for (int i = 0; i < iCustomValueLength; i++)
        {
            // 해당 값을 이용하여 파일의 물리적 경로 생성 (실제 물리적 파일 경로를 설정해주세요.)
            switch (aryDownloadCustomValue[i])
            {
                case "CustomValue1":
                    aryDownloadFilePath[i] = System.IO.Path.Combine(strTempPath, @"images" + cPathChar + "Panorama" + cPathChar + "ViewPhotos.jpg");
                    break;
                case "CustomValue2":
                    aryDownloadFilePath[i] = System.IO.Path.Combine(strTempPath, @"images" + cPathChar + "Scenery" + cPathChar + "image" + cPathChar + "CreativeImages.bmp");
                    break;
            }
        }

        eventVo.SetDownloadFilePath(aryDownloadFilePath);

        //Download Custom Value를 이용하여 물리적 경로를 구해서 경로 설정해주는 Sample 입니다.//
        
        //파일을 다운로드하기 전에 발생하는 이벤트 입니다.
        //파일에 대한 다운로드 경로를 변경하거나 서버에서 구해지는 Stream 다운로드로 처리할 경우 사용합니다.

        //HttpContext context = eventVo.GetContext(); //Context Value
        //string[] aryDownloadFilePath = eventVo.GetDownloadFilePath(); //DownloadFilePath Value
        //string[] aryDownloadCustomValue = eventVo.GetDownloadCustomValue();  //DownloadCustomValue

        //eventVo.SetDownloadFilePath(aryDownloadFilePath); //Change DownloadFilePath Value

        //eventVo.SetCustomError("사용자오류");
        //eventVo.SetCustomError("999", "사용자오류"); /* Error Code를 설정하실 때에는 900이상의 3자리로 설정 */
        
        ////////////////////////////////////////////////////////////////////////////////////
        //다운로드 파일을 서버에서 구해지는 Stream 다운로드로 처리할 경우 파일 Stream 설정//
        ////////////////////////////////////////////////////////////////////////////////////

        //System.IO.Stream[] aryDownloadFileStream; //Download File Stream 설정
        //eventVo.SetDownloadFileStream(aryDownloadFileStream); //Setting DownloadFileStream Value
    }

    private void ExecuteDownloadEvent(EventVo eventVo)
    {
        //다운로드 파일을 서버에서 구해지는 Stream 다운로드로 처리할 경우 Stream을 설정할 이벤트 입니다.

        //HttpContext context = eventVo.GetContext(); //Context Value
        //string[] aryDownloadFilePath = eventVo.GetDownloadFilePath(); //DownloadFilePath Value
        //string[] aryDownloadCustomValue = eventVo.GetDownloadCustomValue();  //DownloadCustomValue

        //System.IO.Stream[] aryDownloadFileStream; //Download File Stream 설정
        //eventVo.SetDownloadFileStream(aryDownloadFileStream); //Setting DownloadFileStream Value
    }

    private void AfterDownloadEvent(EventVo eventVo)
    {
        //파일을 다운로드한 후에 발생하는 이벤트 입니다.
        //다운로드 받을 파일에 대한 정보를 얻은 후 해당 정보에 대하여 로그출력을 하려는 경우 사용합니다.

        //HttpContext context = eventVo.GetContext(); //Context Value
        //string[] aryDownloadFilePath = eventVo.GetDownloadFilePath(); //DownloadFilePath Value
        //string[] aryDownloadCustomValue = eventVo.GetDownloadCustomValue();  //DownloadCustomValue
    }

    public bool IsReusable
    {
        get
        {
            return false;
        }
    }

}