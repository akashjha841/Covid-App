package com.example.android.covidtracker.ui.WorldData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.covidtracker.R;

public class WorldDataFragment extends Fragment {

    private WorldDataViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(WorldDataViewModel.class);
        View root = inflater.inflate(R.layout.nav_world_data, container, false);

        return root;
    }
}