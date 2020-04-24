/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.dao;

import Group5.vivaio.entities.Dipendente;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Bachir_Karim
 */
public interface DipendenteDao extends CrudRepository<Dipendente, Long>{
    Optional<Dipendente> findByUsernameAndPassword(String username, String password);
    
}
