package com.example.tbprojet32.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tbprojet32.R;
import com.example.tbprojet32.data.PokeApi;
import com.example.tbprojet32.presentation.controler.MainControler;
import com.example.tbprojet32.presentation.model.Pokemon;
import com.example.tbprojet32.presentation.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainControler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controler = new MainControler(
                this,
                new GsonBuilder().setLenient().create(),
               getSharedPreferences("applicationTB", Context.MODE_PRIVATE)
        );
        controler.onStart();


}



    public void showList(List<Pokemon> pokemonList) {
        //Todo afficher la lste de pokemon
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //List<pok> input = new ArrayList<>();
        //for (int i = 0; i < 100; i++) {
           // input.add("Test" + i);
        // define an adapter*/
        mAdapter= new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }




    public void showError() {
                Toast.makeText(getApplicationContext(), "API Errorrrr", Toast.LENGTH_SHORT).show();
            }

        }






