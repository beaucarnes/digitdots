package net.beauvine.digitdots;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Add extends AppCompatActivity {

    ImageView firstNumberIV, secondNumberIV;
    TextView answer1TV, answer2TV, answer3TV, scoreNumber;
    int firstNumber, secondNumber, answer1, answer2, answer3, correctAnswer, answerNumber;
    int questionNumber = 1;
    Random random = new Random();
    String imageName;
    int resID;
    int score;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        firstNumberIV = (ImageView) findViewById(R.id.firstNumber);
        secondNumberIV = (ImageView) findViewById(R.id.secondNumber);
        answer1TV = (TextView) findViewById(R.id.answer1);
        answer2TV = (TextView) findViewById(R.id.answer2);
        answer3TV = (TextView) findViewById(R.id.answer3);
        scoreNumber = (TextView) findViewById(R.id.scoreNumber);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            score = savedInstanceState.getInt("SCORE");
            questionNumber = savedInstanceState.getInt("QUESTION");
            scoreNumber.setText(Integer.toString(score));

        }

        getNumbers();


    }

    private void getNumbers() {
        firstNumber = random.nextInt(9) + 1;
        secondNumber = random.nextInt(9) + 1;
        answerNumber = random.nextInt(3) + 1;
        correctAnswer = firstNumber + secondNumber;

        //get random numbers within 3 of correct answer, no repeats, none less than 0
        ArrayList<Integer> listRand = new ArrayList<>();
        ArrayList<Integer> listAnswers = new ArrayList<>();
        for (int i=correctAnswer-3; i<correctAnswer+4; i++) {
            listRand.add(Integer.valueOf(i));
        }
        int counter = 0;
        Collections.shuffle(listRand);
        while (listAnswers.size() < 3) {
            if (listRand.get(counter) > 0 && listRand.get(counter) != correctAnswer)
                listAnswers.add(listRand.get(counter));
            counter++;
        }

        answer1 = answerNumber == 1?correctAnswer:listAnswers.get(0);
        answer2 = answerNumber == 2?correctAnswer:listAnswers.get(1);
        answer3 = answerNumber == 3?correctAnswer:listAnswers.get(2);



        answer1TV.setText(Integer.toString(answer1));
        answer2TV.setText(Integer.toString(answer2));
        answer3TV.setText(Integer.toString(answer3));
        Log.i("logging", "onCreate: " + firstNumber + " " + secondNumber);
        imageName = "dot" + Integer.toString(firstNumber);
        resID = getResources().getIdentifier(imageName, "drawable", "net.beauvine.digitdots");
        firstNumberIV.setImageResource(resID);
        imageName = "dot" + Integer.toString(secondNumber);
        resID = getResources().getIdentifier(imageName, "drawable", "net.beauvine.digitdots");
        secondNumberIV.setImageResource(resID);

    }

    public void answerThree(View view) {
        if (answerNumber == 3) {answerRight();} else {answerWrong(view);}
    }

    public void answerTwo(View view) {
        if (answerNumber == 2) {answerRight();} else {answerWrong(view);}

    }

    public void answerOne(View view) {
        if (answerNumber == 1) {answerRight();} else {answerWrong(view);}

    }

    private void answerRight() {
        score++;
        mp = MediaPlayer.create(this, R.raw.correct);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }});

        mp.start();
        if (questionNumber==10) quizComplete();
        getNumbers();
        answer1TV.setAlpha(1);
        answer2TV.setAlpha(1);
        answer3TV.setAlpha(1);
        answer1TV.setClickable(true);
        answer2TV.setClickable(true);
        answer3TV.setClickable(true);
        questionNumber++;
        scoreNumber.setText(Integer.toString(score));

    }

    private void answerWrong(View view) {
        view.setClickable(false);
        view.setAlpha(0.5f);
        if (score > 0) score--;

        scoreNumber.setText(Integer.toString(score));
        mp = MediaPlayer.create(this, R.raw.incorrect);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }});

        mp.start();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt("SCORE", score);
        savedInstanceState.putInt("QUESTION", questionNumber);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void quizComplete() {
        Intent intent = new Intent(this, Score.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }
}
