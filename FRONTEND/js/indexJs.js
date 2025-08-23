document.addEventListener('DOMContentLoaded', () => {
    const nombre = localStorage.getItem('nombreUsuario');
    const nombreSpan = document.getElementById('nombreUsuario');
    nombreSpan.textContent = nombre ? nombre : '';

    const logoutBtn = document.getElementById('logoutBtn');
    logoutBtn.addEventListener('click', () => {
        localStorage.removeItem('jwt');
        localStorage.removeItem('nombreUsuario');
        window.location.href = 'login.html';
    });
});