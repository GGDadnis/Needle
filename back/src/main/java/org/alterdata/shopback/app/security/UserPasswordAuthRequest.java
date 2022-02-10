package org.alterdata.shopback.app.security;

public class UserPasswordAuthRequest {

    private String nome;
    private String senha;

    public UserPasswordAuthRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
