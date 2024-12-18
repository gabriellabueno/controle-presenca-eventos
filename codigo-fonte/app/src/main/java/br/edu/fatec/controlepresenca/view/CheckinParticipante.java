package br.edu.fatec.controlepresenca.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.EventoController;
import br.edu.fatec.controlepresenca.controller.ParticipanteController;
import br.edu.fatec.controlepresenca.util.Evento;
import br.edu.fatec.controlepresenca.util.Participante;


public class CheckinParticipante extends Fragment {

    private EditText edtNome, edtCpf, edtEmail, edtCurso;
    private Button btnLerQrCode, btnPresenca;

    //Variável para evento
    private Evento evento;

    // Variável para Controller
    private ParticipanteController controller;

    //Variável para controller de evento
    private EventoController eventoController;

    // Variável para manipular evento selecionado em Controle
    private Integer eventoSelecionadoID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_checkin_participante, container, false);

        //Controller para o participante
        controller = new ParticipanteController(this.getContext());

        //Controler para o evento
        eventoController = new EventoController(this.getContext());

        // Elementos XML
        edtNome = view.findViewById(R.id.edtNome);
        edtCpf = view.findViewById(R.id.edtCpf);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtCurso = view.findViewById(R.id.edtCurso);
        btnLerQrCode = view.findViewById(R.id.btnLerQrCode);
        btnPresenca = view.findViewById(R.id.btnPresenca);

        edtCpf.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end > start) {
                String resultingTxt = dest.toString().substring(0, dstart) +
                        source.subSequence(start, end) +
                        dest.toString().substring(dend);

                if (!resultingTxt.matches("^\\d{0,3}(\\.\\d{0,3}){0,2}(-\\d{0,2})?$")) {
                    return "";
                }
            }
            return null;
        }});


        // Recebe ID do evento selecionada da tela Consulta
        if (getArguments() != null) {
            eventoSelecionadoID = getArguments().getInt("eventoSelecionadoID");
        }

        // Busca dados a partir do ID e armazena em instância de Evento
        evento = eventoController.read(eventoSelecionadoID);

        //Desativa checkin caso evento seja encerrado
        if(evento.getStatus().equals("Encerrado")) {
            desativaCheckin();
        }


        // BOTÕES

        // Listener botão Ler QR CODE
        btnLerQrCode.setOnClickListener(view1 -> {
            // Utiliza dependência "zxing-android-embedded"
            IntentIntegrator integrator = new IntentIntegrator(requireActivity());
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Escaneie o QR Code");
            integrator.setCameraId(0); // Uso da câmera traseira
            integrator.setBeepEnabled(true); // Som ao escanear
            integrator.setOrientationLocked(true); // Rotação automática desligada
            integrator.setBarcodeImageEnabled(false); // Não salva a imagem do QR Code
            qrCodeLauncher.launch(integrator.createScanIntent());
        });


        // Listener botão Registrar Presença
        btnPresenca.setOnClickListener(view2 -> {
            Participante participante = recebeInputs();


            if (participante == null) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos",
                        Toast.LENGTH_SHORT).show();
            } else {
                controller.create(eventoSelecionadoID, participante);
                controller.mostrarMensagem("cadastrado");
                limpaCamposEdt();
            }

        });


        return view;
    }

    // Cria um ActivityResultLauncher para manipular o resultado do scan do QR Code
    private final ActivityResultLauncher<Intent> qrCodeLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

                // Verifica se o scan foi bem sucedido e se os dados não são nulos
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {

                    // Analisa o resultado usando a classe IntentIntegrator para obter os dados do QR Code
                    IntentResult intentResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());

                    // Verifica se os dados do QR Code foram obtidos com sucesso
                    if (intentResult.getContents() != null) {
                        // Armazena dados do QR Code em uma string
                        String qrData = intentResult.getContents();

                        try {
                            // Cria um objeto JSON a partir dos dados do QR Code
                            JSONObject jsonObject = new JSONObject(qrData); // Utiliza dependência "org.json"

                            // Preenche inputs com os dados
                            edtNome.setText(jsonObject.getString("nome"));
                            edtCpf.setText(jsonObject.getString("cpf"));
                            edtEmail.setText(jsonObject.getString("email"));
                            edtCurso.setText(jsonObject.getString("curso"));

                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Erro ao analisar o QR Code: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Leitura do QR Code cancelada",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // Recebe dados do participante e armazena em nova instância de Participante
    public Participante recebeInputs() {

        if (edtNome.getText().toString().isEmpty()
                || edtCpf.getText().toString().isEmpty()
                || edtEmail.getText().toString().isEmpty()
                || edtCurso.getText().toString().isEmpty()) {
            return null;

        } else {
            Participante participante = new Participante();

            participante.setNome(edtNome.getText().toString());
            participante.setCpf(edtCpf.getText().toString());
            participante.setEmail(edtEmail.getText().toString());
            participante.setCurso(edtCurso.getText().toString());

            return participante;
        }

    }

    public void limpaCamposEdt() {
        edtNome.setText(null);
        edtCpf.setText(null);
        edtEmail.setText(null);
        edtCurso.setText(null);
    }

    public void desativaCheckin() {

        //Desativa botão de ler QR code
        btnLerQrCode.setEnabled(false);
        btnLerQrCode.setBackgroundColor(Color.DKGRAY);

        // Desativa botão Registrar Presença
        btnPresenca.setEnabled(false);
        btnPresenca.setBackgroundColor(Color.DKGRAY);

        // Desativa campos
        edtNome.setEnabled(false);
        edtCpf.setEnabled(false);
        edtEmail.setEnabled(false);
        edtCurso.setEnabled(false);
    }
}

