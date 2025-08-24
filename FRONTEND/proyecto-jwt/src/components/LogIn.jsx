import React, { useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useLanguage } from '../context/LanguageContext';
import '../assets/css/loginStyle.css';

function Login({onLogin}) {
  console.log(useLanguage());
  const { lang, setLang, t } = useLanguage();
  const navigate = useNavigate();

  useEffect(() => {
    document.title = t.titleLogin || 'PROYECTO JWT';
  }, [lang, t.titleLogin]);

  const changeLanguage = (e) => {
    e.preventDefault();
    const newLang = e.currentTarget.getAttribute('data-lang');
    if (newLang) setLang(newLang);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const form = e.target;
    const name = form.name.value.trim();
    const password = form.password.value.trim();

    if (!name || !password) {
      alert(t.fillAllFields || 'Por favor, rellena todos los campos');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, password }),
      });

      const data = await response.json();

      if (response.ok) {
        localStorage.setItem('nombreUsuario', name);
        localStorage.setItem('token', data.token);
        onLogin();
        navigate('/home');
      } else {
        alert(data.message || t.loginError || 'Error en login');
      }
    } catch {
      alert(t.serverError || 'No se pudo conectar al servidor');
    }
  };

  return (
    <>
      <header id="selectorIdioma" role="navigation" aria-label="Selector de idioma">
        <img
          src="/flags/espanol.png"
          alt="Español"
          className="bandera"
          title="Español"
          data-lang="es"
          onClick={changeLanguage}
        />
        <img
          src="/flags/ingles.png"
          alt="English"
          className="bandera"
          title="English"
          data-lang="en"
          onClick={changeLanguage}
        />
        <img
          src="/flags/frances.png"
          alt="Français"
          className="bandera"
          title="Français"
          data-lang="fr"
          onClick={changeLanguage}
        />
      </header>

      <main>
        <section id="formularioLogin" aria-labelledby="form-title">
          <form method="post" action="/" autoComplete="off" noValidate onSubmit={handleSubmit}>
            <fieldset id="cuadroLogin">
              <h1>{t.loginTitle || 'INICIAR SESIÓN'}</h1>

              <div className="form-group">
                <label htmlFor="name">{t.nameLabel || 'Nombre:'}</label>
                <input
                  type="text"
                  id="name"
                  name="name"
                  placeholder={t.namePlaceholder || 'Javier Carceles'}
                  required
                  pattern="[A-Za-z\s]+"
                  autoComplete="username"
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">{t.passwordLabel || 'Contraseña:'}</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  title={t.passwordTitle || 'La contraseña debe tener al menos 8 caracteres, con al menos una letra mayúscula y una letra minúscula.'}
                  required
                  pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}"
                  autoComplete="current-password"
                />
              </div>

              <p>
                <span>{t.noAccount || '¿No tienes cuenta?'}</span>{' '}
                <Link to="/signin">{t.registerHere || 'Regístrate aquí'}</Link>
              </p>

              <input type="submit" className="botonFormulario" value={t.submitBtn || 'Enviar datos'} />
            </fieldset>
          </form>
        </section>
      </main>
    </>
  );
}

export default Login;
