
CREATE TABLE promocao(
	id INTEGER not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	tipoPromocao VARCHAR(100)
);
CREATE TABLE promocaoPedidoProduto(
	id_pedido INTEGER not null,
	id_produto INTEGER not null,
	id_promocao INTEGER not null,
	CONSTRAINT fk_promocaoPedidoProduto_pedidoProduto FOREIGN KEY (id_pedido,id_produto) REFERENCES pedidoProduto
	CONSTRAINT fk_promocaoPedidoProduto_promocao FOREIGN KEY (id_promocao) REFERENCES promocao
);
