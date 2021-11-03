package br.com.zup.LeadCollector.config;

public class MensagemDeErro {
    public String mensagem;
    public String campo;

    public MensagemDeErro(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
