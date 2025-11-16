package com.oliveira.minhacaixa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.card.MaterialCardView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private NavController navController;
    private DashboardViewModel dashboardViewModel;
    private TextView textTodaySalesValue;
    private TextView textTodaySalesCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Bind views
        MaterialCardView buttonRegisterSale = view.findViewById(R.id.button_register_sale);
        MaterialCardView buttonMyProducts = view.findViewById(R.id.button_my_products);
        MaterialCardView buttonReports = view.findViewById(R.id.button_reports);
        TextView textDate = view.findViewById(R.id.text_date);
        textTodaySalesValue = view.findViewById(R.id.text_today_sales_value);
        textTodaySalesCount = view.findViewById(R.id.text_today_sales_count);

        // Set current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        textDate.setText(currentDate);

        // Setup navigation
        buttonRegisterSale.setOnClickListener(v -> navController.navigate(R.id.navigation_sale));
        buttonMyProducts.setOnClickListener(v -> navController.navigate(R.id.navigation_products));
        buttonReports.setOnClickListener(v -> navController.navigate(R.id.navigation_reports));

        // Observe sales data
        observeTodaySales();
    }

    private void observeTodaySales() {
        // Get start and end of day
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startOfDay = calendar.getTimeInMillis();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        long endOfDay = calendar.getTimeInMillis();

        // CORREÇÃO: Chamando o método correto no ViewModel
        dashboardViewModel.getSalesForDay(startOfDay, endOfDay).observe(getViewLifecycleOwner(), vendas -> {
            if (vendas != null) {
                double total = 0;
                for (Venda venda : vendas) {
                    total += venda.getPrecoTotal();
                }
                textTodaySalesValue.setText(String.format(Locale.getDefault(), "R$ %.2f", total));
                textTodaySalesCount.setText(String.format(Locale.getDefault(), "%d venda(s)", vendas.size()));
            }
        });
    }
}
