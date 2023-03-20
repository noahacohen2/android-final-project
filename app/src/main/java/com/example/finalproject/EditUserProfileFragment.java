package com.example.finalproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.finalproject.model.User;
import com.example.finalproject.databinding.FragmentEditUserProfileBinding;

public class EditUserProfileFragment extends Fragment {

    FragmentEditUserProfileBinding binding;
    User user;

    public EditUserProfileFragment() {
        // Required empty public constructor
        this.user = new User( "123456","lal@gmail.com", "daniel",
                "sabag", "daniel123" , "i am lala");
    }

    public EditUserProfileFragment(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditUserProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.usernameTp.setText(this.user.getUsername());
        binding.firstNameTp.setText(this.user.getFirstName());
        binding.lastNameTp.setText(this.user.getLastName());
        binding.mailTp.setText(this.user.getMail());
        binding.bioTp.setText(this.user.getBio());

        onSave();

        return view;
    }

    public void onSave(){
        binding.saveEditBtn.setOnClickListener(View -> {
            User editedUser = new User(binding.mailTp.getText().toString(),
                    this.user.getPassword(),
                    binding.firstNameTp.getText().toString(),
                    binding.lastNameTp.getText().toString(),
                    binding.usernameTp.getText().toString(),
                    binding.bioTp.getText().toString());

            if(saveUserNewData(editedUser)) {
               // Todo: navigate to profile page
            } else {
                showError();
            }

        });
    }

    // todo: show error on screen
    private void showError() {
    }

    // TODO: save data to db
    private boolean saveUserNewData(User editedUser) {
        return true;
    }
}