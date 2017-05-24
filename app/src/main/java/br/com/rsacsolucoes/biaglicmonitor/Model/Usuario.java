package br.com.rsacsolucoes.biaglicmonitor.Model;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel pelos dados dos usuarios do sistema
 */

public class Usuario {
    private String Nome;
    private String Email;
    private List<Medicao> Medicoes;

    public Usuario(String pNome, String Email) {
        this.Nome = pNome;
        this.Email = Email;
        this.Medicoes = new ArrayList<>();
    }

    public String getNome() {
        return this.Nome;
    }

    public String getEmail(){
        return this.Email;
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
            return Medicoes.get(Medicoes.size());
        }
        else
            return null;
    }
}
