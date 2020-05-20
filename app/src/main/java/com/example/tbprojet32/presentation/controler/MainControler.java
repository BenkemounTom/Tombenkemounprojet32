package com.example.tbprojet32.presentation.controler;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tbprojet32.Singletons;
import com.example.tbprojet32.presentation.model.Pokemon;
import com.example.tbprojet32.presentation.model.RestPokemonResponse;
import com.example.tbprojet32.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainControler {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainControler(){

    }

    public MainControler(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view=mainActivity;
        this.gson=gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){



        List<Pokemon> pokemonList =getDataFromCache();

        if (pokemonList != null) {
            view.showList(pokemonList);
        }else {
            makeApiCall();
        }
    }

    private void makeApiCall() {


        Call<RestPokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Pokemon> pokemonList = response.body().getResults();
                    Toast.makeText(view.getApplicationContext(), "API Sucess", Toast.LENGTH_SHORT).show();
                    savedList(pokemonList);
                    view.showList(pokemonList);
                } else {
                    view.showError();
                }
            }


            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {

                view.showError();
            }
        });
    }

    private void savedList(List<Pokemon> pokemonList) {
        String jsonString=gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(), "List sauvegarder ", Toast.LENGTH_SHORT).show();
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

    public void OnItemClick(){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}

