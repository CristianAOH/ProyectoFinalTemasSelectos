package com.itchii.cristian.argame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.itchii.cristian.argame.Clase.ListAdapter;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ListView lstPersonajes;
    ArrayList listaDc;

    int [] dc = {R.drawable.dc1, R.drawable.dc2, R.drawable.dc3, R.drawable.dc4, R.drawable.dc5};
    String [] nombreDc = {"Batman", "Flash", "Green Lantern", "Superman", "Wonder Woman"};

    int[] jojo = {R.drawable.jojo1, R.drawable.jojo2, R.drawable.jojo3, R.drawable.jojo4, R.drawable.jojo5};
    String [] nombreJojo = {"Jotaru", "Killer Queen", "Star Platinum", "Yoshikage Kira", "Dio Brando"};

    int[] power = {R.drawable.pr1, R.drawable.pr2, R.drawable.pr3, R.drawable.pr4, R.drawable.pr5, R.drawable.pr6, R.drawable.pr7};
    String[] nombrePower = {"Black Ranger", "Lord Drakkon", "Megazord S.P.D", "Pink Ranger", "Red Ranger", "Solar Ranger", "Yellow Ranger"};

    int[] lol = {R.drawable.lol1, R.drawable.lol2, R.drawable.lol3, R.drawable.lol4, R.drawable.lol5, R.drawable.lol6};
    String[] nombreLol = {"Syndra", "Miss Fortune", "Soraka", "Ezreal", "Janna", "Ahri"};

    int[] paladins = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8};
    String[] nombrePal = {"Cassie", "Androxus", "Drogoz", "Evie", "Pip", "Seris", "Skye", "Ying"};

    int[] pokemon = {R.drawable.pk1, R.drawable.pk2, R.drawable.pk3, R.drawable.pk4, R.drawable.pk5, R.drawable.pk6, R.drawable.pk7};
    String[] nombrePokemon = {"Blastoise", "Charizard", "Dragonite", "Gardevoir", "Jolteon", "Sceptile", "Venusaur"};

    int[] marioK = {R.drawable.mk1, R.drawable.mk2, R.drawable.mk3, R.drawable.mk4, R.drawable.mk5};
    String[] nombreMario = {"Mario Hamaka", "Mario Musico", "Pauline", "Peachette", "Rosalina"};

    int [] lock = {R.drawable.lock, R.drawable.unlock};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstPersonajes = (ListView)findViewById(R.id.lstPersonajes);
        lstPersonajes.setAdapter(new ListAdapter(this,nombrePal, paladins, lock));

        Bundle datos = this.getIntent().getExtras();
        String juego = datos.getString("Juego");

        switch (juego){
            case "injustice":
                lstPersonajes.setAdapter(new ListAdapter(this,nombreDc, dc, lock));
                break;
            case "jojos":
                lstPersonajes.setAdapter(new ListAdapter(this,nombreJojo, jojo, lock));
                break;
            case "power":
                lstPersonajes.setAdapter(new ListAdapter(this,nombrePower, power, lock));
                break;
            case "lol":
                lstPersonajes.setAdapter(new ListAdapter(this,nombreLol, lol, lock));
                break;
            case "paladins":
                lstPersonajes.setAdapter(new ListAdapter(this,nombrePal, paladins, lock));
                break;
            case "pokemon":
                lstPersonajes.setAdapter(new ListAdapter(this,nombrePokemon, pokemon, lock));
                break;
            case "mario":
                lstPersonajes.setAdapter(new ListAdapter(this,nombreMario, marioK, lock));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
