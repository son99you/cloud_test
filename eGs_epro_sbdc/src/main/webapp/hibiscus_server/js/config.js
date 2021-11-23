var StorageConfig = {
    INDEXEDDB: {
        DBNAME: 'yt.hibiscus.db',
        SCHEMAVERSION: '1',
        SECUREKEY: 'yt.hibiscus.db.securekey',
        STORENAME: 'yt.hibiscus.db.storage',
        SECKEY: 'yt.hibiscus.db.seckey',
        SESSIONKEY: 'yt.hibiscus.db.sessionkey'
    },

    LOCALSTORAGE: {
        NAME: 'yt.hibiscus.ls',
        CERTIFICATELIST: 'yt.hibiscus.ls.certificatelist',
        CERTIFICATE: 'yt.hibiscus.ls.certificate',
        CUSTOMSID: 'yt.hibiscus.ls.customsid',
        SECKEY: 'yt.hibiscus.ls.seckey',
        DBKEY: 'yt.hibiscus.ls.dbkey',
        BACKUP_FLAG: 'yt.hibiscus.ls.backupflag'
    },

    // webcrypto, fingerprint, server
    STORAGETYPE: {
        mainType: 'webcrypto',          // 우선적으로 동작
        subType: 'fingerprint'          // main 지원여부 체크 후, 지원하지 않을 시 동작
    },

    // key server 주소
    KEYSERVER: {
        url: 'https://172.16.10.116:18443/KeyServer/ReqKey'
    },

    // ca, rootCA 인증서를 저장하고 있는 서버 주소
    CERTSERVER: {
        url: 'https://172.16.10.136:8443/HibiscusServer/cert/'
    },

    WEBCMP: {
        url: location.protocol + '//' + window.location.host + '/SignKorea.cmp'
    },

    // 중계서버
    RELAY: {
        serviceURL: 'https://172.16.10.116:18443/icr',
        siteCode: '0000'
    },

    // 금결원 클라우드 서비스
    OPENCERT: {
        // apiKey: 'ZGFmZHNhZHNhZmRzYWZkc2FmZA==' // 운영
        use: true,
        apiKey: 'bdc1308a82840fc42dab388c6c1e77e317922ddff0a2dc084e281ee6d5458ff77ba9fc33d2d83d4565fcc3ff44650057', // 개발
        corp: '1721301' // 기관코드 
    },
    
    // 인증서 backup, restore 사용여부
    BACKUP: {
        use: false
    }
};

if(typeof StorageConfig !== 'undefined') {
    window.StorageConfig = StorageConfig;
}