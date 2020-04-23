/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.DettagliAttivitaDao;
import Group5.vivaio.entities.DettagliAttivita;
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
@RequestMapping("/dettagliattivita")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class DettagliAttivitaController
{
    @Autowired
    DettagliAttivitaDao dettagliAttivitaDao;
    
    @GetMapping()
    public List<DettagliAttivita> getAllDettagliAttivita()
    {
        return (List<DettagliAttivita>) dettagliAttivitaDao.findAll();
    }
    
    @GetMapping("/{id}")
    public DettagliAttivita get( @PathVariable Long id )
    {
        return dettagliAttivitaDao.findById(id).get();
    }
    
    @PostMapping
    public void post( @RequestBody DettagliAttivita t )
    {
        dettagliAttivitaDao.save(t);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        dettagliAttivitaDao.deleteById(id);
    }
    
}
