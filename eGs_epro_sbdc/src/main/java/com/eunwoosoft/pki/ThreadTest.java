/**************************************************************
 
  Copyright (c) 2016 KTNET
  
  http://www.ktnet.co.kr

  Korea Paperless Trade Center(Pangyo TechnoVally), 338, Pangyo-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, South Korea 

  All rights reserved.
 
  프로그램에 대한 저작권을 포함한 지적재산권은 KTNET에 있으며,
  KTNET이 명시적으로 허용하지 않은 사용, 복사, 변경, 제3자에의 공개,
  배포는 엄격히 금지되며, KTNET의 지적재산권 침해에 해당됩니다.

  You are strictly prohibited to copy, disclose, distribute,
  modify, or use this program in part or as a whole without
  the prior written consent of KTNET.

  KTNET owns the intellectual property rights in and to this program.

***************************************************************/
package com.eunwoosoft.pki;

import tradesign.crypto.provider.JeTS;
import tradesign.crypto.provider.rsa.RSAPrivateKey;
import tradesign.pki.pkcs.EncPrivateKeyInfo;
import tradesign.pki.pkix.X509Certificate;
import tradesign.pki.util.JetsUtil;

/**
 * 인증서 본인확인 예제
 * 
 * @author KTNET
 *
 */

public class ThreadTest extends Thread {
	// 해당 쓰레드가 실행되면 자기 자신의 모니터링 락을 획득
	// 5번 반복하면서 0.5초씩 쉬면서 total에 값을 누적
	// 그후에 notify()메소드를 호출하여 wiat하고 있는 쓰레드를 깨움
	int total;

	@Override
	public void run() {
		synchronized (this) {
			try {
//				Properties prop = new Properties();
//				Reader reader = Resources
				JeTS.installProvider("D:/eGovFrameDev-3.6.0-64bit/workspace/ktNet_Library/src/config/tradesign3280.properties");

//				byte[] pwd = "CERT_PASSWORD_FOR_MOA".getBytes();
                byte[] pwd = {67, 69, 82, 84, 95, 80, 65, 83, 83, 87, 79, 82, 68, 95, 70, 79, 82, 95, 77, 79, 65};

                String privB64 = "MIIFPTBHBgkqhkiG9w0BBQ0wOjAaBgkqhkiG9w0BBQwwDQQIMuj6RAI1L/ECAWQwHAYIKoMajJpEAQQEEA02f+BDe4xvlC/jxJ4mJjMEggTwnpgul/cbaToA6vhZZ4NqreYHk3hYOKi5y2WIbqyDEbi8/2ae1MB3JXm+kXBHcU7DEFcoJ17LmutcIOZTC64DqdA5QVZonWXMPdkGgpLCNZU4ZFXAzmGyEXfU1xvZKsTNKh42BnMnhfLdqOUs8JDfyAThgNFUDByMso+ayR9aKjg71WURvxw9kQZwLIxLAUuA/yuh0D5m9NRCZnBWdpMg09b7UNYNDpTkljwaD4jJpZAevrimu4H2E5m4I2ARaIBzuof/nVCUaYafe6/zzWMDYm/D4MEjIaH985+69X5M4Zv0nqP2ZTkAqzR6ZU8i4aUOxjxMywH3mYSrqT6hOAjnn3Fy7t3TqEl7HqwwET934FcMoLv2z7fZiEO6ontI29WlKEO3AJHpkXA5Ud0fY5DDphfrJuneir5DRJd8UlDSBdCCYfCH8JPvaGhZQyq4tvnXNsxqKF6Y18n5UVLAWabhWkgaukvRDLXlhGyD46FDJvZoN+Eo3zh70SI57IACdP+/O2ytrg/BFOI11f90z48Wfl/PcVMsTp3edUnppSVuq0zKkvaFz6Rd6u3adEBJJeFGeuaccmK6YNHzzpwfqfD8+7eJgVT0Dn0uN0YPVvkLEPqdU3Wrq0YF/pcThTBBknkD/xRyNrRayj42ZdqCVe5NaLGlvwBTVJqIh4ZNVIpj2q7lArLdYgNZYoVs/4CuRRLxIVLc+zBj+C+LV38Ys19DutfyQ7QS6RaLu1mUJWb8QhEqkRe0x40XDsLzl2+VRCF4dg+LCwOXbSGRLf/z3EJFFkif7arN9hDupxwMI+rLM0miWiXsvUyww51HkkKncSq7N5y7RiWCTJYmcUajnzMtXV/ogUYvcJI/JVq5lGTfZ6XAy47yNV3TGMxpxKnpXCcz0hfnoMokntjicsADVrRjUJw3Wx9841rQ+PVyIbc2O7a7hj4FIAI8ZDhILJFj1y7oTiwiGb+RuGKlUDXPOVQkP7tEwLvwwwfgTjSlpn/cHAZ4390UstCvFH3rqshB2FKTE/uFaI8Uf/uHbw2ECABzOfQjIwU3rXQOVob/AwZYUqIoTlFTIJTCTVn2wDxsv+JiPYhqnP7w1JJFqM/lPrSTicGeZkfXVl8D/GLEcIP21Q9X0b1jptZCDm0E1tb43vpFi8BnVs+u9zzD3CnizLxibkI1hF53N4FSM73OULVh7TCNQ+AUuhMI2H4J0vAqa9XBPQDg+GhZCG/Gs6H11pqWe8KoYjQxf+9AIzS83lSTtUtjKy9823Dw/99evEvfeuseJ/edp1fY6e+vRjjwcqyuXA6y83C1ahvwUzjuzCtF9e9tIlUiJKf/9BjTEjh5l1JtrG01aVrL6aZE/pM1bNUiv2/hLS+vxKrOg2iMPzmTjc/qie0t7Z6ik9xemnRvZs7Q9LbUUj/xk0/v4ivQl+b7xlN6CUAGa9EPYSwrclJOJ27G0+dJWTQuk65w5lfEAAF/Dxb0u8/eF9sPFRqyZBjNIDEKVP1KRO/phNFMKIKmEFEpxkSioxHbL3wW+9YuTlsw4eUkAjUhxQ4p/HmCt+M0F0K3prrHksK1VF1688vx5s6A4QdmQcmt83uGJLKl9AasLfrYGtnDk6wxq+rIgYSmTVSFZk6SK3uo1IM4Jof06bCs3WpA1eP80yf0s1YNWYbN55qOIKc6j1uLwfRFQ8Bq/Q==";
                String certB64 = "MIIFjDCCBHSgAwIBAgIEWaLs7zANBgkqhkiG9w0BAQsFADBPMQswCQYDVQQGEwJLUjESMBAGA1UECgwJVHJhZGVTaWduMRUwEwYDVQQLDAxBY2NyZWRpdGVkQ0ExFTATBgNVBAMMDFRyYWRlU2lnbkNBMjAeFw0xNzA2MTQwMDQ0MzVaFw0xODA3MDYwNTUyMDlaMGExCzAJBgNVBAYTAktSMRIwEAYDVQQKDAlUcmFkZVNpZ24xEzARBgNVBAsMCkxpY2Vuc2VkQ0ExDjAMBgNVBAsMBUtUTkVUMRkwFwYDVQQDDBDthYzsiqTtirgoS1RORVQpMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu2l/qN/fnj7YN1SpaUmi6nLmv+YW1xhW2cQ45Np8JomaAGC1lcF3fL3cPer6cpIPbGYeSaJF/W3PL+oFlDBmHBZz1HfYFuKbZa/Q4LUxImX/GzaU3ZZjcnKtT9+qRMmyllX3d30vXyynREX3aCvRbmyh84WIq502SFHq8cmxm39T3MfUheM9z1nUcpDCKzz3zOHG0wkVa2Bm2/QYN6WAQNBDRRnjew4erHcj+W+1gaxGwowhn8FDWgEi/c77ISH0gnz0NWzkFC/xWvCD2BVgbG9s+uFx6F9M/Cr6yQrHik3cRsJUaJQCwRQmum5WEBHMiC/jOeQ7r8a/PFj/4RohkQIDAQABo4ICXDCCAlgwgY8GA1UdIwSBhzCBhIAUTV1WCgcD34PK89Vtjxn8EqyQooqhaKRmMGQxCzAJBgNVBAYTAktSMQ0wCwYDVQQKDARLSVNBMS4wLAYDVQQLDCVLb3JlYSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSBDZW50cmFsMRYwFAYDVQQDDA1LSVNBIFJvb3RDQSA0ggIQCTAdBgNVHQ4EFgQUhu0ZCYJktUr9iWNJ6GOb9o4kU94wDgYDVR0PAQH/BAQDAgbAMHoGA1UdIAEB/wRwMG4wbAYJKoMajJpMAQEDMF8wLgYIKwYBBQUHAgIwIh4gx3QAIMd4yZ3BHLKUACCs9cd4x3jJncEcACDHhbLIsuQwLQYIKwYBBQUHAgEWIWh0dHA6Ly93d3cudHJhZGVzaWduLm5ldC9jcHMuaHRtbDBoBgNVHREEYTBfoF0GCSqDGoyaRAoBAaBQME4MCe2FjOyKpO2KuDBBMD8GCiqDGoyaRAoBAQEwMTALBglghkgBZQMEAgGgIgQgq/tvozk67DKzEG31mjkNWsLNRDD33kCUB+eDI92LShQwZwYDVR0fBGAwXjBcoFqgWIZWbGRhcDovL2xkYXAudHJhZGVzaWduLm5ldDozODkvY249Y3JsMWRwMTE0NSxvdT1jcmxkcDIsb3U9QWNjcmVkaXRlZENBLG89VHJhZGVTaWduLGM9S1IwRgYIKwYBBQUHAQEEOjA4MDYGCCsGAQUFBzABhipodHRwOi8vb2NzcC50cmFkZXNpZ24ubmV0OjE4MDAwL09DU1BTZXJ2ZXIwDQYJKoZIhvcNAQELBQADggEBAF+sIkrMg2poUvKij4uDzQXOzOirwCscBCfVu1QOik6lJ2X2ZvPWwdp8M5WnMA+0o9kQ2kLmf+Q6PkAwIOIZM0+HhGJMqjDhdwQw+IW91q+bL9E8lLREipYn+z+td/EXRvZAM9myQfVlhys7ACjbtaoBPjetTERSxZmxOsGrLtvhLS1PzZU+xiBwqI1RSQ/ahIV2ie3d953VfmcoUwpcoea+HVVPvtFejLHSiLN2AfYqhGwcEsYOeM1wWQJId+qh1BAqlVmPHLficC+vP2GCyz2UDnP4ejIaQ0oHdeow6ibZq/zJGqrhIm9kEqKQN88BSB3TTAJOfa0k7OyyQUTQpLc=";

				byte[] privBytes = JetsUtil.base64ToBytes(privB64);
				byte[] certBytes = JetsUtil.base64ToBytes(certB64);

				EncPrivateKeyInfo enc = new EncPrivateKeyInfo(privBytes);
				RSAPrivateKey priv = (RSAPrivateKey) enc.decrypt(pwd);
				byte[] random = priv.getRandom();
				String idn = "1234567890"; // 주민번호 혹은 사업자 번호

				X509Certificate x509 = new X509Certificate(certBytes);

				// if (x509.VerifyIDN(idn.getBytes(), random))
				if (x509.VerifyIDN(idn.getBytes(), random)) System.out.println("본인확인 성공!");
				else System.out.println("본인확인 실패!");

				for (byte b : pwd) { b = 0; }

//				System.gc();
//				System.runFinalization();
//				System.gc();
//				System.runFinalization();
				//Thread.sleep(500000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}