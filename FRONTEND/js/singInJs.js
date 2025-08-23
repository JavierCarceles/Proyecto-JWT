document.addEventListener('DOMContentLoaded', () => {
    const locationInput = document.getElementById('location');
    const datalist = document.getElementById('countries');
    const form = document.querySelector('form');

    fetch('../data/paises.json')
        .then(response => response.json())
        .then(paises => {
            paises.forEach(pais => {
                const option = document.createElement('option');
                option.value = pais;
                datalist.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error cargando paises:', error);
            alert("No se pudo cargar la lista de países.");
        });

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const name = form.elements['name'].value;
        const email = form.elements['email'].value;
        const password = form.elements['password'].value;
        const confirmPassword = form.elements['confirmPassword'].value;
        const location = locationInput.value;

        if (!location) {
            alert('Debes seleccionar tu ciudad.');
            return;
        }

        if (password !== confirmPassword) {
            alert('Las contraseñas no coinciden');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name, email, password, location })
            });

            const data = await response.text();
            if (response.ok) {
                window.location.href = './login.html';
            } else {
                alert(data);
            }
        } catch (error) {
            alert('No se pudo conectar al servidor');
        }
    });
});
