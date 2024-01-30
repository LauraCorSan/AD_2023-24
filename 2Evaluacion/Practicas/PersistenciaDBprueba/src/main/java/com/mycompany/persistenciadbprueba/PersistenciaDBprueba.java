/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciadbprueba;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author laura
 */
public class PersistenciaDBprueba {

    public static void main(String[] args) {
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("PersistenciaBDprueba");
        PersonasJpaController dao = new PersonasJpaController (emf);
        List <Personas> lista = dao.findPersonasEntities();
        for(Personas person : lista){
            System.out.println("nombre: " + person.getNombres() +"\tedad: " + person.getEdad());
        }
        
        //Insertar una nueva persona
        EntityManager em;
        em = emf.createEntityManager();
        Personas person = new Personas();
        person.setId(5);
        person.setNombres("Lucia");
        person.setApellidos("Sanchez");
        person.setSalario(1500.0);
        person.setEdad(22);
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
    }
}