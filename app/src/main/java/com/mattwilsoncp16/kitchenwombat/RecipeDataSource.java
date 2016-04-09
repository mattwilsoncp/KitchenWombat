package com.mattwilsoncp16.kitchenwombat;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



public class RecipeDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String json_response;

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

    public void createRecipe(String name, String description) {
        try {
            ContentValues values = new ContentValues();
            values.put(Recipe.NAME, name);
            values.put(Recipe.DESCRIPTION, description);
            long insertId = database.insert(Recipe.TABLE_NAME, null,
                    values);
            Cursor cursor = database.query(Recipe.TABLE_NAME,
                    allColumns, Recipe.ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            this.cursorToRecipe(cursor);
            cursor.close();
        }catch (Exception e){
            Log.d("WOMBAT", "EXCEPTION: " + e);
        }
    }

    private void cursorToRecipe(Cursor cursor) {
        Recipe recipe = new Recipe();
        recipe.setId(cursor.getLong(0));
        recipe.setName(cursor.getString(1));
    }

    public ArrayList<HashMap<String, String>> getAllRecipes() {
        this.open();
        ArrayList<HashMap<String, String>> recipes = new ArrayList<HashMap<String, String>>();
        Cursor cursor = database.query(Recipe.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            //map.put("_id", cursor.getLong(0));
            map.put("name", cursor.getString(1));
            map.put("description", cursor.getString(2));
            recipes.add(map);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return recipes;
    }

    public void ImportJson(String response){
        try {
            JSONArray reader = new JSONArray(response);
            this.open();
            for(int i = 0 ; i < reader.length(); i++) {
                JSONObject jo = reader.getJSONObject(i);
                this.createRecipe((String) jo.get("title"), (String) jo.get("description"));
            }
            this.close();
        }catch(Exception e){
            Log.d("WOMBAT", "Exception: " + e);
        }
    }


}
