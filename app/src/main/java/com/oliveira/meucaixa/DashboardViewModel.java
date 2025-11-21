package com.oliveira.meucaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private final VendaDao vendaDao;
    private final long userId;

    public DashboardViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        vendaDao = db.vendaDao();
        
        SessionManager sessionManager = new SessionManager(application);
        userId = sessionManager.getLoggedInUserId();
    }

    public LiveData<List<Venda>> getSalesForDay(long startOfDay, long endOfDay) {
        return vendaDao.getSalesForDay(userId, startOfDay, endOfDay);
    }
}
