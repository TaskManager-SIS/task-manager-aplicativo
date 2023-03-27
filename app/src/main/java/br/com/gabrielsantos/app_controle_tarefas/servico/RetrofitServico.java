package br.com.gabrielsantos.app_controle_tarefas.servico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServico {

    private static final String URL_BASE = "http://192.168.0.103:8080/index.php/";

    public static Retrofit obterServico() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
