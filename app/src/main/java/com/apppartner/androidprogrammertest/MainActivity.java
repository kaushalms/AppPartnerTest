package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tx = (TextView)findViewById(R.id.textView);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Jelloween - Machinato Bold.ttf");
        tx.setTypeface(custom_font);
    }

    public void onLoginButtonClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onChatButtonClicked(View v)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        finish();
    }

    public void onAnimationTestButtonClicked(View v)
    {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
        finish();
    }


}
