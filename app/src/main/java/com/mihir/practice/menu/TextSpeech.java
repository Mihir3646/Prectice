package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mihir.practice.R;

import java.util.Locale;

public class TextSpeech extends Activity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btn_speak;
    private EditText et_text_to_speech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_speech);

        tts = new TextToSpeech(this, this);
        btn_speak = (Button) findViewById(R.id.btn_speak);
        et_text_to_speech = (EditText) findViewById(R.id.et_text_to_speech);

        // button on click event
        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }
        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btn_speak.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {
        String text = et_text_to_speech.getText().toString();
        tts.setPitch((float) 1);
        //		tts.setSpeechRate(2);
        //		tts.setLanguage(Locale.CHINESE); // Chinese language
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}