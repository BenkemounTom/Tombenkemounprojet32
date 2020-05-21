package com.example.tbprojet32.presentation.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tbprojet32.R;
import com.example.tbprojet32.Singletons;
import com.example.tbprojet32.presentation.model.Pokemon;

public class DetailActivity extends AppCompatActivity {

private TextView textedetails;
private TextView texteurl;
private TextView textimage;
private TextView texttype;
private TextView IdType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);


        textedetails = findViewById(R.id.detail_txt);
        texteurl = findViewById(R.id.detail_url);
        textimage = findViewById(R.id.textView_image);
        texttype = findViewById(R.id.TypeofPokemon);
        IdType=findViewById(R.id.textViewId);
        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("keyPokemon");
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetails(pokemon);

    }

    private void showDetails(Pokemon pokemon) {
        textedetails.setText(pokemon.getName());
        texteurl.setText(pokemon.getUrl());
        textimage.setText(pokemon.getImage());
        texttype.setText(pokemon.getType());
        IdType.setText(pokemon.getId());
    }
}






