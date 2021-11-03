package br.com.zup.LeadCollector.config;

import br.com.zup.LeadCollector.lead.exceptions.LeadEProdutoJaCadastroException;
import br.com.zup.LeadCollector.lead.exceptions.LeadNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControladorAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<MensagemDeErro> manipularExcecoesDeValidacao(MethodArgumentNotValidException exception){
        /*
        //forma mais elegante
        List<MensagemDeErro> mensagens = exception.getFieldErrors().stream()
                .map(erro -> new MensagemDeErro(erro.getDefaultMessage(),  fieldError.getField())).collect(Collectors.toList());
        */
        List<MensagemDeErro> mensagens = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()){
            mensagens.add(new MensagemDeErro(fieldError.getDefaultMessage(), fieldError.getField()));
        }

        return mensagens;
    }

    @ExceptionHandler(LeadEProdutoJaCadastroException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemDeErro manipularExcecaoDeLeadEProdutoJaCadastrado(LeadEProdutoJaCadastroException exception){
        return new MensagemDeErro(exception.getMessage(), "sem campo");
    }

    @ExceptionHandler(LeadNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro manipularExcecaoDeLeadEProdutoJaCadastrado(LeadNaoEncontradoException exception){
        return new MensagemDeErro(exception.getMessage(), "sem campo");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemDeErro mensagemGenerica(RuntimeException exception){
        System.out.println(exception);
        return new MensagemDeErro("Algo deu errado. Volte mais tarde", "xablau");
    }
}
