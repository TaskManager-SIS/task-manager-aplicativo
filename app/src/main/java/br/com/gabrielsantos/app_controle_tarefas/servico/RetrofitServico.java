package br.com.gabrielsantos.app_controle_tarefas.servico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServico {

    private static final String URL_BASE = "https://www.taskmanager.targetbr.biz/index.php/";

    public static Retrofit obterServico() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
