package com.example.hocta.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hocta.model.User;

public class SQLiteUserHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="U.db";
    private static final int DB_VERSION = 3;

    public SQLiteUserHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatabase = "CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoten TEXT," +
                "username TEXT," +
                "password TEXT," +
                "ngaysinh TEXT," +
                "gioitinh TEXT)";
        db.execSQL(sqlCreateDatabase);
    }
    //add user
    public void addUser(User user){
        String sql = "INSERT INTO user(hoten, username, password, ngaysinh, gioitinh)" +
                "VALUES(?,?,?,?,?)";
        String[] args = {user.getHoten(), user.getUsername(), user.getPassword(),
        user.getNgaysinh(), user.getGioitinh()};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //update user
    public void updateUser(User user){
        String sql = "UPDATE user SET hoten=?, username=?, password=?, ngaysinh=?, gioitinh=? WHERE id=?";
        String[] args = {user.getHoten(),user.getUsername(), user.getPassword(), user.getNgaysinh(),
                user.getGioitinh(), Integer.toString(user.getId())};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    public User getUserByUserName(String key){
        String sql = "username = ?";
        String[] args = {key};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("user", null, sql, args, null, null, null);
        if (cursor!=null && cursor.moveToNext()){
            int idd = cursor.getInt(0);
            String hoten= cursor.getString(1);
            String username = cursor.getString(2);
            String pass = cursor.getString(3);
            String ngay = cursor.getString(4);
            String gt = cursor.getString(5);
            User user = new User(idd, hoten, username, pass, ngay, gt);
            return user;
        }
        return null;
    }

    public boolean checkUser(String userName){
        String sql = "username = ?";
        String[] args = {userName};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("user", null, sql, args, null, null, null);
        if (cursor!=null && cursor.moveToNext()){
            return true;
        }
        return false;
    }
    public boolean checkUser(String userName, String password){
        String sql = "username=? AND password=?";
        String[] args = {userName, password};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("user", null, sql, args, null, null, null);
        if (cursor!=null){
            return true;
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
