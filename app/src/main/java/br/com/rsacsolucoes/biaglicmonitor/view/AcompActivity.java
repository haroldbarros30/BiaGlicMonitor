package br.com.rsacsolucoes.biaglicmonitor.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.R;
import br.com.rsacsolucoes.biaglicmonitor.adapter.UsuarioAdapter;
import br.com.rsacsolucoes.biaglicmonitor.model.Constants;
import br.com.rsacsolucoes.biaglicmonitor.model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.service.UsuarioService;

import static android.media.CamcorderProfile.get;

/**
 * Tela de Acompanhamento de Paciente, tera a lista de todos os
 */
public class AcompActivity extends AppCompatActivity {


    private RecyclerView AcompRcvLista;
    private List<Usuario> usuarios;
    private Usuario usuarioSnapshot;
    private UsuarioAdapter usuarioAdapter;

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acomp);

        findViews();

        //pega a referencia de todos os usuarios
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //adiciona o listener para monitorar a base de dados em tempo real
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllUsuarios(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
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

    /**
     * Converte os dados do firebase nna lista de usuarios
     * @param dataSnapshot
     */
    private void getAllUsuarios(DataSnapshot dataSnapshot) {

        //verifica se o array foi criado
        if (usuarios == null)
            usuarios = new ArrayList<Usuario>();

        //faz um for em todos os registros encontrados
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

            //verifica se o registro e do tipo usuario
            if (singleSnapshot.getKey().equals(Constants.TAG_usuario)) {
                //converte os dados
                usuarioSnapshot = singleSnapshot.getValue(Usuario.class);

                //busca a ultima medicao do usuario para ser exibida na lista
                if (dataSnapshot.hasChild(Constants.TAG_medicoes)) {

                    //pega o total de medicores
                    long totalMedicoes = dataSnapshot.child(Constants.TAG_medicoes).getChildrenCount();

                    Medicao ultimaMedicao = new Medicao();

                    Iterable<DataSnapshot> MedicoresSnapshot = dataSnapshot.child(Constants.TAG_medicoes).getChildren();

                    //percorre todas as medicoes para pegar a ultima
                    for (long index=0;index<totalMedicoes;index++) {

                        if (MedicoresSnapshot.iterator().hasNext())
                            ultimaMedicao = MedicoresSnapshot.iterator().next().getValue(Medicao.class);

                    }

                    if (ultimaMedicao != null && !ultimaMedicao.getId().equals(""))
                        usuarioSnapshot.AddMedicao(ultimaMedicao);



                }

                //adiciona o usuario caso nao tenha sido identificado nenhuma medicao
                usuarios.add(usuarioSnapshot);
            }
        }

        //cria o adapter para
        usuarioAdapter = new UsuarioAdapter(usuarios, this);

        AcompRcvLista.setAdapter(usuarioAdapter);

        //define o layout a ser exibido na lista
        LinearLayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        AcompRcvLista.setLayoutManager(layout);


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
     * Converte SnapShot para medicao
     * @param dataSnapshot
     * @return
     */
    private Medicao getUltimaMedicao(DataSnapshot dataSnapshot){

        //converte os dados
        Medicao medicaoSnapShot = dataSnapshot.getValue(Medicao.class);
        return medicaoSnapShot;

    }

    /**
     * Vincula as variaveis com as views do layout
     */
    private void findViews() {
        AcompRcvLista =  (RecyclerView)findViewById(R.id.AcompRcvLista);
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
