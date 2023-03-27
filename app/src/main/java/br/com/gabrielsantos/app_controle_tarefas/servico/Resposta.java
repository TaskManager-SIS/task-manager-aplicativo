package br.com.gabrielsantos.app_controle_tarefas.servico;

import com.google.gson.annotations.SerializedName;

public class Resposta<T> {

    @SerializedName("msg")
    private String msg;
    @SerializedName("dados")
    private T dados;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {

        return this.msg;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public T getDados() {

        return this.dados;
    }
}
