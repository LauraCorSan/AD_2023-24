--TEMA 2 PRACTICA 1
/*1. Crear un tipo Dirección con los atributos calle varchar(25), ciudad varchar(20), y código_post number(5).*/
CREATE OR REPLACE TYPE Direccion AS OBJECT (
    calle VARCHAR2(25),
    ciudad VARCHAR2(20),
    codigo_postal NUMBER(5)
);
/
/*2. Crear un tipo Persona con los atributos: Código number, nombre varchar(35), direc de tipo Direccion 
y fecha_nac date.*/

CREATE OR REPLACE TYPE Persona AS OBJECT (
    Codigo NUMBER,
    Nombre VARCHAR2(35),
    Direc Direccion,
    Fecha_Nac DATE
);
/
/*3. Inicializar los objetos declarados en un bloque PL como este:*/
DECLARE
DIR DIRECCION := DIRECCION(NULL, NULL, NULL);
P PERSONA := PERSONA(NULL, NULL, NULL, NULL);
BEGIN
DIR.CALLE := 'La Mina, 3';
DIR.CIUDAD := 'Guadalajara';
DIR.CODIGO_POSTAL := 19001;
P.CODIGO := 1;
P.NOMBRE := 'Elena';
P.DIREC := DIR;
P.FECHA_NAC := TO_DATE('29-OCT-02');

--insert into alumnos values(P);
END;
/

/*4. Crear una tabla alumnos de tipo persona, y en ella:*/
CREATE TABLE Alumnos OF Persona;

--a. Insertar dos alumnos.
INSERT INTO Alumnos VALUES (
    Persona(1, 'Jorge', Direccion('Calle Sal , 14', 'Guadalajara', 12345), TO_DATE('15-01-95'))
);

INSERT INTO Alumnos VALUES (
    Persona(2, 'Maria', Direccion('Calle Talco, 3', 'Villaverde', 28021), TO_DATE('31-03-98'))
);

--b. Seleccionar las filas cuya ciudad sea Guadalajara.
SELECT * FROM Alumnos al WHERE al.Direc.ciudad in ('Guadalajara');

--c. Seleccionar código, Direc(columna de tipo objeto) de los alumnos
SELECT Codigo, Direc FROM Alumnos;

--d. Modificar las filas cuya ciudad sea Guadalajara y convertir la ciudad a minúscula.
UPDATE Alumnos al SET al.Direc.Ciudad = LOWER(al.direc.Ciudad) WHERE al.Direc.Ciudad = 'Guadalajara';

--e. Crear un bloque PL que muestre el nombre y la calle de los alumnos.
DECLARE
    alumno_rec Alumnos%ROWTYPE;
BEGIN
    FOR alumno_rec IN (SELECT * FROM Alumnos) LOOP
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || alumno_rec.Nombre || ', Calle: ' || alumno_rec.Direc.Calle);
    END LOOP;
END;
/

--f. Eliminar aquellas filas cuya ciudad sea Guadalajara.
DELETE FROM Alumnos al WHERE al.Direc.ciudad = 'guadalajara';
