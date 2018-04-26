package com.picapp.picapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cambiar esto a activity_main
        setContentView(R.layout.activity_main);

        //instacia de firebase
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();

        //chequea si el usuario ya esta logueado
        // si no lo esta lo manda a la pagina de login
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            // User is signed in entonces va al feed
            Intent feedIntent = new Intent(MainActivity.this, FeedActivity.class);
            sendTo(feedIntent);
        } else {
            // No user is signed in
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            sendTo(loginIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //levanta la barra del menu con sus items

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //se cargan las acciones del menu de opciones
        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                logout();
        }

        return false;
    }

    //cambio de activities principales
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){

                        case R.id.nav_feed :

                            return true;


                        case R.id.nav_flashes:

                            return true;


                        case R.id.nav_profile:

                            return true;

                        default:
                            return false;

                    }
                }
            };

    //--------------Metodos Privados-------------//

    private void logout() {

        mAuth.signOut();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        sendTo(loginIntent);

    }

    private void sendTo(Intent intent) {

        startActivity(intent);
        finish();

    }

}
