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
        <div><h1>데이터 서명/검증</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">서명 데이터</div>
            <div class="panel-body">
                <form class="form-horizontal" onsubmit="return false;">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="inputData">데이터</label>
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
                    <div class="form-group">
                        <label class="control-label col-sm-2">인코딩</label>
                        <div class="col-sm-10">
                            <div class="radio-list">
                                <label class="radio-inline"><input type='radio' name='encodingType' value='utf-8' /> UTF-8</label>
                                <label class="radio-inline"><input type='radio' name='encodingType' value='euc-kr' /> EUC-KR</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="signDataTest();" class="btn btn-primary">데이터 서명</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명결과</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">서명결과</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="signedData" rows="5" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">인증서 정보</label>
                        <div class="col-sm-10">
                            <div id="signCertInfo" class="scrollable-data"></div>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal" onsubmit="return false;">
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="verifySignedDataTest();" class="btn btn-primary">서명 데이터 검증</button>
                        </div>
                    </div>
<!--                        <div class="checkbox">
                        <label>
                            <input type="checkbox" id="certValidation"/>결과에 인증서 검증 포함
                        </label>
                    </div>-->
                </form>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명 데이터 검증결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">서명 결과</label>
                        <div class="col-sm-10">
                            <textarea id="verifyResult" rows="1" style="width:100%;" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">서명 원문</label>
                        <div class="col-sm-10">
                            <textarea id="verifyContent" rows="3" style="width:100%;" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">서명 정보</label>
                        <div class="col-sm-10">
                            <div id="verifySignerInfo" class="scrollable-data"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <hr>
    </div>
    <%@include file="footer.jsp" %>
</div>
</div>



<script>
    function verify_complete_callback(res) {
        if (res.code == 0) {
            $("#verifyResult").val("검증 성공");
            $("#verifyContent").val(res.data.content);
            for(var i=0;i<res.data.signerInfo.length;i++) {
                var caption = (i+1) + '번째 서명정보';
                $("#verifySignerInfo").append(objectToTable(caption,res.data.signerInfo[i]));
            }
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function sign_complete_callback(res) {
        if (res.code == 0) {
            $("#signedData").val(res.data.signedData);
            $("#signCertInfo").append(objectToTable("",res.data.certInfo));

        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function signDataTest(){
        var options = {};

        $("#signedData").val("");
        $("#signCertInfo").empty();
    
        var data = $("#inputData").val();
        var ssn = $("#ssn").val();
        if(ssn != "") {
            options.ssn = ssn;
        }
        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;
        tsHTML5PKI.signData(data, options, sign_complete_callback);
    }

    function verifySignedDataTest() {
        var data = $("#signedData").val();
        $("#verifyResult").val("");
        $("#verifySignerInfo").html("");
        //var certValidation = $("#certValidation").is(":checked");

        // 서명된 데이터 보여줄 때 사용할 encodingType
        var encodingType = $("input[name=encodingType]:checked").val();
        tsHTML5PKI.verifySignedData(data, {'certValidation': false, 'encodingType': encodingType}, verify_complete_callback);
    }
	//초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init();
    });	
</script>


</body>
</html>
