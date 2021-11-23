//////////////////////////////////////////////////////
//// 고객이 해야하는 설정 (필수)
//// 1. 라이센스 : license (KTNET에 문의)
//// 2. 허용목록 : policySet
//// 3. 상단이미지 : bannerInfo
//// 4. 서버용 KM 인증서 : loginDataKmCert
//// 5. 중계 서비스 설정 : relayGroup (KTNET에 문의)
//////////////////////////////////////////////////////

var tsHTML5PKIConfig = {};
// 라이센스 키 설정. KTNET에서 라이센스 키를 발급받아야 합니다. (기본 허용 URL: localhost, 127.0.0.1)
tsHTML5PKIConfig.license = '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';
tsHTML5PKIConfig.common = {};
tsHTML5PKIConfig.common.logLevel = 0; // [ 0:error / 1:warn / 2:info / 3:log ]
tsHTML5PKIConfig.common.logRollingCount = 3; // 로그 유지 기간(단위: 일)
//tsHTML5PKIConfig.common.imgFolderURI = '../img/'; // 이미지 파일이 들어있는 폴더 지정
tsHTML5PKIConfig.common.imgFolderURI = '/ScoreHTML5/img/'; // 이미지 파일이 들어있는 폴더 지정
tsHTML5PKIConfig.common.clickOutSideEventClose = true; // 인증서 선택창 외부 클릭 시 창 닫힘
tsHTML5PKIConfig.pki = {};
tsHTML5PKIConfig.pki.common = {};
tsHTML5PKIConfig.pki.common.siteName 	= 'siteName';
tsHTML5PKIConfig.pki.common.bannerInfo	= tsHTML5PKIConfig.common.imgFolderURI+'TradeSign_main_logo.png'; // 인증서 선택창 상단에 들어갈 로고 이미지 파일 경로
tsHTML5PKIConfig.pki.common.CAList 		= 'TradeSign:한국무역정보통신|CROSSCERT:한국전자인증|KICA:한국정보인증|SignKorea:(주)코스콤|Yessign:금융결제원|INIPASS:(주)이니텍|';
tsHTML5PKIConfig.pki.common.LDAPInfo 	= 'KISA:ldap.tradesign.net:389|KICA:ldap.signgate.com:389|SignKorea:dir.signkorea.com:389|Yessign:ds.yessign.or.kr:389|CrossCert:dir.crosscert.com:389|TradeSign:ldap.tradesign.net:389|INIPASS:dir.inipass.com:389|';
// 허용할 인증서 목록 설정 (모두허용 : any, 개별설정 : 규칙에 맞게 OID 설정, 범용인증서는 필수로 설정)
tsHTML5PKIConfig.pki.common.policySet 	= 'any';
// tsHTML5PKIConfig.pki.common.policySet 	= '1.2.410.200012.1.1.3:범용법인|1.2.410.200012.1.1.61:TradeSign관리자용|';
// 기업용 범용 인증서 모음
// tsHTML5PKIConfig.pki.common.policySet  = "1 2 410 200012 1 1 3:전자거래범용(기업용)|1 2 410 200004 5 1 1 7:전자거래범용(기업용)|1 2 410 200005 1 1 5:전자거래범용(기업용)|1 2 410 200004 5 2 1 1:전자거래범용(기업용)|1 2 410 200004 5 4 1 2:전자거래범용(기업용)|";
// 개인용 범용 인증서 모음
// tsHTML5PKIConfig.pki.common.policySet  = "1 2 410 200012 1 1 1:전자거래범용(개인용)|1 2 410 200004 5 1 1 5:전자거래범용(개인용)|1 2 410 200005 1 1 1:전자거래범용(개인용)|1 2 410 200004 5 2 1 2:전자거래범용(개인용)|1 2 410 200004 5 4 1 1:전자거래범용(개인용)|";
tsHTML5PKIConfig.pki.common.loadKMCert	= true; // 고객의 KM 인증서 사용여부
tsHTML5PKIConfig.pki.common.viewExpiredCert = false; // 인증서 선택창에서 만료 인증서 보기 (인증서 관리 메뉴에는 항상 노출)

// 로그인 데이터에 사용할 서버용 KM 인증서
// tsHTML5PKIConfig.pki.common.loginDataKmCert = '-----BEGIN CERTIFICATE-----MIIFITCCBAmgAwIBAgIEWZHJYDANBgkqhkiG9w0BAQsFADBPMQswCQYDVQQGEwJLUjESMBAGA1UECgwJVHJhZGVTaWduMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFTATBgNVBAMMDFRyYWRlU2lnbkNBMjAeFw0xNTA1MTEwOTI1MTJaFw0xNjA3MDYwNTUyMDlaMGExCzAJBgNVBAYTAktSMRIwEAYDVQQKDAlUcmFkZVNpZ24xEzARBgNVBAsMCkxpY2Vuc2VkQ0ExDjAMBgNVBAsMBUtUTkVUMRkwFwYDVQQDDBDthYzsiqTtirgoS1RORVQpMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvsS6JEgXvTnVLVn8FXW2B8ZMVKYK7URGHQnf2egGwSxfL33ljI6DIvjeBT/OStNfTgDxTpmIr/zciExVC9P4ZiednRIkcnl3hPOmU1D4zZUpzV6FxwgLvJ39etxjKmabcQ40XQvZgjnpY3vggAfmKAbTdbl8uQvlm98qmbfrSZaiSQhTBMYF6wPgaJorY5vgZn7vPBWvWRpCRDcYMomktR8xd1JPU9cINbWQDbeh+jtoNCHCUG3kE9ZKQ48lY3+rP1E4CCADNJSJZMb5JgDRKLsdmknEyx5/AEoXk0bpnGzx0hh1PS+J7uRxOS5RONGwpS1VG0y6YsFY2V/2P9Tq8wIDAQABo4IB8TCCAe0wgY8GA1UdIwSBhzCBhIAUTV1WCgcD34PK89Vtjxn8EqyQooqhaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYDVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQCTAdBgNVHQ4EFgQUQm9lkzkFZxu42wDGXL5RFamVGLgwDgYDVR0PAQH/BAQDAgUgMHoGA1UdIAEB/wRwMG4wbAYJKoMajJpMAQEEMF8wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLQYIKwYBBQUHAgEWIWh0dHA6Ly93d3cudHJhZGVzaWduLm5ldC9jcHMuaHRtbDBmBgNVHR8EXzBdMFugWaBXhlVsZGFwOi8vbGRhcC50cmFkZXNpZ24ubmV0OjM4OS9jbj1jcmwxZHA1ODMsb3U9Y3JsZHAyLG91PUFjY3JlZGl0ZWRDQSxvPVRyYWRlU2lnbixjPUtSMEYGCCsGAQUFBwEBBDowODA2BggrBgEFBQcwAYYqaHR0cDovL29jc3AudHJhZGVzaWduLm5ldDoxODAwMC9PQ1NQU2VydmVyMA0GCSqGSIb3DQEBCwUAA4IBAQCYmlVQ2mOXmuGA9R3WW2OZBm+tK8jiBUr/htGSYfzFyvwPp67jGfOWa/SwDcQpvL5hA2yBGtrHHl0zaeE3IL+n4v8Ww0p9dkOqMX7aNHLI7Pa25WtMIMWJbkgQDWacWPfHg6rVIZKhTLNqM4kbOdnWUx7OIJGF4ufZdW7DSngY+uUrGL3lflOAh/WunURuBqLaXyg+G9g87R3GsGh8d5wuIEkAS3yuMeIjalXabLQBkOlOzcTjSKSTEi5/jAnDoEnhV5wxgd/nSPn/muh7ECPHTFzqH8wxnYZ8xTU6TKwTqYgAxxHcn7Qjh4yn/RwxXZ/y6nGfhRjlxbWSrthu+xkm-----END CERTIFICATE-----';
tsHTML5PKIConfig.pki.common.loginDataKmCert = '-----BEGIN CERTIFICATE-----MIIFITCCBAmgAwIBAgIEWZpPyDANBgkqhkiG9w0BAQsFADBPMQswCQYDVQQGEwJLUjESMBAGA1UECgwJVHJhZGVTaWduMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFTATBgNVBAMMDFRyYWRlU2lnbkNBMjAeFw0xNjA2MjEwNTE3MDZaFw0xNzA3MDYwNTUyMDlaMGExCzAJBgNVBAYTAktSMRIwEAYDVQQKDAlUcmFkZVNpZ24xEzARBgNVBAsMCkxpY2Vuc2VkQ0ExDjAMBgNVBAsMBUtUTkVUMRkwFwYDVQQDDBDthYzsiqTtirgoS1RORVQpMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzfow3ncW0W69HizlJclJBa8ezllNlCAs6sAWMCknvhiOYZHdr0ZIMT5AUeNdOuERVqszjirCT3VHzAPNtz3O00OjmDMii3+8pnLnWmRdjEBm7crahhEn72svv3RY0LOVjnWxpX+mMbuAdEYVj9N+mRSEkTw6bqzSdyV7w2rkkvhWM38gv8bIXBsmeGwBkahrN3AyIj4q2GL+A5kzbWcz/uyiaBQy7il9hbKLirCaCniyNVh1d/0v5+1DpE5ZmQZpwaJx4W/wsQyyn9ci0d6VbjZgNjFTAs57bQ2jeP/346WfgNKvNCsiHcfV3cTRX8q6g9dimQAcc/clI7H0WGCpbwIDAQABo4IB8TCCAe0wgY8GA1UdIwSBhzCBhIAUTV1WCgcD34PK89Vtjxn8EqyQooqhaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYDVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQCTAdBgNVHQ4EFgQUdZHTNkC1wD6VwPOTjZ31BRDnUgMwDgYDVR0PAQH/BAQDAgUgMHoGA1UdIAEB/wRwMG4wbAYJKoMajJpMAQEEMF8wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLQYIKwYBBQUHAgEWIWh0dHA6Ly93d3cudHJhZGVzaWduLm5ldC9jcHMuaHRtbDBmBgNVHR8EXzBdMFugWaBXhlVsZGFwOi8vbGRhcC50cmFkZXNpZ24ubmV0OjM4OS9jbj1jcmwxZHA4NjMsb3U9Y3JsZHAyLG91PUFjY3JlZGl0ZWRDQSxvPVRyYWRlU2lnbixjPUtSMEYGCCsGAQUFBwEBBDowODA2BggrBgEFBQcwAYYqaHR0cDovL29jc3AudHJhZGVzaWduLm5ldDoxODAwMC9PQ1NQU2VydmVyMA0GCSqGSIb3DQEBCwUAA4IBAQBTGG0u5pqdKuOuM/KnyF01mUrJ7ADurLA8jq98B7pi8bdd03U4HpKbA0GfZks+Aiz2Jb/4Mq0yW19W8HBtd51zwELfBE5F+N4eTXy/PqYhwSg1HW2/tKbPr1dBQTJSKGSWZmLJaf4fmEiwYpuUEOkt19axsa0yRWtj68H4kzfkm1+qBeCxPHFCznZV00lqW7W07nD6k4KwUHGv4FGZ/NAp/k4pIQ60rOeDiehs3HBRG2PtQwXAMbDRjioyyfndqzw96eqxg0/4QDq6JpLzMvqSi1VX82hJz6YeptdMVW987ucwz2tfBS15pv4sUmeds2abdUdrc7v964cb9LGl+9pM-----END CERTIFICATE-----';

// SignedData 옵션 설정
tsHTML5PKIConfig.pki.signedData = {};
tsHTML5PKIConfig.pki.signedData.includeCertChain 	= false;      // 체인 인증서 포함 여부
tsHTML5PKIConfig.pki.signedData.includeSignerCert 	= true;       // 서명자 인증서 포함 여부
tsHTML5PKIConfig.pki.signedData.includeSigningTime  = true;       // 서명시간 포함 여부
//tsHTML5PKIConfig.pki.signedData.includeCRL 		= true;       // CRL 포함 여부
tsHTML5PKIConfig.pki.signedData.includeContent 	    = true;       // 원문 포함 여부
tsHTML5PKIConfig.pki.signedData.signExpiredCert 	= false;      // 만료 인증서로 서명 가능 여부
//tsHTML5PKIConfig.pki.signedData.verifyWithCRL 		= true;   // 서명검증시 CRL 검증 포함 여부 (사용불가)
//tsHTML5PKIConfig.pki.signedData.verifyWithARL 		= false;  // 서명검증시 ARL 검증 포함 여부 (사용불가)

// TSA 설정
tsHTML5PKIConfig.tsa = {};
tsHTML5PKIConfig.tsa.hashAlg = 'SHA256';                                                // HASH 알고리즘 설정
tsHTML5PKIConfig.tsa.url = 'https://tsatest.tradesign.net/service/timestamp/issue';     // TSA 서비스 URL
tsHTML5PKIConfig.tsa.id = '';                                                           // TSA 서비스 ID
tsHTML5PKIConfig.tsa.passwd = '';                                                       // TSA 서비스 pwd

// 중계서비스 설정 (인증서 복사)
tsHTML5PKIConfig.relay = {};
tsHTML5PKIConfig.relay.relayServerURL = 'https://certrelay.tradesign.net';   // 중계 서비스 URL
tsHTML5PKIConfig.relay.relayGroup = 'TestGroup';                             // 중계 서비스 group
tsHTML5PKIConfig.relay.relaySiteName = 'TestGroup';                          // 중계 서비스 site name

// 보안키보드 관련 설정
tsHTML5PKIConfig.vKey = {};
tsHTML5PKIConfig.vKey.logoSrc = tsHTML5PKIConfig.common.imgFolderURI + 'construction.png';   // 보안키보드 안눌리는 키 아이콘 FULL PATH
tsHTML5PKIConfig.vKey.keyboardSrc = tsHTML5PKIConfig.common.imgFolderURI + 'keyboard.png';   // 보안키보드 열기 아이콘 FULL PATH
tsHTML5PKIConfig.vKey.bgColor = '#b4b4b4';                                                   // 보안키보드 바탕색 (변경 불필요)