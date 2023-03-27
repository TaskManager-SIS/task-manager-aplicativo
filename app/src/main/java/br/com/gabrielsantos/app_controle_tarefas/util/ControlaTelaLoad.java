package br.com.gabrielsantos.app_controle_tarefas.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import br.com.gabrielsantos.app_controle_tarefas.R;

public class ControlaTelaLoad {

    private Context context;
    private LayoutInflater layoutInflater;
    private AlertDialog alertDialog;

    public ControlaTelaLoad(Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.criarTelaLoad();
    }

    private void criarTelaLoad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setCancelable(false);
        View viewTelaLoad = this.layoutInflater.inflate(R.layout.layout_tela_load, null);
        builder.setView(viewTelaLoad);
        this.alertDialog = builder.create();
    }

    public void apresentar() {
        this.alertDialog.show();
    }

    public void esconder() {
        this.alertDialog.dismiss();
    }
}
