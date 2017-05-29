package br.com.rsacsolucoes.biaglicmonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rsacsolucoes.biaglicmonitor.model.Usuario;
import br.com.rsacsolucoes.biaglicmonitor.R;

//import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by haroldbarros on 20/05/17.
 */

public class UsuarioAdapter extends RecyclerView.Adapter
        implements View.OnClickListener {

    private List<Usuario> usuarios;
    private Context context;
    private View.OnClickListener listener;

    public UsuarioAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        // faz o inflate
        View view = LayoutInflater.from(context)
                .inflate(R.layout.acomp_item, parent, false);

        UsuarioViewHolder holder = new UsuarioViewHolder(view);

        view.setOnClickListener(listener);

        return holder;

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        //atribui as os valores das variaveis
        UsuarioViewHolder holder = (UsuarioViewHolder) viewHolder;

        Usuario usuario  = usuarios.get(position) ;

        holder.AcompItemTxtNome.setText(usuario.getNome());

        if (usuario.getUltimaMedicao() != null) {

            //// TODO: 20/05/17 verificar como converter double para string
            holder.AcompItemTxtMedicaoValor.setText(String.valueOf(usuario.getUltimaMedicao().getValor()));
            holder.AcompItemTxtMedicaoData.setText(usuario.getUltimaMedicao().getData());
            holder.AcompItemTxtMedicaoHora.setText(usuario.getUltimaMedicao().getHora());
        }
        else
        {
            holder.AcompItemTxtMedicaoValor.setText("");
            holder.AcompItemTxtMedicaoData.setText(R.string.AcompItemVazio);
            holder.AcompItemTxtMedicaoHora.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
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


