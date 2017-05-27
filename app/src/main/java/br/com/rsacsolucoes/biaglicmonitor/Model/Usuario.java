package br.com.rsacsolucoes.biaglicmonitor.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel pelos dados dos usuarios do sistema
 */

public class Usuario implements Serializable {
    private String Nome;
    private String Email;
    private String Telefone;
    private List<Medicao> Medicoes;

    public Usuario() {
        this.Nome = "";
        this.Email = "";
        this.Medicoes = new ArrayList<>();
        this.Telefone = "";
    }

    public Usuario(String pNome, String Email) {
        this.Nome = pNome;
        this.Email = Email;
        this.Medicoes = new ArrayList<>();
        this.Telefone = "";
    }


    public List<Medicao> getMedicoes() {
        return Medicoes;
    }

    public void AddMedicao(Medicao medicao){
        this.Medicoes.add(medicao);
    }

    public Medicao getUltimaMedicao(){

        //se existe medicoes adicionadas
        if (Medicoes.size()>0){
            return Medicoes.get(Medicoes.size()-1);
        }
        else
            return null;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
