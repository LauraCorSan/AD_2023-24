/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciamodulo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author laura
 */
public class PersistenciaModulo {

    public static void main(String[] args) {
        final Modulo modulo = new Modulo();
        modulo.setNombre("BASES DE DATOS");
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try{
            emf = Persistence.createEntityManagerFactory("PersistenciaModuloPU");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(modulo);
            em.getTransaction().commit();
        }catch(final Exception e){
            if(em != null){
                em.getTransaction().rollback();
            }
        }finally{
            if(em!=null)em.close();
            if(emf!=null)emf.close();
        }
    }
}
