package br.com.zup.LeadCollector.lead.exceptions;

public class LeadNaoEncontradoException extends RuntimeException{

    public LeadNaoEncontradoException(String message) {
        super(message);
    }
}
