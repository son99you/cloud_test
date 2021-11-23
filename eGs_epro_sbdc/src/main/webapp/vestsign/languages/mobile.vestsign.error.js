//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2017. 03. 21.
 */
(function (window, vest, undefined) {
    'use strict';

    var type = {
        'ko': 0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2,
        'cn': 3,
        'zh-cn': 3
    };

    var language = [
        {
            /* KoreaMintLib Error */
            KMError_READ_CERTIFICATE_FAILED: "인증서 읽기에 실패했습니다.",
            KMError_PARSE_CERTIFICATE_FAILED: "잘못된 인증서 형식입니다.",

            KMError_INVALID_X509_FORMAT: "잘못된 인증서 형식입니다.",
            KMError_INVALID_KEY_FORMAT: "잘못된 키 형식입니다.",

            KMError_INVALID_PASSWORD: "비밀번호가 틀렸습니다.",
            KMError_CERT_BAG_EMPTY: "선택된 인증서가 없습니다.",
            KMError_ADD_CERT_FAILED: "PFX 인증서 파일 삽입에 실패했습니다.",
            KMError_PKCS12_MAKE_FAILED: "PFX 인증서 파일 생성에 실패했습니다.",
            KMError_PKCS12_ENCODE_FAILED: "PFX 인증서 encoding에 실패했습니다.",

            KMError_EXPORT_CERTIFICATE_CANCEL: "사용자가 입력을 취소했습니다.",

            KMError_OPERATION_GETCERTIFICATELIST: "인증서 리스트 요청 실패했습니다.",
            KMError_OPERATION_GETCERTIFICATE: "인증서 요청 실패했습니다",
            KMError_OPERATION_GETPRIVATEKEY: "비밀번호가 틀렸습니다.",
            KMError_OPERATION_CHANGEPASSWORD: "비밀번호 변경에 실패했습니다.",
            KMError_OPERATION_IMPORTCERTIFICATE: "인증서 저장에 실패했습니다.",
            KMError_OPERATION_DELETECERTIFICATE: "인증서 삭제에 실패했습니다.",
            KMError_OPERATION_VERIFYPIN: "비밀번호 확인을 실패했습니다."
        },
        {

        },
        {

        },
        {

        }
    ];

    function getBrwoserLang() {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage.toLowerCase();
    };

    var getMobileErrorMessage = function(code, mode) {
        var _mode = ( typeof mode === 'undefined' || ''+mode == '' || mode > language.length ) ? type[getBrwoserLang()] : mode;
        var _message = language[_mode];
        for(var i in _message) {
            if(vest.error.mErrorCode[i] == code) {
                return _message[i];
            }
        }
        return 'undefined';
    };

    if (vest) {
        vest.error.getMobileErrorMessage = getMobileErrorMessage;
    }
})(window, vest);