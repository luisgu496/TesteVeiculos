# 🚗 Cadastro de Veículos - Fullstack Kotlin + React

Aplicação **fullstack** para gerenciamento de veículos, com **backend em Kotlin (Ktor)** e **frontend em React (SPA)**.  
O sistema permite cadastrar, listar, atualizar e excluir veículos, além de fornecer estatísticas como:

- Quantidade de veículos não vendidos
- Distribuição por década de fabricação
- Distribuição por fabricante
- Veículos cadastrados na última semana

---

## 📂 Estrutura do Projeto

│  
├── backend/  
│   ├── build.gradle.kts  
│   ├── settings.gradle.kts  
│   ├── gradlew / gradlew.bat  
│   ├── gradle/wrapper/  
│   └── src/  
│       ├── main/  
│       │   ├── kotlin/com/example/backend/  
│       │   │   ├── Application.kt  
│       │   │   ├── models/  
│       │   │   ├── repository/  
│       │   │   ├── routes/  
│       │   │   └── plugins/  
│       │   └── resources/application.conf  
│       └── test/kotlin/com/example/backend/  
│  
└── frontend-veiculos/         
    ├── package.json  
    ├── vite.config.js  
    └── src/  
        ├── main.jsx  
        ├── App.jsx  
        ├── pages/            
        └── components/      



---

## 🛠️ Tecnologias Utilizadas

### **Backend**
- Kotlin
- Ktor
- Gradle

### **Frontend**
- React
- Vite
- JavaScript (ES6+)

---

## 🚀 Como Executar o Projeto

---

### **1️⃣ Clonar o repositório**

```bash
git clone https://github.com/luisgu496/TesteBackendVeiculos
cd TesteVeiculos-main
```

---

### **2️⃣ Rodar o Backend (Ktor)**

1. Acesse a pasta do backend:

```bash
cd backend
```

2. Compile e execute:

```bash
./gradlew run
```

3. A API estará disponível em:
   
```bash
http://localhost:8080
```

---

### **3️⃣ Rodar o Frontend (React)**

1. Acesse a pasta do frontend:
   
```bash
cd ../frontend-veiculos
```

2. Instale as dependências:

 ```bash
npm install
```

3. Inicie o servidor de desenvolvimento:

 ```bash
npm run dev
```

4. O sistema estará disponível em:
   
 ```bash
http://localhost:5173
```

---

## ✍ Autor
Desenvolvido por Luis Gustavo Ferreira Leite  🚀
