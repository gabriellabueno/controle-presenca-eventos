package br.edu.fatec.controlepresenca.view.pessoa;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.ParticipanteController;

public class ManutencaoPessoa extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtGenero, edtIdade, edtAltura, edtPeso;
    private Switch swtSedentario;
    private RadioButton rdbMasculino, rdbFeminino;
    private Switch swtGestante;
    private Button btnAtualizar, btnExcluir;

    // Variáveis para controller:
    private ParticipanteController controller;
    private Pessoa pessoa;

    // Variáveis para definir valores booleanos
    // Salvos como INT pois o MySQL não aceita booleano
    private Integer sexo, gestante, sedentario;

    // Variável para manipular pessoa selecionada em Controle
    private Integer pessoaSelecionadaID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_manutencao, container, false);

        // Inicializa Controller
        controller = new ParticipanteController(this.getContext());

        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNome);
        edtGenero = view.findViewById(R.id.edtGenero);
        edtIdade = view.findViewById(R.id.edtIdade);
        edtPeso = view.findViewById(R.id.edtPeso);
        edtAltura = view.findViewById(R.id.edtAltura);

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

        swtSedentario = view.findViewById(R.id.swtSedentario);
        rdbMasculino = view.findViewById(R.id.rdbMasculino);
        rdbFeminino = view.findViewById(R.id.rdbFeminino);
        swtGestante = view.findViewById(R.id.swtGestante);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);
        btnExcluir = view.findViewById(R.id.btnExcluir);

        // Recebe ID da pessoa selecionada da tela Controle
        if (getArguments() != null) {
            pessoaSelecionadaID = getArguments().getInt("pessoaSelecionadaID");
        }

        // Busca dados a partir do ID e armazena em instância de Pessoa
        //pessoa = controller.read(pessoaSelecionadaID);


        swtGestante.setVisibility(View.GONE); // switch gestante invisível até selecionar sexo

        // RADIO BUTTON FEMININO
        // Listener para mudanças na seleção dos RadioButtons
        rdbFeminino.setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if(rdbFeminino.isChecked()) {
                        swtGestante.setVisibility(View.VISIBLE);
                        sexo = 1;
                    } else {
                        swtGestante.setVisibility(View.GONE);
                        sexo = 0;
                    }
                }
        );

        // SWITCH GESTANTE
        swtGestante.setOnCheckedChangeListener((compoundButton, b) -> {
            gestante = swtGestante.isChecked() ? 1 : 0;
        });


        // SWITCH SEDENTARIO
        swtSedentario.setOnCheckedChangeListener((compoundButton, b) -> {
            sedentario = swtSedentario.isChecked() ? 1 : 0;
        });



        // Preenche campos de inputs com os dados retornados
        preencheCamposEdt(pessoa);


        // BOTÃO ATUALIZAR
        btnAtualizar.setOnClickListener(v -> {
            pessoa = recebeInputs();

            if(pessoa == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                if (pessoa.getIdade() <18)
                    mostraPopup();
                else {
                    pessoa.setId(pessoaSelecionadaID);
                   // controller.update(pessoa);
                    controller.mostrarMensagem("atualizada");
                }
            }

            sexo = 0;
            gestante = 0;
            sedentario = 0;
        });


        //Botão Excluir
        btnExcluir.setOnClickListener(v -> {
            pessoa = new Pessoa();
            pessoa.setId(pessoaSelecionadaID);
           // controller.delete(pessoa);
            controller.mostrarMensagem("removida");
            limpaCamposEdt();
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    // Recebe dados do usuário e armazena em nova instância de Pessoa
    public Pessoa recebeInputs() {
        if (edtNome.getText().toString().isEmpty()
                || edtIdade.getText().toString().isEmpty()
                || edtAltura.getText().toString().isEmpty()
                || edtPeso.getText().toString().isEmpty()
                || !rdbFeminino.isChecked() && !rdbMasculino.isChecked())
        {
            return null;
        } else {
            pessoa = new Pessoa();
            pessoa.setNome(edtNome.getText().toString());
            pessoa.setGenero(edtGenero.getText().toString());
            pessoa.setIdade(Integer.parseInt(edtIdade.getText().toString()));
            pessoa.setAltura(Float.parseFloat(edtAltura.getText().toString()));
            pessoa.setPeso(Double.parseDouble(edtPeso.getText().toString()));
            pessoa.setSexo(sexo);
            pessoa.setGestante(gestante);
            pessoa.setSedentario(sedentario);
            return pessoa;
        }
    }

    // Preenche campos de inputs com os dados da pessoa para Manutencao
    public void preencheCamposEdt(Pessoa pessoa) {
        edtNome.setText(pessoa.getNome());
        edtGenero.setText(pessoa.getGenero());
        edtIdade.setText(String.valueOf(pessoa.getIdade()));

        String alturaFormatada = new DecimalFormat("#.00").format(pessoa.getAltura());
        edtAltura.setText(alturaFormatada);

        edtPeso.setText(String.valueOf(pessoa.getPeso()));

        if(pessoa.getSexo() == 1) {
            rdbFeminino.setChecked(true);
            swtGestante.setVisibility(View.VISIBLE);
        } else {
            rdbMasculino.setChecked(true);
            swtGestante.setVisibility(View.GONE);
        }

        if(pessoa.isGestante() == 1) {
            swtGestante.setChecked(true);
        }

        if(pessoa.isSedentario() == 1)
            swtSedentario.setChecked(true);
    }

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtGenero.setText(null);
        edtIdade.setText(null);
        edtAltura.setText(null);
        edtPeso.setText(null);
        swtGestante.setVisibility(View.GONE);
        rdbFeminino.setChecked(false);
        rdbMasculino.setChecked(false);
        swtSedentario.setChecked(false);
    }

    private void mostraPopup() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_dialog);

        // Configurando os elementos do poup
        TextView title = dialog.findViewById(R.id.popup_title);
        TextView text = dialog.findViewById(R.id.popup_txt);
        Button button = dialog.findViewById(R.id.popup_button);

        title.setText("Aviso");
        text.setText("Para uma melhor experiência esse aplicativo é destinado apenas para pessoas que atingiram a maioridade. " +
                "\n\nO cálculo de IMC para crianças e jovens depende de outros critérios específicos.");


        button.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }



}