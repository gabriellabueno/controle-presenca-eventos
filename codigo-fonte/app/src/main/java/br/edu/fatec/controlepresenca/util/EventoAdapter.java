package br.edu.fatec.controlepresenca.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.edu.fatec.controlepresenca.R;


public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, 0, eventos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Se não houver nenhuma view para reutilizar, cria uma nova
        if (convertView == null) {
            // instancia o layout XML dos itens
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_evento_listview, parent, false);
        }


        // Linkando componentes XML com variáveis
        TextView tvNome = convertView.findViewById(R.id.tvNome),
                tvID = convertView.findViewById(R.id.tvID),
                tvInicio = convertView.findViewById(R.id.tvInicio),
                tvFim = convertView.findViewById(R.id.tvFim),
                tvDuracao = convertView.findViewById(R.id.tvDuracao),
                tvLocal = convertView.findViewById(R.id.tvLocal),
                tvStatus = convertView.findViewById(R.id.tvStatus),
                tvOrganizador = convertView.findViewById(R.id.tvOrganizador),
                tvPalestrantes = convertView.findViewById(R.id.tvPalestrantes);


        // Popula os campos do item com o objeto passado
        Evento evento = getItem(position);
        if (evento != null) {
            tvNome.setText(evento.getNome());
            tvID.setText(evento.getId());
            tvInicio.setText(evento.getHoraInicio().toString());
            tvFim.setText(evento.getHoraFim().toString());
            tvDuracao.setText(evento.getDuracao());
            tvLocal.setText(evento.getLocal());

            if(evento.getStatus() == 1)
                tvStatus.setText("Aberto");
            else
                tvStatus.setText("Fechado");

            tvOrganizador.setText(evento.getNome());
            tvPalestrantes.setText(evento.getPalestrantes());
        }

        return convertView;
    }
}
