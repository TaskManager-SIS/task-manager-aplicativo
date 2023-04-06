package br.com.gabrielsantos.app_controle_tarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gabrielsantos.app_controle_tarefas.entidade.Usuario;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioRetorno;
import br.com.gabrielsantos.app_controle_tarefas.servico.Resposta;
import br.com.gabrielsantos.app_controle_tarefas.servico.RetrofitServico;
import br.com.gabrielsantos.app_controle_tarefas.servico.UsuarioServico;
import br.com.gabrielsantos.app_controle_tarefas.util.ControlaAlerta;
import br.com.gabrielsantos.app_controle_tarefas.util.ControlaTelaLoad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioSeCadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCadastrarse;
    private List<EditText> camposFormulario;
    private List<TextView> feedbacksCamposFormulario;
    private Context contextoApp;
    private ControlaTelaLoad controlaTelaLoad;
    private LinearLayout alerta;
    private TextView mensagemAlerta;
    private ControlaAlerta controlaAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_se_cadastrar);
        // mapeando o contexto
        this.contextoApp = UsuarioSeCadastrarActivity.this;
        // mapeando os elementos de tela
        this.mapearElementosTela();
        // mapeando os eventos dos elementos de tela
        this.buttonCadastrarse.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        this.controlaTelaLoad = new ControlaTelaLoad(this.contextoApp, getLayoutInflater());
        this.controlaAlerta = new ControlaAlerta(this.alerta, this.mensagemAlerta);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // método invocado quando o usuário clica no botão de voltar na ActionBar
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        // método que é invocado quando o usuário clica no botão de voltar padrão do celular
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void mapearElementosTela() {
        this.buttonCadastrarse = findViewById(R.id.button_usuario_se_cadastrar);
        this.camposFormulario = new ArrayList<>();
        this.feedbacksCamposFormulario = new ArrayList<>();
        // adicionando campos a lista de campos
        this.camposFormulario.add(findViewById(R.id.edit_nome));
        this.camposFormulario.add(findViewById(R.id.edit_email));
        this.camposFormulario.add(findViewById(R.id.edit_senha));
        this.camposFormulario.add(findViewById(R.id.edit_senha_confirmacao));
        // adicionando a lista de feedbacks os feedbacks
        this.feedbacksCamposFormulario.add(findViewById(R.id.text_feedback_nome));
        this.feedbacksCamposFormulario.add(findViewById(R.id.text_feedback_email));
        this.feedbacksCamposFormulario.add(findViewById(R.id.text_feedback_senha));
        this.feedbacksCamposFormulario.add(findViewById(R.id.text_feedback_senha_confirmar));
        this.alerta = findViewById(R.id.alerta);
        this.mensagemAlerta = findViewById(R.id.text_mensagem_alerta);
    }

    // método para limpar os campos do formulário de cadastrado de usuário
    private void limparFormulario() {
        this.camposFormulario.get(0).setText("");
        this.camposFormulario.get(1).setText("");
        this.camposFormulario.get(2).setText("");
        this.camposFormulario.get(3).setText("");
    }

    // método para fechar o teclado ao clicar no botão
    private void fecharTecladoAoClicarNoBotao() {

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_usuario_se_cadastrar) {
            // fechando teclado
            this.fecharTecladoAoClicarNoBotao();
            String nome = this.camposFormulario.get(0).getText().toString();
            String email = this.camposFormulario.get(1).getText().toString();
            String senha = this.camposFormulario.get(2).getText().toString();
            String senhaConfirmacao = this.camposFormulario.get(3).getText().toString();
            Boolean dadosUsuarioSaoValidos = true;

            if (nome.trim().equals("")) {
                dadosUsuarioSaoValidos = false;
                this.feedbacksCamposFormulario.get(0).setVisibility(View.VISIBLE);
            } else {
                this.feedbacksCamposFormulario.get(0).setVisibility(View.GONE);
            }

            if (email.trim().equals("")) {
                dadosUsuarioSaoValidos = false;
                this.feedbacksCamposFormulario.get(1).setVisibility(View.VISIBLE);
            } else {
                this.feedbacksCamposFormulario.get(1).setVisibility(View.GONE);
            }

            if (senha.trim().equals("")) {
                dadosUsuarioSaoValidos = false;
                this.feedbacksCamposFormulario.get(2).setVisibility(View.VISIBLE);
            } else {
                this.feedbacksCamposFormulario.get(2).setVisibility(View.GONE);
            }

            if (senhaConfirmacao.trim().equals("")) {
                dadosUsuarioSaoValidos = false;
                this.feedbacksCamposFormulario.get(3).setVisibility(View.VISIBLE);
                this.feedbacksCamposFormulario.get(3).setText("Digite novamente sua senha");
            } else if (!senhaConfirmacao.trim().equals(senha.trim())) {
                dadosUsuarioSaoValidos = false;
                this.feedbacksCamposFormulario.get(3).setVisibility(View.VISIBLE);
                this.feedbacksCamposFormulario.get(3).setText("A senha e a senha de confirmação são diferentes!");
            } else {
                this.feedbacksCamposFormulario.get(3).setVisibility(View.GONE);
            }

            if (dadosUsuarioSaoValidos) {
                this.controlaTelaLoad.apresentar();
                // os dados do usuário são válidos
                try {
                    UsuarioServico usuarioServico = RetrofitServico
                            .obterServico()
                            .create(UsuarioServico.class);
                    Usuario usuarioCadastrar = new Usuario();
                    usuarioCadastrar.setNome(nome);
                    usuarioCadastrar.setEmail(email.trim());
                    usuarioCadastrar.setSenha(senha.trim());
                    Call<Resposta<UsuarioRetorno>> retornoRequisicao = usuarioServico.cadastrarUsuario(usuarioCadastrar);
                    retornoRequisicao.enqueue(new Callback<Resposta<UsuarioRetorno>>() {
                        @Override
                        public void onResponse(@NonNull Call<Resposta<UsuarioRetorno>> call, @NonNull Response<Resposta<UsuarioRetorno>> response) {
                            // requisição foi processada com sucesso
                            Resposta<UsuarioRetorno> usuarioRetornoResposta = response.body();
                            int codigoHttp = response.code();

                            if (codigoHttp == 201) {
                                // usuário cadastrado com sucesso!
                                controlaAlerta.apresentarAlertaSucesso("Cadastro realizado com sucesso!");
                                limparFormulario();
                            } else if (codigoHttp == 200) {
                                // ocorreu algum erro por ação do usuário
                                controlaAlerta.apresentarAlertaErro("Erro! Informe dados válidos!");
                            } else {
                                // erro 500 -> falha na requisição!
                                controlaAlerta.apresentarAlertaErro("Ocorreu um erro, tente novamente em instantes!");
                            }

                            controlaTelaLoad.esconder();
                        }

                        @Override
                        public void onFailure(Call<Resposta<UsuarioRetorno>> call, Throwable t) {
                            // ocorreu um erro ao processar a requisição
                            controlaAlerta.apresentarAlertaErro("Ocorreu um erro ao tentar-se realizar seu cadastro, por gentileza, tente novamente em instantes!");
                            Log.i("ERRO", "Ocorreu o seguinte erro ao tentar-se processar essa requisição: " + t.getMessage());
                            controlaTelaLoad.esconder();
                        }
                    });
                } catch (Exception e) {
                    this.controlaAlerta.apresentarAlertaErro("Ocorreu um erro ao tentar-se realizar seu cadastro, por gentileza, tente novamente em instantes!");
                    Log.i("ERRO", "Ocorreu o seguinte erro: " + e.getMessage());
                    this.controlaTelaLoad.esconder();
                }
            }

        }

    }
}