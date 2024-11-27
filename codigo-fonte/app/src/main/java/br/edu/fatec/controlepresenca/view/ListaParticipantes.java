package br.edu.fatec.controlepresenca.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.ParticipanteController;
import br.edu.fatec.controlepresenca.util.Participante;
import br.edu.fatec.controlepresenca.util.ParticipanteAdapter;

public class ListaParticipantes extends Fragment {

    // ListView
    private ListView listView;

    // Variáveis para Controller
    private ParticipanteController controller;

    // Adapter para apresentar dados no ListView
    ParticipanteAdapter adapter;

    // Variável para manipular evento selecionado em Controle
    private Integer eventoSelecionadoID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_participantes, container, false);

        // Inicializando o controller
        controller = new ParticipanteController(this.getContext());

        // XML ListView
        listView = view.findViewById(R.id.list_view);



        // Recebe ID do evento selecionado da tela Consulta
        if (getArguments() != null) {
            eventoSelecionadoID = getArguments().getInt("eventoSelecionadoID");
        }


       listAll(eventoSelecionadoID);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    // Listar todos os eventos na ListvIiew
    public void listAll(Integer eventoSelecionadoID) {

        // Popula array com dados do BD
        List<Participante> participantes = controller.listAll(eventoSelecionadoID);

        // Envia array para adapter
        adapter = new ParticipanteAdapter(getContext(), participantes);

        // Popula ListView com itens do array
        listView.setAdapter(adapter);
    }



}