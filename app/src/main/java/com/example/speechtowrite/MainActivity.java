package com.example.speechtowrite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {



    SharedPreferences pref;
    EditText ed;

    TextToSpeech tts;
    String word;
    int x ;

    boolean isTrue=false ;
    private int rand;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.editText);

        //isTrue=true;

        tts = new TextToSpeech(this, this);



        pref=getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("0","play");
        editor.putString("1","Done");
        editor.putString("2","go");
        editor.putString("3","chair");
        editor.putString("4","teacher");
        editor.putString("5","go");
        editor.putString("6","computer");
        editor.putString("7","mobile");

        editor.commit();

    }

    public void listen(View view) {


        if(isTrue==true) {
            Random random = new Random();
            x = random.nextInt(7) + 1;
            rand = x ;
            word=pref.getString(String.valueOf(x),"");


        }else{
            word=pref.getString(String.valueOf(rand),"");

        }


       // word=pref.getString(String.valueOf(x),"");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(word + "", TextToSpeech.QUEUE_FLUSH, null, null);
        } else
            tts.speak(word + "", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Check(View view) {

        String write_word = ed.getText().toString();

        if(write_word.equalsIgnoreCase(word)){
            Toast.makeText(this, "Good", Toast.LENGTH_SHORT).show();
            isTrue=true ;
            ed.setText("");
        }else{
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
            isTrue=false;
        }

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.ENGLISH);
//            tts.setSpeechRate(0.7f);
//            tts.setPitch(0.7f);
        }
    }
}
