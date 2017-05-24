package br.com.rsacsolucoes.biaglicmonitor.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.Model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel por retornar os dados de um usuario e suas medicoes
 * Apenas Dados estaticos serao retornados, depois, deve ser adicionar a consulta ao firebase
 */
public class UsuarioService {


    private static Usuario UsuarioLogado;

    //guarda a lista de pessoas que o usuario atual acompanha
    private static List<Usuario> usuarios;


    public static Usuario getUsuarioLogado() {
        return UsuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        UsuarioLogado = usuarioLogado;
    }


    /**
     * Retorna a lista com as pessoas que o usuario esta acompanhando
     * @param usuario Usuario da pessoa responsavel
     * @return lista de pessoas que o usuario esteja acompanhando as medicoes
     */
    public static List<Usuario> GetUsuarios(){

        //se estiver nulo preenche a variavel
        if (usuarios == null) {
            usuarios = new ArrayList<>();

            Usuario usuario1 = new Usuario("Beatriz", "beatriz@teste.com.br");
            usuario1.AddMedicao(new Medicao("20170101", "12:00:65", 120));
            usuario1.AddMedicao(new Medicao("20170102", "10:00:65", 200));
            usuario1.AddMedicao(new Medicao("20170103", "22:00:65", 234));
            usuario1.AddMedicao(new Medicao("20170104", "12:00:65", 234));
            usuario1.AddMedicao(new Medicao("20170105", "01:00:65", 123));

            Usuario usuario2 = new Usuario("Pedro", "pedro@teste.com.br");
            usuario1.AddMedicao(new Medicao("20170101", "12:00:65", 120));

            Usuario usuario3 = new Usuario("Paulo", "paulo@teste.com.br");
            usuario1.AddMedicao(new Medicao("20170101", "12:00:65", 120));

            usuarios.add(usuario1);
            usuarios.add(usuario2);
            usuarios.add(usuario3);
        }

        return usuarios;
    }


    public static void AddUsuario(Usuario usuario){

        //se estiver nulo preenche a variavel
        if (usuarios == null) {
            GetUsuarios();
        }

        //adiciona o registro
        usuarios.add(usuario);

    }


}
