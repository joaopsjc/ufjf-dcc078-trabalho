

CREATE TABLE notificacao(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	id_cliente INTEGER not null,
	mensagem VARCHAR(150),
	dataHoraNotificacao VARCHAR(30),
	lida SMALLINT DEFAULT 0 NOT NULL,	
	CONSTRAINT fk_notificacao_usuario FOREIGN KEY (id_cliente) REFERENCES usuario
);

