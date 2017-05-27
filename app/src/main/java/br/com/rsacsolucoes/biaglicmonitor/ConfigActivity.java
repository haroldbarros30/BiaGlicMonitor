package br.com.rsacsolucoes.biaglicmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.rsacsolucoes.biaglicmonitor.Service.ConfigService;

public class ConfigActivity extends AppCompatActivity {


    private EditText ConfigEdtLembrete;
    private Button ConfigBtnGravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        findViews();
        setActions();

        //busca a configuracao atual
        ConfigEdtLembrete.setText(String.valueOf(ConfigService.getConfiguracao().getLembrete()));


    }


    private void findViews() {
        ConfigEdtLembrete = (EditText)findViewById(R.id.ConfigEdtLembrete);
        ConfigBtnGravar =  (Button) findViewById(R.id.ConfigBtnGravar);
    }

    private void setActions() {

        ConfigBtnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verifica se foi informa valio ou maiopr que zero
                if (ConfigEdtLembrete.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.ConfigMsgInfoLembrete, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                //pega a informacao
                int lembrete = Integer.parseInt(ConfigEdtLembrete.getText().toString());

                //verifica se e maior que zero
                if (lembrete <0)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.ConfigMsgInfoLembrete, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                ConfigService.getConfiguracao().setLembrete(lembrete);

                Toast toast = Toast.makeText(getApplicationContext(), R.string.ConfigMsgConfigGravada, Toast.LENGTH_SHORT);
                toast.show();


            }
        });

    }
}
