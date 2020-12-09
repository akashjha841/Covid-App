package com.example.android.covidtracker.ui.CountryData;

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

public class CountryDataFragment extends Fragment {

    private CountryDataViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(CountryDataViewModel.class);
        View root = inflater.inflate(R.layout.activity_list_country_data, container, false);

        return root;
    }
}