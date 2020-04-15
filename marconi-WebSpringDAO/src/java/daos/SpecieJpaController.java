/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entity.Dipendente;
import entity.Specie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Bachir_Karim
 */
public class SpecieJpaController implements Serializable
{

    public SpecieJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Specie specie )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Dipendente dipendente = specie.getDipendente();
            if( dipendente != null )
            {
                dipendente = em.getReference(dipendente.getClass(), dipendente.getId());
                specie.setDipendente(dipendente);
            }
            em.persist(specie);
            if( dipendente != null )
            {
                Specie oldSpecieOfDipendente = dipendente.getSpecie();
                if( oldSpecieOfDipendente != null )
                {
                    oldSpecieOfDipendente.setDipendente(null);
                    oldSpecieOfDipendente = em.merge(oldSpecieOfDipendente);
                }
                dipendente.setSpecie(specie);
                dipendente = em.merge(dipendente);
            }
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

    public void edit( Specie specie ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Specie persistentSpecie = em.find(Specie.class, specie.getId());
            Dipendente dipendenteOld = persistentSpecie.getDipendente();
            Dipendente dipendenteNew = specie.getDipendente();
            if( dipendenteNew != null )
            {
                dipendenteNew = em.getReference(dipendenteNew.getClass(), dipendenteNew.getId());
                specie.setDipendente(dipendenteNew);
            }
            specie = em.merge(specie);
            if( dipendenteOld != null && !dipendenteOld.equals(dipendenteNew) )
            {
                dipendenteOld.setSpecie(null);
                dipendenteOld = em.merge(dipendenteOld);
            }
            if( dipendenteNew != null && !dipendenteNew.equals(dipendenteOld) )
            {
                Specie oldSpecieOfDipendente = dipendenteNew.getSpecie();
                if( oldSpecieOfDipendente != null )
                {
                    oldSpecieOfDipendente.setDipendente(null);
                    oldSpecieOfDipendente = em.merge(oldSpecieOfDipendente);
                }
                dipendenteNew.setSpecie(specie);
                dipendenteNew = em.merge(dipendenteNew);
            }
            em.getTransaction().commit();
        }
        catch( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if( msg == null || msg.length() == 0 )
            {
                Long id = specie.getId();
                if( findSpecie(id) == null )
                {
                    throw new NonexistentEntityException("The specie with id " + id + " no longer exists.");
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
            Specie specie;
            try
            {
                specie = em.getReference(Specie.class, id);
                specie.getId();
            }
            catch( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException("The specie with id " + id + " no longer exists.", enfe);
            }
            Dipendente dipendente = specie.getDipendente();
            if( dipendente != null )
            {
                dipendente.setSpecie(null);
                dipendente = em.merge(dipendente);
            }
            em.remove(specie);
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

    public List<Specie> findSpecieEntities()
    {
        return findSpecieEntities(true, -1, -1);
    }

    public List<Specie> findSpecieEntities( int maxResults, int firstResult )
    {
        return findSpecieEntities(false, maxResults, firstResult);
    }

    private List<Specie> findSpecieEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select object(o) from Specie as o");
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

    public Specie findSpecie( Long id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Specie.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getSpecieCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select count(o) from Specie as o");
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
