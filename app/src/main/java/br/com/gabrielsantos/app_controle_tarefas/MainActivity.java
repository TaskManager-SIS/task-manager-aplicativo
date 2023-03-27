package br.com.gabrielsantos.app_controle_tarefas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.gabrielsantos.app_controle_tarefas.util.EscondeActionBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // esconder ActionBar
        ActionBar actionBar = getSupportActionBar();
        EscondeActionBar.esconderActionBar(actionBar);
        // redirecionar para a tela de login após 4 segundos
        this.redirecionarTelaLogin();
    }

    private void redirecionarTelaLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        }, 4000);
    }
}