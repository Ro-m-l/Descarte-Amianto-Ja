'use client';
import { useEffect } from "react";
import styles from "./Mapa.module.css";
import "leaflet/dist/leaflet.css";
import L from "leaflet";

export default function Mapa() {
  useEffect(() => {
    // Cria o mapa centralizado em São Paulo
    const map = L.map("map").setView([-23.55052, -46.63331], 12);

    // Camada de tiles
    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "© OpenStreetMap contributors",
    }).addTo(map);

    let routeLine = null;
    let startMarker = null;
    let routeMarkers = [];

    // Ícones
    const startIcon = L.icon({
      iconUrl: "https://maps.gstatic.com/intl/en_us/mapfiles/ms/micons/red-dot.png",
      iconSize: [32, 32],
      iconAnchor: [16, 32],
    });

    const destinoIcon = L.icon({
      iconUrl: "https://maps.gstatic.com/intl/en_us/mapfiles/ms/micons/blue-dot.png",
      iconSize: [32, 32],
      iconAnchor: [16, 32],
    });

    // Função para obter localização do usuário
    async function getUserLocation() {
      if (!navigator.geolocation) {
        alert("Seu navegador não suporta geolocalização.");
        return;
      }

      navigator.geolocation.getCurrentPosition(
        async (pos) => {
          const { latitude, longitude } = pos.coords;

          // Reverse geocoding (OpenStreetMap)
          try {
            const res = await fetch(
              `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`
            );
            const data = await res.json();

            const endereco =
              data.display_name || `${latitude.toFixed(5)}, ${longitude.toFixed(5)}`;

            const input = document.getElementById("startAddress");
            if (input) input.value = endereco;

            // Adiciona marcador no mapa
            if (startMarker) map.removeLayer(startMarker);
            startMarker = L.marker([latitude, longitude], { icon: startIcon })
              .addTo(map)
              .bindPopup("<b>Sua localização atual</b>")
              .openPopup();

            map.setView([latitude, longitude], 14);
          } catch (err) {
            console.error("Erro ao converter coordenadas:", err);
            alert("Não foi possível obter o endereço da sua localização.");
          }
        },
        (err) => {
          console.error(err);
          alert("Não foi possível obter sua localização. Verifique as permissões do navegador.");
        }
      );
    }

    // Função para buscar rotas
    async function getRotasEmpresas() {
      const startAddress = document.getElementById("startAddress").value.trim();
      const numEmpresas = parseInt(document.getElementById("numEmpresas").value, 10);

      if (!startAddress || isNaN(numEmpresas)) {
        alert("Preencha ambos os campos!");
        return;
      }

      try {
        const res = await fetch("/api/rota/rota-empresas", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ startAddress, numEmpresas }),
        });

        const data = await res.json();
        if (!data || !data.rotas) {
          alert("Nenhuma rota encontrada.");
          return;
        }

        // Limpa rotas antigas
        if (routeLine) {
          map.removeLayer(routeLine);
          routeLine = null;
        }
        if (startMarker) {
          map.removeLayer(startMarker);
          startMarker = null;
        }
        routeMarkers.forEach((m) => map.removeLayer(m));
        routeMarkers = [];

        // Ponto inicial
        const startLat = data.startCoord?.lat ?? -23.55052;
        const startLon = data.startCoord?.lon ?? -46.63331;

        startMarker = L.marker([startLat, startLon], { icon: startIcon })
          .addTo(map)
          .bindPopup("<b>Ponto de partida</b>");

        // Desenha rotas
        data.rotas.forEach((r) => {
          const coords = r.coordinates;
          if (!coords || coords.length === 0) return;

          const line = L.polyline(coords, { color: "blue" }).addTo(map);
          routeLine = line;

          const last = coords[coords.length - 1];
          const dist = (r.distanceKm ?? 0).toFixed(1);
          const tempo = (r.durationMin ?? 0).toFixed(0);

          const popupHtml = `
            <b>${r.empresa}</b><br>
            Distância: ${dist} km<br>
            Tempo: ${tempo} min
          `;

          const marker = L.marker(last, { icon: destinoIcon }).addTo(map).bindPopup(popupHtml);
          routeMarkers.push(marker);
        });

        // Ajusta zoom
        if (routeLine) {
          map.fitBounds(routeLine.getBounds().extend([startLat, startLon]));
        }
      } catch (err) {
        console.error("Erro ao buscar rotas:", err);
        alert("Ocorreu um erro ao buscar as rotas.");
      }
    }

    // Atribui funções aos botões
    const btnRotas = document.getElementById("btnRotas");
    const btnGeo = document.getElementById("btnGeo");

    if (btnRotas) btnRotas.onclick = getRotasEmpresas;
    if (btnGeo) btnGeo.onclick = getUserLocation;

    // Cleanup
    return () => map.remove();
  }, []);

  return (
    <main>
      <h1 className="title center-flex">Mapa de Rotas de Descarte</h1>
      <p className={`title center-flex ${styles.index}`}>
        Encontre a empresa licenciada mais próxima especializada em demolição de construções e descarte seguro de amianto.
      </p>

      <div className={styles.routeContainer}>
        <div className={styles.inputGroup}>
          <input
            type="text"
            id="startAddress"
            placeholder="Seu endereço"
            className={styles.input}
          />
          <button id="btnGeo" className={styles.geoButton}>
            📍 Usar minha localização
          </button>
        </div>

        <div className={styles.inputGroup}>
          <input
            type="number"
            id="numEmpresas"
            placeholder="Número de empresas"
            className={styles.inputNum}
          />
          <button id="btnRotas" className={styles.button}>
            Obter Rotas
          </button>
        </div>
      </div>

      <div id="map" className={styles.map}></div>
      <div id="route-info" className={styles.routeInfo}></div>
    </main>
  );
}
