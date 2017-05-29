package br.com.rsacsolucoes.biaglicmonitor.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haroldbarros on 20/05/17.
 * Classe responsavel por guardar das medicoes realizadas
 */
public class Medicao  implements Serializable {
    private String Id;
    private String Data;
    private String Hora;
    private double Valor;

    public Medicao() {
        Id = "";
        Data = "";
        Hora = "";
        Valor = 0;
    }

    public Medicao(double pValor) {

        //busca a data atual
        Date DataAtual = Calendar.getInstance().getTime();

        //formata a data
        DateFormat fmtDt = new SimpleDateFormat("yyyyMMdd");
        String DataAtualStr =  fmtDt.format(DataAtual);

        //formada a hora
        DateFormat fmtHr = new SimpleDateFormat("HH:mm:ss");
        String HoraAtualStr = fmtHr.format(DataAtual);

        this.Id = String.valueOf(Calendar.getInstance().getTimeInMillis()); // DataAtualStr+HoraAtualStr.replace(":","");
        this.Data = DataAtualStr;
        this.Hora = HoraAtualStr;
        this.Valor = pValor;
    }


    public Medicao(String pId, String pData, String pHora,double pValor) {
        this.Id = pId;
        this.Data = pData;
        this.Hora = pHora;
        this.Valor = pValor;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double valor) {
        Valor = valor;
    }
}
