package com.example.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    int count=0; // These things need to be global so you can pass them between onCreate() and onClick()
    TextView display;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Content view is set to the layout at /app/res/layout/activity_main.xml
        display=findViewById(R.id.count); // display and button are instantiated as their counterparts in the layout
        button=findViewById(R.id.button);
        button.setOnClickListener(new ClickListener()); // button gets its ClickListener (you can give many things the same listener if you want)
    }

    public class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){ // When the button is clicked...
            count++; // ...count is incremented by 1...
            Log.e("Current count",count+""); // ...we log the new count (should be in Logcat)...
            display.setText(count+""); // ...and set the counter display to the new value
        }
    }
}