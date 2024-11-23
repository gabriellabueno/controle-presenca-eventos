
SHOW DATABASES;

-- BANCO DE DADOS
CREATE DATABASE db_controle_presenca;
-- DROP DATABASE db_controle_presenca;

USE db_controle_presenca;

/*
DROP TABLE tb_evento;
DROP TABLE tb_palestrante;
DROP TABLE tb_inscricao;
DROP TABLE tb_palestra;
*/

-- CRIA TABELAS

CREATE TABLE tb_evento (
    id_evento_pk INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_evento VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    horaInicio TIME NOT NULL,
    horaFim TIME NOT NULL,
    duracao TINYINT NOT NULL,
    local VARCHAR(50) NOT NULL,
    status TINYINT NOT NULL,
    organizador VARCHAR(100)
);

CREATE TABLE tb_participante (
    cpf_participante_pk VARCHAR(15) NOT NULL PRIMARY KEY ,
    nome_participante VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cargaHoraria TINYINT,
    curso VARCHAR(20)
);

CREATE TABLE tb_palestrante (
    cpf_palestrante_pk VARCHAR(15) NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    titulo VARCHAR(50),
    biografia VARCHAR(255)
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

-- INSERE DADOS

-- Evento
INSERT INTO tb_evento (nome_evento, data, horaInicio, horaFim, duracao, local, status, organizador)
VALUES ('Seminário de Educação', '2024-12-05', '12:00:00', '16:00:00', 4, 'Auditório Principal', 1, 'Maria Souza');

-- Evento com dado nulo
INSERT INTO tb_evento (nome_evento, data, horaInicio, horaFim, duracao, local, status)
VALUES ('Palestra de Tecnologia', '2024-12-01', '08:30:00', '10:30:00', 2, 'Sala 101', 1);

--

-- Participante
INSERT INTO tb_participante 
VALUES ('11111111111', 'Ana Costa', 'ana@example.com', 2, 'Educação');

-- Participante com dado nulo
INSERT INTO tb_participante (cpf_participante_pk, nome_participante, email)
VALUES ('12345678901', 'Carlos Mendes', 'carlos@example.com');

-- Inscrição dos Participantes em Eventos
INSERT INTO tb_inscricao (id_evento_fk,  cpf_participante_fk)
VALUES
(1, '12345678901'),
(2, '11111111111');

--

-- Palestrante
INSERT INTO tb_palestrante 
VALUES ('22222222222', 'Dr. Henrique Pereira', 'henrique@example.com', 'Professor', 'Expert em Tecnologia Educacional');

-- Palestrante com dado nulo
INSERT INTO tb_palestrante (cpf_palestrante_pk, nome_responsavel, email)
VALUES ('55566677788', 'Profa. Julia Almeida', 'julia@example.com');

-- Palestra
INSERT INTO tb_palestra (id_evento_fk,  cpf_palestrante_fk)
VALUES
(1, '22222222222'),
(2, '55566677788');


-- TESTES

-- REMOÇÃO DE PARTICIPANTE NA TABELA INSCRIÇÃO
-- Deve remover apenas da tabela "tb_inscricao"
-- Deve manter registro em "tb_participante"

-- Acha ID da inscrição pelo CPF
SELECT id_inscricao 
FROM tb_inscricao
WHERE cpf_participante_fk LIKE('11111111111');

-- Remove participante do evento
DELETE FROM tb_inscricao 
WHERE id_inscricao = 4 
AND id_evento_fk = 2;

SELECT * FROM tb_inscricao;
SELECT * FROM tb_participante;

-- REMOÇÃO DE PARTICIPANTE NA TABELA PARTICIPANTE
-- Também deve ser removido da tabela "tb_inscricao" automaticamente

-- Remove participante pelo CPF
DELETE FROM tb_participante 
WHERE cpf_participante_pk = '12345678901';

SELECT * FROM tb_participante;
SELECT * FROM tb_inscricao;

--

-- REMOÇÃO DE PALESTRANTE NA TABELA PALESTRA
-- Deve remover apenas da tabela "tb_palestra"
-- Deve manter registro em "tb_palestrante" 

-- Acha ID da palestra pelo CPF
SELECT id_palestra 
FROM tb_palestra
WHERE cpf_palestrante_fk LIKE('22222222222');

-- Remove palestrante do evento
DELETE FROM tb_palestra 
WHERE id_palestra = 1 
AND id_evento_fk = 1;

SELECT * FROM tb_palestra;
SELECT * FROM tb_palestrante;

-- REMOÇÃO DE PALESTRANTE NA TABELA PALESTRANTE
-- Também deve ser removido da tabela "tb_palestra" automaticamente
DELETE FROM tb_palestrante WHERE cpf_palestrante_pk = '55566677788';
SELECT * FROM tb_palestrante;
SELECT * FROM tb_palestra;

--

-- TESTA REMOÇÃO DE UM EVENTO
-- Também deve deletar incricao e palestra automaticamente
DELETE FROM tb_evento WHERE id_evento_pk = 1;
SELECT * FROM tb_evento;
SELECT * FROM tb_inscricao;
SELECT * FROM tb_palestra;

--


-- COMANDOS

SHOW TABLES;

SELECT * FROM tb_evento;
SELECT * FROM tb_participante;
SELECT * FROM tb_palestrante;
SELECT * FROM tb_inscricao;
SELECT * FROM tb_palestra;

/*
TRUNCATE TABLE tb_evento;
TRUNCATE TABLE tb_participante;
TRUNCATE TABLE tb_palestrante;
TRUNCATE TABLE tb_inscricao;
TRUNCATE TABLE tb_palestra;
*/

