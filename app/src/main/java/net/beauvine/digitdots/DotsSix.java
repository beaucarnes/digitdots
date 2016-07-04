package net.beauvine.digitdots;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import static net.beauvine.digitdots.DotHelper.*;


public class DotsSix extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dots);

        //initialize variables
        totalDots = 6;
        dotsDone = false;
        dotNumber = 1;
        myLayout = (RelativeLayout) findViewById(R.id.mainView);

        getScreenDimensions(this);

        checkMode();

        //create screen layout
        NumberBackground numView = new NumberBackground(this);
        numView.setOnClickListener(this);
        numView.setClickable(true);
        numView.setId(NUM_ID+1);
        numView.numBitmap = MainActivity.sixBitmap;
        myLayout.addView(numView);

        dot1 = createLine(1, 2.15f, 2.4f, this);
        dot2 = createLine(2, 2.15f, 2.4f, this);
        dot3 = createLine(3, 2.8f, 6.5f, this);
        dot4 = createLine(4, 2.8f, 6.5f, this);
        dot5 = createLine(5, 2.53f, 1.5f, this);
        dot6 = createLine(6, 2.53f, 1.5f, this);
        dot1.setOnClickListener(this);
        dot2.setOnClickListener(this);
        dot3.setOnClickListener(this);
        dot4.setOnClickListener(this);
        dot5.setOnClickListener(this);
        dot6.setOnClickListener(this);

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
        Intent intent = new Intent(this, DotsFive.class);
        startActivity(intent);
    }

    public void rightNav(View view) {
        Intent intent = new Intent(this, DotsSeven.class);
        startActivity(intent);
    }
}