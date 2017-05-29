package br.com.rsacsolucoes.biaglicmonitor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.R;
import br.com.rsacsolucoes.biaglicmonitor.adapter.MedicaoAdapter;
import br.com.rsacsolucoes.biaglicmonitor.model.Constants;
import br.com.rsacsolucoes.biaglicmonitor.model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.service.UsuarioService;

public class AcompDetalheActivity extends AppCompatActivity {

    private TextView AcompDetalheTxtNome;
    private RecyclerView AcompDetalheRcvLista;
    private Usuario usuarioSelecionado;

    private DatabaseReference databaseReference;

    private List<Medicao> medicoes;
    private MedicaoAdapter medicaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acomp_detalhe);

        findViews();


        //pega o usuario logado por padrao
        usuarioSelecionado = UsuarioService.getUsuarioLogado();

        //verifica se foi passado alguma configuracao de usuario para filtro
        if(getIntent().hasExtra("extra")){
            if(!getIntent().getBundleExtra("extra").getSerializable("usuario").equals(null)){
                //busca o usuario passado pelo parametros
                usuarioSelecionado = (Usuario) getIntent().getBundleExtra("extra").getSerializable("usuario");

            }
        }

        //verifica se foi passado alguma medicao
        if (usuarioSelecionado != null) {

            //preenche o nome do usuario
            AcompDetalheTxtNome.setText(usuarioSelecionado.getNome());

            //pega a referencia do usuario selecionado e suas medicoes
            databaseReference = FirebaseDatabase.getInstance().getReference(usuarioSelecionado.getId()).child(Constants.TAG_medicoes);

            //busca apenas as ultimas 10 medicoes adicionadas pelo usuario
            Query medicaoQuery = databaseReference.limitToLast(10);

            //adiciona o listener para monitorar a base de dados em tempo real
            medicaoQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //pega as medicores realizadas
                    getAllMedicoes(dataSnapshot);
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //pega as medicores realizadas
                    getAllMedicoes(dataSnapshot);
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        }


        setActions();
    }


    private void getAllMedicoes(DataSnapshot dataSnapshot){

        //converte os dados
        Medicao medicaoSnapShot = dataSnapshot.getValue(Medicao.class);

        //verifica se o array foi criado
        if (medicoes == null)
            medicoes = new ArrayList<Medicao>();


        Boolean MedicaoEncontrada = false;

        //verifica se o registro ja foi adicionado na lista, atualizando a lista informado
        for(int index=0; index<medicoes.size(); index++){
            //compara os IDs
            if (medicoes.get(index).getId().equals(medicaoSnapShot.getId())){
                //se foi encontrado a medicao atualiza os dados e sai do processo
                medicoes.set(index,medicaoSnapShot);

                //marca que foi encontrado
                MedicaoEncontrada = true;

                //sai do loop
                break;
            }

        }

        //adiciona a medicao a lista
        if (!MedicaoEncontrada)
            medicoes.add(medicaoSnapShot);


        //atualiza o adapter
        medicaoAdapter = new MedicaoAdapter(medicoes, this);
        //seta o atapter a ListView
        AcompDetalheRcvLista.setAdapter(medicaoAdapter);

        //define o layout a ser exibido na lista
        LinearLayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        //orderna a lista do ultimo registro para o primeiro
        layout.setReverseLayout(true);
        layout.setStackFromEnd(true);

        AcompDetalheRcvLista.setLayoutManager(layout);

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
