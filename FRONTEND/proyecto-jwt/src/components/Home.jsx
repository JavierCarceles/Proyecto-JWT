import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useLanguage } from '../context/LanguageContext';
import '../assets/css/homeStyle.css';

function Home({onLogout}) { 
  const { t } = useLanguage();
  const [nombreUsuario, setNombreUsuario] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const nombre = localStorage.getItem('nombreUsuario') || '';
    setNombreUsuario(nombre);
  }, []);

  const handleLogout = () => {
    
    localStorage.removeItem('token');
    localStorage.removeItem('nombreUsuario');
    onLogout();
    navigate('/login');
  };


  return (
    <main>
      <section id="bienvenida" aria-labelledby="tituloBienvenida">
        <h1 id="tituloBienvenida">
          {t.welcome} {nombreUsuario}{t.exclamation}
        </h1>

        <p>{t.loginSuccess}</p>

        <ul>
          <li>{t.viewProfile}</li>
          <li>{t.accessResources}</li>
          <li>{t.modifyData}</li>
        </ul>

        <button id="logoutBtn" type="button" onClick={handleLogout}>
          {t.logoutBtn}
        </button>
      </section>
    </main>
  );
}

export default Home;
