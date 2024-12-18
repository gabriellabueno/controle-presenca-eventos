package br.edu.fatec.controlepresenca.view.evento;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.graphics.Color;

import androidx.fragment.app.Fragment;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.util.Evento;

public class ManutencaoEvento extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtLocal, edtPalestrante, edtOrganizador, edtData, edtHoraInicio, edtHoraFim;
    private Button btnAtualizar, btnExcluir;

    // Variáveis para controller:
    private EventoController controller;
    private Evento evento;


    // Variável para manipular evento selecionado em Controle
    private Integer eventoSelecionadoID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_manutencao_evento, container, false);

        evento = new Evento();

        // Inicializa Controller
        controller = new EventoController(this.getContext());

        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNome);
        edtLocal = view.findViewById(R.id.edtLocal);
        edtPalestrante = view.findViewById(R.id.edtPalestrante);
        edtOrganizador = view.findViewById(R.id.edtOrganizador);
        edtData = view.findViewById(R.id.edtData);
        edtHoraInicio = view.findViewById(R.id.edtHoraInicio);
        edtHoraFim = view.findViewById(R.id.edtHoraFim);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);
        btnExcluir = view.findViewById(R.id.btnExcluir);

        // Máscara para input data ##/##/####
        edtData.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String resultingTxt = dest.toString().substring(0, dstart) +
                        source.subSequence(start, end) +
                        dest.toString().substring(dend);
                if (!resultingTxt.matches("^\\d{0,2}(/\\d{0,2})?(/\\d{0,4})?$")) {
                    return "";
                }
            }
            return null;
        }});

        // Máscara para input horas ##:##
        edtHoraInicio.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String resultingTxt = dest.toString().substring(0, dstart) +
                        source.subSequence(start, end) +
                        dest.toString().substring(dend);
                if (!resultingTxt.matches("^\\d{0,2}(:\\d{0,2})?$")) {
                    return "";
                }
            }
            return null;
        }});

        // Máscara para input horas ##:##
        edtHoraFim.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String resultingTxt = dest.toString().substring(0, dstart) +
                        source.subSequence(start, end) +
                        dest.toString().substring(dend);
                if (!resultingTxt.matches("^\\d{0,2}(:\\d{0,2})?$")) {
                    return "";
                }
            }
            return null;
        }});

        // Recebe ID do evento selecionado da tela Consulta
        if (getArguments() != null) {
            eventoSelecionadoID = getArguments().getInt("eventoSelecionadoID");
        }

        // Busca dados a partir do ID e armazena em instância de Evento
        evento = controller.read(eventoSelecionadoID);


        // Preenche campos de inputs com os dados retornados
        preencheCamposEdt(evento);

        if(evento.getStatus().equals("Encerrado")) {
            desativaAtualizacao();
        }



        // BOTÃO ATUALIZAR
        btnAtualizar.setOnClickListener(v -> {
            evento = recebeInputs();

            if (evento == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos",
                        Toast.LENGTH_SHORT).show();
            } else {
                evento.setId(eventoSelecionadoID);
                controller.update(evento);
                controller.mostrarMensagem("atualizado");
            }

        });


        //Botão Excluir
        btnExcluir.setOnClickListener(v -> {
            evento = new Evento();
            evento.setId(eventoSelecionadoID);
            controller.delete(evento);
            controller.mostrarMensagem("removido");
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
                || edtPalestrante.getText().toString().isEmpty()
                || edtOrganizador.getText().toString().isEmpty()
                || edtData.getText().toString().isEmpty()
                || edtHoraInicio.getText().toString().isEmpty()
                || edtHoraFim.getText().toString().isEmpty()) {
            return null;

        } else {
            evento = new Evento();
            evento.setNome(edtNome.getText().toString());
            evento.setLocal(edtLocal.getText().toString());
            evento.setPalestrantes(edtPalestrante.getText().toString());
            evento.setOrganizadores(edtOrganizador.getText().toString());
            evento.setData(edtData.getText().toString());
            evento.setHoraInicio(edtHoraInicio.getText().toString());
            evento.setHoraFim(edtHoraFim.getText().toString());
            return evento;
        }

    }

    // Preenche campos de inputs com os dados do evento para Manutencao
    public void preencheCamposEdt(Evento evento) {
        edtNome.setText(evento.getNome());
        edtLocal.setText(evento.getLocal());
        edtPalestrante.setText(evento.getPalestrantes());
        edtOrganizador.setText(evento.getOrganizadores());
        edtData.setText(String.valueOf(evento.getData()));
        edtHoraInicio.setText(String.valueOf(evento.getHoraInicio()));
        edtHoraFim.setText(String.valueOf(evento.getHoraFim()));
    }

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtLocal.setText(null);
        edtPalestrante.setText(null);
        edtOrganizador.setText(null);
        edtData.setText(null);
        edtHoraInicio.setText(null);
        edtHoraFim.setText(null);
    }

    public void desativaAtualizacao() {

        // Desativa botão Atualizar
        btnAtualizar.setEnabled(false);
        btnAtualizar.setBackgroundColor(Color.DKGRAY);

        // Desativa campos
        edtNome.setEnabled(false);
        edtLocal.setEnabled(false);
        edtPalestrante.setEnabled(false);
        edtOrganizador.setEnabled(false);
        edtData.setEnabled(false);
        edtHoraInicio.setEnabled(false);
        edtHoraFim.setEnabled(false);
    }

}