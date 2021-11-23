<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>암호화 테스트</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>데이터 암호화/복호화</h1></div>
        <div class="panel panel-primary">
            <div class="panel-heading">키 정보</div>
            <div class="panel-body">
                <form class="form-horizontal " onsubmit="return false;">
                    <div class="form-group">
                        <label for="key" class="col-sm-2 control-label">키 타입</label>
                        <div class="col-sm-10">
                            <div class="radio-list">
                                <label class="radio-inline"><input type="radio" name="keyType" id="keyTypeGen" value="0" checked> 패스워드 입력</label>
                                <label class="radio-inline"><input type="radio" name="keyType" id="keyTypeSet" value="1" > 키, iv 입력 (Base64)</label>
                                <label class="radio-inline"><input type="radio" name="keyType" id="keyTypeNone" value="2" > 입력 X</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="algorithm" class="col-sm-2 control-label">알고리즘</label>
                        <div class="col-sm-10">
                            <select id="algorithm" class="form-control" name="algorithm"></select>
                        </div>
                    </div>
                    <div class="form-group" id="pwdArea">
                        <label for="key" class="col-sm-2 control-label">암호화 패스워드</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="pwd" name="pwd" placeholder="키, iv 를 생성할 때 사용할 패스워드 입력" value="sample-key">
                        </div>
                    </div>
                    <div class="form-group" id="keyArea">
                        <label for="key" class="col-sm-2 control-label">암호화 키</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="key" name="key" placeholder="" value="">
                        </div>
                    </div>
                    <div class="form-group" id="ivArea">
                        <label for="iv" class="col-sm-2 control-label">암호화 IV</label>
                        <div class="col-sm-10">
                           <input class="form-control" type="text" id="iv" name="iv" placeholder="">
                        </div>
                    </div>
                    <div class="form-group" id="generatedKeyArea">
                        <label for="key" class="col-sm-2 control-label">생성된 암호화 키</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="generatedKey" name="generatedKey" placeholder="" value="" readonly>
                        </div>
                    </div>
                    <div class="form-group" id="generatedIvArea">
                        <label for="iv" class="col-sm-2 control-label">생성된 암호화 IV</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="generatedIv" name="generatedIv" placeholder="" readonly>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">입력데이터</div>
            <div class="panel-body">
                <div class="form-group">
                    <textarea class="form-control" id="inputData" rows="5" cols="80">sample data</textarea>
                </div>
                <button onclick="encryptDataTest();" class="btn btn-primary"> 암호화 </button>
            </div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading">encrypted 데이터</div>
            <div class="panel-body">
                <div class="form-group">
                    <textarea class="form-control" id="encryptedData" rows="5" cols="80"></textarea>
                </div>
                <button onclick="decryptDataTest();" class="btn btn-primary"> 복호화 </button>
            </div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading">decrypted 데이터</div>
            <div class="panel-body">
                <div class="form-group">
                    <textarea class="form-control" id="decryptedData" rows="5" cols="80"></textarea>
                </div>
            </div>
        </div>

        <hr>
        <%@include file="footer.jsp"%>
    </div>
</div>
</div>
<script>
    function encrypt_complete_callback(res) {
        if(res.code == 0) {
            $("#encryptedData").val(res.data.data);
            $("#generatedKey").val(res.data.key);
            $("#generatedIv").val(res.data.iv);

        }
        else {
            alert(res.errorMessage);
        }
    }
    function decrypt_complete_callback(res){
        if(res.code == 0)
            $("#decryptedData").val(res.data.data);
        else
            alert(res.errorMessage);
    }

    function decryptDataTest() {
        var pwd = $("#pwd").val();
        var key = $("#key").val();
        var iv = $("#iv").val();
        var encryptedData = $("#encryptedData").val();
        var alg = $("#algorithm").val();

        if($('input:radio[name="keyType"]:checked').val() != 1){
            key = $("#generatedKey").val();
            iv = $("#generatedIv").val();
        }

        key = {
            pwd:pwd,
            key:key,
            iv:iv
        };

        tsHTML5PKI.decryptData(encryptedData, key, {algorithm:alg}, decrypt_complete_callback);
    }

    function encryptDataTest() {
        var pwd = $("#pwd").val();
        var key = $("#key").val();
        var iv = $("#iv").val();
        var data = $("#inputData").val();
        var algorithm = $("#algorithm").val();

        key = {
            pwd:pwd,
            key:key,
            iv:iv
        };

        tsHTML5PKI.encryptData(data, key, {algorithm:algorithm}, encrypt_complete_callback);
    }

    $(document).ready(function(){
        var algs = ["des","3des","seed"];
        var modes = ["ecb","cbc","cfb","ofb"];

        for(var a=0;a<algs.length;a++) {
            for(var m=0;m<modes.length;m++) {
                var alg = algs[a] + "-" + modes[m];
                $("#algorithm").append('<option value="'+alg+'">'+alg+'</option>');
            }
        }
        $("#algorithm > option[value=3des-cbc]").attr("selected","true");

        $("#keyArea").hide();
        $("#ivArea").hide();

        $('input[type=radio][name=keyType]').change(function(){
            $("#pwd").val("");
            $("#key").val("");
            $("#iv").val("");
            $("#generatedKey").val("");
            $("#generatedIv").val("");

            if(this.value == "0") {
                $("#pwdArea").show();
                $("#keyArea").hide();
                $("#ivArea").hide();
                $("#generatedKeyArea").show();
                $("#generatedIvArea").show();
                $("#pwd").val("sample-key");
            }
            else if (this.value == "1"){
                $("#pwdArea").hide();
                $("#keyArea").show();
                $("#ivArea").show();
                $("#generatedKeyArea").hide();
                $("#generatedIvArea").hide();
            }else{
                $("#pwdArea").hide();
                $("#keyArea").hide();
                $("#ivArea").hide();
                $("#generatedKeyArea").show();
                $("#generatedIvArea").show();
            }
        });
    });

    //초기화 함수 필수
    $(document).ready(function(){
        tsHTML5PKI.init();
    });

</script>
</body>
</html>
