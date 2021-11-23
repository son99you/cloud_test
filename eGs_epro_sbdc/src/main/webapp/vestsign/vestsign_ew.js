(function() {
	vestSign_ew = {};
	
	vestSign_ew.showCertType = function() {
		var type;
		var temp = document.getElementsByName( 'certType' );
	
		for (var i = 0, ii = temp.length; i < ii; ++i) {
			if (temp[i].checked)  type = temp[i].value;
		}
	
		if(type == 'personal'){  	// 개인
			certOID = '1.2.410.200004.5.2.1.2;1.2.410.200004.5.1.1.5;1.2.410.200005.1.1.1;1.2.410.200004.5.4.1.1;1.2.410.200012.1.1.1;';
			return certOID; 
		}else{         				//법인
			certOID = '1.2.410.200004.5.2.1.1;1.2.410.200004.5.1.1.7;1.2.410.200005.1.1.5;1.2.410.200004.5.4.1.2;1.2.410.200012.1.1.3;1.2.410.200012.5.29.1.121;1.2.410.200012.5.29.1.122;1.2.410.200012.5.2.1.41;1 2 410 200012 5 29 1 191;';
			return certOID;
		}
	};
	
	vestSign_ew.init_module = function(option) {

	    var config = {
	    		certificateClass : '16'
	    };
		//config.OID = vestSign_ew.showCertType();
	    config.firstTrigger = "H";
	    config.OID = FwkMssageUtil.getMessage("CONFIG.OID");	
		yettie.init(config);
	};
})();