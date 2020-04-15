/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entity.Attività;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;


/**
 *
 * @author Bachir_Karim
 */
public class AttivitàJpaController implements Serializable
{

    public AttivitàJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Attività attività )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(attività);
            em.getTransaction().commit();
        }
        finally
        {
            if( em != null )
            {
                em.close();
            }
        }
    }

    public void edit( Attività attività ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            attività = em.merge(attività);
            em.getTransaction().commit();
        }
        catch( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if( msg == null || msg.length() == 0 )
            {
                Long id = attività.getId();
                if( findAttività(id) == null )
                {
                    throw new NonexistentEntityException("The attivit\u00e0 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }
        finally
        {
            if( em != null )
            {
                em.close();
            }
        }
    }

    public void destroy( Long id ) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Attività attività;
            try
            {
                attività = em.getReference(Attività.class, id);
                attività.getId();
            }
            catch( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException("The attivit\u00e0 with id " + id + " no longer exists.", enfe);
            }
            em.remove(attività);
            em.getTransaction().commit();
        }
        finally
        {
            if( em != null )
            {
                em.close();
            }
        }
    }

    public List<Attività> findAttivitàEntities()
    {
        return findAttivitàEntities(true, -1, -1);
    }

    public List<Attività> findAttivitàEntities( int maxResults, int firstResult )
    {
        return findAttivitàEntities(false, maxResults, firstResult);
    }

    private List<Attività> findAttivitàEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select object(o) from Attivit\u00e0 as o");
            if( !all )
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public Attività findAttività( Long id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Attività.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getAttivitàCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select count(o) from Attivit\u00e0 as o");
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
