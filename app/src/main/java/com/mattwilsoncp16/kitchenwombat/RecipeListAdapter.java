package com.mattwilsoncp16.kitchenwombat;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    public RecipeListAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return Long.parseLong(data.get(position).get("_id"));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView recipe_title = (TextView)vi.findViewById(R.id.recipe_title);
        TextView recipe_description = (TextView)vi.findViewById(R.id.recipe_description);
        TextView recipe_totaltime = (TextView)vi.findViewById(R.id.recipe_totaltime);
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

       // HashMap<String,String> recipe =  data.get(position);

        // Setting all values in listview
        recipe_title.setText(data.get(position).get("name"));
        recipe_description.setText(data.get(position).get("description"));
        recipe_totaltime.setText("00:30");

        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }




}
