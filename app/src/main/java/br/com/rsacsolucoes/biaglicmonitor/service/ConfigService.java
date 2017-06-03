package br.com.rsacsolucoes.biaglicmonitor.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

import br.com.rsacsolucoes.biaglicmonitor.view.AddMedicaoActivity;
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


    private static AlarmManager alarmMgr;
    private static PendingIntent alarmIntent;

    public static void setConfiguracao(Config configuracao) {
        //guarda a variavel passada na configuracao
        ConfigService.configuracao = configuracao;

        //guarda as configuracoes no device local
        SharedPreferences sharedPref = MainActivity.MainContext.getSharedPreferences(
                MainActivity.MainContext.getString(R.string.preference_lembrete_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt( MainActivity.MainContext.getString(R.string.preference_lembrete_key), configuracao.getLembrete());
        editor.commit();

        if (alarmMgr!= null) {
            // If the alarm has been set, cancel it.
            alarmMgr.cancel(alarmIntent);
        }

        //// TODO: 02/06/17 Verificar o que estou fazendo de errado para ativar o lembrete de medicoes
        //cria o lembrete conforme o tempo determinado para o alarme
        alarmMgr = (AlarmManager)MainActivity.MainContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.MainContext, AddMedicaoActivity.class);
        alarmIntent = PendingIntent.getBroadcast(MainActivity.MainContext, 0, intent, 0);

        long IntervaloMinutos = configuracao.getLembrete() * 60 * 1000;

        // Hopefully your alarm will have a lower frequency than this!
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + IntervaloMinutos,
                IntervaloMinutos, alarmIntent);
    }
}
