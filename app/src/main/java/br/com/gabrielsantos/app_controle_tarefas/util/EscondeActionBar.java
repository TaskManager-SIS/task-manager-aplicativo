package br.com.gabrielsantos.app_controle_tarefas.util;

import androidx.appcompat.app.ActionBar;

public class EscondeActionBar {

    public static void esconderActionBar(ActionBar actionBar) {

        if (actionBar != null) {
            actionBar.hide();
        }

    }
}
