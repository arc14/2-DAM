DROP DATABASE IF EXISTS liga;
CREATE DATABASE liga CHARACTER SET utf8mb4;
USE liga;

-- TABLAS
CREATE TABLE EQUIPO(
	CodEquipo VARCHAR(4) PRIMARY KEY,
	Nombre varchar(30) NOT NULL,
	Localidad VARCHAR(15)
);

CREATE TABLE JUGADOR (
	CodJugador VARCHAR(4) PRIMARY KEY,
	Nombre varchar(30) NOT NULL,
	Fecha_nacimiento date,
	Demarcacion VARCHAR(10) CHECK (Demarcacion IN ('Portero','Defensa','Delantero','Medio')),
	CodEquipo VARCHAR(4) not null,
FOREIGN KEY (CodEquipo) REFERENCES EQUIPO(CodEquipo)
);

CREATE TABLE PARTIDO(
	CodPartido VARCHAR(4) PRIMARY KEY,
CodEquipoLocal VARCHAR(4),
CodEquipoVisitante VARCHAR(4),
	Fecha Date,
	Jornada VARCHAR(20),
FOREIGN KEY (CodEquipoLocal) REFERENCES EQUIPO(CodEquipo),
FOREIGN KEY (CodEquipoVisitante) REFERENCES EQUIPO(CodEquipo),
CONSTRAINT chk_fecha CHECK (MONTH(Fecha) IN (1,2,3,4,5,6,9,10, 11,12))
);

CREATE TABLE INCIDENCIA(
	NumIncidencia VARCHAR(6) PRIMARY KEY,
	CodPartido VARCHAR(4),
	CodJugador VARCHAR(4),
    Minuto Integer(2),
    Tipo VARCHAR(20) CHECK (Tipo IN ('Gol','Tarjeta_amarilla','Tarjeta_roja')),
FOREIGN KEY (CodPartido) REFERENCES PARTIDO(CodPartido),
FOREIGN KEY (CodJugador) REFERENCES JUGADOR(CodJugador),
CONSTRAINT chk_minuto CHECK (Minuto > 0 and Minuto <=99)
);



DELIMITER $$

CREATE PROCEDURE ResultadoPartido(codLocal VARCHAR(4), codVisitante VARCHAR(4))
   COMMENT 'Mostrar resultado partido'
BEGIN
   DECLARE partidos int;

   -- 1. Obtener el número de pedidos por cliente
   SELECT *
   INTO partidos
   FROM partido
   WHERE CodEquipoLocal = codLocal AND CodEquipoVisitante = codVisitante;

END$$

DELIMITER ;