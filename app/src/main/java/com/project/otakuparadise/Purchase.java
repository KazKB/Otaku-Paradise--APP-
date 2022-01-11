package com.project.otakuparadise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Purchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<CharSequence> menuAdapter, sizeAdapter, quantityAdapter;
    private Button add;
    private ImageButton cart, profile;
    private ImageView image;
    private Intent intent;
    private Spinner menu, size, quantity;
    private TextView name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_purchase);

        initialiseVars();
        getInfo();

        profile.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Purchase.this, Profile.class);

            startActivity(intent);
            finish();
        });

        cart.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Purchase.this, Cart.class);

            startActivity(intent);
            finish();
        });

        add.setOnClickListener(v -> {
            //Adds the information of the item to CartInfo
            CartInfo.setName(name.getText().toString());
            CartInfo.setImage(PurchaseInfo.getImage());
            CartInfo.setPrice(price.getText().toString());
            CartInfo.setQuantity(quantity.getSelectedItem().toString());
            CartInfo.setSize(size.getSelectedItem().toString());

            Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Purchase.this, Index.class);

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
                intent = new Intent(Purchase.this, Index.class);

                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(Purchase.this, Apparel.class);

                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(Purchase.this, Accessories.class);

                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Purchase.this, Figurines.class);

                startActivity(intent);
                finish();
                break;
            case 5:
                intent = new Intent(Purchase.this, Posters.class);

                startActivity(intent);
                finish();
                break;
            case 6:
                intent = new Intent(Purchase.this, MainActivity.class);

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
        name = findViewById(R.id.itemName);
        image = findViewById(R.id.itemImage);
        price = findViewById(R.id.itemPrice);
        add = findViewById(R.id.addToCart);
        menu = findViewById(R.id.menuButton);
        profile = findViewById(R.id.profileButton);
        cart = findViewById(R.id.cartButton);
        size = findViewById(R.id.sizeSpinner);
        quantity = findViewById(R.id.quantitySpinner);

        //Spinner Set Ups
        //Setting up the Adapter - Context, string-array, layout
        menuAdapter = ArrayAdapter.createFromResource(Purchase.this, R.array.dropDown2, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setPopupBackgroundResource(R.color.cart);
        menu.setAdapter(menuAdapter);
        menu.setOnItemSelectedListener(this);

        //Setting up the Adapter - Context, string-array, layout
        sizeAdapter = ArrayAdapter.createFromResource(Purchase.this, R.array.sizeDropDown, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(sizeAdapter);
        size.setOnItemSelectedListener(null);

        //Setting up the Adapter - Context, string-array, layout
        quantityAdapter = ArrayAdapter.createFromResource(Purchase.this, R.array.quantityDropDown, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantityAdapter);
        quantity.setOnItemSelectedListener(null);
    }

    private void getInfo() {
        name.setText(PurchaseInfo.getName());
        image.setImageResource(PurchaseInfo.getImage());
        price.setText(PurchaseInfo.getPrice());
    }
}