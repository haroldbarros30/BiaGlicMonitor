package br.com.rsacsolucoes.biaglicmonitor.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rsacsolucoes.biaglicmonitor.R;

/**
 * Created by haroldbarros on 24/05/17.
 */

public class MedicaoViewHolder extends RecyclerView.ViewHolder {


    final TextView AcompDetalheTxtMedicaoValor;
    final TextView AcompDetalheTxtMedicaoData;
    final TextView AcompDetalheTxtMedicaoHora;


    public MedicaoViewHolder(View view) {
        super(view);
        AcompDetalheTxtMedicaoValor = (TextView) view.findViewById(R.id.AcompDetalheTxtMedicaoValor);
        AcompDetalheTxtMedicaoData = (TextView) view.findViewById(R.id.AcompDetalheTxtMedicaoData);
        AcompDetalheTxtMedicaoHora = (TextView) view.findViewById(R.id.AcompDetalheTxtMedicaoHora);

    }
}