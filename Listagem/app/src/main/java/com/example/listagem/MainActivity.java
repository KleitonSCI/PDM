package com.example.listagem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById((R.id.listView));
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item1, android.R.id.text1,nomes);

        PlanetaDAO dao = new PlanetaDAO();
        PlanetaAdapter planetaAdapter = new PlanetaAdapter(this,R.layout.planetas,dao.planetas);
        listview.setAdapter(planetaAdapter);



    }
}