CREATE TABLE VOTE (
UID_VOTE BIGINT NOT NULL,
VALUE_ENUM VARCHAR(100) NOT NULL,
FK_SCHEDULE_UID BIGINT NOT NULL,
FK_ASSOCIATED_UID BIGINT NOT NULL,
VOTE_TIME TIMESTAMP
);

COMMENT ON TABLE VOTE IS 'Tabela com as informações dos votos.';
COMMENT ON COLUMN VOTE.UID_VOTE IS 'Identificador único da tabela VOTE.';
COMMENT ON COLUMN VOTE.VALUE_ENUM IS 'Campo que guarda o valor do voto.';
COMMENT ON COLUMN VOTE.FK_SCHEDULE_UID IS 'Chave estrangeira referenciando o UID da Sessão de uma pauta em que foi efetuado o voto.';
COMMENT ON COLUMN VOTE.FK_ASSOCIATED_UID IS 'Chave estrangeira referenciando o UID do associado que votou.';
COMMENT ON COLUMN VOTE.VOTE_TIME IS 'Momento do voto.';