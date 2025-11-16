package com.oliveira.minhacaixa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI; // 1. IMPORT NECESSÁRIO
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);

        // Acessa o NavController a partir do NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // --- A SOLUÇÃO FINAL ---
        // 2. Voltando a usar a linha "mágica" do NavigationUI.
        // Agora que o projeto está limpo e 100% em Java, esta é a forma correta e mais estável
        // de conectar a barra de navegação ao controlador.
        // Ela gerencia a navegação entre as abas e a pilha de navegação (back stack) automaticamente.
        NavigationUI.setupWithNavController(bottomNavView, navController);
    }
}
