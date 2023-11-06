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

INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('João Silva', '1985-04-12 00:00:00', '12345678901', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Maria Oliveira', '1990-07-19 00:00:00', '23456789012', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Pedro Alcântara', '1975-09-23 00:00:00', '34567890123', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Ana Sousa', '2000-01-02 00:00:00', '45678901234', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Luiz Costa', '1988-05-15 00:00:00', '56789012345', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Patrícia Nobre', '1995-03-28 00:00:00', '67890123456', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Rafael Santos', '1982-10-31 00:00:00', '78901234567', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Fernanda Gomes', '1999-12-10 00:00:00', '89012345678', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Carlos Magno', '1970-06-22 00:00:00', '90123456789', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Tatiana Azambuja', '2002-08-05 00:00:00', '01234567890', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Roberto Rocha', '1994-04-17 00:00:00', '12345067801', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Juliana Queiroz', '1986-11-30 00:00:00', '23456078912', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('André Lima', '1992-02-20 00:00:00', '34567089023', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Camila Porto', '1983-05-25 00:00:00', '45678090134', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Marcos Braga', '2001-07-07 00:00:00', '56789001245', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Sofia Castelo', '1997-09-09 00:00:00', '67890112356', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Ricardo Neves', '1968-12-15 00:00:00', '78901223467', false);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Vanessa Dias', '1993-03-03 00:00:00', '89012334578', true);
INSERT INTO public.pessoa (nome, datanascimento, cpf, funcionario) VALUES ('Thiago Moraes', '1987-01-01 00:00:00', '90123445689', false);


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
