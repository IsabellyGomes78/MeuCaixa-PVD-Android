package com.oliveira.minhacaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProductListViewModel extends AndroidViewModel {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductListViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAll(); // CORREÇÃO: Chamando o método correto
    }

    LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
