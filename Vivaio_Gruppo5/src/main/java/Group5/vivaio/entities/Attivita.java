/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;


/**
 *
 * @author Bachir_Karim
 */
@Entity
@Data
@Table(name = "attivita")
public class Attivita implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Dipendente dipendente;
    
    @ManyToOne
    private DettagliAttivita dettagliAttivita;
    
    @Temporal(TemporalType.DATE)
    private Date dataPrenotazione;
    
    @Temporal(TemporalType.DATE)
    private Date dataEffettuazione;
    
    @Basic
    private boolean evaso;
}
