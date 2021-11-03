package br.com.zup.LeadCollector.lead.dtos;

import br.com.zup.LeadCollector.produto.dtos.ProdutoDTO;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.List;

public class LeadDTO {
    @Email(message = "Email invalido")
    private String email;
    @Size(min = 2, max = 21, message = "nome deve ter no minimo 2 à 21 caracteres")
    @NotBlank
    private String nome;
    @CPF(message = "CPF INVALIDO")
    private String cpf;
    private String telefone;
    private List<ProdutoDTO> produtos;

    public LeadDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
