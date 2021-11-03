package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.lead.dtos.LeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/leads")
//Proxy Pattern
public class LeadController {
    @Autowired
    private LeadService leadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarLead(@RequestBody LeadDTO leadDTO){
       try{
           leadService.salvarLead(leadDTO);
       }catch (RuntimeException exception){
           throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
       }

    }

    @GetMapping
    public List<LeadDTO> exibirMailing(){
        return leadService.retornarTodosOsLead();
    }
}
