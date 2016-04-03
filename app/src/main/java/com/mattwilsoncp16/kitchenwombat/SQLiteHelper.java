package com.mattwilsoncp16.kitchenwombat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {


    // Database creation SQL statement
    private static final String DATABASE_CREATE =
            "create table "+ Recipe.TABLE_NAME +"(_id integer primary key autoincrement, " +
                    "name text, " +
                    "description text, " +
                    "cookTime int, " +
                    "cookingMethod text, " +
                    "prepTime int, " +
                    "recipeCategory text, " +
                    "recipeCuisine text, " +
                    "totalTime int)";

    public SQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + Recipe.TABLE_NAME);
        onCreate(db);
    }

}
