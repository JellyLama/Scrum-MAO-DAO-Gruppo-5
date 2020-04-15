/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entity.Pianta;
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
public class PiantaJpaController implements Serializable
{

    public PiantaJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Pianta pianta )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pianta);
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

    public void edit( Pianta pianta ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            pianta = em.merge(pianta);
            em.getTransaction().commit();
        }
        catch( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if( msg == null || msg.length() == 0 )
            {
                Long id = pianta.getId();
                if( findPianta(id) == null )
                {
                    throw new NonexistentEntityException("The pianta with id " + id + " no longer exists.");
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
            Pianta pianta;
            try
            {
                pianta = em.getReference(Pianta.class, id);
                pianta.getId();
            }
            catch( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException("The pianta with id " + id + " no longer exists.", enfe);
            }
            em.remove(pianta);
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

    public List<Pianta> findPiantaEntities()
    {
        return findPiantaEntities(true, -1, -1);
    }

    public List<Pianta> findPiantaEntities( int maxResults, int firstResult )
    {
        return findPiantaEntities(false, maxResults, firstResult);
    }

    private List<Pianta> findPiantaEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select object(o) from Pianta as o");
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

    public Pianta findPianta( Long id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Pianta.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getPiantaCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select count(o) from Pianta as o");
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
