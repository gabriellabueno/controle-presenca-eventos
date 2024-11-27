package br.edu.fatec.controlepresenca.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import br.edu.fatec.controlepresenca.R;

public class Home extends Fragment {

    Button btnComecar;
    TextView txtIntroducao;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnComecar = view.findViewById(R.id.btnComecar);

        // BOTÃO COMEÇAR
        // Redireciona para tela de Cadastro
        btnComecar.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_home_to_cadastro);
        });


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}