package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton imageButton;
    Switch SwitchText;
    Switch switch3;
    CheckBox Checkbox_Text;
    CheckBox checkbox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                }
            }


        });
        SwitchText = findViewById(R.id.switch3);
        SwitchText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton switchView, boolean isChecked) {
                if (isChecked == true){
                    CharSequence textOn = getString(R.string.switch_on);
                    int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

                    Toast toast = Toast.makeText(ListItemsActivity.this , textOn, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }
                if (isChecked == false){
                    CharSequence textOn = getString(R.string.switch_off);
                    int duration = Toast.LENGTH_LONG; //= Toast.LENGTH_LONG if Off

                    Toast toast = Toast.makeText(ListItemsActivity.this , textOn, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }


            }
        });
        Checkbox_Text = findViewById(R.id.checkBox4);
        Checkbox_Text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", getString(R.string.activity_completed));
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish(); // User clicked OK button
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss(); // User cancelled the dialog
                            }
                        })
                        .show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton btnImg = findViewById(R.id.imageButton);
            btnImg.setImageBitmap(imageBitmap);
        }
    }


        protected void onResume () {
            super.onResume();
            Log.i(ACTIVITY_NAME, "In onResume()");
        }


        protected void onStart () {
            super.onStart();
            Log.i(ACTIVITY_NAME, "In onStart()");
        }

        protected void onPause () {
            super.onPause();
            Log.i(ACTIVITY_NAME, "In onPause()");
        }


        protected void onStop () {
            super.onStop();
            Log.i(ACTIVITY_NAME, "In onStop()");
        }

        protected void onDestroy () {
            super.onDestroy();
            Log.i(ACTIVITY_NAME, "In onDestroy()");
        }
    @Override

    public void onBackPressed() {

        super.onBackPressed();

        setResult(Activity.RESULT_CANCELED);

        finish();

    }


    }
