
CREATE TABLE TipoDocumento (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE EstadoCivil (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE funcionarios (
	id serial PRIMARY KEY,
	id_tipo_identificasion INT,
	num_identificasion varchar(20) NULL,
	nombre varchar(100) NULL,
	apellido varchar(100) NULL,
	id_estado_civil INT,
	sexo bpchar(1) NULL,
	direccion varchar(200) NULL,
	telefono varchar(15) NULL,
	fecha_nacimiento date null,
	FOREIGN KEY (id_tipo_identificasion) REFERENCES TipoDocumento(id) ON DELETE cascade,
    FOREIGN KEY (id_estado_civil) REFERENCES EstadoCivil(id) ON DELETE cascade
);

CREATE TABLE TipoRelacion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE GrupoFamiliar (
    id serial PRIMARY KEY,
    id_funcionario INT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT null,
    id_relacion INT,
    direccion varchar(200) null,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) ON DELETE cascade,
    FOREIGN KEY (id_relacion) REFERENCES TipoRelacion(id) ON DELETE cascade
);

CREATE TABLE NivelEstudio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Universidad (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE FormacionAcademica (
    id SERIAL PRIMARY KEY,
    id_funcionario INT,
    titulo VARCHAR(100) NOT NULL,
    id_institucion INT,
    id_nivelEstudio INT,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) ON DELETE cascade,
    FOREIGN KEY (id_institucion) REFERENCES Universidad(id) ON DELETE cascade,
    FOREIGN KEY (id_nivelEstudio) REFERENCES NivelEstudio(id) ON DELETE cascade
);
