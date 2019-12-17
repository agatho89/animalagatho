package com.google.codelabs.mdc.java.shrine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.shrine.network.ImageRequester;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter used to show a simple grid of products.
 */
public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {
    // 1. 데이터 목록을 가지고 있는 컬렉션
    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    ProductCardRecyclerViewAdapter(List<ProductEntry> productList) {
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }
    public ProductEntry getProduct(int poistion){
        return this.productList.get(poistion);
    }
    // 2. 뷰홀더가 생성될 때 처리
    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }
    // 3. 뷰홀더와 데이터가 바인딩 할 때 처리
    // 뷰홀더 객체와 각 아이템의 포지션 값을 넘겨줌
    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
        if (productList != null && position < productList.size()) {
            // 1. 어댑터의 데이터 컬렉션에서 position위치의 객체 선택
            ProductEntry product = productList.get(position);
            // 2. 뷰홀더 객체에 속성 설정 - 뷰홀더의 뷰객체와 데이터 객체간의 바인딩
            // 텍스트뷰(화면).setText(데이터)
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            // ..setImageFromUrl(이미지뷰(화면), url(데이터)
            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }
    // 4. 데이터의 개수를 반환
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
