//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2017-03-21.
 */
(function (window, vest, undefined) {
    'use strict';

    /**
     * Created by wjdcks725 on 2017-03-21.
     */
    var mErrorCode = {
        /* KoreaMintLib Error */
        KMError_READ_CERTIFICATE_FAILED: 10001,
        KMError_PARSE_CERTIFICATE_FAILED: 10002,

        KMError_INVALID_X509_FORMAT: 20002,
        KMError_INVALID_KEY_FORMAT: 20003,

        KMError_INVALID_PASSWORD: 30001,
        KMError_CERT_BAG_EMPTY: 30002,
        KMError_ADD_CERT_FAILED: 30004,
        KMError_PKCS12_MAKE_FAILED: 30005,
        KMError_PKCS12_ENCODE_FAILED: 30006,

        KMError_EXPORT_CERTIFICATE_CANCEL: 30007,

        KMError_OPERATION_GETCERTIFICATELIST: 90001,
        KMError_OPERATION_GETCERTIFICATE: 90002,
        KMError_OPERATION_GETPRIVATEKEY: 90003,
        KMError_OPERATION_CHANGEPASSWORD: 90004,
        KMError_OPERATION_IMPORTCERTIFICATE: 90005,
        KMError_OPERATION_DELETECERTIFICATE: 90006,
        KMError_OPERATION_VERIFYPIN: 90007
    };

    if (vest) {
        vest.error.mErrorCode = vest.error.mErrorCode || (vest.error.mErrorCode = {});
        vest.extend(vest.error, {
            'mErrorCode': mErrorCode
        });
    }
})(window, vest);
