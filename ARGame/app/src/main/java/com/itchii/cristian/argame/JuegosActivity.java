package com.itchii.cristian.argame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class JuegosActivity extends AppCompatActivity {

    ImageButton btnInjustice, btnJojos, btnPower, btnLol, btnPaladins, btnPokemon, btnMarioK;
    Intent inLanzarInjustice;
    Intent inLanzarJojos;
    Intent inLanzarPower;
    Intent inLanzarLeagueOfLegends;
    Intent inLanzarPaladins;
    Intent inLanzarPokemon;
    Intent inLanzarMarioKartTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);

        btnInjustice = (ImageButton)findViewById(R.id.btnInjustice);
        btnJojos = (ImageButton)findViewById(R.id.btnJojos);
        btnPower = (ImageButton)findViewById(R.id.btnPower);
        btnLol = (ImageButton)findViewById(R.id.btnLol);
        btnPaladins = (ImageButton)findViewById(R.id.btnPaladins);
        btnPokemon = (ImageButton)findViewById(R.id.btnPokemon);
        btnMarioK = (ImageButton)findViewById(R.id.btnMario);

        inLanzarInjustice = new Intent(JuegosActivity.this, Lista.class);
        inLanzarJojos = new Intent(JuegosActivity.this, Lista.class);
        inLanzarPower = new Intent(JuegosActivity.this, Lista.class);
        inLanzarLeagueOfLegends = new Intent(JuegosActivity.this, Lista.class);
        inLanzarPaladins = new Intent(JuegosActivity.this, Lista.class);
        inLanzarPokemon = new Intent(JuegosActivity.this, Lista.class);
        inLanzarMarioKartTour = new Intent(JuegosActivity.this, Lista.class);

        btnInjustice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarInjustice.putExtra("Juego","injustice");
                startActivity(inLanzarInjustice);
            }
        });

        btnJojos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarJojos.putExtra("Juego","jojos");
                startActivity(inLanzarJojos);
            }
        });

        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarPower.putExtra("Juego","power");
                startActivity(inLanzarPower);
            }
        });

        btnLol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarLeagueOfLegends.putExtra("Juego","lol");
                startActivity(inLanzarLeagueOfLegends);
            }
        });

        btnPaladins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarPaladins.putExtra("Juego","paladins");
                startActivity(inLanzarPaladins);
            }
        });

        btnPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarPokemon.putExtra("Juego","pokemon");
                startActivity(inLanzarPokemon);
            }
        });

        btnMarioK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLanzarMarioKartTour.putExtra("Juego","mario");
                startActivity(inLanzarMarioKartTour);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
