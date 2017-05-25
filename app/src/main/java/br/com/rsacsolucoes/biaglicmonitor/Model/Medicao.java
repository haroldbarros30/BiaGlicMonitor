package br.com.rsacsolucoes.biaglicmonitor.Model;

import java.io.Serializable;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel por guardar das medicoes realizadas
 */
public class Medicao  implements Serializable {
    private final String Data;
    private final String Hora;
    private final double Valor;

    public Medicao(String pData, String pHora,double pValor) {
        this.Data = pData;
        this.Hora = pHora;
        this.Valor = pValor;
    }

    public String getData() {
        return this.Data;
    }

    public String getHora() {
        return this.Hora;
    }

    public double getValor() {
        return this.Valor;
    }

}
