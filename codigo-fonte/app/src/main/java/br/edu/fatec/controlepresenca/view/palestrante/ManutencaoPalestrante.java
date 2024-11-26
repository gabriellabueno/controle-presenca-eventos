package br.edu.fatec.controlepresenca.view.palestrante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.PalestranteController;
import br.edu.fatec.controlepresenca.util.Palestrante;

public class ManutencaoPalestrante extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtCpf, edtEmail;
    private Button btnAtualizar, btnExcluir;

    // Variáveis para Controller
    private PalestranteController palestranteController;
    private Palestrante palestrante;

    // Variável para manipular palestrante selecionado no ListView
    private String palestranteSelecionadoCpf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_manutencao_palestrante, container, false); //VERIFICAR ESSE ERRO

        palestrante = new Palestrante();
        // Inicializa Controller
        palestranteController = new PalestranteController(this.getContext());

        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNome);
        edtCpf = view.findViewById(R.id.edtCpf);
        edtEmail = view.findViewById(R.id.edtEmail);

        /* POSSÍVEL MÉTODO PARA MÁSCARA DE CPF

        // Máscara para input altura #.##
        edtAltura.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String resultingTxt = dest.toString().substring(0, dstart) +
                        source.subSequence(start, end) +
                        dest.toString().substring(dend);
                if (!resultingTxt.matches("^\\d{0,1}(\\.\\d{0,2})?$")) {
                    return "";
                }
            }
            return null;
        }});
        */

        btnAtualizar = view.findViewById(R.id.btnAtualizarPlt);
        btnExcluir = view.findViewById(R.id.btnExcluirPlt);

        // Recebe CPF da pessoa selecionada da tela Controle
        if (getArguments() != null) {
            palestranteSelecionadoCpf = getArguments().getString("palestranteSelecionadoCpf");
        }

        // Busca dados a partir do CPF e armazena em instância de Palestrante
        palestrante = palestranteController.read(palestranteSelecionadoCpf);


        // Preenche campos de inputs com os dados retornados
        preencheCamposEdt(palestrante);


        // BOTÃO ATUALIZAR
        btnAtualizar.setOnClickListener(v -> {
            palestrante = recebeInputs();

            if (palestrante == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                palestrante.setCpf(palestranteSelecionadoCpf);
                palestranteController.update(palestrante);
                palestranteController.mostrarMensagem("atualizado");
            }

        });


        //Botão Excluir
        btnExcluir.setOnClickListener(v -> {
            palestrante = new Palestrante();
            palestrante.setCpf(palestranteSelecionadoCpf);
            palestranteController.delete(palestrante);
            palestranteController.mostrarMensagem("removido");
            limpaCamposEdt();
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    // Recebe dados e armazena em nova instância de Palestrante
    public Palestrante recebeInputs() {

        if (edtNome.getText().toString().isEmpty()
                || edtCpf.getText().toString().isEmpty()
                || edtEmail.getText().toString().isEmpty()) {
            return null;

        } else {
            palestrante = new Palestrante();
            palestrante.setNome(edtNome.getText().toString());
            palestrante.setCpf(edtCpf.getText().toString());
            palestrante.setEmail(edtEmail.getText().toString());
            return palestrante;
        }
    }


    // Preenche campos de inputs com os dados da pessoa para Manutencao
    public void preencheCamposEdt(Palestrante palestrante) {
        edtNome.setText(palestrante.getNome());
        edtCpf.setText(palestrante.getCpf());
        edtEmail.setText(String.valueOf(palestrante.getEmail()));

        /*Depois usar para CPF
        String alturaFormatada = new DecimalFormat("#.00").format(palestrante.getAltura());
        edtAltura.setText(alturaFormatada);
         */
    }

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtCpf.setText(null);
        edtEmail.setText(null);
    }

}