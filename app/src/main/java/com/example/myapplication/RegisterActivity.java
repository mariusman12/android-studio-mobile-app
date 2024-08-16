package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edPasswordConfirm;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextTextPersonName2);
        edEmail = findViewById(R.id.editTextTextPersonEmail);
        edPassword = findViewById(R.id.editTextTextPassword);
        edPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password =  edPassword.getText().toString();
                String passwordConfirm =  edPasswordConfirm.getText().toString();
                Database db= new Database(getApplicationContext(),"aplicatie",null,1);
                if (user.length() == 0 || password.length() ==0 ||email.length() ==0||passwordConfirm.length() ==0){
                    Toast.makeText(getApplicationContext(), "Campurile sunt goale",Toast.LENGTH_SHORT).show();
                }else {
                   if (password.compareTo(passwordConfirm) == 0) {
                        db.register(user,email,password);
                       Toast.makeText(getApplicationContext(), "Ai fost inregistrat cu succes", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(getApplicationContext(), "Parolele nu sunt identice", Toast.LENGTH_SHORT).show();
                   }
                   }
                }

        });


        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}