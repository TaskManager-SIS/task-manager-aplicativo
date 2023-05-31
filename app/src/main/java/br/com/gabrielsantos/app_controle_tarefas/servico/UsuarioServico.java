package br.com.gabrielsantos.app_controle_tarefas.servico;

import br.com.gabrielsantos.app_controle_tarefas.entidade.Usuario;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioConsultaLogin;
import br.com.gabrielsantos.app_controle_tarefas.entidade.UsuarioRetorno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioServico {

    @POST("usuario")
    Call<Resposta<UsuarioRetorno>> cadastrarUsuario(@Body Usuario usuarioCadastrar);

    @POST("usuario/buscar-pelo-email-e-senha")
    Call<Resposta<UsuarioRetorno>> buscarUsuarioPeloEmailESenha(@Body Usuario usuario);
}
