package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editNome,editEmail,editData;
    Button botao;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associar variaveis locais com a interface
        editNome = findViewById(R.id.txtNome);
        editEmail=findViewById(R.id.txtEmail);
        editData=findViewById(R.id.txtData);
        botao=findViewById(R.id.btnCadastrar);
        listView=findViewById(R.id.ListView);

        database=openOrCreateDatabase("meubd",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS dados (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar,email varchar,data date); ");
        carregarDados();

        //Definindo tratamento para evento de clique
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editNome.getText().toString();
                String email = editEmail.getText().toString();
                String dataN = editData.getText().toString();


                SimpleDateFormat simpleDateDestino = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateorigem = new SimpleDateFormat("dd/MM/yyyy");
                String str;
                try {

                    str=simpleDateDestino.format(simpleDateorigem.parse(dataN));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                ContentValues cv = new ContentValues();
                cv.put("nome",nome);
                cv.put("email",email);
                cv.put("data",str);
                long status = database.insert("dados",null,cv);
                if(status>0){
                    Toast.makeText(getApplicationContext(),"Usuário inserido com sucesso!",Toast.LENGTH_LONG).show();
                    carregarDados();
                    editNome.setText(null);
                    editEmail.setText(null);
                    editData.setText(null);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Erro",Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nome,email,data;
                String id = String.valueOf(i+1);

                Cursor cursor = database.rawQuery("select * from dados where id=?",new String[]{id});
                cursor.moveToFirst();
                nome = cursor.getString(1);
                email = cursor.getString(2);
                data = cursor.getString(3);

                editNome.setText(nome);
                editEmail.setText(email);
                editData.setText(data);

            }
        });


    }
    public void carregarDados(){
        Cursor cursor = database.rawQuery("select * from dados",null);
        cursor.moveToFirst();
        ArrayList<String> nomes = new ArrayList<>();
        while(!cursor.isAfterLast()){
            nomes.add(cursor.getString(1));
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,nomes);
        listView.setAdapter(adapter);
    }
    public void mostrarDados(){

    }
}