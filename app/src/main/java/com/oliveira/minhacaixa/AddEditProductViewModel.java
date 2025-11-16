package com.oliveira.minhacaixa;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddEditProductViewModel extends AndroidViewModel {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public AddEditProductViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<Product> getProductById(long productId) {
        return productDao.getById(productId);
    }

    public void saveProduct(Product product) {
        executorService.execute(() -> productDao.insert(product));
    }

    // CORREÇÃO: "Deletar" agora significa apenas zerar o estoque
    public void deleteProduct(Product product) {
        executorService.execute(() -> {
            product.setStock(0);
            productDao.update(product);
        });
    }
}
