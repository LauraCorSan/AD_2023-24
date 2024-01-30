/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persistenciadbemp;

import com.mycompany.persistenciadbemp.exceptions.NonexistentEntityException;
import com.mycompany.persistenciadbemp.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author laura
 */
public class DeptJpaController implements Serializable {

    public DeptJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dept dept) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dept);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDept(dept.getDeptno()) != null) {
                throw new PreexistingEntityException("Dept " + dept + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dept dept) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dept = em.merge(dept);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dept.getDeptno();
                if (findDept(id) == null) {
                    throw new NonexistentEntityException("The dept with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dept dept;
            try {
                dept = em.getReference(Dept.class, id);
                dept.getDeptno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dept with id " + id + " no longer exists.", enfe);
            }
            em.remove(dept);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dept> findDeptEntities() {
        return findDeptEntities(true, -1, -1);
    }

    public List<Dept> findDeptEntities(int maxResults, int firstResult) {
        return findDeptEntities(false, maxResults, firstResult);
    }

    private List<Dept> findDeptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dept.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dept findDept(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dept.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeptCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dept> rt = cq.from(Dept.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
