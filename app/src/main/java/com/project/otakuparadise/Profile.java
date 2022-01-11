package com.project.otakuparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import static com.project.otakuparadise.R.color.index;

public class Profile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<CharSequence> dropDown;
    private DatabaseReference profileRef;
    private ImageButton home, cart;
    private Intent intent;
    private Spinner menu;
    private String n, u, e, a, d, m, y;
    private TextView name, username, email, address, dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_profile);

        initialiseVars();
        getDatabaseInfo();
        
        home.setOnClickListener(v -> {
            intent = new Intent(Profile.this, Index.class);

            startActivity(intent);
            finish();
        });

        cart.setOnClickListener(v -> {
            intent = new Intent(Profile.this, Cart.class);

            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Profile.this, Index.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Goes to Activity based on what was selected
        switch(position) {
            case 0:
                break;
            case 1:
                intent = new Intent(Profile.this, Apparel.class);

                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(Profile.this, Accessories.class);

                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(Profile.this, Figurines.class);

                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Profile.this, Posters.class);

                startActivity(intent);
                finish();
                break;
            case 5:
                intent = new Intent(Profile.this, MainActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initialiseVars() {
        //Elements on Screen
        menu = findViewById(R.id.menuButton);
        name = findViewById(R.id.nameOutput);
        username = findViewById(R.id.usernameOutput);
        email = findViewById(R.id.emailOutput);
        address = findViewById(R.id.addressOutput);
        dob = findViewById(R.id.dobOutput);
        home = findViewById(R.id.homeButton);
        cart = findViewById(R.id.cartButton);

        //Connection Connection
        profileRef = FirebaseDatabase.getInstance().getReference().child("UserOP").child(Username.getUsername());

        //Spinner Set Up
        //Setting up the Adapter - Context, string-array, layout
        dropDown = ArrayAdapter.createFromResource(Profile.this, R.array.dropDown, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setPopupBackgroundResource(R.color.profile);
        menu.setAdapter(dropDown);
        menu.setOnItemSelectedListener(this);
    }

    private void getDatabaseInfo() {
        profileRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //Get info from database
                n = snapshot.child("name").getValue(String.class);
                u = snapshot.child("username").getValue(String.class);
                e = snapshot.child("email").getValue(String.class);
                a = snapshot.child("address").getValue(String.class);
                d = snapshot.child("date").getValue(String.class);
                m = snapshot.child("month").getValue(String.class);
                y = snapshot.child("year").getValue(String.class);

                //Set TextViews with info from database
                name.setText(n);
                username.setText(u);
                email.setText(e);
                address.setText(a);
                dob.setText(d + "/" + m + "/" + y);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}