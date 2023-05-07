package com.gangula.yumyumapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.net.ConnectException;

public class DatabaseHelper extends SQLiteOpenHelper {

    // database name
    public static final String DATABASE_NAME = "yum_yum_app.db";


    public DatabaseHelper(Context context) {
        super(context, "yum_yum_app.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // SQL query for the database
        sqLiteDatabase.execSQL("create table registerUser_table (User_id INTEGER primary key autoincrement, F_NAME TEXT, L_NAME TEXT,  EMAIL TEXT, M_NUMBER TEXT, PASSWD TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE recipet_list (" +
                "Reci_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "User_id INTEGER," +
                "Recipet_Title TEXT," +
                "Recipet_Description TEXT," +
                "CreatAt DATETIME," +
                "FOREIGN KEY(User_id) REFERENCES registerUser_table(User_id))" +
                "");

    }


    // create database if its not exits
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS registerUser_table");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS recipet_list");
        onCreate(sqLiteDatabase);

    }

    // **************************************************************************************************
    // insert to the register table
    public boolean insertData(String f_name, String l_name, String email, String number, String passwd){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("F_NAME", f_name);
        contentValues.put("L_NAME", l_name);
        contentValues.put("EMAIL", email);
        contentValues.put("M_NUMBER", number);
        contentValues.put("PASSWD", passwd);


        long result  = db.insert("registerUser_table", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
// get all data from register table
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from registerUser_table" , null);
        return result;

    }
    // check user passwd
    public Boolean checkUserPasswd(String username, String passwd){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT EMAIL, PASSWD FROM registerUser_table WHERE EMAIL = ? AND passwd = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, passwd});
        boolean result = cursor.moveToFirst();
        cursor.close();


        return result;
    }

    // update the register table
    public boolean updateData(String id, String f_name, String l_name, String email, String number, String passwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_id", id);
        contentValues.put("F_NAME", f_name);
        contentValues.put("L_NAME", l_name);
        contentValues.put("EMAIL", email);
        contentValues.put("M_NUMBER", number);
        contentValues.put("PASSWD", passwd);


        db.update("registerUser_table", contentValues, "id = ? ", new String[] {id});
        return  true;
    }

    // delete the data form the register table
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("registerUser_table", "ID = ?", new String[] {id});

    }
    // ************************************************************************************************************
    // code for the recipes table.
    public boolean recipe_list(Integer userID, String Recipe_title, String Recipe_description, String createdAt){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("User_id",userID);
        contentValues.put("Recipet_Title",Recipe_title);
        contentValues.put("Recipet_Description",Recipe_description);
        contentValues.put("CreatAt",createdAt);

        long result = db.insert("recipet_list", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    // code for the data retrieve

    public Cursor getAllRecipe(String currentUserID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT User_id, Recipet_Title, Recipet_Description FROM recipet_list WHERE User_id = ?", new String[]{currentUserID});

        return result;
    }

// ************************************************************************************************************************
    // common function for the data retrieve
        // Get the user id by email
        public int getUserIdByEmail(String email) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT User_id FROM registerUser_table WHERE EMAIL=?", new String[]{email});

            int userId = -1;
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(0);
            }

            cursor.close();
            db.close();
            return userId;
        }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from registerUser_table where EMAIL = ? and PASSWD = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    // check if the email is already exits
    public boolean checkEmailAllReadyReg(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from registerUser_table where EMAIL = ?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }




}
