<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>멀티 서명 생성</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>멀티 서명 생성</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">서명 데이터</div>
            <div class="panel-body">
                <form class="form-horizontal" onsubmit="return false;">
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="inputData1">데이터 1 (플레인)</label>
                        <div class="col-sm-9">
                            <input class="form-control" id="inputData1" rows="3" value="테스트용 서명 데이터 입니다"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="inputData2">데이터 2 (Base64)</label>
                        <div class="col-sm-9">
                            <input class="form-control" id="inputData2" rows="3" value="4SBCYXNlNjQgUgwIIA=="/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="inputData3">데이터 3 (Hashed Base64)</label>
                        <div class="col-sm-9">
                            <input class="form-control" id="inputData3" rows="3" value="VOvd+Y9e8mup93Ige4T7E5kmf09KXHqCz4nAZzOZA/Y="/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="ssn">신원확인 정보</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="ssn" placeholder="인증서 신원확인 정보">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-3"></div>
                        <div class="col-sm-9">
                            <button onclick="signDataTest();" class="btn btn-primary">서명 생성</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">서명결과</div>
            <div class="panel-body">
                <div id="signResult">

                </div>
            </div>
        </div>
        <hr>
    </div>
    <%@include file="footer.jsp" %>
</div>
</div>



<script>
    function sign_complete_callback(res) {
        $("#signResult").empty();
        if (res.code == 0) {
            for(var i=0;i<res.data.length;i++) {
                $("#signResult").append(objectToTable((i+1) + "번째 서명",res.data[i]));
            }
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function signDataTest() {
        var options = {};

        $("#signedData").val("");
        $("#signCertInfo").empty();


        var data1 = $("#inputData1").val();
        var data2 = $("#inputData2").val();
        var data3 = $("#inputData3").val();

		// 입력된 데이타 또는 파일 경로로 Signature 값 생성.
		// dataType : 101 - plainText 가 서명할 문자열을 가짐.
		// dataType : 102 - Base64 encoding 된 서명할 데이터를 가짐
		// dataType : 104 - Base64 encoding 된 Hash Value 를 서명할 데이터로 가짐
        // dataType : 103 - plainText 가 서명할 파일의 절대 경로를 가짐.(지원안함)
		var data = [];
        if(data1 != "") data.push({dataType:101,data:data1});
        if(data2 != "") data.push({dataType:102,data:data2});
        if(data3 != "") data.push({dataType:104,data:data3});

        var ssn = $("#ssn").val();
        if(ssn != "") {
            options.ssn = ssn;
        }

        tsHTML5PKI.generateSignature(data, options, sign_complete_callback);
    }

    //초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init(true);
    });

</script>


</body>
</html>
