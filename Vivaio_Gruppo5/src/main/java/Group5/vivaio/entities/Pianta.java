/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;


/**
 *
 * @author Bachir_Karim
 */
@Entity
@Data
@Table(name = "piante")
public class Pianta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String nome;
    
    @Basic
    private String descrizione;
    
    @Basic
    private String immagine;
    
    @Basic
    private String stagioneFioritura;

    @ManyToOne
    private Specie specie;
    
    @ManyToOne
    private Tipo tipo;

}
