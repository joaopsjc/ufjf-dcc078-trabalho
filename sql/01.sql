

CREATE TABLE tipoUsuario (
    id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    nome varchar(50) not null
);

INSERT INTO tipoUsuario (nome) values
('Cliente'),
('Empresa'),
('Entregador');


CREATE TABLE usuario (
    id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    nome VARCHAR(100),
    login VARCHAR(100),
    senha VARCHAR(100),
    cpf varchar(11),
    cnpj varchar(14),
    tipoUsuarioId int not null,
    CONSTRAINT fk_usuario_tipoUsuario FOREIGN KEY (tipoUsuarioId) REFERENCES tipoUsuario
);

