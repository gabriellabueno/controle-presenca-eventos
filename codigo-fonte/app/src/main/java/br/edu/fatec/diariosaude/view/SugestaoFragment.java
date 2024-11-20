package br.edu.fatec.diariosaude.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.List;

import br.edu.fatec.diariosaude.R;
import br.edu.fatec.diariosaude.controller.PessoaController;
import br.edu.fatec.diariosaude.util.Pessoa;
import br.edu.fatec.diariosaude.util.Evento;
import br.edu.fatec.diariosaude.util.PessoaAdapter;

public class SugestaoFragment extends Fragment {

    private ListView listViewSugestao;

    // Variáveis para Controller
    private PessoaController controller;

    // Adapter para apresentar dados no ListView
    PessoaAdapter adapter;

    // Variáveis para componentes XML
    private TextView edtIMC, edtIndiceIMC, txtNutricao, txtAtvFisica, txtSedentario;
    private LinearLayout layoutSugestoes;
    private Button btnReferencia;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_sugestao, container, false);

        // Inicializando o controller
        controller = new PessoaController(this.getContext());

        // Variáveis para componentes XML
        edtIMC = view.findViewById(R.id.edtIMC);
        edtIndiceIMC = view.findViewById(R.id.edtIndiceIMC);
        txtNutricao = view.findViewById(R.id.txtNutricao);
        txtAtvFisica = view.findViewById(R.id.txtAtvFisica);
        txtSedentario = view.findViewById(R.id.txtSedentario);
        btnReferencia = view.findViewById(R.id.btnReferencia);
        layoutSugestoes = view.findViewById(R.id.layout_sugestoes);
        listViewSugestao = view.findViewById(R.id.listViewSugestao);

        layoutSugestoes.setVisibility(View.GONE);
        txtSedentario.setVisibility(View.GONE);

        // Listar pessoas no ListView
        List<Pessoa> pessoas = listAll();
        if(pessoas == null) {
            controller.updateTable();
        }

        // Clica no item da ListView leva para a tela de Manutenção
        listViewSugestao.setOnItemClickListener((parent, v, position, id) -> {

            // Obtém o pessoa clicada
            Pessoa pessoaSelecionada = adapter.getItem(position);
           // Integer pessoaID = pessoaSelecionada.getId();

            /*
            if(pessoaID != null) {
                pessoaSelecionada = controller.read(pessoaID);
                apresentaSugestao(pessoaSelecionada);
                layoutSugestoes.setVisibility(View.VISIBLE);
            }
             */

        });

        // Mostra referências quando clilca no TextView
        btnReferencia.setOnClickListener( v -> {
            mostraPopup();
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

    public void apresentaSugestao(Pessoa pessoa) {
        limparCampos();


        // Formata IMC p/ 2 casas decimais
        DecimalFormat decimalFormat = new DecimalFormat("#.00");


    }

    // Listar todos os alunos na ListvIiew
    public List<Pessoa> listAll() {

        // Popula array com dados do BD
        List<Pessoa> pessoas = controller.listAll();

        // Envia array para adapter
        adapter = new PessoaAdapter(getContext(), pessoas);

        // Popula ListView com itens do array
        listViewSugestao.setAdapter(adapter);
        return pessoas;
    }

    private void mostraPopup() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_dialog);

        // Configurando os elementos do poup
        TextView title = dialog.findViewById(R.id.popup_title);
        TextView text = dialog.findViewById(R.id.popup_txt);
        Button button = dialog.findViewById(R.id.popup_button);

        title.setText("Referências");
        text.setText("Diretrizes da OMS para atividade física e comportamento sedentário: \nhttps://iris.who.int/bitstream/handle/10665/337001/9789240014886-por.pdf?sequence=102&isAllowed=y#:~:text=Para%20saúde%20e%20bem-estar,dia%20para%20crianças%20e%20adolescentes.\n\n" +
                "Instituto Nacional de Saúde dos EUA (NIH): \nhttps://www.nhlbi.nih.gov/health-topics/physical-activity\n\n" +
                "Organização Mundial da Saúde (OMS): \nhttps://www.who.int/news-room/fact-sheets/detail/physical-activity\n\n" +
                "Organização Mundial da Saúde (OMS) - Nutrição e envelhecimento: \nhttps://www.who.int/news-room/fact-sheets/detail/healthy-ageing\n\n" +
                "Institutos Nacionais de Saúde (NIH) - National Institute on Aging: \nhttps://www.nia.nih.gov/news/research-focuses-aging-nutrition\n\n" +
                "Sociedade Brasileira de Geriatria e Gerontologia (SBGG) - Nutrição na terceira idade: \nhttps://www.sbgg.org.br/ ");

        button.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public void limparCampos() {
        edtIMC.setText(null);
        edtIndiceIMC.setText(null);
        txtNutricao.setText(null);
        txtAtvFisica.setText(null);
        txtSedentario.setText(null);
        txtSedentario.setVisibility(View.GONE);
    }

}