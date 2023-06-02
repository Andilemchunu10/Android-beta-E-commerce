package com.example.android_beta_e_commerce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper databaseHelper;
    private Context context;
    private static final String DATABASE_NAME="Groceries";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME ="Products";
    private static final String COLUMN_ID ="id";
    private static final String COLUMN_PRODUCTIMG ="productImg";
    private static final String COLUMN_PRODUCTNAME ="productName";
    private static final String COLUMN_PRODUCTPRICE ="productPrice";
    private static final String COLUMN_PRODUCTCATEGORY ="productCategory";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+TABLE_NAME+
                        " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_PRODUCTIMG +" TEXT,"+
                        COLUMN_PRODUCTNAME+" TEXT,"+
                        COLUMN_PRODUCTPRICE+" TEXT,"+
                        COLUMN_PRODUCTCATEGORY+" TEXT);";
        String ROW1 = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_PRODUCTIMG + ", " + COLUMN_PRODUCTNAME + ", "
                + COLUMN_PRODUCTPRICE + ", " + COLUMN_PRODUCTCATEGORY + ", "
                + KEY_EXPIRYDATE + ") Values ('Cash', '', '', '0', '')";
        db.execSQL(ROW1);
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
