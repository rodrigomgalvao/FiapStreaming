-- DROP TABLE favoritacao_video;
-- DROP TABLE visualizacao_video;
-- DROP TABLE video;
-- DROP TABLE video_categoria;
-- DROP TABLE usuario;

CREATE TABLE IF NOT EXISTS video_categoria
(
    id     UUID DEFAULT RANDOM_UUID(),
    titulo VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario
(
    id           UUID DEFAULT RANDOM_UUID(),
    nome_usuario VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS video
(
    id              UUID DEFAULT RANDOM_UUID(),
    titulo_video    VARCHAR(255),
    descricao       VARCHAR(255),
    url             VARCHAR(255),
    data_publicacao DATE,
    id_categoria    UUID,
    PRIMARY KEY (id),
    FOREIGN KEY (id_categoria) REFERENCES video_categoria (id)
);

CREATE TABLE IF NOT EXISTS visualizacao_video
(
    id         UUID DEFAULT RANDOM_UUID(),
    data_visualizacao DATE,
    id_usuario UUID,
    id_video   UUID,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    FOREIGN KEY (id_video) REFERENCES video (id)
);

CREATE TABLE IF NOT EXISTS favoritacao_video
(
    id              UUID DEFAULT RANDOM_UUID(),
    data_favoritacao DATE,
    id_usuario      UUID,
    id_video        UUID,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    FOREIGN KEY (id_video) REFERENCES video (id)
);