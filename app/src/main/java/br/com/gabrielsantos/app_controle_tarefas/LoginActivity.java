package br.com.gabrielsantos.app_controle_tarefas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.gabrielsantos.app_controle_tarefas.entidade.Usuario;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioConsultaLogin;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioRetorno;
import br.com.gabrielsantos.app_controle_tarefas.servico.Resposta;
import br.com.gabrielsantos.app_controle_tarefas.servico.RetrofitServico;
import br.com.gabrielsantos.app_controle_tarefas.servico.UsuarioServico;
import br.com.gabrielsantos.app_controle_tarefas.util.ControlaAlerta;
import br.com.gabrielsantos.app_controle_tarefas.util.ControlaTelaLoad;
import br.com.gabrielsantos.app_controle_tarefas.util.EscondeActionBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewClicarIrTelaCadastrarse;
    private TextView textViewIrTelaRecuperarSenha;
    private Context context;
    private LinearLayout alerta;
    private TextView mensagemAlerta;
    private TextView textViewFeedbackEmail;
    private TextView textViewFeedbackSenha;
    private ControlaAlerta controlaAlerta;
    private ControlaTelaLoad controlaTelaLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.context = LoginActivity.this;
        // escondendo a ActionBar
        ActionBar actionBar = getSupportActionBar();
        EscondeActionBar.esconderActionBar(actionBar);
        // mapeando os elementos de tela
        this.mapearElementosDeTela();
        // mapeando eventos de click
        this.buttonLogin.setOnClickListener(this);
        this.textViewClicarIrTelaCadastrarse.setOnClickListener(this);
        this.textViewIrTelaRecuperarSenha.setOnClickListener(this);
        this.controlaTelaLoad = new ControlaTelaLoad(this.context, getLayoutInflater());
        this.controlaAlerta = new ControlaAlerta(this.alerta, this.mensagemAlerta);
    }

    private void mapearElementosDeTela() {
        this.editTextEmail = findViewById(R.id.edit_email);
        this.editTextSenha = findViewById(R.id.edit_senha);
        this.buttonLogin = findViewById(R.id.button_login);
        this.textViewClicarIrTelaCadastrarse = findViewById(R.id.text_ir_tela_cadastrarse);
        this.textViewIrTelaRecuperarSenha = findViewById(R.id.text_ir_tela_recuperar_senha);
        this.textViewFeedbackEmail = findViewById(R.id.txt_feedback_email);
        this.textViewFeedbackSenha = findViewById(R.id.txt_feedback_senha);
        this.alerta = findViewById(R.id.alerta);
        this.mensagemAlerta = findViewById(R.id.text_mensagem_alerta);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_login:
                // clicou no botão de login
                boolean formularioValido = true;
                String email = editTextEmail.getText().toString().trim();
                String senha = editTextSenha.getText().toString().trim();

                // validar o formulário de login
                if (email.equals("")) {
                    formularioValido = false;
                    textViewFeedbackEmail.setVisibility(View.VISIBLE);
                } else {
                    textViewFeedbackEmail.setVisibility(View.GONE);
                }

                if (senha.equals("")) {
                    formularioValido = false;
                    textViewFeedbackSenha.setVisibility(View.VISIBLE);
                } else {
                    textViewFeedbackSenha.setVisibility(View.GONE);
                }

                if (formularioValido) {
                    // formulário válido

                    this.controlaTelaLoad.apresentar();
                    try {
                        UsuarioServico usuarioServico = RetrofitServico
                                .obterServico()
                                .create(UsuarioServico.class);
                        Usuario usuario = new Usuario();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        Call<Resposta<UsuarioRetorno>> retornoRequisicao = usuarioServico
                                .buscarUsuarioPeloEmailESenha(usuario);
                        retornoRequisicao.enqueue(new Callback<Resposta<UsuarioRetorno>>() {
                            @Override
                            public void onResponse(Call<Resposta<UsuarioRetorno>> call, Response<Resposta<UsuarioRetorno>> response) {
                                int codigoHttp = response.code();

                                controlaTelaLoad.esconder();

                                if (codigoHttp == 200) {
                                    Resposta<UsuarioRetorno> resposta = response.body();
                                    String msg = resposta.getMsg();

                                    if (msg.equals("Usuário encontrado com sucesso!")) {
                                        // Log.i("OK", "Login efetivado com sucesso!");
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.putExtra("id_usuario_logado", resposta.getDados().getId());
                                        intent.putExtra("nome_usuario_logado", resposta.getDados().getNome());
                                        startActivity(intent);
                                        finishAffinity();
                                    } else {
                                        controlaAlerta.apresentarAlertaErro(msg);
                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<Resposta<UsuarioRetorno>> call, Throwable t) {
                                controlaTelaLoad.esconder();
                                Log.i("Erro", t.getMessage());
                                controlaAlerta.apresentarAlertaErro("Ocorreu um erro ao tentar-se realizar o login!");
                            }
                        });
                    } catch (Exception e) {
                        this.controlaTelaLoad.esconder();
                        Log.i("Erro", e.getMessage());
                        this.controlaAlerta.apresentarAlertaErro("Ocorreu um erro ao tentar-se realizar o login!");
                    }

                }

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