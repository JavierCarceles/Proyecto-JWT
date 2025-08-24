function setLanguage(lang) {
  if (!window.translations || !window.translations[lang]) return;
  const t = window.translations[lang];

  document.querySelectorAll('[data-i18n]').forEach(el => {
    const key = el.getAttribute('data-i18n');
    if (t[key]) {
      if (el.tagName === 'INPUT' || el.tagName === 'TEXTAREA') {
        el.value = t[key];
      } else {
        el.textContent = t[key];
      }
    }
  });

  document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
    const key = el.getAttribute('data-i18n-placeholder');
    if (t[key]) el.placeholder = t[key];
  });

  document.querySelectorAll('[data-i18n-title]').forEach(el => {
    const key = el.getAttribute('data-i18n-title');
    if (t[key]) el.title = t[key];
  });

  document.querySelectorAll('[data-i18n-value]').forEach(el => {
    const key = el.getAttribute('data-i18n-value');
    if (t[key]) el.value = t[key];
  });

  if (t.title) document.title = t.title;
}

document.addEventListener('DOMContentLoaded', () => {
  const savedLang = localStorage.getItem('language') || 'es';
  setLanguage(savedLang);

  document.querySelectorAll('.change-language').forEach(link => {
    link.addEventListener('click', e => {
      e.preventDefault();
      const lang = e.currentTarget.getAttribute('data-lang');
      if (!lang) return;
      setLanguage(lang);
      localStorage.setItem('language', lang);
    });
  });
});
