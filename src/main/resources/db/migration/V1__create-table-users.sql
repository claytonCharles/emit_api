SET GLOBAL time_zone = '-03:00';

CREATE TABLE IF NOT EXISTS tb_users_roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL,
    register_date DATETIME NOT NULL DEFAULT NOW()
);

INSERT INTO tb_users_roles (name) VALUES ("ADMIN");
INSERT INTO tb_users_roles (name) VALUES ("MANAGER");
INSERT INTO tb_users_roles (name) VALUES ("VIEWER");

CREATE TABLE IF NOT EXISTS tb_users (
    id VARCHAR(64) PRIMARY KEY UNIQUE NOT NULL DEFAULT (uuid()),
    mail VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    password_temp BOOLEAN DEFAULT false,
    name VARCHAR(50) NOT NULL,
    birthday DATETIME,
    photo VARCHAR(255),
    role_id INT NOT NULL DEFAULT 3,
    role_expiration_date DATETIME,
    register_date DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES tb_users_roles(id)
);

CREATE TABLE IF NOT EXISTS rtb_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(64) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    birthday DATETIME,
    photo VARCHAR(255),
    role_id INT NOT NULL,
    role_expiration_date DATETIME,
    register_date DATETIME NOT NULL,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES tb_users(id),
    CONSTRAINT RFK_role_id FOREIGN KEY (role_id) REFERENCES tb_users_roles(id)
);

CREATE TRIGGER IF NOT EXISTS record_tb_user AFTER UPDATE ON tb_users FOR EACH ROW INSERT INTO rtb_users (
    user_id, 
    mail, 
    password, 
    name, 
    birthday, 
    photo, 
    role_id, 
    role_expiration_date, 
    register_date
) VALUES (
    OLD.id, 
    OLD.mail, 
    OLD.password,
    OLD.name, 
    OLD.photo, 
    OLD.birthday, 
    OLD.role_id, 
    OLD.role_expiration_date, 
    OLD.register_date
);