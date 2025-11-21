package com.oliveira.meucaixa;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public abstract class VendaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long inserirVenda(Venda venda);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void inserirItensVenda(List<ItemVenda> itens);

    @Transaction
    public void salvarVendaCompleta(Venda venda, List<ItemVenda> itens) {
        long vendaId = inserirVenda(venda);
        for (ItemVenda item : itens) {
            item.setVendaId(vendaId);
        }
        inserirItensVenda(itens);
    }

    @Query("SELECT * FROM vendas WHERE user_id = :userId AND data >= :startOfDay AND data < :endOfDay ORDER BY data DESC")
    public abstract LiveData<List<Venda>> getSalesForDay(long userId, long startOfDay, long endOfDay);

    @Query("SELECT * FROM vendas WHERE user_id = :userId AND data >= :startOfMonth AND data < :endOfMonth ORDER BY data DESC")
    public abstract LiveData<List<Venda>> getSalesForMonth(long userId, long startOfMonth, long endOfMonth);
}
