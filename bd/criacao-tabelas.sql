
CREATE TABLE tb_evento (
    id_evento_pk INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_evento VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    horaInicio TIME NOT NULL,
    horaFim TIME NOT NULL,
    duracao TINYINT NOT NULL,
    local VARCHAR(50) NOT NULL,
    status TINYINT NOT NULL,
    organizador VARCHAR(100) NOT NULL
);

CREATE TABLE tb_participante (
    cpf_participante_pk VARCHAR(15) NOT NULL PRIMARY KEY ,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    curso VARCHAR(20) NOT NULL,
    cargaHoraria TINYINT
);

CREATE TABLE tb_palestrante (
    cpf_palestrante_pk VARCHAR(15) NOT NULL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE tb_inscricao (
    id_inscricao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_evento_fk INT NOT NULL,
    cpf_participante_fk VARCHAR(15) NOT NULL UNIQUE,
    FOREIGN KEY (id_evento_fk) REFERENCES tb_evento(id_evento_pk)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cpf_participante_fk) REFERENCES tb_participante(cpf_participante_pk)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tb_palestra (
    id_palestra INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_evento_fk INT NOT NULL,
    cpf_palestrante_fk VARCHAR(15) NOT NULL UNIQUE,
    FOREIGN KEY(id_evento_fk) REFERENCES tb_evento(id_evento_pk)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cpf_palestrante_fk) 
    REFERENCES tb_palestrante(cpf_palestrante_pk)
	ON DELETE CASCADE ON UPDATE CASCADE
);
