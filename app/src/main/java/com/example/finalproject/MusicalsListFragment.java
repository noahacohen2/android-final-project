package com.example.finalproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.finalproject.databinding.FragmentMusicalsListBinding;
import com.example.finalproject.model.Model;
import com.example.finalproject.model.Musical;
import com.example.finalproject.model.Review;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MusicalsListFragment extends Fragment {

    List<Musical> data = new LinkedList<>();
    MusicalRecyclerAdapter adapter;
    FragmentMusicalsListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMusicalsListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.musicalFragList.setHasFixedSize(true);
        binding.musicalFragList.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MusicalRecyclerAdapter(getLayoutInflater(),data);
        binding.musicalFragList.setAdapter(adapter);


        adapter.setOnItemClickListener(new MusicalRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int pos) {
                Musical mus = data.get(pos);
                // todo: add musical as param
                Navigation.findNavController(view)
                        .navigate(R.id.action_musicalsListFragment_to_musicalFragment);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    void reloadData(){
        binding.progressBar.setVisibility(View.VISIBLE);
        data = Model.instance().getAllMusicals();
        adapter.setData(data);
        binding.progressBar.setVisibility(View.GONE);

        // todo: add callback
//        Model.instance().getAllMusicals((stList)->{
//            data = stList;
//            adapter.setData(data);
//            binding.progressBar.setVisibility(View.GONE);
//        });
    }
}