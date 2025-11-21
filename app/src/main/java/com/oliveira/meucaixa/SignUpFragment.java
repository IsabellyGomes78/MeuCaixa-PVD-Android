package com.oliveira.meucaixa;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SignUpFragment extends Fragment {

    private AuthViewModel authViewModel;
    private EditText editTextName;
    private EditText editTextDob;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        NavController navController = Navigation.findNavController(view);

        editTextName = view.findViewById(R.id.edit_text_name_signup);
        editTextDob = view.findViewById(R.id.edit_text_dob_signup);
        Button buttonConfirm = view.findViewById(R.id.button_confirm_signup);

        editTextDob.addTextChangedListener(new DateMaskTextWatcher(editTextDob));

        buttonConfirm.setOnClickListener(v -> {
            String nome = editTextName.getText().toString().trim();
            String dob = editTextDob.getText().toString().trim();

            if (TextUtils.isEmpty(nome) || dob.length() != 10) {
                Toast.makeText(getContext(), "Por favor, preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
                return;
            }

            // CORREÇÃO: Passa um callback para ser executado APÓS o término da inscrição
            authViewModel.signUp(nome, dob, () -> {
                // Este código só será executado quando o ViewModel nos "avisar" que terminou
                Toast.makeText(getContext(), "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("registeredUserName", nome);
                navController.navigate(R.id.action_signUpFragment_to_loginFragment, bundle);
            });
        });
    }
}
