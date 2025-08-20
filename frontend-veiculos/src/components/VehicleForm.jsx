import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

export default function VehicleForm({ id }) {
  const [form, setForm] = useState({
    veiculo: '',
    marca: '',
    ano: '',
    descricao: '',
    vendido: false
  });
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      api.get(`/veiculos/${id}`).then(res => setForm(res.data));
    }
  }, [id]);

  const salvar = async () => {
    const payload = { ...form, ano: Number(form.ano) };
    if (id) await api.put(`/veiculos/${id}`, payload);
    else await api.post('/veiculos', payload);
    navigate('/');
  };

  return (
    <div style={{ display: 'grid', gap: 8, maxWidth: 360 }}>
      <input placeholder="Veículo" value={form.veiculo}
             onChange={e => setForm({ ...form, veiculo: e.target.value })} />
      <input placeholder="Marca (ex: Honda, Ford...)" value={form.marca}
             onChange={e => setForm({ ...form, marca: e.target.value })} />
      <input placeholder="Ano" type="number" value={form.ano}
             onChange={e => setForm({ ...form, ano: e.target.value })} />
      <textarea placeholder="Descrição"
                value={form.descricao}
                onChange={e => setForm({ ...form, descricao: e.target.value })} />
      <label>
        Vendido?{" "}
        <input type="checkbox" checked={form.vendido}
               onChange={e => setForm({ ...form, vendido: e.target.checked })} />
      </label>
      <div>
        <button onClick={salvar}>Salvar</button>{" "}
        <button onClick={() => navigate('/')}>Cancelar</button>
      </div>
    </div>
  );
}