package com.oliveira.minhacaixa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products WHERE product_stock > 0 ORDER BY product_name ASC")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM products WHERE product_name LIKE :query AND product_stock > 0 ORDER BY product_name ASC")
    LiveData<List<Product>> searchByName(String query);

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    LiveData<Product> getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product); // NOVO MÉTODO

    @Delete
    void delete(Product product);

    @Query("DELETE FROM products WHERE id = :productId")
    void deleteById(long productId);
}
