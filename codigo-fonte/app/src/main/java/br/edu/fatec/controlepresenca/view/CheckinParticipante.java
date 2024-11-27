package br.edu.fatec.controlepresenca.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.edu.fatec.controlepresenca.R;
import br.edu.fatec.controlepresenca.controller.ParticipanteController;
import br.edu.fatec.controlepresenca.util.Participante;


public class CheckinParticipante extends Fragment {

    private EditText edtNome, edtCpf, edtEmail, edtCurso;
    private Button btnLerQrCode, btnPresenca;

    // Variável para Controller
    private ParticipanteController controller;

    // Variável para manipular evento selecionado em Controle
    private Integer eventoSelecionadoID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_checkin_participante, container, false);

        controller = new ParticipanteController(this.getContext());


        // Elementos XML
        edtNome = view.findViewById(R.id.edtNome);
        edtCpf = view.findViewById(R.id.edtCpf);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtCurso = view.findViewById(R.id.edtCurso);
        btnLerQrCode = view.findViewById(R.id.btnLerQrCode);
        btnPresenca = view.findViewById(R.id.btnPresenca);

        // Recebe ID do evento selecionada da tela Consulta
        if (getArguments() != null) {
            eventoSelecionadoID = getArguments().getInt("eventoSelecionadoID");
        }


        // BOTÕES

        // Listener botão Ler QR CODE
        btnLerQrCode.setOnClickListener(view1 -> {
            IntentIntegrator integrator = new IntentIntegrator(requireActivity());
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Escaneie um QR Code");
            integrator.setCameraId(0); // Use a câmera traseira
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

    private final ActivityResultLauncher<Intent> qrCodeLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    IntentResult intentResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
                    if (intentResult != null && intentResult.getContents() != null) {
                        // Trate os dados do QR Code aqui
                        String qrData = intentResult.getContents();
                        String[] parts = qrData.split("\\,");
                        if (parts.length == 4) {
                            edtNome.setText(parts[0]);
                            edtCpf.setText(parts[1]);
                            edtEmail.setText(parts[2]);
                            edtCurso.setText(parts[3]);
                        } else {
                            Toast.makeText(getContext(), "Formato inválido de QR Code!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Leitura do QR Code cancelada", Toast.LENGTH_SHORT).show();
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
}

