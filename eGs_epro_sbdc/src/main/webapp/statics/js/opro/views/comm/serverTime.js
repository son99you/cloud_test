	var v_sYear		= "";
	var v_sMonth	= "";
	var v_sDay		= "";
	var v_sHour;
	var v_sMinute	= "";
	var v_sSecond	= "";
	var v_sD		= "";
	var clearId		= null;
	var v_Hour		= "";
	var v_Minute	= "";
	var v_Second	= "";

	serverTime = function(serverTimeInput, st){
		var systime = st;
		$("#serverTime").text(systime);
		systime = $("#serverTime").text();
//		serverTimeInput.value = systime;
//		systime = serverTimeInput.value;
		v_sYear 	= new Number(systime.substring(0, 4));
		v_sMonth 	= new Number(systime.substring(4, 6));
		v_sDay 		= new Number(systime.substring(6, 8));
		v_sHour 	= new Number(systime.substring(8, 10));
		v_sMinute 	= new Number(systime.substring(10, 12));
		v_sSecond 	= new Number(systime.substring(12, 14));
		v_sD 		= new Number(systime.substring(14, 15)) - 1;
		WorldTime();
	};

	WorldTime = function(){
		var leapyear2 = LeapYear2(v_sYear);
		if(v_sMinute<0){
	  		v_sMinute = 60 + v_sMinute;
	  		v_sHour = v_sHour - 1;
	  	}

	  	if(v_sHour<0){
	  		v_sHour = 24 + v_sHour;
	  		v_sDay = v_sDay - 1;
	  		v_sD--;
	  		if(v_sD < 0) v_sD = 6;
	  	}

	  	if(v_sDay<=0){
	  		v_sMonth = v_sMonth - 1;
	  		if(v_sMonth==1||v_sMonth==3||v_sMonth==5||v_sMonth==7||v_sMonth==8||v_sMonth==10||v_sMonth==12)
	  			v_sDay = 31 + v_sDay;
	  		else if(v_sMonth==4||v_sMonth==6||v_sMonth==9||v_sMonth==11)
	  			v_sDay = 30 + v_sDay;
	  		else if(v_sMonth==2 && leapyear2=="true")
	  			v_sDay = 29 + v_sDay;
	  		else if(v_sMonth==2 && leapyear2!="true")
	  			v_sDay = 28 + v_sDay;
	  		else if(v_sMonth<1){
	  			v_sYear = v_sYear - 1;
	  			v_sMonth = 11;
	  			v_sDay = 31;
	  		}
	  	}
	  	if(v_sSecond>=60){
	  		v_sSecond = v_sSecond - 60;
	  		v_sMinute = v_sMinute + 1;
	  	}

		if(v_sMinute>=60){
	  		v_sMinute = v_sMinute-60;
	  		v_sHour = v_sHour + 1;
	    }
	  	if(v_sHour>=24){
	  		v_sHour = v_sHour - 24;
	  		v_sDay = v_sDay + 1;
	  		v_sD++;
	  		if(v_sD > 6) v_sD = 0;
	  	}

	    if(v_sMonth==1||v_sMonth==3||v_sMonth==5||v_sMonth==7||v_sMonth==8||v_sMonth==10){
			if(v_sDay>31){
				v_sDay = 1;
				v_sMonth = v_sMonth + 1;
			}
		}
		else if(v_sMonth==4||v_sMonth==6||v_sMonth==9||v_sMonth==11){
			if(v_sDay>30){
				v_sDay = 1;
				v_sMonth = v_sMonth + 1;
			}
		}
		else if(v_sMonth==2 && leapyear2=="true"){
			if(v_sDay>29){
				v_sDay = 1;
				v_sMonth = v_sMonth + 1;
			}
		}
		else if(v_sMonth==2 && leapyear2!="true"){
			if(v_sDay>28){
				v_sDay = 1;
				v_sMonth = v_sMonth + 1;
			}
		}
		else if(v_sMonth==12){
			if(v_sDay>31){
				v_sDay = 1;
				v_sMonth = 0;
				v_sYear = v_sYear + 1;
			}
	    }

		(v_sMonth<10) ? v_Month="0"+v_sMonth : v_Month=v_sMonth;
	    (v_sDay<10) ? v_Day="0"+v_sDay : v_Day=v_sDay;
	    (v_sHour<10) ? v_Hour="0"+v_sHour : v_Hour=v_sHour;
	    (v_sMinute<10) ? v_Minute="0"+v_sMinute : v_Minute=v_sMinute;
	    (v_sSecond<10) ? v_Second="0"+v_sSecond : v_Second=v_sSecond;

	    v_ST=v_Hour + ":" + v_Minute + ":" + v_Second;

		//서버시간
		//document.getElementById( "serverTime" ).value = v_sYear + "년 " + v_Month + "월 " + v_Day + "일 " + v_ST;
		$("#serverDate").text(v_sYear + "-" + v_Month + "-" + v_Day);
		$("#serverTime").text(v_ST);
	    ++v_sSecond;

	    // 메모리 clear -할당된 메모리 영역을 반환하지 않음으로써 시스템 부하발생... 중요.
	    clearTimeout(clearId);
	    clearId = setTimeout("WorldTime(serverTime)",1000);
	};

	LeapYear2 = function(year){
		if((year%4)==0) return "true";
	};
