package br.edu.fatec.controlepresenca.view.evento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.sql.Date;
import java.sql.Time;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.util.Evento;

public class CadastroEvento extends Fragment {

    // Variáveis para componentes XML
    private EditText edtNome, edtLocal, edtOrganizador,
            edtData, edtHoraInicio, edtHoraFim;
    private TextView txtNovoPalestrante;
    private Button btnCadastrar;

    // Variáveis para Controller
    private EventoController eventoController;
    private Evento evento;

    // Variáveis para definir valores booleanos
    // Salvos como INT pois o MySQL não aceita booleano
    private Integer status;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Infla o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_evento, container, false);

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
        txtNovoPalestrante = view.findViewById(R.id.txtNovoPalestrante);

        btnCadastrar = view.findViewById(R.id.btnCadastrar);

        // BOTÃO CADASTRAR
        btnCadastrar.setOnClickListener(v -> {
            evento = recebeInputs();


            if (evento == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                eventoController.create(evento);
                eventoController.mostrarMensagem("inserido");
                limpaCamposEdt();
            }
        });


        txtNovoPalestrante.setOnClickListener(v -> {
            //Mudar para fragment CadastroPalestrante
            NavController navController = NavHostFragment.findNavController(this);
            //navController.navigate(R.id.action_home_to_cadastro);
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

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtLocal.setText(null);
        edtOrganizador.setText(null);
        edtData.setText(null);
        edtHoraInicio.setText(null);
        edtHoraFim.setText(null);
    }

}