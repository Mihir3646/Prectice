package com.mihir.practice;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Mihir
 * @description: Menu List class for all small Apps
 */
public class MenuActivity extends ListActivity {
    //Array for Menu items
    private String MenuItems[] = {"Email", "Camera", "BitmapAnim", "FileOperations",
            "TabView", "Map", "TextSpeech", "GridViewActivity",
            "AnimationViews", "TextWatcherExample", "TextSwitcherDemo",
            "SwipeEventDemo", "VibraterDemo", "AutoCompleteTextBox",
            "CustomeToast", "TabViewSms"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting adapter for Menu List...
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MenuItems));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Class<?> ourClass = Class.forName("com.mihir.practice.menu." + MenuItems[position]);

            Intent menuIntent = new Intent(MenuActivity.this, ourClass);
            startActivity(menuIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aboutus:
                Intent mIntent = new Intent(MenuActivity.this, AboutUs.class);
                startActivity(mIntent);
                break;
            case R.id.menu_contactus:

                break;
        }
        return false;
    }
}
