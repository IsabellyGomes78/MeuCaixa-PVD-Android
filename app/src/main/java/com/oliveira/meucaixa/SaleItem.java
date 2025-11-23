package com.oliveira.meucaixa;

/**
 * Representa um item dentro do carrinho de compras na UI.
 * Esta é uma classe de modelo simples, não uma entidade do banco de dados.
 */
public class SaleItem {
    private final Product product;
    private int quantity;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getter para o produto
    public Product getProduct() {
        return product;
    }

    // Getter para a quantidade
    public int getQuantity() {
        return quantity;
    }

    // Setter para a quantidade (necessário para incrementar/decrementar no carrinho)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Método para calcular o preço total do item
    public double getTotalPrice() {
        // Garante que o produto não é nulo antes de acessar o preço
        if (product != null) {
            return product.getPrice() * quantity;
        }
        return 0.0;
    }
}
