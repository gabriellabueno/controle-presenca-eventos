package br.edu.fatec.controlepresenca.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.fatec.controlepresenca.R;


public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, 0, eventos);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Se não houver nenhuma view para reutilizar, cria uma nova
        if (convertView == null) {
            // instancia o layout XML dos itens
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_evento_listview, parent, false);
        }


        // Linkando componentes XML com variáveis
        TextView tvNome = convertView.findViewById(R.id.tvNome),
                tvData = convertView.findViewById(R.id.tvData),
                tvInicio = convertView.findViewById(R.id.tvInicio),
                tvFim = convertView.findViewById(R.id.tvFim),
                tvLocal = convertView.findViewById(R.id.tvLocal),
                tvStatus = convertView.findViewById(R.id.tvStatus),
                tvOrganizador = convertView.findViewById(R.id.tvOrganizador),
                tvPalestrantes = convertView.findViewById(R.id.tvPalestrantes);


        // Popula os campos do item com o objeto passado
        Evento evento = getItem(position);
        if (evento != null) {
            tvNome.setText(evento.getNome());
            tvData.setText(evento.getData());
            tvInicio.setText(evento.getHoraInicio());
            tvFim.setText(evento.getHoraFim());
            tvLocal.setText(evento.getLocal());
            tvStatus.setText(evento.getStatus());
            tvOrganizador.setText(evento.getOrganizadores());
            tvPalestrantes.setText(evento.getPalestrantes());
        }

        return convertView;
    }
}
