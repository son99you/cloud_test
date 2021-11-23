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
                        <label class="control-label col-sm-2" for="inputData1">데이터1</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="inputData1" rows="3" value="테스트용 서명 데이터 입니다 1"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="inputData2">데이터2</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="inputData2" rows="3" value="테스트용 서명 데이터 입니다 2"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="inputData3">데이터3</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="inputData3" rows="3" value="테스트용 서명 데이터 입니다 3"/>
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
                        <div class="col-sm-3">
                            <div class="radio-list">
                                <label class="radio-inline"><input type='radio' name='encodingType' value='utf-8' /> UTF-8</label>
                                <label class="radio-inline"><input type='radio' name='encodingType' value='euc-kr' /> EUC-KR</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button onclick="generateMultiSignedData();" class="btn btn-primary">데이터 서명</button>
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

    function MultiSignedDataCallBack(res){
        if(res.code ==0){

            for(var i=0;i<res.data.length;i++) {
                $("#signResult").append(objectToTable((i+1) + "번째 서명",res.data[i]));
            }
        }else{
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function generateMultiSignedData(){

        var options = {};
        $("#signCertInfo").empty();

        var data = [];
        data.push($('#inputData1').val());
        data.push($('#inputData2').val());
        data.push($('#inputData3').val());
        var ssn = $("#ssn").val();
        if(ssn != "") {
            options.ssn = ssn;
        }
        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;
        tsHTML5PKI.signData(data, options, MultiSignedDataCallBack);
    }

    //초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init();
    });

    </script>

</body>
</html>
