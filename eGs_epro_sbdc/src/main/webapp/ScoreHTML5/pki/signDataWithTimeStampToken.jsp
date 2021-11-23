<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>데이터 서명/검증</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>데이터 서명/검증 + TimeToken</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">서명 데이터</div>
            <div class="panel-body">
                <form class="form-horizontal" onsubmit="return false;">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="ssn">데이터</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="inputData" rows="3" >테스트용 서명 데이터 입니다.</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="ssn">신원확인 정보</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="ssn" placeholder="인증서 신원확인 정보">
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="signDataWithTimeStampTokenTest();" class="btn btn-primary">데이터 서명</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="signedData">결과</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="signedData" rows="5" ></textarea>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <form class="form-inline" onsubmit="return false;">
                                <button onclick="verifySignedDataTest();" class="btn btn-primary">서명 데이터 검증.</button>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="certValidation"/>결과에 인증서 검증 포함
                                    </label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명 데이터 검증결과.</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">서명 원문</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="verifyResult" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">서명 정보</label>
                        <div class="col-sm-10">
                            <div id="signerInfo" ></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <hr>
        <%@include file="footer.jsp" %>

    </div>
</div>
</div>



<script>
    function verify_complete_callback(res) {
        if (res.code == 0) {
            $("#verifyResult").val(res.data.content);
            for(var i=0;i<res.data.signerInfo.length;i++) {
                var caption = (i+1) + '번째 서명정보';
                $("#signerInfo").append(objectToTable(caption,res.data.signerInfo[i]));
            }
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }
    function sign_complete_callback(res) {
        if (res.code == 0) {
            $("#signedData").val(res.data.signedData);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function signDataWithTimeStampTokenTest() {
        var data = $("#inputData").val();
        $("#signedData").val("");

        var options = {};
        var ssn = $("#ssn").val();
        if(ssn != "")
            options.ssn = ssn;

        tsHTML5PKI.signDataWithTimeStampToken(data, options, sign_complete_callback);
    }

    function verifySignedDataTest() {
        var data = $("#signedData").val();
        $("#verifyResult").val("");
        $("#signerInfo").html("");
        var certValidation = $("#certValidation").is(":checked");
        tsHTML5PKI.verifySignedData(data, {"certValidation": certValidation}, verify_complete_callback);
    }
	
	//초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init(true);
    });

    </script>


</body>
</html>
