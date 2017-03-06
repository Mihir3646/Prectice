package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.mihir.practice.R;

public class AutoCompleteTextBox extends Activity {
    private AutoCompleteTextView autoCompletetv_contry = null;
    private TextView tv_country = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_complete_textbox);

        String COUNTRIES[] = {"INDIA", "ITALY", "JAPAN", "USA", "ICELAND", "INDONESIA", "UK", "IRAN", "IRAQ"};

        autoCompletetv_contry = (AutoCompleteTextView) findViewById(R.id.autoCompletetv_contry);
        tv_country = (TextView) findViewById(R.id.tv_country);
        Button mBtn_select = (Button) findViewById(R.id.btn_select);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        // Set the adapter
        autoCompletetv_contry.setAdapter(adapter);
        autoCompletetv_contry.setThreshold(1);

        mBtn_select.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String country = autoCompletetv_contry.getText().toString();
                tv_country.setText("Selected Country: " + country);

            }
        });
    }
}
