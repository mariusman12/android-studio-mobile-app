package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacturiActivity extends AppCompatActivity {

    EditText edittext_societate,edittext_facturant,edittext_serie,edittext_pret;
    Button button_add,button_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturi);


        edittext_societate=findViewById(R.id.edittext_societate);
        edittext_facturant=findViewById(R.id.edittext_facturant);
        edittext_serie=findViewById(R.id.edittext_serie);
        edittext_pret=findViewById(R.id.edittext_pret);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         String stringSocietate = edittext_societate.getText().toString();
        String stringFacturant = edittext_facturant.getText().toString();
        String stringSerie = edittext_serie.getText().toString();
        String stringPret = edittext_pret.getText().toString();

                if (stringSocietate.length() <=0 || stringFacturant.length() <=0){
                    Toast.makeText(FacturiActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    Database databaseHelperClass = new Database(FacturiActivity.this);
                    FacturaModelClass facturaModelClass = new FacturaModelClass(stringSocietate,stringFacturant,stringSerie,stringPret);
                    databaseHelperClass.adaugaFactura(facturaModelClass);
                    Toast.makeText(FacturiActivity.this, "Am adaugat factura", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacturiActivity.this,ViewFacturiActivity.class);
                startActivity(intent);
            }
        });
    }
}