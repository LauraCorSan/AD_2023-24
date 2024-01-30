/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciadbemp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author laura
 */
public class PersistenciaDBemp {

    public static void main(String[] args) {
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("PersistenciaBDempPU");
        DeptJpaController dao = new DeptJpaController (emf);
        List <Dept> lista = dao.findDeptEntities();
        for(Dept departamento : lista){
            System.out.println("nombre departamento: " + departamento.getDname());
        }
        
        //Ejecucion 2: Dar de alta dept
        EntityManager em;
        em = emf.createEntityManager();
        Dept undept = new Dept();
        undept.setDeptno(60);
        undept.setDname("RRHH");
        undept.setLoc("Burgos");
            em.getTransaction().begin();
            em.persist(undept);
            em.getTransaction().commit();
    }
}
