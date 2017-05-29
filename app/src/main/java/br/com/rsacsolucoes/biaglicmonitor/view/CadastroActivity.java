package br.com.rsacsolucoes.biaglicmonitor.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.rsacsolucoes.biaglicmonitor.R;
import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.service.UsuarioService;

public class CadastroActivity extends AppCompatActivity {


    private EditText CadastroEdtId;
    private EditText CadastroEdtNome;
    private EditText CadastroEdtEmail;
    private EditText CadastroEdtTelefone;
    private Button   CadastroBtnGravar;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        findViews();
        setActions();

        //pega os dados do usuario logado e preenche com os dados do usuario logado
        usuario = UsuarioService.getUsuarioLogado();

        //preenche os dados
        CadastroEdtId.setText(usuario.getId());
        CadastroEdtNome.setText(usuario.getNome());
        CadastroEdtEmail.setText(usuario.getEmail());
        CadastroEdtTelefone.setText(usuario.getTelefone());
        CadastroEdtNome.setText(usuario.getNome());

    }

    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {

        CadastroEdtId = (EditText) findViewById(R.id.CadastroEdtId);
        CadastroEdtNome = (EditText) findViewById(R.id.CadastroEdtNome);
        CadastroEdtEmail = (EditText) findViewById(R.id.CadastroEdtEmail);
        CadastroEdtTelefone = (EditText) findViewById(R.id.CadastroEdtTelefone);
        CadastroBtnGravar = (Button) findViewById(R.id.CadastroBtnGravar);
    }



    /**
     * Define as acoes e os listeners para os eventos dos objetos
     */
    private void setActions() {

        CadastroBtnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuario == null){
                    usuario = new Usuario();
                }

                //guara os dados de cadastro na variavel de login
                usuario.setId(CadastroEdtId.getText().toString());
                usuario.setNome(CadastroEdtNome.getText().toString());
                usuario.setEmail(CadastroEdtEmail.getText().toString());
                usuario.setTelefone(CadastroEdtTelefone.getText().toString());


                try {

                    //grava o novo cadastro
                    if (!UsuarioService.setUsuarioLogado(usuario))
                        return;

                } catch (Exception e){

                    //se houve algum erro o  sistema exibe a mensagem de erro
                    Toast toast = Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }


                //finaliza a activity se o cadastro foi realizado com sucesso
                finish();


            }
        });

    }
}
