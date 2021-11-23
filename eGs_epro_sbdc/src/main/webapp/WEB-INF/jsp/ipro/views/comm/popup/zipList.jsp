<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta name="title" content="한국에너지기술연구원">
<meta name="description" content="한국에너지기술연구원">
<meta name="keywords" content="한국에너지기술연구원"> -->
<script src="../commons/js/kier_design/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
$(function() {
	var url = location.href;
	var confmKey = "U01TX0FVVEgyMDE4MDIyNjEwNTExNTEwNzY5MzU=";
	var resultType = "4"; // 도로명주소 검색결과 화면 출력내용, 1 : 도로명, 2 : 도로명+지번, 3 : 도로명+상세건물명, 4 : 도로명+지번+상세건물명
	var inputYn= $('input[name=inputYn]').val();
	if(inputYn != "Y"){
		$('input[name=confmKey]').val(confmKey);
		$('input[name=returnUrl]').val(url);
		$('input[name=resultType]').val(resultType);
		$('form').attr({'action':'http://www.juso.go.kr/addrlink/addrLinkUrl.do'});
		$('form').submit();
	}else {
		window.opener.jusoCallBack($('input[name=roadFullAddr]').val(),$('input[name=roadAddrPart1]').val(),$('input[name=addrDetail]').val(),$('input[name=roadAddrPart2]').val(),
			$('input[name=engAddr]').val(),$('input[name=jibunAddr]').val(),$('input[name=zipNo]').val(),$('input[name=admCd]').val(),$('input[name=rnMgtSn]').val(),
			$('input[name=bdMgtSn]').val(),$('input[name=detBdNmList]').val(),$('input[name=bdNm]').val(),$('input[name=bdKdcd]').val(),
			$('input[name=siNm]').val(),$('input[name=sggNm]').val(),$('input[name=emdNm]').val(),$('input[name=liNm]').val(),
			$('input[name=rn]').val(),$('input[name=udrtYn]').val(),$('input[name=buldMnnm]').val(),$('input[name=buldSlno]').val(),
			$('input[name=mtYn]').val(),$('input[name=lnbrMnnm]').val(),$('input[name=lnbrSlno]').val(),$('input[name=emdNo]').val());
		window.close();
	}
});
</script>
</head>
<body>
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
		
		<!-- 도로명주소 관련 input -->
		<input type="hidden" id="inputYn" name="inputYn" value="${inputYn}"/><!--도로명주소 전체(포멧)-->
		<input type="hidden" id="roadFullAddr" name="roadFullAddr" value="${roadFullAddr}"/><!--도로명주소 전체(포멧)-->
		<input type="hidden" id="roadAddrPart1" name="roadAddrPart1" value="${roadAddrPart1}"/><!--도로명주소-->
		<input type="hidden" id="addrDetail" name="addrDetail" value="${addrDetail}"/><!--고객입력 상세주소-->
		<input type="hidden" id="roadAddrPart2" name="roadAddrPart2" value="${roadAddrPart2}"/><!--참고주소-->
		<input type="hidden" id="engAddr" name="engAddr" value="${engAddr}"/><!--영문 도로명주소-->
		<input type="hidden" id="jibunAddr" name="jibunAddr" value="${jibunAddr}"/><!--지번-->
		<input type="hidden" id="zipNo" name="zipNo" value="${zipNo}"/><!--우편번호-->
		<input type="hidden" id="admCd" name="admCd" value="${admCd}"/><!--행정구역코드-->
		<input type="hidden" id="rnMgtSn" name="rnMgtSn" value="${rnMgtSn}"/><!--도로명코드-->
		<input type="hidden" id="bdMgtSn" name="bdMgtSn" value="${bdMgtSn}"/><!--건물관리번호-->
		<input type="hidden" id="detBdNmList" name="detBdNmList" value="${detBdNmList}"/><!--상세번물명-->
		<input type="hidden" id="bdNm" name="bdNm" value="${bdNm}"/><!--건물명-->
		<input type="hidden" id="bdKdcd" name="bdKdcd" value="${bdKdcd}"/><!--공동주택여부-->
		<input type="hidden" id="siNm" name="siNm" value="${siNm}"/><!--시도명-->
		<input type="hidden" id="sggNm" name="sggNm" value="${sggNm}"/><!--시군구명-->
		<input type="hidden" id="emdNm" name="emdNm" value="${emdNm}"/><!--읍면동명-->
		<input type="hidden" id="liNm" name="liNm" value="${liNm}"/><!--법정리명-->
		<input type="hidden" id="rn" name="rn" value="${rn}"/><!--도로명-->
		<input type="hidden" id="udrtYn" name="udrtYn" value="${udrtYn}"/><!--지하여부-->
		<input type="hidden" id="buldMnnm" name="buldMnnm" value="${buldMnnm}"/><!--건물본번-->
		<input type="hidden" id="buldSlno" name="buldSlno" value="${buldSlno}"/><!--건물부번-->
		<input type="hidden" id="mtYn" name="mtYn" value="${mtYn}"/><!--산여부-->
		<input type="hidden" id="lnbrMnnm" name="lnbrMnnm" value="${lnbrMnnm}"/><!--지번본번(번지)-->
		<input type="hidden" id="lnbrSlno" name="lnbrSlno" value="${lnbrSlno}"/><!--지번부번(호)-->
		<input type="hidden" id="emdNo" name="emdNo" value="${emdNo}"/><!--읍면동일련번호-->
				
		<!-- <input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/> -->
	</form>
</body>
</html>