package br.edu.fatec.controlepresenca.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.fatec.controlepresenca.R;


public class ParticipanteAdapter extends ArrayAdapter<Participante> {
    private Context context;

    public ParticipanteAdapter(Context context, List<Participante> participante) {
        super(context, 0, participante);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Se não houver nenhuma view para reutilizar, cria uma nova
        if (convertView == null) {
            // instancia o layout XML dos itens
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_participante_listview, parent, false);
        }


        // Linkando componentes XML com variáveis
        TextView tvNome = convertView.findViewById(R.id.tvNome),
                tvCPF = convertView.findViewById(R.id.tvCPF),
                tvEmail = convertView.findViewById(R.id.tvEmail),
                tvCurso = convertView.findViewById(R.id.tvCurso);


        // Popula os campos do item com o objeto passado
        Participante participante = getItem(position);

        if (participante != null) {
            tvNome.setText(participante.getNome());
            tvCPF.setText(participante.getCpf());
            tvEmail.setText(participante.getEmail());
            tvCurso.setText(participante.getCurso());
        }



        return convertView;
    }
}
