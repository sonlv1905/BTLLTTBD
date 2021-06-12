package com.example.hocta.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hocta.model.TuMoi;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTuMoiHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Word.db";
    private static final int DB_VERSION = 2;

    public SQLiteTuMoiHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatabase = "CREATE TABLE word(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tu TEXT," +
                "loaitu TEXT," +
                "nghia TEXT)";
        db.execSQL(sqlCreateDatabase);
    }

    //add
    public void addTM(TuMoi tu){
        String sql = "INSERT INTO word(tu, loaitu, nghia) VALUES(?,?,?)";
        String[] args = {tu.getTu(), tu.getLoaiTu(), tu.getNghia()};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //update
    public void updateTM(TuMoi tu){
        String sql = "UPDATE word SET tu=?, loaitu=?, nghia=? WHERE id=?";
        String[] args = {tu.getTu(), tu.getLoaiTu(), tu.getNghia(), Integer.toString(tu.getId())};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //delete
    public void deleteTM(int id){
        String sql = "DELETE FROM word WHERE id=?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase stm = getWritableDatabase();
        stm.execSQL(sql, args);
    }

    //getAll
    public List<TuMoi> getAll(){
        List<TuMoi> list = new ArrayList<>();
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("word", null, null, null, null, null, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tu = cursor.getString(1);
            String loaitu = cursor.getString(2);
            String nghia = cursor.getString(3);
            list.add(new TuMoi(id, tu,loaitu ,nghia));
        }
        return list;
    }

    //get by loai tu
    public List<TuMoi> getByLoaiTu(String loai){
        List<TuMoi> list = new ArrayList<>();
        String sql = "loaitu like ?";
        String[] args = {loai};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("word", null, sql, args, null, null, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tu = cursor.getString(1);
            String loaitu = cursor.getString(2);
            String nghia = cursor.getString(3);
            list.add(new TuMoi(id, tu,loaitu ,nghia));
        }
        return list;
    }

    //get by tu
    public List<TuMoi> getByTu(String word){
        List<TuMoi> list = new ArrayList<>();
        String sql = "tu like ?";
        String[] args = {"%"+word+"%"};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("word", null, sql, args, null, null, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tu = cursor.getString(1);
            String loaitu = cursor.getString(2);
            String nghia = cursor.getString(3);
            list.add(new TuMoi(id, tu,loaitu ,nghia));
        }
        return list;
    }
    //get by tu 2
    public boolean getTu(String key){
        String sql = "tu = ?";
        String[] args = {key};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("word", null, sql, args, null, null, null);
        if (cursor!=null && cursor.moveToNext()){
            return true;
        }
        return false;
    }

    //get by id
    public TuMoi getById(int idTu){
        TuMoi tuMoi ;
        String sql = "id=?";
        String [] args = {Integer.toString(idTu)};
        SQLiteDatabase stm = getReadableDatabase();
        Cursor cursor = stm.query("word", null, sql, args, null,null, null);
        if(cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tu = cursor.getString(1);
            String loaitu = cursor.getString(2);
            String nghia = cursor.getString(3);
            tuMoi = new TuMoi(id, tu, loaitu, nghia);
            return tuMoi;
        }
        return null;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
