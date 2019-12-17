package com.google.codelabs.mdc.java.shrine;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationHost {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_main_activity);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }


    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
            System.out.println("-- add to backstack --");
        }

        transaction.commit();
    }
    @Override
    public  void setMenuNavigation(View view) {
        // view == backdrop의 겉에 있는 LinearLayout

        view.findViewById(R.id.menu_mammalia_label).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("-- btn_mammalia ACTION_UP --");
                        navigateTo(new AniMammaliaFragment(), true);
                        return false;
                }

                return false;
            }
        });
        view.findViewById(R.id.menu_reptilia_label).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("-- btn_reptilia ACTION_UP --");
                        navigateTo(new AniReptiliaFragment(), true);
                        return false;
                }
                return false;
            }
        });
        view.findViewById(R.id.menu_alage_label).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("-- btn_reptilia ACTION_UP --");
                        navigateTo(new AniAlgaeFragment(), true);
                        return false;
                }
                return false;
            }
        });
        view.findViewById(R.id.menu_all_label).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("-- btn_reptilia ACTION_UP --");
                        navigateTo(new ProductGridFragment(), true);
                        return false;
                }
                return false;
            }
        });
    }
}