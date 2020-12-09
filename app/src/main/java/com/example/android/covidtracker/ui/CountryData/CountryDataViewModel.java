package com.example.android.covidtracker.ui.CountryData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountryDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CountryDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

}