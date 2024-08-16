package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewFacturiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_facturi);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Database databaseHelperClass = new Database(this);
        List<FacturaModelClass> facturaModelClasses = databaseHelperClass.getFacturiList();

        if (facturaModelClasses.size() > 0){
            FacturaAdapterClass facturadapterclass = new FacturaAdapterClass(facturaModelClasses,ViewFacturiActivity.this);
            recyclerView.setAdapter(facturadapterclass);
        }else {
            Toast.makeText(this, "Nu sunt facturi in bd", Toast.LENGTH_SHORT).show();
        }
    }
}