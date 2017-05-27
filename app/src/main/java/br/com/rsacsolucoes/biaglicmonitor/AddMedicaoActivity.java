package br.com.rsacsolucoes.biaglicmonitor;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.rsacsolucoes.biaglicmonitor.Model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static br.com.rsacsolucoes.biaglicmonitor.R.id.ImgLogo;
import static br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService.getUsuarioLogado;

public class AddMedicaoActivity extends AppCompatActivity {


    private ImageView AddMedicaoImgLogo;
    private Button AddMedicaoBtnGravar;
    private EditText AddMedicaoEdtTaxa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicao);

        findViews();
        setActions();
        ValidateTransitions(AddMedicaoImgLogo);
    }

    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {
        AddMedicaoImgLogo = (ImageView)findViewById(R.id.AddMedicaoImgLogo);
        AddMedicaoBtnGravar = (Button)findViewById(R.id.AddMedicaoBtnGravar);
        AddMedicaoEdtTaxa = (EditText)findViewById(R.id.AddMedicaoEdtTaxa);
    }

    /**
     * Valida a animacao indicando qual e a view que devera ser o destino da animacao
     * @param view
     */
    private void ValidateTransitions(View view){
        //Verifica se a versao do android suporta animacao do material design
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         view.setTransitionName("TrImgLogo");
        }

    }


    /**
     * Define as acoes e os listeners para os eventos dos objetos
     */
    private void setActions() {
        AddMedicaoBtnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //faz a validacao se foi informado alguma medicao
                if (AddMedicaoEdtTaxa.getText().equals("")){

                    Toast toast = Toast.makeText(getApplicationContext(), R.string.AddMedicaoMsgInfoMedicao, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                //pega o valor informado no campo de texto
                double valor = Double.parseDouble(AddMedicaoEdtTaxa.getText().toString());

                //verifica se o valor e maior que zero
                if (valor<=0)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.AddMedicaoMsgInfoMedicao, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                //busca a data atual
                Date DataAtual = Calendar.getInstance().getTime();

                //formata a data
                DateFormat fmtDt = new SimpleDateFormat("yyyyMMdd");
                String DataAtualStr =  fmtDt.format(DataAtual);

                //formada a hora
                DateFormat fmtHr = new SimpleDateFormat("HH:mm:ss");
                String HoraAtualStr = fmtHr.format(DataAtual);

                Medicao medicao = new Medicao(DataAtualStr,HoraAtualStr,valor);

                //adiciona a medicao ao usuario logado
                getUsuarioLogado().AddMedicao(medicao);

                Toast toast = Toast.makeText(getApplicationContext(), R.string.AddMedicaoMsgMedicaoAdicionada, Toast.LENGTH_SHORT);
                toast.show();


            }
        });

    }

}
