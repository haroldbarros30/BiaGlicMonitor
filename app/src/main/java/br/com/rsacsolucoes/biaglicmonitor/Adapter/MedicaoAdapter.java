package br.com.rsacsolucoes.biaglicmonitor.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.Model.Medicao;
import br.com.rsacsolucoes.biaglicmonitor.Model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.R;

/**
 * Created by haroldbarros on 24/05/17.
 */

public class MedicaoAdapter extends RecyclerView.Adapter
        implements View.OnClickListener {

    private List<Medicao> medicoes;
    private Context context;
    private View.OnClickListener listener;

    public MedicaoAdapter(List<Medicao> medicoes, Context context) {
        this.medicoes = medicoes;
        this.context = context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        // faz o inflate
        View view = LayoutInflater.from(context)
                .inflate(R.layout.acomp_deyalhe_item, parent, false);

        MedicaoViewHolder holder = new MedicaoViewHolder(view);

        view.setOnClickListener(listener);

        return holder;

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        //atribui as os valores das variaveis
        MedicaoViewHolder holder = (MedicaoViewHolder)viewHolder;

        Medicao medicao  = medicoes.get(position) ;

        if (medicao != null) {

            holder.AcompDetalheTxtMedicaoValor.setText(String.valueOf(medicao.getValor()));
            holder.AcompDetalheTxtMedicaoData.setText(medicao.getData());
            holder.AcompDetalheTxtMedicaoHora.setText(medicao.getHora());
        }

    }

    @Override
    public int getItemCount() {
        return medicoes.size();
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    /**
     * Evento onclick do item da lista
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

}



