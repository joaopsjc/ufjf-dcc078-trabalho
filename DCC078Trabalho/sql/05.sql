

ALTER TABLE produto ADD COLUMN estado VARCHAR(100);

UPDATE produto SET estado='Disponivel';

