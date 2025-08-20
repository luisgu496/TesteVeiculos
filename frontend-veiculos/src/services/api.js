import axios from 'axios';

const api = axios.create({
  baseURL: '/', // Vite proxy redireciona para o backend
  headers: { 'Content-Type': 'application/json' }
});

export default api;