# Login Seguro

Projeto de login e gerenciamento de usuários feito com Java, Spring Boot, Thymeleaf e MongoDB Atlas. A ideia foi montar uma base simples e segura, que possa ser adaptada para outros projetos no futuro sem precisar mudar toda a lógica.

## Tecnologias utilizadas

* Java 25
* Spring Boot
* Spring Security
* Thymeleaf
* MongoDB Atlas
* HTML e CSS

## Funcionalidades

* Cadastro de usuários com validação dos dados.
* Login e logout usando Spring Security.
* Senhas protegidas com hash BCrypt.
* Controle de acesso por perfil.
* Sessões armazenadas no MongoDB Atlas.
* Área administrativa para consultar usuários e alterar seus perfis.
* Interface com CSS separado do código Java, facilitando futuras mudanças visuais.

### Perfis de acesso

* **UsuarioComum:** acesso à área comum.
* **UsuarioVIP:** acesso à área comum e à área VIP.
* **Admin:** acesso às áreas comum, VIP e administrativa.

Novos cadastros recebem o perfil `UsuarioComum`. O administrador pode alterar os perfis dos usuários pela área administrativa.

## Como executar

### 1. Pré-requisitos

Você precisa ter Java instalado, acesso ao MongoDB Atlas e Git (caso queira clonar o projeto).

### 2. Clonar o repositório

```bash
git clone https://github.com/0xRibeiro/Login-Seguro.git
cd Login-Seguro
```

### 3. Configurar o MongoDB Atlas

Crie um cluster no MongoDB Atlas, um usuário de banco de dados e libere o acesso da sua máquina nas configurações de rede.

Copie a URI de conexão do Atlas. O usuário do banco precisa ter permissão para ler e gravar dados.

### 4. Configurar o arquivo `.env`

Crie um arquivo chamado `.env` na raiz do projeto, no mesmo nível do `pom.xml`, com estas variáveis:

```properties
MONGODB_URI=sua_uri_de_conexao_do_mongodb
ADMIN_EMAIL=admin@seusistema.com
ADMIN_PASSWORD=coloque_uma_senha_forte_aqui
```

Substitua os valores de exemplo pelos seus dados reais. A URI deve apontar para o banco `login_seguro`.

As variáveis `ADMIN_EMAIL` e `ADMIN_PASSWORD` são usadas para criar uma conta de administrador inicial, caso ela ainda não exista.

**Importante:** não compartilhe suas credenciais do arquivo `.env`

### 5. Executar o projeto

Rode o projeto através de sua IDE como Intellij ou Netbeans

Depois, acesse http://localhost:8080.

A tela de login é disponibilizada pelo Spring Security. Para criar uma conta comum, acesse `/cadastro`.

## Estrutura do projeto

* `config`: configurações de segurança, senha, sessões e administrador inicial.
* `controller`: controla as rotas e as páginas.
* `model`: representa os dados dos usuários.
* `repository`: faz a comunicação com o MongoDB.
* `service`: concentra as regras de negócio dos usuários.
* `resources/templates`: páginas HTML com Thymeleaf.
* `resources/static/css`: estilos visuais da aplicação.

## Decisões de projeto

As senhas são armazenadas com BCrypt, e o Spring Security controla a autenticação e as permissões de cada perfil. Os usuários e as sessões são armazenados no MongoDB Atlas.

A apresentação foi separada da lógica de negócio. Assim, mudanças nas páginas podem ser feitas nos templates e no CSS sem precisar reescrever o sistema de login. As cores também variam conforme o perfil do usuário, servindo como uma base para futuras personalizações visuais.

O projeto foi organizado em módulos para facilitar a manutenção e a adaptação para outros temas.
