package br.com.gabrielsantos.app_controle_tarefas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.gabrielsantos.app_controle_tarefas.util.EscondeActionBar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewClicarIrTelaCadastrarse;
    private TextView textViewIrTelaRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // escondendo a ActionBar
        ActionBar actionBar = getSupportActionBar();
        EscondeActionBar.esconderActionBar(actionBar);
        // mapeando os elementos de tela
        this.mapearElementosDeTela();
        // mapeando eventos de click
        this.buttonLogin.setOnClickListener(this);
        this.textViewClicarIrTelaCadastrarse.setOnClickListener(this);
        this.textViewIrTelaRecuperarSenha.setOnClickListener(this);
    }

    private void mapearElementosDeTela() {
        this.editTextEmail = findViewById(R.id.edit_email);
        this.editTextSenha = findViewById(R.id.edit_senha);
        this.buttonLogin = findViewById(R.id.button_login);
        this.textViewClicarIrTelaCadastrarse = findViewById(R.id.text_ir_tela_cadastrarse);
        this.textViewIrTelaRecuperarSenha = findViewById(R.id.text_ir_tela_recuperar_senha);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_login:
                // clicou no botão de login
                break;
            case R.id.text_ir_tela_cadastrarse:
                // clicou em cadastra-se
                Intent intent = new Intent(getApplicationContext(), UsuarioSeCadastrarActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.text_ir_tela_recuperar_senha:
                // clicou em recuperar senha
                break;
            default:
        }

    }
}