package br.com.rsacsolucoes.biaglicmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.Service.UsuarioService;

import static br.com.rsacsolucoes.biaglicmonitor.R.id.ImgLogo;

public class CadastroActivity extends AppCompatActivity {


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
        CadastroEdtNome.setText(usuario.getNome());
        CadastroEdtEmail.setText(usuario.getEmail());
        CadastroEdtTelefone.setText(usuario.getTelefone());
        CadastroEdtNome.setText(usuario.getNome());

    }

    /**
     * realiza o vinculo das variaveis com as view xml
     */
    private void findViews() {

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
                usuario.setNome(CadastroEdtNome.getText().toString());
                usuario.setEmail(CadastroEdtEmail.getText().toString());
                usuario.setTelefone(CadastroEdtTelefone.getText().toString());

                //grava o novo cadastro
                UsuarioService.setUsuarioLogado(usuario);

            }
        });

    }
}
