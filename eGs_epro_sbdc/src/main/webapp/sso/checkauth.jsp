<%@page import="org.apache.commons.httpclient.HttpClient"%>
<%@page import="org.apache.commons.httpclient.methods.PostMethod"%>
<%@page import="org.apache.commons.httpclient.NameValuePair"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.*"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.util.Iterator"%>
<%@include file="./agentInfo.jsp"%>
<%
    String resultCode = request.getParameter("resultCode") == null ? "" : request.getParameter("resultCode");
    String secureToken = request.getParameter("secureToken") == null ? "" : request.getParameter("secureToken");
    String secureSessionId = request.getParameter("secureSessionId") == null ? "" : request.getParameter("secureSessionId");
    System.out.println("secureSessionId : " + secureSessionId);
    
    String clientIp = request.getRemoteAddr();
    
    String logOutUrl = AUTHORIZATION_URL + "logout";
    String logOutUrlAgentId = logOutUrl + "?agentId=" + agentId;
    String resultMessage = "";
    String resultData = "";
    String returnUrl = "";
    
    session.setAttribute("secureSessionId", secureSessionId);
    
    Boolean isErrorPage=false;
    session.setAttribute("isErrorPage", isErrorPage);
    if (resultCode == "" || secureToken == "" || secureSessionId == "") {
        isErrorPage=true;
        session.setAttribute("isErrorPage", isErrorPage);
        System.out.println("error!!!");
        returnUrl = "error.jsp";
    } else {
        
        if (resultCode.equals("000000")) {
            System.out.println("checkauth page sessionId : " + session.getId());
            if (AUTHORIZATION_URL == "") {
                
                System.out.println("AUTHORIZATION_URL is empty");
                return ;
            }
            
            String TOKEN_AUTHORIZATION_URL = AUTHORIZATION_URL + "token/authorization";
            
            System.out.println("resultCode : " + resultCode);
            System.out.println("secureToken : " + secureToken);
            System.out.println("secureSessionId : " + secureSessionId);
            System.out.println("TOKEN_AUTHORIZATION_URL : " + TOKEN_AUTHORIZATION_URL);
            System.out.println("agentId : " + agentId);
            System.out.println("requestData : " + requestData);
            System.out.println("clientIp : " + clientIp);
            
            resultCode = "";
            resultMessage = "";
            resultData = "";
            returnUrl = "";
            
            PostMethod postMethod = null;
            try {
                postMethod  = new PostMethod(TOKEN_AUTHORIZATION_URL);
                NameValuePair[] nameValuePair = {
                    new NameValuePair("secureToken", secureToken),
                    new NameValuePair("secureSessionId", secureSessionId),
                    new NameValuePair("requestData", requestData),
                    new NameValuePair("agentId", agentId),
                    new NameValuePair("clientIP", clientIp)
                };
                
                postMethod.setQueryString(nameValuePair);
                
                HttpClient httpClient = new HttpClient();
                httpClient.executeMethod(postMethod);
                
                String httpResponse = postMethod.getResponseBodyAsString();
                System.out.println("httpResponse : " + httpResponse);
                
                JSONParser parser = new JSONParser();                
                JSONObject jsonObject = (JSONObject)parser.parse(httpResponse);
                
                // 사용자 요청 정보
                JSONObject requestDatajsonObject = (JSONObject)jsonObject.get("user");
                
                resultCode = (String)jsonObject.get("resultCode");
                resultMessage = (String)jsonObject.get("resultMessage");
                returnUrl = (String)jsonObject.get("returnUrl");
                boolean useCSMode  = jsonObject.get("useCSMode") == null ? false:Boolean.valueOf(jsonObject.get("useCSMode").toString());
                
                System.out.println("resultCode :" + resultCode);
                System.out.println("resultMessage :" + resultMessage);
                System.out.println("returnUrl : " + returnUrl);
                System.out.println("useCSMode : " + useCSMode);
                
                if (resultCode.equals("000000")) {
                    String[] keys = requestData.split(",");
                
                    for (int i = 0; i < keys.length; i++) {
                        String value = (String)requestDatajsonObject.get(keys[i]);
                        
                        session.setAttribute(keys[i], value);
                        System.out.println("key:" + keys[i] + ",value:" + value);
                    }
                    
                    if (useCSMode) {
                        returnUrl = AUTHORIZATION_URL + "token/saveToken.html";
                    }
                } else {
                    if (resultCode.equals("310019")) {
                        String useISignPage = (String)jsonObject.get("useISignPage");
                        if (useISignPage.equals("true")) {
                            returnUrl = AUTHORIZATION_URL + returnUrl + "&secureSessionId=" + secureSessionId;
                        }
                    } else {
                        System.out.println("call error.jsp");
                        returnUrl = "error.jsp";
                    }
                    
                }
                
                session.setAttribute("resultCode", resultCode);
                session.setAttribute("resultMessage", resultMessage);
                
                
                if(returnUrl == null || returnUrl.equals("")) {
                    returnUrl = "agentProc.jsp";
                }
                
                
            } catch (Exception e) {
                
                System.out.println(e.toString());
                isErrorPage = true;
                session.setAttribute("isErrorPage", isErrorPage);
                session.setAttribute("resultCode", resultCode);
                session.setAttribute("resultMessage", resultMessage);
                System.out.println(resultCode);
                System.out.println(resultMessage);
                
                returnUrl = "error.jsp";
            } finally {
                try {
                    if (postMethod != null) {
                        postMethod.releaseConnection();
                    }
                } catch (Exception e) {
                    
                }
                
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
<form name="sendForm" method="post">
    <input type="hidden" name="agentId" value="<%=agentId%>" />
    <input type="hidden" name="errCode" value="<%=resultCode%>" />
    <input type="hidden" name="secureSessionId" value="<%=secureSessionId%>" />
</form>

<script>
    var sendUrl = "<%=returnUrl%>";
    var sendForm = document.sendForm;
    sendForm.action = sendUrl;
    sendForm.submit();
</script>
</body>
</html>