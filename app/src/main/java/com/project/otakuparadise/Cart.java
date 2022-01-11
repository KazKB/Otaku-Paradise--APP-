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

public class Cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<CharSequence> dropDown;
    private Button remove;
    private ImageButton profile, home;
    private ImageView image;
    private Intent intent;
    private TextView name, price, size, quantity;
    private Spinner menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_cart);

        initialiseVars();
        getInfo();

        profile.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Cart.this, Profile.class);

            startActivity(intent);
            finish();
        });

        home.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Cart.this, Index.class);

            startActivity(intent);
            finish();
        });

        remove.setOnClickListener(v -> {
            //Sets the information in CartInfo to null
            CartInfo.setName(null);
            CartInfo.setImage(0);
            CartInfo.setPrice(null);
            CartInfo.setSize(null);
            CartInfo.setQuantity(null);

            //Sets the cart to say add an item
            name.setText(R.string.empty);
            image.setImageResource(R.drawable.white_empty_icon);
            price.setText(R.string.empty);
            size.setText(R.string.empty);
            quantity.setText(R.string.empty);

            Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Cart.this, Index.class);

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
                intent = new Intent(Cart.this, Index.class);

                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(Cart.this, Apparel.class);

                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(Cart.this, Accessories.class);

                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Cart.this, Figurines.class);

                startActivity(intent);
                finish();
                break;
            case 5:
                intent = new Intent(Cart.this, Posters.class);

                startActivity(intent);
                finish();
                break;
            case 6:
                intent = new Intent(Cart.this, MainActivity.class);

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
        size = findViewById(R.id.itemSize);
        quantity = findViewById(R.id.itemQuantity);
        menu = findViewById(R.id.menuButton);
        profile = findViewById(R.id.profileButton);
        home = findViewById(R.id.homeButton);
        remove = findViewById(R.id.remove);

        //Setting up the Adapter - Context, string-array, layout
        dropDown = ArrayAdapter.createFromResource(Cart.this, R.array.dropDown2, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setPopupBackgroundResource(R.color.cart);
        menu.setAdapter(dropDown);
        menu.setOnItemSelectedListener(this);
    }

    private void getInfo() {
        if(CartInfo.getName() == null) {
            //Sets the cart to say add an item
            name.setText(R.string.empty);
            image.setImageResource(R.drawable.white_empty_icon);
            price.setText(R.string.empty);
            size.setText(R.string.empty);
            quantity.setText(R.string.empty);
        }
        else if(CartInfo.getName() != "") {
            //Sets the cart to the info that was sent to CartInfo
            name.setText(CartInfo.getName());
            image.setImageResource(CartInfo.getImage());
            price.setText(CartInfo.getPrice());
            size.setText(CartInfo.getSize());
            quantity.setText(CartInfo.getQuantity());
        }
    }
}