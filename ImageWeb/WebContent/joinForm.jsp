<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script src="join.js"></script>
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>


<form name=joinForm action = "joinProcess.jsp" method="post">
<table width="500" border="0" cellpadding="5"  cellspacing="5" align="center"> 
	<tr>
		<td colspan="2" align=center>
			<b><font size="5" color="black">회원가입</font></b><font color="black">
</font>		</td>
	</tr>
	<tr>
		<td width=100><font color="black">사용자ID</font></td>
		<td><font color="black"><input type="text" name="member_id">
		<input type="button" value="ID 중복확인" OnClick="idCheck(this.form.member_id.value)"></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td><font size="2" color="black">※ 영문자, 숫자, _만 입력 가능.</font><font color="black"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">비밀번호</font></td>
		<td><font color="black"><input type="password" name="password"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">비밀번호 확인</font></td>
		<td><font color="black"><input type="password" name="repassword"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">이름</font></td>
		<td><font color="black"><input type="text" name="name"></font></td>
	</tr>
	<tr>
    	<td><font color="black">주소</font></td>
		<td width="430"><font color="black">도,광역(특별)시 입력: <input name="province" type="text" style="width:56px;"  /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>	
		<td width="430"><font color="black">시,군 입력: <input type="text" name="town" style="width:56px" /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td colspan="2"><font color="black">나머지 주소입력: <input name="address" type="text" style="width:310px;" /></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">전화번호</font></td>
		<td><font color="black"><input type="text" name="phone_number"></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td><font size="2" color="black">※  -없이 입력</font><font color="black"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">E-mail</font></td>
		<td><font color="black"><input type="text" name="mail" size="30"></font></td>
	</tr>
	<tr>
		<td colspan="2" align = right>
			<font color="black"><input type="button" value="등록" onClick="inputCheck()"></font></td>
	</tr>

</table>
</form>

</body>
</html>