/*1. Realizar un programa que solicite un numero de empleado determinado, el programa debe comprobar si
 el salario es mayor que 1000 y si no es asi actualizar el salario con una subida del 20%, y en una tabla 
 temporal insertar el nombre, el código del empleado y el mensaje "Salario actualizado". Si el sueldo ya 
 superaba los 1000 se inserta en la tabla temporal  el código del empleado y  el mensaje "Salario no actualizado".*/
drop table temp;
create global temporary table temp(nombre varchar2(50),codigo number(4), mensaje varchar2(50));

DECLARE
    v_codigo employee.employee_id %type;
    v_salario employee.salary %type;
    v_nombre employee.first_name %type;
BEGIN
    select first_name, salary into v_nombre, v_salario from employee where employee_id=&v_codigo;
    if v_salario<1000 then
        v_salario := v_salario+(v_salario*0.1);
        update employee set salary = v_salario where employee_id=v_codigo;
        insert into temp values (v_nombre ,v_codigo, 'Salario Actualizado');
    else
        insert into temp values (v_nombre, v_codigo, 'Salario NO Actualizado');
    end if;
END;    
/   
      
/*2. Hacer un programa que solicite por teclado un codigo de cliente e inserte en la tabla temporal el numero
total de pedidos de ese cliente, el precio total de esos pedidos, el codigo del cliente y el nombre del cliente*/
drop table temp;
create global temporary table temp(nombre varchar2(50),codigo number(4), num_pedidos number(4), precio_total number(8));

DECLARE
    v_codigo sales_order.customer_id%type;
    v_nombre customer.name%type;
    v_num_pedidos number(4);
    v_total sales_order.total%type;
BEGIN
    v_codigo := &ID_customer;
    select count(*), sum(total) into v_num_pedidos, v_total from sales_order where customer_id = v_codigo;
    select name into v_nombre from customer where customer_id = v_codigo;
    insert into temp values (v_nombre, v_codigo, v_num_pedidos, v_total);
END;    
/

/*3. Crearse un registro t_reg_emple con la siguiente estructura: codigo del empleado, nombre y el job.
Insertar en una tabla temporal el nombre del empleado y job para el empleado 7782*/ 
drop table temp;
create table temp(codigo number(4),nombre varchar2(50),trabajo varchar2(50));

DECLARE
TYPE t_reg_emple is record (
        cod_empleado number(4),
        nombre varchar2(50),
        trabajo varchar2(80));
        
v_empleado t_reg_emple;
BEGIN
    select employee_id, first_name, function into v_empleado from employee 
    join job on employee.job_id=job.job_id and employee_id = 7782; 
    insert into temp values v_empleado;
END;    
/

/*4. Insertar 50 filas en una tabla. Recorre todas las filas de la tabla y las inserta en una tabla temporal. 
Crear la tabla students e insertar algunos datos.*/
drop table students;
create table students(NIA number(4) primary key, nombre varchar2(50),apellido varchar2(50));

insert into students values (111, 'Julia', 'Sanchez');
insert into students values (112, 'Luis', 'Navarro');
insert into students values (113, 'Antonio', 'Lozano');
/

/*5. Llenar una tabla PL con los nombres de los clientes y cargarla en una tabla temporal de sql que contenga 
el índice de la tabla PL y el nombre del cliente, y visualizar ésta.*/
drop table temp;
create table tabla_pl (
    id_cliente number(4),
    nombre_cliente varchar2(50)
);

BEGIN
    insert into tabla_pl (id_cliente, nombre_cliente)
    SELECT customer_id, name FROM customer;
END;    

create global temporary table temp (indice number(4), nombre_cliente varchar2(50)); 

insert into temp (indice, nombre_cliente)
select id_cliente, nombre_cliente from tabla_pl;

select * from temp;

/*6. Llenar una tabla temporal con los códigos de clientes, nombre del cliente y código de empleado de tres 
formas posibles*/
/*a.	usando bucle LOOP*/
drop table temp;

create global temporary table temp (
        cliente_id number(4),
        nombre_cliente varchar2(50),
        codigo_empleado number(4)
    );

DECLARE
    v_cliente_id customer.customer_id%type;
    v_nombre_cliente customer.name%type;
    v_codigo_empleado customer.salesperson_id%type;
BEGIN
    
    FOR c IN (select customer_id, name, salesperson_id from customer) LOOP
        insert into temp values (c.cliente_id, c.nombre_cliente, c.codigo_empleado);
    END LOOP;
    
    FOR row_data IN (select * from temp) LOOP
        DBMS_OUTPUT.PUT_LINE('Cliente ID: ' || row_data.cliente_id || ', Nombre: ' || row_data.nombre_cliente || ', Código Empleado: ' || row_data.codigo_empleado);
    END LOOP;
END;
/

/*b.	usando bucle WHILE*/
drop table temp;

    create global temporary table temp (
        cliente_id number(4),
        nombre_cliente varchar2(50),
        codigo_empleado number(4)
    );

DECLARE
    v_cliente_id customer.customer_id%type;
    v_nombre_cliente customer.name%type;
    v_codigo_empleado customer.salesperson_id%type;
    v_counter number := 1;
BEGIN
    
    WHILE v_counter <= (select COUNT(*) from customer) LOOP
        select customer_id, name, salesperson_id 
        into v_cliente_id, v_nombre_cliente, v_codigo_empleado
        from customer
        where ROWNUM = v_counter;
        
        insert into temp values (v_cliente_id, v_nombre_cliente, v_codigo_empleado);
        
        v_counter := v_counter + 1;
    END LOOP;
    
    -- Mostrar los datos de la tabla temporal
    FOR row_data IN (select * from temp) LOOP
        DBMS_OUTPUT.PUT_LINE('Cliente ID: ' || row_data.cliente_id || ', Nombre: ' || row_data.nombre_cliente || ', Código Empleado: ' || row_data.codigo_empleado);
    END LOOP;
END;
/


/*c.	usando bucle FOR*/
drop table temp;

    create global temporary table temp (
        cliente_id number(4),
        nombre_cliente varchar2(50),
        codigo_empleado number(4)
    );

DECLARE
    v_cliente_id customer.customer_id%type;
    v_nombre_cliente customer.name%type;
    v_codigo_empleado customer.salesperson_id%type;
BEGIN

    FOR c IN (SELECT customer_id, name, salesperson_id  FROM customer) LOOP
        INSERT INTO temp VALUES (c.cliente_id, c.nombre_cliente, c.codigo_empleado);
    END LOOP;

    FOR row_data IN (SELECT * FROM temp) LOOP
        DBMS_OUTPUT.PUT_LINE('Cliente ID: ' || row_data.cliente_id || ', Nombre: ' || row_data.nombre_cliente || ', Código Empleado: ' || row_data.codigo_empleado);
    END LOOP;
END;
/

/*7. A los pedidos del cliente 2, añadir 10 unidades más a la cantidad e insertar en una tabla temporal el código 
de artículo y un mensaje que diga "Diez unidades más vendidas".*/
drop table temp;

update item set quantity = quantity + 10 where order_id IN (
    select order_id from sales_order where customer_id = 2);

create global temporary table temp(item_id number(4), message varchar2(100));
insert into temp (item_id, message)
select item.item_id, 'Diez unidades más vendidas' as message from item 
join sales_order ON item.order_id = sales_order.order_id where sales_order.customer_id = 2;
/

/*8. Crear un procedimiento que añada nuevos pedidos a un cliente determinado. El procedimiento recibe 
el id_pedido y id_cliente.*/
CREATE OR REPLACE PROCEDURE InsertarNuevoPedido(
    p_id_pedido IN NUMBER,
    p_id_cliente IN NUMBER
)
IS
BEGIN
    INSERT INTO sales_order (order_id, order_date, customer_id, ship_date, total)
    VALUES (p_id_pedido, SYSDATE, p_id_cliente, SYSDATE, 0);
    
END InsertarNuevoPedido;
/

/*9. Procedimiento que borra los pedidos del cliente especificado. Recibe el cod_cliente.*/ 
CREATE OR REPLACE PROCEDURE BorrarPedidosCliente(
    p_customer_id IN NUMBER
)
IS
BEGIN
    
    delete from sales_order where customer_id = p_customer_id;
    
    COMMIT; -- Para confirmar los cambios permanentemente
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK; -- Deshacer cambios en caso de error
END BorrarPedidosCliente;
/

/*10. Procedimiento para cambiar el oficio de un empleado. Se pasa al cod_empleado y el nuevo oficio. 
Insertará en TEMP oficio_ant, nuevo oficio, cod_empleado.*/
drop table temp;
CREATE TABLE temp (
    oficio_antiguo VARCHAR2(50),
    nuevo_oficio VARCHAR2(50),
    cod_empleado NUMBER
);
CREATE OR REPLACE PROCEDURE CambiarOficioEmpleado(
    p_cod_empleado IN NUMBER,
    p_nuevo_oficio IN VARCHAR2
)
IS
    v_oficio_antiguo VARCHAR2(50);
BEGIN

    select job.function into v_oficio_antiguo from employee e
    join job on e.job_id = job.job_id where e.employee_id = p_cod_empleado;
    
    update employee
    set job_id = (select job_id from job where function = p_nuevo_oficio)
    where employee_id = p_cod_empleado;
    
    insert into temp (oficio_antiguo, nuevo_oficio, cod_empleado)
    values (v_oficio_antiguo, p_nuevo_oficio, p_cod_empleado);
    
END CambiarOficioEmpleado;
/

/*11. Crear una función "Anual" para devolver el salario anual cuando se pasa el salario mensual y la comisión 
de un empleado. Asegurarse que controla nulos. Utilizar una variable de acoplamiento para ver lo que devuelve.*/
CREATE OR REPLACE FUNCTION Anual(
    p_salario_mensual IN NUMBER,
    p_comision IN NUMBER
)
RETURN NUMBER
IS
    v_salario_anual NUMBER;
BEGIN
    
    IF p_salario_mensual IS NOT NULL AND p_comision IS NOT NULL THEN
        v_salario_anual := (p_salario_mensual * 12) + p_comision;
    ELSE
        v_salario_anual := NULL;
    END IF;
    
    RETURN v_salario_anual;
END Anual;

DECLARE
    v_resultado NUMBER;
BEGIN
    v_resultado := Anual(1000, 200); -- ejemplo de llamar funcion
    DBMS_OUTPUT.PUT_LINE('Salario anual: ' || v_resultado);
END;
/

/*12. Crear un paquete "Actualiza" que tiene tres procedimientos y una tabla PL:
a.	Un procedimiento de alta de un pedido.
b.	Un procedimiento de baja de los pedidos y detalles de un cliente concreto.
c.	Una tabla PL que almacenará los códigos de los pedidos.
d.	Un procedimiento "Listar" que tiene como parámetro 'in' el código de cliente y como parámetro de salida 
una tabla PL con los códigos de los pedidos del cliente especificado, además se grabará en una tabla temporal 
el código del pedido y el código del artículo.*/
CREATE OR REPLACE PACKAGE Actualiza AS
    -- A
    PROCEDURE AltaPedido(p_IdCliente IN NUMBER, p_IdPedido IN NUMBER, p_IdArticulo IN NUMBER, p_Cantidad IN NUMBER);

    -- B
    PROCEDURE BajaPedidosCliente(p_IdCliente IN NUMBER);

    -- C
    TYPE PedidoCodes IS TABLE OF NUMBER INDEX BY PLS_INTEGER;

    -- D
    PROCEDURE Listar(p_IdCliente IN NUMBER, p_Pedidos OUT PedidoCodes);
END Actualiza;
/

CREATE OR REPLACE PACKAGE BODY Actualiza AS
    -- A. Procedimiento para dar de alta un pedido
    PROCEDURE AltaPedido(p_OrderId IN NUMBER, p_OrderDate IN DATE, p_CustomerId IN NUMBER, p_ShipDate IN DATE, p_Total IN NUMBER) IS
    BEGIN
        -- Insertar un nuevo pedido en la tabla de pedidos
        INSERT INTO sales_order (order_id, order_date, customer_id, ship_date, total)
        VALUES (p_OrderId, p_OrderDate, p_CustomerId, p_ShipDate, p_Total);

    END AltaPedido;

    -- B. Procedimiento para dar de baja pedidos y detalles de un cliente específico
    PROCEDURE BajaPedidosCliente(p_CustomerId IN NUMBER) IS
    BEGIN
        -- Eliminar pedidos del cliente concreto de la tabla de pedidos
        DELETE FROM sales_order WHERE customer_id = p_CustomerId;

        -- Eliminar detalles de los pedidos del cliente concreto de la tabla de detalles de pedidos (si existe)
        DELETE FROM order_details WHERE order_id IN (SELECT order_id FROM sales_order WHERE customer_id = p_CustomerId);

    END BajaPedidosCliente;

    -- D. Procedimiento para listar los pedidos de un cliente y grabarlos en una tabla temporal
    PROCEDURE Listar(p_CustomerId IN NUMBER, p_Pedidos OUT PedidoCodes) IS
    BEGIN
        -- Recuperar los códigos de los pedidos del cliente concreto y grabarlos en p_Pedidos
        FOR pedidos_rec IN (SELECT order_id FROM sales_order WHERE customer_id = p_CustomerId) LOOP
            p_Pedidos(pedidos_rec.order_id) := pedidos_rec.order_id;
        END LOOP;

    END Listar;
END Actualiza;
/

/*13. Crear un trigger para ver como suceden los eventos de activación. Vamos a utilizar la tabla plantilla y 
vamos a cambiar a las "Enfermeras" por "Interinos".*/

-- Me creo la tabla plantilla porque no hay ninguna parecida en nuestra base de datos
CREATE TABLE plantilla (
    empleado_id NUMBER PRIMARY KEY,
    nombre VARCHAR2(100),
    puesto VARCHAR2(50)
);

INSERT INTO plantilla (empleado_id, nombre, puesto) VALUES (1, 'Juan', 'Enfermeras');
INSERT INTO plantilla (empleado_id, nombre, puesto) VALUES (2, 'Maria', 'Médico');
INSERT INTO plantilla (empleado_id, nombre, puesto) VALUES (3, 'Carlos', 'Enfermeras');
INSERT INTO plantilla (empleado_id, nombre, puesto) VALUES (4, 'Ana', 'Administrativo');


CREATE OR REPLACE TRIGGER CambiarEnfermeras
BEFORE INSERT OR UPDATE ON plantilla
FOR EACH ROW
BEGIN
    -- Si el nuevo valor de la columna puesto es "Enfermeras", cambiarlo a "Interinos"
    IF :NEW.puesto = 'Enfermeras' THEN
        :NEW.puesto := 'Interinos';
    END IF;
END CambiarEnfermeras;
/

/*14. Crear un disparador a nivel de fila tal que después de insertar, modificar o borrar un detalle de la 
tabla detalles introduzca en la tabla temporal el usuario , la fecha del sistema el código de pedido que se ha 
modificado, insertado o borrado, así como un mensaje que diga "Detalle dado de alta", "Detalle borrado", 
"Detalle modificado" según corresponda.*/

-- Me creo la tabla detalles porque no hay ninguna parecida en nuestra base de datos
CREATE TABLE detalles (
    detalle_id NUMBER PRIMARY KEY,
    codigo_pedido NUMBER,
    descripcion VARCHAR2(255),
    cantidad NUMBER
);

INSERT INTO detalles (detalle_id, codigo_pedido, descripcion, cantidad) VALUES (1, 101, 'Producto A', 3);
INSERT INTO detalles (detalle_id, codigo_pedido, descripcion, cantidad) VALUES (2, 102, 'Producto B', 5);
INSERT INTO detalles (detalle_id, codigo_pedido, descripcion, cantidad) VALUES (3, 101, 'Producto C', 2);
INSERT INTO detalles (detalle_id, codigo_pedido, descripcion, cantidad) VALUES (4, 103, 'Producto D', 4);
INSERT INTO detalles (detalle_id, codigo_pedido, descripcion, cantidad) VALUES (5, 102, 'Producto E', 1);



drop table temp;
CREATE TABLE temp (
    usuario VARCHAR2(50),
    fecha DATE,
    codigo_pedido NUMBER,
    mensaje VARCHAR2(100)
);

CREATE OR REPLACE TRIGGER detalles_trigger
AFTER INSERT OR UPDATE OR DELETE ON detalles
FOR EACH ROW
DECLARE
    v_mensaje VARCHAR2(100);
BEGIN
    IF INSERTING THEN
        v_mensaje := 'Detalle dado de alta';
    ELSIF UPDATING THEN
        v_mensaje := 'Detalle modificado';
    ELSIF DELETING THEN
        v_mensaje := 'Detalle borrado';
    END IF;

    -- Inserta información en la tabla temporal
    INSERT INTO temp (usuario, fecha, codigo_pedido, mensaje)
    VALUES (USER, SYSDATE, :OLD.codigo_pedido, v_mensaje);
END;
/

/*15. Disparador que inserta una fila en la tabla Temp con el texto ‘subida de salario empleado’ y el número 
del empleado al que se le ha subido el salario. El disparador se activará después de actualizar el salario de la 
tabla empleador.*/ 
drop table temp;
CREATE TABLE temp (
    mensaje VARCHAR2(100),
    empleado_id NUMBER
);

CREATE OR REPLACE TRIGGER subida_salario_trigger
AFTER UPDATE OF salary ON employee
FOR EACH ROW
BEGIN
    IF :OLD.salary != :NEW.salary THEN
        INSERT INTO temp (mensaje, empleado_id)
        VALUES ('subida de salario empleado', :NEW.employee_id);
    END IF;
END;
/

/*16. Trigger que se dispara cada vez que se borra un empleado, guardando el número empleado, apellido y departamento 
en la tabla TEMP.*/
drop table temp;
CREATE TABLE temp (
    empleado_id NUMBER,
    apellido VARCHAR2(50),
    departamento_id NUMBER
);

CREATE OR REPLACE TRIGGER borrado_empleado_trigger
AFTER DELETE ON employee
FOR EACH ROW
BEGIN
    INSERT INTO temp (empleado_id, apellido, departamento_id)
    VALUES (:OLD.employee_id, :OLD.last_name, :OLD.department_id);
END;
/

/*17. Limitar a 5 (0 al 4) el número de detalles de cada pedido.*/

-- Ya tengo la tabla detalles del ejercicio 14

CREATE TABLE PedidoDetallesContador (
    codigo_pedido NUMBER PRIMARY KEY,
    contador NUMBER DEFAULT 0
);

CREATE OR REPLACE TRIGGER limitar_detalles_trigger
BEFORE INSERT ON detalles
FOR EACH ROW
DECLARE
    v_contador NUMBER;
BEGIN
    -- Obtener el contador actual para el pedido
    SELECT contador INTO v_contador
    FROM PedidoDetallesContador
    WHERE codigo_pedido = :NEW.codigo_pedido;

    -- Verificar si el contador es menor que 5
    IF v_contador < 5 THEN
        -- Incrementar el contador
        UPDATE PedidoDetallesContador
        SET contador = v_contador + 1
        WHERE codigo_pedido = :NEW.codigo_pedido;
    ELSE
        -- Cancelar la inserción si es igual o mayor que 5
        RAISE_APPLICATION_ERROR(-20001, 'No se pueden agregar más de 5 detalles a este pedido.');
    END IF;
END;
/

/*18. Solicitar un código de cliente por teclado; si existe se inserta su código en una tabla temporal y el mensaje 
'EXISTE', y si no existe se dispara la excepción NO_DATA_FOUND y se inserta en la tabla temporal el mensaje 'NO EXISTE'*/
drop table temp;
CREATE TABLE temp (
    codigo_cliente NUMBER,
    mensaje VARCHAR2(80)
);

DECLARE
    v_customer_id NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Introduce el código de cliente:');
    &v_customer_id;

    -- Encontrar el cliente por el ID proporcionado
    BEGIN
        SELECT customer_id INTO v_customer_id
        FROM customer
        WHERE customer_id = v_customer_id;

        -- Insertar el código del cliente y el mensaje 'EXISTE' en temp
        INSERT INTO temp (codigo_cliente, mensaje)
        VALUES (v_customer_id, 'EXISTE');
        
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Insertar el mensaje 'NO EXISTE' en temp si no se encuentra el cliente
            INSERT INTO tabla_temporal (codigo_cliente, mensaje)
            VALUES (v_customer_id, 'NO EXISTE');
    END;
END;
/

/*19. Se solicita un código de artículo por teclado y se inserta en una tabla temporal el precio y la descripción de 
este artículo si está pedido, es decir, si su código está en la tabla detalle. Si el artículo no está pedido, se 
genera una excepción de usuario  con el mensaje "El artículo (código) no lo ha pedido ningún cliente"*/
drop table temp;
CREATE TABLE temp (
    codigo_articulo VARCHAR2(50),
    precio NUMBER,
    descripcion VARCHAR2(255),
    mensaje VARCHAR2(100)
);

--Nuestra tabla detalles no tiene algunas columnas que pide, pero este seria el codigo

DECLARE
    v_codigo_articulo detalles.codigo_articulo%TYPE;
    v_precio detalles.precio%TYPE;
    v_descripcion detalles.descripcion%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Ingrese el código del artículo: ');
    &v_codigo_articulo;

    -- Verificar si el artículo está en la tabla detalles
    BEGIN
        SELECT precio, descripcion INTO v_precio, v_descripcion
        FROM detalles
        WHERE codigo_articulo = v_codigo_articulo;

        -- El artículo si está en la tabla detalles(pasamos a temp)
        INSERT INTO temp (codigo_articulo, precio, descripcion, mensaje)
        VALUES (v_codigo_articulo, v_precio, v_descripcion, 'ARTICULO PEDIDO');

        DBMS_OUTPUT.PUT_LINE('Artículo encontrado. Precio: ' || v_precio || ', Descripción: ' || v_descripcion);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Si el artículo no está en la tabla detalles
            RAISE_APPLICATION_ERROR(-20001, 'El artículo (' || v_codigo_articulo || ') no lo ha pedido ningún cliente');
    END;
END;
/

/*20. Modificar el ejercicio 2 añadiendo excepciones, de tal forma que en el gestor de excepciones controlemos que 
select ha fallado, insertando en una tabla temporal cliente no existe, o bien articulo no existe, según haya fallado 
uno u otro.*/
drop table temp;
CREATE TABLE temp (
    nombre VARCHAR2(50),
    codigo NUMBER(4),
    num_pedidos NUMBER(4),
    precio_total NUMBER(8)
);

DECLARE
    v_codigo sales_order.customer_id%TYPE;
    v_nombre customer.name%TYPE;
    v_num_pedidos NUMBER(4);
    v_total sales_order.total%TYPE;
BEGIN
    v_codigo := &ID_customer;

    BEGIN
        SELECT name INTO v_nombre FROM customer WHERE customer_id = v_codigo;
        
        BEGIN
            SELECT COUNT(*), SUM(total) INTO v_num_pedidos, v_total FROM sales_order WHERE customer_id = v_codigo;

            INSERT INTO temp VALUES (v_nombre, v_codigo, v_num_pedidos, v_total);
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Capturar la excepción si no hay pedidos para este cliente
            INSERT INTO temp VALUES (v_nombre, v_codigo, 0, 0);
    END;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Capturar la excepción si el cliente no existe
            INSERT INTO temp VALUES ('Cliente no existe', v_codigo, 0, 0);
    END;
END;
/

/*21. Crear un trigger que se ejecutará automáticamente cuando se elimine algún empleado en la tabla correspondiente 
visualizando el número y el nombre de los empleados borrados.*/

CREATE OR REPLACE TRIGGER eliminar_empleado_trigger
AFTER DELETE ON employee
FOR EACH ROW
DECLARE
    v_employee_id employee.employee_id%TYPE;
    v_last_name employee.last_name%TYPE;
BEGIN
    -- Obtener el id y el nombre del empleado eliminado
    v_employee_id := :OLD.employee_id;
    v_last_name := :OLD.last_name;
    
    -- Mostrar información sobre el empleado eliminado
    DBMS_OUTPUT.PUT_LINE('Empleado eliminado - Número: ' || v_employee_id || ', Apellido: ' || v_last_name);
END;
/



