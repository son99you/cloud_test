<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>해시 데이터</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>해쉬 데이터 생성</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">입력 데이터</div>
            <div class="panel-body">
            <form class="form-horizontal " onsubmit="return false;">
                <div class="form-group">
                    <label for="iv" class="col-sm-2 control-label">알고리즘</label>
                    <div class="col-sm-10">
                        <select id="algorithm" class="form-control" name="algorighm"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="iv" class="col-sm-2 control-label">해쉬할 데이터</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="inputData" rows="3" cols="80">sample hash data</textarea>
                    </div>
                </div>
                <div class="form-group">
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
                        <button onclick="hashDataTest();" class="btn btn-primary">해쉬</button>
                    </div>
                </div>
            </form>
        </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">해쉬결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">해쉬값</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="hashData" rows="5" cols="80"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
    </form>
    <%@include file="footer.jsp" %>
</div>
</div>



<script>
    function hash_complete_callback(res) {
        if (res.code == 0) {
            $("#hashData").val(res.data);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function hashDataTest() {
        var data = $("#inputData").val();
        $("#hashData").val("");
        var alg = $("#algorithm").val();

        var options = {};
        var encodingType = $("input[name=encodingType]:checked").val();
        options.encodingType = encodingType;

        tsHTML5PKI.hashData(alg, data, options, hash_complete_callback);
    }

    $(document).ready(function(){
//        var algs = ["MD5","RIPEMD160","SHA1","HAS160","SHA256","SHA384","SHA512"];
        var algs = ["MD5", "SHA1", "SHA256","SHA384","SHA512"];

        for(var a=0;a<algs.length;a++) {
                $("#algorithm").append('<option value="'+algs[a]+'">'+algs[a]+'</option>');
        }
        $("#algorithm > option[value=SHA256]").attr("selected","true");
    });

	//초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init();
    });
	
</script>


</body>
</html>
