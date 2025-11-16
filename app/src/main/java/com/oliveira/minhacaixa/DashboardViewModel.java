package com.oliveira.minhacaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private VendaDao vendaDao;

    public DashboardViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        vendaDao = db.vendaDao();
    }

    // CORREÇÃO: Garantindo que este método existe e repassa a chamada corretamente.
    public LiveData<List<Venda>> getSalesForDay(long startOfDay, long endOfDay) {
        return vendaDao.getSalesForDay(startOfDay, endOfDay);
    }
}
