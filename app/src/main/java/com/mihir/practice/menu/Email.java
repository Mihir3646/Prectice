package com.mihir.practice.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mihir.practice.R;
import com.mihir.practice.SendMail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends Activity implements OnClickListener {
    private EditText et_recipient = null, et_subject = null, et_message = null;
    private String[] emailCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

        et_recipient = (EditText) findViewById(R.id.et_recipient);
        et_subject = (EditText) findViewById(R.id.et_subject);
        et_message = (EditText) findViewById(R.id.et_message);
        Button mBtn_send = (Button) findViewById(R.id.btn_send);

        mBtn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String mRecipient = et_recipient.getText().toString().trim();
        String mSubject = et_subject.getText().toString().trim();
        String mMassage = et_message.getText().toString().trim();

        if (mSubject.equals(""))
            mSubject = "no subject";

        String err_msg = "Invalid email";
        String concateStr = "";

        for (int i = 0; i <= mRecipient.length(); i++) {
            emailCheck = mRecipient.split("\\s+");
        }

        for (String anEmailCheck : emailCheck) {
            if (!emailValidator(anEmailCheck)) {
                et_recipient.requestFocus();
                err_msg = err_msg.concat("\n" + anEmailCheck);
            } else {
                concateStr = concateStr.concat(anEmailCheck + ",");
            }
            Log.d("Splited Email", anEmailCheck);
        }
        if (concateStr.endsWith(","))
            concateStr = concateStr.substring(0, concateStr.length() - 1);

        Log.d("Concated Email", concateStr);

        if (err_msg.equals("Invalid email")) {
            try {
                Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);
                sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{concateStr});
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                sendEmailIntent.putExtra(Intent.EXTRA_TEXT, mMassage);
                sendEmailIntent.setType("plain/text");
                //				sendEmailIntent.setType("message/rfc822");
                startActivity(sendEmailIntent);

                //strings for mail
                String from = "android123testing@gmail.com";
                String to = mRecipient;
                String subject = "Send Mail Test";
                String body = "New Mail recived";
                String password = "hitech@#12345";

                /**object of Send Mail */
                SendMail SendMail = new SendMail(from, to, subject, body, password);

                //Send Mail
                SendMail.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            et_recipient.requestFocus();
            et_recipient.invalidate();
            et_recipient.setError(err_msg);
            Log.d("Error msg", err_msg);
        }
    }

    private boolean emailValidator(String str_email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(str_email);
        return matcher.matches();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.email_activity_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Menu Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
