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
import com.example.tbprojet32.presentation.model.Pokemon;
import com.example.tbprojet32.presentation.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://pokeapi.co/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("applicationTB", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Pokemon> pokemonList =getDataFromCache();

        if (pokemonList != null) {
        showList(pokemonList);
    }else {
        makeApiCall();
    }

}

    private List<Pokemon> getDataFromCache() {
       String jsonPokemon = sharedPreferences.getString("jsonPokemonList",null);


       if(jsonPokemon ==null) {
           return null;
       }else{
           Type ListType = new TypeToken<Pokemon>(){}.getType();
           return gson.fromJson(jsonPokemon,ListType);
       }

    }

    private void showList(List<Pokemon> pokemonList) {
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

       /* List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter*/
        mAdapter= new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }


        private void makeApiCall() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            PokeApi pokeApi = retrofit.create(PokeApi.class);

            Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();
            call.enqueue(new Callback<RestPokemonResponse>() {
                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        List<Pokemon> pokemonList = response.body().getResults();
                        Toast.makeText(getApplicationContext(), "API Sucess", Toast.LENGTH_SHORT).show();
                        savedList(pokemonList);
                        showList(pokemonList);
                    } else {
                        showError();
                    }
                }


                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    showError();
                }
            });
        }

    private void savedList(List<Pokemon> pokemonList) {
            String jsonString=gson.toJson(pokemonList);
        sharedPreferences
            .edit()
            .putString("jsonPokemonList", jsonString)
            .apply();
        Toast.makeText(getApplicationContext(), "List sauvegarder ", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
                Toast.makeText(getApplicationContext(), "API Errorrrr", Toast.LENGTH_SHORT).show();
            }

        }






