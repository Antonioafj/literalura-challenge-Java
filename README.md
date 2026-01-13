# 📚 LiterAlura - Challenge ONE Java Back End

<div align="center">
  <img src="https://github.com/user-attachments/assets/07f1d756-daa5-4d50-bbc9-1bb40f862d72" alt="Banner LiterAlura" width="850">
</div>

<p align="center">
  <img src="https://img.shields.io/badge/Status-FINALIZADO-blue" alt="Status">
  <img src="https://img.shields.io/badge/API_Usada-Gutendex-green" alt="API">
  <img src="https://img.shields.io/badge/Challenge_ONE-Java_Back_End_Alura-00FF00" alt="Challenge">
  <img src="https://img.shields.io/github/stars/Antonioafj?style=social" alt="Stars">
</p>

## 📖 Descrição do Projeto
O **LiterAlura** é um catálogo de livros interativo que consome a API **Gutendex**. O diferencial deste projeto é a implementação de um banco de dados relacional para persistir as consultas, permitindo gerenciar autores e títulos, além de realizar buscas filtradas por idiomas e anos de vida dos autores.

Este desafio consolida conhecimentos em **Spring Boot**, **Spring Data JPA** e **PostgreSQL**.

---

## 🚀 Funcionalidades e Demonstração
- `Catálogo Amplo`: Acesso a mais de 70 mil livros através da integração com a API Gutendex.
- `Persistência de Dados`: Salva livros e autores pesquisados no banco de dados local.
- `Consultas Avançadas`: Filtros por autores vivos em determinado ano e quantidade de livros por idioma.
- `Estatísticas`: Exibição de dados literários coletados.

### 📽️ Demonstração em execução:
<div align="center">
  <img src="https://github.com/user-attachments/assets/df3e13e2-14d8-4e92-acfa-22e1efaf19cb" alt="GIF de demonstração LiterAlura" width="600">
</div>

---

## 🛠️ Técnicas e Tecnologias Utilizadas
- **Java 17+**: Linguagem de programação.
- **Spring Boot**: Framework base para a aplicação.
- **Spring Data JPA**: Gerenciamento de persistência e repositórios.
- **PostgreSQL**: Banco de dados relacional.
- **Jackson/Gson**: Processamento de JSON da API Gutendex.
- **IntelliJ IDEA**: Ambiente de desenvolvimento.

## 🔧 Como rodar o projeto
1. **Clone o repositório:**
   git clone https://github.com/Antonioafj/literalura-challenge-Java.git

2. **Configuração do Banco de Dados:**
   Certifique-se de ter o PostgreSQL instalado e configure as credenciais no arquivo `src/main/resources/application.properties`.

3. **Compile e execute:**
   Execute a aplicação através da sua IDE ou via terminal com:
   mvn spring-boot:run

## 📂 Acesso ao Projeto
Você pode acessar todos os arquivos do projeto [clicando aqui](https://github.com/Antonioafj/literalura-challenge-Java/tree/main).

---

## 👤 Autor
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Antonioafj">
        <img src="https://avatars.githubusercontent.com/u/167789057?v=4" width="115px;" alt="Antonio Alves Ferreira Jr."/><br>
        <sub><b>Antonio Alves Ferreira Jr.</b></sub>
      </a>
    </td>
  </tr>
</table>

---
*Projeto desenvolvido como parte do Challenge ONE - Alura + Oracle*
