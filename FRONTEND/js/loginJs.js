document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const name = form.elements['name'].value;
        const password = form.elements['password'].value;

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, password })
            });

            const data = await response.text();
            if (response.ok) {
                // Guarda el nombre del usuario en localStorage para mostrarlo en index.html
                localStorage.setItem('nombreUsuario', name);
                // Redirige a index.html
                window.location.href = './index.html';
            } else {
                alert('Error en login');
            }
        } catch (error) {
            alert('No se pudo conectar al servidor');
        }
    });
});