package net.beauvine.digitdots;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import static net.beauvine.digitdots.DotHelper.*;


public class DotsFive extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dots);

        //initialize variables
        totalDots = 5;
        dotsDone = false;
        dotNumber = 1;
        myLayout = (RelativeLayout) findViewById(R.id.mainView);

        getScreenDimensions(this);

        checkMode();

        //create screen layout
        NumberBackground numView = new NumberBackground(this);
        numView.numBitmap = MainActivity.fiveBitmap;
        numView.setOnClickListener(this);
        numView.setClickable(true);
        numView.setId(NUM_ID+1);
        myLayout.addView(numView);

        dot1 = createDot(1, -5.5f, 17.5f, this);
        dot2 = createDot(2, 3.5f, 17.5f, this);
        dot3 = createDot(3, 2.7f, 2.8f, this);
        dot4 = createDot(4, -5.1f, 2f, this);
        dot5 = createDot(5, 2.55f, 1.75f, this);
        dot1.setOnClickListener(this);
        dot2.setOnClickListener(this);
        dot3.setOnClickListener(this);
        dot4.setOnClickListener(this);
        dot5.setOnClickListener(this);

        getLayoutInflater().inflate(R.layout.navbuttons, myLayout);

    }


    public void onClick(View v) {
        clickHelper(v, getApplicationContext());
    }


    public void refreshDots(View view) {
        refreshHelper();
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void leftNav(View view) {
        Intent intent = new Intent(this, DotsFour.class);
        startActivity(intent);
    }

    public void rightNav(View view) {
        Intent intent = new Intent(this, DotsSix.class);
        startActivity(intent);
    }
}