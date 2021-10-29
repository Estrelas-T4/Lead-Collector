package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.lead.dtos.LeadDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    private List<LeadDTO> mailing = new ArrayList<>();

    public void salvarLead(LeadDTO leadDTO){
        mailing.add(leadDTO);
    }

}
