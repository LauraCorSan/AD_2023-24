-- Tema 2 Practica 9

/*1. Se desea guardar los nombres de los hijos de empleados, en vez de una colección que nos limita la cantidad de hijos, 
vamos a usar un tipo tabla, usando el ejemplo de la práctica 7 redefine el tipo hijos con un tipo tabla llamado 
tabla_hijos de tipo varchar(30).*/

CREATE OR REPLACE TYPE tabla_hijos AS TABLE OF VARCHAR2(30);
/

/*2. Crea la tabla empleado basándola en el tipo tabla_hijos
Idemp  number
Nombre  varchar(30)
Apellidos varchar(30)
Hijos de tipo tabla_hijos
Nested table hijos store as t_hijos*/

CREATE TABLE empleado (
  Idemp NUMBER,
  Nombre VARCHAR2(30),
  Apellidos VARCHAR2(30),
  Hijos tabla_hijos,
  CONSTRAINT pk_empleado PRIMARY KEY (Idemp)
) NESTED TABLE Hijos STORE AS t_hijos;
/

--3. La columna hijos es tipo tabla_hijos almacenada sobre un tipo de segmento especial llamado tabla anidada.
--a. Consulta los objetos de la base de datos con SELECT.

SELECT object_name, object_type, status
FROM user_objects 
WHERE object_name like '%HIJO%';
/

--b. Consulta las estructuras de almacenamiento que usa Oracle para almacenar los objetos con SELECT. 

SELECT segment_name, segment_type 
FROM user_segments 
WHERE segment_name like '%HIJO%';
/

/*4. Inserta dos empleados con INSERT con estos datos:
Id	nombre	apellidos	hijos
1	Fernando 	Moreno	(Elena,Pablo)
2	David	Sanchez	(Carmen,Candela)*/

INSERT INTO empleado (idemp, nombre, apellidos, hijos) 
VALUES (
    1,
    'Fernando',
    'Moreno',
    tabla_hijos('Elena','Pablo')
);

INSERT INTO empleado (idemp, nombre, apellidos, hijos) 
VALUES (
    2,
    'David',
    'Sanchez',
    tabla_hijos('Carmen','Candela')
);


--5. Lista todos los empleados.

SELECT *
FROM empleado;
/

--6. Lista todos los hijos del empleado 1, usando TABLE.

SELECT *
FROM TABLE (
    SELECT hijos
    FROM empleado
    WHERE idemp = 1
);    
/

--7. Actualiza la tabla empleado cambiando el nombre de los hijos del empleado idemp 1 por Carmen, Candela, Cayetana.

UPDATE empleado
SET hijos = CAST(MULTISET(
        SELECT COLUMN_VALUE FROM TABLE(tabla_hijos('Carmen', 'Candela', 'Cayetana'))
    ) AS tabla_hijos)
WHERE idemp = 1;


--8. Listar todos los hijos del empleado 1 y 
SELECT idemp, COLUMN_VALUE AS hijo
FROM empleado, TABLE(empleado.hijos)
WHERE idemp IN (1, 2);
