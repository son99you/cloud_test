//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900


var params = (function () {
    'use strict';

    var Parameters = function () {
        var _plain,
            _option,
            _callback,
            _errorcallback;
        var params = this;

        params.setParameters = function (plain, option, callback, errorcallback) {
            _plain = plain;
            _option = JSON.parse(option);
            _callback = callback;
            _errorcallback = errorcallback;
        };

        params.getPlain = function () {
            return _plain;
        };

        params.getOption = function () {
            return _option;
        };

        params.getCallback = function () {
            return _callback;
        };

        params.getErrorcallback = function () {
            return _errorcallback;
        };

        params.getParameters = function () {
            return {
                plain: _plain,
                option: _option,
                callback: _callback,
                errorcallback: _errorcallback
            };
        };
    };

    return new Parameters();
})();

var browserCertificateDragAnddropImport = (function (doc, $, vest, params, vestSign) {
    
    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;
    var _keySafer;
    var _pfxKeySafer;
    var _hibiKeySafer;
    var dragAnddropDiv = $('#dragAnddropDiv');
    var certPin = $('#passwordInput');
    var pfxPin = $('#pfxPasswordInput');
    var pfxnpkiPin = $('#pfxnpkiPasswordInput');
    var browserCertificatePasswordInput = $('#browserCertificatePasswordInput');
    var browserCertificateImportBtn = $("#browserCertificateImportBtn");
    var browserCertificateImportFile = $('#browserCertificateImportFile');
    var browserCertificateImportContentBackGround = $('#browserCertificateImportContentBackGround');
    var windowDownload = $('#window_download');
    var pwdText = $('#pwdText');
    var pwdBackGround = $('#pwdBackGround');
    var submitButton = $('#submit_btn');
    var cancelButton = $('#cancel_btn');
    var commandText = $('#importResult');
    var selectFileInfo = $('#selectFileInfo');

    var hibiscusErrorHandler = function (error) {
        // getError(error.error + error.message);
        if(error.error == 2423000) { // 금결원 인증서가 아니라 OpenCert쪽에서 에러난 경우 error skip
            _param.callback(true);
        } else {
            getError(vest.error(error.error, decodeURIComponent(error.message)));
        }
    }

    function keyEvent(key, func) {
        if (key.keyCode == 13) {
            func();
            key.preventDefault();
        }
    };

    var arrayBufferToHex = function (buffer) {
        return (Array.prototype.map.call(new Uint8Array(buffer), function(hex) {
            return ('00' + hex.toString(16)).slice(-2);
        })).join('');
    }

    var hibiGetPassword = function (password, callback) {
        var pwd, decKeySafer;
        if (typeof password !== "string" && typeof password.value !== "undefined") {
            decKeySafer = vest.decryptKeySafer(password.type, password.config);
            pwd = password.value;
        } else {
            decKeySafer = vest.decryptKeySafer();
            pwd = browserCertificatePasswordInput.val();
        }

        decKeySafer.getDecryptedPassword(pwd, callback);
        // if (_config.ablePwd) {
        //     if (!_keySafer.initalizeCheck() || _keySafer.getType() == "undefined" || _keySafer.getType() == "ahnlabKeyhook") {
        //         callback(browserCertificatePasswordInput.val());
        //     } else {
        //         // _keySafer.getPassword(callback);
        //         _keySafer.getPassword(function (password) {
        //             callback(password);
        //         }, 'passwordInput');
        //     }
        // } else {
        //     callback(undefined);
        // }
    };

    var okEvent = function (json) {
        _hibiKeySafer.getPassword(function (password) { 
            hibiGetPassword(password, function (password) {
            var certSet;
            if (typeof json.pfx !== 'undefined') {
                try {
                    // certSet = vest.pki.CertificateSet.fromPFX(arrayBufferToHex(json.pfx), password, {encoding: 'hex'});
                    if (_config.yessign.use) { // 금결원 공동저장소
                        var timeStamp = new Date().getTime();
                        vest.token.openCertSetP12(arrayBufferToHex(json.pfx), password, {encoding: 'hex', timeStamp: timeStamp}, function (res) {
                            vest.token.importP12(vest.token.TYPE.HIBISCUS, password, '', '', {
                                pfx: arrayBufferToHex(json.pfx),
                                encoding: 'hex',
                                timeStamp: timeStamp
                            }, function (res) {
                                _param.callback(true);
                                // iframeClose();
                            }, hibiscusErrorHandler);
                        }, hibiscusErrorHandler);
                    } else {
                        vest.token.importP12(vest.token.TYPE.HIBISCUS, password, '', '', {
                            pfx: arrayBufferToHex(json.pfx),
                            encoding: 'hex'
                        }, function (res) {
                            _param.callback(true);
                            // iframeClose();
                        }, hibiscusErrorHandler);
                    }
                } catch (e) {
                    getError(browserCertificateDragAnddropImportLang(9));
                    return;
                }
            } else if (typeof json.signCert !== 'undefined' && typeof json.signPri !== 'undefined') {
                try {
                    certSet = new vest.pki.CertificateSet.fromString(json);
                    var certJson = certSet.toObject({encoding: 'base64'});
                    var timeStamp = new Date().getTime();
                    if (_config.yessign.use) { // 금결원 공동저장소
                        vest.token.openCertSetCertificate(vest.token.TYPE.HIBISCUS, {
                            cert: certJson.signCert,
                            key: certJson.signPri,
                            kmCert: certJson.kmCert,
                            kmKey: certJson.kmPri
                        }, password, {timeStamp: timeStamp}, function (res) {
                            vest.token.importCertificate(vest.token.TYPE.HIBISCUS, {
                                cert: certJson.signCert,
                                key: certJson.signPri,
                                kmCert: certJson.kmCert,
                                kmKey: certJson.kmPri
                            }, password, {timeStamp: timeStamp}, function (res) {
                                _param.callback(res);
                                // iframeClose();
                            }, hibiscusErrorHandler);
                        }, hibiscusErrorHandler);
                    } else {
                        vest.token.importCertificate(vest.token.TYPE.HIBISCUS, {
                            cert: certJson.signCert,
                            key: certJson.signPri,
                            kmCert: certJson.kmCert,
                            kmKey: certJson.kmPri
                        }, password, {}, function (res) {
                            _param.callback(res);
                            // iframeClose();
                        }, hibiscusErrorHandler);
                    }
                } catch (e) {
                    getError(browserCertificateDragAnddropImportLang(8));
                    return;
                }
            } else {
                // importBrowserBtn.click();
                // 가져오기 처음으로, 에러
                getError(browserCertificateDragAnddropImportLang(15));
                browserCertificatePasswordInput.val('');
            }
        })
        }, 'browserCertificatePasswordInput');
        
        // getPassword(nextEvent);
        
    }

    var browserCertificateImport = function (json) {
        browserCertificateImportContentBackGround.show();
        selectFileInfo.show();
        pwdBackGround.hide();
        browserCertificatePasswordInput.val('');
        getError(browserCertificateDragAnddropImportLang(10));
        submitButton.unbind('click').unbind('keydown');
        submitButton.click(function () {
            okEvent(json);
        });
    };

    var browserCertificateImportPFX = function (files) {
        var reader = new FileReader();
        var json = {};
        var tmpName = (files[0].name).substr((files[0].name).length - 4, (files[0].name).length);
        
        reader.onload = function (theFile) {
            if (tmpName == ".pfx" || tmpName == ".PFX" || tmpName == ".p12" || tmpName == ".P12") {
                json.pfx = theFile.target.result;
                showSelectFileInfo(files[0].name);
                browserCertificateImport(json);
            } else {
                getError(new vest.error(2300));
            }
        };
        reader.readAsArrayBuffer(files[0]);
    }

    var browserCertificateImportNPKI = function (files, indexParam, jsonParam) {
        var reader = new FileReader();
        var state = 'none';
        var json = (typeof jsonParam !== 'undefined' || typeof jsonParam === 'object') ? jsonParam : {};
        var index = (typeof indexParam !== 'undefined') ? indexParam : 0;

        if (typeof index !== 'undefined' && index >= files.length) {
            if (typeof json.signCert !== 'undefined' && typeof json.signPri !== 'undefined') {
                browserCertificateImport(json);
            } else {
                getError(new vest.error(2302));
            }
        } else {
            state = files[index].name;
            reader.readAsArrayBuffer(files[index]);
        }

        reader.onload = function (theFile) {
            index++;
            if (state.toLowerCase() == 'signcert.der') {
                //json.signCert = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
                json.signCert = arrayBufferToHex(theFile.target.result);
                showSelectFileInfo(state);
            } else if (state.toLowerCase() == 'signpri.key') {
                // json.signPri = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
                json.signPri = arrayBufferToHex(theFile.target.result);
            } else if (state.toLowerCase() == 'kmcert.der') {
                // json.kmCert = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
                json.kmCert = arrayBufferToHex(theFile.target.result);
            } else if (state.toLowerCase() == 'kmpri.key') {
                // json.kmPri = vest.signHelper.encodeArrayBuffer(theFile.target.result, 'hex');
                json.kmPri = arrayBufferToHex(theFile.target.result);
            } else {
                // error
                getError(browserCertificateDragAnddropImportLang(14));
            }

            browserCertificateImportNPKI(files, index, json);
        };
    };

    var browserCertificateImportAddEvent = function () {        
        browserCertificateImportBtn.keydown(function (key) {
            keyEvent(key, function () {
                browserCertificateImportFile.val('');
                browserCertificateImportFile.change(function (evt) {
                    switch(evt.target.files.length) {
                        case 1:
                            browserCertificateImportPFX(evt.target.files);
                            break;
                        case 2:
                        case 4:
                            browserCertificateImportNPKI(evt.target.files);
                            break;
                        default:
                            getError(browserCertificateDragAnddropImportLang(13));
                            break;
                    }
                });
                browserCertificateImportFile.click();
            });
        });


        browserCertificateImportBtn.unbind('click')
            .bind('click', function () {
                browserCertificateImportFile.val('');
                browserCertificateImportFile.change(function (evt) {
                    switch(evt.target.files.length) {
                        case 1:
                            browserCertificateImportPFX(evt.target.files);
                            break;
                        case 2:
                        case 4:
                            browserCertificateImportNPKI(evt.target.files);
                            break;
                        default:
                            getError(browserCertificateDragAnddropImportLang(13));
                            break;
                    }
                });
                browserCertificateImportFile.click();
            });
        browserCertificateImportBtn.unbind('dragenter dragover drop')
            .bind('dragenter dragover', false)
            .bind('drop', function (evt) {
                switch(evt.originalEvent.dataTransfer.files.length) {
                    case 1:
                        evt.stopPropagation();
                        evt.preventDefault();
                        browserCertificateImportPFX(evt.originalEvent.dataTransfer.files);
                        break;
                    case 2:
                    case 4:
                        evt.stopPropagation();
                        evt.preventDefault();
                        browserCertificateImportNPKI(evt.originalEvent.dataTransfer.files);
                        break;
                    default:
                        getError(browserCertificateDragAnddropImportLang(13));
                        break;
                }
            });
            windowDownload.unbind('click')
            .bind('click', function (e) {
                window.open(_config.NPKI2PFX.window);
            });
    }

    var getE2EInfo = function (callback) {
        var vender;

        if (_keySafer.getNumber() == 7) {
            vender = {
                type: _keySafer.getNumber()
            };
        }
        vest.token.getE2EInfo(vender, callback, vestCertErrorHandler);
    }

    var keySaferScriptLoading = function (keySaferObj, i, func, errorcallback) {
        var script = keySaferObj.getScript();
        var scriptLen = script.length;

        if (scriptLen == 0) {
            errorcallback();
            return;
        }

        var callback = function () {
            if (i == scriptLen - 1) {
                if (keySaferObj.isCert()) {
                    getE2EInfo(function (result) {
                        var data = {
                            publicKey: result.publicKey,
                            keySaferPath: keySaferObj.getPath()
                        };
                        func(data);
                    });
                } else {
                    func();
                }
                return;
            }
            keySaferScriptLoading(keySaferObj, ++i, func, errorcallback);
        };

        var head = document.getElementsByTagName("head")[0];
        var keyScript = document.createElement("script");
        keyScript.src = keySaferObj.getPath() + script[i];

        var flag = false;
        if (keyScript.addEventListener) {
            keyScript.addEventListener("load", callback);
        }
        else if (keyScript.readyState) {
            keyScript.onreadystatechange = function () {
                if (this.readyState == "loaded" || this.readyState == "complete") {
                    if (!flag) {
                        flag = true;
                        callback();
                    }
                }
            };
        } else {
            keySaferObj = keySafer();
        }

        keyScript.onerror = function (error) {
            errorcallback(error.target.src);
        };

        head.appendChild(keyScript);
    };

    function showBrowserCertificatePassword() {
        certPin.hide();
        pfxnpkiPin.hide();
        pfxPin.hide();
        // browserCertificatePasswordInput.val('');
        browserCertificatePasswordInput.show();
    }

    function showBrowserCertificateImportDiv() {
        browserCertificateImportContentBackGround.hide();
        selectFileInfo.hide();
        pwdBackGround.show();
        dragAnddropDiv.show();
        getError(browserCertificateDragAnddropImportLang(11));
        // commandText.val('사용 가능한 인증서가 없습니다. PFX / NPKI 인증서를 선택해주세요.');
        showBrowserCertificatePassword();
    }

    function iframeClose() {
        try {
            _parent.upperGetParameters();
        } catch (e) {
            getError(browserCertificateDragAnddropImportLang(12));
            _parent.upperClose();
            return;
        }
        // 입력 취소시 에러 코드, 메시지를 다음과 같이 하기위함.
        var param = params.getParameters();
        var error = {
            error: 12025,
            message: browserCertificateDragAnddropImportLang(16)
        };

        param.errorcallback(error);
        _parent.upperClose();
    }

    function getError(error) {
        if (typeof error === 'string') {
            commandText.val(error);
        }
        else if ((typeof (error.getReason) !== 'undefined') && (typeof (error.code) !== 'undefined')) {
            var message = vest.error.getErrorMessage(error.code, _config.language);
            if (vest.error.getErrorMessage(error.code, _config.language) == 'undefined') message = error.getReason();

            commandText.val(message + ' [' + error.code + ']');
        }
        else {
            commandText.val('Unknown Error');
        }
    };

    function showSelectFileInfo(filename) {
        selectFileInfo.val(filename+'파일을 선택하였습니다.');
    }

    var hibiKeySaferInit = function () {
        _hibiKeySafer.init(function(res) {
            if(res == _config.hibiscuskeystrokeEncryption) {
                _config.hibiscuskeystrokeEncryption = 'vikie';
                createHibiKeySafer();
                return;
            }
            return;
        })
    }

    var createHibiKeySafer = function () {
        _hibiKeySafer = keySafer(_config.hibiscuskeystrokeEncryption, ['browserCertificatePasswordInput'], _config.pfxKeySaferConfig);
        keySaferScriptLoading(_hibiKeySafer, 0, hibiKeySaferInit, function (data) {
            _hibiKeySafer = keySafer();
        });
    }
    
    $(document).ready(function() {
        try {
            _parent.upperGetParameters();
        } catch (e) {
            getError(browserCertificateDragAnddropImportLang(12));
            _parent.upperClose();
        }

        _param = params.getParameters();
        _config.keySaferConfig.transkey.position.x = [-165, -144];
        _config.keySaferConfig.transkey.position.y = [-120, -210];
        _config.pfxKeySaferConfig.vikie.positionX = 0;
        _config.pfxKeySaferConfig.vikie.positionY = 180;

        browserCertificateImportAddEvent();
        showBrowserCertificateImportDiv();
        createHibiKeySafer();

        $("#titleImport").focus();
        

        cancelButton.click(function () {
            _param.callback();
            iframeClose();
        });

        windowDownload.keydown(function (key) {
            if (!(key.shiftKey) && key.keyCode == 9) {
                $("[tabindex=1]").focus();
                key.preventDefault();
            }
            keyEvent(key, function () {
                cancelEvent(function () {
                    _param.callback();
                    iframeClose();
                });
            });
        });

    });


})(document, jQuery, vest, params, VestSign);

var yettie = vest.util.size;