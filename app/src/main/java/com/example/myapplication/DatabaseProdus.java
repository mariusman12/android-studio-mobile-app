package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.myapplication.DatabaseProdus;
public class DatabaseProdus extends SQLiteOpenHelper {
    public DatabaseProdus(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table produs(nume text,pret text, descriere text)";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adaugaprodus ( String nume, String pret, String descriere){
        ContentValues cv =new ContentValues();
        cv.put("nume", nume);
        cv.put("pret", pret);
        cv.put("descriere", descriere);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("produs",null,cv);
        db.close();
    }


}
