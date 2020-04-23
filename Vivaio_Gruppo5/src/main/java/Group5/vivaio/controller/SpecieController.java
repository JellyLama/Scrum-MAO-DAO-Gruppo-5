/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.SpecieDao;
import Group5.vivaio.entities.Specie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/specie")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class SpecieController
{
    @Autowired
    SpecieDao specieDao;
    
    @GetMapping()
    public List<Specie> getAllSpecie()
    {
        return (List<Specie>) specieDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Specie get( @PathVariable Long id )
    {
        return specieDao.findById(id).get();
    }
    
    @PostMapping
    public void post( @RequestBody Specie s )
    {
        specieDao.save(s);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        specieDao.deleteById(id);
    }
    
}
