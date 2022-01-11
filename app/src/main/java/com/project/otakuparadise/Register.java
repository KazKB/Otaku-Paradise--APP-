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

public class Register extends AppCompatActivity {
    private Button register;
    private DatabaseReference registerRef;
    private EditText name, username, email, password, address, date, month, year;
    private Intent intent;
    private String uN;
    private UserCredentials user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_register);

        initialiseVars();

        register.setOnClickListener(v -> {
            getDatabaseInfo();
            checkUsername();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Register.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    private void initialiseVars() {
        //Elements on Screen
        name = findViewById(R.id.nameInput);
        username = findViewById(R.id.usernameInput);
        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        address = findViewById(R.id.addressInput);
        date = findViewById(R.id.dateInput);
        month = findViewById(R.id.monthInput);
        year = findViewById(R.id.yearInput);
        register = findViewById(R.id.registerButton);

        //Database Connection
        registerRef = FirebaseDatabase.getInstance().getReference().child("UserOP");

        //Initialise Class
        user = new UserCredentials();
    }

    private void getDatabaseInfo (){
        registerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //Gets username from database
                uN = snapshot.child(username.getText().toString().trim()).child("username").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void checkUsername() {
        //Checks if username already in database
        if(username.getText().toString().trim().equals(uN)) {
            Toast.makeText(Register.this, "Username Already Taken", Toast.LENGTH_SHORT).show();
        }
        //Sends data to database
        else if(!username.getText().toString().trim().equals(uN)) {
            sendToDatabase();
        }
    }

    private void sendToDatabase() {
        //Checks if fields are empty
        if(name.getText().toString().trim().equals("") || username.getText().toString().trim().equals("") || email.getText().toString().trim().equals("") || password.getText().toString().trim().equals("") || address.getText().toString().trim().equals("") || date.getText().toString().trim().equals("") || month.getText().toString().trim().equals("") || year.getText().toString().trim().equals("")) {
            Toast.makeText(Register.this, "Can't Have Empty Fields", Toast.LENGTH_SHORT).show();
        }
        //Checks if password is 6-12 characters
        else if(password.getText().length() < 6 || password.getText().length() > 12) {
            Toast.makeText(Register.this, "Password Is Too Long Or Short", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().length() >= 6 && password.getText().length() <= 12) {
            //Sets data to class
            user.setName(name.getText().toString());
            user.setUsername(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());
            user.setAddress(address.getText().toString().trim());
            user.setDate(date.getText().toString().trim());
            user.setMonth(month.getText().toString().trim());
            user.setYear(year.getText().toString().trim());

            //Sends to node unique by username
            registerRef.child(user.getUsername()).setValue(user);

            //Allows the username to be used throughout the project
            Username.setUsername(user.getUsername());

            //Starts Index screen and closes this screen
            intent = new Intent(Register.this, Index.class);

            startActivity(intent);
            finish();
        }
    }
}