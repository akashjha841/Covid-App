package com.example.android.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class AboutUsActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_about_us);


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
    }


}