<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>Base64 인코딩/디코딩</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container" >
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>데이터 base64Encode / base64Decode</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading"> 인코딩할 데이터 </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">원본 데이터</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" cols="80" id="plainData">테스트 데이터입니다.</textarea>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="encTest();" class="btn btn-primary"> 인코딩 </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-info">
            <div class="panel-heading"> 인코딩 결과 데이터</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Base64 Encoding</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" cols="80" id="encodedData"></textarea>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="decTest();" class="btn btn-primary"> 디코딩 </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading"> 디코딩 결과 데이터</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Base64 Decoding</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" cols="80" id="decodedData"></textarea>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <hr/>
    </div>
    <%@include file="footer.jsp"%>
</div>
</div>
<script>
    function enc_complete_callback(res) {
        if(res.code == 0) {
            $("#encodedData").val(res.data);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }
    function dec_complete_callback(res){
        if(res.code == 0) {
            $("#decodedData").val(res.data);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function encTest() {
        var data  = $("#plainData").val();
        tsHTML5PKI.base64EncodeData(data,enc_complete_callback);
    }
    function decTest() {
        var data  = $("#encodedData").val();
        tsHTML5PKI.base64DecodeData(data,dec_complete_callback);
    }

	//초기화 함수 필수
    $(document).ready(function(){
		tsHTML5PKI.init(true);
    });

</script>


</body>
</html>
