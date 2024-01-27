

CREATE TABLE IF NOT EXISTS video_categoria (id INT NOT NULL AUTO_INCREMENT, titulo VARCHAR(255),  PRIMARY KEY (id));



CREATE TABLE IF NOT EXISTS usuario (
id INT NOT NULL AUTO_INCREMENT,
nome_usuario VARCHAR(255),  
PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS tutorial (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(255), description VARCHAR(255), published BOOLEAN, PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS video (
    id INT NOT NULL AUTO_INCREMENT,
    titulo_video VARCHAR(255),
    descricao VARCHAR(255),
    url VARCHAR(255),
    data_publicacao DATE,
    id_categoria INT, 
    PRIMARY KEY (id),
    FOREIGN KEY (id_categoria) REFERENCES video_categoria(id)
);
