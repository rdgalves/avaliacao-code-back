-- -----------------------------------------------------
-- Table: Pessoa
-- -----------------------------------------------------
CREATE TABLE pessoa (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    datanascimento DATE,
    cpf VARCHAR(14),
    funcionario BOOLEAN,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table: Projeto
-- -----------------------------------------------------
CREATE TABLE projeto (
    id BIGSERIAL NOT NULL,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE,
    data_previsao_fim DATE,
    data_fim DATE,
    descricao VARCHAR(5000),
    status VARCHAR(45),
    orcamento FLOAT,
    risco VARCHAR(45),
    idgerente BIGINT NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente) REFERENCES pessoa (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table: Membros
-- -----------------------------------------------------
CREATE TABLE membros (
    idprojeto BIGINT NOT NULL,
    idpessoa BIGINT NOT NULL,
    CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto, idpessoa),
    CONSTRAINT fk_membros_projeto FOREIGN KEY (idprojeto) REFERENCES projeto (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa) REFERENCES pessoa (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
