package com.example.tbprojet32.presentation.model;

import com.example.tbprojet32.presentation.model.Pokemon;

import java.util.List;

public class RestPokemonResponse {

    private Integer count;
    private String next;
    private List<Pokemon> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}
