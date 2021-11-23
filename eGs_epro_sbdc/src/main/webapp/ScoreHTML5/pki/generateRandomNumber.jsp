<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>랜덤값 생성</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>랜덤값 생성</h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">랜덤값 생성</div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group">
                        <label for="randomLength" class="col-sm-2 control-label">랜덤값 길이</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="number" name="randomLength" id="randomLength" value="10">
                        </div>
                        <div class="col-sm-6">
                            <button onclick="genRandomNumberTest();" class="btn btn-primary">랜덤값 생성</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel panel-success">
            <div class="panel-heading">랜덤값</div>
            <div class="panel-body">
                <textarea class="form-control" id="randomNumber" rows="5" ></textarea>
            </div>
        </div>

        <hr>
    </div>
    <%@include file="footer.jsp" %>
</div>
</div>



<script>
    function generate_complete_callback(res) {
        if (res.code == 0) {
            var randomNum = res.data
            var randomNumBase64 = forge.util.encode64(randomNum);
            $("#randomNumber").val(randomNumBase64);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function genRandomNumberTest() {
        $("#randomNumber").val("");

        var randomLength = $("#randomLength").val();
        tsHTML5PKI.generateRandomNumber(randomLength, generate_complete_callback);

    }

</script>


</body>
</html>
