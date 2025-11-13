import { useEffect, useState } from "react";
import styles from "./Noticias.module.css";

// Função para decodificar entidades HTML
const decodeHtmlEntities = (str) => {
  const doc = new DOMParser().parseFromString(str, "text/html");
  return doc.documentElement.textContent || str;
};

function NewsCard({ article }) {
  const [imgSrc, setImgSrc] = useState(article.imageUrl || "/assets/placeholder.png");

  const handleImageError = () => {
    // Em caso de erro no carregamento da imagem, usamos o placeholder
    setImgSrc("/assets/placeholder.png");
  };

  const decodedTitle = decodeHtmlEntities(article.title); // Decodifica o título antes de renderizar

  return (
    <div className={styles.card}>
      <div className={styles.cardLeft}>
        <h1>
          <a href={article.link} target="_blank" rel="noopener noreferrer">
            {decodedTitle} {/* Exibe o título decodificado */}
          </a>
        </h1>
        <p>{article.content}</p>
        <div className={styles.cardFooter}>
          <span>{article.author || "Unknown Source"}</span>
        </div>
      </div>
      <div className={styles.cardRight}>
        <img
          src={imgSrc}
          alt="Card"
          onError={handleImageError} // Detecta erro no carregamento da imagem
        />
      </div>
    </div>
  );
}

export default function Noticias() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState(false);

  useEffect(() => {
    fetch("/api/news")
      .then((res) => {
        if (!res.ok) throw new Error("Erro ao carregar notícias");
        return res.json();
      })
      .then((data) => {
        setArticles(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Erro ao carregar notícias:", err);
        setErro(true);
        setLoading(false);
      });
  }, []);

  return (
    <main className={styles.main}>
      <h1 className="title center-flex">Últimas Notícias</h1>

      <div className={styles.container}>
        {loading && <p style={{ textAlign: "center" }}>Carregando notícias...</p>}
        {erro && (
          <p style={{ textAlign: "center" }}>
            Não foi possível carregar as notícias. Tente novamente mais tarde.
          </p>
        )}
        {!loading &&
          !erro &&
          articles.map((article, i) => <NewsCard key={i} article={article} />)}
      </div>
    </main>
  );
}
