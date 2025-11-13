import { useEffect, useState } from "react";
import styles from "./Empresas.module.css";

export default function Empresas() {
  const [empresas, setEmpresas] = useState([]);
  const [erro, setErro] = useState(null);

  useEffect(() => {
    async function fetchEmpresas() {
      try {
        const response = await fetch("/api/empresas"); // backend
        if (!response.ok) throw new Error("Erro ao buscar empresas");
        const data = await response.json();
        setEmpresas(data);
      } catch (error) {
        console.error(error);
        setErro("Não foi possível carregar as empresas.");  
      }
    }

    fetchEmpresas();
  }, []);

  return (
    <main className={styles.main}>
      <h1 className="title center-flex">Empresas de Descarte</h1>

      <div className={styles.container}>
        {erro && <p className={styles.erro}>{erro}</p>}
        {empresas.length > 0 ? (
          empresas.map((empresa, i) => (
            <div key={i} className={styles.card}>
              <h3>{empresa.nome}</h3>
              <p>{empresa.endereco}</p>
              <p>Tel: {empresa.telefone}</p>
              {empresa.email && <p>Email: {empresa.email}</p>}
              {empresa.site && (
                <p>
                  <a href={empresa.site} target="_blank" rel="noopener noreferrer">
                    Site
                  </a>
                </p>
              )}
            </div>
          ))
        ) : (
          !erro && <p>Carregando empresas...</p>
        )}
      </div>
    </main>
  );
}