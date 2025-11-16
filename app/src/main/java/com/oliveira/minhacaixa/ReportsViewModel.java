package com.oliveira.minhacaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ReportsViewModel extends AndroidViewModel {

    private VendaDao vendaDao;

    public ReportsViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        vendaDao = db.vendaDao();
    }

    public LiveData<List<Venda>> getSalesForDay(long startOfDay, long endOfDay) {
        return vendaDao.getSalesForDay(startOfDay, endOfDay);
    }

    public LiveData<List<Venda>> getSalesForMonth(long startOfMonth, long endOfMonth) {
        return vendaDao.getSalesForMonth(startOfMonth, endOfMonth);
    }
}
