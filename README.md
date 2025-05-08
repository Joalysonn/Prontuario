Arquitetura e Tecnologias:

Backend:
Java com Spring Boot
RESTful API
PostgreSQL como banco de dados
Maven para gerenciamento de dependências

Frontend:
Angular (baseado na configuração do CORS e estrutura do projeto)
Nginx como servidor web

Infraestrutura:
Docker e Docker Compose para containerização
Três containers principais:
Backend (porta 8080)
Frontend (porta 80)
Banco de dados PostgreSQL (porta 5432)

Funcionalidades Principais:
O sistema possui dois módulos principais:
Gestão de Pacientes:
Cadastro de pacientes
Consulta de pacientes
Atualização de dados
Remoção de pacientes
Gestão de Prontuários:
Criação de prontuários
Consulta de prontuários
Busca de prontuários por paciente
Atualização de prontuários
Remoção de prontuários

Estrutura da API:
Endpoints RESTful:
/api/pacientes - Operações CRUD de pacientes
/api/prontuarios - Operações CRUD de prontuários
/api/prontuarios/paciente/{pacienteId} - Busca de prontuários por paciente

Características Técnicas:
API RESTful com suporte a CORS
Arquitetura em camadas (Controller, Service, Model)
Persistência de dados em PostgreSQL
Containerização completa da aplicação
Configuração de ambiente via variáveis de ambiente
