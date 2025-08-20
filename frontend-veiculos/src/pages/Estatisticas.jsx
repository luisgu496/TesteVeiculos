import { useEffect, useState } from 'react';
import api from '../services/api';

export default function Estatisticas() {
  const [naoVendidos, setNaoVendidos] = useState(0);
  const [porMarca, setPorMarca] = useState({});
  const [porDecada, setPorDecada] = useState({});
  const [recentes, setRecentes] = useState([]);

  useEffect(() => {
    api.get('/estatisticas/nao-vendidos').then(res => setNaoVendidos(res.data.naoVendidos));
    api.get('/estatisticas/por-marca').then(res => setPorMarca(res.data));
    api.get('/estatisticas/por-decada').then(res => setPorDecada(res.data));
    api.get('/estatisticas/ultima-semana').then(res => setRecentes(res.data));
  }, []);

  return (
    <div>
      <h2>📊 Estatísticas</h2>
      <p><b>Não vendidos:</b> {naoVendidos}</p>

      <h3>Por Marca</h3>
      <ul>
        {Object.entries(porMarca).map(([marca, qtd]) => (
          <li key={marca}>{marca}: {qtd}</li>
        ))}
      </ul>

      <h3>Por Década</h3>
      <ul>
        {Object.entries(porDecada).map(([decada, qtd]) => (
          <li key={decada}>{decada}: {qtd}</li>
        ))}
      </ul>

      <h3>Última semana</h3>
      <ul>
        {recentes.map(v => (
          <li key={v.id}>{v.veiculo} — criado em {v.created}</li>
        ))}
      </ul>
    </div>
  );
}