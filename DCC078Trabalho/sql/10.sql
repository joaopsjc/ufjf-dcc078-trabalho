CREATE TABLE comboProduto (
        id_combo INTEGER not null,
        id_produto INTEGER not null,
	CONSTRAINT fk_ComboProduto_combo FOREIGN KEY (id_combo) REFERENCES produto,
	CONSTRAINT fk_ComboProduto_produto FOREIGN KEY (id_produto) REFERENCES produto
);


