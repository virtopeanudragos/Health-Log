package com.example.healthlog;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    // Member variables for UI components
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //database
        try {
            copyDatabase(this);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error
        }

        // Initialize and setup UI components
        initializeToolbarAndDrawer();
        setupNavigationView();
        setupBottomNavigationBar();
        loadDefaultFragment();
        setupBackPressHandler();
    }

    private void initializeToolbarAndDrawer() {
        // Initialize the Toolbar
        toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);

        // Setup Drawer Layout with the toolbar
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }



    private void setupNavigationView() {
        navigationView = findViewById(R.id.top_nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            handleNavigationDrawerItemSelected(item);
            return true;
        });
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnItemSelectedListener(item -> {
            handleBottomNavigationItemSelected(item);
            return true;
        });
    }

    private void loadDefaultFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }



    private void handleNavigationDrawerItemSelected(MenuItem item) {
        // Determine which item was selected
        int id = item.getItemId();

        // Load corresponding fragment or perform other actions
        if (id == R.id.nav_home) {
            // Load home fragment
        } else if (id == R.id.nav_profile) {
            // Load profile fragment
        }
        // Add more conditions for other menu items

        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void handleBottomNavigationItemSelected(MenuItem item) {
        Fragment selectedFragment = null;

        int id = item.getItemId();
        if (id == R.id.navigation_home) {
            selectedFragment = new HomeFragment();
        } else if (id == R.id.navigation_workout) {
            selectedFragment = new WorkoutFragment();
        } else if (id == R.id.navigation_food) {
            selectedFragment = new FoodFragment();
        } else if (id == R.id.navigation_progress) {
            selectedFragment = new ProgressFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
        }
    }



    private void setupBackPressHandler() {
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });
    }


    private void copyDatabase(Context context) throws IOException {
        File dbFile = context.getDatabasePath("workoutRoutines.db");
        if (!dbFile.exists()) {
            InputStream is = context.getAssets().open("workoutRoutines.db");
            OutputStream os = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                os.write(buffer);
            }

            os.flush();
            os.close();
            is.close();
        }
    }

}