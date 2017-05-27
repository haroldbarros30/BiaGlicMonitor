package br.com.rsacsolucoes.biaglicmonitor.Service;

import br.com.rsacsolucoes.biaglicmonitor.Model.Config;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;

/**
 * Created by haroldbarros on 26/05/17.
 * Servico responsavel por definir a configuracao padrao
 */

public class ConfigService {

    private static Config configuracao = new Config(60);

    public static Config getConfiguracao() {
        return configuracao;
    }

    public static void setConfiguracao(Config configuracao) {
        ConfigService.configuracao = configuracao;
    }
}
