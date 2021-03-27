package com.example.androidbasics2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count=0; // Prepare our global variables
    int purchaseCount=0;
    int cost=5;
    TextView display,purchaseDisplay;
    Button button,purchase;
    Handler handler; // Used to "schedule" future executions on a separate thread

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // onCreate() does the usual stuff (see Android Basics (the first app))
        display=findViewById(R.id.display);
        purchaseDisplay=findViewById(R.id.purchaseDisplay);
        button=findViewById(R.id.button);
        purchase=findViewById(R.id.purchase);
        button.setOnClickListener(new ClickListener());
        purchase.setOnClickListener(new ClickListener()); // Reused the same ClickListener for fun
        handler=new Handler();
        handler.postDelayed(new Runnable(),500); // handler starts doing its thing, scheduled in 500 milliseconds (half a second)
    }

    public class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){ // view is the component that just got clicked
            if(view.getId()==R.id.button){ // If it's the button, do the standard Cookie Clicker stuff...
                count++;
                Log.e("Current count",count+"");
                display.setText(count+"");
            }
            else{ // ...otherwise, it's the purchase button
                if(count>=cost){ // If the user can afford a thingy...
                    count-=cost; // ...take the payment...
                    purchaseCount++; // ...purchase a thingy...
                    cost*=2; // ...and double the cost of purchasing the next thingy (not very balanced)
                    Log.e("Current purchase count",purchaseCount+"");
                    display.setText(count+""); // Adjust all the displays
                    purchaseDisplay.setText("Thingies:"+purchaseCount);
                    purchase.setText("Purchase thingy for "+cost+" things"); // Buttons have text too!
                }
                else{ // If they couldn't afford a thingy, display a Toast (popup standardized for Android development)
                    Toast.makeText(getApplicationContext(),"You don't have enough thingies!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class Runnable implements java.lang.Runnable{
        @Override
        public void run(){ // run() is called in the handler's thread after 500 milliseconds
            count+=purchaseCount; // Count goes up by 1 every tick for every thingy owned
            Log.e("Current count",count+"");
            display.setText(count + "");
            handler.postDelayed(this,500); // The next run() is scheduled in 500 milliseconds
        }
    }
}