<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common.jsp" %>
    <title>전자봉투 데이터 서명/검증</title>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>Envelop / DecryptEnvelop </h1></div>

        <div class="panel panel-primary">
            <div class="panel-heading">Envelop될 원문 데이터</div>
            <div class="panel-body">
                <form onsubmit="return false" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="testCert">인증서</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="testCert" rows="5" cols="80"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="testData">데이터</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="testData" rows="3" cols="80">Envelop 용 샘플 데이터 입니다.</textarea>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <button  onclick="envelopDataTest();" class="btn btn-primary">Envelop</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading">Envelop 결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="envelopedData">결과</label>
                        <div class="col-sm-10">
                            <textarea id="envelopedData" rows="5" cols="80" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group actions">
                        <form onsubmit="return false;">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-10">
                                <button onclick="decryptEnvelopDataTest();" class="btn btn-primary">서명 데이터 검증</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading">서명 데이터 검증결과</div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="decryptEnvelopedData">검증결과</label>
                        <div class="col-sm-10">
                            <textarea id="decryptEnvelopedData" rows="5" cols="80" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">키정보</label>
                        <div class="col-sm-10">
                            <div id="ecvelopKeyInfo" class="scrollable-data"></div>
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
    function decrypt_complete_callback(res) {
        if (res.code == 0) {
            $("#decryptEnvelopedData").val(res.data.data);
            $("#ecvelopKeyInfo").append(objectToTable("",res.data.keyInfo));
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }
    function envelop_complete_callback(res) {
        if (res.code == 0) {
            $("#envelopedData").val(res.data);
        }
        else {
            alert("error code = " + res.code + ", message = " + res.errorMessage);
        }
    }

    function envelopDataTest() {
        var data = $("#testData").val();
        var cert = $("#testCert").val();
        $("#envelopedData").val("");
        tsHTML5PKI.envelopData(data, cert,{}, envelop_complete_callback);
    }

    function decryptEnvelopDataTest() {
        var envelopedData = $("#envelopedData").val();
        $("#verifyResult").val("");
        $("#ecvelopKeyInfo").empty();
        $("#decryptEnvelopedData").val('');

        var certValidation = $("#certValidation").is(":checked");
        tsHTML5PKI.decryptEnvelopedData(envelopedData, {"certValidation": certValidation}, decrypt_complete_callback);
    }

    //초기화 함수 필수
    $(document).ready(function(){
        var kmCert        = "-----BEGIN CERTIFICATE-----MIIFLTCCBBWgAwIBAgIEWZHJYjANBgkqhkiG9w0BAQsFADBPMQswCQYDVQQGEwJLUjESMBAGA1UECgwJVHJhZGVTaWduMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFTATBgNVBAMMDFRyYWRlU2lnbkNBMjAeFw0xNTA1MTEwOTI1NDlaFw0xNjA2MDkwMjE4MzlaMG0xCzAJBgNVBAYTAktSMRIwEAYDVQQKDAlUcmFkZVNpZ24xFTATBgNVBAsMDEFjY3JlZGl0ZWRDQTENMAsGA1UECwwEVEVTVDEkMCIGA1UEAwwb7YWM7Iqk7Yq4MV9LVE5FVF8wMDAwMDY4NzI1MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAveYox+dQm+/optOXm6Dvz79tfBgQD2IfAWeTlhFtVLQuCfDhXP2+IPSPnSHEv8PVrsRK0NrKnKqAMJF8FUr/u3spQs2xuMHfN4WtU+Vw70Y3ZEFC30hyPa0outUWAiImlBuAjmMpOSeW0WSRq93FseF2AQM7SZlzcyfX+kOpQIGbFTIT2ky5ryEpd9hX9gjPfJh7X3eB3Fjk82XuoiB/yUEh7pFqDWgxcVXVh9FLygGmdSVH11r/8uVzBDo+rAEp0I6QuUBf+jJAxkL6fwi1jAk8ZzFHWR2y7u2BCSxDJ+ekosjCg9VeD/vvDZZHJeohilr5beNVRxMirFRjs1KjFwIDAQABo4IB8TCCAe0wgY8GA1UdIwSBhzCBhIAUTV1WCgcD34PK89Vtjxn8EqyQooqhaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYDVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQCTAdBgNVHQ4EFgQUGm48OW5bVFEaVu7RKCq+0EeegckwDgYDVR0PAQH/BAQDAgUgMHoGA1UdIAEB/wRwMG4wbAYJKoMajJpMAQEEMF8wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLQYIKwYBBQUHAgEWIWh0dHA6Ly93d3cudHJhZGVzaWduLm5ldC9jcHMuaHRtbDBmBgNVHR8EXzBdMFugWaBXhlVsZGFwOi8vbGRhcC50cmFkZXNpZ24ubmV0OjM4OS9jbj1jcmwxZHA1ODMsb3U9Y3JsZHAyLG91PUFjY3JlZGl0ZWRDQSxvPVRyYWRlU2lnbixjPUtSMEYGCCsGAQUFBwEBBDowODA2BggrBgEFBQcwAYYqaHR0cDovL29jc3AudHJhZGVzaWduLm5ldDoxODAwMC9PQ1NQU2VydmVyMA0GCSqGSIb3DQEBCwUAA4IBAQAcuZcKKDin/gC+Zy5KulpFGZF1MXdHgecZugvUwGVGhgL+yrBUFWNM3Z0swLnfXJM1kc4mUB2ecmIEJ9/6KbFYHRqbMTNhXemDeVthFV9VuK4hF7Uh5ZkVzbt0kn6Qz6ilP6ZmNBQfOZDEBAZEjcdZ+24t0yR4qDELxcLCJMyeRUIjiMG2CpSmsHxA94wM4jpmd/xQQ52p83VVln8jQfAjk+SGSGqdGv6onvZgjACloJ6JhfngeluOKGEk062dQisf08t1s/I8i6q4u2JbyIbOfHY6kgPRPbAKwco5SXGQbHm9wzZk3F5dJwxHU+5ge9+f0Ttih6fR4uzBM1FMm4Oy-----END CERTIFICATE-----";
        $("#testCert").val(kmCert);
        tsHTML5PKI.init();
    });

</script>


</body>
</html>
