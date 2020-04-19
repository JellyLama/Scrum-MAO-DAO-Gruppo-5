/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.AttivitaDao;
import Group5.vivaio.entities.Attivita;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/attivita")
public class AttivitaController
{
    @Autowired
    AttivitaDao attivitaDao;
    
    @GetMapping()
    public List<Attivita> getAllAttivita()
    {
        return (List<Attivita>) attivitaDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Attivita get( @PathVariable Long id )
    {
        return attivitaDao.findById(id).get();
    }

    @PostMapping
    public void post( @RequestBody Attivita a )
    {
        attivitaDao.save(a);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        attivitaDao.deleteById(id);
    }
    
}
