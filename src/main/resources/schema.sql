

CREATE TABLE IF NOT EXISTS video_categoria (id INT NOT NULL AUTO_INCREMENT, titulo VARCHAR(255),  PRIMARY KEY (id));

drop table usuario;

CREATE TABLE IF NOT EXISTS usuario (id INT NOT NULL AUTO_INCREMENT, nome_usuario VARCHAR(255),  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS tutorial (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(255), description VARCHAR(255), published BOOLEAN, PRIMARY KEY (id));
