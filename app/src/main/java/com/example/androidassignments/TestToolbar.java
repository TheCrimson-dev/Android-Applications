package com.example.androidassignments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class TestToolbar extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "TestToolBar";
    static String optionText = "You selected item 1.";

    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Good job", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        switch(mi.getItemId()){
            case R.id.action_one:
                Log.d("Toolbar","Option 1 selected");
                Snackbar.make(findViewById(R.id.action_one), optionText, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case R.id.action_two:
                Log.d("Toolbar", "Option 2 selected");
                AlertDialog.Builder newBuilder = new AlertDialog.Builder(TestToolbar.this);
                newBuilder.setTitle(R.string.tool_dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Clicked ok button
                                Intent intent = new Intent();
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.action_three:
                Log.d("Toolbar", "Option 3 selected");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(TestToolbar.this);
                LayoutInflater inflater = this.getLayoutInflater();
                final View DialogLayout = inflater.inflate(R.layout.dialog_toolbar, null);


                builder2.setView(DialogLayout)
                        .setPositiveButton((R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                optionText = ((EditText) DialogLayout.findViewById(R.id.new_message)).getText().toString();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.actionBtnAbout:
                Log.d("Toolbar", "Option 4 selected");
                CharSequence txt = "Version 1.0 by Alan Iahnis";
                int time = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(getApplicationContext(), txt, time);
                toast.show();
                break;
        }
        return true;
    }
}