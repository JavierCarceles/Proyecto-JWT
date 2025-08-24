import React, { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useLanguage } from '../context/LanguageContext';
import '../assets/css/singInStyle.css';

function SignIn() {
  const { lang, t } = useLanguage();
  const [countries, setCountries] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    document.title = t.titleSignIn || 'Registro de usuario';

    fetch('/paises.json')
      .then(res => res.json())
      .then(data => {
        setCountries(data[lang] || []);
      })
      .catch(() => {
        alert(t.loadCountriesError || 'No se pudo cargar la lista de países.');
      });
  }, [lang, t]);

  const handleSubmit = async e => {
    e.preventDefault();
    const form = e.target;
    const name = form.name.value.trim();
    const email = form.email.value.trim();
    const password = form.password.value;
    const confirmPassword = form.confirmPassword.value;
    const location = form.location.value.trim();

    if (!location) {
      alert(t.mustSelectCountry || 'Debes seleccionar tu país.');
      return;
    }

    if (password !== confirmPassword) {
      alert(t.passwordsNoMatch || 'Las contraseñas no coinciden');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, password, location }),
      });

      const data = await response.text();
      if (response.ok) {
       navigate('/login');
      } else {
        alert(data);
      }
    } catch {
      alert(t.serverError || 'No se pudo conectar al servidor');
    }
  };

  return (
    <>
      <div id="volverContainer">
        <Link to="/login" id="volverLink">← {t.backLink || 'Volver'}</Link>
      </div>

      <main>
        <section id="formularioRegistro" aria-labelledby="tituloRegistro">
          <form onSubmit={handleSubmit} autoComplete="off" noValidate>
            <fieldset id="cuadroRegistro">
              <h1 id="tituloRegistro">{t.signInTitle || 'Registro'}</h1>

              <div className="form-group">
                <label htmlFor="name">{t.signInNameLabel || 'Nombre:'}</label>
                <input
                  type="text"
                  id="name"
                  name="name"
                  placeholder={t.signInNamePlaceholder || 'Javier Carceles'}
                  required
                  pattern="[A-Za-z\s]+"
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">{t.signInEmailLabel || 'Email:'}</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder={t.signInEmailPlaceholder || 'ejemplo@email.com'}
                  required
                  pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                  title={t.signInEmailTitle || 'Introduce un email válido'}
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">{t.signInPasswordLabel || 'Contraseña:'}</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  required
                  pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}"
                  title={t.signInPasswordTitle || 'La contraseña debe tener al menos 8 caracteres, con al menos una letra mayúscula y una letra minúscula.'}
                />
              </div>

              <div className="form-group">
                <label htmlFor="confirmPassword">{t.signInConfirmPasswordLabel || 'Confirmar contraseña:'}</label>
                <input
                  type="password"
                  id="confirmPassword"
                  name="confirmPassword"
                  required
                  pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}"
                  title={t.signInConfirmPasswordTitle || 'Repite la contraseña'}
                />
              </div>

              <div className="form-group">
                <label htmlFor="location">{t.signInCountryLabel || 'País:'}</label>
                <input
                  list="countries"
                  id="location"
                  name="location"
                  placeholder={t.signInCountryPlaceholder || 'Selecciona tu país'}
                  required
                />
                <datalist id="countries">
                  {countries.map((country, i) => (
                    <option key={i} value={country} />
                  ))}
                </datalist>
              </div>

              <input
                type="submit"
                className="botonFormulario"
                value={t.signInSubmitBtn || 'Registrarse'}
              />
            </fieldset>
          </form>
        </section>
      </main>
    </>
  );
}

export default SignIn;
