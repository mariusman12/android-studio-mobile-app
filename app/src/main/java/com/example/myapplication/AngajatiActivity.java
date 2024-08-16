package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AngajatiActivity extends AppCompatActivity {

    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    private ListView listViewAngajati;
    private String[][] array1={
            {"CEO","Dorin Iogea","Programator","8hr","7000"},
            {"Full Stack Dev","Cristea Adrian","Programator ","8hr","5400"},
            {"WEb develioer","Miahi Darius","Programator ","8hr","5200"},
            {"WEb develioer","Liviu Constantin","Programator ","8hr","4300"},
            {"WEb develioer","Gelu Feliciu","Programator ","8hr","4000"},
            {".net developer","Adrian Bercea","Programator ","8hr","4000"},
            {"Salata Castravete","Constantin Leonidas","Programator ","8hr","3500"}

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angajati);


        listViewAngajati = findViewById(R.id.listViewAngajati);
        list = new ArrayList();
        for(int i=0;i<array1.length;i++){
            item= new HashMap<String,String>();
            item.put("line1","Functie:"+array1[i][0]);
            item.put("line2","Nume"+array1[i][1]);
            item.put("line3","Post"+array1[i][2]);
            item.put("line4","Program"+array1[i][3]);
            item.put("line5","Salar"+array1[i][4]+"/RON");

            list.add(item);

        }
        sa = new SimpleAdapter(this, list,R.layout.multilines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewAngajati);
        lst.setAdapter(sa);
        Button buttonback = findViewById(R.id.buttonBackAngajati);
        buttonback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(AngajatiActivity.this,HomeActivity.class));

            }
        });

    }
}