package br.com.gabrielsantos.app_controle_tarefas.entidade;

import com.google.gson.annotations.SerializedName;

public class UsuarioRetorno extends Usuario {

    @SerializedName("id")
    private int id;
    @SerializedName("ativo")
    private boolean ativo;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return this.id;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean getAtivo() {

        return this.ativo;
    }
}
