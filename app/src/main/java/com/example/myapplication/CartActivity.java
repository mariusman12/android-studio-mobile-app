package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    HashMap<String,String> item;
    ListView lst;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;

    Button backButton;
private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        backButton =findViewById(R.id.btnBack2);
        tvTotal =findViewById(R.id.textTotalPrice);
         lst= findViewById(R.id.listViewCart);



        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();


        Database db = new Database(getApplicationContext(),"aplicatie",null,1);
        float totalAmount=0;
        ArrayList dbData = db.getCartData(username);

        packages = new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i] = new String[5];
        }

        for( int i=0;i<dbData.size();i++){
            String arrData= dbData.get(i).toString();
            String[] strData= arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]= strData[0];
            packages[i][4] = "Cost : " + strData[1] + "/$";
            totalAmount+=Float.parseFloat(strData[1]);
        }

        Log.d("tag","Totatlul este : " + totalAmount+"$");
tvTotal.setText("Cost total cos " + totalAmount);

    list = new ArrayList();
    for ( int i =0; i<packages.length;i++){
     item= new HashMap<String,String>();
        item.put("line1",packages[i][0]);
        item.put("line2",packages[i][1]);
        item.put("line3",packages[i][2]);
        item.put("line4",packages[i][3]);
        item.put("line5",packages[i][4]);
        list.add(item);

    }
sa = new SimpleAdapter(this,list,R.layout.multilines,new String[]{"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});


        lst.setAdapter(sa);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(CartActivity.this,ProdusActivity.class));
            }
        });
    }
}