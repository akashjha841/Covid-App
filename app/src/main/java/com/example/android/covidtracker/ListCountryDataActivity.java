package com.example.android.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListCountryDataActivity extends AppCompatActivity {

    private RecyclerView rv_country_wise;
    private CountryWiseAdapter countryWiseAdapter;
    private ArrayList<CountryWiseModel> countryWiseModelArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText et_search;

    private String str_country, str_confirmed, str_confirmed_new, str_active, str_active_new, str_recovered, str_recovered_new,
            str_death, str_death_new, str_tests;

    private WorldDataActivity activity = new WorldDataActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_country_data);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_country_data:

                        Intent intent = new Intent(getApplicationContext(), ListCountryDataActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_world_data:

                        Intent intent1 = new Intent(getApplicationContext(), WorldDataActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.nav_about_us:

                        Intent intent2 = new Intent(getApplicationContext(), AboutUsActivity.class);
                        startActivity(intent2);
                        return true;

                    case R.id.nav_home:

                        Intent intent3 = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent3);
                        return true;



                }
                return false;
            }
        });

        getSupportActionBar().setTitle("World Data (Select Country)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Init();
        FetchCountryWiseData();

        //Setting swipe refresh layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FetchCountryWiseData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //Search
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Filter(s.toString());
            }
        });
    }


        private void Filter(String text) {
        ArrayList<CountryWiseModel> filteredList = new ArrayList<>();
        for (CountryWiseModel item : countryWiseModelArrayList) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        countryWiseAdapter.filterList(filteredList, text);
    }


    private void FetchCountryWiseData() {
        //Show progress dialog
        activity.ShowDialog(this);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiURL = "https://corona.lmao.ninja/v2/countries";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                apiURL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            countryWiseModelArrayList.clear();
                            for (int i=0;i<response.length(); i++){
                                JSONObject countryJSONObject = response.getJSONObject(i);

                                str_country = countryJSONObject.getString("country");
                                str_confirmed = countryJSONObject.getString("cases");
                                str_confirmed_new = countryJSONObject.getString("todayCases");
                                str_active = countryJSONObject.getString("active");
                                str_recovered = countryJSONObject.getString("recovered");
                                str_death = countryJSONObject.getString("deaths");
                                str_death_new = countryJSONObject.getString("todayDeaths");
                                str_tests = countryJSONObject.getString("tests");
                                JSONObject flagObject = countryJSONObject.getJSONObject("countryInfo");
                                String flagUrl = flagObject.getString("flag");

                                //Creating an object of our country model class and passing the values in the constructor
                                CountryWiseModel countryWiseModel  = new CountryWiseModel(str_country, str_confirmed, str_confirmed_new, str_active,
                                        str_death, str_death_new, str_recovered, str_tests, flagUrl);
                                //adding data to our arraylist
                                countryWiseModelArrayList.add(countryWiseModel);
                            }
                            Collections.sort(countryWiseModelArrayList, new Comparator<CountryWiseModel>() {
                                @Override
                                public int compare(CountryWiseModel o1, CountryWiseModel o2) {
                                    if (Integer.parseInt(o1.getConfirmed())> Integer.parseInt(o2.getConfirmed())){
                                        return -1;
                                    } else {
                                        return 1;
                                    }
                                }
                            });
                            Handler makeDelay = new Handler();
                            makeDelay.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    countryWiseAdapter.notifyDataSetChanged();
                                    activity.DismissDialog();
                                }
                            }, 1000);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


    private void Init() {
        swipeRefreshLayout = findViewById(R.id.activity_country_wise_swipe_refresh_layout);
        et_search = findViewById(R.id.activity_country_wise_search_editText);

        rv_country_wise = findViewById(R.id.activity_country_wise_recyclerview);
        rv_country_wise.setHasFixedSize(true);
        rv_country_wise.setLayoutManager(new LinearLayoutManager(this));

        countryWiseModelArrayList = new ArrayList<>();
        countryWiseAdapter = new CountryWiseAdapter(ListCountryDataActivity.this, countryWiseModelArrayList);
        rv_country_wise.setAdapter(countryWiseAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}