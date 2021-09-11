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
    codigo_endereco_loja uuid         not null
        constraint fk_endereco_loja references tb_endereco_loja,
    codigo_pessoa        uuid         not null
        constraint fk_codigo_pessoa references tb_pessoa,
    constraint uk_codigo_pessoa unique (codigo_pessoa)
);

create table tb_produto
(
 codigo_produto uuid primary key,
 nome_produto varchar(50) not null,
 descricao varchar (100) not null,
 preco numeric(12,2) not null,
 codigo_loja uuid not null references tb_loja
);