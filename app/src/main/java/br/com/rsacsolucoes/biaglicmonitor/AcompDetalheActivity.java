package br.com.rsacsolucoes.biaglicmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.Adapter.MedicaoAdapter;
import br.com.rsacsolucoes.biaglicmonitor.Adapter.UsuarioAdapter;
import br.com.rsacsolucoes.biaglicmonitor.Model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static br.com.rsacsolucoes.biaglicmonitor.R.id.AcompRcvLista;

public class AcompDetalheActivity extends AppCompatActivity {

    private TextView AcompDetalheTxtNome;
    private RecyclerView AcompDetalheRcvLista;

    List<Medicao> medicoes;
    MedicaoAdapter medicaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acomp_detalhe);

        findViews();
        
        //busca o usuario passado pelo parametros
        //// TODO: 25/05/17 verificar se foi passado alguma informacao afim de evitar erros 
        Usuario usuario = (Usuario) getIntent().getBundleExtra("extra").getSerializable("usuario");

        //verifica se foi passado alguma medicao
        if (usuario != null) {

            AcompDetalheTxtNome.setText(usuario.getNome());

            medicoes = usuario.getMedicoes(); //MedicoesService.GetMedicoes();

            //verifica se existe medicao
            if (medicoes != null) {
                //cria o adapter para
                medicaoAdapter = new MedicaoAdapter(medicoes, this);

                AcompDetalheRcvLista.setAdapter(medicaoAdapter);

                //define o layout a ser exibido na lista
                LinearLayoutManager layout = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);

                AcompDetalheRcvLista.setLayoutManager(layout);
            }
        }

        setActions();
    }


    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {
        AcompDetalheTxtNome = (TextView)findViewById(R.id.AcompDetalheTxtNome);
        AcompDetalheRcvLista =  (RecyclerView)findViewById(R.id.AcompDetalheRcvLista);
    }

    /**
     * Define as acoes e os listeners para os eventos dos objetos
     */
    private void setActions() {

    }

}
