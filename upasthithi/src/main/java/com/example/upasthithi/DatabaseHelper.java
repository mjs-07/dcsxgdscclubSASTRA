package com.example.upasthithi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_name = "Signup.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase UpasthithiDatabase) {
        UpasthithiDatabase.execSQL("create Table allusers(name TEXT, email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase UpasthithiDatabase, int i, int i1) {
        UpasthithiDatabase.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String name,String email, String password){
        SQLiteDatabase UpasthithiDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = UpasthithiDatabase.insert("allusers", null, contentValues);

        if (result == 1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkName(String name){
        SQLiteDatabase UpasthithiDatabase = this.getWritableDatabase();
        Cursor cursor = UpasthithiDatabase.rawQuery("Select * from allusers where name = ?",new String[]{name});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase UpasthithiDatabase = this.getWritableDatabase();
        Cursor cursor = UpasthithiDatabase.rawQuery("Select * from allusers where email = ?",new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase UpasthithiDatabase = this.getWritableDatabase();
        Cursor cursor = UpasthithiDatabase.rawQuery("Select * from allusers where email = ? and password = ?",new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
