package com.google.codelabs.mdc.java.shrine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);
        final TextInputLayout loginTextInput = view.findViewById(R.id.login_text_input);
        final TextInputEditText loginEditText = view.findViewById(R.id.login_user_name);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);

        view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        super.onCreate(savedInstanceState);
        CheckBox autoLoginCheck = view.findViewById(R.id.autoLoginCheck);
        SharedPreferences setting = this.getActivity().getSharedPreferences("setting", 0);
        final SharedPreferences.Editor editor =setting.edit();
    autoLoginCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked) {
                String ID = loginEditText.getText().toString();
                String PW = passwordEditText.getText().toString();

                editor.putString("ID", ID);
                editor.putString("PW", PW);
                editor.putBoolean("autoLoginCheck_enabled", true);
                editor.commit();
            }else {
                editor.clear();
                editor.commit();
            }
        }
    });
        if(setting.getBoolean("autoLoginCheck_enabled", false)){

            loginEditText.setText(setting.getString("ID", ""));
            passwordEditText.setText(setting.getString("PW", ""));
            autoLoginCheck.setChecked(true);

        }



        // Snippet from "Navigate to the next Fragment" section goes here.
        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nickName(loginEditText.getText())) {
                    loginTextInput.setError(getString(R.string.shr_error_name));
                } else {
                    loginTextInput.setError(null);

                    if (!password(passwordEditText.getText())) {
                        passwordTextInput.setError(getString(R.string.shr_error_password));
                    } else {
                        passwordTextInput.setError(null);
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), true); // Navigate to the next Fragment
                }
                    // Clear the error
                    // Navigate to the next Fragment
                    // getActivity() 현재 엑티비티 객체를 획득
                    // (NavigationHost) getActivity() 획득한 엑티비티 객체를 NavigationHost 타입으로 변환
                    // -> NavigationHost 타입은 Interface
                    // ((NavigationHost) getActivity()).navigateTo(..)
                    // -> NavigationHost 인터페이스(Interface)를 구현한(Implementation) 클래스의 navigateTo가 호출
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), true); // Navigate to the next Fragment
                }
            }
        });

        // Clear the error once more than 8 characters are typed.
        /* passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error

                }
                return false;
            }
        }); */
        return view;
    }


    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    /*
   In reality, this will have more complex logic including, but not limited to, actual
   authentication of the username and password.
*/
    private boolean nickName(@Nullable Editable text) {
        return text != null && text.length() >= 2;
    }
    private boolean password(@Nullable Editable text) {
        return text != null && text.length() >= 6;
    }
}
