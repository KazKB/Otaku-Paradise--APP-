package com.project.otakuparadise;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Index extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayAdapter<CharSequence> dropDown;
    private Button viewAllButton;
    private ConstraintLayout bannerLayout;
    private ImageButton profile, cart;
    private ImageView newArrivalsImage, bestSellerImage;
    private int[] banner;
    private int i;
    private Intent intent;
    private Spinner menu;
    private String[] bannerName;
    private TextView bannerTitle, newArrivalsTitle, newArrivalsName, newArrivalsPrice, bestSellerTitle, bestSellerName, bestSellerPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_index);

        initialiseVars();
        setBanner();

        profile.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Index.this, Profile.class);

            startActivity(intent);
            finish();
        });

        cart.setOnClickListener(v -> {
            //Starts Profile Screen and closes this screen
            intent = new Intent(Index.this, Cart.class);

            startActivity(intent);
            finish();
        });

        bannerTitle.setOnClickListener(v -> {
            if(bannerTitle.getText().toString().equals("Apparel")) {
                intent = new Intent(Index.this, Apparel.class);

                startActivity(intent);
                finish();
            }
            else if(bannerTitle.getText().toString().equals("Accessories")) {
                intent = new Intent(Index.this, Accessories.class);

                startActivity(intent);
                finish();
            }
            else if(bannerTitle.getText().toString().equals("Figurines")) {
                intent = new Intent(Index.this, Figurines.class);

                startActivity(intent);
                finish();
            }
            else if(bannerTitle.getText().toString().equals("Posters")) {
                intent = new Intent(Index.this, Posters.class);

                startActivity(intent);
                finish();
            }
        });

        newArrivalsTitle.setOnClickListener(v -> goNewArrivals());
        newArrivalsImage.setOnClickListener(v -> goNewArrivals());
        newArrivalsName.setOnClickListener(v -> goNewArrivals());
        newArrivalsPrice.setOnClickListener(v -> goNewArrivals());

        bestSellerTitle.setOnClickListener(v -> goBestSeller());
        bestSellerImage.setOnClickListener(v -> goBestSeller());
        bestSellerName.setOnClickListener(v -> goBestSeller());
        bestSellerPrice.setOnClickListener(v -> goBestSeller());

        viewAllButton.setOnClickListener(v -> {
        intent = new Intent(Index.this, ViewAll.class);

        startActivity(intent);
        finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Index.this, Index.class);

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
                intent = new Intent(Index.this, Apparel.class);

                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(Index.this, Accessories.class);

                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(Index.this, Figurines.class);

                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(Index.this, Posters.class);

                startActivity(intent);
                finish();
                break;
            case 5:
                intent = new Intent(Index.this, MainActivity.class);

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
        profile = findViewById(R.id.profileButton);
        cart = findViewById(R.id.cartButton);
        bannerLayout = findViewById(R.id.bannerLayout);
        bannerTitle = findViewById(R.id.itemButton);
        newArrivalsImage = findViewById(R.id.newArrivalsImage);
        newArrivalsName = findViewById(R.id.newArrivalsName);
        newArrivalsTitle = findViewById(R.id.newArrivalsTitle);
        newArrivalsPrice = findViewById(R.id.newArrivalsPrice);
        bestSellerImage = findViewById(R.id.bestSellerImage);
        bestSellerName = findViewById(R.id.bestSellerName);
        bestSellerTitle = findViewById(R.id.bestSellerTitle);
        bestSellerPrice = findViewById(R.id.bestSellerPrice);
        viewAllButton = findViewById(R.id.viewAllButton);

        //Variable
        banner = new int[]{R.drawable.apparel_banner, R.drawable.accessories_banner, R.drawable.figurines_banner, R.drawable.posters_banner};
        bannerName = getResources().getStringArray(R.array.bannerName);
        i = 0;

        //Spinner Set Up
        //Setting up the Adapter - Context, string-array, layout
        dropDown = ArrayAdapter.createFromResource(Index.this, R.array.dropDown, android.R.layout.simple_spinner_item);
        //Sets Drop Down - Drop Down Layout
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setPopupBackgroundResource(R.color.index);
        menu.setAdapter(dropDown);
        menu.setOnItemSelectedListener(this);
    }

    private void setBanner() {
        //Changes the banner every 5 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (i < 3) {
                    //Allows gui to be edited as Timer is a new thread
                    runOnUiThread(() -> {
                        bannerLayout.setBackgroundResource(banner[i]);
                        bannerTitle.setText(bannerName[i]);
                    });
                    i++;
                } else {
                    i = 0;
                    //Allows gui to be edited as Timer is a new thread
                    runOnUiThread(() -> {
                            bannerLayout.setBackgroundResource(banner[i]);
                            bannerTitle.setText(bannerName[i]);
                        });
                }
                setBanner();
            }
        }, 5000);
    }

    private void goNewArrivals() {
        intent = new Intent(Index.this, NewArrivals.class);

        startActivity(intent);
        finish();
    }

    private void goBestSeller() {
        intent = new Intent(Index.this, BestSellers.class);

        startActivity(intent);
        finish();
    }
}