package br.edu.fatec.controlepresenca.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.edu.fatec.diariosaude.R;

public class PalestranteAdapter extends ArrayAdapter<Palestrante> {
    private Context context;

    // ADICIONAR PARÂMETRO PARA VISIBILIDADE DO CHECKBOX
    public PalestranteAdapter(Context context, List<Palestrante> palestrantes) {
        super(context, 0, palestrantes);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Se não houver nenhuma view para reutilizar, cria uma nova
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate( // instancia o layout XML dos itens
                    R.layout.item_palestrante_listview, parent, false);
        }

        // Linkando componentes XML com variáveis
        CheckBox cbPalestrante = convertView.findViewById(R.id.cbPalestrante);
        TextView tvNome = convertView.findViewById(R.id.tvNome),
                tvCPF = convertView.findViewById(R.id.tvCPF),
                tvEmail = convertView.findViewById(R.id.tvEmail);


        // Popula os campos do item com o objeto passado
        Palestrante palestrante = getItem(position);
        if (palestrante != null) {
            tvNome.setText(palestrante.getNome());
            tvCPF.setText(palestrante.getCpf());
            tvEmail.setText(palestrante.getEmail());
        }

        return convertView;
    }
}
