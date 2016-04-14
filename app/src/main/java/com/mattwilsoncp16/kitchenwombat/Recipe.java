package com.mattwilsoncp16.kitchenwombat;

public class Recipe {
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String COOKTIME = "cookTime";
    public static final String COOKINGMETHOD = "cookingMethod";
    public static final String PREPTIME = "prepTime";
    public static final String RECIPECUISINE = "recipeCuisine";
    public static final String TOTALTIME = "totalTime";

    private long id;
    private String name;
    private String description;

    public static final String TABLE_NAME = "RECIPES";

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }


    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description) { this.description = description; }
}
