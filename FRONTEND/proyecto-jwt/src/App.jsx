import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/LogIn';
import SignIn from './components/SignIn';
import Home from './components/Home';
import { LanguageProvider } from './context/LanguageContext';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));

  useEffect(() => {
    function onStorageChange() {
      setIsAuthenticated(!!localStorage.getItem('token'));
    }
    window.addEventListener('storage', onStorageChange);
    return () => window.removeEventListener('storage', onStorageChange);
  }, []);

  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
  };

  return (
    <LanguageProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Navigate to="/login" replace />} />
          <Route path="/login" element={<Login onLogin={handleLogin} />} />
          <Route path="/signin" element={<SignIn />} />
          <Route
            path="/home"
            element={
              isAuthenticated ? <Home onLogout={handleLogout} /> : <Navigate to="/login" replace />
            }
          />
          <Route path="*" element={<Navigate to="/login" replace />} />
        </Routes>
      </Router>
    </LanguageProvider>
  );
}

export default App;
