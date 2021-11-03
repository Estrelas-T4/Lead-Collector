package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.lead.dtos.LeadDTO;
import br.com.zup.LeadCollector.produto.dtos.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    private List<LeadDTO> mailing = new ArrayList<>();

    public void salvarLead(LeadDTO leadDTO){
        try{
            verificarLeadEProduto(leadDTO);
        }
        mailing.add(leadDTO);
    }

    public void verificarLeadEProduto(LeadDTO objetoChegandoAgora){
        try{
            LeadDTO objetoDaLista = buscarLead(objetoChegandoAgora.getEmail());

            for (ProdutoDTO produtoDTO : objetoChegandoAgora.getProdutos()){
                if (produtoEstaPresente(objetoDaLista.getProdutos(), produtoDTO.getNomeProduto())){
                    throw new RuntimeException("Produto e Lead já cadastro");
                }
            }
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }

    }

    public LeadDTO buscarLead(String email){
        for(LeadDTO leadDTO : mailing){
            if(leadDTO.getEmail().equalsIgnoreCase(email)){
                return leadDTO;
            }
        }
        throw new RuntimeException("Lead não encontrado");
    }

    public boolean produtoEstaPresente(List<ProdutoDTO> listaDeInteresse, String nomeProduto){
        for(ProdutoDTO produtoDTO : listaDeInteresse){
            if (produtoDTO.getNomeProduto().equalsIgnoreCase(nomeProduto)){
                return true;
            }
        }
        return false;
    }

    public List<LeadDTO> retornarTodosOsLead(){
        return mailing;
    }

}
