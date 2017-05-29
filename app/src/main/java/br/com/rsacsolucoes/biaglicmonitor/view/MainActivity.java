package br.com.rsacsolucoes.biaglicmonitor.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.rsacsolucoes.biaglicmonitor.R;
import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.service.UsuarioService;

public class MainActivity extends AppCompatActivity {

    public static Context MainContext;


    private Button MainBtnAdd;
    private Button MainBtnAcompanhar;
    private Button MainBtnCompartilhar;
    private Button MainBtnCadastro;
    private Button MainBtnConfigurar;
    private Button MainBtnMinhasMedicoes;
    private ImageView ImgLogo;
    public static String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pega o id do telefone que sera necessario para as validacoes posteriores com o firebase
        android_id = Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);

        //guarda para ser usado por outras classes
        //// TODO: 27/05/17 Duvida sobre qual a melhor forma de resolver este problema, para armazenar as configuracoes no ConfigService
        MainContext = getApplicationContext();

        findViews();
        setActions();

        //verifica se o usuario ja fez o cadastro e chamando a tela de cadastro
        VerificarUsuarioLogado();
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


    /**
     * Funcao deve garantir que o usuario seja cadastrado nao permitrindo segui com o processo enquanto o cadastro nao e realizado
     * @return
     */
    private boolean VerificarUsuarioLogado() {
        //verifica se o usuario realizou o cadastro
        Usuario usuariologado = UsuarioService.getUsuarioLogado();

        //chama a tela de cadastro de usuario
        if (usuariologado == null){
            goToCadastroActivity();
            return false;
        }

        //se nao foi informado  o email o sistema chama a tela de cadastro
        if (usuariologado.getEmail().equals("")){
            goToCadastroActivity();
            return false;
        }


        //retorna que o usuario esta logado, liberando a tela principal do aplicativo
        return true;
    }

    public void goToCompartilharActivity() {

        //verifica se o usuario esta cadastrado e chama a tela de cadastro
        if (!VerificarUsuarioLogado())
            return;


        Intent intent = new Intent(MainActivity.this, CompartilharActivity.class);
        startActivity(intent);
    }



    private void goToCadastroActivity() {
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void goToConfigActivity() {

        //verifica se o usuario esta cadastrado e chama a tela de cadastro
        if (!VerificarUsuarioLogado())
            return;

        Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
        startActivity(intent);
    }

    private void goToAcompActivity() {

        //verifica se o usuario esta cadastrado e chama a tela de cadastro
        if (!VerificarUsuarioLogado())
            return;

        Intent intent = new Intent(MainActivity.this, AcompActivity.class);
        startActivity(intent);
    }

    private void goToAcompDetalheActivity() {

        //verifica se o usuario esta cadastrado e chama a tela de cadastro
        if (!VerificarUsuarioLogado())
            return;

        Intent intent = new Intent(MainActivity.this, AcompDetalheActivity.class);
        startActivity(intent);
    }




}
