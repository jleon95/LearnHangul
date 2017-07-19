package com.learnhangul.learnhangul;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutHangulActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_hangul);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleMarginStart(90);
        toolbar.setTitle(getString(R.string.toolbat_title_about_hangul));
        setSupportActionBar(toolbar);

        TextView textAboutHangul = (TextView) findViewById(R.id.text_about_hangul);
        textAboutHangul.setMovementMethod(new ScrollingMovementMethod());

    }
}
