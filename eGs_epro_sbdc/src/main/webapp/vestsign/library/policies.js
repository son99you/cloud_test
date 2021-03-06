//version: 3.1.8_e54ce34761bf3c9776b1c6dd23fc7a4531eaad89
//update: Thu Jan 16 10:11:57 2020 +0900

/**
 * Created by wjdcks725 on 2015. 10. 5..
 */

 (function (window, vest) {
    'use strict';

    var classPolicies = (function() {

        var policies = [
            [   // 1
            "1.2.410.200004.5.1.1.5",
            "1.2.410.200005.1.1.1",
            "1.2.410.200004.5.2.1.2",
            "1.2.410.200004.5.3.1.9",
            "1.2.410.200004.5.4.1.1",
            "1.2.410.200012.1.1.1",
            "1.2.410.200004.5.5",
            "1.2.410.200004.5.5.1.1"
            ],
            [   // 16
                "1.2.410.200004.5.1.1.7",
                "1.2.410.200005.1.1.5",
                "1.2.410.200004.5.2.1.1",
                "1.2.410.200004.5.3.1.2",
                "1.2.410.200004.5.4.1.2",
                "1.2.410.200012.1.1.3",
                "1.2.410.200004.5.5.1.2"
            ],
            [   // 256
                "1.2.410.200004.5.1.1.9",
                "1.2.410.200004.5.2.1.7.2",
                "1.2.410.200004.5.4.1.102",
                "1.2.410.200012.1.1.103"
            ],
            [   // 
                "1.2.410.100001.2.1",
                "1.2.410.100001.2.1.1",
                "1.2.410.100001.2.1.2",
                "1.2.410.100001.2.1.3",
                "1.2.410.100001.2.1.4",
                "1.2.410.100001.2.1.5",
                "1.2.410.100001.2.1.6",
                "1.2.410.100001.2.2",
                "1.2.410.100001.2.2.1",
                "1.2.410.100001.2.2.2"

            ]
        ];

        function makeClass (arr, index) {
            for(var i=0; i < policies[index].length; i++)
                arr.push(policies[index][i]);

            return arr;
        };

        return {
            value: policies,
            makeClassPolicies: function(config) {
                if(config == 0 || config == '0') config = 273;

                var arr = [];
                if( (config & 0x00000001) == 1 ) {
                    arr = makeClass(arr, 0);
                }
                if( (config & 0x000000F0) == 16 ) {
                    arr = makeClass(arr, 1);
                }
                if( (config & 0x00000F00) == 256 ) {
                    arr = makeClass(arr, 2);
                }
                return arr;
            }
        }
    })();

    var policies = {
        // yessign : ???????????????
        '1.2.410.200005.1.1.1': {
            caName: 'yessignCA Class 1',
            //usage: '??????(??????)'
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.5': {
            caName: 'yessignCA-Test Class 1',
            //usage: '??????(??????)'
            usage: policiesLang(1),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.4': {
            caName: 'yessignCA Class 1',
            //usage: '??????/??????(??????)'
            usage: policiesLang(2),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.2': {
            caName: 'yessignCA Class 1',
            //usage: '??????/??????(??????)'
            usage: policiesLang(3),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.6.2': {
            caName: 'yessignCA Class 1',
            //usage: '???????????????'
            usage: policiesLang(4),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.6.8': {
            caName: 'yessignCA Class 1',
            //usage: '???????????????'
            usage: policiesLang(5),
            type: 'NPKI'
        },

        // SignKorea : ????????? : ??????????????????
        '1.2.410.200004.5.1.1.5': {
            caName: 'SignKorea CA2',
            //usage: '??????(??????)'
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200004.5.1.1.7': {
            caName: 'SignKorea CA2',
            //usage: '??????(??????)'
            usage: policiesLang(1),
            type: 'NPKI'
        },
        '1.2.410.200004.5.1.1.10': {
            caName: 'SignKorea CA2',
            //usage: '??????(??????)'
            usage: policiesLang(12),
            type: 'NPKI'
        },
        '1.2.410.200004.5.1.1.9': {
            caName: 'SignKorea CA2',
            //usage: '??????(??????)'
            usage: policiesLang(13),
            type: 'NPKI'
        },
        '1.2.410.200004.5.1.1.9.2': {
            caName: 'SignKorea CA2',
            //usage: '??????(??????)'
            usage: policiesLang(14),
            type: 'NPKI'
        },
        '1.2.410.200005.1.1.12.902': {
            caName: 'SignKorea CA2',
            //usage: '???????????????'
            usage: policiesLang(5),
            type: 'NPKI'
        },

        // CrossCert : ??????????????????
        '1.2.410.200004.5.4.1.1': {
            caName: 'CrossCertCA2',
            //usage: '??????(??????)'
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200004.5.4.1.2': {
            caName: 'CrossCertTestCA2',
            //usage: '??????(??????)'
            usage: policiesLang(1),
            type: 'NPKI'
        },
        '1.2.410.200004.5.4.1.101': {
            caName: 'CrossCertCA2',
            //usage: '??????/??????'
            usage: policiesLang(8),
            type: 'NPKI'
        },
        '1.2.410.200004.5.4.1.103': {
            caName: 'CrossCertCA2',
            //usage: '???????????????'
            usage: policiesLang(4),
            type: 'NPKI'
        },
        '1.2.410.200004.5.4.2.80': {
            caName: 'CrossCertCA2',
            //usage: '???????????????'
            usage: policiesLang(5),
            type: 'NPKI'
        },

        // signGATE : ??????????????????
        '1.2.410.200004.5.2.1.2': {
            caName: 'signGATE CA4',
            //usage: '??????(??????)'
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200004.5.2.1.1': {
            caName: 'signGATE FTCACA4',
            //usage: '1??????(??????)'
            usage: policiesLang(10),
            type: 'NPKI'
        },
        '1.2.410.200004.5.2.1.7.1': {
            caName: 'signGATE CA4',
            //usage: '??????/??????'
            usage: policiesLang(8),
            type: 'NPKI'
        },
        '1.2.410.200004.5.2.1.7.3': {
            caName: 'signGATE CA4',
            //usage: '???????????????'
            usage: policiesLang(4),
            type: 'NPKI'
        },
        '1.2.410.200004.5.2.1.6.369': {
            caName: 'signGATE CA4',
            //usage: '?????????'
            usage: policiesLang(6),
            type: 'NPKI'
        },

        // TradeSign : ????????????????????????
        '1.2.410.200012.1.1.1': {
            caName: 'TradeSignCA2',
            //usage: '??????(??????)'
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200012.1.1.3': {
            caName: 'TradeSignCA2011Test2',
            //usage: '??????(??????)'
            usage: policiesLang(1),
            type: 'NPKI'
        },
        '1.2.410.200012.1.1.101': {
            caName: 'TradeSignCA2',
            //usage: '??????/??????'
            usage: policiesLang(8),
            type: 'NPKI'
        },
        '1.2.410.200012.1.1.105': {
            caName: 'TradeSignCA2',
            //usage: '???????????????'
            usage: policiesLang(4),
            type: 'NPKI'
        },
        '1.2.410.200012.5.19.1.1': {
            caName: 'TradeSignCA2',
            //usage: '???????????????'
            usage: policiesLang(5),
            type: 'NPKI'
        },
        //inipass
        '1.2.410.200004.5.5': {
            caName: 'inipass',
            usage: policiesLang(17),
            type: 'NPKI'
        },
        '1.2.410.200004.5.5.1.1': {
            caName: 'inipass',
            usage: policiesLang(0),
            type: 'NPKI'
        },
        '1.2.410.200004.5.5.1.2': {
            caName: 'inipass',
            usage: policiesLang(1),
            type: 'NPKI'
        },
        // GPKI
        '1.2.410.100001.2.1': {
            caName: '???????????????',
            // usage: '?????????'
            usage: policiesLang(13),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.1': {
            caName: '???????????????',
            // usage: '????????????(??????)'
            usage: policiesLang(14),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.2': {
            caName: '???????????????',
            // usage: '????????????(??????)'
            usage: policiesLang(15),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.3': {
            caName: '???????????????',
            // usage: '???????????????(??????)'
            usage: policiesLang(16),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.4': {
            caName: '???????????????',
            // usage: '????????????(??????/??????)'
            usage: policiesLang(17),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.5': {
            caName: '???????????????',
            // usage: '????????????(??????/??????)'
            usage: policiesLang(18),
            type: 'GPKI'
        },
        '1.2.410.100001.2.1.6': {
            caName: '???????????????',
            // usage: '???????????????(??????/??????)'
            usage: policiesLang(19),
            type: 'GPKI'
        },
        '1.2.410.100001.2.2': {
            caName: '???????????????',
            // usage: '?????????'
            usage: policiesLang(20),
            type: 'GPKI'
        },
        '1.2.410.100001.2.2.1': {
            caName: '???????????????',
            // usage: '????????????'
            usage: policiesLang(21),
            type: 'GPKI'
        },
        '1.2.410.100001.2.2.2': {
            caName: '???????????????',
            // usage: '?????????(??????/??????)'
            usage: policiesLang(22),
            type: 'GPKI'
        }

    };

    if (vest) {
        vest.util = vest.util || (vest.util = {});
        vest.extend(vest.util, {
            'policies': policies,
            'classPolicies': classPolicies
        });
    }
})(window, vest);
