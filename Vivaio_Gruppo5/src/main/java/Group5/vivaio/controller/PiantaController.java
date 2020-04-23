/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.PiantaDao;
import Group5.vivaio.entities.Pianta;
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
@RequestMapping("/piante")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PiantaController
{
    @Autowired
    PiantaDao piantaDao;
    
    @GetMapping()
    public List<Pianta> getAllPiante()
    {
        return (List<Pianta>) piantaDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Pianta get( @PathVariable Long id )
    {
        return piantaDao.findById(id).get();
    }
    
    @PostMapping
    public void post( @RequestBody Pianta p )
    {
        piantaDao.save(p);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        piantaDao.deleteById(id);
    }
    
}
