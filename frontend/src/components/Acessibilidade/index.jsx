import React, { useEffect, useState } from "react";
import styles from "./Acessibilidade.module.css";

const Acessibilidade = () => {
  const [fontSize, setFontSize] = useState("medium");
  const [ttsActive, setTtsActive] = useState(false);
  const [isSpeaking, setIsSpeaking] = useState(false);

  // Ajuste de tamanho da fonte global
  useEffect(() => {
    document.documentElement.style.fontSize =
      fontSize === "small" ? "14px" : fontSize === "large" ? "18px" : "16px";
  }, [fontSize]);

  // Efeito: ativa/desativa listeners de clique quando o TTS está ativo
  useEffect(() => {
    const handleClick = (e) => {
      if (!ttsActive) return;

      // Impede que clique em botões do painel ativem leitura
      if (e.target.closest(`.${styles.acessibilidade}`)) return;

      const text = e.target.innerText.trim() || e.target.placeholder || e.target.value;
      if (!text) return;

      // Cancela fala atual antes de iniciar a nova
      window.speechSynthesis.cancel();

      const utterance = new SpeechSynthesisUtterance(text);
      utterance.lang = "pt-BR";

      setIsSpeaking(true);

      utterance.onend = () => {
        setIsSpeaking(false);
      };

      utterance.onerror = () => {
        setIsSpeaking(false);
      };

      window.speechSynthesis.speak(utterance);
    };

    const handleFocus = (e) => {
      if (!ttsActive) return;

      const text = e.target.placeholder || e.target.value || e.target.innerText;
      if (!text) return;

      window.speechSynthesis.cancel();

      const utterance = new SpeechSynthesisUtterance(text);
      utterance.lang = "pt-BR";

      setIsSpeaking(true);

      utterance.onend = () => {
        setIsSpeaking(false);
      };

      utterance.onerror = () => {
        setIsSpeaking(false);
      };

      window.speechSynthesis.speak(utterance);
    };

    if (ttsActive) {
      document.addEventListener("click", handleClick);
      document.querySelectorAll("input, textarea").forEach((input) => {
        input.addEventListener("focus", handleFocus);
      });
    } else {
      document.removeEventListener("click", handleClick);
      document.querySelectorAll("input, textarea").forEach((input) => {
        input.removeEventListener("focus", handleFocus);
      });
      window.speechSynthesis.cancel(); // para tudo ao desativar
      setIsSpeaking(false);
    }

    return () => {
      document.removeEventListener("click", handleClick);
      document.querySelectorAll("input, textarea").forEach((input) => {
        input.removeEventListener("focus", handleFocus);
      });
    };
  }, [ttsActive]);

  const toggleTTS = () => {
    if (ttsActive) {
      window.speechSynthesis.cancel();
      setIsSpeaking(false);
      setTtsActive(false);
    } else {
      setTtsActive(true);
    }
  };

  return (
    <div className={styles.acessibilidade}>
      <div className={styles["font-size-controls"]}>
        <button
          className={`${styles["font-btn"]} ${
            fontSize === "small" ? styles.active : ""
          }`}
          onClick={() => setFontSize("small")}
        >
          A
        </button>
        <button
          className={`${styles["font-btn"]} ${
            fontSize === "medium" ? styles.active : ""
          }`}
          onClick={() => setFontSize("medium")}
        >
          A
        </button>
        <button
          className={`${styles["font-btn"]} ${
            fontSize === "large" ? styles.active : ""
          }`}
          onClick={() => setFontSize("large")}
        >
          A
        </button>
      </div>

      <button
        id="toggleButton"
        className={`${styles["tts-button"]} ${
          ttsActive ? styles.active : ""
        }`}
        onClick={toggleTTS}
      >
        🔊{" "}
        {ttsActive
          ? isSpeaking
            ? "Lendo..."
            : "Modo leitura ativado (clique em textos)"
          : "Ativar leitura de texto"}
      </button>
    </div>
  );
};

export default Acessibilidade;
