<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>loginfod</title>
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>
<font color="black"><jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
</font>
   <div class="login_area">
      <div class="login_top" style="background-image:url('images/login_01.png');"></div>
      <div class="login_bodyarea" style="background-image:url('images/login_02.png');">
        <div class="right_head" style="background-image:url('images/right_head.png');">
          <div align="center"><font color="black">회원정보</font></div>
        </div>
        <div class="right_textbox"> 
	      <p>  <font color="black">이름 : <jsp:getProperty property="name" name="loginfo"/></font></p>
        </div>
        <div class="right_textbox">
      <p>  <font color="black">ID : <jsp:getProperty property="member_id" name="loginfo"/></font></p>
       </div>
     	<font color="black"><input type="button" value="정보수정" onclick="location.href='modifyForm.jsp'">
          <input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
</font>       
      </div>
      <div class="login_bottom" style="background-image:url('images/login_03.png');"></div>
    </div>   


</body>
</html>