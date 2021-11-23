<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>추가 서명/검증</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>추가 서명/검증</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">서명 데이터</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
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
                            <button class="btn btn-primary" id="signButton" onclick="signData();">서명하기</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">서명 결과 (1차)</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="signedData1">서명결과</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="signedData1" rows="5" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="signedData1">서명자 정보</label>
                        <div class="col-sm-10">
                            <div class="scrollable-data" id="signSignerInfo1"></div>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button class="btn btn-primary" id="_signButton" onclick="addSignInSignedData();">추가서명하기</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">서명 결과(2차)</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="signedData2">서명결과</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="signedData2" rows="5" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="signedData2">서명자 정보</label>
                        <div class="col-sm-10">
                            <div class="scrollable-data" id="signSignerInfo2"></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">검증</div>
            <div class="panel-body">
                <button class="btn btn-primary" onclick="veirfySignedDataTest();">서명 검증하기</button>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="certValidation" disabled/>결과에 인증서 검증 포함
                    </label>
                </div>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명 검증 결과</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label for="verifyResult" class="col-sm-2 control-label">검증 결과</label>
                        <div class="col-sm-10">
                            <textarea id="verifyResult" rows="1" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="verifyContent" class="col-sm-2 control-label">원문 데이터</label>
                        <div class="col-sm-10">
                            <textarea readonly id="verifyContent" rows="3" class="form-control" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="verifyData" class="col-sm-2 control-label">서명 정보</label>
                        <div class="col-sm-10">
                            <div id="signerInfo" class="scrollable-data " ></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <hr>
    </div>
    <%@include file="footer.jsp" %>
</div>
</div>



<script>

    function signData() {

        $("#signedData1").val("");
        $("#signSignerInfo1").empty();
        $("#signedData2").val("");
        $("#signSignerInfo2").empty();
        $("#verifyResult").val("");
        $("#verifyContent").val("");
        $("#verifySignerInfo").html("");

        var options = {};
        var data = $("#inputData").val();
        var ssn = $("#ssn").val();
        if(ssn != "") {
            options.ssn = ssn;
        }
        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;
        tsHTML5PKI.signData(data, options, signData_callback);
    }

    function signData_callback(res) {

        if (res.code == 0) {
            $("#signedData1").val(res.data.signedData);
            $("#signSignerInfo1").append(objectToTable("",res.data.certInfo));

        }
        else
            alert("error code = " + res.code + ", message = " + res.errorMessage);
    }

    function addSignInSignedData() {

        $("#signedData2").val('');
        $("#signSignerInfo2").empty();

        var options = {};
        var signedData = $("#signedData1").val();
        var ssn = $("#ssn").val();
        if(ssn != "") {
            options.ssn = ssn;
        }

        tsHTML5PKI.addSignInSignedData(signedData, options, addSignInSignedData_callback);
    }

    function addSignInSignedData_callback(res) {
        if (res.code == 0) {
            $("#signedData2").val(res.data.signedData);
            $("#signSignerInfo2").append(objectToTable("",res.data.certInfo));
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function veirfySignedDataTest() {

        $("#signerInfo").html("");
        $("#verifyContent").val("");

        var data = $("#signedData2").val();
        var encodingType = $("input[name=encodingType]:checked").val();
        <%--var certValidation = $("#certValidation").is(":checked");--%>

        tsHTML5PKI.verifySignedData(data, {'certValidation': false, 'encodingType': encodingType}, verify_complete_callback);

    }

    function verify_complete_callback(res) {
        if (res.code == 0) {
            $("#verifyResult").val("검증 성공");
            $("#verifyContent").val(res.data.content);

            for(var i=0;i<res.data.signerInfo.length;i++) {
                var caption = (i+1) + '번째 서명정보';
                $("#signerInfo").append(objectToTable(caption,res.data.signerInfo[i]));
            }
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    //초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init();
    });

    </script>


</body>
</html>
