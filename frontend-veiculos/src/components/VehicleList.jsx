import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';

export default function VehicleList() {
  const [veiculos, setVeiculos] = useState([]);
  const [marca, setMarca] = useState('');
  const [ano, setAno] = useState('');
  const [cor, setCor] = useState('');

  const carregar = () => {
    const params = new URLSearchParams();
    if (marca) params.set('marca', marca);
    if (ano) params.set('ano', ano);
    if (cor) params.set('cor', cor);
    api.get('/veiculos' + (params.toString() ? `?${params}` : '')).then(res => setVeiculos(res.data));
  };

  useEffect(() => {
    carregar();
  }, []);

  const excluir = async (id) => {
    if (confirm('Deseja realmente excluir?')) {
      await api.delete(`/veiculos/${id}`);
      carregar();
    }
  };

  return (
    <div>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Filtrar por marca" value={marca} onChange={e => setMarca(e.target.value)} />
        <input placeholder="Ano" type="number" value={ano} onChange={e => setAno(e.target.value)} style={{ marginLeft: 8 }} />
        <input placeholder="Cor" value={cor} onChange={e => setCor(e.target.value)} style={{ marginLeft: 8 }} />
        <button onClick={carregar} style={{ marginLeft: 8 }}>Filtrar</button>
        <Link to="/novo" style={{ marginLeft: 8 }}>➕ Novo</Link>
      </div>
      <table border="1" cellPadding="6">
        <thead>
          <tr>
            <th>ID</th>
            <th>Veículo</th>
            <th>Marca</th>
            <th>Ano</th>
            <th>Cor</th>
            <th>Vendido</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {veiculos.map(v => (
            <tr key={v.id}>
              <td>{v.id}</td>
              <td>{v.veiculo}</td>
              <td>{v.marca}</td>
              <td>{v.ano}</td>
              <td>{v.cor}</td>
              <td>{v.vendido ? 'Sim' : 'Não'}</td>
              <td>
                <Link to={`/editar/${v.id}`}>✏️ Editar</Link>{" "}
                <button onClick={() => excluir(v.id)}>🗑️ Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
