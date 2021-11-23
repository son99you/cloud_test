<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>VID 검사</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container" >
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>VID 검사</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading"> 데이터입력 </div>
            <div class="panel-body">
                <form class="form-horizontal" onsubmit="return false;">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >SSN</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="ssn" placeholder="주민등록 번호">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >Cert</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" id="cert" > </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" >Random</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="r" placeholder="랜덤">
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button class="btn btn-primary" onclick="verifyVIDTest();">verifyVID</button>
                            <button class="btn btn-primary" onclick="verifyVID2Test();">verifyVID2</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading"> 검증 결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">검증결과</label>
                        <div class="col-sm-10">
                            <input id="verifyResult" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">인증서정보</label>
                        <div class="col-sm-10">
                            <div id="verifyCertInfo" class="scrollable-data"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
    <%@include file="footer.jsp"%>
</div>
</div>
<script>
    function verify_complete_callback(res) {
        if(res.code == 0) {
            $("#verifyResult").val((res.data.result == true) ? "성공" : "실패");
            $("#verifyCertInfo").append(objectToTable("",res.data.certInfo));
        }
        else {
            alert(res.errorMessage);
        }
    }

    function verifyVIDTest() {
        $("#verifyResult").val("");
        $("#verifyCertInfo").empty();
        var ssn = $("#ssn").val();
        tsHTML5PKI.verifyVID(ssn,{},verify_complete_callback);
    }
    function verifyVID2Test() {
        $("#verifyResult").val("");
        $("#verifyCertInfo").empty();
        var ssn = $("#ssn").val();
        var cert = $("#cert").val();
        var r = $("#r").val();
        tsHTML5PKI.verifyVID2(cert,ssn,r,{},verify_complete_callback);
    }
	
	//초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init(true);
    });

</script>


</body>
</html>
