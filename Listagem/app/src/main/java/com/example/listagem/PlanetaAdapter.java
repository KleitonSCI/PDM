package com.example.listagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class PlanetaAdapter  extends ArrayAdapter<Planeta> {
    Context mContext;
    int mResourceXML;
    List<Planeta> planetas;
    public PlanetaAdapter(Context context, int resource, List<Planeta> objects){
        super(context,resource,objects);
        mContext = context;
        mResourceXML = resource;
        planetas = objects;
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View v = layoutInflater.inflate(mResourceXML,parent,false);
        TextView tvnomeplaneta = v.findViewById(R.id.textViewNome);
        ImageView imageView =v.findViewById(R.id.imageView);
        Planeta planeta = planetas.get(position);

        tvnomeplaneta.setText(planeta.nome);
        imageView.setImageResource(planeta.foto);
        return v;

    }
}
