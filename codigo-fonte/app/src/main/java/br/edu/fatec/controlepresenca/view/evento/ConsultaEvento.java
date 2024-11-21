package br.edu.fatec.controlepresenca.view.evento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.util.Evento;
import br.edu.fatec.controlepresenca.util.PessoaAdapter;

public class ConsultaEvento extends Fragment {

    private ListView listViewControle;

    // Variáveis para Controller
    private EventoController controller;

    // Adapter para apresentar dados no ListView
    PessoaAdapter adapter;



    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_controle, container, false);

        // Inicializando o controller
        controller = new EventoController(this.getContext());


        listViewControle = view.findViewById(R.id.listViewControle);

        // Listar pessoas no ListView
        List<Evento> pessoas = listAll();
        if(pessoas == null) {
            controller.updateTable();
        }


        // Clica no item da ListView leva para a tela de Manutenção
        listViewControle.setOnItemClickListener((parent, v, position, id) -> {

            // Obtém o pessoa clicada
           // Evento eventoSelecionado = adapter.getItem(position);
           // Integer pessoaSelecionadaID = pessoaSelecionada.getId();

            /*
           if(pessoaSelecionadaID != null) {
                NavController navController = NavHostFragment.findNavController(this);
                Bundle bundle = new Bundle();
                bundle.putInt("pessoaSelecionadaID", pessoaSelecionadaID);
                navController.navigate(R.id.action_controle_to_manutencao, bundle);
            }
             */

        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onResume() {
        super.onResume();

        listAll();
        if(adapter.isEmpty())
            controller.updateTable();
    }


    // Listar todos os eventos na ListvIiew
    public List<Evento> listAll() {

        // Popula array com dados do BD
        List<Evento> eventos = controller.listAll();

        // Envia array para adapter
      //  adapter = new PessoaAdapter(getContext(), eventos);

        // Popula ListView com itens do array
        listViewControle.setAdapter(adapter);
        return eventos;
    }


}