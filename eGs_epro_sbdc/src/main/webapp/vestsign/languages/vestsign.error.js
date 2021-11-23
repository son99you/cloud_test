//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by nori on 2016. 1. 14..
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
            // Hibiscus ErrorCode
            // ChangePin.js
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "비밀번호 변경에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_EPKI_TO_PKI_FAILED: "비밀번호 변경에 실패했습니다.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_EPKI_FAILED: "비밀번호 변경에 실패했습니다.(PKCS8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_PKI_ENCRYPT_FAILED: '비밀번호 변경에 실패했습니다.(key encrypt)',
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SET_CERTIFICATE_FAILED: "비밀번호 변경에 실패했습니다.(import certificate)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "비밀번호 변경에 실패했습니다.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_KM_EPKI_TO_PKI_FAILED: "비밀번호 변경에 실패했습니다.(km pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_KM_EPKI_FAILED: "비밀번호 변경에 실패했습니다.(km pkcs8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_KMEPKI_ENCRYPT_FAILED: "비밀번호 변경에 실패했습니다.(km key encrypt)",

            // GenerateSignature.js
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CUSTOM_SID_FAILED: "세션이 만료되었거나 잘못되었습니다.(비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_DOUBLE_ENCPIN_DECRYPT_FAILED: "세션이 만료되었거나 잘못되었습니다.(비밀번호를 확인하세요) (enckey decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_ENCPIN_DECRYPT_FAILED: "세션이 만료되었거나 잘못되었습니다.(비밀번호를 확인하세요) (key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "전자서명에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_EPKI_TO_PKI_FAILED: "전자서명에 실패했습니다.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SIGN_FAILED: "전자서명에 실패했습니다.(GenerateSignature)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETENCRYPTRANDOM_FAILED: "전자서명에 실패했습니다.(GetEncryptRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETRANDOM_FAILED: "전자서명에 실패했습니다.(GetRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_GLOBAL_KEY_FAILED: "전자서명에 실패했습니다.(getGlobalKey)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_PFX_TO_CERT_FAILED: "비밀번호를 확인하세요. (pfx to cert)",

            // GetCertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",

            // GetCertificateList.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_GET_ITEM_FAILED: "인증서 목록이 존재하지 않습니다.",

            // ImportCertificate.js
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERT_FAILED: "입력값이 잘못되었습니다.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "비밀번호를 확인하세요.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "인증서 저장에 실패했습니다.(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "인증서 저장에 실패했습니다.(km key encrypt)", // kmKey = ?
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERTIFICATELIIST_FAILED: "인증서 저장에 실패했습니다.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "인증서 저장에 실패했습니다.(Set CertificateList)",

            // VerifyPin.js
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "비밀번호 확인에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_EPKI_TO_PKI_FAILED: "비밀번호 확인에 실패하였습니다.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "비밀번호 확인에 실패하였습니다.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_KM_EPKI_TO_PKI_FAILED: "비밀번호 확인에 실패하였습니다.(km pkcs8 decrypt)",

            // DeleteCertificate.js
            HIBISCUS_SERVICE_ERROR_DELETE_CERIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "인증서 삭제에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_EPKI_TO_PKI_FAILED: "인증서 삭제에 실패했습니다.(pksc8 decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_REMOVE_CERTIFICATE_FAILED: "인증서 삭제에 실패했습니다.(remove Certificate)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATELIST_FAILED: "인증서 삭제에 실패했습니다.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "인증서 삭제에 실패했습니다.(Set CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "인증서 삭제에 실패했습니다.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_KM_EPKI_TO_PKI_FAILED: "인증서 삭제에 실패했습니다.(km pksc8 decrypt)",

            // ImportP12.js
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_PFX_TO_CERT_FAILED: "PFX 또는 비밀번호가 잘못되었습니다.(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERT_FAILED: "입력값이 잘못되었습니다.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "인증서 저장에 실패했습니다.(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "인증서 저장에 실패했습니다.(km key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERTIFICATELIST_FAILED: "인증서 저장에 실패했습니다.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATELIST_FAILED: "인증서 저장에 실패했습니다.(Set CertificateList)",

            // CheckComplexPin.js
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_LENGTH_ERROR: "비밀번호는 10자 이상 30자 이하로 입력하세요.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_NOT_SAME: "비밀번호가 서로 동일하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_ERROR: "신규 비밀번호 생성 규칙:\n1. 1111 또는 aaaa와 같이 4번 이상 같은 문자 연속으로 사용 불가\n2. 1234 또는 abcd 같이 4번 연속된 문자 사용 불가\n3. ababab와 같이 3번 이상 두글자 연속으로 사용 금지\n4. abcabc와 같이 2번 이상 세글자 연속으로 사용 금지\n",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_SAME: "비밀번호가 서로 동일합니다.",

            // ExportCertificate.js
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_DECRYPT_FAILED: "인증서 내보내기에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "인증서 내보내기에 실패했습니다.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_CERT_TO_PFX_FAILED: "인증서 내보내기에 실패했습니다.(cert to pfx)",

            // VerifyVID.js
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "VID 검증에 실패했습니다.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_EPKI_TO_PKI_FAILED: "VID 검증에 실패했습니다.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_FAILED: "VID 검증에 실패했습니다.",

            // VerifySignature.js
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_PARAMETER: "입력값이 잘못되었습니다.(paramter)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS1_VERIFY_FAILED: "검증에 실패했습니다.(PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS1_VERIFY_FAILED: "검증에 실패했습니다.(KOSCOM PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS7_VERIFY_FAILED: "검증에 실패했습니다.(KOSCOM PKCS7)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS7_VERIFY_FAILED: "검증에 실패했습니다.(PKCS7)",

            // GetCACertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_BYTES_FAILED: "인증서 정보를 가져올 수 없습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_MATCHING_DN_FAILED: "CA 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_SERVER_FAILED: "CA 인증서가 서버에 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_BYTES_FAILED: "CA 인증서 정보를 가져올 수 없습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_MATCHING_DN_FAILED: "ROOT CA 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_SERVER_FAILED: "ROOT CA 인증서가 서버에 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "ROOT CA 인증서 정보를 가져올 수 없습니다.(getRootHash)",

            // ValidateCertificate.js
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",

            // OpenCertInit.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_DECRYPT_API_KEY_FAILED: "공동/클라우드 저장소 초기화에 실패했습니다.(api key decrypt)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_FAILED: "공동/클라우드 저장소 초기화에 실패했습니다.(opencert init)",

            // OpenCertSync.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_MERGE_CERTINFOS_FAILED: "공동/클라우드 저장소 동기화에 실패했습니다.(opencert sync)",

            // OpenCertGetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_OPENCERT_PASSWORD_FAILED: "공동/클라우드 저장소부터 인증서를 가져오는데 실패했습니다.(api password 생성 실패)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_FAILED: "공동/클라우드 저장소로부터 인증서를 가져오는데 실패했습니다.(비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_ENCRYPTED_PKCS12_FAILED: "공동/클라우드 저장소로부터 인증서를 가져오는데 실패했습니다.(pkcs12 decrypt)",

            // OpenCertSetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_PFX_TO_CERT_FAILED: "비밀번호를 확인하세요(pfx to cert).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_EPKI_TO_PKI_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_CERT_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GENERATE_HASH_HEX_DATA_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(getRootHash).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_OPENCERT_PASSWORD_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(api password 생성 실패).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(opencert set pkcs12).",

            // OpenCertRemoveP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_FAILED: "공동/클라우드 저장소의 인증서를 삭제하는데 실패했습니다(opencert remove pkcs12).",

            // OpenCertSetEvent.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_EVENT_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",

            // OpenCertSetCertificate.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_EPKI_TO_PKI_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_CERT_TO_PFX_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(cert to pfx).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_CERT_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(hash data 생성 실패).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_OPENCERT_PASSWORD_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(api password 생성 실패).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_SET_P12_FAILED: "공동/클라우드 저장소에 인증서를 저장하는데 실패했습니다(opencert set pkcs12).",
        
            // GetCertInfos.js
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_FAILED: "인증서 목록이 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_CERT_FAILED: "로컬 저장소의 인증서 정보를 불러오는데 실패했습니다.(Certificate)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GENERATE_HASH_HEX_DATA_FAILED: "로컬 저장소의 인증서 정보를 불러오는데 실패했습니다.(hash data 생성 실패)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_NOT_FOUND_CERTIFICATE: "인증서가 존재하지 않습니다.(null)",

            // OpenCertGetFP.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.(null)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GENERATE_HASH_HEX_DATA_FAILED: "해당 인증서의 fp 값을 얻는데 실패했습니다.(hash data 생성 실패)",

            // OpenCertVerifyVID.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_PFX_TO_CERT_FAILED: "PFX 또는 비밀번호가 잘못되었습니다.(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_EPKI_TO_PKI_FAILED: "비밀번호를 확인하세요.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_FAILED: "VID 검증에 실패했습니다.",

            // Envelope.js
            HIBISCUS_SERVICE_ERROR_ENVELOPE_INVALID_JSON_DATA: "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_ENVELOPE_GET_CERT_FAILED: "입력값이 잘못되었습니다.(잘못된 인증서 형식)",
            HIBISCUS_SERVICE_ERROR_ENVELOPE_DN_MUST_BE_INCLUDED_IN_JSON_DATA: "DN이 입력되지 않았습니다",
            HIBISCUS_SERVICE_ERROR_ENVELOPE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_ENVELOPE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_ENVELOPE_FAILED: "전자봉투에 실패했습니다.",

            // Deenvelope.js
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_INVALID_JSON_DATA:  "입력값이 잘못되었습니다.",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_GET_CERTIFICATE_MATCHING_DN_FAILED: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_GET_ITEM_NOT_FOUND_CERTIFICATE: "해당 인증서가 존재하지 않습니다.",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "전자봉투 복호화에 실패했습니다. (비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_EPKI_TO_PKI_FAILED:  "전자봉투 복호화에 실패했습니다. (비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "키분배용 인증서 전자봉투 복호화에 실패했습니다. (비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_KM_EPKI_TO_PKI_FAILED: "키분배용 인증서 전자봉투 복호화에 실패했습니다. (비밀번호를 확인하세요)",
            HIBISCUS_SERVICE_ERROR_DEENVELOPE_FAILED: "전자봉투 복호화에 실패했습니다.",

            // VestCert ErrorCode
            ServiceError_UNKNOWN: "아직 등록되지 않은 오류코드",
            ServiceError_SERVICE_REJECTED: "올바른 MangoWire 메시지가 아니므로, 서비스가 거절되었습니다.",
            ServiceError_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "지원하는 언어가 아닙니다.",

            ServiceError_TOKEN_NOT_INITIALIZED: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_TOKEN_NOT_FOUND: "보안디스크가 존재하지 않습니다.",
            ServiceError_TOKEN_BAD: "보안디스크의 상태가 비정상입니다. 초기화 하세요.",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikey가 설치되지 않았습니다. 프로그램을 설치 해주세요.",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikey가 최신 버전이 아닙니다. 프로그램을 업데이트 해주세요.",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikey 옵션이 유효하지 않습니다.",
            ServiceError_TOKEN_NOT_RECOGNIZED: "허용된 토큰이 아닙니다.",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "토큰에서 해당기능을 지원하지 않습니다.",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSL 서비스 초기화에 실패하였습니다.",

            ServiceError_SESSIONID_NOT_EXIST: "세션이 만료되었거나 잘못되었습니다. 다시 접속하세요.",
            ServiceError_SESSION_IS_USING: "다른 곳에서 세션이 사용중입니다. 다시 접속하세요.",

            ServiceError_OPERATION_NOT_EXPECTED: "현재 이 기능을 수행할 수 없습니다.",
            ServiceError_OPERATION_NOT_SUPPORTED: "지원되지 않는 기능입니다.",

            ServiceError_INVALID_INPUT: "입력값이 잘못되었습니다.",
            ServiceError_INVALID_INPUT_TOKENID: "토큰 식별자가 잘못되었습니다.",

            ServiceError_NO_SSL_CERTIFICATE: "등록된 SSL 인증서가 존재하지 않습니다.",
            ServiceError_CERTIFICATE_NOT_FOUND: "인증서가 존재하지 않습니다.",


            ServiceError_DELETE_CERTIFICATE_FAILED: "인증서 삭제에 실패했습니다.(기타 에러)",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "Program files에 저장된 인증서는 삭제할 수 없습니다.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PWD_INCORRECT: "인증서 삭제에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_DELETE_PIN_INCORRECT: "인증서 삭제에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "인증서 삭제에 실패했습니다(PIN 번호 입력을 취소했습니다).",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "인증서 삭제에 실패했습니다(비밀번호 입력을 취소했습니다).",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "키쌍 생성이 사용자에 의해 취소되었습니다.",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "비밀번호가 잘못되어 키쌍 생성에 실패하였습니다.",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "비밀번호가 잘못되어 장치가 잠겨, 키쌍 생성에 실패하였습니다.",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "비밀번호가 잘못되어 키쌍 생성에 실패하였습니다.",

            ServiceError_GENERATE_SIGNATURE_NOT_MATCHED_KEY_PAIR: "저장된 인증서의 키쌍이 다릅니다.",
            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "전자서명에 실패하였습니다.",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "전자서명에 실패하였습니다(식별번호 생성에 실패).", //"encrypt VID Random failed(0x%x, false)"
            ServiceError_GENERATE_SIGNATURE_CANCELED: "비밀번호 입력이 사용자에 의해 취소 되었습니다.",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "통합보안토큰의 패스워드가 잘못되었습니다.",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom 서명은 인증서가 있어야합니다.",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_NOT_CERTIFICATE: "전자서명에 실패하였습니다. \n(인증서 키가 변경되어 해당 인증서를 지우고 다시 가져와야 합니다)",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKey 서비스가 초기화 되지 않았습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "PIN 번호를 확인하세요.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "PIN 입력을 취소했습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "비밀번호를 확인하세요.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikey 서비스를 취소했습니다.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ERROR: "파일 읽기에 실패했습니다(경로를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ERROR: "파일 쓰기에 실패했습니다(경로를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ACCESS_DENIED: "권한 문제로 인해 파일 읽기에 실패했습니다.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ACCESS_DENIED: "권한 문제로 인해 파일 쓰기에 실패했습니다.",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "인증서 가져오기를 실패하였습니다.",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "인증서를 찾을 수 없습니다.",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "잘못된 session ID가 입력되었습니다.",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "session ID가 NULL로 입력되었습니다.",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "session 생성에 실패했습니다.",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "인증서 비밀번호 확인에 실패했습니다.(기타 에러)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_VERIFY_PIN_FAILED: "비밀번호 확인에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "비밀번호 확인에 실패했습니다(서명용 인증서와 암호화용 인증서의 비밀번호가 일치하지 않습니다).",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "서버에 접속할 수 없습니다.",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "인증서 발급에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "PKCS#5 암호화에 실패했습니다.",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 메시지 구성에 실패했습니다.",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "발급된 인증서 저장에 실패했습니다.(initialize 실패)",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "발급된 전자서명용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "발급된 키분배용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "CA 공개키 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "발급된 인증서 저장에 실패했습니다.(finalize 실패)",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "과금 발급은 현재 지원되지 않습니다.",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "ICCard가 지원하지 않는 인증서입니다.",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "인증서 갱신에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "갱신할 인증서를 가져오는데 실패했습니다.",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "갱신할 인증서를 추가하는데 실패했습니다.",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "갱신할 키파일을 추가하는데 실패했습니다.",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "PKCS#5 암호화에 실패했습니다.",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 메시지 구성에 실패했습니다.",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.",
            ServiceError_CMP_UPDATE_INVALID_PWD: "비밀번호를 확인하세요.",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "인증서 가져오기 초기화에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "서명용 인증서 가져오기에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "암호용 인증서 가져오기에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "인증기관 인증서 가져오기에 실패하였습니다.",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "인증서 가져오기에 실패하였습니다.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "과금 갱신은 현재 지원되지 않습니다.",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "인증서를 갱신할 수 있는 기간이 아닙니다. 인증서 갱신은 만료 1개월 전 부터 가능합니다.",
            ServiceError_CMP_UPDATE_INVALID_PIN: "PIN 번호를 확인하세요.",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "인증서 폐기에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "폐기할 인증서를 가져오는데 실패했습니다.",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "폐기할 인증서를 추가하는데 실패했습니다.",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "폐기할 키파일을 추가하는데 실패했습니다.",
            ServiceError_CMP_REVOKE_INVALID_PWD: "비밀번호를 확인하세요.",
            ServiceError_CMP_REVOKE_INVALID_PIN: "PIN 번호를 확인하세요.",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PIN이 잠겼습니다.",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "단말 식별 값을 가져오지 못했습니다(Windows 외 타 OS는 추후 지원합니다).",
            ServiceError_GET_PCIDENTITY_FAILED: "단말 식별 값을 가져오지 못했습니다(기타 에러).",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "비밀번호 변경에 실패했습니다(인증서 형식에 문제가 발생했습니다).",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "비밀번호 변경에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "비밀번호 변경에 실패했습니다(인증서를 저장할 때 문제가 발생했습니다).",
            ServiceError_CHANGE_PIN_FAILED: "비밀번호 변경에 실패하였습니다(기타 에러).",
            ServiceError_CHANGE_PIN_FAILED_PROGRAM_FILES_PATH_WARNING: "Program files에 저장된 인증서 비밀번호는 변경할 수 없습니다.",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "인증서 내보내기를 취소했습니다.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "인증서 내보내기에 실패했습니다(인증서 형식에 문제가 발생했습니다).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "인증서 내보내기에 실패했습니다(인증서를 찾지 못했습니다).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "인증서 내보내기에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "인증서 내보내기에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "인증서 내보내기에 실패했습니다(add certificate fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "인증서 내보내기에 실패했습니다(encode pfx fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "인증서 내보내기에 실패했습니다(기타 에러).",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "인증서 가져오기에 실패했습니다(인증서 선택을 취소했습니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "인증서 가져오기에 실패했습니다(비밀번호 입력을 취소했습니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "인증서 가져오기에 실패했습니다(PFX 형식의 인증서가 아닙니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PWD: "인증서 가져오기에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "인증서 가져오기에 실패했습니다(기타 에러).",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "유효하지 않은 CERTINDENTIFIER 입니다.",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "비밀번로 입력이 취소되었습니다.",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "입력된 매체는 사용할 수 없는 매체입니다.",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "인증서 저장매체 변경에 실패했습니다(비밀번호 입력을 취소했습니다).",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "인증서 저장매체 변경에 실패했습니다.",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "인증서 저장매체 변경에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "인증서 저장매체 변경에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "변경할 인증서 저장매체가 같습니다.",
            ServiceError_CHANGE_STORAGE_FAILED: "인증서 저장매체 변경에 실패했습니다(기타 에러).",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "인증서 형식이 잘못되었습니다.",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "인증서 검증에 실패했습니다.",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "인증서 유효성 검증에 실패했습니다(기타 에러).",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "session id가 없어 session 저장에 실패했습니다.",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "잘못된 tray 목록입니다.",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "잘못된 tray operate 동작입니다.",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "원문이 필요한 전자서명입니다.",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "아직 지원되지 않는 전자서명입니다.",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "X509 인증서형태가 아닙니다.",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "public key 형태가 아닙니다.",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "서명검증에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "파일 읽기에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "파일 쓰기에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_ACCESS_DENIED: "권한 문제로 인해 파일 읽기에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_ACCESS_DENIED: "권한 문제로 인해 파일 쓰기에 실패했습니다.",

            ServiceError_VERIFY_VID_INVALID_CERTID: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_INVALID_KEYID: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_INVALID_IDN: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_VERIFY_VID_NOT_FOUND: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "X509 인증서형태가 아닙니다.",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "random을 가져오는데 실패했습니다.",
            ServiceError_VERIFY_VID_INVALID_PWD: "비밀번호를 확인하세요.",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "VID 검증에 실패했습니다.",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "유효하지 않은 입력입니다.",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "유효하지 않은 알고리즘입니다.",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "지원하지 않은 hash 알고리즘입니다.",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digest를 실패했습니다(파일을 찾을 수 없습니다).",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digest를 실패했습니다(파일을 읽는데 오류가 발생했습니다).",
            ServiceError_GET_HASH_FAILED: "message digest를 실패했습니다(기타 에러).",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "유효하지 않은 입력입니다.",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "암호화 키가 null 입니다.",
            ServiceError_ENCRYPT_FAILED_IV_IS_NULL: "암호화 IV가 유효하지 않습니다.",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "지원하지 않는 키 길이 입니다.",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "지원하지 않은 알고리즘입니다.",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "지원하지 않는 암호화 모드입니다.",
            ServiceError_ENCRYPT_FAILED: "암호화에 실패하였습니다.",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "유효하지 않은 입력입니다.",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "복호화 키가 null 입니다.",
            ServiceError_DECRYPT_FAILED_IV_IS_NULL: "복호화 IV가 유효하지 않습니다.",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "지원하지 않는 키 길이 입니다.",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "지원하지 않은 알고리즘입니다.",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "지원하지 않는 복호화 모드입니다.",
            ServiceError_DECRYPT_FAILED: "복호화에 실패하였습니다.",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "전자봉투 입력 값이 유효하지 않습니다.",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "전자봉투를 실패했습니다(입력한 인증서가 x509가 아닙니다).",
            ServiceError_ENVELOPE_FAILED: "전자봉투를 실패했습니다(기타 에러).",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ERROR: "파일 읽기에 실패했습니다(경로를 확인하세요).",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ERROR: "파일 쓰기에 실패했습니다(경로를 확인하세요).",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "권한 문제로 인해 파일 읽기에 실패했습니다.",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "권한 문제로 인해 파일 쓰기에 실패했습니다.",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "전자봉투 복호화의 입력값이 유효하지 않습니다.",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "전자봉투 복호화에 실패했습니다(입력을 취소했습니다).",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "전자봉투 복호화에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "전자봉투 복호화에 실패했습니다(비밀번호를 취소했습니다).",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: "파일 읽기에 실패했습니다.",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: '파일 쓰기에 실패했습니다.',
            ServiceError_DEENVELOPE_FAILED: "전자봉투 복호화에 실패했습니다.",
            ServiceError_DEENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "권한 문제로 인해 파일 읽기에 실패했습니다.",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "권한 문제로 인해 파일 쓰기에 실패했습니다.",
            ServiceError_DEENVELOPE_FAILED_INVALID_TEXT: "전자봉투 복호화에 실패했습니다(암호문이 손상되거나 파일의 위치가 잘못되었습니다).",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "PFX 전자서명에 실패했습니다(기타 에러).",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "PFX 전자서명에 실패했습니다(선택을 취소했습니다).",

            /* import, export certificate and key */
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INVALID_ARGUMENT: "인증서 내보내기에 실패했습니다.(입력값이 잘못되었습니다)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INPUT_CANCELED: "인증서 내보내기에 실패했습니다.(비밀번호 입력을 취소했습니다)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_PRIVATEKEY_FAILED: "인증서 내보내기에 실패했습니다.(개인키를 찾지 못했습니다)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_CERTIFICATE_FAILED: "인증서 내보내기에 실패했습니다.(인증서를 찾지 못했습니다)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_INCORRECT: "인증서 내보내기에 실패했습니다.(PIN 번호를 확인하세요)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_LOCKED: "인증서 내보내기에 실패했습니다.(장치가 잠겼습니다)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PWD_INCORRECT: "인증서 내보내기에 실패했습니다.(비밀번호를 확인하세요)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED: "인증서 내보내기에 실패했습니다.",

            /* mac address error message */
            ServiceError_MAC_ADDRESS_ERROR: "MAC주소 가져오기에 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_HANDLE: "DLL 핸들 가져오기에 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_FUNC: "DLL 함수 가져오기에 실패했습니다.",
            ServiceError_MAC_ADDRESS_NONVALIDATED_RETURN_FUNC: "함수 반환값이 유효하지 않습니다.",
            ServiceError_MAC_ADDRESS_FAILED_MEMORY: "메모리를 할당하는데 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_REG_OPEN: "레지스트리를 여는데 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_GET_REG_VALUE: "레지스트리 값을 얻는데 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_REG_CLOSE: "레지스트리를 닫는데 실패했습니다.",
            ServiceError_MAC_ADDRESS_FAILED_GET_LAST_NETID: "NetId를 가져오는데 실패했습니다.",

            /* getFilePath */
            ServiceError_GET_FILE_PATH_CANCELED: "파일 선택을 취소했습니다.",

            /* securedisk */
            ServiceError_SECUREDISK_BACKUP_ERROR: "안전디스크 백업 및 복구에 실패하였습니다.",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_CERTINDENTIFIER: "입력 값이 잘못되었습니다.(인증서).",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_KEYINDENTIFIER: "키 값이 잘못되었습니다.",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_TOKENINDENTIFIER: "토큰 값이 잘못되었습니다.",
            ServiceError_SECUREDISK_BACKUP_INVALID_VALUE: "값이 유효하지 않습니다.",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_BACKUPCERTLIST: "백업 인증서 리스트를 만드는데 실패했습니다.",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_SECURETOKEN: "안전디스크를 찾는데 실패하였습니다.",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_HANDLE: "입력 핸들이 존재하지 않습니다.",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_DATA: "데이터가 잘못되었거나 존재하지 않습니다.",
            ServiceError_SECUREDISK_BACKUP_INVALID_RANGE: "데이터의 범위가 잘못되었습니다.",
            ServiceError_SECUREDISK_BACKUP_INVALID_PIN: "비밀번호가 유효하지 않습니다.",
            ServiceError_SECUREDISK_BACKUP_FAILED_CASTING: "변환에 실패하였습니다.",

            /* pin complex check */
            ServiceError_CHECK_COMPLEX_PIN_ERROR: '비밀번호 무결성 검사 에러',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PINS: '값이 입력되지 않았습니다',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: '입력한 두개의 비밀번호가 일치하지 않습니다.',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SHORT_LENGTH: '10자 이상의 신규 인증서 비밀번호를 입력 하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_SAME_PWD: '신규 인증서 비밀번호와 이전 인증서 비밀번호가 동일합니다.',
            ServiceError_CHECK_COMPLEX_PIN_GETPIN_FAILED: '입력값 획득에 실패 했습니다(키보드 보안 연동 오류)',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PIN: '입력한 비밀번호가 생성규칙에 위배됩니다.',
            ServiceError_CHECK_COMPLEX_PIN_UPDATE_ERROR: '안전성강화를 위해 갱신 인증서 비밀번호를 10자 이상으로 변경해 주십시오.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_CHAR: '숫자와 특수문자를 하나 이상 포함하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_NUMBER: '영문자와 특수문자를 하나 이상 포함하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_SPECIAL: '숫자와 영문자를 하나 이상 포함하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_NO_CHAR: '영문자를 하나 이상 포함하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_NO_NUMBER: '숫자를 하나 이상 포함하십시오.',
            ServiceError_CHECK_COMPLEX_PIN_NO_SPECIAL: '특수문자를 하나 이상 포함하십시오(\", \', \\, \| 제외)',
            ServiceError_CHECK_COMPLEX_PIN_PATTERN: "신규 비밀번호 생성 규칙:\n1. 1111 또는 aaaa와 같이 4번 이상 같은 문자 연속으로 사용 불가\n2. 1234 또는 abcd 같이 4번 연속된 문자 사용 불가\n3. ababab와 같이 3번 이상 두글자 연속으로 사용 금지\n4. abcabc와 같이 2번 이상 세글자 연속으로 사용 금지\n",

            /* keyboardProtection */
            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "키보드보안 연동 실패했습니다.",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "키보드보안 초기화 실패했습니다.",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "입력값 획득에 실패 했습니다(키보드 보안 연동 오류).",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "키보드보안 공개키 획득에 실패했습니다.",

            /* mobile usim */
            ServiceError_MOBILE_USIM_NOT_PRESENT: "유심칩이 존재하지 않습니다.",
            ServiceError_TOKEN_MOBILE_USIM_INVALID_OPTIONS: "전자봉투를 위한 입력값이 유효하지 않습니다.",
            ServiceError_MOBILE_USIM_USER_CANCELED: "스마트인증이 취소되었습니다.",

            /* mobisign */
            ServiceError_MOBISIGN_INVALID_OPTIONS: "모비싸인의 옵션이 잘못되었습니다.",
            ServiceError_MOBISIGN_USER_CANCELED: "모비싸인이 취소되었습니다.",
            ServiceError_MOBISIGN_NOT_LOADED: "모비싸인 모듈 로딩에 실패했습니다.",

            /* relay.raon */
            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "인증서 복사에 실패했습니다(인증번호 요청이 필요합니다).",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "인증서 복사에 실패했습니다(인증번호 요청에 실패했습니다).",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "인증서 복사에 실패했습니다(전송된 인증서가 없습니다).",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "인증서 복사에 실패했습니다(보내는 단말에 인증번호를 입력해주세요).",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "인증서 복사에 실패했습니다(인증서 내보내기에 실패했습니다).",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "인증서 복사에 실패했습니다(인증번호가 일치하지 않습니다.)",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "인증서 복사에 실패했습니다(지원되지 않는 토큰입니다.).",
            // 금보원 인증서 탈취 취약점 관련 수정사항
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_INPUT_CANCELED: "비밀번호 입력이 사용자에 의해 취소 되었습니다.",
            ServiceError_RELAY_RAON_CERTIFICATE_INVALID_CERTINDENTIFIER: "해당 인증서를 찾을 수 없습니다.",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED: "인증서 복사에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "인증서 복사에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_RELAY_RAON_TOKEN_FUNCTION_NOT_SUPPORTED: "인증서 복사에 실패했습니다(지원되지 않는 매체입니다).",
            ServiceError_RELAY_RAON_IVALID_REF_NUM: "인증서 복사에 실패했습니다(승인번호 검증에 실패했습니다).",

            /* certificateSynchronize */
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_CERTIDNTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INPUT_CANCELED: "비밀번호 입력이 사용자에 의해 취소 되었습니다.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED: "비밀번호 변경에 실패했습니다.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_SIGN_PWD_FAILED: "비밀번호 변경에 실패했습니다(서명용 비밀번호를 확인하세요)",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_KM_PWD_FAILED: "비밀번호 변경에 실패했습니다(키분배용 비밀번호를 확인하세요)",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_FIND_KMCERTIFICATE: "키분배용 인증서 존재하지 않습니다.",

            /* script */
            ScriptError_NOT_UNDEFINED_ERROR: "정의되지 않은 에러가 발생했습니다.",
            ScriptError_INCORRECT_TYPE_ERROR: "잘못된 형식의 에러가 발생했습니다.",

            ScriptError_SIGNATURE_P7_ERROR: "전자서명에 실패했습니다(P7).",
            ScriptError_SIGNATURE_P1_ERROR: "전자서명에 실패했습니다(P1).",
            ScriptError_VERIFY_P7_MAKESIGNEDDATA_ERROR: "검증에 실패했습니다(올바른 서명문 아님).",
            ScriptError_VERIFY_P7_ERROR: "검증에 실패했습니다(P7).",
            ScriptError_VERIFY_P1_ERROR: "검증에 실패했습니다(P1).",

            ScriptError_DECRYPTKEY_ERROR: "비밀번호를 확인하세요(_decryptKey).",
            ScriptError_ENCRYPTKEY_ERROR: "개인키 암호화에 실패했습니다(_encryptKey).",

            ScriptError_IMPORT_FILE_IS_NOT_P12: "인증서 가져오기에 실패했습니다(PFX 형식의 인증서가 아닙니다).",
            ScriptError_IMPORT_INCORRECT_PIN: "인증서 가져오기에 실패했습니다(비밀번호를 확인하세요).",
            ScriptError_IMPORT_FILE_IS_NOT_NPKI: "인증서 가져오기에 실패했습니다(인증서 형식 아닙니다).",

            ScriptError_CANCEL_BUTTON_EVENT: "취소 버튼에 대한 이벤트가 발생했습니다.",

            ScriptError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: '입력한 두개의 비밀번호가 일치하지 않습니다.',
            ScriptError_CHECK_COMPLEX_PIN_PATTERN: "신규 비밀번호 생성 규칙:\n1. 10자 이상\n2. 숫자, 영소문자, 특수문자 반드시 포함(\", \', \\, \| 제외)\n3. 영문자는 대/소문자 구분됨\n4. 1111 또는 aaaa와 같이 4번 이상 같은 문자 연속으로 사용 불가\n5. 1234 또는 abcd 같이 4번 연속된 문자 사용 불가\n6. ababab와 같이 3번 이상 두글자 연속으로 사용 금지\n7. abcabc와 같이 2번 이상 세글자 연속으로 사용 금지\n",
            ScriptError_CHECK_COMPLEX_PIN_SAME_PWD: '신규 인증서 비밀번호와 이전 인증서 비밀번호가 동일합니다.'
        },
        {
            // Hibiscus ErrorCode
            // ChangePin.js
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "Password change failed.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_EPKI_TO_PKI_FAILED: "Password change failed.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_EPKI_FAILED: "Password change failed.(PKCS8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_PKI_ENCRYPT_FAILED: 'Password change failed.(key encrypt)',
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SET_CERTIFICATE_FAILED: "Password change failed.(import certificate)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "Password change failed.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_KM_EPKI_TO_PKI_FAILED: "Password change failed.(km pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_KM_EPKI_FAILED: "Password change failed.(km pkcs8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_KMEPKI_ENCRYPT_FAILED: "Password change failed.(km key encrypt)",
            
            // GenerateSignature.js
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CUSTOM_SID_FAILED: "Session invalid.",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_DOUBLE_ENCPIN_DECRYPT_FAILED: "Session invalid.(enckey decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_ENCPIN_DECRYPT_FAILED: "Session invalid.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "The generate signature failed.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_EPKI_TO_PKI_FAILED: "The generate signature failed.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SIGN_FAILED: "The generate signature failed.(GenerateSignature)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETENCRYPTRANDOM_FAILED: "The generate signature failed.(GetEncryptRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETRANDOM_FAILED: "The generate signature failed.(GetRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_GLOBAL_KEY_FAILED: "The generate signature failed.(getGlobalKey)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_PFX_TO_CERT_FAILED: "Please check your password.(pfx to cert)",

            // GetCertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            
            // GetCertificateList.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_GET_ITEM_FAILED: "The list of certificates does not exist.",
            
            // ImportCertificate.js
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERT_FAILED: "Invalid input.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "Please check your password.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "Failed to save certificate.(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "Failed to save certificate.(km key encrypt)", // kmKey = ?
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATE_FAILED: "Failed to save certificate.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERTIFICATELIIST_FAILED: "Failed to save certificate.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "Failed to save certificate.(Set CertificateList)",

            // VerifyPin.js
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "Failed to confirm password.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_EPKI_TO_PKI_FAILED: "Failed to confirm password.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "Failed to confirm password.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_KM_EPKI_TO_PKI_FAILED: "Failed to confirm password.(km pkcs8 decrypt)",

            // DeleteCertificate.js
            HIBISCUS_SERVICE_ERROR_DELETE_CERIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "Failed to delete certificate.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_EPKI_TO_PKI_FAILED: "Failed to delete certificate.(pksc8 decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_REMOVE_CERTIFICATE_FAILED: "Failed to delete certificate.(remove Certificate)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATELIST_FAILED: "Failed to delete certificate.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "Failed to delete certificate.(Set CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "Failed to delete certificate.(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_KM_EPKI_TO_PKI_FAILED: "Failed to delete certificate.(km pksc8 decrypt)",

            // ImportP12.js
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_PFX_TO_CERT_FAILED: "PFX or password confirmation failed.(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERT_FAILED: "Invalid input.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "Failed to save certificate.(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "Failed to save certificate.(km key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATE_FAILED: "Failed to save certificate.(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERTIFICATELIST_FAILED: "Failed to save certificate.(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATELIST_FAILED: "Failed to save certificate.(Set CertificateList)",

            // CheckComplexPin.js
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_LENGTH_ERROR: "Please enter a password between 10 characters and 30 characters.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_NOT_SAME: "The passwords are not the same.",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_ERROR: "New password creation rules:\n1. The same character can not be used continuously for more than 4 times, such as 1111 or aaaa\n2. not for use fourth consecutive characters like 1234 or abcd\n3. not for use uses more than three times in two consecutive letters, such as ababab.\n4. Not for use more than once in three consecutive letters, such as abcabc\n",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_SAME: "The password is the same.",

            // ExportCertificate.js
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_DECRYPT_FAILED: "Certificate export failed.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "Certificate export failed.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_CERT_TO_PFX_FAILED: "Certificate export failed.(cert to pfx)",

            // VerifyVID.js
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "VID verification failed.(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_EPKI_TO_PKI_FAILED: "VID verification failed.(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_FAILED: "VID verification failed.",

            // VerifySignature.js
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_PARAMETER: "Invalid input.(paramter)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS1_VERIFY_FAILED: "Verification failed.(PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS1_VERIFY_FAILED: "Verification failed.(KOSCOM PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS7_VERIFY_FAILED: "Verification failed.(KOSCOM PKCS7)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS7_VERIFY_FAILED: "Verification failed.(PKCS7)",

            // GetCACertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_BYTES_FAILED: "Could not get certificate information.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_MATCHING_DN_FAILED: "The CA certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_SERVER_FAILED: "The CA certificate does not exist on the server.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_BYTES_FAILED: "Could not get CA certificate information.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_MATCHING_DN_FAILED: "The ROOT CA certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_SERVER_FAILED: "The ROOT CA certificate does not exist on the server.",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "ROOT CA certificate information could not be retrieved.(getRootHash)",

            // ValidateCertificate.js
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            
            // OpenCertInit.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_DECRYPT_API_KEY_FAILED: "Co-located / cloud storage initialization failed.(api key decrypt)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_FAILED: "Co-located / cloud storage initialization failed.(opencert init)",

            // OpenCertSync.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_MERGE_CERTINFOS_FAILED: "Co-located / cloud storage sync failed.(opencert sync)",

            // OpenCertGetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_OPENCERT_PASSWORD_FAILED: "Failed to get certificate from co-located / cloud storage.(Failed to generate api password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_FAILED: "Failed to get certificate from co-located / cloud storage.(Check your password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_ENCRYPTED_PKCS12_FAILED: "Failed to get certificate from co-located / cloud storage.(pkcs12 decrypt)",

            // OpenCertSetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_PFX_TO_CERT_FAILED: "Please check your password.(pfx to cert).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_EPKI_TO_PKI_FAILED: "Failed to store certificates in co-located / cloud storage(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_CERT_FAILED: "Failed to store certificates in co-located / cloud storage(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GENERATE_HASH_HEX_DATA_FAILED: "Failed to store certificates in co-located / cloud storage(getRootHash).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_OPENCERT_PASSWORD_FAILED: "Failed to store certificates in co-located / cloud storage(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_FAILED: "Failed to store certificates in co-located / cloud storage(opencert set pkcs12).",

            // OpenCertRemoveP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_FAILED: "Failed to delete certificates from co-located / cloud storage(opencert remove pkcs12).",

            // OpenCertSetEvent.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_EVENT_INVALID_JSON_DATA: "Invalid input.",

            // OpenCertSetCertificate.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_EPKI_TO_PKI_FAILED: "Failed to store certificates in co-located / cloud storage(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_CERT_TO_PFX_FAILED: "Failed to store certificates in co-located / cloud storage(cert to pfx).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_CERT_FAILED: "Failed to store certificates in co-located / cloud storage(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "Failed to store certificates in co-located / cloud storage(Failed to generate hash data).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_OPENCERT_PASSWORD_FAILED: "Failed to store certificates in co-located / cloud storage(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_SET_P12_FAILED: "Failed to store certificates in co-located / cloud storage(opencert set pkcs12).",
        
            // GetCertInfos.js
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_INVALID_JSON_DATA:  "Invalid input.",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_FAILED: "The list of certificates does not exist.",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_CERT_FAILED: "Failed to fetch certificate information from local store.(Certificate)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GENERATE_HASH_HEX_DATA_FAILED: "Failed to fetch certificate information from local store.(Failed to generate hash data)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_NOT_FOUND_CERTIFICATE: "Certificate does not exist.(null)",

            // OpenCertGetFP.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_CERTIFICATE_MATCHING_DN_FAILED: "That certificate does not exist.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_ITEM_NOT_FOUND_CERTIFICATE: "That certificate does not exist.(null)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GENERATE_HASH_HEX_DATA_FAILED: "Failed to get fp value for that certificate.(Failed to generate hash data)",
            
            // OpenCertVerifyVID.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_INVALID_JSON_DATA: "Invalid input.",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_PFX_TO_CERT_FAILED: "PFX or password confirmation failed.(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_EPKI_TO_PKI_FAILED: "Please check your password",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_FAILED: "VID verification failed.",

            // VestCert ErrorCode
            ServiceError_UNKNOWN: "The error code is not registered yet",
            ServiceError_SERVICE_REJECTED: "Service has bveen rejected becasue of invalid MangoWiore message.",
            ServiceError_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "It is not a supported language.",

            ServiceError_TOKEN_NOT_INITIALIZED: "SecureDisk is not initialized.",
            ServiceError_TOKEN_NOT_FOUND: "SecureDisk does not exist.",
            ServiceError_TOKEN_BAD: "SecureDisk is abnormal, Please initialized first.",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikey is not installed. Please install the program.",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikey is not the latest version. Please update your program.",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikey option value is invalid.",
            ServiceError_TOKEN_NOT_RECOGNIZED: "Token not allowed.",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "It does not support that feature in the token.",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSL Service initialization failed.",

            ServiceError_SESSIONID_NOT_EXIST: "The session expires or is invalid. Please connect again.",
            ServiceError_SESSION_IS_USING: "The session is in use elsewhere. Please connect again.",

            ServiceError_OPERATION_NOT_EXPECTED: "Currently, you can not perform this function.",
            ServiceError_OPERATION_NOT_SUPPORTED: "This function is not supported.",

            ServiceError_INVALID_INPUT: "Invalid input.",
            ServiceError_INVALID_INPUT_TOKENID: "The token identifier is invalid.",

            ServiceError_NO_SSL_CERTIFICATE: "Registered SSL certificate does not exist.",
            ServiceError_CERTIFICATE_NOT_FOUND: "Certificate does not exist.",


            ServiceError_DELETE_CERTIFICATE_FAILED: " Failed to delete the certificate (other error)",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "Certificate stored in the [Program files] can not be deleted.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PWD_INCORRECT: "Delete a certificate failed (check password).",
            ServiceError_DELETE_PIN_INCORRECT: "It failed to delete certificate (Make a PIN number).",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "Failed to delete certificate (canceled the PIN number).",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "Failed to delete certificate (canceled the password).",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "Invalid input.",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "Invalid input.",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "Generate key pair has been canceled by the user.",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "Failed to generate key pairs password is incorrect.",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "The key-pair generation failed, because the device is locked by wrong password.",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "The password is incorrect Failed to generate key pairs.",

            ServiceError_GENERATE_SIGNATURE_NOT_MATCHED_KEY_PAIR: "Different key sets of stored certificates.",
            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "Unexpected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "It failed to electronic signatures.",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "SecureDisk is not initialized.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "Enter Password of the certificate is incorrect.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "t failed to electronic signature (VID Random Number generated on failure).", //"encrypt VID Random failed(0x%x, false)"
            ServiceError_GENERATE_SIGNATURE_CANCELED: "Password input has been canceled by the user.",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "Invalid input.",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "The PIN number entered is invalid.",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom a digital signature certificate is required.",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_NOT_CERTIFICATE:"Failed to electronic signatures \n(Since the certificate key has changed, you must clear and re-import the certificate.)",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKey service is not initialized.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "Check out the PIN number.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "It was canceled by entering a PIN.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "I've canceled the password input.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "Check the password.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikey canceled the service.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ERROR: "Failed to read the file (check the path).",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ERROR: "Failed to write the file (check the path).",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ACCESS_DENIED: "Due to the rights issue it failed to read the file.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ACCESS_DENIED: "Due to the rights issue it failed to read the file.",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "Invalid input.",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "Failed to load the certificate.",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "Can not find the certificate.",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "Incorrect session ID was input.",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "The session ID is input as NULL.",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "Failed to create session.",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "Enter the password canceled.",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "SecureDisk is not initialized.",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "Invalid input.",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "Unexpected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "Failed password verification certificate (other error)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_VERIFY_PIN_FAILED: "The password check failed (check password).",
            ServiceError_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "Failed to confirm the password (the signature certificate does not match the password of the certificate for encryption).",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "It failed to communicate with the CA server.",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "The input value for the certificates issued were incorrect.",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been inputted.",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "Input password canceled",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "Failed PKCS#5 encryption.",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "Failed PKCS#8 mssage generation.",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "The certificate store failed.",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "It failed to save the issued certificate. (Initialize failed)",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "Failed issued certificates for signing electronic stores.",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "Failed to save the certificate issued for key distribution.",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "Failed CA public key storage.",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "Failed to save the issued certificate. (Finalize failure)",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "Billing issue is not currently supported.",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "The certificate that is not supported by the IC Card.",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "The input values for the renewal certificate is invalid.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been entered.",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "Input the password canceled.",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "Failed to retrieve the certificate to be renewed.",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "Failed to add the certificate to be renewed.",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "Failed to add the key file to be updated.",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "Failed PKCS#5 encryption.",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "Failed PKCS#8 mssage generation.",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "The certificate store failed.",
            ServiceError_CMP_UPDATE_INVALID_PWD: "Check the previous certificate password.",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "It failed to save the issued certificate. (Initialize failed)",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "Failed issued certificates for signing electronic stores.",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "Failed to save the certificate issued for key distribution.",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "Failed CA public key storage.",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "Failed to save the issued certificate. (Finalize failure)",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "Billing issue is not currently supported.",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "Period in which it is possible to update the certificate does not have. Update of the certificate has expired, it is possible from one month ago.",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "The input values for the invalid certificate revocation.",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been entered.",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "Input the password canceled.",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "Failed to retrieve the certificate to be revoked.",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "Failed to add the certificate to be revoked.",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "Failed to add the key file to be revoked.",
            ServiceError_CMP_REVOKE_INVALID_PWD: "Please confirm your password.",
            ServiceError_CMP_REVOKE_INVALID_PIN: "Please check the PIN number.",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PIN is locked.",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "Failed to get the device identification Values (Windows OS is subsequently other outside support).",
            ServiceError_GET_PCIDENTITY_FAILED: "Failed to get the device identification value (other error).",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "Enter the password canceled.",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "The password change failed (There was a problem with the certificate format).",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "Failed to change password (check password).",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "Failed to change password (There was a problem in stroring the certificate",
            ServiceError_CHANGE_PIN_FAILED: "The password change failed (other error).",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "Enter the password canceled.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "It has canceled the certificate export.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "Export Certificate failed (There was a problem with the certificate format).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "Export Certificate failed (Failed to find the certificate).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "Export Certificate failed (check password).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "Exporting the certificate failed (Check the password).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "Export Certificate failed (add certificate fail). ",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "Export Certificate failed (encode pfx fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "Export Certificate failed(other error).",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "The Certificate Import failed (canceled the certificate selection).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "The Certificate Import failed (canceled the password input).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "Failed to import the certificate (not certificate of PFX format).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PWD: "The Certificate Import failed (check password).",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "The Certificate Import failed (other error).",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "Failed to verify passwords (Invalid input).",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "Failed to verify passwords (the input has been canceled).",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "The input storage is not available.",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "Failed to change the certificate storage (canceled the password input).",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "Failed to change the certificate storage media.",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "Failed to change the certificate storage (check password).",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "It failed to change the certificate storage medium (Check password).",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "Selected same storage for changing stroage",
            ServiceError_CHANGE_STORAGE_FAILED: "Failed to change the certificate storage media (other error).",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "This format of certificate is invalid.",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "It failed certificate validation.",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "Certificate validation failed (other errors).",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "session id is not saved becasue the session ID is null.",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "Invalid tray list.",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "Invalid tray operation.",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "Invalid input.",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "The original digital signature is necessary.",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "The signature type is not supported yet.",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "The certificate is not a form X509.",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "The key is not a form of public key ",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "Signature verification failed.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "Failed to read the file (check the path).",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "Failed to write the file (check the path).",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_ACCESS_DENIED: "Due to the rights issue it failed to read the file.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_ACCESS_DENIED: "Due to the rights issue it failed to write the file.",

            ServiceError_VERIFY_VID_INVALID_CERTID: "Input value is incorrect.",
            ServiceError_VERIFY_VID_INVALID_KEYID: "Input value is incorrect.",
            ServiceError_VERIFY_VID_INVALID_IDN: "Input value is incorrect.",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "Security disk was not initialized.",
            ServiceError_VERIFY_VID_NOT_FOUND: "Input value is incorrect.",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "There is no form of X509 certificate.",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "It failed to bring random.",
            ServiceError_VERIFY_VID_INVALID_PWD: "It failed to bring random（Please check the password）.",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "It failed to VID verification.",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digest I failed（Input value was wrong）.",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digest I failed（This is an unsupported algorithm）.",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digest I failed（This is an unsupported algorithm）.",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digest I failed（File not found）.",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digest I failed（There was an error to read the file）.",
            ServiceError_GET_HASH_FAILED: "message digest I failed（Other error）.",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "It failed in message encryption(Input value is incorrect).",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "It failed in message encryption(t does not have a value of key).",
            ServiceError_ENCRYPT_FAILED_IV_IS_NULL: "IV encryption is not valid.",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "It failed in message encryption(The length of the key is incorrect).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "It failed in message encryption(This is an unsupported encryption algorithm).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "It failed in message encryption(This is an unsupported operating mode).",
            ServiceError_ENCRYPT_FAILED: "It failed in message encryption（Other error）.",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "It failed to message decryption(Input value is incorrect).",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "It failed to message decryption(It does not have a value of key).",
            ServiceError_DECRYPT_FAILED_IV_IS_NULL: "IV decryption is not valid.",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "It failed to message decryption(The length of the key is incorrect).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "It failed to message decryption(This is an unsupported encryption algorithm).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "It failed to message decryption(This is an unsupported operating mode).",
            ServiceError_DECRYPT_FAILED: "It failed to message decryption(Other error).",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "It failed the envelope(Invalid input).",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "It failed the envelope(Enter the certificate is not the x509).",
            ServiceError_ENVELOPE_FAILED: "It failed the envelope(Other error).",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ERROR: "Failed to read the file (check the path).",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ERROR: "Failed to write the file (check the path).",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "Due to the rights issue it failed to read the file.",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "Due to the rights issue it failed to write the file.",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "It failed to child envelope decryption(Input value is incorrect).",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "It failed to child envelope decryption(It canceled the input).",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "It failed to child envelope decryption(Please check the PIN number).",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "It failed to child envelope decryption(I canceled the password).",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: "Failed to read file .",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: 'Failed to write file .',
            ServiceError_DEENVELOPE_FAILED: "It failed to child envelope decryption.",
            ServiceError_DEENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "Due to the rights issue it failed to read the file.",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "Due to the rights issue it failed to write the file.",
            ServiceError_DEENVELOPE_FAILED_INVALID_TEXT: "It failed to decode the electronic envelope (ciphertext is damaged or the location of the file is invalid).",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "It failed to PFX signature（Other error）.",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "It failed to PFX signature（I was deselected）.",

            /* import, export certificate and key */
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INVALID_ARGUMENT: "Failed to export the certificate . ( Invalid input )",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INPUT_CANCELED: "Exporting the certificate failed. ( Canceled the password )",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_PRIVATEKEY_FAILED: "Exporting the certificate failed ( could not find the private key)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_CERTIFICATE_FAILED: "Exporting the certificate failed. ( I did not find the certificate)",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_INCORRECT: "Exporting the certificate failed (check the PIN number )",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_LOCKED: "Export Certificate failed. ( The device is locked )",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PWD_INCORRECT: "Exporting the certificate failed (check password )",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED: "Exporting the certificate failed.",

            /* mac address error message */
            ServiceError_MAC_ADDRESS_ERROR: "Failed to import MAC addresses.",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_HANDLE: "Import failed DLL handles.",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_FUNC: "Failed to import DLL functions.",
            ServiceError_MAC_ADDRESS_NONVALIDATED_RETURN_FUNC: "Function return value is not valid.",
            ServiceError_MAC_ADDRESS_FAILED_MEMORY: "It failed to allocate memory .",
            ServiceError_MAC_ADDRESS_FAILED_REG_OPEN: "Failed to open the registry .",
            ServiceError_MAC_ADDRESS_FAILED_GET_REG_VALUE: "Obtaining a registry value failed.",
            ServiceError_MAC_ADDRESS_FAILED_REG_CLOSE: "To close the Registry failed.",
            ServiceError_MAC_ADDRESS_FAILED_GET_LAST_NETID: "Bringing the NetId failed.",

            /* getFilePath */
            ServiceError_GET_FILE_PATH_CANCELED: "It canceled the file selection.",

            /* securedisk */
            ServiceError_SECUREDISK_BACKUP_ERROR: "Safe Disk failed backup and recovery.",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_CERTINDENTIFIER: "Invalid value ( certificate).",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_KEYINDENTIFIER: "The key value is invalid.",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_TOKENINDENTIFIER: "The token value is invalid.",
            ServiceError_SECUREDISK_BACKUP_INVALID_VALUE: "This value is not valid.",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_BACKUPCERTLIST: "Failed to create a backup certificate list.",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_SECURETOKEN: "Finding a safe disk failure.",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_HANDLE: "Enter the handle does not exist.",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_DATA: "The data is invalid or does not exist .",
            ServiceError_SECUREDISK_BACKUP_INVALID_RANGE: "The scope of the data is incorrect.",
            ServiceError_SECUREDISK_BACKUP_INVALID_PIN: "The password is not valid.",
            ServiceError_SECUREDISK_BACKUP_FAILED_CASTING: "It failed to convert.",

            /* pin complex check */
            ServiceError_CHECK_COMPLEX_PIN_ERROR: 'Password integrity check error',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PINS: 'Value did not enter',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: 'Two of the password entered does not match.',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SHORT_LENGTH: '10 Please enter the password for more new certificate character',
            ServiceError_CHECK_COMPLEX_PIN_SAME_PWD: 'Password for the new certificate of the password and the old certificate will be the same.',
            ServiceError_CHECK_COMPLEX_PIN_GETPIN_FAILED: 'Failed to input value acquisition (keyboard security linkage error)',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PIN: 'The passwords you entered were in violation of the production rules.',
            ServiceError_CHECK_COMPLEX_PIN_UPDATE_ERROR: 'Please change the password for the update certificate to more than 10 characters in order to enhance the safety.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_CHAR: 'Include one or more of the numbers and special characters.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_NUMBER: 'Include one or more of the letters and special characters.',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_SPECIAL: 'Include one or more of the numbers and letters.',
            ServiceError_CHECK_COMPLEX_PIN_NO_CHAR: 'Include one or more of the letters.',
            ServiceError_CHECK_COMPLEX_PIN_NO_NUMBER: 'Include one or more of the numbers.',
            ServiceError_CHECK_COMPLEX_PIN_NO_SPECIAL: 'Include one or more of the special character(\", \', \\, \| except)',
            ServiceError_CHECK_COMPLEX_PIN_PATTERN: "New password creation rules:\n1. The same character can not be used continuously for more than 4 times, such as 1111 or aaaa\n2. not for use fourth consecutive characters like 1234 or abcd\n3. not for use uses more than three times in two consecutive letters, such as ababab.\n4. Not for use more than once in three consecutive letters, such as abcabc\n",

            /* keyboardProtection */
            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "Invalid input.",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "The keyboard security module operation was failed.",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "The keyboard security module initialization failed",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "Failed to obtain the input values (keyboard interlocking security error)",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "Failed to retrieve Public Key from keyboard security module.",

            ServiceError_MOBILE_USIM_NOT_PRESENT: "Smart certification program was not installed.",
            ServiceError_TOKEN_MOBILE_USIM_INVALID_OPTIONS: "Smart authentication options is incorrect.",
            ServiceError_MOBILE_USIM_USER_CANCELED: "Smart authentication has been canceled.",

            /* mobisign */
            ServiceError_MOBISIGN_INVALID_OPTIONS: "Mobisign this option is invalid.",
            ServiceError_MOBISIGN_USER_CANCELED: "Mobisign has been canceled.",
            ServiceError_MOBISIGN_NOT_LOADED: "MobiSign failed module loading.",

            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "Failed to copy of the certificate (authentication number request is required).",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "Copy of the certificate to the failed (failed authentication number request).",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "Copy of the certificate to the failed (no sent certificate).",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "Copy of the certificate to the failed (please enter the authentication number to the transmitting terminal).",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "Copy of the certificate to the failed (and failed to export the certificate).",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "t failed to copy of the certificate (no authentication numbers match).",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "Copy of the certificate to the failed (it is a token that is not supported).",
            // 금보원 인증서 탈취 취약점 관련 수정사항
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_INPUT_CANCELED: "Password input was canceled by the user.",
            ServiceError_RELAY_RAON_CERTIFICATE_INVALID_CERTINDENTIFIER: "The certificate could not be found.",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED: "Certificate copy failed (please check your password).",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "Certificate copy failed (please check your password).",
            ServiceError_RELAY_RAON_TOKEN_FUNCTION_NOT_SUPPORTED: "Certificate copy failed (unsupported medium).",
            ServiceError_RELAY_RAON_IVALID_REF_NUM: "Certificate copy failed (authorization number verification failed).",

            /* certificateSynchronize */
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_CERTIDNTIFIER: "The input value is invalid.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_ARGUMENT: "The input value is invalid.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INPUT_CANCELED: "Your password entry was canceled by the user.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED: "Password change failed.",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_SIGN_PWD_FAILED: "Password change failed (please check sign-in password)",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_KM_PWD_FAILED: "Password change failed (check password for key distribution)",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_FIND_KMCERTIFICATE: "The key distribution certificate does not exist.",

            /* script */
            ScriptError_NOT_UNDEFINED_ERROR: "An undefined error occurred.",
            ScriptError_INCORRECT_TYPE_ERROR: "An error has occurred in the wrong format .",

            ScriptError_SIGNATURE_P7_ERROR: "Failed to signatures (P7).",
            ScriptError_SIGNATURE_P1_ERROR: "Failed to signatures (P1).",
            ScriptError_VERIFY_P7_MAKESIGNEDDATA_ERROR: "It failed to verify (not valid signature statement).",
            ScriptError_VERIFY_P7_ERROR: "It failed to verify (P7).",
            ScriptError_VERIFY_P1_ERROR: "It failed to verify (P1).",

            ScriptError_DECRYPTKEY_ERROR: "Please confirm your password (_decryptKey).",
            ScriptError_ENCRYPTKEY_ERROR: "Failed private key encryption (_encryptKey).",

            ScriptError_IMPORT_FILE_IS_NOT_P12: "It failed to import the certificate ( not a certificate in PFX format).",
            ScriptError_IMPORT_INCORRECT_PIN: "Failed to import certificate (Please confirm your password ).",

            ScriptError_CANCEL_BUTTON_EVENT: "The event occurred on the cancel button.",

            ScriptError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: "Two of the password entered does not match.",
            ScriptError_CHECK_COMPLEX_PIN_PATTERN: "New password creation rules:\n1. The same character can not be used continuously for more than 4 times, such as 1111 or aaaa\n2. not for use fourth consecutive characters like 1234 or abcd\n3. not for use uses more than three times in two consecutive letters, such as ababab.\n4. Not for use more than once in three consecutive letters, such as abcabc\n",
            ScriptError_CHECK_COMPLEX_PIN_SAME_PWD: 'Password for the new certificate of the password and the old certificate will be the same.'
        },
        {
            // Hibiscus ErrorCode
            // ChangePin.js
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "パスワード変更に失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_EPKI_TO_PKI_FAILED: "パスワード変更に失敗しました。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_EPKI_FAILED: "パスワード変更に失敗しました。(PKCS8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_PKI_ENCRYPT_FAILED: 'パスワード変更に失敗しました。(key encrypt)',
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SET_CERTIFICATE_FAILED: "パスワード変更に失敗しました。(import certificate)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "パスワード変更に失敗しました。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_KM_EPKI_TO_PKI_FAILED: "パスワード変更に失敗しました。(km pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_KM_EPKI_FAILED: "パスワード変更に失敗しました。(km pkcs8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_KMEPKI_ENCRYPT_FAILED: "パスワード変更に失敗しました。(km key encrypt)",

            // GenerateSignature.js
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CUSTOM_SID_FAILED: "セッションが期限切れ無効です。",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_DOUBLE_ENCPIN_DECRYPT_FAILED: "セッションが期限切れ無効です。(enckey decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_ENCPIN_DECRYPT_FAILED: "セッションが期限切れ無効です。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "電子署名に失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_EPKI_TO_PKI_FAILED: "電子署名に失敗しました。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SIGN_FAILED: "電子署名に失敗しました。(GenerateSignature)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETENCRYPTRANDOM_FAILED: "電子署名に失敗しました。(GetEncryptRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETRANDOM_FAILED: "電子署名に失敗しました。(GetRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_GLOBAL_KEY_FAILED: "電子署名に失敗しました。(getGlobalKey)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_PFX_TO_CERT_FAILED: "パスワードを確認してください。(pfx to cert)",

            // GetCertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",

            // GetCertificateList.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_GET_ITEM_FAILED: "証明書のリストが存在しません。",

            // ImportCertificate.js
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERT_FAILED: "入力した値は正しくありません。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "パスワードを確認してください。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "認証書の保存に失敗しました。(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "認証書の保存に失敗しました。(km key encrypt)", // kmKey = ?
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATE_FAILED: "認証書の保存に失敗しました。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERTIFICATELIIST_FAILED: "認証書の保存に失敗しました。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "認証書の保存に失敗しました。(Set CertificateList)",

            // VerifyPin.js
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "パスワードの確認に失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_EPKI_TO_PKI_FAILED: "パスワードの確認に失敗しました。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "パスワードの確認に失敗しました。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_KM_EPKI_TO_PKI_FAILED: "パスワードの確認に失敗しました。(km pkcs8 decrypt)",

            // DeleteCertificate.js
            HIBISCUS_SERVICE_ERROR_DELETE_CERIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "認証書の削除に失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_EPKI_TO_PKI_FAILED: "認証書の削除に失敗しました。(pksc8 decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_REMOVE_CERTIFICATE_FAILED: "認証書の削除に失敗しました。(remove Certificate)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATELIST_FAILED: "認証書の削除に失敗しました。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "認証書の削除に失敗しました。(Set CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "認証書の削除に失敗しました。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_KM_EPKI_TO_PKI_FAILED: "認証書の削除に失敗しました。(km pksc8 decrypt)",

            // ImportP12.js
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_PFX_TO_CERT_FAILED: "PFXまたはパスワードの確認に失敗しました。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERT_FAILED: "入力した値は正しくありません。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "認証書の保存に失敗しました。(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "認証書の保存に失敗しました。(km key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATE_FAILED: "認証書の保存に失敗しました。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERTIFICATELIST_FAILED: "認証書の保存に失敗しました。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATELIST_FAILED: "認証書の保存に失敗しました。(Set CertificateList)",

            // CheckComplexPin.js
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_LENGTH_ERROR: "パスワードは、10文字以上30文字以下で入力してください。",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_NOT_SAME: "パスワードが同一ではありません。",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_ERROR: "新規パスワード生成規則:\n1. 1111又はaaaaのように同じ文字で4回以上連続する文字列は使用不可\n2. 1234又はabcdように4文字以上連続する文字列は使用不可\n3. abababのように3回以上２文字連続する文字列は使用不可\n4. abcabcのように2回以上３文字連続する文字列は使用不可\n",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_SAME: "パスワードが同じです。",

            // ExportCertificate.js
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_DECRYPT_FAILED: "証明書のエクスポートに失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "証明書のエクスポートに失敗しました。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_CERT_TO_PFX_FAILED: "証明書のエクスポートに失敗しました。(cert to pfx)",

            // VerifyVID.js
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "VID検証に失敗しました。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_EPKI_TO_PKI_FAILED: "VID検証に失敗しました。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_FAILED: "VID検証に失敗しました。",

            // VerifySignature.js
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_PARAMETER: "入力した値は正しくありません。(paramter)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS1_VERIFY_FAILED: "検証に失敗しました。(PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS1_VERIFY_FAILED: "検証に失敗しました。(KOSCOM PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS7_VERIFY_FAILED: "検証に失敗しました。(KOSCOM PKCS7)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS7_VERIFY_FAILED: "検証に失敗しました。(PKCS7)",

            // GetCACertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_BYTES_FAILED: "証明書情報を取得できませんでした。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_MATCHING_DN_FAILED: "CA証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_SERVER_FAILED: "CA証明書がサーバーに存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_BYTES_FAILED: "CA証明書情報を取得できませんでした。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_MATCHING_DN_FAILED: "ルートCA証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_SERVER_FAILED: "ルートCA証明書がサーバーに存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "ルートCA証明書情報を取得できませんでした。(getRootHash)",

            // ValidateCertificate.js
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",

            // OpenCertInit.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_DECRYPT_API_KEY_FAILED: "コロケーション/クラウドストレージの初期化に失敗しました。(api key decrypt)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_FAILED: "コロケーション/クラウドストレージの初期化に失敗しました。(opencert init)",

            // OpenCertSync.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_MERGE_CERTINFOS_FAILED: "コロケーション/クラウドストレージの同期に失敗しました。(opencert sync)",

            // OpenCertGetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_OPENCERT_PASSWORD_FAILED: "コロケーション/クラウドストレージから証明書を取得できませんでした。(Failed to generate api password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_FAILED: "コロケーション/クラウドストレージから証明書を取得できませんでした。(Check your password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_ENCRYPTED_PKCS12_FAILED: "コロケーション/クラウドストレージから証明書を取得できませんでした。(pkcs12 decrypt)",

            // OpenCertSetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_PFX_TO_CERT_FAILED: "パスワードを確認してください。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_EPKI_TO_PKI_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_CERT_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GENERATE_HASH_HEX_DATA_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(getRootHash).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_OPENCERT_PASSWORD_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(opencert set pkcs12).",

            // OpenCertRemoveP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_FAILED: "コロケーション/クラウドストレージから証明書を削除できませんでした(opencert remove pkcs12).",

            // OpenCertSetEvent.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_EVENT_INVALID_JSON_DATA: "入力した値は正しくありません。",

            // OpenCertSetCertificate.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_EPKI_TO_PKI_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_CERT_TO_PFX_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(cert to pfx).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_CERT_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(Failed to generate hash data).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_OPENCERT_PASSWORD_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_SET_P12_FAILED: "コロケーション/クラウドストレージに証明書を保存できませんでした(opencert set pkcs12).",
        
            // GetCertInfos.js
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_INVALID_JSON_DATA:  "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_FAILED: "証明書のリストが存在しません。",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_CERT_FAILED: "ローカルストアから証明書情報を取得できませんでした。(Certificate)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GENERATE_HASH_HEX_DATA_FAILED: "ローカルストアから証明書情報を取得できませんでした。(Failed to generate hash data)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",

            // OpenCertGetFP.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_CERTIFICATE_MATCHING_DN_FAILED: "その証明書が存在しません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_ITEM_NOT_FOUND_CERTIFICATE: "その証明書が存在しません。(null)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GENERATE_HASH_HEX_DATA_FAILED: "その証明書のfp値を取得できませんでした。(Failed to generate hash data)",
            
            // OpenCertVerifyVID.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_INVALID_JSON_DATA: "入力した値は正しくありません。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_PFX_TO_CERT_FAILED: "PFXまたはパスワードの確認に失敗しました。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_EPKI_TO_PKI_FAILED: "パスワードを確認してください。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_FAILED: "VID検証に失敗しました。",

            // VestCert ErrorCode
            ServiceError_UNKNOWN: "未登録エラーコード",
            ServiceError_SERVICE_REJECTED: "MangoWireメッセージが不正のため、サービスを中断します。",
            ServiceError_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "サポートしている言語がありません。",

            ServiceError_TOKEN_NOT_INITIALIZED: "セキュリティディスクが初期化されませんでした。",
            ServiceError_TOKEN_NOT_FOUND: "セキュリティディスクが存在しません。",
            ServiceError_TOKEN_BAD: "セキュリティディスクの状態が異常です。初期化してください。",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikeyがインストールされていません。プログラムをインストールしてください。",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikeyは最新バージョンではありません。プログラムを更新してください。",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikeyオプションの値が無効です。",
            ServiceError_TOKEN_NOT_RECOGNIZED: "許可されたトークンはありません。",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "トークンでその機能をサポートしていません。",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSLサービスの初期化に失敗しました。",

            ServiceError_SESSIONID_NOT_EXIST: "セッションの有効期間が切れたか、または無効です。再接続してください。",
            ServiceError_SESSION_IS_USING: "他の場所でセッションを使用中です。再接続してください。",

            ServiceError_OPERATION_NOT_EXPECTED: "現在、この機能を実行することはできません。",
            ServiceError_OPERATION_NOT_SUPPORTED: "サポートできない機能です。",

            ServiceError_INVALID_INPUT: "入力した値は正しくありません。",
            ServiceError_INVALID_INPUT_TOKENID: "トークン識別者が正しくありません。",

            ServiceError_NO_SSL_CERTIFICATE: "登録されたSSLが存在しません。",
            ServiceError_CERTIFICATE_NOT_FOUND: "認証書が存在しません。",


            ServiceError_DELETE_CERTIFICATE_FAILED: "認証書の削除に失敗しました。（その他のエラー）",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "プログラムファイルに保存された認証書は削除できません。",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PWD_INCORRECT: "認証書の削除に失敗しました。（パスワードを確認してください。）",
            ServiceError_DELETE_PIN_INCORRECT: "認証書の削除に失敗しました（PIN番号を確認してください）。",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "認証書の削除に失敗しました（PIN番号の入力をキャンセルしました）。",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "認証書の削除に失敗しました（パスワードの入力をキャンセルしました）。",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "秘密鍵・公開鍵の生成がユーザによってキャンセルされました。",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "パスワードが正しくありません。秘密鍵・公開鍵の生成に失敗しました。",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "パスワードがロックされて、秘密鍵・公開鍵の生成に失敗しました。",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "パスワードが間違って鍵ペアの生成に失敗しました。",

            ServiceError_GENERATE_SIGNATURE_NOT_MATCHED_KEY_PAIR: "保存された証明書の鍵ペアが異なります。",
            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "電子署名に失敗しました。",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "認証書のパスワード入力が間違っています。",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "電子署名に失敗しました。(シリアル番号の生成に失敗)", //"encrypt VID Random failed(0x%x, false)"
            ServiceError_GENERATE_SIGNATURE_CANCELED: "パスワードの入力はユーザによってキャンセルされました。",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "PIN番号の入力が間違っています。",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom電子署名認証書が必要になります。",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_NOT_CERTIFICATE:"電子署名に失敗しました。\n(証明書のキーが変更され、その証明書を消去してインポートする必要があります)",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKeyサービスが初期化されていません。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "PIN番号を確認してください。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "PIN入力を取り消しました。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "パスワードを確認してください。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikeyサービスをキャンセルしました。",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ERROR: "ファイル読み取りに失敗しました。",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ERROR: "ファイル書き込みに失敗しました。",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ACCESS_DENIED: "ファイル読み取りに失敗しました。(アクセスが拒否されました)",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ACCESS_DENIED: "ファイル書き込みに失敗しました。(アクセスが拒否されました)",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "認証書のインポートに失敗しました。",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "認証書を見つかりません。",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "入力したセッションIDが正しくありません。",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "セッションIDでNULLを入力しました。",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "セッションの生成に失敗しました。",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "認証書パスワードの確認に失敗しました。(その他のエラー)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_VERIFY_PIN_FAILED: "パスワードの確認に失敗しました。(パスワードを確認してください。)",
            ServiceError_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "パスワードの確認に失敗しました（署名用証明書と暗号化用の証明書のパスワードが一致しません）。",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "CAサーバーとの通信に失敗しました。",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "認証書発給時に入力した値は正しくありません。",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "PKCS#5 暗号化に失敗しました。",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 メッセージの生成に失敗しました。",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "発行された電子署名用認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "発行された鍵配布用認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "CAの公開鍵の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "発行された認証書の保存に失敗しました。（finalize失敗）",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "有料発行はサポートされていません。",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "IC Cardがサポートしていない認証書です。",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "認証書更新時に入力した値は正しくありません。",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "更新する認証書のインポートに失敗しました。",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "更新する認証書の追加に失敗しました。",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "更新するキーファイルの追加に失敗しました。",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "PKCS#5 暗号化に失敗しました。",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 メッセージの生成に失敗しました。",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_INVALID_PWD: "古い認証書のパスワードを確認してください。",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "発行された電子署名用認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "発行された鍵配布用認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "CAの公開鍵の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "有料発行はサポートされていません。",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "認証書を更新することができる期間はありません。認証書の更新は有効期限が切れ、1ヶ月前から可能です。",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "認証書廃棄に入力した値は正しくありません。",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "廃棄する認証書のインポートに失敗しました。",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "廃棄する認証書の追加に失敗しました。",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "廃棄するキーファイルの追加に失敗しました。",
            ServiceError_CMP_REVOKE_INVALID_PWD: "パスワードを確認してください。",
            ServiceError_CMP_REVOKE_INVALID_PIN: "PIN番号を確認してください。",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PINがロックされています。",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "PC識別値をインポートできませんでした。(Windows以外の他のOSは、今後サポートします。)",
            ServiceError_GET_PCIDENTITY_FAILED: "PC識別値をインポートできませんでした。(その他のエラー)",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "パスワードの変更に失敗しました。(認証書の形式に問題が発生しました。)",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "パスワードの変更に失敗しました。(パスワードを確認してください。）",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "パスワードの変更に失敗しました。(認証書を保存する時に問題が発生しました。）",
            ServiceError_CHANGE_PIN_FAILED: "パスワードの変更に失敗しました。(その他のエラー）",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "認証書のエクスポートをキャンセルしました。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "認証書のエクスポートに失敗しました。(認証書の形式に問題が発生しました。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "認証書のエクスポートに失敗しました。(認証書の検索に失敗しました。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "認証書のエクスポートに失敗しました。(パスワードを確認してください。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "認証書のエクスポートに失敗しました（パスワードを確認してください）。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "認証書のエクスポートに失敗しました。(add certificate fail)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "認証書のエクスポートに失敗しました。(encode pfx fail)",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "認証書のエクスポートに失敗しました。(その他のエラー)",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "認証書のインポートに失敗しました。(認証書選択をキャンセルしました。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "認証書のインポートに失敗しました。(パスワードの入力をキャンセルしました。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "認証書のインポートに失敗しました。(PFX形式の認証書ではありません。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PWD: "認証書のインポートに失敗しました。(パスワードを確認してください。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "認証書のインポートに失敗しました。(その他のエラー)",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "パスワードの確認に失敗しました（入力値が間違っていた）。",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "パスワードの確認に失敗しました（入力をキャンセルしました）。",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "使用できない媒体です。",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "認証書の保存媒体の変更に失敗しました。(パスワードの入力をキャンセルしました。)",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "認証書の保存媒体の変更に失敗しました。",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "認証書の保存媒体の変更に失敗しました。(パスワードを確認してください。)",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "認証書の保存媒体の変更に失敗しました（パスワードを確認してください）。",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "変更する認証書の保存媒体が同じです。",
            ServiceError_CHANGE_STORAGE_FAILED: "認証書の保存媒体の変更に失敗しました。(その他のエラー)",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "認証書の形式が正しくありません。",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "認証書の検証に失敗しました。",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "認証書の有効性検証に失敗しました。(その他のエラー)",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "セッションIDが存在しません。セッションの保存に失敗しました。",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "トレイリストが正しくありません。",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "トレイの動作が有効ではありません。",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "入力した値は正しくありません。",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "元電子文書が必要な電子署名です。",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "まだサポートされていない電子署名です。",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "X509認証書の形態ではありません。",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "公開鍵の形態ではありません。",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "署名検証に失敗しました。",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "ファイルを読むにエラーが発生しました",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "파일 쓰기에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_ACCESS_DENIED: "ファイル読み取りに失敗しました。(アクセスが拒否されました)",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_ACCESS_DENIED: "ファイル書き込みに失敗しました。(アクセスが拒否されました)",

            ServiceError_VERIFY_VID_INVALID_CERTID: "入力値が間違っています。",
            ServiceError_VERIFY_VID_INVALID_KEYID: "入力値が間違っています。",
            ServiceError_VERIFY_VID_INVALID_IDN: "入力値が間違っています。",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されていませんでした。",
            ServiceError_VERIFY_VID_NOT_FOUND: "入力値が間違っています。",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "X509認証書の形がありません。",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "randomを持ってくるのに失敗しました。",
            ServiceError_VERIFY_VID_INVALID_PWD: "randomを持ってくるのに失敗しました（パスワードを確認してください）。",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "VID検証に失敗しました。",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digestを失敗しました（入力値が間違っていた）。",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digestを失敗しました（サポートしていないアルゴリズムです）。",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digestを失敗しました（サポートしていないアルゴリズムです）。",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digestを失敗しました（ファイルが見つかりません）。",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digestを失敗しました（ファイルを読むにエラーが発生しました）。",
            ServiceError_GET_HASH_FAILED: "message digestを失敗しました（その他のエラー）。",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "message暗号化に失敗しました(入力値が間違っています)。",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "message暗号化に失敗しました(keyの値がありません)。",
            ServiceError_ENCRYPT_FAILED_IV_IS_NULL: "暗号化IVが有効ではありません。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message暗号化に失敗しました(keyの長さが間違っています)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message暗号化に失敗しました(サポートしていない暗号化アルゴリズムです)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "message暗号化に失敗しました(サポートしていない動作モードです)。",
            ServiceError_ENCRYPT_FAILED: "message暗号化に失敗しました（その他のエラー）。",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "message復号化に失敗しました(入力値が間違っています)。",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "message復号化に失敗しました(keyの値がありません)。",
            ServiceError_DECRYPT_FAILED_IV_IS_NULL: "復号化IVが有効ではありません。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message復号化に失敗しました(keyの長さが間違っています)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message復号化に失敗しました(サポートしていない暗号化アルゴリズムです)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "message復号化に失敗しました(サポートしていない動作モードです)。",
            ServiceError_DECRYPT_FAILED: "message復号化に失敗しました(その他のエラー)。",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "電子封筒を失敗しました(入力値が間違っています)。",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "電子封筒を失敗しました(入力された認証書がx509はありません)。",
            ServiceError_ENVELOPE_FAILED: "電子封筒を失敗しました(その他のエラー)。",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ERROR: "ファイル読み取りに失敗しました。",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ERROR: "ファイル書き込みに失敗しました。",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "ファイル読み取りに失敗しました。(アクセスが拒否されました)",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "ファイル書き込みに失敗しました。(アクセスが拒否されました)",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "子封筒復号化に失敗しました(入力値が間違っています)。",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "子封筒復号化に失敗しました(入力をキャンセルしました)。",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "子封筒復号化に失敗しました(PIN番号を確認してください)。",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "子封筒復号化に失敗しました(パスワードをキャンセルしました)。",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: "ファイル読み取りに失敗しました。",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: "ファイル書き込みに失敗しました。",
            ServiceError_DEENVELOPE_FAILED: "子封筒復号化に失敗しました。",
            ServiceError_DEENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "ファイル読み取りに失敗しました。(アクセスが拒否されました)",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "ファイル書き込みに失敗しました。(アクセスが拒否されました)",
            ServiceError_DEENVELOPE_FAILED_INVALID_TEXT: "電子封筒復号化に失敗しました（暗号が破損したり、ファイルの位置が間違っていた）。",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "PFX電子署名に失敗しました（他のエラー）。",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "PFX電子署名に失敗しました（選択を解除しました）。",

            /* import, export certificate and key */
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INVALID_ARGUMENT: "証明書のエクスポートに失敗しました。（入力値が間違ってい）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INPUT_CANCELED: "証明書のエクスポートに失敗しました。 （パスワードの入力をキャンセルしました）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_PRIVATEKEY_FAILED: "証明書のエクスポートに失敗しました。 （秘密鍵が見つかりませんでした）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_CERTIFICATE_FAILED: "証明書のエクスポートに失敗しました。 （証明書が見つかりませんでした）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_INCORRECT: "証明書のエクスポートに失敗しました。 （ PIN番号を確認してください）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_LOCKED: "証明書のエクスポートに失敗しました。 （デバイスがロックされています）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PWD_INCORRECT: "証明書のエクスポートに失敗しました。 （パスワードを確認してください）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED: "証明書のエクスポートに失敗しました。 ",

            /* mac address error message */
            ServiceError_MAC_ADDRESS_ERROR: "MACアドレスを取得するに失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_HANDLE: "DLLのハンドルを取得するに失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_FUNC: "DLL関数をインポートに失敗しました。",
            ServiceError_MAC_ADDRESS_NONVALIDATED_RETURN_FUNC: "関数の戻り値が有効ではありません。",
            ServiceError_MAC_ADDRESS_FAILED_MEMORY: "メモリを割り当てに失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_REG_OPEN: "レジストリを開くことが失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_GET_REG_VALUE: "レジストリ値を得るのに失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_REG_CLOSE: "レジストリを閉じた失敗しました。",
            ServiceError_MAC_ADDRESS_FAILED_GET_LAST_NETID: "NetIdを持ってくるのに失敗しました。",

            /* getFilePath */
            ServiceError_GET_FILE_PATH_CANCELED: "ファイル選択を解除しました。",

            /* securedisk */
            ServiceError_SECUREDISK_BACKUP_ERROR: "安全ディスクのバックアップと復元に失敗しました。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_CERTINDENTIFIER: "入力値が間違っています。 （証明書） 。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_KEYINDENTIFIER: "キーの値が間違っています。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_TOKENINDENTIFIER: "トークンの値が間違っています。",
            ServiceError_SECUREDISK_BACKUP_INVALID_VALUE: "値が有効ではありません。",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_BACKUPCERTLIST: "バックアップ証明書のリストを作る失敗しました。",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_SECURETOKEN: "安全ディスクの検索に失敗しました。",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_HANDLE: "入力ハンドルが存在しません。",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_DATA: "データが間違っているか、存在しません。",
            ServiceError_SECUREDISK_BACKUP_INVALID_RANGE: "データの範囲が間違っています。",
            ServiceError_SECUREDISK_BACKUP_INVALID_PIN: "パスワードが有効ではありません。",
            ServiceError_SECUREDISK_BACKUP_FAILED_CASTING: "変換に失敗しました。",

            /* pin complex check */
            ServiceError_CHECK_COMPLEX_PIN_ERROR: 'パスワードの整合性チェックエラー',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PINS: '値が入力されていませんでした',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: '入力された二つのパスワードが一致しません。',
            ServiceError_CHECK_COMPLEX_PIN_NOT_SHORT_LENGTH: '10文字以上の新規証明書のパスワードを入力してください',
            ServiceError_CHECK_COMPLEX_PIN_SAME_PWD: '新規証明書のパスワードと古い証明書のパスワードが同じになります。',
            ServiceError_CHECK_COMPLEX_PIN_GETPIN_FAILED: '入力値取得に失敗しました（キーボードセキュリティ連動エラー）',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PIN: '入力したパスワードが生成規則に違反します。',
            ServiceError_CHECK_COMPLEX_PIN_UPDATE_ERROR: '安全性を強化するために更新証明書のパスワードを10文字以上に変更してください。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_CHAR: '数字と特殊文字を一つ以上含みなさい。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_NUMBER: '英字と特殊文字を一つ以上含みなさい。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_SPECIAL: '数字と英字を一つ以上含みなさい。',
            ServiceError_CHECK_COMPLEX_PIN_NO_CHAR: '英字を一つ以上含みなさい。',
            ServiceError_CHECK_COMPLEX_PIN_NO_NUMBER: '数字を一つ以上含みなさい。',
            ServiceError_CHECK_COMPLEX_PIN_NO_SPECIAL: '特殊文字を一つ以上含みなさい(\", \', \\, \| を除く)',
            ServiceError_CHECK_COMPLEX_PIN_PATTERN: "新規パスワード生成規則:\n1. 1111又はaaaaのように同じ文字で4回以上連続する文字列は使用不可\n2. 1234又はabcdように4文字以上連続する文字列は使用不可\n3. abababのように3回以上２文字連続する文字列は使用不可\n4. abcabcのように2回以上３文字連続する文字列は使用不可\n",

            /* keyboardProtection */
            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "入力した値は正しくありません。",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "セキュリティキーボードの実行に失敗しました。",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "セキュリティキーボードの初期化に失敗しました。",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "入力した値の取得に失敗しました。(セキュリティキーボード実行エラー)",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "セキュリティキーボードの公開鍵の取得に失敗しました。",

            ServiceError_MOBILE_USIM_NOT_PRESENT: "スマート認定プログラムがインストールされていませんでした。",
            ServiceError_TOKEN_MOBILE_USIM_INVALID_OPTIONS: "スマート認証のオプションが間違っています。",
            ServiceError_MOBILE_USIM_USER_CANCELED: "スマート認証が取り消されました。",

            /* mobisign */
            ServiceError_MOBISIGN_INVALID_OPTIONS: "mobisignオプションが無効です。",
            ServiceError_MOBISIGN_USER_CANCELED: "mobisignがキャンセルされました。",
            ServiceError_MOBISIGN_NOT_LOADED: "MobiSignモジュールのロードに失敗しました。",

            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "認証書のコピーに失敗しました（認証番号要求が必要になります）。",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "認証書のコピーに失敗しました（認証番号要求に失敗しました）。",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "認証書のコピーに失敗しました（送信された認証書がありません）。",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "認証書のコピーに失敗しました（送信端末に認証番号を入力してください）。",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "認証書のコピーに失敗しました（認証書のエクスポートに失敗しました）。",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "認証書のコピーに失敗しました（認証番号が一致しません）。",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "認証書のコピーに失敗しました（サポートされていないトークンです）。",
            // 금보원 인증서 탈취 취약점 관련 수정사항
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_INPUT_CANCELED: "パスワードの入力がユーザーによってキャンセルされました。",
            ServiceError_RELAY_RAON_CERTIFICATE_INVALID_CERTINDENTIFIER: "その証明書を見つけることができません。",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED: "証明書のコピーに失敗しました（パスワードを確認してください）。",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "証明書のコピーに失敗しました（パスワードを確認してください）。",
            ServiceError_RELAY_RAON_TOKEN_FUNCTION_NOT_SUPPORTED: "証明書のコピーに失敗しました（サポートされていないメディアです）。",
            ServiceError_RELAY_RAON_IVALID_REF_NUM: "証明書のコピーに失敗しました（承認番号の検証に失敗しました）。",

            /* certificateSynchronize */
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_CERTIDNTIFIER: "入力値が無効です。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_ARGUMENT: "入力値が無効です。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INPUT_CANCELED: "パスワードの入力がユーザーによって取り消されました。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED: "パスワードの変更に失敗しました。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_SIGN_PWD_FAILED: "パスワードの変更に失敗しました（署名用のパスワードを確認してください）",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_KM_PWD_FAILED: "パスワードの変更に失敗しました（鍵配布用のパスワードを確認してください）",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_FIND_KMCERTIFICATE: "鍵配布用証明書が存在しません。",

            /* script */
            ScriptError_NOT_UNDEFINED_ERROR: "定義されていないエラーが発生しました。",
            ScriptError_INCORRECT_TYPE_ERROR: "不正な形式のエラーが発生しました。",

            ScriptError_SIGNATURE_P7_ERROR: "電子署名に失敗しました（P7）。",
            ScriptError_SIGNATURE_P1_ERROR: "電子署名に失敗しました（P1）。",
            ScriptError_VERIFY_P7_MAKESIGNEDDATA_ERROR: "検証に失敗しました（有効な署名文はありません）。",
            ScriptError_VERIFY_P7_ERROR: "検証に失敗しました（P7）。",
            ScriptError_VERIFY_P1_ERROR: "検証に失敗しました（P1）。",

            ScriptError_DECRYPTKEY_ERROR: "パスワードを確認してください（ _decryptKey ） 。",
            ScriptError_ENCRYPTKEY_ERROR: "秘密鍵の暗号化に失敗しました（ _encryptKey ） 。",

            ScriptError_IMPORT_FILE_IS_NOT_P12: "証明書のインポートに失敗しました（ PFX形式の証明書がありません） 。",
            ScriptError_IMPORT_INCORRECT_PIN: "証明書のインポートに失敗しました（パスワードを確認してください） 。",

            ScriptError_CANCEL_BUTTON_EVENT: "キャンセルボタンのイベントが発生しました。",

            ScriptError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: "新規証明書のパスワードと古い証明書のパスワードが同じになります。",
            ScriptError_CHECK_COMPLEX_PIN_PATTERN: "新規パスワード生成規則:\n1. 1111又はaaaaのように同じ文字で4回以上連続する文字列は使用不可\n2. 1234又はabcdように4文字以上連続する文字列は使用不可\n3. abababのように3回以上２文字連続する文字列は使用不可\n4. abcabcのように2回以上３文字連続する文字列は使用不可\n",
            ScriptError_CHECK_COMPLEX_PIN_SAME_PWD: '新規証明書のパスワードと古い証明書のパスワードが同じになります。'
        },
        {
            // Hibiscus ErrorCode
            // ChangePin.js
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "密码更改失败。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_EPKI_TO_PKI_FAILED: "密码更改失败。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_EPKI_FAILED: "密码更改失败。(PKCS8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_PKI_ENCRYPT_FAILED: '密码更改失败。(key encrypt)',
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SET_CERTIFICATE_FAILED: "密码更改失败。(import certificate)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "密码更改失败。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_KM_EPKI_TO_PKI_FAILED: "密码更改失败。(km pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_PKI_TO_KM_EPKI_FAILED: "密码更改失败。(km pkcs8 encrypt)",
            HIBISCUS_SERVICE_ERROR_CHANGE_PIN_SECURE_INDEXED_DB_NEW_KMEPKI_ENCRYPT_FAILED: "密码更改失败。(km key encrypt)",
            
            // GenerateSignature.js
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CUSTOM_SID_FAILED: "会话无效。",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_DOUBLE_ENCPIN_DECRYPT_FAILED: "会话无效。(enckey decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_ENCPIN_DECRYPT_FAILED: "会话无效。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "生成签名失败。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_EPKI_TO_PKI_FAILED: "生成签名失败。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_SIGN_FAILED: "生成签名失败。(GenerateSignature)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETENCRYPTRANDOM_FAILED: "生成签名失败。(GetEncryptRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_GETRANDOM_FAILED: "生成签名失败。(GetRandom)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_GET_GLOBAL_KEY_FAILED: "生成签名失败。(getGlobalKey)",
            HIBISCUS_SERVICE_ERROR_GENERATE_SIGNATURE_PFX_TO_CERT_FAILED: "请检查您的密码。(pfx to cert)",

            // GetCertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            
            // GetCertificateList.js
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_GET_CERTIFICATELIST_GET_ITEM_FAILED: "证书列表不存在。",
            
            // ImportCertificate.js
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERT_FAILED: "输入无效(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "请检查您的密码。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "无法保存证书。(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "无法保存证书。(km key encrypt)", // kmKey = ?
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATE_FAILED: "无法保存证书。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_GET_CERTIFICATELIIST_FAILED: "无法保存证书。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "无法保存证书。(Set CertificateList)",

            // VerifyPin.js
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "无法确认密码。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_EPKI_TO_PKI_FAILED: "无法确认密码。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "无法确认密码。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_PIN_KM_EPKI_TO_PKI_FAILED: "无法确认密码。(km pkcs8 decrypt)",

            // DeleteCertificate.js
            HIBISCUS_SERVICE_ERROR_DELETE_CERIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "无法删除证书。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_EPKI_TO_PKI_FAILED: "无法删除证书。(pksc8 decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_REMOVE_CERTIFICATE_FAILED: "无法删除证书。(remove Certificate)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_GET_CERTIFICATELIST_FAILED: "无法删除证书。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SET_CERTIFICATELIST_FAILED: "无法删除证书。(Set CertificateList)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_SECURE_INDEXED_DB_KM_PKI_DECRYPT_FAILED: "无法删除证书。(km key decrypt)",
            HIBISCUS_SERVICE_ERROR_DELETE_CERTIFICATE_KM_EPKI_TO_PKI_FAILED: "无法删除证书。(km pksc8 decrypt)",

            // ImportP12.js
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_PFX_TO_CERT_FAILED: "PFX或密码确认失败。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERT_FAILED: "输入无效(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KEY_ENCRYPT_FAILED: "无法保存证书。(key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SECURE_INDEXED_DB_KM_KEY_ENCRYPT_FAILED: "无法保存证书。(km key encrypt)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATE_FAILED: "无法保存证书。(Certificate)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_GET_CERTIFICATELIST_FAILED: "无法保存证书。(Get CertificateList)",
            HIBISCUS_SERVICE_ERROR_IMPORT_P12_SET_CERTIFICATELIST_FAILED: "无法保存证书。(Set CertificateList)",

            // CheckComplexPin.js
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_LENGTH_ERROR: "请输入10个字符到30个字符之间的密码。",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_NOT_SAME: "密码不一样。",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_ERROR: "新密码创建规则：\ n1。相同的字符不能连续使用超过4次，例如1111或aaaa \ n2。不要使用第四个连续的字符，如1234或abcd \ n3。不使用在连续两个字母中使用三次以上，例如ababab。\ n4。不要在三个连续的字母中多次使用，例如abcabc \ n",
            HIBISCUS_SERVICE_ERROR_CHECK_COMPLEX_PIN_SAME: "密码是一样的。",

            // ExportCertificate.js
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_SECURE_INDEXED_DB_KEY_DECRYPT_FAILED: "证书导出失败。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_EPKI_TO_PKI_FAILED: "证书导出失败。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_EXPORT_CERTIFICATE_CERT_TO_PFX_FAILED: "证书导出失败。(cert to pfx)",

            // VerifyVID.js
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_SECURE_INDEXED_DB_PKI_DECRYPT_FAILED: "VID验证失败。(key decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_EPKI_TO_PKI_FAILED: "VID验证失败。(pkcs8 decrypt)",
            HIBISCUS_SERVICE_ERROR_VERIFY_VID_FAILED: "VID验证失败。",

            // VerifySignature.js
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_INVALID_PARAMETER: "输入无效(paramter)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS1_VERIFY_FAILED: "验证失败(PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS1_VERIFY_FAILED: "验证失败(KOSCOM PKCS1)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_KOSCOM_PKCS7_VERIFY_FAILED: "验证失败(KOSCOM PKCS7)",
            HIBISCUS_SERVICE_ERROR_VERIFY_SIGNATURE_PKCS7_VERIFY_FAILED: "验证失败(PKCS7)",

            // GetCACertificate.js
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_BYTES_FAILED: "无法获取证书信息。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_MATCHING_DN_FAILED: "CA证书不存在。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_CA_CERT_FROM_SERVER_FAILED: "服务器上不存在CA证书。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_BYTES_FAILED: "无法获取CA证书信息。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_MATCHING_DN_FAILED: "ROOT CA证书不存在。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GET_ROOTCA_CERT_FROM_SERVER_FAILED: "服务器上不存在ROOT CA证书。",
            HIBISCUS_SERVICE_ERROR_GET_CA_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "无法检索ROOT CA证书信息。(getRootHash)",

            // ValidateCertificate.js
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_VALIDATE_CERTIFICATE_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            
            // OpenCertInit.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_DECRYPT_API_KEY_FAILED: "共存/云存储初始化失败。(api key decrypt)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_INIT_FAILED: "共存/云存储初始化失败。(opencert init)",

            // OpenCertSync.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SYNC_MERGE_CERTINFOS_FAILED: "共存/云存储同步失败。(opencert sync)",

            // OpenCertGetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_OPENCERT_PASSWORD_FAILED: "无法从共址/云存储中获取证书。(Failed to generate api password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_FAILED: "无法从共址/云存储中获取证书。(Check your password)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_P12_GET_ENCRYPTED_PKCS12_FAILED: "无法从共址/云存储中获取证书。(pkcs12 decrypt)",

            // OpenCertSetP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_PFX_TO_CERT_FAILED: "请检查您的密码。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_EPKI_TO_PKI_FAILED: "无法将证书存储在共存/云存储中(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_CERT_FAILED: "无法将证书存储在共存/云存储中(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GENERATE_HASH_HEX_DATA_FAILED: "无法将证书存储在共存/云存储中(getRootHash).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_GET_OPENCERT_PASSWORD_FAILED: "无法将证书存储在共存/云存储中(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_P12_FAILED: "无法将证书存储在共存/云存储中(opencert set pkcs12).",

            // OpenCertRemoveP12.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_REMOVE_P12_FAILED: "无法从共存/云存储中删除证书(opencert remove pkcs12).",

            // OpenCertSetEvent.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_EVENT_INVALID_JSON_DATA: "输入无效",

            // OpenCertSetCertificate.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_EPKI_TO_PKI_FAILED: "无法将证书存储在共存/云存储中(pkcs8 decrypt).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_CERT_TO_PFX_FAILED: "无法将证书存储在共存/云存储中(cert to pfx).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_CERT_FAILED: "无法将证书存储在共存/云存储中(Certificate).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GENERATE_HASH_HEX_DATA_FAILED: "无法将证书存储在共存/云存储中(Failed to generate hash data).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_GET_OPENCERT_PASSWORD_FAILED: "无法将证书存储在共存/云存储中(Failed to generate api password).",
            HIBISCUS_SERVICE_ERROR_OPENCERT_SET_CERTIFICATE_SET_P12_FAILED: "无法将证书存储在共存/云存储中(opencert set pkcs12).",
        
            // GetCertInfos.js
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_INVALID_JSON_DATA:  "输入无效",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_FAILED: "证书列表不存在。",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_CERT_FAILED: "無法從本地存儲獲取證書信息。(Certificate)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GENERATE_HASH_HEX_DATA_FAILED: "無法從本地存儲獲取證書信息。(Failed to generate hash data)",
            HIBISCUS_SERVICE_ERROR_GET_CERTINFOS_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            
            // OpenCertGetFP.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_CERTIFICATE_MATCHING_DN_FAILED: "该证书不存在。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GET_ITEM_NOT_FOUND_CERTIFICATE: "该证书不存在。(null)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_GET_FP_GENERATE_HASH_HEX_DATA_FAILED: "無法獲得該證書的fp值。(Failed to generate hash data)",

            // OpenCertVerifyVID.js
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_INVALID_JSON_DATA: "输入无效",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_PFX_TO_CERT_FAILED: "PFX或密码确认失败。(pfx to cert)",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_EPKI_TO_PKI_FAILED: "请检查您的密码。",
            HIBISCUS_SERVICE_ERROR_OPENCERT_VERIFY_VID_FAILED: "VID验证失败。",
            
            // VestCert ErrorCode
            ServiceError_UNKNOWN: "未登录的错误代码",
            ServiceError_SERVICE_REJECTED: "不是正确的MangoWire信息,因此拒绝服务.",
            ServiceError_MEMORY_ALLOCATION_FAILED: "内存分配失败.",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "不受支持的语言.",

            ServiceError_TOKEN_NOT_INITIALIZED: "安全磁盘未初始化.",
            ServiceError_TOKEN_NOT_FOUND: "不存在安全磁盘.",
            ServiceError_TOKEN_BAD: "安全磁盘非正常状态.请初始化.",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "未安装Ubikey.请安装此程序.",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "当前Ubikey非最新版本.请升级程序.",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikey的选项值发生错误.",
            ServiceError_TOKEN_NOT_RECOGNIZED: "未被允许的令牌.",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "令牌不支持相应功能.",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSL服务初始化失败.",

            ServiceError_SESSIONID_NOT_EXIST: "会话过期或无效.请重新访问.",
            ServiceError_SESSION_IS_USING: "会话正在进行.请重新访问.",

            ServiceError_OPERATION_NOT_EXPECTED: "当前无法执行此功能.",
            ServiceError_OPERATION_NOT_SUPPORTED: "不受支持的功能.",

            ServiceError_INVALID_INPUT: "输入值无效.",
            ServiceError_INVALID_INPUT_TOKENID: "令牌认证无效.",

            ServiceError_NO_SSL_CERTIFICATE: "不存在登录的SSL认证书.",
            ServiceError_CERTIFICATE_NOT_FOUND: "认证书不存在.",


            ServiceError_DELETE_CERTIFICATE_FAILED: "认证书删除失败.(其他错误)",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "输入值无效.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "应用程序文件夹中储存的认证书无法删除.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PWD_INCORRECT: "认证书删除失败(请确认密码).",
            ServiceError_DELETE_PIN_INCORRECT: "认证书删除失败(请确认PIN码).",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "认证书删除失败(PIN码输入取消).",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "认证书删除失败(密码输入取消).",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "输入值无效.",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom失败.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "输入值无效.",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen 密钥失败",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "用户取消生成密钥对.",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "PIN码错误导致生成密钥对失败.",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "PIN码错误导致设备被锁.生成密钥对失败.",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "密码错误导致生成密钥对失败.",

            ServiceError_GENERATE_SIGNATURE_NOT_MATCHED_KEY_PAIR: "存儲的證書不同的密鑰集。",
            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "非预期的密钥标识符",
            ServiceError_GENERATE_SIGNATURE_FAILED: "电子签名失败.",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "安全磁盘未初始化.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "电子签名失败(请确认密码).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "电子签名失败(请确认密码).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "电子签名失败(设备被锁).",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "电子签名失败(请确认密码).",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "电子签名失败(识别码生成失败).", //"encrypt VID Random failed(0x%x, false)"
            ServiceError_GENERATE_SIGNATURE_CANCELED: "用户取消输入密码.",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "输入值无效.",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "集成的安全令牌的密碼不正確.",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom电子签名需要认证书.",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_NOT_CERTIFICATE:"电子签名失败(由于证书密钥已更改，您必须清除并重新导入证书.)",


            ServiceError_GET_CERTIFICATE_LIST_FAILED: "功能失败",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKey服务未初始化.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "请确认PIN码.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "取消PIN码输入.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "取消密码输入.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "请确认密码.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "UBIKey服务取消.",

            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ERROR: "無法讀取該文件（檢查路徑）.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ERROR: "無法寫入文件（檢查路徑）.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_READ_ACCESS_DENIED: "由於配股未能讀取文件.",
            ServiceError_GENERATE_SIGNATURE_FAILED_FILE_WRITE_ACCESS_DENIED: "由於配股未能提交了一封信.",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "输入值无效.",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "认证书导入失败.",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "认证书无法查找.",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "输入了错误的session ID.",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "session ID无效输入.",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "session生成失败.",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "取消密码输入.",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "输入值无效.",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "输入值无效.",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "非预期的密钥标识符",
            ServiceError_PUSH_CERTIFICATE_FAILED: "认证推送失败.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "认证书密码确认失败.(其他错误)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "输入值无效.",
            ServiceError_VERIFY_PIN_FAILED: "密码确认失败(请确认密码).",
            ServiceError_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "它未能確認密碼（用於簽名和加密證書不匹配的密碼）。",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "电子签名失败(请确认密码).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "电子签名失败(请确认密码).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "电子签名失败(设备被锁).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "电子签名失败(设备被锁).",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "内存分配失败.",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "与CA服务器的通信失败.",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "与签发认证书有关的输入值无效.",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "输入了不被支持的机构代码.",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "取消密码输入.",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "PKCS#5加密失败.",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8信息构成失败.",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "认证书储存失败.",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "签发的认证储存失败.(初始化失败)",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "签发的电子签名认证书储存失败.",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "签发的密钥分配用认证书储存失败.",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "CA公共密钥储存失败.",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "发放的认证书储存失败.(finalize失败)",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "目前不支持收费发放.",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "ICCard是不受支持的认证书.",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "有关认证书升级的输入值无效.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "输入了不被支持的机构代码.",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "取消密码输入.",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "预备升级的认证书导入失败.",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "预备升级的认证书添加失败.",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "预备升级的密钥文件添加失败.",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "PKCS#5加密失败.",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8信息构成失败.",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "认证书储存失败.",
            ServiceError_CMP_UPDATE_INVALID_PWD: "请确认旧认证书密码.",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "签发的认证储存失败.(初始化失败)",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "签发的电子签名认证书储存失败.",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "签发的密钥分配用认证书储存失败.",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "CA公共密钥储存失败.",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "签发的认证书储存失败.(finalize失败)",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "目前不支持收费发放.",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "不是认证书升级时间.认证书到期一个月前可进行更新.",
            ServiceError_CMP_UPDATE_INVALID_PIN: "请确认PIN码",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "有关吊销认证书的输入值无效.",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "输入了不被支持的机构代码.",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "取消输入密码.",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "预备吊销的认证书导入失败.",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "预备吊销的认证书添加失败.",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "预备吊销的密钥文件添加失败.",
            ServiceError_CMP_REVOKE_INVALID_PWD: "请确认密码.",
            ServiceError_CMP_REVOKE_INVALID_PIN: "请确认PIN码.",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PIN被锁.",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "内存分配失败.",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "终端识别值无法导入(将来会支持Windows以外其他的OS).",
            ServiceError_GET_PCIDENTITY_FAILED: "终端识别值无法导入(其他错误).",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "输入值无效.",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "取消输入密码.",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "修改密码失败(认证书格式问题).",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "修改密码失败(请确认密码).",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "修改密码失败(认证书储存时出错).",
            ServiceError_CHANGE_PIN_FAILED: "修改密码失败(其他错误).",
            ServiceError_CHANGE_PIN_FAILED_PROGRAM_FILES_PATH_WARNING: "应用程序文件夹中储存的认证书密码无法修改.",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "取消输入密码.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "认证书导出失败.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "认证书导出失败(认证书格式问题).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "认证书导出失败(无法查找认证书).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "认证书导出失败(请确认密码).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "认证书导出失败(请确认密码).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "认证书导出失败(认证书添加失败).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "认证书导出失败(pfx编码失败).",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "认证书导出失败(其他错误).",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "认证书导入失败(取消认证书选择).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "认证书导入失败(取消密码输入).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "认证书导入失败(不是pfx格式的认证书）.",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PWD: "认证书导入失败(请确认密码).",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "认证书导入失败(其他错误).",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "無效CERTINDENTIFIER.",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "輸入保密號碼已被取消.",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "输入值无效.",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "输入的媒体无法使用.",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "认证书存储媒体变更失败(取消输入密码).",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "认证书存储媒体变更失败.",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "认证书存储媒体变更失败(请确认密码).",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "认证书存储媒体变更失败(请确认密码).",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "变更的认证书储存媒体与之前相同.",
            ServiceError_CHANGE_STORAGE_FAILED: "认证书存储媒体变更失败(其他错误).",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "输入值无效.",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "认证书格式错误.",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "认证书验证失败.",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "认证书有效性验证失败(其他错误).",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "无session id导致存储失败.",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "错误的tray目录.",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "错误的tray操作.",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "输入值无效.",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "原文所需的电子签名.",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "不受支持的电子签名.",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "不是X509认证书形式.",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "不是公开密钥形式.",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "签名验证失败.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "文件读取失败.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "文件书写失败.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_ACCESS_DENIED: "由於配股未能讀取文件.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_ACCESS_DENIED: "由於配股未能提交了一封信.",

            ServiceError_VERIFY_VID_INVALID_CERTID: "输入值无效.",
            ServiceError_VERIFY_VID_INVALID_KEYID: "输入值无效.",
            ServiceError_VERIFY_VID_INVALID_IDN: "输入值无效.",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "安全磁盘未初始化.",
            ServiceError_VERIFY_VID_NOT_FOUND: "输入值无效.",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "不是X509认证书形式.",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "random导入失败.",
            ServiceError_VERIFY_VID_INVALID_PWD: "random导入失败(请确认密码).",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "VID验证失败.",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digest失败(输入值无效).",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digest失败(不受支持的算法).",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digest失败(不受支持的算法).",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digest失败(文件无法查找).",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digest失败(读取文件时发生错误).",
            ServiceError_GET_HASH_FAILED: "message digest失败(其他错误).",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "信息加密失败(输入值无效).",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "信息加密失败(无密钥值).",
            ServiceError_ENCRYPT_FAILED_IV_IS_NULL: "信息加密失败(不受支持的padding).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "信息加密失败(密钥值数位错误).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "信息加密失败(不受支持的密码和算法).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "信息加密失败(不受支持的运行模式).",
            ServiceError_ENCRYPT_FAILED: "信息加密失败(.",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "信息解码失败(输入值无效).",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "信息解码失败(无密钥值).",
            ServiceError_DECRYPT_FAILED_IV_IS_NULL: "信息解码失败(不受支持的padding).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "信息解码失败(密钥值数位错误).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "信息解码失败(不受支持的解码及算法).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "信息解码失败(不受支持的运行模式).",
            ServiceError_DECRYPT_FAILED: "信息解码失败.",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "电子信封失败(输入值无效).",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "电子信封失败(输入的认证书不是x509).",
            ServiceError_ENVELOPE_FAILED: "电子信封失败(其他错误).",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ERROR: "無法讀取該文件（檢查路徑）.",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ERROR: "無法寫入文件（檢查路徑）.",
            ServiceError_ENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "由於配股未能讀取文件.",
            ServiceError_ENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "由於配股未能提交了一封信.",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "电子信封解码失败(输入值无效).",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "电子信封解码失败(取消输入).",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "电子信封解码失败(请确认PIN码).",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "电子信封解码失败(取消密码).",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: "無法讀取文件.",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: "無法寫入文件.",
            ServiceError_DEENVELOPE_FAILED: "电子信封解码失败.",
            ServiceError_DEENVELOPE_FAILED_FILE_READ_ACCESS_DENIED: "由於配股未能讀取文件.",
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE_ACCESS_DENIED: "由於配股未能提交了一封信.",
            ServiceError_DEENVELOPE_FAILED_INVALID_TEXT: "它沒有電子信封解碼（密文被損壞或該文件的位置是無效的）。",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "PFX电子签名失败(其他错误).",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "PFX电子签名失败(取消选择).",

            /* import, export certificate and key */
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INVALID_ARGUMENT: "無法導出證書.（無效輸入）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_INPUT_CANCELED: "導出證書失敗。 （取消了密碼）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_PRIVATEKEY_FAILED: "導出證書失敗（找不到私鑰）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_SEARCH_CERTIFICATE_FAILED: "導出證書失敗。 （我沒找到證書）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_INCORRECT: "導出證書失敗（檢查PIN碼）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PIN_LOCKED: "導出證書失敗。 （該設備被鎖定）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED_PWD_INCORRECT: "導出證書出現故障（檢查密碼）",
            ServiceError_EXPORT_CERTIFICATE_AND_KEY_FAILED: "導出證書出現故障",

            /* mac address error message */
            ServiceError_MAC_ADDRESS_ERROR: "無法導入MAC地址。",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_HANDLE: "導入失敗DLL處理。",
            ServiceError_MAC_ADDRESS_FAILED_GET_DLL_FUNC: "無法導入DLL函數。",
            ServiceError_MAC_ADDRESS_NONVALIDATED_RETURN_FUNC: "函數的返回值是無效的。",
            ServiceError_MAC_ADDRESS_FAILED_MEMORY: "它沒有分配內存。",
            ServiceError_MAC_ADDRESS_FAILED_REG_OPEN: "無法打開註冊表。",
            ServiceError_MAC_ADDRESS_FAILED_GET_REG_VALUE: "獲取註冊表值失敗。",
            ServiceError_MAC_ADDRESS_FAILED_REG_CLOSE: "要關閉註冊表失敗。",
            ServiceError_MAC_ADDRESS_FAILED_GET_LAST_NETID: "瞻NETID失敗。",

            /* getFilePath */
            ServiceError_GET_FILE_PATH_CANCELED: "它取消了文件選擇.",

            /* securedisk */
            ServiceError_SECUREDISK_BACKUP_ERROR: "安全磁盤發生故障的備份和恢復。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_CERTINDENTIFIER: "無效值（證書） 。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_KEYINDENTIFIER: "關鍵值無效。",
            ServiceError_SECUREDISK_BACKUP_FAILED_INVALID_TOKENINDENTIFIER: "令牌值無效。",
            ServiceError_SECUREDISK_BACKUP_INVALID_VALUE: "該值是無效的。",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_BACKUPCERTLIST: "無法創建一個備份證書列表。",
            ServiceError_SECUREDISK_BACKUP_FAILED_FIND_SECURETOKEN: "找到一個安全的磁盤故障。",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_HANDLE: "進入手柄不存在。",
            ServiceError_SECUREDISK_BACKUP_NOT_EXIST_DATA: "數據是無效的，或不存在。",
            ServiceError_SECUREDISK_BACKUP_INVALID_RANGE: "數據的範圍是不正確。",
            ServiceError_SECUREDISK_BACKUP_INVALID_PIN: "密碼是無效的。",
            ServiceError_SECUREDISK_BACKUP_FAILED_CASTING: "它無法轉換。",

            /* pin complex check */
            ServiceError_CHECK_COMPLEX_PIN_ERROR: '密碼完整性檢查錯誤',
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PINS: "沒有輸入該值.",
            ServiceError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: "您輸入的兩個密碼不匹配.",
            ServiceError_CHECK_COMPLEX_PIN_NOT_SHORT_LENGTH: "更改您的密碼，請輸入超過30位到10位以內。",
            ServiceError_CHECK_COMPLEX_PIN_SAME_PWD: "新的證書是一樣的以前的密碼，證書密碼。",
            ServiceError_CHECK_COMPLEX_PIN_GETPIN_FAILED: "未能獲得輸入（鍵盤聯鎖安全錯誤） 。",
            ServiceError_CHECK_COMPLEX_PIN_INVALID_PIN: "您輸入的密碼是違背創建的規則。",
            ServiceError_CHECK_COMPLEX_PIN_UPDATE_ERROR: '請更改您的密碼再次增強安全證書至少10個字符。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_CHAR: '請至少包含一個數字和特殊字符。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_NUMBER: '包括一個或多個字母和特殊字符。',
            ServiceError_CHECK_COMPLEX_PIN_ONLY_SPECIAL: '請至少包含一個數字和字母字符。',
            ServiceError_CHECK_COMPLEX_PIN_NO_CHAR: '請至少包含一個字母字符。',
            ServiceError_CHECK_COMPLEX_PIN_NO_NUMBER: '包括至少一個數字。',
            ServiceError_CHECK_COMPLEX_PIN_NO_SPECIAL: '請至少包括一個特殊字符（\“，\'，\\，\ |除外）',
            ServiceError_CHECK_COMPLEX_PIN_PATTERN: "新密碼創建規則方面：\n1 。在連續的文本，如1111 ，漲幅超過四倍AAAA \n2不可用。 1234禁用或連續四次為ABCD字母\n3 。利用連續兩個字母，如ABABAB \n 4禁止至少三次。禁止使用三種或更多的連續的字母兩次，因為ABCABC \n",

            /* keyboardProtection */
            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "输入值无效.",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "键盘安全联动失败.",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "键盘安全初始化失败.",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "获取输入值失败(键盘联动出错).",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "键盘安全公开密钥获取失败.",

            /* mobile usim */
            ServiceError_MOBILE_USIM_NOT_PRESENT: "未安装智能认证程序.",
            ServiceError_TOKEN_MOBILE_USIM_INVALID_OPTIONS: "智能认证选择无效.",
            ServiceError_MOBILE_USIM_USER_CANCELED: "取消智能认证.",

            /* mobisign */
            ServiceError_MOBISIGN_INVALID_OPTIONS: "Mobisign這個選項是無效的。",
            ServiceError_MOBISIGN_USER_CANCELED: "mobisign已被取消.",
            ServiceError_MOBISIGN_NOT_LOADED: "MobiSign故障模塊加載。",

            /* relay.raon */
            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "认证书复制失败(需要验证码请求).",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "认证书复制失败(验证码请求失败).",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "认证书复制失败(没有已发送的认证书).",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "认证书复制失败(请输入发送的终端验证码).",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "认证书复制失败(认证书导出失败).",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "认证书复制失败(验证码不一致).",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "认证书复制失败(不受支持的令牌).",
            // 금보원 인증서 탈취 취약점 관련 수정사항
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_INPUT_CANCELED: "密码输入已被用户取消。",
            ServiceError_RELAY_RAON_CERTIFICATE_INVALID_CERTINDENTIFIER: "找不到证书。",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED: "证书复制失败（请检查您的密码）。",
            ServiceError_RELAY_RAON_VERIFY_PIN_FAILED_NOT_MATCHED_PWD: "证书复制失败（请检查您的密码）。",
            ServiceError_RELAY_RAON_TOKEN_FUNCTION_NOT_SUPPORTED: "证书复制失败（不支持的媒体）。",
            ServiceError_RELAY_RAON_IVALID_REF_NUM: "证书复制失败（授权号验证失败）。",

            /* certificateSynchronize */
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_CERTIDNTIFIER: "無效的輸入。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INVALID_ARGUMENT: "無效的輸入。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_INPUT_CANCELED: "它是由用戶密碼取消。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED: "它未能改變密碼。",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_SIGN_PWD_FAILED: "無法更改密碼（檢查簽名的密碼）",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_KM_PWD_FAILED: "它未能改變密碼（用於密鑰分發檢查密碼）",
            ServiceError_CERTIFICATE_SYNCHRONIZE_FAILED_FIND_KMCERTIFICATE: "證書不存在密鑰分發。",

            /* script */
            ScriptError_NOT_UNDEFINED_ERROR: "未定义的错误发生.",
            ScriptError_INCORRECT_TYPE_ERROR: "形式不正确的错误发生.",

            ScriptError_SIGNATURE_P7_ERROR: "未能電子簽名（ P7）.",
            ScriptError_SIGNATURE_P1_ERROR: "未能電子簽名（ P1）.",
            ScriptError_VERIFY_P7_MAKESIGNEDDATA_ERROR: "它無法驗證（無效簽名語句）.",
            ScriptError_VERIFY_P7_ERROR: "它無法驗證（ P7）.",
            ScriptError_VERIFY_P1_ERROR: "它無法驗證（ P1）.",

            ScriptError_DECRYPTKEY_ERROR: "請確認您的密碼（ _decryptKey ）.",
            ScriptError_ENCRYPTKEY_ERROR: "失敗的私有密鑰加密（ _encryptKey ）.",

            ScriptError_IMPORT_FILE_IS_NOT_P12: "它沒有導入證書（而不是在PFX格式的證書）.",
            ScriptError_IMPORT_INCORRECT_PIN: "無法導入證書（請確認您的密碼）.",

            ScriptError_CANCEL_BUTTON_EVENT: "关于 '取消' 键的特殊事件发生.",

            ScriptError_CHECK_COMPLEX_PIN_NOT_SAME_PWD: "新的證書是一樣的以前的密碼，證書密碼",
            ScriptError_CHECK_COMPLEX_PIN_PATTERN: "新密碼創建規則方面：\n1 。在連續的文本，如1111 ，漲幅超過四倍AAAA \n2不可用。 1234禁用或連續四次為ABCD字母\n3 。利用連續兩個字母，如ABABAB \n 4禁止至少三次。禁止使用三種或更多的連續的字母兩次，因為ABCABC \n",
            ScriptError_CHECK_COMPLEX_PIN_SAME_PWD: '新的證書是一樣的以前的密碼，證書密碼。'
        }
    ];

    function getBrwoserLang() {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage.toLowerCase();
    };

    var getErrorMessage = function (code, mode) {
        // 공통저장소 7자리 넘어옴. 앞 4자리 + 000 일곱자리 다시 생성.
        if ((typeof code.legnth !== 'undefined' && code.length == 7) || code >= 1000000) {
            code = code + '';
            code = code.substr(0, 4) + '000';
            code = Number(code);
        }
        
        var _mode = (typeof mode === 'undefined' || '' + mode == '' || mode > language.length) ? type[getBrwoserLang()] : mode;
        var _message = language[_mode];
        for (var i in _message) {
            if (vest.error.errorCode[i] == code) {
                return _message[i];
            }
        }
        return 'undefined';
    };

    if (vest) {
        vest.error.getErrorMessage = getErrorMessage;
    }
})(window, vest);