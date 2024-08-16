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

public class LoginActivity extends AppCompatActivity {


    EditText edUsername, edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextTextPersonEmail);
        edPassword = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.buttonlogin);
        tv = findViewById(R.id.textView3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUsername.getText().toString();
                String password =  edPassword.getText().toString();
                Database db= new Database(getApplicationContext(),"aplicatie",null,1);
                if (user.length() == 0 || password.length() ==0){
                    Toast.makeText(getApplicationContext(), "Campurile sunt goale",Toast.LENGTH_SHORT).show();
                }else {
                    if(db.login(user, password) ==1) {
                        Toast.makeText(getApplicationContext(), "Ai fost logat cu succes", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username",user);
                        editor.apply();
                        Log.d("tag","Avem user:"+user);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Date de acces invalide", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}