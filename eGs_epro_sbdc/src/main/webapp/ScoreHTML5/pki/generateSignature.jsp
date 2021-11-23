<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="./common.jsp" %>
    <title>서명 생성/검증</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>서명 생성/검증</h1></div>
        <div class="panel panel-primary">
            <div class="panel-heading">서명 데이터 생성</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label for="dataType" class="control-label col-sm-2">서명값 생성 타입</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="dataType" name="dataType">
                                <option value="101">데이터 서명 생성</option>
                                <option value="102">Base64Encoding 된 데이터 서명 생성</option>
                                <!--<option value="103">파일 서명 생성</option>-->
                                <option value="104">Hash 된 데이터 서명 생성</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group" id="dataGroup">
                        <label class="control-label col-sm-2" for="data">데이터</label>
                        <div class="col-sm-10">
                            <textarea cols="80" rows="3" class="form-control" name="data" id="data" placeholder="서명값을 생성할 데이터 입력">서명생성용 샘플 데이터</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="ssn">신원확인 정보</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="ssn" placeholder="인증서 신원확인 정보">
                        </div>
                    </div>
                    <div class="form-group" id ='encoding'>
                        <label class="control-label col-sm-2" for="inputData">인코딩</label>
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
                           <button onclick="generateSignatureTest();" class="btn btn-primary"> 서명값 생성</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명 생성 결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">서명값</label>
                        <div class="col-sm-10">
                            <textarea id="sigData" rows="5" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">인증서 정보</label>
                        <div class="col-sm-10">
                            <div id="sigCertInfo" class="scrollable-data"></div>
                            <div id="sigCert" ></div>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="verifySignatureTest();" class="btn btn-primary">서명 데이터 검증</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명 데이터 검증결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">검증결과</label>
                        <div class="col-sm-10">
                            <textarea id="verifyResult" rows="1" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">인증서 정보</label>
                        <div class="col-sm-10">
                            <div class="scrollable-data" id="verifyCertInfo"></div>
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
            $("#verifyResult").val("서명검증 성공");
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function generate_signature_complete_callback(res) {
        if (res.code == 0) {
            $("#sigData").val(res.data.signature);
            $("#sigCert").text(res.data.certInfo.cert);
            $("#sigCert").hide();
            $("#sigCertInfo").append(objectToTable("",res.data.certInfo));
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function generateSignatureTest(){
        $("#sigCertInfo").empty();
        $("#sigData").val("");
        $("#sigCert").text("");

        var data = {
            dataType: $("#dataType").val(),
            data:$("#data").val()
        }

        var options = {};
        var ssn = $("#ssn").val();
        if(ssn != "")
            options.ssn = ssn;

        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;

        tsHTML5PKI.generateSignature(data, options, generate_signature_complete_callback);
    }

    function verifySignatureTest(){
        $("#verifyCertInfo").empty();
        var dataType = $("#dataType").val();
        var data = $("#data").val();

        var sigData = $("#sigData").val();
        var signerCert = $("#sigCert").text();

        $("#verifyResult").val("");

        var options = {};
        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;

        tsHTML5PKI.verifySignature(dataType,data,sigData,signerCert, options, verify_complete_callback);
    }

    $(document).ready(function(){
        $("#dataType").change(function() {
            var dataType = $("#dataType").val();
            if(dataType == 101) {
                $('#encoding').show();
            }
            else {
                $("#encoding").hide();
            }
        });
    });
	
	//초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init(true);
    });
	
</script>

</body>
</html>
