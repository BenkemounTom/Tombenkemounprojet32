package com.example.tbprojet32.data;

import com.example.tbprojet32.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
    @GET("newapi.json")
    Call<RestPokemonResponse> getPokemonResponse();

}
