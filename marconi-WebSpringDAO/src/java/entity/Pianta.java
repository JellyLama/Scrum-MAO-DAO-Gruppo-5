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
public class Pianta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity = Specie.class)
    private Specie specie;

    @Basic
    private String nome;
    
    @Basic
    private String descrizione;
    
    @Basic
    private String immagine;
    
    @Basic
    private String stagioneDiFioritura;
    
    @ManyToOne(targetEntity = Tipo.class)
    private Tipo tipo;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Specie getSpecie()
    {
        return specie;
    }

    public void setSpecie( Specie specie )
    {
        this.specie = specie;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione( String descrizione )
    {
        this.descrizione = descrizione;
    }

    public String getImmagine()
    {
        return immagine;
    }

    public void setImmagine( String immagine )
    {
        this.immagine = immagine;
    }

    public String getStagioneDiFioritura()
    {
        return stagioneDiFioritura;
    }

    public void setStagioneDiFioritura( String stagioneDiFioritura )
    {
        this.stagioneDiFioritura = stagioneDiFioritura;
    }

    public Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo( Tipo tipo )
    {
        this.tipo = tipo;
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
        if( !(object instanceof Pianta) )
        {
            return false;
        }
        Pianta other = (Pianta) object;
        if( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Pianta[ id=" + id + " ]";
    }
    
}
