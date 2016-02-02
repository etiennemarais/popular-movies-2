package com.olx.etiennemarais.popularmovies.views.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.olx.etiennemarais.popularmovies.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Fragment listFragment = new ListFragment();

        getSupportFragmentManager().beginTransaction()
            .add(R.id.main_content, listFragment)
            .commit();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
