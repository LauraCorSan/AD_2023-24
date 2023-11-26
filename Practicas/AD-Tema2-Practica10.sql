--Tema 2 Practica 10

/*Supongamos que queremos almacenar la información de clientes, y que hay clientes que tienen 3 móviles (el móvil de 
su trabajo, su móvil particular y otro móvil de otra compañía que le ofrece buenos descuentos de sms).

Id_cli	Nombre	    Apellido	Dirección	Población	Provincia	teléfono	Móvil
1	    Francisco	pérez	    sol	        madrid	    madrid	    91345655	6564433
En el modelo E/R la solución óptima sería crear una relación que sea cliente-movil y que contenga el id_cliente y su 
número de móvil.

Id_cli	móvil
1	    654555555
1	    666543211
1	    699767676

Las BBDD orientadas a objetos brindan la posibilidad de crear un tipo de objeto.*/

/*1. Crear un tipo objeto llamado telefono con dos atributos
	Tipo varchar(30)
	Numero number*/ 
    
CREATE OR REPLACE TYPE telefono AS OBJECT (
    Tipo VARCHAR2(30),
    Numero NUMBER
);    
/

--2. Crear una tabla tipo llamada listin basada en el tipo objeto, para añadir la funcionalidad de múltiples valores.
 
CREATE OR REPLACE TYPE telefono_nt AS TABLE OF telefono;

CREATE TABLE listin (
    id NUMBER,
    telefonos telefono_nt 
) NESTED TABLE telefonos STORE AS telefonos_tab;


/*3. Crear la tabla clientes  con los campos
Id_cli number
		Nombre varchar(30)
		Apellido varchar(30)
		Dirección  varchar(30)
		Población varchar(30)
		Provincia varchar(30)
        Telefonos
Siendo teléfonos de tipo listin, añade la tabla anidada teléfonos que se almacena como tel_tab.*/

CREATE TABLE clientes (
    Id_cli NUMBER,
    Nombre VARCHAR2(30),
    Apellido VARCHAR2(30),
    Dirección VARCHAR2(30),
    Población VARCHAR2(30),
    Provincia VARCHAR2(30),
    Telefonos telefono_nt,
    CONSTRAINT clientes_pk PRIMARY KEY (Id_cli)
) NESTED TABLE Telefonos STORE AS tel_tab;
/

--4. Inserta 3 registros, con 3 teléfonos cada uno. 

DECLARE
    v_telefonos telefono_nt := telefono_nt(); -- Declaramos una variable del tipo de tabla anidada
    
BEGIN
    -- Primer cliente con 3 teléfonos
    v_telefonos.EXTEND(3); -- Extiende la tabla anidada para almacenar 3 teléfonos
    v_telefonos(1) := telefono('fijo', 923456789);
    v_telefonos(2) := telefono('movil personal', 682454001);
    v_telefonos(3) := telefono('movil empresa', 621276303);
    
    INSERT INTO clientes (Id_cli, Nombre, Apellido, Dirección, Población, Provincia, Telefonos)
    VALUES (1, 'Jorge', 'Sanchez', 'Calle de la piruleta, 6', 'Madrid', 'Madrid', v_telefonos);
    
    -- Segundo cliente con 3 teléfonos
    v_telefonos.DELETE; -- Borra los datos previos de la tabla anidada
    v_telefonos.EXTEND(3);
    v_telefonos(1) := telefono('fijo', 948551266);
    v_telefonos(2) := telefono('personal', 677819929);
    v_telefonos(3) := telefono('empresa', 622453644);
    
    INSERT INTO clientes (Id_cli, Nombre, Apellido, Dirección, Población, Provincia, Telefonos)
    VALUES (2, 'Antonio', 'Perez', 'Calle sal si puedes, 0', 'Madrid', 'Madrid', v_telefonos);
    
    -- Tercer cliente con 3 teléfonos
    v_telefonos.DELETE;
    v_telefonos.EXTEND(3);
    v_telefonos(1) := telefono('fijo', 950113322);
    v_telefonos(2) := telefono('personal', 633444505);
    v_telefonos(3) := telefono('empresa', 699220111);
    
    INSERT INTO clientes (Id_cli, Nombre, Apellido, Dirección, Población, Provincia, Telefonos)
    VALUES (3, 'Marta', 'Alonso', 'Calle del arroyo, 23 ', 'Madrid', 'Madrid', v_telefonos);
    
    --COMMIT;
END;
/

--5. Selecciona todos los clientes. 

SELECT 
    c.Id_cli,
    c.Nombre,
    c.Apellido,
    c.Dirección,
    c.Población,
    c.Provincia,
    tel.Tipo,
    tel.Numero
FROM clientes c, TABLE(c.Telefonos) tel;
/

--6. Consulta las estructuras de almacenamiento que usa oracle para almacenar los objetos. 

SELECT segment_name, segment_type, tablespace_name
FROM user_segments
WHERE segment_name IN ('CLIENTES', 'TELEFONOS');
/

--7. Consulta los objetos de la base de datos.

SELECT object_name, object_type, status
FROM user_objects
WHERE object_name IN ('CLIENTES', 'TELEFONOS');
/

--8. Lista la vista user_nested_tables. 

SELECT * FROM USER_NESTED_TABLES;

--9. Lista todos los teléfonos del cliente 3, usando el operador TABLE. 

SELECT t.Tipo, t.Numero
FROM clientes c,
TABLE (SELECT c.Telefonos FROM clientes c WHERE c.Id_cli = 3) t;

/*10. Actualiza la tabla clientes cambiando los números de teléfono del cliente 1 por : 
'fijo',934444444  
'movil personal',65555555 
'movil empresa',644444444*/ 

UPDATE clientes
SET Telefonos = telefono_nt(
    telefono('fijo', 934444444),
    telefono('movil personal', 65555555),
    telefono('movil empresa', 644444444)
)
WHERE Id_cli = 1;
/

--11. Visualizar todos los teléfonos de todos los clientes.

SELECT c.Id_cli, t.Tipo, t.Numero
FROM clientes c,
TABLE(c.Telefonos) t
ORDER BY c.Id_cli;
/

--12. Visualizar el nombre, id , tipo de teléfono, número de teléfono de todos los teléfonos de todos los clientes

SELECT c.Nombre, c.Id_cli, t.Tipo, t.Numero
FROM clientes c
JOIN TABLE(c.Telefonos) t ON 1=1
ORDER BY c.Id_cli, t.Tipo;
/
