import { Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import NovoVeiculo from './pages/NovoVeiculo';
import EditarVeiculo from './pages/EditarVeiculo';
import Estatisticas from './pages/Estatisticas';

export default function App() {
  return (
    <div style={{ padding: 16, fontFamily: 'system-ui, Arial' }}>
      <h1>🚗 Cadastro de Veículos</h1>
      <nav style={{ marginBottom: 12 }}>
        <Link to="/">🏠 Home</Link> |{" "}
        <Link to="/novo">➕ Novo</Link> |{" "}
        <Link to="/estatisticas">📊 Estatísticas</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/novo" element={<NovoVeiculo />} />
        <Route path="/editar/:id" element={<EditarVeiculo />} />
        <Route path="/estatisticas" element={<Estatisticas />} />
      </Routes>
    </div>
  );
}