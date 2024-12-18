package br.edu.fatec.controlepresenca.view.evento;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.util.Evento;
import br.edu.fatec.controlepresenca.util.EventoAdapter;
import br.edu.fatec.controlepresenca.R;

public class ConsultaEvento extends Fragment {

    // ListView
    private ListView listView;

    // Variáveis para Controller
    private EventoController controller;

    // Adapter para apresentar dados no ListView
    EventoAdapter adapter;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_consulta_evento, container, false);

        // Inicializando o controller
        controller = new EventoController(this.getContext());

        // XML ListView
        listView = view.findViewById(R.id.listViewControle);


        listAll();


        // Clica no item da ListView leva para a tela de Manutenção
        listView.setOnItemClickListener((parent, v, position, id) -> {

            // Obtém o pessoa clicada
            Evento eventoSelecionado = adapter.getItem(position);
            Integer eventoSelecionadoID = eventoSelecionado.getId();


           if(eventoSelecionadoID != null)
                mostraPopup(eventoSelecionadoID);


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
    }


    // Listar todos os eventos na ListvIiew
    public void listAll() {

        // Popula array com dados do BD
        List<Evento> eventos = controller.listAll();

        // Envia array para adapter
        adapter = new EventoAdapter(getContext(), eventos);

        // Popula ListView com itens do array
        listView.setAdapter(adapter);
    }

    private void mostraPopup(Integer eventoSelecionadoID) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_dialog);

        // Configurando os elementos do poup
        TextView title = dialog.findViewById(R.id.popup_title);
        Button btn_1 = dialog.findViewById(R.id.popup_btn_1),
                btn_2 = dialog.findViewById(R.id.popup_btn_2),
                btn_3 = dialog.findViewById(R.id.popup_btn_3),
                btn_4 = dialog.findViewById(R.id.popup_btn_4),
                btn_sair = dialog.findViewById(R.id.btn_sair);


        //Definindo texto do título e botões
        title.setText("Selecione a opção desejada");
        btn_1.setText("Adicionar participante");
        btn_2.setText("Lista de participantes");
        btn_3.setText("Alterar evento");
        btn_4.setText("Encerrar evento");

        // Checkin Participante
        btn_1.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            Bundle bundle = new Bundle();
            bundle.putInt("eventoSelecionadoID", eventoSelecionadoID);
            navController.navigate(R.id.action_popup_to_checkin, bundle);
            dialog.dismiss();
        });

        // Lista de Participantes
        btn_2.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            Bundle bundle = new Bundle();
            bundle.putInt("eventoSelecionadoID", eventoSelecionadoID);
            navController.navigate(R.id.action_popup_to_lista, bundle);
            dialog.dismiss();
        });

        // Manutencao Evento
        btn_3.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            Bundle bundle = new Bundle();
            bundle.putInt("eventoSelecionadoID", eventoSelecionadoID);
            navController.navigate(R.id.action_popup_to_manutencao, bundle);
            dialog.dismiss();
        });

        // Finaliza Evento
        btn_4.setOnClickListener(view -> {
            Evento evento = controller.read(eventoSelecionadoID);

            // atualiza status na classe
            evento.finalizaEvento();
            // atualiza status no BD
            controller.updateStatus(eventoSelecionadoID);

            // atualiza List View
            listAll();

            dialog.dismiss();
        });

        // Sair
        btn_sair.setOnClickListener(view -> {
           dialog.dismiss();
        });


        dialog.show();
    }

}