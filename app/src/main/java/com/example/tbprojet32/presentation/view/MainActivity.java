package com.example.tbprojet32.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tbprojet32.R;
import com.example.tbprojet32.Singletons;
import com.example.tbprojet32.presentation.controler.MainControler;
import com.example.tbprojet32.presentation.model.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//todo MainActivity

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
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
            //   getSharedPreferences("applicationTB", Context.MODE_PRIVATE)
        );
        controler.onStart();


}



    public void showList(List<Pokemon> pokemonList) {
        //Todo afficher la lste de pokemon
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        //List<pok> input = new ArrayList<>();
        //for (int i = 0; i < 100; i++) {
           // input.add("Test" + i);
        // define an adapter*/
        mAdapter= new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                controler.OnItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }




    public void showError() {
                Toast.makeText(getApplicationContext(), "API Errorrrr", Toast.LENGTH_SHORT).show();
            }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("keyPokemon", Singletons.getGson().toJson(pokemon));

        MainActivity.this.startActivity(myIntent);


      //  Toast.makeText(getApplicationContext(), "Navigation", Toast.LENGTH_SHORT).show();
    }
}






