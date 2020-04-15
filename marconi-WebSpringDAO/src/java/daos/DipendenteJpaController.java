/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import daos.exceptions.NonexistentEntityException;
import entity.Dipendente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import entity.Specie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Bachir_Karim
 */
public class DipendenteJpaController implements Serializable
{

    public DipendenteJpaController( EntityManagerFactory emf )
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public void create( Dipendente dipendente )
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Specie specie = dipendente.getSpecie();
            if( specie != null )
            {
                specie = em.getReference(specie.getClass(), specie.getId());
                dipendente.setSpecie(specie);
            }
            em.persist(dipendente);
            if( specie != null )
            {
                Dipendente oldDipendenteOfSpecie = specie.getDipendente();
                if( oldDipendenteOfSpecie != null )
                {
                    oldDipendenteOfSpecie.setSpecie(null);
                    oldDipendenteOfSpecie = em.merge(oldDipendenteOfSpecie);
                }
                specie.setDipendente(dipendente);
                specie = em.merge(specie);
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

    public void edit( Dipendente dipendente ) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Dipendente persistentDipendente = em.find(Dipendente.class, dipendente.getId());
            Specie specieOld = persistentDipendente.getSpecie();
            Specie specieNew = dipendente.getSpecie();
            if( specieNew != null )
            {
                specieNew = em.getReference(specieNew.getClass(), specieNew.getId());
                dipendente.setSpecie(specieNew);
            }
            dipendente = em.merge(dipendente);
            if( specieOld != null && !specieOld.equals(specieNew) )
            {
                specieOld.setDipendente(null);
                specieOld = em.merge(specieOld);
            }
            if( specieNew != null && !specieNew.equals(specieOld) )
            {
                Dipendente oldDipendenteOfSpecie = specieNew.getDipendente();
                if( oldDipendenteOfSpecie != null )
                {
                    oldDipendenteOfSpecie.setSpecie(null);
                    oldDipendenteOfSpecie = em.merge(oldDipendenteOfSpecie);
                }
                specieNew.setDipendente(dipendente);
                specieNew = em.merge(specieNew);
            }
            em.getTransaction().commit();
        }
        catch( Exception ex )
        {
            String msg = ex.getLocalizedMessage();
            if( msg == null || msg.length() == 0 )
            {
                Long id = dipendente.getId();
                if( findDipendente(id) == null )
                {
                    throw new NonexistentEntityException("The dipendente with id " + id + " no longer exists.");
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
            Dipendente dipendente;
            try
            {
                dipendente = em.getReference(Dipendente.class, id);
                dipendente.getId();
            }
            catch( EntityNotFoundException enfe )
            {
                throw new NonexistentEntityException("The dipendente with id " + id + " no longer exists.", enfe);
            }
            Specie specie = dipendente.getSpecie();
            if( specie != null )
            {
                specie.setDipendente(null);
                specie = em.merge(specie);
            }
            em.remove(dipendente);
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

    public List<Dipendente> findDipendenteEntities()
    {
        return findDipendenteEntities(true, -1, -1);
    }

    public List<Dipendente> findDipendenteEntities( int maxResults, int firstResult )
    {
        return findDipendenteEntities(false, maxResults, firstResult);
    }

    private List<Dipendente> findDipendenteEntities( boolean all, int maxResults, int firstResult )
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select object(o) from Dipendente as o");
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

    public Dipendente findDipendente( Long id )
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Dipendente.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public int getDipendenteCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createQuery("select count(o) from Dipendente as o");
            return ((Long) q.getSingleResult()).intValue();
        }
        finally
        {
            em.close();
        }
    }
    
}
