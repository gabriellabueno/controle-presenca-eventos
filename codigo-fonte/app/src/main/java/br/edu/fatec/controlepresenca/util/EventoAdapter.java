package br.edu.fatec.controlepresenca.util;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventoAdapter extends ArrayAdapter<Evento> {
    private Context context;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, 0, eventos);
        this.context = context;
    }

    // convertView - view que é "reciclada"
    // quando o ListView precisa apresentar um item, checa se há uma view disponível
    // ao invés de criar uma do zero

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Se não houver nenhuma view para reutilizar, cria uma nova
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate( // instancia o layout XML dos itens
                    R.layout.item_pessoa_listview, parent, false);
        }

        // Linkando componentes XML com variáveis
        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvIdade = convertView.findViewById(R.id.tvIdade);


        // Popula os campos do item com o objeto passado
        Pessoa pessoa = getItem(position);
        if (pessoa != null) {
           // tvID.setText(String.valueOf(pessoa.getId()));
            tvNome.setText(pessoa.getNome());
        }


        return convertView;
    }
}
