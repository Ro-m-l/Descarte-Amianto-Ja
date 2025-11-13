/* Post compartilhado por guia e noticia */
'use client';
import styles from "./Post.module.css";
import rectStyles from "../../Rect.module.css";

export default function Post({ source, date, title, link, img, isGuide = false }) {
  return (
    <>
      <div className={styles.post}>
        {/* Baseado no xml testado. Para guias, mostra a fonte */}
        {isGuide && source && <p>{source}</p>}

        {date && <p className={styles.data}>{date}</p>}

        {/* Título com link */}
        <h2>
          <a href={link} target="_blank" rel="noopener noreferrer">
            {title}
          </a>
        </h2>

        {/* Para guias, mostra a imagem */}
        {isGuide && img && <img src={img} alt={`Imagem sobre ${title}`} />}
      </div>
      <div className={rectStyles.rect}></div>
    </>
  );
}
