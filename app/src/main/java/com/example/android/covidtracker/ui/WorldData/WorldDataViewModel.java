package com.example.android.covidtracker.ui.WorldData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorldDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WorldDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}