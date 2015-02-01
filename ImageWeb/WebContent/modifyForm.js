function inputCheck(){
	
	if(document.modifyForm.password.value==""){
		alert("비밀번호를 확인해 주세요.");
		document.modifyForm.password.focus();
		return;
	}
	if(document.modifyForm.repassword.value==""){
		alert("비밀번호를 확인해 주세요.");
		document.modifyForm.repassword.focus();
		return;
	}
	if(document.modifyForm.password.value != document.modifyForm.repassword.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.modifyForm.member_id.focus();
		return;
	}
	
	if(document.modifyForm.name.value==""){
		alert("이름을 입력해 주세요.");
		document.modifyForm.name.focus();
		return;
	}
	if(document.modifyForm.province.value==""){
		alert("도를 입력해 주세요.");
		document.modifyForm.province.focus();
		return;
	}
	if(document.modifyForm.town.value==""){
		alert("주소를 입력해 주세요.");
		document.modifyForm.town.focus();
		return;
	}
	if(document.modifyForm.address.value==""){
		alert("주소를 입력해 주세요.");
		document.modifyForm.address.focus();
		return;
	}
	if(document.modifyForm.phone_number.value==""){
		alert("전화번호를 입력해 주세요.");
		document.modifyForm.phone_number.focus();
		return;
		
	}
	if(document.modifyForm.mail.value==""){
		alert("이메일을 입력해 주세요.");
		document.modifyForm.mail.focus();
		return;
	}
	document.modifyForm.submit();
}


