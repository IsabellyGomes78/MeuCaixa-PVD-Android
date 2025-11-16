package com.oliveira.minhacaixa;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSaleViewModel extends AndroidViewModel {
    private final ProductDao productDao;
    private final VendaDao vendaDao;
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();
    public final LiveData<List<Product>> searchResults;

    public NewSaleViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        this.productDao = db.productDao();
        this.vendaDao = db.vendaDao();

        searchResults = Transformations.switchMap(searchQuery, query -> {
            if (query == null || query.trim().isEmpty()) {
                return new MutableLiveData<>(new ArrayList<>());
            }
            return productDao.searchByName("%" + query + "%");
        });
    }

    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }

    public void salvarVendaCompleta(List<SaleItem> cartItems, double totalValue) {
        databaseExecutor.execute(() -> {
            Venda novaVenda = new Venda();
            novaVenda.setPrecoTotal(totalValue);
            novaVenda.setData(System.currentTimeMillis());

            List<ItemVenda> itensParaSalvar = new ArrayList<>();
            for (SaleItem saleItem : cartItems) {
                if (novaVenda.getNomeProduto() == null) {
                    novaVenda.setNomeProduto(saleItem.getProduct().getName());
                    novaVenda.setQuantidade(saleItem.getQuantity());
                }
                itensParaSalvar.add(new ItemVenda(0, saleItem.getProduct().getId(), saleItem.getProduct().getName(), saleItem.getProduct().getPrice(), saleItem.getQuantity()));
            }
            vendaDao.salvarVendaCompleta(novaVenda, itensParaSalvar);

            // Lógica de baixa de estoque
            for (SaleItem item : cartItems) {
                Product produto = item.getProduct();
                int novoEstoque = produto.getStock() - item.getQuantity();
                produto.setStock(Math.max(0, novoEstoque));
                productDao.update(produto); // CORREÇÃO: Usando update em vez de insert/delete
            }
        });
    }
}
