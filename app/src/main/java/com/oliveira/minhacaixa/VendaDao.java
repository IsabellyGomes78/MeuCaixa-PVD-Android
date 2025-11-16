package com.oliveira.minhacaixa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

@Dao
public abstract class VendaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long inserirVenda(Venda venda); // Síncrono

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void inserirItensVenda(List<ItemVenda> itens); // Síncrono

    @Transaction
    public void salvarVendaCompleta(Venda venda, List<ItemVenda> itens) {
        long vendaId = inserirVenda(venda);
        for (ItemVenda item : itens) {
            item.setVendaId(vendaId);
        }
        inserirItensVenda(itens);
    }

    @Query("SELECT * FROM vendas ORDER BY data DESC")
    public abstract LiveData<List<Venda>> obterTodasAsVendas();

    @Query("SELECT * FROM vendas WHERE data >= :startOfDay AND data < :endOfDay ORDER BY data DESC")
    public abstract LiveData<List<Venda>> getSalesForDay(long startOfDay, long endOfDay);

    // NOVO MÉTODO:
    @Query("SELECT * FROM vendas WHERE data >= :startOfMonth AND data < :endOfMonth ORDER BY data DESC")
    public abstract LiveData<List<Venda>> getSalesForMonth(long startOfMonth, long endOfMonth);
}
