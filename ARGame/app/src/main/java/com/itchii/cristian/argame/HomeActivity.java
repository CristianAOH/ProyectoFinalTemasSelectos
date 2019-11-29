package com.itchii.cristian.argame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    TextView txtNombre, txtApellidos;
    ImageView imgPerfil;
    Button btnSignOut;
    Intent inLanzarLogin, inLanzarColeccion;
    Button btnColeccion, btnCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnColeccion = (Button)findViewById(R.id.btnColeccion);
        btnCamara = (Button)findViewById(R.id.btnCamara);

        inLanzarLogin = new Intent(HomeActivity.this, LoginActivity.class);
        inLanzarColeccion = new Intent(HomeActivity.this, JuegosActivity.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*
        if (user != null){
            txtNombre.setText(user.getDisplayName());
            txtApellidos.setText(user.getEmail());
            Uri photoUrl = user.getPhotoUrl();
            Glide.with(HomeActivity.this).load(photoUrl+"?type=normal").into(imgPerfil);
        }else{
            txtNombre.setText("Nombre no disponible");
            txtApellidos.setText("Email no disponible");

        }
        */
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.cristian.ProyectoTemas");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
        });

        btnColeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inLanzarColeccion);
            }
        });

    }

    public void SignOut(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(inLanzarLogin);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
