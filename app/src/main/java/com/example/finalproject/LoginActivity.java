package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.finalproject.databinding.ActivityLoginBinding;
import com.example.finalproject.model.User;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onNotSingedIn();
        onLogin();
    }

    private void onNotSingedIn() {
        binding.signinTv.setOnClickListener(View -> {
            changeActivity(SigninActivity.class);
        });
    }

   public void onLogin(){
       binding.loginBtn.setOnClickListener(View -> {
           User user = new User(binding.usernameTp.getText().toString(),
                                binding.passwordTp.getText().toString());
          if(isAuth(user)) {
              changeActivity(MainActivity.class);
          } else {
              showError();
          }

       });
    }

    private void changeActivity(Class activityClass) {
        Intent intent = new Intent(LoginActivity.this, activityClass);
        startActivity(intent);
    }

    private void showError() {
        // TODO
    }

    public boolean isAuth(User userToCheck) {
        // TODO
        return true;
    }
}