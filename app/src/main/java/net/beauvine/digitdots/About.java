package net.beauvine.digitdots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class About extends AppCompatActivity {
    TextView aboutText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutText = (TextView) findViewById(R.id.aboutText);

        aboutText.setMovementMethod(new ScrollingMovementMethod());
        Linkify.addLinks(aboutText, Linkify.WEB_URLS);
    }
}
