document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('form');
  if (!form) return;

  form.addEventListener('submit', async e => {
    e.preventDefault();
    const name = form.elements['name']?.value;
    const password = form.elements['password']?.value;
    if (!name || !password) {
      alert('Por favor, rellena todos los campos');
      return;
    }
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, password })
      });
      const data = await response.json();
      console.log('JWT recibido:', data.token);
      debugger;
      if (response.ok) {
        localStorage.setItem('nombreUsuario', name);
        localStorage.setItem('token', data.token);
        window.location.href = './index.html';
      } else {
        alert(data.message || 'Error en login');
      }
    } catch {
      alert('No se pudo conectar al servidor');
    }
  });
});
