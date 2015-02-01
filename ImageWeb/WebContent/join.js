function inputCheck(){
	/*
	if(document.joinForm.member_id.value==""){
		alert("사용자ID를 입력해 주세요.");
		document.joinForm.member_id.focus();
		return;
	}
	if(document.joinForm.password.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.joinForm.password.focus();
		return;
	}
	if(document.joinForm.repassword.value==""){
		alert("비밀번호를 확인해 주세요.");
		document.joinForm.repassword.focus();
		return;
	}
	if(document.joinForm.password.value != document.joinForm.repassword.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.joinForm.member_id.focus();
		return;
	}
	if(document.joinForm.name.value==""){
		alert("이름을 입력해 주세요.");
		document.joinForm.name.focus();
		return;
	}
	if(document.joinForm.province==""){
		alert("도,광역(특별)시를 입력해 주세요.");
		document.joinForm.province.focus();
		return;
	}
	if(document.joinForm.town==""){
		alert("시, 구, 군을 입력해 주세요.");
		document.joinForm.town.focus();
		return;
	}
	if(document.joinForm.address.value==""){
		alert("주소를 입력해 주세요.");
		document.joinForm.address.focus();
		return;
	}
	if(document.joinForm.phone_number.value==""){
		alert("전화번호를 입력해 주세요.");
		document.joinForm.phone_number.focus();
		return;
		
	}
	if(document.joinForm.mail.value==""){
		alert("이메일을 입력해 주세요.");
		document.joinForm.mail.focus();
		return;
	}
	*/
	document.joinForm.submit();
}


function idCheck(id){
	if(id == ""){
		alert("아이디를 입력해주세요.");
		document.joinForm.member_id.focus();
	}else{
		url="idCheck.jsp?member_id=" + id;
		window.open(url,"post","witdh=50 height=30");
	}
}

