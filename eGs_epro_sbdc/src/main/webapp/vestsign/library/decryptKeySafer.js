//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2017. 3. 6..
 */

/*
 notKeySafer  : 0
 nProtect     : 10
 dillService  : 666
 */
var decryptKeySafer = function (mode, config) {

    var decryptKeySaferAdapter = function () {
        var _mode = mode;
        var _object;

        switch (_mode) {
            case 'npk':
            case 10:
                _object = new nProtect(config);
                break;
            case 'dill':
            case 666:
                _object = new dillService(config);
                break;
            default:
                _object = new notKeySafer();
        }

        return _object;
    };

    function addScript(path, fileArray, callback) {
        var decryptScript;
        var head = document.getElementsByTagName('head')[0];

        var loading = function(i, func) {
            if(fileArray.length > i) {
                decryptScript = document.createElement('script');
                decryptScript.src = path + fileArray[i];

                var flag = false;
                if(decryptScript.addEventListener) {
                    decryptScript.addEventListener('load', function(){
                        loading(++i, func);
                    });
                }else if(decryptScript.readyState) {
                    decryptScript.onreadystatechange = function() {
                        if(this.readyState == 'loaded' || this.readyState == 'complete') {
                            if(!flag) {
                                flag = true;
                                loading(++i, func);
                            }
                        }
                    }
                }

                head.appendChild(decryptScript);
            }else {
                func();
            }
        };

        loading(0, callback);
    }

    var notKeySafer = function () {
        var _type = 'undefined';

        this.getDecryptedPassword = function (password, callback) {
            callback(password);
        };
        this.getType = function () {
            return _type;
        };
    };

    var nProtect = function (config) {
        var _type = 'npk';

        this.getDecryptedPassword = function (password, callback) {
            //return nprotectDecrypt(password);
            callback( nprotectDecrypt(password) );
        };
        this.getType = function () {
            return _type;
        };
    };

    var dillService = function(config) {
        var _type = 'dill';
        var _path = config.path;
        var _script = config.fileName;

        this.getDecryptedPassword = function(password, callback) {
            //{id: id, data: value}
            var getPassword = function() {
                callback(dill.server.getPassword(password));
            };

            if(typeof window.dill !== 'object') {
                addScript(_path, _script, getPassword);
            } else {
                getPassword();
            }
        }
    };

    return decryptKeySaferAdapter();
};

vest.decryptKeySafer = decryptKeySafer;