package br.com.zup.LeadCollector.lead;

import br.com.zup.LeadCollector.lead.dtos.LeadDTO;
import br.com.zup.LeadCollector.lead.exceptions.LeadEProdutoJaCadastroException;
import br.com.zup.LeadCollector.lead.exceptions.LeadNaoEncontradoException;
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
            LeadDTO leadDaLista = buscarLead(leadDTO.getEmail());
            atualizarListaDeProdutos(leadDTO.getProdutos(), leadDaLista);

        }catch (LeadNaoEncontradoException exception){
            mailing.add(leadDTO);
        }

    }

    public void verificarLeadEProduto(LeadDTO objetoChegandoAgora){
            LeadDTO objetoDaLista = buscarLead(objetoChegandoAgora.getEmail());

            for (ProdutoDTO produtoDTO : objetoChegandoAgora.getProdutos()){
                if (produtoEstaPresente(objetoDaLista.getProdutos(), produtoDTO.getNomeProduto())){
                    throw new LeadEProdutoJaCadastroException("Lead e Produto já cadastrado");
                }
            }

    }

    public LeadDTO buscarLead(String email){
        for(LeadDTO leadDTO : mailing){
            if(leadDTO.getEmail().equalsIgnoreCase(email)){
                return leadDTO;
            }
        }
        throw new LeadNaoEncontradoException("Lead não encontrado");
    }

    public boolean produtoEstaPresente(List<ProdutoDTO> listaDeInteresse, String nomeProduto){
        for(ProdutoDTO produtoDTO : listaDeInteresse){
            if (produtoDTO.getNomeProduto().equalsIgnoreCase(nomeProduto)){
                return true;
            }
        }
        return false;
    }

    public void atualizarListaDeProdutos(List<ProdutoDTO> novosProdutos, LeadDTO leadParaAtualizar){
        for (ProdutoDTO produtoDTO : novosProdutos){
            leadParaAtualizar.getProdutos().add(produtoDTO);
        }
    }

    public List<LeadDTO> retornarTodosOsLead(){
        return mailing;
    }

}
