import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Index from '../pages/Home/index';             // Página inicial (index.jsx)
import Empresas from '../pages/Empresas';      // Página Empresas (Empresas/index.jsx)
import Guias from '../pages/Guias';            // Página Guias (Guias/index.jsx)
import Mapa from '../pages/Mapa';              // Página Mapa (Mapa/index.jsx)
import Noticias from '../pages/Noticias';      // Página Notícias (Noticias/index.jsx)
import NotFound from '../pages/NaoEncontrado'; // Página 404 (se a rota não for encontrada)

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<Index />} />  
    <Route path="/Empresas" element={<Empresas />} />
    <Route path="/Guias" element={<Guias />} />
    <Route path="/Mapa" element={<Mapa />} />
    <Route path="/Noticias" element={<Noticias />} />
    <Route path="*" element={<NotFound />} /> {/* Página para rotas não encontradas */}
  </Routes>
);

export default AppRoutes;
