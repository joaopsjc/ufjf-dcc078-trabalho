
ALTER TABLE pedidoproduto DROP FOREIGN KEY id_promocao;
ALTER TABLE pedidoproduto DROP COLUMN id_promocao;

ALTER TABLE pedidoproduto ADD COLUMN tipoPromocao VARCHAR(100);

