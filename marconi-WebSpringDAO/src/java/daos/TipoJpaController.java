/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entity.Tipo;
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
public class TipoJpaController implements Serializable
{

    public TipoJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Tipo tipo )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipo);
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

    public void edit( Tipo tipo ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            tipo = em.merge(tipo);
            em.getTransaction().commit();
        }
        catch( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if( msg == null || msg.length() == 0 )
            {
                Long id = tipo.getId();
                if( findTipo(id) == null )
                {
                    throw new NonexistentEntityException("The tipo with id " + id + " no longer exists.");
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
            Tipo tipo;
            try
            {
                tipo = em.getReference(Tipo.class, id);
                tipo.getId();
            }
            catch( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException("The tipo with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipo);
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

    public List<Tipo> findTipoEntities()
    {
        return findTipoEntities(true, -1, -1);
    }

    public List<Tipo> findTipoEntities( int maxResults, int firstResult )
    {
        return findTipoEntities(false, maxResults, firstResult);
    }

    private List<Tipo> findTipoEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select object(o) from Tipo as o");
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

    public Tipo findTipo( Long id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Tipo.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getTipoCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select count(o) from Tipo as o");
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
