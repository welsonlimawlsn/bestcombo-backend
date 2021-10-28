INSERT INTO tb_papel (codigo_papel, descricao_papel, nome_papel)
VALUES (1, 'Parceiros', 'PAPEL_PARCEIRO');
INSERT INTO tb_papel (codigo_papel, descricao_papel, nome_papel)
VALUES (2, 'Cliente', 'PAPEL_CLIENTE');
INSERT INTO tb_papel (codigo_papel, descricao_papel, nome_papel)
VALUES (3, 'Administrador', 'PAPEL_ADMINISTRADOR');

INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (1, 'Novo Parceiro');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (2, 'Buscar Parceiro Por Código');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (3, 'Lista Parceiros');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (4, 'Busca Loja Por Parceiro');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (5, 'Cadastrar Loja');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (6, 'Listar Lojas');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (7, 'Cadastrar Produto');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (8, 'Listar Produtos');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (9, 'Cadastrar Cliente');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (10, 'Download Imagem');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (11, 'Upload Imagem');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (12, 'Busca Endereço por CEP');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (13, 'Lista Categorias');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (14, 'Busca Lojas por Termo');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (15, 'Busca Produto por Código');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (16, 'Cadastrar Novo Pedido');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (17, 'Parceiro Aceita Pedido');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (18, 'Atualiza Todos Pedidos Pendentes Pagamento');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (19, 'Lista Pedidos');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (20, 'Busca Pedido por Código');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (21, 'Consulta Saldos Parceiro');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (22, 'Lista Movimentos Por Data Parceiro');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (23, 'Adiciona Chave Pix');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (24, 'Nova solicitação de saque');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (25, 'Consulta Solicitação Saldo Andamento');
INSERT INTO tb_caso_de_uso (codigo_caso_de_uso, descricao_caso_de_uso)
VALUES (26, 'Altera Situacao Solicitação Saldo');


INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (3, 2);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 2);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (3, 3);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 4);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (3, 4);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 5);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 7);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 11);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (2, 16);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 17);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 19);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (2, 19);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 20);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (2, 20);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 21);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 22);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 23);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 24);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (1, 25);
INSERT INTO tb_caso_de_uso_papel (codigo_papel, codigo_caso_de_uso)
VALUES (3, 26);

INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (1, 'Parceiro');
INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (2, 'Cliente');
INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (3, 'Administrador');


insert into tb_tipo_servico (codigo_tipo_servico, descricao_tipo_servico, nome_tipo_servico)
VALUES (1, 'Venda de produtos alimentícios', 'Produtos Alimentícios');
insert into tb_tipo_servico (codigo_tipo_servico, descricao_tipo_servico, nome_tipo_servico)
VALUES (2, 'Prestação de serviços variados, como aluguel de itens para festa, garçons, conzinheiros etc.',
        'Prestação de Serviços');

INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (1, 'Salgados', 1);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (2, 'Doces', 1);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (3, 'Bolos', 1);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (8, 'Bebidas', 1);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (4, 'Cozinheiro', 2);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (5, 'Brinquedos infláveis', 2);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (6, 'Decoração', 2);
INSERT INTO tb_categoria (codigo_categoria, nome_categoria, codigo_tipo_servico)
VALUES (7, 'Serviço de Mesa', 2);

INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (1, 'Aguardando Aprovação do Parceiro');
INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (2, 'Aguardando Pagamento do Cliente');
INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (3, 'Parceiro Preparando Pedido');
INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (4, 'Parceiro Preparando Pedido');
INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (5, 'Pedido concluido');
INSERT INTO tb_situacao_pedido (codigo_situacao_pedido, nome_situacao_pedido)
VALUES (6, 'Pedido Cancelado');


INSERT INTO tb_tipo_movimento_conta (codigo, descricao)
VALUES (1, 'Crédito');
INSERT INTO tb_tipo_movimento_conta (codigo, descricao)
VALUES (2, 'Débito');

INSERT INTO tb_situacao_andamento_solicitacao_saque (codigo, descricao)
VALUES (1, 'Solicitado');
INSERT INTO tb_situacao_andamento_solicitacao_saque (codigo, descricao)
VALUES (2, 'Em andamento');
INSERT INTO tb_situacao_andamento_solicitacao_saque (codigo, descricao)
VALUES (3, 'Concluido');


INSERT INTO tb_pessoa (codigo_pessoa, cpf_pessoa, email_pessoa, nome_pessoa, sobrenome_pessoa, tipo_pessoa,
                       usuario_pessoa)
VALUES ('c215d450-917f-4d7c-ac49-e14ff0498f15', '00000000000', 'contato@bestcombo.com', 'BestCombo', 'BestCombo', 3,
        'bestcombo');

INSERT INTO tb_endereco_loja (codigo_endereco_loja, bairro_endereco_loja, cep_endereco_loja, cidade_endereco_loja,
                              complemento_endereco_loja, estado_endereco_loja, numero_endereco_loja, rua_endereco_loja)
VALUES ('83eeeb6d-b7a6-498b-81c1-96d4aba777b3', 'Ceilândia Norte', '72275-107', 'Brasília', null, 'DF', '8',
        'QNR 01 Conjunto G');

INSERT INTO tb_loja (codigo_loja, cnpj_loja, nome_loja, codigo_endereco_loja, codigo_pessoa, imagem_loja,
                     descricao_loja)
VALUES ('ea31c776-fadb-4c4d-895f-84d18d25b452', '00000000000000', 'BestCombo', '83eeeb6d-b7a6-498b-81c1-96d4aba777b3',
        'c215d450-917f-4d7c-ac49-e14ff0498f15', 'semimagem', 'BestCombo');