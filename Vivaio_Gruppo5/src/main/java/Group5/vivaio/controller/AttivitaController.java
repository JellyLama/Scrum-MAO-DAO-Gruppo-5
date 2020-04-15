/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/url")
public class AttivitaController
{
    
    @GetMapping()
    public List<Object> list()
    {
        return null;
    }
    
    @GetMapping("/{id}")
    public Object get( @PathVariable String id )
    {
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put( @PathVariable String id, @RequestBody Object input )
    {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post( @RequestBody Object input )
    {
        return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable String id )
    {
        return null;
    }
    
}
