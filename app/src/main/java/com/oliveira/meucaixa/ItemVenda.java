package com.oliveira.meucaixa;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "sale_items",
        primaryKeys = {"vendaId", "produtoId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Venda.class,
                        parentColumns = "id",
                        childColumns = "vendaId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "id",
                        childColumns = "produtoId",
                        onDelete = ForeignKey.RESTRICT
                )
        },
        indices = {@Index("vendaId"), @Index("produtoId")}
)
public class ItemVenda {
    private long vendaId;
    private long produtoId;
    private String nomeProduto;
    private double precoProduto;
    private int quantidade;

    public ItemVenda(long vendaId, long produtoId, String nomeProduto, double precoProduto, int quantidade) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public long getVendaId() { return vendaId; }
    public void setVendaId(long vendaId) { this.vendaId = vendaId; }
    public long getProdutoId() { return produtoId; }
    public void setProdutoId(long produtoId) { this.produtoId = produtoId; }
    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public double getPrecoProduto() { return precoProduto; }
    public void setPrecoProduto(double precoProduto) { this.precoProduto = precoProduto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
