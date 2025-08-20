export default {
  server: {
    proxy: {
      '/veiculos': 'http://localhost:8080',
      '/estatisticas': 'http://localhost:8080',
    }
  }
}