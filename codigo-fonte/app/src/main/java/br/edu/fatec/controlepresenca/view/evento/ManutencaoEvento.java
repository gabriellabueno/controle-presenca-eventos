package br.edu.fatec.controlepresenca.view.evento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.Date;
import java.sql.Time;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.util.Evento;

public class ManutencaoEvento extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtLocal, edtOrganizador,
            edtData, edtHoraInicio, edtHoraFim;
    private ListView lvPalestrantesM;
    private Button btnAtualizar, btnExcluir;

    // Variáveis para controller:
    private EventoController eventoController;
    private Evento evento;

    // Variáveis para definir valores booleanos
    // Salvos como INT pois o MySQL não aceita booleano
    private Integer status;

    // Variável para manipular pessoa selecionada em Controle
    private Integer eventoSelecionadoID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_manutencao_evento, container, false);

        evento = new Evento();
        // Inicializa Controller
        eventoController = new EventoController(this.getContext());

        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNome);
        edtLocal = view.findViewById(R.id.edtLocal);
        edtOrganizador = view.findViewById(R.id.edtOrganizador);
        edtData = view.findViewById(R.id.edtData);
        edtHoraInicio = view.findViewById(R.id.edtHoraInicio);
        edtHoraFim = view.findViewById(R.id.edtHoraFim);
        lvPalestrantesM = view.findViewById(R.id.lvPalestrantesM);

        btnAtualizar = view.findViewById(R.id.btnAtualizar);
        btnExcluir = view.findViewById(R.id.btnExcluir);

        // Recebe ID da pessoa selecionada da tela Controle
        if (getArguments() != null) {
            eventoSelecionadoID = getArguments().getInt("eventoSelecionadoID");
        }

        // Busca dados a partir do ID e armazena em instância de Pessoa
        evento = eventoController.read(eventoSelecionadoID);


        // Preenche campos de inputs com os dados retornados
        preencheCamposEdt(evento);

        //Clica no item do list view leva para Manutenção de palestrantes
        lvPalestrantesM.setOnClickListener(view1 -> {

        });


        // BOTÃO ATUALIZAR
        btnAtualizar.setOnClickListener(v -> {
            evento = recebeInputs();

            if (evento == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                evento.setId(eventoSelecionadoID);
                eventoController.update(evento);
                eventoController.mostrarMensagem("atualizado");
            }

        });


        //Botão Excluir
        btnExcluir.setOnClickListener(v -> {
            evento = new Evento();
            evento.setId(eventoSelecionadoID);
            eventoController.delete(evento);
            eventoController.mostrarMensagem("removido");
            limpaCamposEdt();
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    // Recebe dados do evento e armazena em nova instância de Evento
    public Evento recebeInputs() {

        if (edtNome.getText().toString().isEmpty()
                || edtLocal.getText().toString().isEmpty()
                || edtOrganizador.getText().toString().isEmpty()
                || edtData.getText().toString().isEmpty()
                || edtHoraInicio.getText().toString().isEmpty()
                || edtHoraFim.getText().toString().isEmpty()) {
            return null;

        } else {
            evento = new Evento();
            evento.setNome(edtNome.getText().toString());
            evento.setLocal(edtLocal.getText().toString());
            evento.setOrganizadores(edtOrganizador.getText().toString());
            evento.setData(Date.valueOf(edtData.getText().toString()));
            evento.setHoraInicio(Time.valueOf(edtHoraInicio.getText().toString()));
            evento.setHoraInicio(Time.valueOf(edtHoraFim.getText().toString()));
            return evento;
        }

    }

    // Preenche campos de inputs com os dados do evento para Manutencao
    public void preencheCamposEdt(Evento evento) {
        edtNome.setText(evento.getNome());
        edtLocal.setText(evento.getLocal());
        edtOrganizador.setText(evento.getOrganizadores());
        edtData.setText(String.valueOf(evento.getData()));
        edtHoraInicio.setText(String.valueOf(evento.getHoraInicio()));
        edtHoraFim.setText(String.valueOf(evento.getHoraFim()));
    }

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtLocal.setText(null);
        edtOrganizador.setText(null);
        edtData.setText(null);
        edtHoraInicio.setText(null);
        edtHoraFim.setText(null);
    }
}