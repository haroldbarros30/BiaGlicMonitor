package br.com.rsacsolucoes.biaglicmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.Adapter.UsuarioAdapter;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static android.media.CamcorderProfile.get;

/**
 * Tela de Acompanhamento de Paciente, tera a lista de todos os
 */
public class AcompActivity extends AppCompatActivity {


    RecyclerView AcompRcvLista;
    List<Usuario> usuarios;
    UsuarioAdapter usuarioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acomp);

        findViews();

        //Busca a lista de pessoas que o atual usuario faz acompanhamento
        //// TODO: 20/05/17 Adicionar o usuario logado/cadastrado no sistema
        usuarios = UsuarioService.GetUsuarios();

        //cria o adapter para
        usuarioAdapter = new UsuarioAdapter(usuarios, this);

        AcompRcvLista.setAdapter(usuarioAdapter);

        //define o layout a ser exibido na lista
        LinearLayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        AcompRcvLista.setLayoutManager(layout);

        setActions();

    }

    /**
     * Vincula as variaveis com as views do layout
     */
    private void findViews() {
        AcompRcvLista =  (RecyclerView)findViewById(R.id.AcompRcvLista);
    }

    /**
     * atribui as acoes as views
     */
    private void setActions() {

        /**
         * define o evento click da lista
         */
        usuarioAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = AcompRcvLista.getChildLayoutPosition(view);
                Bundle bundle = new Bundle();
               //guarda a pessoa selecionada para ser recuperada posteriormente
                bundle.putSerializable("usuario", usuarios.get(position));

                goToAcompDetalhe(bundle);
            }
        });

    }


    /**
     * Chama a tela de acompanhamento de uma pessoa
     * @param bundle deve ser passado a pessoa selecionada para filtro posterior
     */
    private void goToAcompDetalhe(Bundle bundle){
        Intent intent = new Intent(AcompActivity.this, AcompDetalheActivity.class);
        if (bundle != null) {
            intent.putExtra("extra", bundle);
        }

        startActivity(intent);

    }


}
