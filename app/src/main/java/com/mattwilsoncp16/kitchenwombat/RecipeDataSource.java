package com.mattwilsoncp16.kitchenwombat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RecipeDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private String[] allColumns = {
            Recipe.ID,
            Recipe.NAME,
            Recipe.DESCRIPTION
           };

    public RecipeDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Recipe createRecipe(String name) {
        ContentValues values = new ContentValues();
        values.put(Recipe.NAME, name);
        long insertId = database.insert(Recipe.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(Recipe.TABLE_NAME,
                allColumns, Recipe.ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Recipe newRecipe = cursorToRecipe(cursor);
        cursor.close();
        return newRecipe;
    }

    private Recipe cursorToRecipe(Cursor cursor) {
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getLong(0));
        recipe.setName(cursor.getString(1));
        return recipe;
    }

      public ArrayList<HashMap<String,String>> getAllRecipes() {

        ArrayList<HashMap<String,String>> recipes = new ArrayList<HashMap<String, String>>();
        Cursor cursor = database.query(Recipe.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            //map.put("_id", cursor.getLong(0));
            map.put("title", cursor.getString(1));
            map.put("description", cursor.getString(2));
            recipes.add(map);
            cursor.moveToNext();
        }
        cursor.close();
        return recipes;
    }

}
