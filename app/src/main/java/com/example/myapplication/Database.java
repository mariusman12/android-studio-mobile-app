package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database  extends SQLiteOpenHelper {


    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "aplicatie";
    //Database Table name
    private static final String TABLE_NAME = "facturi";
    //Table columns
    public static final String ID = "id";
    public static final String SOCIETATE = "societate";
    public static final String FACTURANT = "facturant";
    public static final String SERIE = "serie";
    public static final String PRET = "pret";
    private SQLiteDatabase sqLiteDatabase;


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + SOCIETATE + " TEXT NOT NULL,"+FACTURANT+" TEXT NOT NULL,"+SERIE+" TEXT NOT NULL,"+PRET+" TEXT NOT NULL);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text,email text, password text)";
        db.execSQL(query1);

        String query2 = "create table cart(username text,product text, price float)";
        db.execSQL(query2);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register ( String username, String email, String password){
        ContentValues cv =new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username, String password){
        int result =0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db =getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username =? and password =?",str);
        if (c.moveToFirst()){ // daca a gasit result se face 1 deci avem userul in bd
            result=1;
        }
        return result;
    }

    public void addCart(String username,String product, float price){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery("select * from cart where username=? and product = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
    public void removeCart(String username){
        String str[] = new String[1];
        str[0] = username;
        SQLiteDatabase db =getWritableDatabase();
        db.delete("cart","username =?",str);
        db.close();
    }

    public ArrayList getCartData(String username){
        ArrayList<String> arr =new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();
        String str[] = new String[1];
        str[0]=username;
        Cursor c = db.rawQuery("select * from cart where username=?", str);
        if (c.moveToFirst()) {

            do{
                String product= c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);

            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void adaugaFactura(FacturaModelClass facturaModelClass){
        ContentValues cv = new ContentValues();
        cv.put("societate", facturaModelClass.getSocietate());
        cv.put("facturant", facturaModelClass.getFacturant());
        cv.put("serie", facturaModelClass.getSerie());
        cv.put("pret", facturaModelClass.getPret());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("facturi",null,cv);
        db.close();
    }

    public List<FacturaModelClass> getFacturiList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<FacturaModelClass> storeFacturi = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String societate = cursor.getString(1);
                String facturant = cursor.getString(2);
                String serie = cursor.getString(3);
                String pret = cursor.getString(4);
                storeFacturi.add(new FacturaModelClass(id,societate,facturant,serie,pret));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeFacturi;
    }

    public void updateFactura(FacturaModelClass facturaModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.SOCIETATE,facturaModelClass.getSocietate());
        contentValues.put(Database.FACTURANT,facturaModelClass.getFacturant());
        contentValues.put(Database.SERIE,facturaModelClass.getSerie());
        contentValues.put(Database.PRET,facturaModelClass.getPret());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(facturaModelClass.getId())});
    }

    public void deleteFactura(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }


}
