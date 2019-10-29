
DROP TABLE usuario;
DROP TABLE TipoUsuario;

CREATE TABLE usuario (
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	nome VARCHAR(100),
	login VARCHAR(100),
	senha VARCHAR(100),
	documento VARCHAR(20),
	tipoUsuario VARCHAR(100) not null
);

CREATE TABLE endereco(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_usuario INTEGER not null,
	numero VARCHAR(50),
	nome VARCHAR(100),
	bairro VARCHAR(100),
	cidade VARCHAR(100),
	estado VARCHAR(100),
	tipoEndereco VARCHAR(100),
	cep VARCHAR(10),
	CONSTRAINT fk_endereco_usuario FOREIGN KEY (id_usuario) REFERENCES usuario
);

CREATE TABLE contato(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_usuario INTEGER not null,
	valor VARCHAR(100),
	tipoContato VARCHAR(100),
	cep INTEGER,
	CONSTRAINT fk_contato_usuario FOREIGN KEY (id_usuario) REFERENCES usuario
);

CREATE TABLE dadosBancarios(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_usuario INTEGER not null,
	nomeBanco VARCHAR(100),
	codigoBanco INTEGER,
	agencia INTEGER,
	numero INTEGER,
	CONSTRAINT fk_dadosBancarios_usuario FOREIGN KEY (id_usuario) REFERENCES usuario
);

CREATE TABLE pedido(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_cliente INTEGER NOT NULL,
	id_empresa INTEGER NOT NULL,
	id_entregador INTEGER NOT NULL,
	id_endereco INTEGER NOT NULL,
	frete DOUBLE,
	estado VARCHAR(100),
	CONSTRAINT fk_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES usuario,
	CONSTRAINT fk_pedido_empresa FOREIGN KEY (id_empresa) REFERENCES usuario,
	CONSTRAINT fk_pedido_entregador FOREIGN KEY (id_entregador) REFERENCES usuario,
	CONSTRAINT fk_pedido_endereco FOREIGN KEY (id_endereco) REFERENCES endereco
);

CREATE TABLE produto(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_empresa INTEGER NOT NULL,
	nome VARCHAR(100),
	categoria VARCHAR(100),
	descricao VARCHAR(100),
	quantidade INTEGER,
	preco DOUBLE,
	CONSTRAINT fk_produto_empresa FOREIGN KEY (id_empresa) REFERENCES usuario
);


CREATE TABLE pedidoProduto(
	id_pedido INTEGER NOT NULL,
	id_produto INTEGER NOT NULL,
	CONSTRAINT fk_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto,
	CONSTRAINT fk_produto_pedido FOREIGN KEY (id_pedido) REFERENCES pedido,
	CONSTRAINT pk_pedido_produto PRIMARY KEY (id_pedido,id_produto)
);

