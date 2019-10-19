package com.sanjaykua.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBasehelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="employee.db";
    public  static final String TABLE_NAME="employeeDetails";
    public  static final String COL1="ID";
    public  static final String COL2="NAME";
    public  static final String COL3="MOBILE";
    public  static final String COL4="EMAIL";
    public  static final String COL5="PASSWORD";

    public DataBasehelper(MainActivity mainActivity) {
        super(mainActivity,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +" NAME TEXT,MOBILE TEXT,EMAIL TEXT,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String mobile,String email,String paasword) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, mobile);
        contentValues.put(COL4, email);
        contentValues.put(COL5, paasword);

        long result = database.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

        public Cursor getAllData()
        {
            SQLiteDatabase database1=this.getWritableDatabase();
            Cursor res=database1.rawQuery("select * from "+TABLE_NAME,null);
            return res;
        }

        public boolean updateData(String id,String name,String mobile,String email,String paasword){
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL1, id);
            contentValues.put(COL2, name);
            contentValues.put(COL3, mobile);
            contentValues.put(COL4, email);
            contentValues.put(COL5, paasword);

            database.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
            return true;
        }

        public Integer delateData(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID = ?",new String[]{id});
        }

}
