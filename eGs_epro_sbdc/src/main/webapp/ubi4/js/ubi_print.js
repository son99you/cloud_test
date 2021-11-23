function pdfPrint(filename, printflag, downfilename) {
		console.log('pdfPrint : ' + filename);
		console.log('printflag : ' + printflag);
		console.log('downfilename : ' + downfilename);
		var pdfobj = null;
		
		var divPrint = document.getElementById("UbiHTMLViewerDiv_print");
		if (!divPrint) {
			divPrint = document.createElement("div");
			divPrint.id = "UbiHTMLViewerDiv_print";
		//	this.divMain.appendChild(divPrint);
		}
		var isAdobePrint = false;
		
		// IE - PdfObj 사용
		if( __ubi_isIE && printflag) {
			// useAdobeReader가 false이면 무조건 저장
			if( _ubi_checkPdfReader() ) {
				pdfobj = document.createElement("object");
				pdfobj.setAttribute('id', this.vid+'UbiPdfDoc');
				pdfobj.setAttribute('classid', 'CLSID:CA8A9780-280D-11CF-A24D-444553540000');
				pdfobj.setAttribute('type', 'application/pdf');
				pdfobj.style.position = "absolute";
				pdfobj.style.top = "0px";
				pdfobj.style.width = "0px";
				pdfobj.style.height = "0px";
				divPrint.appendChild(pdfobj);
		
				try {
					pdfVersion = pdfobj.getVersions();
					pdfVersion = pdfVersion.substring(pdfVersion.indexOf('=')+1, pdfVersion.indexOf('.'));
					if (((__ubi_ieVersion > 10) && parseInt(pdfVersion) < 10) || parseInt(pdfVersion) <= 6) {
						// IE버전이 10이상이고 Acrobat Reader가 10 버전 이하일 경우는 DownLoad 인쇄
						isAdobePrint = false;
					} else {
						isAdobePrint = true;
					}
				}
				catch(e){
					// 버전정보를 가져오지 못할 경우 PDF는 설치되어 잇는데 PDF 하위버전으로 함수가 제공 안될 경우이므로 File로 Download
					isAdobePrint = false;
				}
			} else {
				isAdobePrint = false;
			}
			console.log('isAdobePrint : ' + isAdobePrint);
			if( isAdobePrint ) {
				
				var url = "";
				var ubiserverurl = "/ubi4/ubi_print.jsp";//"/UbiServer/ubieform/ubieform_print.jsp";
				//ubiserverurl = ubiserverurl.toUpperCase();
				// ubiserverurl이 상대 경로로 되어 있을 경우 앞에 주소를 붙인다. 
				if (ubiserverurl.indexOf("HTTP") != 0) {
					ubiserverurl = self.location.protocol +"//"+ self.location.host + ubiserverurl;
				} else {
					ubiserverurl = this.params.ubiserverurl;
				}
				
				url = ubiserverurl + "?exportfilename=" + filename +"&&printflag=" +printflag;
				
				pdfobj.src = url;
				if (printflag) {
					pdfobj.setCurrentPage(1);
					pdfobj.printWithDialog();
				}
				else {
				
					var framename = 'UbiHTMLViewerDiv_ubipdf';
					var formname = 'UbiHTMLViewerDiv_pdfForm';
					console.log('2');
					var postUrl = [];
					console.log('3');
					postUrl.push("<iframe id='" + framename + "' name='" + framename + "' width='200' height='200' style='position:absolute; top:20px; left:50px; display:none;'></iframe>");
					console.log('4'); 
					postUrl.push(" <form name='" + formname + "' method='post' action='/ubi4/ubi_print.jsp' target='" + framename + "'>");
					console.log('5');
					var inputArray = [];
					console.log('6');
					inputArray.push("<input type='hidden' name='exportfilename' value='" + filename + "'>\n");
					inputArray.push("<input type='hidden' name='printflag' value='" + printflag + "'>\n");
					inputArray.push("<input type='hidden' name='downfilename' value='" + downfilename + "'>\n");
					console.log('7');
					postUrl.push(inputArray.join(''));
					console.log('8');
					postUrl.push("</form>");
					
					divPrint.innerHTML = postUrl.join();
					console.log('9');
					console.log(divPrint.innerHTML); 
					
					if (document.forms[formname] == undefined) {
						divPrint.innerHTML = "<iframe id='" + framename + "' name='" + framename + "' width='200' height='200' style='position:absolute; top:20px; left:50px; display:none;'></iframe>";
						var formobj = document.createElement("form");
						formobj.method = 'post';
						formobj.name = formname;
						formobj.action = "/ubi4/ubi_print.jsp";
						formobj.target = framename;
						divPrint.appendChild(formobj);
						formobj.innerHTML = inputArray.join("");				
					}
					document.forms[formname].submit();
					
					if(printflag) {
						var ifr = document.getElementById(framename);
						var params = this.params;
						var intervalCnt = 0;
						self.interval = setInterval(function() {
							//var embedobj = null;
							var isComplete = false;
							try {
								intervalCnt++;
								//embedobj=ifr.contentDocument.plugin; 
								//if (embedobj != undefined && ifr.contentDocument.readyState === 'complete') {
								if (document.getElementById(framename).contentWindow.location.href != 'about:blank' && ifr.contentDocument.readyState === 'complete') {
									isComplete = true;
									clearInterval(self.interval);
									document.getElementById(framename).focus();
									document.getElementById(framename).contentWindow.print();
								}
								
								if (intervalCnt >= 30) {
									clearInterval(self.interval);
									var w = window.open('', 'Ubi_PDF','menubar=no,toolbar=no,status=no,scrollbars=no');
									divPrint.innerHTML = "";
									var formobj = document.createElement("form");
									formobj.name = 'pdfForm';
									formobj.method = 'post';
									formobj.action = '/ubi4/ubi_print.jsp';
									formobj.target = 'Ubi_PDF';
									formobj.innerHTML = inputArray.join('');
									divPrint.appendChild(formobj);
									document.pdfForm.submit();
									
								}
							} catch (e) {
								clearInterval(self.interval);
								var w = window.open('', 'Ubi_PDF','menubar=no,toolbar=no,status=no,scrollbars=no');
								/*
								var postUrl = [];
								postUrl.push("<form name='pdfForm' method='post' action='" + params.ubiserverurl + "#toolbar=1' target='Ubi_PDF'>");
								postUrl.push(inputArray.join(''));
								postUrl.push("</form>");
								divPrint.innerHTML = postUrl.join('');
								*/
								divPrint.innerHTML = "";
								var formobj = document.createElement("form");
								formobj.name = 'pdfForm';
								formobj.method = 'post';
								formobj.action = '/ubi4/ubi_print.jsp';
								formobj.target = 'Ubi_PDF';
								formobj.innerHTML = inputArray.join('');
								divPrint.appendChild(formobj);
								document.pdfForm.submit();
							}
						}, 100);
					}		
				}
			}
			else {
				alert("Adobe Reader가 설치되어 있지 않습니다.\n저장 후 출력을 해주시거나 다른 브라우저를 이용해주시기 바랍니다.");
				/*
				// Adobe Reader를 정상적으로 불러오지 못한 경우 팝업을 띄움.
				if (this.ieAdobeReaderInfoPopup) {
					this.toolbar.makePrintPdfErrorMessage(flag, filename);
					var obj = document.getElementById(this.vid +"Popup_PdfError_Message");
					if (obj) {
						obj.style.display = "";
					}
					
				} else {
					this._isPrintPDF = true;
					this._exportEnd(flag, filename);
				}
				*/
			}
		}
		else {
			// Firefox는 PDF를 해석하여 브라우저에서 HTML로 다시 만들어 보여줌으로 정확하게 해석을 못할 경우가 있음
			//2015년 4월 24일 Chrome PDF Print window.close()로 인해 창이 사라지지 않는 현상으로 innerHTML사용
			if (__ubi_isChrome || __ubi_isOpera || __ubi_isWhale || __ubi_isSafari || __ubi_isFF || __ubi_isIE ) {
				console.log('1');
				var framename = 'UbiHTMLViewerDiv_ubipdf';
				var formname = 'UbiHTMLViewerDiv_pdfForm';
				console.log('2');
				var postUrl = [];
				console.log('3');
				postUrl.push("<iframe id='" + framename + "' name='" + framename + "' width='200' height='200' style='position:absolute; top:20px; left:50px; display:none;'></iframe>");
				console.log('4'); 
				postUrl.push(" <form name='" + formname + "' method='post' action='/ubi4/ubi_print.jsp' target='" + framename + "'>");
				console.log('5');
				var inputArray = [];
				console.log('6');
				inputArray.push("<input type='hidden' name='exportfilename' value='" + filename + "'>\n");
				inputArray.push("<input type='hidden' name='printflag' value='" + printflag + "'>\n");
				inputArray.push("<input type='hidden' name='downfilename' value='" + downfilename + "'>\n");
				console.log('7');
				postUrl.push(inputArray.join(''));
				console.log('8');
				postUrl.push("</form>");
				
				divPrint.innerHTML = postUrl.join();
				console.log('9');
				console.log(divPrint.innerHTML); 
				
				if (document.forms[formname] == undefined) {
					divPrint.innerHTML = "<iframe id='" + framename + "' name='" + framename + "' width='200' height='200' style='position:absolute; top:20px; left:50px; display:none;'></iframe>";
					var formobj = document.createElement("form");
					formobj.method = 'post';
					formobj.name = formname;
					formobj.action = "/ubi4/ubi_print.jsp";
					formobj.target = framename;
					divPrint.appendChild(formobj);
					formobj.innerHTML = inputArray.join("");				
				}
				document.forms[formname].submit();
				
				if(printflag) {
					var ifr = document.getElementById(framename);
					var params = this.params;
					var intervalCnt = 0;
					self.interval = setInterval(function() {
						//var embedobj = null;
						var isComplete = false;
						try {
							intervalCnt++;
							//embedobj=ifr.contentDocument.plugin; 
							//if (embedobj != undefined && ifr.contentDocument.readyState === 'complete') {
							if (document.getElementById(framename).contentWindow.location.href != 'about:blank' && ifr.contentDocument.readyState === 'complete') {
								isComplete = true;
								clearInterval(self.interval);
								document.getElementById(framename).focus();
								document.getElementById(framename).contentWindow.print();
							}
							
							if (intervalCnt >= 30) {
								clearInterval(self.interval);
								var w = window.open('', 'Ubi_PDF','menubar=no,toolbar=no,status=no,scrollbars=no');
								divPrint.innerHTML = "";
								var formobj = document.createElement("form");
								formobj.name = 'pdfForm';
								formobj.method = 'post';
								formobj.action = '/ubi4/ubi_print.jsp';
								formobj.target = 'Ubi_PDF';
								formobj.innerHTML = inputArray.join('');
								divPrint.appendChild(formobj);
								document.pdfForm.submit();
								
							}
						} catch (e) {
							clearInterval(self.interval);
							var w = window.open('', 'Ubi_PDF','menubar=no,toolbar=no,status=no,scrollbars=no');
							/*
							var postUrl = [];
							postUrl.push("<form name='pdfForm' method='post' action='" + params.ubiserverurl + "#toolbar=1' target='Ubi_PDF'>");
							postUrl.push(inputArray.join(''));
							postUrl.push("</form>");
							divPrint.innerHTML = postUrl.join('');
							*/
							divPrint.innerHTML = "";
							var formobj = document.createElement("form");
							formobj.name = 'pdfForm';
							formobj.method = 'post';
							formobj.action = '/ubi4/ubi_print.jsp';
							formobj.target = 'Ubi_PDF';
							formobj.innerHTML = inputArray.join('');
							divPrint.appendChild(formobj);
							document.pdfForm.submit();
						}
					}, 100);
				}
			}
			else if (__ubi_isMobile ||  saveflag) {
				// IE가 아닌 경우 생성된 PDF파일을 팝업 페이지를 띄움.
				var w = window.open('', 'Ubi_PDF','menubar=no,toolbar=no,status=no,scrollbars=no');
				var postUrl = "<form name='pdfForm' method='post' action='" + this.params.ubiserverurl + "#toolbar=1' target='Ubi_PDF'>";
				postUrl += "<input type='hidden' name='cHJvY2lk' value='GATEWAY'>"; 
				postUrl += "<input type='hidden' name='reqtype' value='94'>";
				postUrl += "<input type='hidden' name='reqsubtype' value='2'>";
				postUrl += "<input type='hidden' name='exportfilename' value='" + filename + "'>";
				postUrl += "<input type='hidden' name='jrffile' value='" + this.params.jrffile + "'>";
				postUrl += "<input type='hidden' name='resid' value='" + this.params.resid + "'>";
				postUrl += "<input type='hidden' name='key' value='" + this.params.key + "'>";
				postUrl += "<input type='hidden' name='serviceid' value='" + this.params.serviceid + "'>";
				postUrl += "<input type='hidden' name='exportseq' value='" + this.exportSeq + "'>";
				
				// 사용자 정의 param
				if (this.events.getUserParams) {
					var userparams = this.events.getUserParams();
					if (userparams != undefined) {
						for ( var i = 0; i < userparams.length; i+=2) {
							var str = "<input type='hidden' name='" + userparams[i] + "' value='" + userparams[i+1] + "'>";
							postUrl += str;
						}
					}
				}
				
				postUrl += "</form>";
				divPrint.innerHTML = postUrl;
				document.pdfForm.submit();
				
				// PrintEnd Event 호출
				if (this.events.printEnd) {
					this.events.printEnd(true);				
				}
			} else {
				this._isPrintPDF = true;
				this._exportEnd(flag, filename);
			}
		}
	}