package com.mattwilsoncp16.kitchenwombat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class EditRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        Long id = b.getLong("key");
        RecipeDataSource ds = new RecipeDataSource(this);
        ds.open();
        Recipe recipe = ds.getRecipe(id);
        ds.close();

        View vi = (View) findViewById(R.id.ScrollView01);
        TextView recipe_name = (TextView)vi.findViewById(R.id.EditRecipeName);
        recipe_name.setText(recipe.getName());
        TextView recipe_description = (TextView)vi.findViewById(R.id.EditRecipeDescription);
        recipe_description.setText(recipe.getDescription());

    }

}
