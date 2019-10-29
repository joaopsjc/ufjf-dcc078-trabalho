DROP TABLE promocaoPedidoProduto;

ALTER TABLE pedidoProduto ADD COLUMN id_promocao INTEGER;
ALTER TABLE pedidoProduto ADD FOREIGN KEY (id_promocao) REFERENCES promocao (id);



