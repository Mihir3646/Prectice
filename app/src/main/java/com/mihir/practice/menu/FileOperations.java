package com.mihir.practice.menu;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mihir.practice.Common;
import com.mihir.practice.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class FileOperations extends Activity implements OnClickListener {
    private TextView tv_password = null, tv_flag = null;
    private File file_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_operations);

        tv_password = (TextView) findViewById(R.id.tv_password);
        tv_flag = (TextView) findViewById(R.id.tv_flag);
        Button mBtn_save = (Button) findViewById(R.id.btn_save);
        Button mBtn_retrive = (Button) findViewById(R.id.btn_retrive);
        Button mBtn_call_log = (Button) findViewById(R.id.btn_call_log);
        Button mBtn_sms_log = (Button) findViewById(R.id.btn_sms_log);

        String mDir_path = Common.createDirIfNotExists(FileOperations.this, ".MyChild");
        file_password = new File(mDir_path, "password.txt");

        mBtn_save.setOnClickListener(this);
        mBtn_retrive.setOnClickListener(this);
        mBtn_call_log.setOnClickListener(this);
        mBtn_sms_log.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                writeToFile();
                break;
            case R.id.btn_retrive:
                retriveFromFile();
                break;
            case R.id.btn_call_log:
                getAllCallLogs();
                break;
            case R.id.btn_sms_log:
                break;
        }
    }

    private void writeToFile() {
        FileOutputStream fop = null;
        try {
            Log.d("File path", "FIle path = " + file_password.getAbsolutePath());

            // if file doesn't exists, then create it
            if (!file_password.exists()) {
                file_password.createNewFile();

                //code for generate random number password
                String characters = "0123456789abcdefghijklmnoporstuvwxyz";
                final StringBuilder sb = new StringBuilder(8);

                for (int i = 0; i < sb.capacity(); i++) {
                    int ndx = (int) (Math.random() * characters.length());
                    sb.append(characters.charAt(ndx));
                }
                Log.d("pass", sb.toString());

                fop = new FileOutputStream(file_password, true);
                fop.write(sb.toString().getBytes());
                fop.flush();
                fop.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void retriveFromFile() {
        BufferedReader br = null;
        StringBuilder text = new StringBuilder();

        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader(file_password.getAbsolutePath()));
            while ((sCurrentLine = br.readLine()) != null) {
                text.append(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        tv_flag.setText(text.toString());
    }

    private void getAllCallLogs() {
        StringBuilder sb = new StringBuilder();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");

        while (managedCursor.moveToNext()) {
            String contactName = managedCursor.getString(name);
            if (contactName == null)
                contactName = "UnKnown";

            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;

            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("<br><b>Name:</b> ").append(contactName).append("<br><b>Phone Number:</b> ").append(phNumber).append(" <br><b>Call Type:</b> ").append(dir).append(" <br><b>Call Date:</b> ").append(callDayTime).append(" <br><b>Call duration in sec</b> : ").append(callDuration).append("<br>----------------------------------<br><br>");
        }
        // managedCursor.close();
        String logs = sb.toString();
        tv_password.setText(Html.fromHtml(logs));
    }
}
