package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.finalproject.databinding.ActivitySigninBinding;
import com.example.finalproject.model.User;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onSignin();
    }

    private void onSignin() {
        binding.signinBtn.setOnClickListener(View -> {
            User userToAdd = new User(binding.usernameTp.getText().toString(),
                                 binding.passwordTp.getText().toString(),
                                 binding.firstNameTp.getText().toString(),
                                 binding.lastNameTp.getText().toString(),
                                 binding.mailTp.getText().toString(),
                                 binding.bioTp.getText().toString());

            if(saveUser(userToAdd)){
                changeActivity(MainActivity.class);
            } else {

            }
        });
    }

    private void changeActivity(Class activityClass) {
        Intent intent = new Intent(SigninActivity.this, activityClass);
        startActivity(intent);
    }
    private boolean saveUser(User userToAdd) {
        // TODO
        return true;
    }
}