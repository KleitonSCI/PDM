package com.example.listagem;

import java.util.ArrayList;

public class PlanetaDAO {

    ArrayList<Planeta> planetas;

    public PlanetaDAO() {
        planetas = new ArrayList<>();
        planetas.add(new Planeta("Terra",R.drawable.earth));
        planetas.add(new Planeta("Marte",R.drawable.mars));

    }
}
