<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>

	  <!--  머리 시작   -->
	 <div id="topheader">
	   <div class="logo"></div>

	  </div>
	 <div id="search_strip">
<!-- 	   <div class="freeregistration">
	
	 </div> -->	 
	   <div class="search_area">
	    <div class="search_box">
				<%--검색 시작 --%>

				<form action="categoryprocess.jsp" method="POST">
				<input type="hidden" name="menu" value="search">
					 <SELECT name="category">
						<option value="pants">바지</option>
						<option value="shoes">신발</option>
						<option value="tshirts">티</option>
						<option value="etc">잡화</option>
						<option value="outer">아우터</option>
					</SELECT> <input type="text" name="input_Search" />
					 		  <input type="submit" value="검색" />

				</form>

				<%--검색 끝 --%>
	    </div>
	  </div>
	 </div>
	  <!--  바디 시작   -->
	<div id="body_area">
		<!--  메뉴 부분  -->
	  <div class="left">
<%
		pageContext.include("category.jsp");
%>   
	  </div>	
	  	<!--  메뉴 부분   끝 -->
	  <!--  게시판 부분   -->
	  <div class="midarea">
<%
	int boardNumber = categoryNumber.getPageNumber();

	if(boardNumber==1){
		pageContext.include("pants.jsp");
	}else if(boardNumber==2){
		pageContext.include("shoes.jsp");
	}else if(boardNumber==3){
		pageContext.include("tshirts.jsp");
	}else if(boardNumber==4){
		pageContext.include("etc.jsp");
	}else if(boardNumber==5){
		pageContext.include("outer.jsp");
	}else if(boardNumber==6){
		pageContext.include("cart.jsp");
	}else if(boardNumber==7){
		pageContext.include("checkOrder.jsp");
	}else if(boardNumber==8){
		pageContext.include("order.jsp");
	}else if(boardNumber==9){
		pageContext.include("refund.jsp");
	}else if(boardNumber==10){
		pageContext.include("productInformation.jsp");
	}else if (boardNumber == 11) {
		pageContext.include("search.jsp");
	}else if(boardNumber==12){
		pageContext.include("joinForm.jsp");
	}else{
		pageContext.include("home.jsp");
	}

%>
	  </div>
 	<!--  게시판부분  끝 -->
 	  <!-- 로그인 부분 -->
	  <div class="right">
	  <form action="loginprocess.jsp" method="post" name="loginForm">
	<% 
	  	
		request.setCharacterEncoding("euc-kr");

		String id =loginfo.getMember_id();
		if (id != null){
			pageContext.include("loginfo.jsp"); 
		}else{
			pageContext.include("login.jsp");
		}
	%>
	<!--  로그린 부분 끝 -->
		  </form>	
		</div>

</div>

<!--  
<div id="foot">
	<div class="foot_copyrights">
	  	<div align="center">
	  		&copy; Copyright Information Goes Here. All Rights Reserved
	  	</div>
	</div>
	<div class="foot_designed">
		<div align="center">
	       Designed By : HomPage
	    </div>
	  </div>
</div>
-->
</body>
</html>