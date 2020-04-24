/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.dao;

import Group5.vivaio.entities.Attivita;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author Bachir_Karim
 */
public interface AttivitaDao extends CrudRepository<Attivita, Long>
{

    @Query(value = "SELECT * FROM attivita a WHERE a.dipendente_id IS NOT NULL",
            nativeQuery = true)
    List<Attivita> findAllAttivitaSeguite();

    @Query(value = "SELECT * FROM attivita a WHERE a.dipendente_id IS NULL",
            nativeQuery = true)
    List<Attivita> findAllAttivitaNonSeguite();
    
    @Query(value = "SELECT * FROM attivita a WHERE a.evaso IS TRUE", nativeQuery = true)
    List<Attivita> findAllAttivitaEvase();
    
    @Query(value = "SELECT * FROM attivita a WHERE a.evaso IS FALSE", nativeQuery = true)
    List<Attivita> findAllAttivitaNonEvase();
}
