import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styles from "./HeaderPrincipal.module.css"; // Importa o CSS
import Acessibilidade from "../Acessibilidade";

export default function HeaderPrincipal() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isMobile, setIsMobile] = useState(window.innerWidth <= 480);

  // Função para alternar a visibilidade do menu
  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  // Detectar mudanças no tamanho da tela
  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 480);
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <header className={styles.header}>
      <div className={styles.logo}>Descarte Amianto Já</div>

      {/* Exibe o botão de dropdown apenas em telas <480px */}
      {isMobile && (
        <button 
          className={styles.dropdownButton} 
          onClick={toggleMenu}
        >
          {isMenuOpen ? "-" : "+"}
        </button>
      )}

      <nav className={`${styles.nav} ${isMenuOpen ? styles.open : ""}`}>
        <Link to="/Mapa">Mapa</Link>
        <Link to="/Noticias">Notícias</Link>
        <Link to="/Guias">Guias</Link>
        <Link to="/Empresas">Empresas</Link>
        <Link to="/">Home</Link>
      </nav>

      <Acessibilidade />
    </header>
  );
}
