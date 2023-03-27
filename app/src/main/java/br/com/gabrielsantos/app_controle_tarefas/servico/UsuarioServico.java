package br.com.gabrielsantos.app_controle_tarefas.servico;

import br.com.gabrielsantos.app_controle_tarefas.entidade.Usuario;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioRetorno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioServico {

    @POST("usuario")
    Call<Resposta<UsuarioRetorno>> cadastrarUsuario(@Body Usuario usuarioCadastrar);
}
