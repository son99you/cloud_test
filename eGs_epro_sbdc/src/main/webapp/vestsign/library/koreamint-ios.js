//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900


(function(mainObject){

    var messageNumber = 0;
    var callbackPool = {};

    function sender(reqData, callback) {
        // javascript -> ios
        messageNumber++;
        reqData.messageNumber = messageNumber;
        callbackPool[messageNumber] = callback;

        window.webkit.messageHandlers.iosCallKoreamintLib.postMessage(reqData);
    }

    function receiver(repString) {
        // ios -> javascript json
        //[webView stringByEvaluatingJavaScriptFromString:[NSString stringWithFormat:@"javascriptFunction('%@')", parameter]];
        // callback, errorcallback 함수인지 확인 필요. 함수가 아니면 네이티브에 에러리턴.
        /*
         repObject.code = 0         // number: 결과
         repObject.messageNumber    // number: 골백함수.
         repObject.data             // object: 요청 오퍼레이션마다 다름.
         repObject.operation        // string: 오퍼레이션
            에러나면 어쩔꺼야??? 처리방법 생각좀.
         */
        var rep = JSON.parse(repString);
        //parent.document.getElementById('res1').innerText = repString;
        //parent.document.getElementById('res2').innerText = 'test';
        var callback = callbackPool[rep.messageNumber];

        callback(rep);
    }

    var errorHandler = function(error) {
        //alert(error.getReason());
        alert(vest.util.refactoryMsg.koreaMintLibErrorHandler(error.code));
    }

    var isError = function(data){
        if(typeof data.code != 'undefined' && data.code == 0) {
            return false;
        }
        return true;
    }

    var isIOS = function() {
        if(typeof window.webkit !== 'undefined' && typeof window.webkit.messageHandlers !== 'undefined' && typeof window.webkit.messageHandlers.iosCallKoreamintLib !== 'undefined'){
            return true;
        }
        return false;
    };

    var STATUS = {
        READY: 'ready',
        CLOSE: 'close',
        SIGN: 'sign',
        MANAGER: 'manager'
    };

    var domStatus = function(status, data, options, callback, errorcallback) {
        var req = {};
        req.operation = 'domStatus';
        req.data = {
            status: status,
            data: data,
            options: options
        };

        sender(req, function(){});

    };

    var getCertificateList = function(options, callback, errorcallback) {
        var req = {};
        req.operation = 'getCertificateList';
        req.data = {
            options: options
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result.list);
            }
        });
    };

    var getCertificate = function(dn, options, callback, errorcallback ) {
        var req = {};

        req.operation = 'getCertificate';
        req.data = {
            options: options,
            dn: dn
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result.certificate);
            }
        });
    };

    var getPrivateKey = function(dn, pin, options, callback, errorcallback ) {
        var req = {};

        req.operation = 'getPrivateKey';
        req.data = {
            dn: dn,
            pin: pin,
            options: options
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message + '(password fail)'));
            }else {
                callback(result.privatekey);
            }
        });
    };

    var changePassword = function(dn, oldPin, newPin, options, callback, errorcallback) {
        var req = {};

        req.operation = 'changePassword';
        req.data = {
            dn: dn,
            oldPin: oldPin,
            newPin: newPin,
            options: options
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result);
            }
        });
    };

    var importCertificate = function(cert, key, kmCert, kmKey, options, callback, errorcallback) {
        var req = {};
        req.operation = 'importCertificate';
        req.data = {
            cert: cert,
            key: key,
            options: options
        };

        if(kmCert != 'undefined' && kmKey != 'undefined' && kmCert != "" && kmKey != "") {
            req.data.kmCert = kmCert;
            req.data.kmKey = kmKey;
        }
        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result);
            }
        });
    };

    var deleteCertificate = function(dn, options, callback, errorcallback) {
        var req = {};

        req.operation = 'deleteCertificate';
        req.data = {
            dn: dn,
            options: options
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result);
            }
        });
    };

    var verifyPin = function(dn, pin, options, callback, errorcallback) {
        var req = {};

        req.operation = 'verifyPin';
        req.data = {
            dn: dn,
            pin: pin,
            options: options
        };

        sender(req, function(result){
            if(isError(result)) { // 에러코드인가요?
                errorcallback(new vest.error(result.code, result.message));
            }else {
                callback(result);
            }
        });
    };


    if (mainObject) {
        mainObject.iosKoreamintLib = mainObject.iosKoreamintLib || (mainObject.iosKoreamintLib = {});
        mainObject.extend(mainObject.iosKoreamintLib, {
            receiver: receiver,
            isIOS: isIOS,
            errorHandler: errorHandler,
            STATUS: STATUS,
            domStatus: domStatus,
            getCertificateList: getCertificateList,
            getPrivateKey: getPrivateKey,
            getCertificate: getCertificate,
            changePassword: changePassword,
            importCertificate: importCertificate,
            deleteCertificate: deleteCertificate,
            verifyPin: verifyPin
        });

    }
})(vest);