package com.google.codelabs.mdc.java.shrine.staggeredgridlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.shrine.NavigationHost;
import com.google.codelabs.mdc.java.shrine.ProductDetailFragment;
import com.google.codelabs.mdc.java.shrine.R;
import com.google.codelabs.mdc.java.shrine.network.ImageRequester;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class StaggeredProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredProductCardViewHolder> {

    private List<ProductEntry> productList;
    private ImageRequester imageRequester;
    FragmentActivity mainActivity;


    public StaggeredProductCardRecyclerViewAdapter(FragmentActivity activity, List<ProductEntry> productList) {
        this.mainActivity = activity;
        this.productList = productList;
        imageRequester = ImageRequester.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.shr_staggered_product_card_first;
        if (viewType == 1) {
            layoutId = R.layout.shr_staggered_product_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.shr_staggered_product_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            final ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            //holder.productDetail.setText(product.detail_page);
            imageRequester.setImageFromUrl(holder.productImage, product.url);
            // 제품명을 다른 상세화면에 넘기기
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    System.out.println("-- " + product.title +  " clicked --");
                    // 상세화면으로 이동
                    ((NavigationHost) mainActivity).navigateTo(new ProductDetailFragment().newInstance(product)
                            , true);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
