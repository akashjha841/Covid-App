package com.example.android.covidtracker;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private int STORAGE_PERMISSION_CODE = 123;
    Button btnLogout, btnPermission, btnCovid, btnWorld, btnWebsite, btnCall ;
    ImageView cleanYourHand,wm,cc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nav_home);

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
                return true;
            }
        });


        btnLogout = findViewById(R.id.logout);
        btnPermission = findViewById(R.id.permission);
        btnWorld = findViewById(R.id.btnWorld);
        btnWebsite = findViewById(R.id.website);
        btnCall = findViewById(R.id.call);
        cleanYourHand = findViewById(R.id.cleanYourHand);
          cc = findViewById(R.id.imageView3);
          wm= findViewById(R.id.imageView5);

        cleanYourHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,HandWashActivity.class);
                startActivity(intent);
            }
        });

        wm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this,WearMaskActivity.class);
                startActivity(intent);
            }
        });
        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this,CloseContactActivity.class);
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+18337844397"));
                startActivity(intent);
            }
        });


        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,WebsiteActivity.class);
                startActivity(intent);
            }
        });


        btnWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,WorldDataActivity.class);
                startActivity(intent);
            }
        });

        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestVariousPermission();
            }
        });

    }



    @AfterPermissionGranted(123)
    private void requestVariousPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this,perms)){
            Toast.makeText(this, "Opening camera and Bluetooth", Toast.LENGTH_SHORT).show();
        }else {
            EasyPermissions.requestPermissions(this,"We Need permissions",
                    123,perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){
            Toast.makeText(this, " Icon clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this,AboutUsActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
