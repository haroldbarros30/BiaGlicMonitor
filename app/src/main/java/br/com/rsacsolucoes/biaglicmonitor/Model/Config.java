package br.com.rsacsolucoes.biaglicmonitor.Model;

import java.io.Serializable;

/**
 * Created by haroldbarros on 26/05/17.
 * Classe de responsavel por guardar os parametros de configuracao
 */

public class Config  implements Serializable {

    /**
     * Define o tempo em minutos que devera ser utilizado para o lembrete de medicao
     */
    private int Lembrete;


    public Config() {
        //default do lembrete configurado para 1h
        this.Lembrete = 60;
    }

    public Config(int lembrete) {
        this.Lembrete = lembrete;
    }

    public int getLembrete() {
        return this.Lembrete;
    }

    public void setLembrete(int lembrete) {
        this.Lembrete = lembrete;
    }
}
