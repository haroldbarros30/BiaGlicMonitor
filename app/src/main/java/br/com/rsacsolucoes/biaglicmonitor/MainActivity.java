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

import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static android.R.attr.name;
import static android.R.attr.reqFiveWayNav;

public class MainActivity extends AppCompatActivity {

    private Button BtnAdd;
    private Button BtnAcompanhar;
    private Button BtnCompartilhar;
    private Button BtnCadastro;
    private Button BtnConfigurar;
    private ImageView ImgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setActions();

        //define o usuario logado para os testes
        UsuarioService.setUsuarioLogado(new Usuario("Clo","Clo@teste.com.br"));
    }

    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {

        BtnAdd = (Button) findViewById(R.id.BtnAdd);
        BtnCompartilhar = (Button) findViewById(R.id.BtnCompartilhar);
        BtnAcompanhar = (Button) findViewById(R.id.BtnAcompanhar);
        BtnCadastro = (Button) findViewById(R.id.BtnCadastro);
        BtnConfigurar = (Button) findViewById(R.id.BtnConfigurar);
        ImgLogo = (ImageView)findViewById(R.id.ImgLogo);

    }


    /**
     * Define as acoes e os listeners para os eventos dos objetos
     */
    private void setActions() {

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddMedicaoActivity(ImgLogo);
            }
        });
        BtnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCompartilharActivity();
            }

        });
        BtnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCadastroActivity();
            }
        });
        BtnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfigActivity();
            }
        });
        BtnAcompanhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcompActivity();
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




}
