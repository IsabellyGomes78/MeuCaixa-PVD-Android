package com.oliveira.minhacaixa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder> {

    private final List<Product> productList;
    private final OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductSearchAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    // MÉTODO ADICIONADO PARA ATUALIZAR A LISTA
    public void setProducts(List<Product> products) {
        this.productList.clear();
        if (products != null) {
            this.productList.addAll(products);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView stock;
        private final TextView price;
        private final OnProductClickListener listener;

        public ViewHolder(@NonNull View itemView, OnProductClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = itemView.findViewById(R.id.search_result_name);
            stock = itemView.findViewById(R.id.search_result_stock);
            price = itemView.findViewById(R.id.search_result_price);
        }

        public void bind(final Product product) {
            name.setText(product.getName());
            stock.setText("Estoque: " + product.getStock());
            price.setText(String.format(Locale.getDefault(), "R$ %.2f", product.getPrice()));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductClick(product);
                }
            });
        }
    }
}
