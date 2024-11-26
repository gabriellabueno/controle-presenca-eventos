package br.edu.fatec.controlepresenca.view.palestrante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.fatec.controlepresenca.controller.PalestranteController;
import br.edu.fatec.controlepresenca.util.Palestrante;
import br.edu.fatec.controlepresenca.R; //VERIFICAR SE É POSSÍVEL MUDAR CAMINHO DE DIRETÓRIO

public class CadastroPalestrante extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtCpf, edtEmail;
    private Button btnCadastrar;

    // Variáveis para Controller
    private PalestranteController palestranteController;
    private Palestrante palestrante;

    // Variáveis para definir valores booleanos
    // Salvos como INT pois o MySQL não aceita booleano
    private Integer sexo, gestante, sedentario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_palestrante, container, false);

        palestrante = new Palestrante();
        // Inicializa Controller
        palestranteController = new PalestranteController(this.getContext());


        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNomePlt);
        edtCpf = view.findViewById(R.id.edtCpfPlt);
        edtEmail = view.findViewById(R.id.edtEmailPlt);

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

        btnCadastrar = view.findViewById(R.id.btnCadastrarPlt);

        // BOTÃO CADASTRAR
        btnCadastrar.setOnClickListener(v -> {
            palestrante = recebeInputs();

            if (palestrante == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                palestranteController.create(1, palestrante); //VERIFICAR COMO RECEBER INPUT DE idEvento
                palestranteController.mostrarMensagem("inserido");
                limpaCamposEdt();
            }
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

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtCpf.setText(null);
        edtEmail.setText(null);
    }


}