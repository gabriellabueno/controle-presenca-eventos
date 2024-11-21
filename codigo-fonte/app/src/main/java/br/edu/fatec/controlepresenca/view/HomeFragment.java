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

public class HomeFragment extends Fragment {

    Button btnComecar;
    TextView txtIntroducao;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Apresenta o layout do Fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnComecar = view.findViewById(R.id.btnComecar);
        txtIntroducao = view.findViewById((R.id.txtIntroducao));


        // Insere texto no TextView (txtIntroducao)
        String introducao = "\nSua jornada para uma vida saudável começa aqui. " +
                "Nós acreditamos que cada pessoa é única, e por isso oferecemos sugestões personalizadas " +
                "de alimentação e exercícios físicos levando em consideração suas características corporais." +
                "\nCom o Saúde em Dia, você terá acesso a:" +
                "\n\nSugestões Nutricionais: Dicas de refeições saudáveis adaptadas às suas necessidades." +
                "\n\nRotinas de Exercícios: Sugestões de atividades físicas que se encaixam no seu perfil e estilo de vida." +
                "\n\nPrepare-se para transformar sua saúde e bem-estar de forma simples e eficaz. Vamos juntos nessa jornada!";
        txtIntroducao.setText(introducao);


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