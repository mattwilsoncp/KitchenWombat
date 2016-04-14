package com.mattwilsoncp16.kitchenwombat;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {
    private RecipeDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);

        RecipeDataSource datasource = new RecipeDataSource(this);

        ArrayList<HashMap<String,String>> recipes = datasource.getAllRecipes();
        RecipeListAdapter adapter=new RecipeListAdapter(this, recipes);
        ListView list = (ListView) findViewById(R.id.listRecipes);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditRecipeActivity.class);
                Bundle b = new Bundle();
                b.putLong("key", parent.getItemIdAtPosition(position));


                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    public void goMainActivity(View view){
        finish();
    }
}
