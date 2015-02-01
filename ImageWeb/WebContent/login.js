function loginCheck() {
        if ( document.loginForm.id.value=="" ) {
                alert("ID를 입력해 주세요.");
                document.loginForm.id.focus();
                return;
        }
        if ( document.loginForm.password.value=="" ) {
                alert("암호를 입력해 주세요.");
                document.loginForm.password.focus();
                return;
        }
        document.loginForm.menu.value='login';
        document.loginForm.submit();
}