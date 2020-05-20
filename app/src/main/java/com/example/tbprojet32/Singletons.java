package com.example.tbprojet32;

import com.example.tbprojet32.data.PokeApi;
import com.example.tbprojet32.presentation.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PokeApi pokeApiInstance;

    public static Gson getGson() {
        if(gsonInstance == null ){
            gsonInstance = new GsonBuilder().setLenient().create();

        }
        return gsonInstance;
    }

    public static PokeApi getPokeApi(){
        if(pokeApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            pokeApiInstance = retrofit.create(PokeApi.class);

        }
        return pokeApiInstance;

    }


}
