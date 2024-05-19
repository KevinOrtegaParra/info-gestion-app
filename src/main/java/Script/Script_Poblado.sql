INSERT INTO TipoRelacion (tipo) 
VALUES('Padre'), 
('Madre'), 
('Hijo/a'), 
('Esposo/a'), 
('Abuelo/a'), 
('Tío/a');

INSERT INTO TipoDocumento (nombre) 
VALUES('Cédula de Ciudadanía'), 
('Tarjeta de Identidad'), 
('Cédula de Extranjería'), 
('Pasaporte'), 
('Licencia de Conducción'), 
('Identificación Milita'),
('Tarjeta Profesional'),
('Tarjeta de Seguro Social');

INSERT INTO EstadoCivil (nombre) 
VALUES('Soltero/a'), 
('Casado/a'), 
('Viudo/a'), 
('Divorciado/a'), 
('Unión Civil/Pareja de Hecho'), 
('Separado/a');

INSERT INTO Universidad (nombre) 
VALUES('Universidad Nacional'), 
('Universidad de Antioquia'), 
('Universidad EAFIT');

INSERT INTO NivelEstudio (nombre) VALUES
('Pregrado'),
('Posgrado'),
('Maestría'),
('Doctorado');

INSERT INTO funcionarios
(id_tipo_identificasion, num_identificasion, nombre, apellido, id_estado_civil, sexo, direccion, telefono, fecha_nacimiento)
values
(1, '1100752197', 'Kevin Anonio', 'Ortega Parra', 1, 'M', 'car 9 #9-09', '3046673191', '2003-10-02'),
(4, '4561237', 'david', 'perez', 3, 'M', 'car 13 #4-30', '25640', '2000-09-05'),
(2, '11005796345', 'Juliana', 'Ortega', 1, 'F', 'car 16 #10-05', '3024694835', '2005-10-31');

INSERT INTO GrupoFamiliar (id_funcionario, nombre, apellido, id_relacion, direccion) 
VALUES
(1, 'Luis', 'Pérez', 3, 'Calle 123'),
(3, 'Ana', 'Pérez', 4, 'Calle 123'),
(2, 'Pedro', 'Gómez', 1, 'Av. Principal');

INSERT INTO FormacionAcademica (id_funcionario, titulo, id_institucion, id_nivelEstudio)
VALUES
(1, 'Ingeniero de Sistemas', 2, 1),
(2, 'Máster en Administración', 1, 3),
(3, 'Licenciatura en Educación', 3, 2);


