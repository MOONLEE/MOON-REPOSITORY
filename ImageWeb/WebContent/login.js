function loginCheck() {
        if ( document.loginForm.id.value=="" ) {
                alert("ID�� �Է��� �ּ���.");
                document.loginForm.id.focus();
                return;
        }
        if ( document.loginForm.password.value=="" ) {
                alert("��ȣ�� �Է��� �ּ���.");
                document.loginForm.password.focus();
                return;
        }
        document.loginForm.menu.value='login';
        document.loginForm.submit();
}