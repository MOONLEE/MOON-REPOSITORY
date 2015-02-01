<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>login</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script src="login.js"></script>
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>
   <div class="login_area">
      <div class="login_bodyarea" style="background-image:url('images/login_02.png');">
        <div class="right_head" style="background-image:url('images/right_head.png');">
          <div align="center"><font color="black">로그인</font></div>
        </div>        
        
     
      
          <font color="black"><input type="hidden" name="menu" value="login">
          <input name="id" type="text" class="righttextbox" value="" /><br><br>      
          <input name="password" type="password" class="righttextbox" value="" /><br><Br>        
         
      
     
          <input type="button" value="로그인" onclick="loginCheck()">
          <input type="button" value="회원가입" onclick="location.href='join.jsp'">
</font>  		
      </div>      
      <div class="login_bottom" style="background-image:url('images/login_03.png');">      
      </div>
    </div>   
</body>
</html>