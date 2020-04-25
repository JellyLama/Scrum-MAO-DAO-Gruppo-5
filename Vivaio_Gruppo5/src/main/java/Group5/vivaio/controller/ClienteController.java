/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.ClienteDao;
import Group5.vivaio.entities.Cliente;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Bachir_Karim
 */
@RestController
@RequestMapping("/clienti")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ClienteController
{

    @Autowired
    ClienteDao clienteDao;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> getAllClienti()
    {
        return (List<Cliente>) clienteDao.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cliente getClienteById( @PathVariable Long id )
    {
        return clienteDao.findById(id).get();
    }

    @PostMapping()
    public Cliente addCliente(HttpServletRequest request, @RequestBody Cliente c)
    {
        return clienteDao.save(c);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente( @PathVariable Long id )
    {
        clienteDao.deleteById(id);
    }
    
    @GetMapping(params ={"username", "password"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cliente findClienteByUsernamePassword(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        return clienteDao.findByUsernameAndPassword(username, password).get();
    }

}
