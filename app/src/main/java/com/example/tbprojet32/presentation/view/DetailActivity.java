package com.example.tbprojet32.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbprojet32.R;
import com.example.tbprojet32.Singletons;
import com.example.tbprojet32.presentation.model.Pokemon;

public class DetailActivity extends AppCompatActivity {

private TextView textedetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        textedetails = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("keyPokemon");
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetails(pokemon);

    }

    private void showDetails(Pokemon pokemon) {
        textedetails.setText(pokemon.getName());
    }
}






