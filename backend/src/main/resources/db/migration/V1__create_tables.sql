-- V1__create_tables.sql
-- Script de criação inicial das tabelas do banco de dados Boação.

CREATE TABLE atividade (
    id INT IDENTITY NOT NULL,
    id_ong INT,
    id_tag INT,
    possui_certificacao BIT,
    criado_em DATETIME2(6),
    data_atividade DATETIME2(6),
    status_atividade VARCHAR(20) NOT NULL CHECK (status_atividade IN ('ANDAMENTO','ENCERRADA','CONGELADA')),
    carga_horaria_diaria VARCHAR(255),
    descricao VARCHAR(255),
    endereco_completo VARCHAR(255),
    nome VARCHAR(255),
    periodo VARCHAR(255) CHECK (periodo IN ('MANHA','TARDE','NOITE')),
    titulo VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE avaliacao (
    estrelas INT NOT NULL,
    id INT IDENTITY NOT NULL,
    is_avaliado BIT NOT NULL,
    ong_id INT,
    feedback VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE endereco (
    id INT IDENTITY NOT NULL,
    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    numero VARCHAR(255),
    pais VARCHAR(255),
    rua VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE historico_atividade (
    atividade_id INT,
    avaliacao_id INT,
    certificado BIT NOT NULL,
    encerrado_em DATE,
    id INT IDENTITY NOT NULL,
    voluntario_id INT,
    carga_horaria DATETIME2(6),
    data_inscricao DATETIME2(6),
    status_candidatura VARCHAR(255) CHECK (status_candidatura IN ('PENDENTE','REALIZADO','APROVADO','REJEITADO')),
    PRIMARY KEY (id)
);

CREATE TABLE ong (
    endereco_id INT,
    id INT IDENTITY NOT NULL,
    usuario_id INT,
    cnpj VARCHAR(255),
    nome_entidade VARCHAR(255),
    telefone VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE ongs_tags (
    ong_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (ong_id, tag_id)
);

CREATE TABLE password_recover (
    expiration DATETIMEOFFSET(6) NOT NULL,
    id BIGINT IDENTITY NOT NULL,
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role (
    id INT IDENTITY NOT NULL,
    authority VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tag (
    id INT IDENTITY NOT NULL,
    nome VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id INT IDENTITY NOT NULL,
    role_id INT,
    criado_em DATETIME,
    email VARCHAR(255),
    senha VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE voluntario (
    data_nascimento DATE,
    endereco_id INT,
    id INT IDENTITY NOT NULL,
    usuario_id INT,
    cpf VARCHAR(255),
    nome_completo VARCHAR(255),
    telefone VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE voluntarios_tags (
    tag_id INT NOT NULL,
    voluntario_id INT NOT NULL,
    PRIMARY KEY (tag_id, voluntario_id)
);

CREATE UNIQUE NONCLUSTERED INDEX UKbn7aqxiqucuykyh05rf2eh9pn ON historico_atividade (avaliacao_id) WHERE avaliacao_id IS NOT NULL;
CREATE UNIQUE NONCLUSTERED INDEX UKrh16fjcgwj8sj5d9bw1f1l0px ON ong (endereco_id) WHERE endereco_id IS NOT NULL;
CREATE UNIQUE NONCLUSTERED INDEX UKb9upe8wxxv52prab80of3igxd ON ong (usuario_id) WHERE usuario_id IS NOT NULL;
CREATE UNIQUE NONCLUSTERED INDEX UK4xmoioem1rmgrmggjoofc15e4 ON voluntario (endereco_id) WHERE endereco_id IS NOT NULL;
CREATE UNIQUE NONCLUSTERED INDEX UK7vp2lc97f1xwfv4o7rs86lcia ON voluntario (usuario_id) WHERE usuario_id IS NOT NULL;

ALTER TABLE atividade ADD CONSTRAINT FKbujs5t8vnw4ltjsua5scy5y1v FOREIGN KEY (id_ong) REFERENCES ong;
ALTER TABLE atividade ADD CONSTRAINT FKbuntm4lbobt6vxb48q57lpg4a FOREIGN KEY (id_tag) REFERENCES tag;
ALTER TABLE avaliacao ADD CONSTRAINT FKmser7kg88pqql915kpw85x1yf FOREIGN KEY (ong_id) REFERENCES ong;
ALTER TABLE historico_atividade ADD CONSTRAINT FKfc5wr6swxpa5oh4r4ultkw8fp FOREIGN KEY (atividade_id) REFERENCES atividade;
ALTER TABLE historico_atividade ADD CONSTRAINT FKfnngymyh4up0ohcmbgnqsyp2o FOREIGN KEY (avaliacao_id) REFERENCES avaliacao;
ALTER TABLE historico_atividade ADD CONSTRAINT FKr2skrdl34j201rlq3kh3jeshp FOREIGN KEY (voluntario_id) REFERENCES voluntario;
ALTER TABLE ong ADD CONSTRAINT FKjj3ot3h8p5rgd2dsjosltfu0q FOREIGN KEY (endereco_id) REFERENCES endereco;
ALTER TABLE ong ADD CONSTRAINT FKefdovx9panloqylktv9nc62ep FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE ongs_tags ADD CONSTRAINT FK77flcmec7al27jwo7p3udxo4h FOREIGN KEY (tag_id) REFERENCES tag;
ALTER TABLE ongs_tags ADD CONSTRAINT FK13ksup4i1hq1lx88ycgx7lvid FOREIGN KEY (ong_id) REFERENCES ong;
ALTER TABLE usuario ADD CONSTRAINT FK3qieopaxyoowdge29v1m51wr9 FOREIGN KEY (role_id) REFERENCES role;
ALTER TABLE voluntario ADD CONSTRAINT FKgpm1xlv4n4pk2qf8kiel4lmhd FOREIGN KEY (endereco_id) REFERENCES endereco;
ALTER TABLE voluntario ADD CONSTRAINT FK9t3ostrlp7b4588xly90q4hk1 FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE voluntarios_tags ADD CONSTRAINT FKgqwtjo1w8iee62ldgbf9yk289 FOREIGN KEY (tag_id) REFERENCES tag;
ALTER TABLE voluntarios_tags ADD CONSTRAINT FKl3nop9d5od5x5rqiis5fwjb58 FOREIGN KEY (voluntario_id) REFERENCES voluntario;