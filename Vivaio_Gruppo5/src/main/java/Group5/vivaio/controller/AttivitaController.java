/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Group5.vivaio.controller;

import Group5.vivaio.dao.AttivitaDao;
import Group5.vivaio.entities.Attivita;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/attivita")
@CrossOrigin(allowedHeaders = "*", origins = "*")
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
    
    @GetMapping(params ={"seguito"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attivita> checkSeguitoAttivita(HttpServletRequest request, @RequestParam("seguito") boolean seguito){
        if(seguito)
            return attivitaDao.findAllAttivitaSeguite();
        else
            return attivitaDao.findAllAttivitaNonSeguite();
    }
    
    @GetMapping(params ={"evaso", "idCliente"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attivita> checkEvasoAttivitaByCliente(HttpServletRequest request, @RequestParam("evaso") boolean evaso, @RequestParam("idCliente") Long idCliente){
        if(evaso)
            return attivitaDao.findAllAttivitaByClienteEvase(idCliente);
        else
            return attivitaDao.findAllAttivitaByClienteAndNonEvase(idCliente);
    }
    
    @GetMapping(params ={"evaso", "idDipendente"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attivita> checkEvasoAttivitaByDipendente(HttpServletRequest request, @RequestParam("evaso") boolean evaso, @RequestParam("idDipendente") Long idDipendente){
        if(evaso)
            return attivitaDao.findAllAttivitaByDipendenteEvase(idDipendente);
        else
            return attivitaDao.findAllAttivitaByDipendenteAndNonEvase(idDipendente);
    }
    
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public Attivita patchDipendente(@PathVariable Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException
    {
        Attivita attivita = attivitaDao.findById(id).get();
        Attivita attivitaAggiornata = applyPatchToAttivita(patch, attivita);
        attivitaDao.save(attivitaAggiornata);
        return attivitaAggiornata;
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
    
    private Attivita applyPatchToAttivita(
            JsonPatch patch, Attivita targetAttivita ) throws JsonPatchException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode patched = patch.apply(mapper.convertValue(targetAttivita, JsonNode.class));
        return mapper.treeToValue(patched, Attivita.class);
    }
}
