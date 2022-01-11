package com.project.otakuparadise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Figurines extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<CharSequence> menuDropDown, sortDropDown;
    private ImageButton profile, cart;
    private ImageView item1, item2, item3, item4;
    private Intent intent;
    private Spinner menu, sort;
    private TextView name1, name2, name3, name4, price1, price2, price3, price4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_figurines);

        initialiseVars();

        profile.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Figurines.this, Profile.class);

            startActivity(intent);
            finish();
        });

        cart.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Figurines.this, Cart.class);

            startActivity(intent);
            finish();
        });

        item1.setOnClickListener(v -> goToPurchase());
        item2.setOnClickListener(v -> goToPurchase());
        item3.setOnClickListener(v -> goToPurchase());
        item4.setOnClickListener(v -> goToPurchase());
        name1.setOnClickListener(v -> goToPurchase());
        name2.setOnClickListener(v -> goToPurchase());
        name3.setOnClickListener(v -> goToPurchase());
        name4.setOnClickListener(v -> goToPurchase());
        price1.setOnClickListener(v -> goToPurchase());
        price2.setOnClickListener(v -> goToPurchase());
        price3.setOnClickListener(v -> goToPurchase());
        price4.setOnClickListener(v -> goToPurchase());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(Figurines.this, Index.class);

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
                intent = new Intent(Figurines.this, Index.class);

                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(Figurines.this, Apparel.class);

                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(Figurines.this, Accessories.class);

                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Figurines.this, Figurines.class);

                startActivity(intent);
                finish();
                break;
            case 5:
                intent = new Intent(Figurines.this, Posters.class);

                startActivity(intent);
                finish();
                break;
            case 6:
                intent = new Intent(Figurines.this, MainActivity.class);

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
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);
        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        price4 = findViewById(R.id.price4);
        menu = findViewById(R.id.menuButton);
        cart = findViewById(R.id.cartButton);
        sort = findViewById(R.id.sortButton);
        profile = findViewById(R.id.profileButton);

        //Spinner Set Ups
        //Setting up the Adapter - Context, string-array, layout
        menuDropDown = ArrayAdapter.createFromResource(Figurines.this, R.array.dropDown2, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        menuDropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setPopupBackgroundResource(R.color.figurines);
        menu.setAdapter(menuDropDown);
        menu.setOnItemSelectedListener(this);

        //Setting up the Adapter - Context, string-array, layout
        sortDropDown = ArrayAdapter.createFromResource(Figurines.this, R.array.sort, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        sortDropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sort.setPopupBackgroundResource(R.color.figurines);
        sort.setAdapter(sortDropDown);
        sort.setOnItemSelectedListener(null);
    }

    private void goToPurchase() {
        intent = new Intent(Figurines.this, Purchase.class);

        PurchaseInfo.setImage(R.drawable.figurine_item);
        PurchaseInfo.setName(name1.getText().toString());
        PurchaseInfo.setPrice(price1.getText().toString());

        startActivity(intent);
        finish();
    }
}