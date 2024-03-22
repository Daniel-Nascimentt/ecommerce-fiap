# TECH CHALLENGE 5ª FASE - E-COMMERCE FIAP


O sistema de e-commerce possui arquitetura de microserviços composto por seis microserviços. 
Cada microserviço desempenha funções específicas dentro do ecossistema, com interações entre eles facilitadas pelo MS-Gateway do Spring.
Todos os microserviços foram desenvolvidos em Java com Spring Boot e Spring Security, com exceção do microserviço de gestão de itens. Esse micro service não possui spring security para facilitar os testes da arquitetura como um todo.
Cada microserviço que contém o Spring security é capaz de validar tokens gerados pelo microserviço de autenticação e todos implementam validação de entrada de dados para garantir a integridade dos mesmos. Cada micro serviço possuí sua única base de dados, conforme os princípios da arquitetura de microserviços. 

 Abaixo está um resumo de cada microserviço e suas funcionalidades:

1. *Microserviço de Autenticação:*
   - Responsável pela autenticação de usuários e geração de tokens JWT.
   - Utiliza Spring Security e práticas recomendadas de segurança, como bcrypt, para encriptar senhas de usuário.
   - Ao registrar um novo usuário, se comunica com o MS de Clientes para cadastra-lo na base do respectivo MS.

2. *Microserviço de Clientes:*
   - Gerencia informações dos clientes, incluindo operações CRUD.
   - Integra-se ao microserviço de autenticação para receber dados de novos usuários cadastrados.

3. *Microserviço de Gestão de Itens:*
   - Administra o catálogo de produtos do e-commerce, com operações CRUD.
   - Não possui Spring Security para simplificar o fluxo de teste da arquitetura. Poderia ser implementado um outro micro serviço de Funcionarios por exemplo, ai poderia implementar spring security em ambos para compor a lógica de cadastro de itens na plataforma. 

4. *Microserviço de Carrinho:*
   - Permite a gestão de carrinhos de compras, com operações para adicionar, remover e visualizar itens.
   - Requer autenticação e integra-se ao microserviço de gestão de itens para validar a disponibilidade de itens.

5. *Microserviço de Pagamentos:*
   - Simula o processo de pagamento de carrinhos de compras. É apenas um micro serviço que atualiza o status do carrinho para *PAGO*.
   - Recebe o ID do usuário e fecha o carrinho correspondente, atualizando seu status como pago.

6. *MS-Gateway do Spring:*
   - Faz o direcionamento das requisições para os devidos microserviços garantindo segurança e controle de tráfego.


 

