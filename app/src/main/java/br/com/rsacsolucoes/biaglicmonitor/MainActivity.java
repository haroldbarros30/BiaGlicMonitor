package br.com.rsacsolucoes.biaglicmonitor;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.rsacsolucoes.biaglicmonitor.Model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static android.R.attr.name;
import static android.R.attr.reqFiveWayNav;
import static br.com.rsacsolucoes.biaglicmonitor.R.id.MainBtnAdd;
import static br.com.rsacsolucoes.biaglicmonitor.R.id.MainBtnCadastro;
import static br.com.rsacsolucoes.biaglicmonitor.R.id.MainBtnCompartilhar;
import static br.com.rsacsolucoes.biaglicmonitor.R.id.MainBtnConfigurar;

public class MainActivity extends AppCompatActivity {

    private Button MainBtnAdd;
    private Button MainBtnAcompanhar;
    private Button MainBtnCompartilhar;
    private Button MainBtnCadastro;
    private Button MainBtnConfigurar;
    private Button MainBtnMinhasMedicoes;
    private ImageView ImgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setActions();

        //define o usuario logado para os testes
        UsuarioService.setUsuarioLogado(new Usuario("Clo","Clo@teste.com.br"));


        Usuario usuario = UsuarioService.getUsuarioLogado();
        usuario.AddMedicao(new Medicao("20170101","00:01:00",100));
        usuario.AddMedicao(new Medicao("20170101","00:02:00",200));
        usuario.AddMedicao(new Medicao("20170101","00:03:00",300));
        usuario.AddMedicao(new Medicao("20170101","00:04:00",400));

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuario");
        myRef.setValue(usuario);

    }

    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {

        MainBtnAdd = (Button) findViewById(R.id.MainBtnAdd);
        MainBtnCompartilhar = (Button) findViewById(R.id.MainBtnCompartilhar);
        MainBtnAcompanhar = (Button) findViewById(R.id.MainBtnAcompanhar);
        MainBtnCadastro = (Button) findViewById(R.id.MainBtnCadastro);
        MainBtnConfigurar = (Button) findViewById(R.id.MainBtnConfigurar);
        MainBtnMinhasMedicoes = (Button) findViewById(R.id.MainBtnMinhasMedicoes);
        ImgLogo = (ImageView)findViewById(R.id.ImgLogo);

    }


    /**
     * Define as acoes e os listeners para os eventos dos objetos
     */
    private void setActions() {

        MainBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddMedicaoActivity(ImgLogo);
            }
        });
        MainBtnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCompartilharActivity();
            }
        });
        MainBtnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCadastroActivity();
            }
        });
        MainBtnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfigActivity();
            }
        });
        MainBtnAcompanhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcompActivity();
            }
        });
        MainBtnMinhasMedicoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcompDetalheActivity();
            }
        });
        ImgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddMedicaoActivity(v);
            }
        });
    }

    /**
     * Chama a tela para adicionar medicao
     * @param view View atual
     */
    private void goToAddMedicaoActivity(View view){

        Intent intent = new Intent(MainActivity.this, AddMedicaoActivity.class);

       //Verifica se a versao do android suporta animacao do material design
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setExitTransition(new Explode());
            //getWindow().setEnterTransition(new Explode());
            //getWindow().setEnterTransition(new ChangeBounds());

            ActivityOptions options = view != null?
                    ActivityOptions.makeSceneTransitionAnimation(this,view,"TrImgLogo"):
                    ActivityOptions.makeSceneTransitionAnimation(this);

            startActivity(intent,options.toBundle());

        }
        else{ //se nao suporta animacao continua com o fluxo normal do processo

            startActivity(intent);
        }

    }

    public void goToCompartilharActivity() {
        Intent intent = new Intent(MainActivity.this, CompartilharActivity.class);
        startActivity(intent);
    }

    private void goToCadastroActivity() {
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void goToConfigActivity() {
        Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
        startActivity(intent);
    }

    private void goToAcompActivity() {
        Intent intent = new Intent(MainActivity.this, AcompActivity.class);
        startActivity(intent);
    }

    private void goToAcompDetalheActivity() {
        Intent intent = new Intent(MainActivity.this, AcompDetalheActivity.class);
        startActivity(intent);
    }




}
