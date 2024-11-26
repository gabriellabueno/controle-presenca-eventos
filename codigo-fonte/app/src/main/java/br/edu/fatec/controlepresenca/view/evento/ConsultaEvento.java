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

    private ListView listViewControle;

    // Variáveis para Controller
    private EventoController controller;

    // Adapter para apresentar dados no ListView
    EventoAdapter adapter;

    private Evento evento;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_consulta_evento, container, false);

        // Inicializando o controller
        controller = new EventoController(this.getContext());


        listViewControle = view.findViewById(R.id.listViewControle);

        // Listar pessoas no ListView
        List<Evento> eventos = listAll();
        if (eventos == null) {
            controller.updateTable();
        }


        // Clica no item da ListView leva para a tela de Manutenção
        listViewControle.setOnItemClickListener((parent, v, position, id) -> {

            // Obtém o pessoa clicada
            Evento eventoSelecionado = adapter.getItem(position);
            Integer eventoSelecionadoID = eventoSelecionado.getId();


           if(eventoSelecionadoID != null) {
                NavController navController = NavHostFragment.findNavController(this);
                Bundle bundle = new Bundle();
                bundle.putInt("eventoSelecionadoID", eventoSelecionadoID);
                //navController.navigate(R.id.action_controle_to_manutencao, bundle);
            }

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
        if (adapter.isEmpty())
            controller.updateTable();
    }


    // Listar todos os eventos na ListvIiew
    public List<Evento> listAll() {

        // Popula array com dados do BD
        List<Evento> eventos = controller.listAll();

        // Envia array para adapter
        adapter = new EventoAdapter(getContext(), eventos);

        // Popula ListView com itens do array
        listViewControle.setAdapter(adapter);
        return eventos;
    }

    private void mostraPopup() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_dialog);

        // Configurando os elementos do poup
        TextView title = dialog.findViewById(R.id.popup_title);
        Button btn_1 = dialog.findViewById(R.id.popup_btn_1),
                btn_2 = dialog.findViewById(R.id.popup_btn_2),
                btn_3 = dialog.findViewById(R.id.popup_btn_3);

        //Definindo texto do título e botões
        title.setText("Selecione a opção desejada:");
        btn_1.setText("Adicionar participante");
        btn_2.setText("Alterar evento");
        btn_3.setText("Finalizar evento");

        //Métodos para botões
        btn_1.setOnClickListener(v -> {
            dialog.dismiss();
            //Mudar para fragment CheckinParticipante
            NavController navController = NavHostFragment.findNavController(this);
            //navController.navigate(R.id.action_home_to_cadastro);
        });

        btn_2.setOnClickListener(view -> {
            //Mudar para fragment CheckinParticipante
            NavController navController = NavHostFragment.findNavController(this);
            //navController.navigate(R.id.action_home_to_cadastro);
        });

        btn_3.setOnClickListener(view -> {
            evento.finalizaEvento();
        });

        dialog.show();
    }

}