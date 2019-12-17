package com.google.codelabs.mdc.java.shrine;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.android.volley.toolbox.NetworkImageView;
import com.google.codelabs.mdc.java.shrine.network.ImageRequester;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;

public class ProductDetailFragment extends Fragment {
    public ProductDetailFragment newInstance(ProductEntry product) {
        ProductDetailFragment fragment = new ProductDetailFragment();

        // 매개변수 추가
        Bundle bundle = new Bundle();
        bundle.putString("title", product.title);
        bundle.putString("url", product.url);
        bundle.putString("price", product.price);
        bundle.putString("detail_page", product.detail_page);
        fragment.setArguments(bundle);

        return  fragment;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_product_detail_fragment, container, false);

        // Set up the tool bar
        setUpToolbar(view);
        // 백드롭 메뉴 이벤트 설정
        //System.out.println(view.findViewById(R.id.backdrop));
        ((NavigationHost) getActivity()).setMenuNavigation(view.findViewById(R.id.backdrop));

        TextView txt_title = view.findViewById(R.id.product_title);
        TextView txt_price = view.findViewById(R.id.product_price);
        TextView txt_detail_page = view.findViewById(R.id.product_detail_page);
        NetworkImageView image = view.findViewById(R.id.product_image);

        // 매개변수를 뷰에 설정
        txt_title.setText(getArguments().getString("title"));
        txt_price.setText(getArguments().getString("price"));
        txt_detail_page.setText(getArguments().getString("detail_page"));
        ImageRequester imageRequester = ImageRequester.getInstance();
        imageRequester.setImageFromUrl(image, getArguments().getString("url"));

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.product_detail),
                new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.ic_menu_rabbit), // Menu open icon
                getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
    }
}
