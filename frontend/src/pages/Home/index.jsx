'use client';
import styles from "./Home.module.css";
import '../../index.css';

export default function Home() {
  return (
    <main className={styles.mainContainer}>
      <div className={styles.backgroundSvg}></div>
      <h1 className={styles.homeTitle}>Bem Vindo!</h1>
      <p className={styles.intro}>
        Aqui você encontrará informações confiáveis e acessíveis <br />
        sobre o amianto, um material perigoso ainda <br />
        presente em muitos ambientes. Temos o objetivo de informar, conscientizar e proteger.
      </p>
    </main>
  );
}
