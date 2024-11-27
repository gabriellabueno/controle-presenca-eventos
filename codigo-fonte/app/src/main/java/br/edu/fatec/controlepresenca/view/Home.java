package br.edu.fatec.controlepresenca.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import br.edu.fatec.controlepresenca.R;

public class Home extends Fragment {

    private TextView tvSaudacao;
    private TextView tvInstrucoes;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvSaudacao = view.findViewById(R.id.tvSaudacao);
        tvInstrucoes = view.findViewById(R.id.tvInstrucoes);

        tvSaudacao.setText("Bem Vindo (a)! \n\n Gerencie seus eventos de forma prática! Cadastre, edite e adicione participantes rapidamente utilizando QR Code.");
        tvInstrucoes.setText("Para começar, clique no menu lateral (ícone de três linhas no canto superior esquerdo) e escolha a opção desejada. Navegue pelas diferentes funcionalidades e explore tudo o que o app oferece!");

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}