package com.example.tg_novo.models;

public class Notification {

    public Notification(){}

    public Notification(String usuario, String mensagem){
        this.usuario= usuario;
        this.mensagem = mensagem;
    }

    private String usuario;

    private String mensagem;



    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}