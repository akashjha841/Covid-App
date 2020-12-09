package com.example.android.covidtracker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView textView3;
    EditText emailRS;
    Button buttonRP;
    private FirebaseAuth mFirebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mFirebaseAuth = FirebaseAuth.getInstance();
        textView3 = findViewById(R.id.textView3);
        emailRS = findViewById(R.id.emailRS);
        buttonRP = findViewById(R.id.buttonRP);

        buttonRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMail = emailRS.getText().toString();

                if (TextUtils.isEmpty(userMail)){
                    Toast.makeText(ResetPasswordActivity.this, "Can't be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    mFirebaseAuth.sendPasswordResetEmail(userMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this, "Please check your mail", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this, "something went wrong "+ message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

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

            Toast.makeText(this, " About Us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ResetPasswordActivity.this,AboutUsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}