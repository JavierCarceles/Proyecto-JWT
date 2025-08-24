import React, { createContext, useState, useEffect, useContext } from 'react';
import translations from '../assets/js/languages';

const LanguageContext = createContext();

export function LanguageProvider({ children }) {
  const [lang, setLang] = useState(localStorage.getItem('language') || 'es');

  useEffect(() => {
    localStorage.setItem('language', lang);
  }, [lang]);

  const t = translations[lang] || translations['es'];

  return (
    <LanguageContext.Provider value={{ lang, setLang, t }}>
      {children}
    </LanguageContext.Provider>
  );
}

export function useLanguage() {
  const context = useContext(LanguageContext);
  if (!context) {
    throw new Error('useLanguage must be used within a LanguageProvider');
  }
  return context;
}
