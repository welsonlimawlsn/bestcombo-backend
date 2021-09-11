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

INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (1, 'Parceiro');
INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (2, 'Cliente');
INSERT INTO tb_tipo_pessoa (codigo_tipo_pessoa, descricao_tipo_pessoa)
VALUES (3, 'Administrador');

