package com.charartpav.converte.controllers;

import com.charartpav.converte.controllers.exceptions.NonexistentEntityException;
import com.charartpav.converte.models.UserList;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.charartpav.converte.models.UserRole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**@author Artem Charykov*/
public class UserListJpaController implements Serializable {

   public UserListJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(UserList userList) {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         UserRole userRoleID = userList.getUserRoleID();
         if (userRoleID != null) {
            userRoleID = em.getReference(userRoleID.getClass(), userRoleID.getUserRoleID());
            userList.setUserRoleID(userRoleID);
         }
         em.persist(userList);
         if (userRoleID != null) {
            userRoleID.getUserListList().add(userList);
            userRoleID = em.merge(userRoleID);
         }
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(UserList userList) throws NonexistentEntityException, Exception {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         UserList persistentUserList = em.find(UserList.class, userList.getUserID());
         UserRole userRoleIDOld = persistentUserList.getUserRoleID();
         UserRole userRoleIDNew = userList.getUserRoleID();
         if (userRoleIDNew != null) {
            userRoleIDNew = em.getReference(userRoleIDNew.getClass(), userRoleIDNew.getUserRoleID());
            userList.setUserRoleID(userRoleIDNew);
         }
         userList = em.merge(userList);
         if (userRoleIDOld != null && !userRoleIDOld.equals(userRoleIDNew)) {
            userRoleIDOld.getUserListList().remove(userList);
            userRoleIDOld = em.merge(userRoleIDOld);
         }
         if (userRoleIDNew != null && !userRoleIDNew.equals(userRoleIDOld)) {
            userRoleIDNew.getUserListList().add(userList);
            userRoleIDNew = em.merge(userRoleIDNew);
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            Long id = userList.getUserID();
            if (findUserList(id) == null) {
               throw new NonexistentEntityException("The userList with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(Long id) throws NonexistentEntityException {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         UserList userList;
         try {
            userList = em.getReference(UserList.class, id);
            userList.getUserID();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The userList with id " + id + " no longer exists.", enfe);
         }
         UserRole userRoleID = userList.getUserRoleID();
         if (userRoleID != null) {
            userRoleID.getUserListList().remove(userList);
            userRoleID = em.merge(userRoleID);
         }
         em.remove(userList);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<UserList> findUserListEntities() {
      return findUserListEntities(true, -1, -1);
   }

   public List<UserList> findUserListEntities(int maxResults, int firstResult) {
      return findUserListEntities(false, maxResults, firstResult);
   }

   private List<UserList> findUserListEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(UserList.class));
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

   public UserList findUserList(Long id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(UserList.class, id);
      } finally {
         em.close();
      }
   }

   public int getUserListCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<UserList> rt = cq.from(UserList.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }

}
