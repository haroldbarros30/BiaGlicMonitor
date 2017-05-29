package br.com.rsacsolucoes.biaglicmonitor.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel pelos dados dos usuarios do sistema
 */

public class Usuario implements Serializable {
    private String Id;
    private String Nome;
    private String Email;
    private String Telefone;
    private List<Medicao> Medicoes;

    public Usuario() {
        this.Id = "";
        this.Nome = "";
        this.Email = "";
        this.Medicoes = new ArrayList<>();
        this.Telefone = "";
    }

    public Usuario(String pNome, String Email) {
        this.Id = "";
        this.Nome = pNome;
        this.Email = Email;
        this.Medicoes = new ArrayList<>();
        this.Telefone = "";
    }


    @Exclude
    public List<Medicao> getMedicoes() {
        return Medicoes;
    }

    public void AddMedicao(Medicao medicao){
        this.Medicoes.add(medicao);
    }

    @Exclude
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
