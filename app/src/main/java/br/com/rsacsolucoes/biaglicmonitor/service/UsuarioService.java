package br.com.rsacsolucoes.biaglicmonitor.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.view.MainActivity;
import br.com.rsacsolucoes.biaglicmonitor.model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.R;

import static br.com.rsacsolucoes.biaglicmonitor.model.Constants.TAG_medicoes;
import static br.com.rsacsolucoes.biaglicmonitor.model.Constants.TAG_usuario;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel por retornar os dados de um usuario e suas medicoes
 * Apenas Dados estaticos serao retornados, depois, deve ser adicionar a consulta ao firebase
 */
public class UsuarioService {

    private static Usuario UsuarioLogado;

    /**
     * Retorna o usuario logado/cadastrado
     * @return
     */
    public static Usuario getUsuarioLogado() {

        //verifica se a variavel de usuario cadastrada ja foi carregada
        if (UsuarioLogado == null) {

            //guarda as configuracoes no device local
            SharedPreferences sharedPref = MainActivity.MainContext.getSharedPreferences(
                    MainActivity.MainContext.getString(R.string.preference_usuario_key), Context.MODE_PRIVATE);


            //cria o objeto de configuracao
            UsuarioLogado = new Usuario();

            //pega o valor armazenado no celular
            UsuarioLogado.setId(sharedPref.getString(MainActivity.MainContext.getString(R.string.preference_usuario_id), MainActivity.android_id));
            UsuarioLogado.setNome(sharedPref.getString(MainActivity.MainContext.getString(R.string.preference_usuario_nome), ""));
            UsuarioLogado.setEmail(sharedPref.getString(MainActivity.MainContext.getString(R.string.preference_usuario_email), ""));
            UsuarioLogado.setTelefone(sharedPref.getString(MainActivity.MainContext.getString(R.string.preference_usuario_telefone), ""));

        }

        return UsuarioLogado;
    }

    /**
     * Grava o usuario logado, nas preferencias do sistema e tambem no banco firebase
     * @param usuarioLogado
     * @return
     */
    public static boolean setUsuarioLogado(Usuario usuarioLogado) {

        //valida se os dados do usuario foram preenchidos corretamente
        if (usuarioLogado == null) {
            throw new RuntimeException("Usuario não informado");
        }

        //verifica se o email foi informado
        if (usuarioLogado.getNome().equals("")) {
            throw new RuntimeException("Nome Obrigatorio");
        }


        //verifica se o email foi informado
        if (usuarioLogado.getEmail().equals("")) {
            throw new RuntimeException("E-mail Obrigatorio");
        }

        //// TODO: 27/05/17 validar se o email esta em um formato valido e se o mesmo ja nao esta cadastrado no firebase


        //// TODO: 27/05/17 mudar para que a chave do usuario seja o token do mesmo cadastrado no firebase
        if (usuarioLogado.getId().equals(""))
            usuarioLogado.setId(MainActivity.android_id);

        UsuarioLogado = usuarioLogado;

        //guarda as configuracoes no device local
        SharedPreferences sharedPref = MainActivity.MainContext.getSharedPreferences(
                MainActivity.MainContext.getString(R.string.preference_usuario_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MainActivity.MainContext.getString(R.string.preference_usuario_id), UsuarioLogado.getId());
        editor.putString(MainActivity.MainContext.getString(R.string.preference_usuario_nome), UsuarioLogado.getNome());
        editor.putString(MainActivity.MainContext.getString(R.string.preference_usuario_email), UsuarioLogado.getEmail());
        editor.putString(MainActivity.MainContext.getString(R.string.preference_usuario_telefone), UsuarioLogado.getTelefone());

        //salva as preferencias
        editor.commit();

        //ace o banco de dados
        DatabaseReference UsuarioRef = FirebaseDatabase.getInstance().getReference(usuarioLogado.getId()).child(TAG_usuario);

        //grava o usuario no banco de dados
        UsuarioRef.setValue(usuarioLogado);

        //retorna que o cadastro foi realizado com  sucesso
        return true;

    }

    /**
     * Grava uma nova medicao no firebase ao usuario logado
     * @param medicao medicao a ser gravado no firebase para o usuario logado
     */
    public static void AddMedicao(Medicao medicao) {

        //adiciona a medicao ao usuario logado
        getUsuarioLogado().AddMedicao(medicao);

        //adiciona a nova medicao a base de dados do firebase

        //pega o nó da medicao
        DatabaseReference MedicaoRef = FirebaseDatabase.getInstance().getReference(getUsuarioLogado().getId())
                .child(TAG_medicoes)
                .child(medicao.getId());

        //guarda no banco de dados do firebase
        MedicaoRef.setValue(medicao);


    }
}
