--AD-Tema 2 Practica 11

--Se desea gestionar un supermercado y construir el modelo para la gestión de las listas de la compra.
/*1. Crea un tipo para almacenar direcciones postales con dos campos, la dirección y el código postal, llamado 
Tipo_direccion.*/ 

CREATE TYPE Tipo_direccion AS OBJECT (
    direccion VARCHAR2(100),
    codigo_postal VARCHAR2(10)
);
/

--2. Crea un tipo contacto (tipo_contacto)para almacenar un número de teléfono y un email.

CREATE TYPE Tipo_contacto AS OBJECT (
    numero_telefono VARCHAR2(15),
    correo_electronico VARCHAR2(100)
);
/

/*3. Crea un tipo persona (tipo_persona) con los campos id, nombre, apellido, dirección y contacto.  Después crea un
subtipo cliente (tipo_cliente) con otro campo adicional llamado número de pedidos.*/

CREATE TYPE Tipo_persona AS OBJECT (
    id NUMBER,
    nombre VARCHAR2(50),
    apellido VARCHAR2(50),
    direccion Tipo_direccion,  -- Utilizamos el tipo creado anteriormente
    contacto Tipo_contacto     -- Utilizamos el tipo creado anteriormente
)NOT FINAL;

CREATE TYPE Tipo_cliente UNDER Tipo_persona (
    numero_pedidos NUMBER
);

--4. Describe el tipo_direccion, el tipo_contacto , el tipo persona y el tipo cliente.
 
SELECT * 
FROM USER_TYPE_ATTRS
WHERE TYPE_NAME = 'TIPO_DIRECCION';
--------------------------------------------
SELECT * 
FROM USER_TYPE_ATTRS
WHERE TYPE_NAME = 'TIPO_CONTACTO';
--------------------------------------------
SELECT * 
FROM USER_TYPE_ATTRS
WHERE TYPE_NAME = 'TIPO_PERSONA';
--------------------------------------------
SELECT * 
FROM USER_TYPE_ATTRS
WHERE TYPE_NAME = 'TIPO_CLIENTE';


/*5. Crea un tipo artículo (tipo_articulo)con los campos id, nombre, descripción, precio y porcentaje de iva. Después 
crea un tipo tabla anidada  (tabla_articulos)  as table of tipo_articulo.*/
 
-- Crear type Tipo_articulo
CREATE TYPE Tipo_articulo AS OBJECT (
    id NUMBER,
    nombre VARCHAR2(100),
    descripcion VARCHAR2(255),
    precio NUMBER,
    porcentaje_iva NUMBER
);

-- Crear tabla anidada Tabla_articulos
CREATE TYPE Tabla_articulos AS TABLE OF Tipo_articulo;


--6. Describe el tipo articulo.
SELECT * 
FROM USER_TYPE_ATTRS
WHERE TYPE_NAME = 'TIPO_ARTICULO';


/*7. Crea un tipo para la lista de la compra(tipo_lista_compra)   y otro para su detalle (tipo_lista_detalle). El 
tipo_lista_detalle contendrá un numero number, artículo de tipo_articulo y la cantidad number. El tipo_lista_compra 
contendrá un identificador, fecha, cliente (será una referencia al tipo_cliente)  y un atributo llamado detalle que 
será una tabla anidada de tipo_lista_detalle.   (crea previamente el tipo  tabla anidada llámalo 
tab_lista_detalle as table of tipo_lista_detalle). 
Se deberá incluir en la definición una función miembro para calcular el total de la lista de la compra.*/ 

-- Crear el tipo Tipo_lista_detalle
CREATE TYPE Tipo_lista_detalle AS OBJECT (
    numero NUMBER,
    articulo Tipo_articulo,
    cantidad NUMBER
);

-- Crear el tipo Tab_lista_detalle como tabla anidada de Tipo_lista_detalle
CREATE TYPE Tab_lista_detalle AS TABLE OF Tipo_lista_detalle;

-- Crear el tipo Tipo_lista_compra
CREATE TYPE Tipo_lista_compra AS OBJECT (
    identificador NUMBER,
    fecha DATE,
    cliente REF Tipo_cliente ,
    detalle Tab_lista_detalle,

    -- Función miembro para calcular el total de la lista de la compra
    MEMBER FUNCTION calcular_total RETURN NUMBER
);

-- Implementar la función miembro para calcular el total
CREATE TYPE BODY Tipo_lista_compra AS
    MEMBER FUNCTION calcular_total RETURN NUMBER IS
        total NUMBER := 0;
    BEGIN
        FOR i IN 1..self.detalle.COUNT LOOP
            total := total + (self.detalle(i).articulo.precio * self.detalle(i).cantidad);
        END LOOP;
        RETURN total;
    END calcular_total;
END;


--8. Crea el body del tipo lista de la compra para definir el método total.
 
CREATE OR REPLACE TYPE BODY Tipo_lista_compra AS
    MEMBER FUNCTION calcular_total RETURN NUMBER IS
        total NUMBER := 0;
    BEGIN
        FOR i IN 1..self.detalle.COUNT LOOP
            total := total + (self.detalle(i).articulo.precio * self.detalle(i).cantidad);
        END LOOP;
        RETURN total;
    END calcular_total;
END;
/


--9. Crea una tabla clientes e inserta dos clientes con número pedidos a 0.  

CREATE TABLE clientes (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(50),
    apellido VARCHAR2(50),
    direccion Tipo_direccion,  -- Utiliza el tipo Tipo_direccion definido anteriormente
    contacto Tipo_contacto,    -- Utiliza el tipo Tipo_contacto definido anteriormente
    numero_pedidos NUMBER
);


INSERT INTO clientes VALUES (1, 'Jose', 'Perez', Tipo_direccion('Direccion1', 'CP1'), Tipo_contacto('111-111-1111', 'cliente1@example.com'), 0);

INSERT INTO clientes VALUES (2, 'Carla', 'Alonso', Tipo_direccion('Direccion2', 'CP2'), Tipo_contacto('222-222-2222', 'cliente2@example.com'), 0);


/*10. Crea la tabla para las listas de la compra e inserta una lista de la compra con un detalle de dos artículos para
el cliente id =1.*/

CREATE TABLE listas_compra (
    identificador NUMBER PRIMARY KEY,
    fecha DATE,
    cliente_id NUMBER,
    detalle Tab_lista_detalle
)
NESTED TABLE detalle STORE AS detalle_tab; -- Añade esta línea para especificar la cláusula de almacenamiento

-- Insertar una lista de compra para el cliente con id = 1
INSERT INTO listas_compra (identificador, fecha, cliente_id, detalle)
VALUES
    (1, SYSDATE, 1, Tab_lista_detalle(
                            tipo_lista_detalle(1, tipo_articulo(101, 'Articulo1','Descripcion1', 10.5, 5),3),
                            tipo_lista_detalle(2, tipo_articulo(102, 'Articulo2','Descripcion2', 20.0, 8),2)
                            )
    );
/

--11. Muestra con una select los datos de la lista de la compra.

SELECT * FROM listas_compra;

--12. Construye una select para mostrar por pantalla el id de una lista de la compra y su total.

SELECT lc.identificador AS id_lista_compra
       lc.calcular_total() AS total_lista_compra
FROM listas_compra lc


