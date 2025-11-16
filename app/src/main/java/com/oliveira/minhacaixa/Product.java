package com.oliveira.minhacaixa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Representa a tabela 'products' no banco de dados.
 * Versão em Java.
 */
@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "product_price")
    private double price;

    @ColumnInfo(name = "product_stock")
    private int stock;

    // Construtor vazio é necessário para o Room
    public Product() {
    }

    // Getters e Setters para todas as propriedades, necessários para o Room acessar os campos.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
