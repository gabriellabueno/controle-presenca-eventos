package br.edu.fatec.diariosaude.view.evento;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.edu.fatec.diariosaude.R;
import br.edu.fatec.diariosaude.controller.ParticipanteController;

public class CadastroEvento extends Fragment {
    
    // Variáveis para componentes XML
    private EditText edtNome, edtGenero, edtIdade, edtAltura, edtPeso;
    private Switch swtSedentario;
    private RadioButton rdbMasculino, rdbFeminino;
    private Switch swtGestante;
    private Button btnCadastrar;

    // Variáveis para Controller
    private ParticipanteController pessoaController;
    private Pessoa pessoa;

    // Variáveis para definir valores booleanos
    // Salvos como INT pois o MySQL não aceita booleano
    private Integer sexo, gestante, sedentario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        pessoa = new Pessoa();
        // Inicializa Controller
        pessoaController = new ParticipanteController(this.getContext());


        // Variáveis para componentes XML
        edtNome = view.findViewById(R.id.edtNome);
        edtGenero = view.findViewById(R.id.edtGenero);
        edtIdade = view.findViewById(R.id.edtIdade);
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

        edtPeso = view.findViewById(R.id.edtPeso);
        swtSedentario = view.findViewById(R.id.swtSedentario);
        rdbMasculino = view.findViewById(R.id.rdbMasculino);
        rdbFeminino = view.findViewById(R.id.rdbFeminino);
        swtGestante = view.findViewById(R.id.swtGestante);
        btnCadastrar = view.findViewById(R.id.btnCadastrar);


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


        // BOTÃO CADASTRAR
        btnCadastrar.setOnClickListener(v -> {
            pessoa = recebeInputs();

            /*
            if(pessoa == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
               if (pessoa.getIdade() <18)
                    mostraPopup();
               else {
                    pessoaController.create(pessoa);
                    pessoaController.mostrarMensagem("inserida");
                    limpaCamposEdt();
                }

            }
             */

            sexo = 0;
            gestante = 0;
            sedentario = 0;
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
            return pessoa;
        }

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