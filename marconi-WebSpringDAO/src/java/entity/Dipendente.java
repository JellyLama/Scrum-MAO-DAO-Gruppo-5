/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author Bachir_Karim
 */
@Entity
public class Dipendente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String Categoria;
    
    @Basic
    private String cognome;

    @Basic
    private String nome;
    
    @Basic
    private String annoAssunzione;

    @Basic
    private String qualifica;
    
    @Basic
    private float costoPerOra;
    
    @ManyToOne(targetEntity = Specie.class)
    private Specie specie;
    
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getCategoria()
    {
        return Categoria;
    }

    public void setCategoria( String Categoria )
    {
        this.Categoria = Categoria;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome( String cognome )
    {
        this.cognome = cognome;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getAnnoAssunzione()
    {
        return annoAssunzione;
    }

    public void setAnnoAssunzione( String annoAssunzione )
    {
        this.annoAssunzione = annoAssunzione;
    }

    public String getQualifica()
    {
        return qualifica;
    }

    public void setQualifica( String qualifica )
    {
        this.qualifica = qualifica;
    }

    public float getCostoPerOra()
    {
        return costoPerOra;
    }

    public void setCostoPerOra( float costoPerOra )
    {
        this.costoPerOra = costoPerOra;
    }

    public Specie getSpecie()
    {
        return specie;
    }

    public void setSpecie( Specie specie )
    {
        this.specie = specie;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !(object instanceof Dipendente) )
        {
            return false;
        }
        Dipendente other = (Dipendente) object;
        if( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Dipendente[ id=" + id + " ]";
    }
    
}
