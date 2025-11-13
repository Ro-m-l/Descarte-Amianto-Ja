'use client';
import Post from "../../components/Post";
import { guiasRemocao } from "../../data/guias";
import styles from "./Guias.module.css";

export default function Guias() {
  return (
    <main>
      <div className={`${styles.feed} center-flex`}>
        {guiasRemocao.map((item, index) => (
          <Post
            key={index}
            source={item.source}
            title={item.title}
            link={item.link}
            img={item.img}
            isGuide={true}
          />
        ))}
      </div>
    </main>
  );
}
