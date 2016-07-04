package net.beauvine.digitdots;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Contains all shared code for creating numbers and dots.
 */
public class DotHelper {
    public final static float dotScale = 0.1f;
    public static float dotAlpha = 0.6f;
    public static final int dotSize = 8;
    public static final double ratio = (547d / 828d);
    public static int width;
    public static int height;
    public static RelativeLayout myLayout;
    public static View dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8, dot9;
    public static boolean dotsDone = false;
    public static int dotNumber = 1;
    public static final int MY_DOT = 9000;
    public static final int NUM_ID = 1000;
    public static ArrayList<View> dotList = new ArrayList<>();
    public static String toastText = "You did it!";
    public static int totalDots = 2;
    public static MediaPlayer mp2;
    public static MediaPlayer mp;


    public static View createDot(int num, float left, float top, Context context) {
        Button dot = new Button(context);
        dot.setBackgroundResource(R.drawable.circle);
        dot.setId(MY_DOT+num);
        dot.setAlpha(dotAlpha);
        dot.setClickable(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(height/dotSize, height/dotSize);
        params.leftMargin = (int)((width / 2) - height * ratio / left);
        params.topMargin = (int)(height / top);
        myLayout.addView(dot, params);
        dotList.add(dot);
        return dot;
    }

    public static View createLine(int num, float left, float top, Context context) {
        Button dot = new Button(context);
        dot.setBackgroundResource(R.drawable.rectangle);
        dot.setId(MY_DOT+num);
        dot.setAlpha(dotAlpha);
        dot.setClickable(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(height/(dotSize-3.5)), (int)(height/dotSize/2.5));
        params.leftMargin = (int)((width / 2) - height * ratio / left);
        params.topMargin = (int)(height / top);
        if (dot.getId() % 2 == 0) {
            dot.setRotation(45);
        } else {
            dot.setRotation(135);
        }
        myLayout.addView(dot, params);
        dotList.add(dot);
        return dot;
    }

    public static void refreshHelper () {
        for (int i = 0; i < dotList.size(); i++ ){
            View dot = dotList.get(i);
            if (!dot.isClickable()) {
                dot.animate().setDuration(300).setInterpolator(new AnticipateInterpolator())
                        .scaleXBy(-dotScale).scaleYBy(-dotScale).alpha(dotAlpha);
                dot.setClickable(true);}
        }
        dotNumber = 1;
        dotsDone = false;
    }

    public static void checkMode() {
        if (MainActivity.mode.equals("practice")) {
            dotAlpha = 0;
            toastText = "Great job!";
        } else {
            dotAlpha = .8f;
            toastText = "You did it!";
        }
    }

    public static void getScreenDimensions(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    public static void clickHelper (View v, final Context context) {
        int audioId;

        if (v.getId() == NUM_ID+1) {
            if (!dotsDone) {
                mp = MediaPlayer.create(context, R.raw.incorrect);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });

                mp.start();
            }

        } else {
            //animate dot clicked
            v.animate().setDuration(300).setInterpolator(new AnticipateInterpolator())
                    .scaleXBy(dotScale).scaleYBy(dotScale).alpha(1);
            v.setClickable(false);


            //play audio of number of dots clicked
            switch (dotNumber) {
                case 1:
                    audioId = R.raw.one;
                    break;
                case 2:
                    audioId = R.raw.two;
                    break;
                case 3:
                    audioId = R.raw.three;
                    break;
                case 4:
                    audioId = R.raw.four;
                    break;
                case 5:
                    audioId = R.raw.five;
                    break;
                case 6:
                    audioId = R.raw.six;
                    break;
                case 7:
                    audioId = R.raw.seven;
                    break;
                case 8:
                    audioId = R.raw.eight;
                    break;
                case 9:
                default:
                    audioId = R.raw.nine;
                    break;
            }
            dotNumber++;

            if (dotNumber > totalDots) {
                dotsDone = true;
                dotNumber = 1;
            }


            mp = MediaPlayer.create(context, audioId);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {

                    //celebrate if final dot is clicked

                    if (dotsDone) {
                        mp.release();
                        mp2 = MediaPlayer.create(context, R.raw.correct2);
                        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            public void onCompletion(MediaPlayer mp) {
                            mp.release();

                            }});

                        mp2.start();

                        Toast toast = Toast.makeText(context,toastText, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        LinearLayout toastLayout = (LinearLayout) toast.getView();
                        TextView toastTV = (TextView) toastLayout.getChildAt(0);
                        toastTV.setTextSize(30);
                        toast.show();

                    }
                }
            });

            mp.start();

        }
    }


}



