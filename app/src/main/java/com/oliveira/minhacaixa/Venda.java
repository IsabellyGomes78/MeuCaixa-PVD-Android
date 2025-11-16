package com.oliveira.minhacaixa;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "vendas")
public class Venda {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nomeProduto;
    private int quantidade;
    private double precoTotal;
    private long data; // Campo para armazenar o timestamp da venda

    // Construtor principal que o Room usará
    public Venda() {}

    @Ignore // Ignora este construtor para o Room
    public Venda(String nomeProduto, int quantidade, double precoTotal, long data) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.data = data;
    }

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(double precoTotal) { this.precoTotal = precoTotal; }

    public long getData() { return data; }
    public void setData(long data) { this.data = data; }
}
