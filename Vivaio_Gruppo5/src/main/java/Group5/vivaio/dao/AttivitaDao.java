/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.dao;

import Group5.vivaio.entities.Attivita;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Bachir_Karim
 */
public interface AttivitaDao extends CrudRepository<Attivita, Long>
{
    
}
