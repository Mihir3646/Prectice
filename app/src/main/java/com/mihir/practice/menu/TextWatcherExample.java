package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.mihir.practice.R;

public class TextWatcherExample extends Activity {
    private TextView tv_password_strength = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_watcher);

        // Get the Reference of EditText
        final EditText mEt_password = (EditText) findViewById(R.id.et_password);
        tv_password_strength = (TextView) findViewById(R.id.tv_password_strength);

        // Attach TextWatcher to EditText
        mEt_password.addTextChangedListener(mTextEditorWatcher);
    }

    // EditTextWacther Implementation
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // When No Password Entered
            tv_password_strength.setText("Not Entered");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0)
                tv_password_strength.setText("Not Entered");
            else if (s.length() < 6)
                tv_password_strength.setText("EASY");
            else if (s.length() < 10)
                tv_password_strength.setText("MEDIUM");
            else if (s.length() < 15)
                tv_password_strength.setText("STRONG");
            else
                tv_password_strength.setText("STRONGEST");

            if (s.length() == 20)
                tv_password_strength.setText("Password Max Length Reached");
        }
    };
}
