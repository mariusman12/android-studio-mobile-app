package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProdusDetailsActivity extends AppCompatActivity {

    TextView tvTotalCost,serie,nume,magazin,expira;

    Button btnaddToCart, btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produs_details);

        tvTotalCost= findViewById(R.id.textViewTotalCost);
        nume= findViewById(R.id.multiLine1);
        magazin= findViewById(R.id.multiLine2);
        expira= findViewById(R.id.multiLine3);
        serie= findViewById(R.id.multiLine4);
        btnback=  findViewById(R.id.buttonBack);
        btnaddToCart= findViewById(R.id.buttonAddToCart);

        Intent intent = getIntent();

        nume.setText(intent.getStringExtra("text1"));
        magazin.setText(intent.getStringExtra("text2"));
        expira.setText(intent.getStringExtra("text3"));
        serie.setText(intent.getStringExtra("text4"));
        tvTotalCost.setText("Total cost"+intent.getStringExtra("text5")+"$");

        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProdusDetailsActivity.this,ProdusActivity.class));

            }
        });
        btnaddToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username","").toString();
                String product = nume.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text5").toString());
                Log.d("tag","Avem user : "+username);
                Log.d("tag","Avem product : "+product);
                Log.d("tag","Avem price : "+price);

                Database db = new Database(getApplicationContext(),"aplicatie",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Produs deja adaugat", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price);
                    Toast.makeText(getApplicationContext(), "Produs adaugat cu succes ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProdusDetailsActivity.this,ProdusActivity.class));
                }
            }
        });
    }





}