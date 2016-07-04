package net.beauvine.digitdots;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import static net.beauvine.digitdots.DotHelper.*;


public class DotsFour extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dots);

        //initialize variables
        totalDots = 4;
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
        numView.numBitmap = MainActivity.fourBitmap;
        myLayout.addView(numView);

        dot1 = createDot(1, 2.52f, 2.2f, this);
        dot3 = createDot(2, -11.5f, 2.2f, this);
        dot2 = createDot(3, -11.5f, 20f, this);
        dot4 = createDot(4, -11.5f, 1.37f, this);
        dot1.setOnClickListener(this);
        dot2.setOnClickListener(this);
        dot3.setOnClickListener(this);
        dot4.setOnClickListener(this);

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
        Intent intent = new Intent(this, DotsThree.class);
        startActivity(intent);
    }

    public void rightNav(View view) {
        Intent intent = new Intent(this, DotsFive.class);
        startActivity(intent);
    }
}