create table tb_caso_de_uso
(
    codigo_caso_de_uso    integer     not null
        constraint pk_caso_de_uso primary key,
    descricao_caso_de_uso varchar(50) not null
);

create table tb_papel
(
    codigo_papel    integer     not null
        constraint pk_papel primary key,
    descricao_papel varchar(50) not null,
    nome_papel      varchar(50) not null
);

create table tb_caso_de_uso_papel
(
    codigo_papel       integer not null
        constraint fk_codigo_papel references tb_papel,
    codigo_caso_de_uso integer not null
        constraint pk_codigo_caso_de_uso references tb_caso_de_uso,
    constraint pk_caso_de_uso_papel
        primary key (codigo_papel, codigo_caso_de_uso)
);

create table tb_tipo_pessoa
(
    codigo_tipo_pessoa    integer
        constraint pk_tipo_pesso primary key,
    descricao_tipo_pessoa varchar(50) not null
);

create table tb_pessoa
(
    codigo_pessoa    uuid         not null
        constraint pk_pessoa primary key,
    cpf_pessoa       varchar(11)  not null,
    email_pessoa     varchar(255) not null
        constraint uk_email_pessoa unique,
    telefone_pessoa  varchar(11)  not null,
    nome_pessoa      varchar(50)  not null,
    sobrenome_pessoa varchar(50)  not null,
    tipo_pessoa      integer      not null
        constraint fk_tipo_pessoa references tb_tipo_pessoa,
    usuario_pessoa   varchar(30)  not null
        constraint uk_usuario_pessoa unique,
    constraint uk_cpf_tipo_pessoa unique (cpf_pessoa, tipo_pessoa)
);

create table tb_endereco_pessoa
(
    codigo_endereco_pessoa      uuid         not null
        constraint pk_endereco_pessoa primary key,
    bairro_endereco_pessoa      varchar(50)  not null,
    cep_endereco_pessoa         varchar(9)   not null,
    cidade_endereco_pessoa      varchar(50)  not null,
    complemento_endereco_pessoa varchar(50),
    estado_endereco_pessoa      varchar(50)  not null,
    numero_endereco_pessoa      varchar(10)  not null,
    rua_endereco_pessoa         varchar(100) not null,
    codigo_pessoa               uuid         not null
        constraint fk_codigo_pessoa references tb_pessoa
);

create table tb_endereco_loja
(
    codigo_endereco_loja      uuid         not null
        constraint pk_endereco_loja primary key,
    bairro_endereco_loja      varchar(50)  not null,
    cep_endereco_loja         varchar(9)   not null,
    cidade_endereco_loja      varchar(50)  not null,
    complemento_endereco_loja varchar(50),
    estado_endereco_loja      varchar(50)  not null,
    numero_endereco_loja      varchar(10)  not null,
    rua_endereco_loja         varchar(100) not null
);

create table tb_loja
(
    codigo_loja          uuid         not null
        constraint pk_loja primary key,
    cnpj_loja            varchar(14)  not null
        constraint uk_cnpj_loja unique,
    nome_loja            varchar(100) not null,
    telefone_loja        varchar(11)  not null,
    descricao_loja       varchar(500) not null,
    imagem_loja          varchar(100) not null,
    chave_pix_loja       varchar(100),
    codigo_endereco_loja uuid         not null
        constraint fk_endereco_loja references tb_endereco_loja,
    codigo_pessoa        uuid         not null
        constraint fk_codigo_pessoa references tb_pessoa,
    constraint uk_codigo_pessoa unique (codigo_pessoa)
);

create table tb_tipo_servico
(
    codigo_tipo_servico    int          not null
        constraint pk_tipo_servico primary key,
    descricao_tipo_servico varchar(100) not null,
    nome_tipo_servico      varchar(50)  not null
);


create table tb_produto
(
    codigo_produto     uuid primary key,
    nome_produto       varchar(50)    not null,
    descricao          varchar(100)   not null,
    preco              numeric(12, 2) not null,
    quantidade_pessoas int            not null,
    imagem             varchar(100)   not null,
    codigo_loja        uuid           not null references tb_loja,
    ativo              bool           not null
);


create table tb_categoria
(
    codigo_categoria    int primary key,
    nome_categoria      varchar(50)                    not null,
    codigo_tipo_servico int references tb_tipo_servico not null
);


create table tb_produto_categoria
(
    codigo_produto   uuid references tb_produto,
    codigo_categoria int references tb_categoria,
    constraint pk_produto_categoria primary key (codigo_categoria, codigo_produto)
);

create table tb_loja_categoria
(
    codigo_loja      uuid references tb_loja,
    codigo_categoria int references tb_categoria,
    constraint pk_loja_categoria primary key (codigo_loja, codigo_categoria)
);

create table tb_tipo_situacao_pedido
(
    codigo_tipo_situacao_pedido int primary key,
    nome_situacao_pedido        varchar(50) not null
);

create table tb_pedido
(
    codigo_pedido                  uuid primary key,
    codigo_cliente_pedido          uuid           not null references tb_pessoa,
    codigo_loja_pedido             uuid           not null references tb_loja,
    data_pedido                    timestamp      not null,
    data_ultima_atualizacao_pedido timestamp      not null,
    data_agendamento_pedido        timestamp      not null,
    codigo_situacao_pedido         int            not null references tb_tipo_situacao_pedido,
    valor_total_pedido             decimal(12, 2) not null,
    observacao_pedido              varchar(500),
    motivo_cancelamento_pedido     varchar(500)
);

create table tb_pedido_produto
(
    codigo_pedido          uuid           not null references tb_pedido,
    codigo_produto         uuid           not null references tb_produto,
    valor_unitario_produto decimal(12, 2) not null,
    valor_total_produto    decimal(12, 2) not null,
    quantidade_produto     int            not null,
    CONSTRAINT pk_pedido_produto primary key (codigo_pedido, codigo_produto)
);

create table tb_tipo_movimento_conta
(
    codigo    int primary key,
    descricao varchar(50) not null
);

create table tb_saldo
(
    codigo_loja      uuid           not null references tb_loja,
    data_saldo       date           not null,
    valor_disponivel decimal(12, 2) not null,
    valor_futuro     decimal(12, 2) not null,
    constraint saldo_pk primary key (codigo_loja, data_saldo)
);

create table tb_movimento_conta
(
    codigo_movimento_conta          uuid primary key,
    codigo_loja                     uuid           not null references tb_loja,
    codigo_pedido                   uuid references tb_pedido,
    tipo_movimento_conta            int            not null references tb_tipo_movimento_conta,
    valor_movimento_conta           decimal(12, 2) not null,
    data_movimento_conta            timestamp      not null,
    data_efetivacao_movimento_conta timestamp,
    efetivado_movimento_conta       bool           not null,
    descricao_movimento_conta       varchar(50)    not null,
    UNIQUE (codigo_loja, codigo_pedido, tipo_movimento_conta)
);

create table tb_pagarme_pedido
(
    codigo_pedido    uuid references tb_pedido primary key,
    codigo_cartao    varchar(50) not null,
    codigo_transacao varchar(50)
);

create table tb_situacao_andamento_solicitacao_saque
(
    codigo    int primary key,
    descricao varchar(50) not null
);

create table tb_solicitacao_saque
(
    codigo_solicitacao_saque                  uuid primary key,
    codigo_situacao_solicitacao_saque         int references tb_situacao_andamento_solicitacao_saque not null,
    data_solicitacao_saque                    timestamp                                              not null,
    data_ultima_atualizacao_solicitacao_saque timestamp                                              not null,
    codigo_loja_solicitacao_saque             uuid references tb_loja                                not null,
    chave_pix_solicitacao_saque               varchar(100)                                           not null,
    valor_solicitacao_saque                   decimal(12, 2)                                         not null
);

alter table tb_movimento_conta
    add codigo_solicitacao_saque uuid references tb_solicitacao_saque unique;

create table tb_parametros
(
    id        int primary key,
    descricao varchar(50)  not null,
    valor     varchar(100) not null
);

alter table tb_loja add column data_cadastro_loja timestamp default now();

alter table tb_produto add column data_cadastro_produto timestamp default now();