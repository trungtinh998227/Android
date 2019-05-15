package com.example.mini_project.Activities.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mini_project.Activities.Model.User;
import com.example.mini_project.Activities.Utils.Attribute;

public class DBManagement extends SQLiteOpenHelper {
    private Context context;
    public  DBManagement(Context context){
        super(context,Attribute.DB_NAME,null,Attribute.DB_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create users table
        String CREATE_USERS_TABLE ="create table "+Attribute.DB_TABLE+"( " +
               Attribute.KEY_USERNAME + " text primary key," +
               Attribute.KEY_PASSWORD + " text," +
               Attribute.KEY_BIRTHDAY + " date," +
               Attribute.KEY_NAME + "text )";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //deleting table USERS
        db.execSQL(" drop table if exists "+ Attribute.DB_TABLE);
        //create table
        onCreate(db);
    }

    // Add new user
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Attribute.KEY_USERNAME,user.getUserName());
        value.put(Attribute.KEY_PASSWORD,user.getPassword());
        value.put(Attribute.KEY_BIRTHDAY,user.getBirthday());
        value.put(Attribute.KEY_NAME,user.getName());
        db.insert(Attribute.DB_TABLE,null, value);
        db.close();
    }
}