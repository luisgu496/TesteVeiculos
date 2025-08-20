import VehicleForm from '../components/VehicleForm';
import { useParams } from 'react-router-dom';

export default function EditarVeiculo() {
  const { id } = useParams();
  return (
    <div>
      <h2>Editar Veículo #{id}</h2>
      <VehicleForm id={id} />
    </div>
  );
}