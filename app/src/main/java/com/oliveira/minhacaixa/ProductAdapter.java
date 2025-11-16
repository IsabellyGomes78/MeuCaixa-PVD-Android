package com.oliveira.minhacaixa;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private final OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = productList.get(position);
        holder.bind(currentProduct, listener);
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final TextView stockTextView;
        private final ImageView stockWarningIcon;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_product_name);
            priceTextView = itemView.findViewById(R.id.text_product_price);
            stockTextView = itemView.findViewById(R.id.text_product_stock);
            stockWarningIcon = itemView.findViewById(R.id.icon_stock_warning);
        }

        public void bind(final Product product, final OnProductClickListener listener) {
            nameTextView.setText(product.getName());
            priceTextView.setText(String.format(Locale.getDefault(), "R$ %.2f", product.getPrice()));
            stockTextView.setText(String.format(Locale.getDefault(), "Estoque: %d", product.getStock()));

            // Lógica para estoque baixo
            if (product.getStock() <= 5) { // Limite de estoque baixo
                stockTextView.setTextColor(Color.RED);
                stockWarningIcon.setVisibility(View.VISIBLE);
            } else {
                stockTextView.setTextColor(Color.GRAY);
                stockWarningIcon.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(v -> listener.onProductClick(product));
        }
    }
}
