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
<!-- <meta name="title" content="�ѱ����������������">
<meta name="description" content="�ѱ����������������">
<meta name="keywords" content="�ѱ����������������"> -->
<script src="../commons/js/kier_design/jquery-1.12.2.min.js"></script>
<script type="text/javascript">
$(function() {
	var url = location.href;
	var confmKey = "U01TX0FVVEgyMDE4MDIyNjEwNTExNTEwNzY5MzU=";
	var resultType = "4"; // ���θ��ּ� �˻���� ȭ�� ��³���, 1 : ���θ�, 2 : ���θ�+����, 3 : ���θ�+�󼼰ǹ���, 4 : ���θ�+����+�󼼰ǹ���
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
		
		<!-- ���θ��ּ� ���� input -->
		<input type="hidden" id="inputYn" name="inputYn" value="${inputYn}"/><!--���θ��ּ� ��ü(����)-->
		<input type="hidden" id="roadFullAddr" name="roadFullAddr" value="${roadFullAddr}"/><!--���θ��ּ� ��ü(����)-->
		<input type="hidden" id="roadAddrPart1" name="roadAddrPart1" value="${roadAddrPart1}"/><!--���θ��ּ�-->
		<input type="hidden" id="addrDetail" name="addrDetail" value="${addrDetail}"/><!--���Է� ���ּ�-->
		<input type="hidden" id="roadAddrPart2" name="roadAddrPart2" value="${roadAddrPart2}"/><!--�����ּ�-->
		<input type="hidden" id="engAddr" name="engAddr" value="${engAddr}"/><!--���� ���θ��ּ�-->
		<input type="hidden" id="jibunAddr" name="jibunAddr" value="${jibunAddr}"/><!--����-->
		<input type="hidden" id="zipNo" name="zipNo" value="${zipNo}"/><!--�����ȣ-->
		<input type="hidden" id="admCd" name="admCd" value="${admCd}"/><!--���������ڵ�-->
		<input type="hidden" id="rnMgtSn" name="rnMgtSn" value="${rnMgtSn}"/><!--���θ��ڵ�-->
		<input type="hidden" id="bdMgtSn" name="bdMgtSn" value="${bdMgtSn}"/><!--�ǹ�������ȣ-->
		<input type="hidden" id="detBdNmList" name="detBdNmList" value="${detBdNmList}"/><!--�󼼹�����-->
		<input type="hidden" id="bdNm" name="bdNm" value="${bdNm}"/><!--�ǹ���-->
		<input type="hidden" id="bdKdcd" name="bdKdcd" value="${bdKdcd}"/><!--�������ÿ���-->
		<input type="hidden" id="siNm" name="siNm" value="${siNm}"/><!--�õ���-->
		<input type="hidden" id="sggNm" name="sggNm" value="${sggNm}"/><!--�ñ�����-->
		<input type="hidden" id="emdNm" name="emdNm" value="${emdNm}"/><!--���鵿��-->
		<input type="hidden" id="liNm" name="liNm" value="${liNm}"/><!--��������-->
		<input type="hidden" id="rn" name="rn" value="${rn}"/><!--���θ�-->
		<input type="hidden" id="udrtYn" name="udrtYn" value="${udrtYn}"/><!--���Ͽ���-->
		<input type="hidden" id="buldMnnm" name="buldMnnm" value="${buldMnnm}"/><!--�ǹ�����-->
		<input type="hidden" id="buldSlno" name="buldSlno" value="${buldSlno}"/><!--�ǹ��ι�-->
		<input type="hidden" id="mtYn" name="mtYn" value="${mtYn}"/><!--�꿩��-->
		<input type="hidden" id="lnbrMnnm" name="lnbrMnnm" value="${lnbrMnnm}"/><!--��������(����)-->
		<input type="hidden" id="lnbrSlno" name="lnbrSlno" value="${lnbrSlno}"/><!--�����ι�(ȣ)-->
		<input type="hidden" id="emdNo" name="emdNo" value="${emdNo}"/><!--���鵿�Ϸù�ȣ-->
				
		<!-- <input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/> -->
	</form>
</body>
</html>