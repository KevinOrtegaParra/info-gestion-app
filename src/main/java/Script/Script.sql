
CREATE TABLE funcionarios (
	id serial NOT NULL,
	tipo_identificasion varchar(50) NULL,
	num_identificasion varchar(20) NULL,
	nombre varchar(100) NULL,
	apellido varchar(100) NULL,
	estado_civil varchar(20) NULL,
	sexo bpchar(1) NULL,
	direccion varchar(200) NULL,
	telefono varchar(15) NULL,
	fecha_nacimiento date NULL
);

CREATE TABLE GrupoFamiliar (
    id serial NOT NULL,
    id_funcionario INT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT null,
    id_relacion INT,
    direccion varchar(200) null,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) ON DELETE cascade,
    FOREIGN KEY (id_relacion) REFERENCES TipoRelacion(id) ON DELETE cascade
);

CREATE TABLE TipoRelacion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE FormacionAcademica (
    id SERIAL PRIMARY KEY,
    id_funcionario INT,
    titulo VARCHAR(100) NOT NULL,
    id_institucion INT,
    nivelEstudio VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) ON DELETE cascade,
    FOREIGN KEY (id_institucion) REFERENCES Universidad(id) ON DELETE CASCADE
);

CREATE TABLE Universidad (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);




INSERT INTO funcionarios
(id, tipo_identificasion, num_identificasion, nombre, apellido, estado_civil, sexo, direccion, telefono, fecha_nacimiento)
values
('Cédula de Ciudadanía', '1100752197', 'Kevin Anonio', 'Ortega Parra', 'Soltero/a', 'M', 'car 9 #9-09', '3046673191', '2003-10-02'),
('Pasaporte', '4561237', 'david', 'perez', 'Viudo/a', 'M', 'car 13 #4-30', '25640', '2000-09-05'),
('Tarjeta de Identidad', '11005796345', 'Juliana', 'Ortega', 'Soltero/a', 'F', 'car 16 #10-05', '3024694835', '2005-10-31');

INSERT INTO TipoRelacion (tipo) 
VALUES('Padre'), ('Madre'), ('Hijo/a'), ('Esposo/a'), ('Abuelo/a'), ('Tío/a');

INSERT INTO Universidad (nombre) 
VALUES('Universidad Nacional'), ('Universidad de Antioquia'), ('Universidad EAFIT');

INSERT INTO GrupoFamiliar (id_funcionario, nombre, apellido, id_relacion, direccion) 
VALUES
(4, 'Luis', 'Pérez', 3, 'Calle 123'),
(6, 'Ana', 'Pérez', 4, 'Calle 123'),
(5, 'Pedro', 'Gómez', 1, 'Av. Principal');

INSERT INTO FormacionAcademica (id_funcionario, titulo, id_institucion, nivelEstudio)
VALUES
(4, 'Ingeniero de Sistemas', 2, 'Pregrado'),
(5, 'Máster en Administración', 1, 'Posgrado'),
(6, 'Licenciatura en Educación', 3, 'Pregrado');
