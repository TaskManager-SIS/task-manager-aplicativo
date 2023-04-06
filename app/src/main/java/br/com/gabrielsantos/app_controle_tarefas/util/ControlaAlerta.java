package br.com.gabrielsantos.app_controle_tarefas.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.gabrielsantos.app_controle_tarefas.R;

public class ControlaAlerta {

    private LinearLayout alerta;
    private String tipoAlerta;
    private TextView mensagemAlerta;

    public ControlaAlerta(LinearLayout alerta, TextView mensagemAlerta) {
        this.alerta = alerta;
        this.mensagemAlerta = mensagemAlerta;
    }

    @SuppressLint("ResourceAsColor")
    public void apresentarAlertaSucesso(String mensagemSucesso) {
        this.alerta.setVisibility(View.VISIBLE);
        this.alerta.setBackgroundColor(Color.GREEN);
        this.mensagemAlerta.setText(mensagemSucesso);
    }

    @SuppressLint("ResourceAsColor")
    public void apresentarAlertaErro(String mensagemErro) {
        this.alerta.setVisibility(View.VISIBLE);
        this.alerta.setBackgroundColor(Color.RED);
        this.mensagemAlerta.setText(mensagemErro);
    }
}
