package br.com.gabrielsantos.app_controle_tarefas.entidade;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("nome")
    private String nome;
    @SerializedName("email")
    private String email;
    @SerializedName("senha")
    private String senha;

    public String getNome() {

        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {

        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {

        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
