package br.com.rsacsolucoes.biaglicmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {


    private Button BtnAdd;
    private Button BtnCompartilhar;
    private Button BtnCadastro;
    private Button BtnConfigurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        setActions();


    }

    private void findViews() {

        BtnAdd = (Button) findViewById(R.id.BtnAdd);
        BtnCompartilhar = (Button) findViewById(R.id.BtnCompartilhar);
        BtnCadastro = (Button) findViewById(R.id.BtnCadastro);
        BtnConfigurar = (Button) findViewById(R.id.BtnConfigurar);

    }

    private void setActions() {

        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddMedicaoActivity();
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
    }

    private void goToAddMedicaoActivity(){
        Intent intent = new Intent(MainActivity.this, AddMedicaoActivity.class);
        startActivity(intent);
    }

    private void goToCompartilharActivity() {
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




}
