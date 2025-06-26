document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('loginBtn');
    const registerBtn = document.getElementById('registerBtn');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const authSwitch = document.querySelector('.auth-switch');

    // Анимация переключения между формами
    loginBtn.addEventListener('click', function() {
        if (!this.classList.contains('active')) {
            this.classList.add('active');
            registerBtn.classList.remove('active');
            authSwitch.classList.remove('register-active');
            loginForm.classList.add('active');
            registerForm.classList.remove('active');
        }
    });

    registerBtn.addEventListener('click', function() {
        if (!this.classList.contains('active')) {
            this.classList.add('active');
            loginBtn.classList.remove('active');
            authSwitch.classList.add('register-active');
            registerForm.classList.add('active');
            loginForm.classList.remove('active');
        }
    });
});