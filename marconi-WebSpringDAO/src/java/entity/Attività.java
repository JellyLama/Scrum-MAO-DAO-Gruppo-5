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
public class Attività implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;
    
    @ManyToOne(targetEntity = Dipendente.class)
    private Dipendente dipendente;
    
    @Basic
    private String tipo;
    
    @Basic
    private String nome;
    
    @Basic
    private String dataPrenotazione;
    
    @Basic
    private String dataEffettuazione;
    
    @Basic
    private boolean necessitaPiante;
    
    @Basic
    private boolean evaso;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente( Cliente cliente )
    {
        this.cliente = cliente;
    }

    public Dipendente getDipendente()
    {
        return dipendente;
    }

    public void setDipendente( Dipendente dipendente )
    {
        this.dipendente = dipendente;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo( String tipo )
    {
        this.tipo = tipo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getDataPrenotazione()
    {
        return dataPrenotazione;
    }

    public void setDataPrenotazione( String dataPrenotazione )
    {
        this.dataPrenotazione = dataPrenotazione;
    }

    public String getDataEffettuazione()
    {
        return dataEffettuazione;
    }

    public void setDataEffettuazione( String dataEffettuazione )
    {
        this.dataEffettuazione = dataEffettuazione;
    }

    public boolean isNecessitaPiante()
    {
        return necessitaPiante;
    }

    public void setNecessitaPiante( boolean necessitaPiante )
    {
        this.necessitaPiante = necessitaPiante;
    }

    public boolean isEvaso()
    {
        return evaso;
    }

    public void setEvaso( boolean evaso )
    {
        this.evaso = evaso;
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
        if( !(object instanceof Attività) )
        {
            return false;
        }
        Attività other = (Attività) object;
        if( (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Attivit\u00e0[ id=" + id + " ]";
    }
    
}
