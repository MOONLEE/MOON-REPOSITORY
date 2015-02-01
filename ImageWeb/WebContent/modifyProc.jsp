<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ShoppingSite.HomeShoppingDBCP" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="loginfodbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />


<% request.setCharacterEncoding("EUC-KR"); %>

<jsp:useBean id="member" class="ShoppingSite.MemberEntity"/>
<jsp:setProperty name="member" property="*"/>

<%
String id = loginfo.getMember_id();
member.setMember_id(id);

HomeShoppingDBCP manager = new HomeShoppingDBCP();
manager.updateMember(member);
%>

<script>
alert("정보가 수정되었습니다.");
location.href="index.jsp";
</script>

</body>
</html>