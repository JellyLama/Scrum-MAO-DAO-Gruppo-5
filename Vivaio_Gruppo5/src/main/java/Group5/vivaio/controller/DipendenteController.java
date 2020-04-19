/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.DipendenteDao;
import Group5.vivaio.entities.Dipendente;
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
@RequestMapping("/dipendenti")
public class DipendenteController
{
    @Autowired
    DipendenteDao dipendenteDao;
    
    @GetMapping()
    public List<Dipendente> getAllDipendenti()
    {
        return (List<Dipendente>) dipendenteDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Dipendente getDipendenteById( @PathVariable Long id )
    {
        return dipendenteDao.findById(id).get();
    }

    @PostMapping
    public void post( @RequestBody Dipendente d )
    {
        dipendenteDao.save(d);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        dipendenteDao.deleteById(id);
    }
    
}
