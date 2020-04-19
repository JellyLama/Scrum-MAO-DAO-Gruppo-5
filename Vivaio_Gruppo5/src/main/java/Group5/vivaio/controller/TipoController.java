/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.TipoDao;
import Group5.vivaio.entities.Tipo;
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
@RequestMapping("/tipi")
public class TipoController
{
    @Autowired
    TipoDao tipoDao;
    
    @GetMapping()
    public List<Tipo> getAllTipi()
    {
        return (List<Tipo>) tipoDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Tipo get( @PathVariable Long id )
    {
        return tipoDao.findById(id).get();
    }
    
    @PostMapping
    public void post( @RequestBody Tipo t )
    {
        tipoDao.save(t);
    }
    
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id )
    {
        tipoDao.deleteById(id);
    }
    
}
