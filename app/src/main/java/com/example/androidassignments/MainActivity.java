package com.example.androidassignments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 5, 1},
        k = 1,
        d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"},
        d2 = {"Lcom/example/androidassignments/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "AndroidAssignments.app"}
)
public final class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    Button ButtonText;
    Button ChatButton;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Intent intent = getIntent();
        if (intent != null) {
            Log.d(ACTIVITY_NAME, "Received Intent");
        }
        ButtonText = findViewById(R.id.button3);
        ButtonText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }

        });
        ChatButton = findViewById(R.id.button2);
        ChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(intent);

            }
        });
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data){
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == 10){
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }
        if (responseCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            int time = Toast.LENGTH_LONG;
            Toast display = Toast.makeText(this, messagePassed, time);
            display.show();
        }
    }


    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
