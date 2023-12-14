--TEMA2 PRACTICA 2
/*Se desea crear una tabla de mascotas en la que cada mascota tiene un  veterinario y esos veterinarios ya están 
almacenados en una tabla de objetos, se podría hacer uso de la palabra reservada REF para indicar que el veterinario 
ya existe y por lo tanto, solo se almacena una referencia a ese veterinario en la tabla mascotas. 
Previo a crear las tablas debes definir los tipos correspondientes, sabiendo que veterinario  contendrá los atributos
“id”, “nombre” y “dirección”, y mascota contendrá los atributos “id”, “raza”, “nombre”, “vet”*/

-- Definir el tipo Veterinario
CREATE OR REPLACE TYPE Veterinario AS OBJECT (
    id NUMBER,
    nombre VARCHAR2(50),
    direccion VARCHAR2(50)
);
/

-- Definir el tipo Mascota con REF Veterinario
CREATE OR REPLACE TYPE Mascota AS OBJECT (
    id NUMBER,
    raza VARCHAR2(50),
    nombre VARCHAR2(50),
    vet REF Veterinario
);
/

-- Crear tabla Veterinarios
CREATE TABLE Veterinarios OF Veterinario (
    PRIMARY KEY (id)
);

-- Crear tabla Mascotas con REF Veterinario
CREATE TABLE Mascotas OF Mascota (
    PRIMARY KEY (id),
    FOREIGN KEY (vet) REFERENCES Veterinarios
);


/*Realiza las siguientes actividades en base a lo anterior:
A.	Inserta la tabla veterinarios un registro
1,'jesus perez','C/El mareo,29'*/

INSERT INTO Veterinarios VALUES (Veterinario(1, 'jesus perez', 'C/El mareo, 29'));

/*B. Inserta en la tabla mascotas una mascota
Id=1 raza =perro nombre=sproket, para el veterinario código 1.*/

INSERT INTO mascotas VALUES (1, 'perro', 'sproket',(SELECT REF(v) FROM veterinarios v WHERE v.id=1));

--C. Listar la tabla mascotas de forma que se obtenga su OID.
SELECT * FROM Mascotas;

--D. Listar los datos reales de la tabla mascota en vez de su OID.
SELECT id, nombre, raza, DEREF(vet).id, DEREF(vet).nombre, DEREF(vet).direccion FROM mascotas;

--E. Listar el nombre y la raza de las mascotas así como el nombre de su veterinario.
SELECT nombre, raza, DEREF(vet).nombre FROM mascotas; 

--F. Borra las tablas y los tipos.
DROP TABLE Mascotas;
DROP TABLE Veterinarios;
DROP TYPE Mascota;
DROP TYPE Veterinario;