# ETL- Locadora e Vendas
1. Criar os usuários: locadora, dw_locadora, vendas e dw_vendas;
2. Conectar com o usuário locadora e executar o script da base operacional;
3. Abrir o modelo dimensional da locadora (Data Modeler) e gerar o script da base dimensional;
4. Conectar com o usuário dw_locadora e executar o script do passo 3;
5. Conectar com o usuário vendas e executar o script da base operacional;
6. Abrir o modelo dimensional "vendas" (Data Modeler), e gerar o script da base dimensional;
7. Conectar com o usuário dw_vendas e executar o script do passo 6;

Executando esses passos vocês terão:
1. Estudo de caso "Locadora" onde:
a) O usuário "locadora" contém a base transacional;
b) O usuário "dw_locadora" contém a base dimensional;
2. Estudo de caso "Vendas", onde:
a) O usuáro "vendas" contém a base transacional;
b) O usuário "dw_vendas" contém a base dimensional;

Tarefa:
-----------

1. Criar processos ETL (Extract, Transform and Load) das bases transacionais para as bases dimensionais dos estudos de caso:

a) Locadora;
b) Vendas
