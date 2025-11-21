package com.oliveira.meucaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProductListViewModel extends AndroidViewModel {

    private final ProductDao productDao;
    private final long userId;

    public ProductListViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        SessionManager sessionManager = new SessionManager(application);
        userId = sessionManager.getLoggedInUserId();
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDao.getAll(userId);
    }
}
