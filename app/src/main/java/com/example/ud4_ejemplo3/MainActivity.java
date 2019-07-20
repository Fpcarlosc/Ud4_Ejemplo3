package com.example.ud4_ejemplo3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos el array de vehiculos.
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        vehiculos.add(new Vehiculo("Ecto1", "Los cazafantasmas"));
        vehiculos.add(new Vehiculo("DeLorean", "Regreso al futuro"));
        vehiculos.add(new Vehiculo("Kitt", "El coche fantástico"));
        vehiculos.add(new Vehiculo("Halcón Milenario", "Star Wars"));
        vehiculos.add(new Vehiculo("Planet Express", "Futurama"));
        vehiculos.add(new Vehiculo("TARDIS", "Doctor Who"));
        vehiculos.add(new Vehiculo("USS Enterprise", "Star Trek"));
        vehiculos.add(new Vehiculo("Nabucodonosor", "Matrix"));
        vehiculos.add(new Vehiculo("Odiseus", "Ulises 31"));
        vehiculos.add(new Vehiculo("Nostromo", "Alien"));

        // Buscamos el RecyclerView e indicamos que su tamaño es fijo
        RecyclerView recycler = findViewById(R.id.recyclerView);

        recycler.setHasFixedSize(true);

        // Añadimos la línea de separación de elementos de la lista
        // 0 para horizontal y 1 para vertical
        recycler.addItemDecoration(new DividerItemDecoration(MainActivity.this, 1));

        // Creamos un LinearLayout que contendrá cada elemento del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);

        // Creamos el adapter y lo asignamos al RecyclerView
        VehiculoAdapter adapter = new VehiculoAdapter(vehiculos);

        recycler.setAdapter(adapter);

    }
}
