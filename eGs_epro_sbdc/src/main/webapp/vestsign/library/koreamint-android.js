//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

(function (mainObject) {
    var callbackPool;
    var errCallbackPool;

    function convertToHex(str) {
        var hex = '';
        for(var i=0;i<str.length;i++) {
            hex += ''+str.charCodeAt(i).toString(16);
        }
        return hex;
    }

    function receiver(repCode) {
        if (repCode == 0) callbackPool();
        else errCallbackPool();
    }

    var getCertAndKey = function (dn, pin, callback, errorcallback) {
        callbackPool = callback;
        errCallbackPool = errorcallback;
        myettie.getCertKey(dn, convertToHex(pin));
    };

    var getCertificateList = function (callback, errorcallback) {
        callbackPool = callback;
        errCallbackPool = errorcallback;
        myettie.getCertList();
    };

    var changePassword = function (cn, pwd, newpwd, callback, errorcallback) {
        callbackPool = callback;
        errCallbackPool = errorcallback;
        myettie.changeCertPwd(cn, convertToHex(pwd), convertToHex(newpwd));
    };

    var importCertificate = function (cert, key, kmCert, kmKey, callback, errorcallback) {
        callbackPool = callback;
        errCallbackPool = errorcallback;
        myettie.saveCert(cert, key, kmCert, kmKey);
    };

    var deleteCertificate = function (cn, callback, errorcallback) {
        callbackPool = callback;
        errCallbackPool = errorcallback;
        myettie.deleteCert(cn);
    };
//////////////////////////////////////////////////////////////////////////////////
    function resCert(repCode, cert, key) {
        if (repCode == 0) {
            var res = {
                cert: cert,
                key: key
            };
            callbackPool(res);
        } else {
            errCallbackPool(repCode);
        }
    }

    function resCertList(repCode, certlist) {
        var clist = [];
        
        if (certlist.length !== 0) {
            clist = certlist.split(',');
        }

        if (repCode == 0) {
            callbackPool(clist);
        } else {
            errCallbackPool(repCode);
        }
        
    }
    if(typeof parent.window.yettie !== 'undefined') {
        if (typeof parent.window.yettie.app !== 'undefined') {
            // android app과 통신
            parent.window.yettie.app.resCertList = resCertList;
            parent.window.yettie.app.resCert = resCert;
            parent.window.yettie.app.resChgPwd = receiver;
            parent.window.yettie.app.resDelCert = receiver;
            parent.window.yettie.app.resSaveCert = receiver;
        }
    }
    

    if (mainObject) {
        mainObject.androidKoreamintLib = mainObject.androidKoreamintLib || (mainObject.androidKoreamintLib = {});
        mainObject.extend(mainObject.androidKoreamintLib, {
            getCertificateList: getCertificateList,
            getCertAndKey: getCertAndKey,
            changePassword: changePassword,
            importCertificate: importCertificate,
            deleteCertificate: deleteCertificate
        });
    }

})(vest);