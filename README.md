
# 🥗 NutriWeb

NutriWeb é uma aplicação web desenvolvida para auxiliar profissionais da nutrição no gerenciamento de planos alimentares, cadastros de pacientes e controle de avaliações nutricionais.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Maven**
- **JUnit 5**
- **Selenium WebDriver**
- **Docker**
- **GitLab CI/CD**

---

## 📁 Estrutura do Projeto

```
nutriweb/
├── Entrega01/
│   ├── Plano_de_Teste/
│   └── inspecao/
├── Entrega02/
│   └── NutriWebNew/
│       ├── Dockerfile
│       ├── pom.xml
│       └── src/
│           ├── main/
│           └── test/
└── .gitlab-ci.yml
```

---

## ⚙️ Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/JuanAbdallah/Nutriweb.git
cd nutriweb/Entrega02/NutriWebNew/NutriWebNew
```

### 2. Rodar a aplicação localmente

```bash
./mvnw spring-boot:run
```

Acesse em: `http://localhost:8080`

---

## ✅ Testes

A aplicação possui uma suíte robusta de testes automatizados e documentação de testes manuais.

### 🧪 Testes Automatizados

- Localizados em: `src/test/java/`
- Frameworks: **JUnit 5** + **Selenium WebDriver**
- Cobertura: autenticação, cadastros, segurança, fluxo de uso

#### Rodar testes:

```bash
./mvnw test
```

### 📝 Plano de Testes Manual

Documentação disponível em:

```
Entrega01/Plano_de_Teste/Plano de Testes.docx
```

Inclui:

- Casos de Teste Funcionais
- Critérios de Aceitação
- Riscos e Métricas
- Cobertura de Requisitos

---

## 🤖 Integração Contínua e Deploy (CI/CD)

Através do GitLab CI/CD, o projeto é automaticamente:

1. **Buildado** com Maven
2. **Testado** com JUnit
3. **Empacotado** (.jar)
4. **Implantado** via **SSH/SCP** em um servidor **Windows Server**

O pipeline está definido em `.gitlab-ci.yml`. As credenciais e variáveis sensíveis (como IP, usuário e senha/SSH Key) devem estar configuradas nas *CI/CD Variables* do GitLab.

---

## 🐳 Docker (Opcional)

Se preferir usar Docker:

```bash
docker build -t nutriweb .
docker run -p 8080:8080 nutriweb
```

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos. Licença a ser definida.

---

## 👥 Autores

- Juan Abdallah Ritti de Oliveira
- Rafael Ferreira Campos

