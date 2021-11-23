    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="./common.jsp" %>
    <title>로그 확인</title>
    <style>
    </style>
</head>
<body class="page-container-bg-solid page-header-fixed">
<div class="page-container">
<div class="container">
    <%@include file="header.jsp" %>
    <div class="col-md-12">
        <div><h1>로그 확인</h1></div>
        <div class="panel panel-primary">
            <div class="panel-heading"><label id="tsHTML5_version"></label></div>
            <div class="panel-body">
                <form onsubmit="return false;" class="form-horizontal">
                    <div class="form-group" id="dateGroup">
                        <label class="control-label col-sm-2" for="data">조회 일자</label>
                        <div class="col-sm-2">
                            <select id='date' class="form-control">
                                <option>날짜 선택</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <button onclick="getLog();" class="btn btn-primary">조회</button>
                            <button onclick="downLog()" class="btn btn-primary">다운로드</button>
                        </div>
                    </div>
                    <div class="form-group" id="dataGroup">
                        <label class="control-label col-sm-2" for="data">데이터</label>
                        <div class="col-sm-10">
                            <textarea cols="120" rows="30" class="form-control" name="data" id="data"></textarea>
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
    window.onload = function(){
        var keys = [];
        var yyyy, mm, dd, dateText;
        var date = new Date();
        for (var i = 0; i < tsHTML5PKIConfig.common.logRollingCount; i++) {
            var newDate = new Date(date - (1000*3600*24*i));
            yyyy = newDate.getFullYear().toString();
            mm = (newDate.getMonth()+1).toString();
            dd = newDate.getDate().toString();
            dateText = yyyy + (mm[1]? mm:'0'+mm[0]) + (dd[1]? dd: '0'+dd[0]);
            keys.push(dateText);
        }

        //ts5Log.getLogList();
        var selectBox = document.getElementById('date');
        for(var i in keys){
            selectBox.options.add(new Option(keys[i], keys[i]));
        }

        $("select option:eq(1)").attr("selected", "selected");
        getLog();

        document.getElementById('tsHTML5_version').innerHTML = "버전 정보<br>" + tsHTML5PKI.version().replace('\n','<br>');
    }

    function getLog(){
        var date = $('#date option:selected').val();
        $('#data').val(tsHTML5PKI.getLog(true, date));
    }

    function downLog(){
        var date = $('#date option:selected').val();
        tsHTML5PKI.getLog(false, date);
    }
	
</script>

</body>
</html>
