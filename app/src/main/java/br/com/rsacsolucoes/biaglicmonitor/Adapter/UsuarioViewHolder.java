package br.com.rsacsolucoes.biaglicmonitor.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rsacsolucoes.biaglicmonitor.R;

/**
 * Created by haroldbarros on 20/05/17.
 */

public class UsuarioViewHolder extends RecyclerView.ViewHolder {

    final TextView AcompItemTxtNome;
    final TextView AcompItemTxtMedicaoValor;
    final TextView AcompItemTxtMedicaoData;
    final TextView AcompItemTxtMedicaoHora;


    public UsuarioViewHolder(View view) {
        super(view);
        AcompItemTxtNome = (TextView) view.findViewById(R.id.AcompItemTxtNome);
        AcompItemTxtMedicaoValor = (TextView) view.findViewById(R.id.AcompItemTxtMedicaoValor);
        AcompItemTxtMedicaoData = (TextView) view.findViewById(R.id.AcompItemTxtMedicaoData);
        AcompItemTxtMedicaoHora = (TextView) view.findViewById(R.id.AcompItemTxtMedicaoHora);

    }

}
