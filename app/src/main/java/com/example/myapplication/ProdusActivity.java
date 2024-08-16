package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.HashMap;

public class ProdusActivity extends AppCompatActivity {
    private EditText editTextNume, editTextPret, editTextDescriere;
    private Button buttonAdauga;
    private ListView listViewProduse;
    private DatabaseProdus databaseHelper;
    private SQLiteDatabase db;
    private String[][] array1={
            {"Paine","Lidl","Expira 13.12.2023 ","Serie:12321321321","2"},
            {"Salam","Lidl","Expira 13.12.2023 ","Serie:12221321321","10"},
            {"Iaurt","Lidl","Expira 13.12.2023 ","Serie:12323321321","5"},
            {"Nutella","Lidl","Expira 13.12.2023 ","Serie:12111321321","20"},
            {"Salata Ardei","Lidl","Expira 13.12.2023 ","Serie:12321321111","12"},
            {"Salata Rosii","Lidl","Expira 13.12.2023 ","Serie:12321321121","12"},
            {"Salata Castravete","Lidl","Expira 13.12.2023 ","Serie:12322221321","12"}

    };

    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    Button btnGoToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produs);

        editTextNume = findViewById(R.id.editTextNume);
        editTextPret = findViewById(R.id.editTextPret);
        editTextDescriere = findViewById(R.id.editTextDescriere);
        btnGoToCart = findViewById(R.id.buttonGoToCart);
//        buttonAdauga = findViewById(R.id.buttonAdauga);
        listViewProduse = findViewById(R.id.listViewProduse);

//        buttonAdauga.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nume = editTextNume.getText().toString();
//                String pret = editTextNume.getText().toString();
//                String descriere = editTextDescriere.getText().toString();
//
//                DatabaseProdus db= new DatabaseProdus(getApplicationContext(),"aplicatie2",null,1);
//                db.adaugaprodus(nume,pret,descriere);
//
//            }
//        });


        list = new ArrayList();
        for(int i=0;i<array1.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",array1[i][0]);
            item.put("line2",array1[i][1]);
            item.put("line3",array1[i][2]);
            item.put("line4",array1[i][3]);
            item.put("line5","Price"+array1[i][4]+"/$");

            list.add(item);

        }
        sa = new SimpleAdapter(this, list,R.layout.multilines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewProduse);
        lst.setAdapter(sa);



        Button buttonback = findViewById(R.id.buttonBack);
        buttonback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProdusActivity.this,HomeActivity.class));

            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent it = new Intent(ProdusActivity.this,ProdusDetailsActivity.class);
                it.putExtra("text1",array1[i][0]);
                it.putExtra("text2",array1[i][1]);
                it.putExtra("text3",array1[i][2]);
                it.putExtra("text4",array1[i][3]);
                it.putExtra("text5",array1[i][4]);
                startActivity(it);
            }
        });


        btnGoToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProdusActivity.this,CartActivity.class));
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        databaseHelper.close();
    }
}
