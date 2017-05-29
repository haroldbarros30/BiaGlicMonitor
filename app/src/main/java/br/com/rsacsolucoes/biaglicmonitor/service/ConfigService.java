package br.com.rsacsolucoes.biaglicmonitor.service;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.rsacsolucoes.biaglicmonitor.view.MainActivity;
import br.com.rsacsolucoes.biaglicmonitor.model.Config;
import br.com.rsacsolucoes.biaglicmonitor.R;

/**
 * Created by haroldbarros on 26/05/17.
 * Servico responsavel por definir a configuracao padrao
 */

public class ConfigService {

    private static Config configuracao;

    public static Config getConfiguracao() {


        //verifica se as configuracoes ja foram carregadas
        if (configuracao == null) {


            //guarda as configuracoes no device local
            SharedPreferences sharedPref = MainActivity.MainContext.getSharedPreferences(
                    MainActivity.MainContext.getString(R.string.preference_lembrete_key), Context.MODE_PRIVATE);


            //cria o objeto de configuracao
            configuracao = new Config();

            //pega o valor armazenado no celular
            configuracao.setLembrete(sharedPref.getInt(MainActivity.MainContext.getString(R.string.preference_lembrete_key), 60));

        }

        return configuracao;
    }

    public static void setConfiguracao(Config configuracao) {
        //guarda a variavel passada na configuracao
        ConfigService.configuracao = configuracao;


        //guarda as configuracoes no device local
        SharedPreferences sharedPref = MainActivity.MainContext.getSharedPreferences(
                MainActivity.MainContext.getString(R.string.preference_lembrete_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt( MainActivity.MainContext.getString(R.string.preference_lembrete_key), configuracao.getLembrete());
        editor.commit();

    }
}
