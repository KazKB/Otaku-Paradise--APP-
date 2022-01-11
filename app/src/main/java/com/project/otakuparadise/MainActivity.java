package com.project.otakuparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private Button login, registerRedirect;
    private DatabaseReference loginRef;
    private EditText username, password;
    private Intent intent;
    private String uN, pW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);

        initialiseVars();

        login.setOnClickListener(v -> {
            getDatabaseInfo();
        });

        registerRedirect.setOnClickListener(v ->{
            //Starts Register screen and closes this screen
            intent = new Intent(MainActivity.this, Register.class);

            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(MainActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    private void initialiseVars() {
        //Elements on Screen
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.loginButton);
        registerRedirect = findViewById(R.id.registerRedirect);

        //Database Connection
        loginRef = FirebaseDatabase.getInstance().getReference().child("UserOP");
    }

    private void getDatabaseInfo() {
        loginRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //Get username and password from database
                uN = snapshot.child(username.getText().toString().trim()).child("username").getValue(String.class);
                pW = snapshot.child(username.getText().toString().trim()).child("password").getValue(String.class);

                //Checks if fields are empty
                if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Can't Have Empty Fields", Toast.LENGTH_SHORT).show();
                }
                //Checks if information entered is wrong
                else if (!username.getText().toString().trim().equals(uN) || !password.getText().toString().trim().equals(pW)) {
                    Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
                //Checks if information is correct
                else if (username.getText().toString().trim().equals(uN) && password.getText().toString().trim().equals(pW)) {
                    //Starts Index screen and closes this screen
                    intent = new Intent(MainActivity.this, Index.class);

                    //Allows the username to be used throughout the project
                    Username.setUsername(uN);

                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}