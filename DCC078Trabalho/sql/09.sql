


ALTER TABLE pedido DROP COLUMN id_cliente;
ALTER TABLE pedido DROP COLUMN id_empresa;
ALTER TABLE pedido DROP COLUMN id_entregador;
ALTER TABLE pedido DROP COLUMN id_endereco;


ALTER TABLE pedido ADD COLUMN id_cliente INTEGER;
ALTER TABLE pedido ADD COLUMN id_empresa INTEGER;
ALTER TABLE pedido ADD COLUMN id_entregador INTEGER;
ALTER TABLE pedido ADD COLUMN id_endereco INTEGER;


