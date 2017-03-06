package com.mihir.practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CallReciever extends BroadcastReceiver {

    private String str_call_detail;

    @Override
    public void onReceive(final Context context, Intent intent) {

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING))
            Toast.makeText(context, "Ringing...", Toast.LENGTH_SHORT).show();
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            Toast.makeText(context, "OFF Hook...", Toast.LENGTH_SHORT).show();

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Toast.makeText(context, "Ideal.", Toast.LENGTH_SHORT).show();

            Thread timer = new Thread() {
                public void run() {
                    try {
                        //sleep thread for time duration
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        getLastCallLogEntry(context);
                    }
                }
            };
            timer.start();
        }
    }

    private void getLastCallLogEntry(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("Call Log :");

        Cursor managedCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number;
        int name;
        int type;
        int date;
        int duration;
        if (managedCursor != null) {
            number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
            name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
            date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
            duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

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
                    case 5:
                        dir = "INCOMING";
                        break;

                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }
                sb.append("<br><b>Name: </b>").append(contactName).append("<br><b>Phone Number: </b>").append(phNumber).append("<br><b>Call Type: </b>").append(dir).append("<br><b>Call Date: </b>").append(callDayTime).append("<br><b>Call duration in sec: </b>").append(callDuration).append("<br>----------------------------------<br><br>");
                Log.d("Log", "" + Html.fromHtml(sb.toString()));

                break;
            }
            managedCursor.close();
        }

/*		String[] projection = new String[] { BaseColumns._ID,
                CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER,
				CallLog.Calls.TYPE, CallLog.Calls.DURATION, CallLog.Calls.DATE };
		
		ContentResolver resolver = context.getContentResolver();
		Cursor cur = resolver.query(CallLog.Calls.CONTENT_URI, projection, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
		
		if(cur.moveToFirst())
		{
			int name = cur.getColumnIndex(CallLog.Calls.CACHED_NAME);
			int numberColumn = cur.getColumnIndex(CallLog.Calls.NUMBER);
			int typeColumn = cur.getColumnIndex(CallLog.Calls.TYPE);
			int durationcolumn = cur.getColumnIndex(CallLog.Calls.DURATION);
			int date = cur.getColumnIndex(CallLog.Calls.DATE);
			
			String contactName = cur.getString(name);
			String number = cur.getString(numberColumn);
			String type = cur.getString(typeColumn);
			String duration = cur.getString(durationcolumn);
			
			if(contactName == null)
				contactName = "UnKnown";

			switch (Integer.parseInt(type)) 
			{
			case 1: case 5:
				type = "INCOMING";
				break;
				
			case 2:
				type = "OUTGOING";
				break;

			case 3:
				type = "MISSED";
				break;
			}
			
			Spanned logs = Html.fromHtml("<br><b>Name:</b> " + contactName
					+ "<br><b>Phone Number:</b> " + number
					+ " <br><b>Call Type:</b> " + type
					+ " <br><b>Call duration in sec</b> : " + duration
					+ " <br><b>Call Date:</b> " + date
					+ "<br>----------------------------------<br><br>");
			Log.d("Log", ""+ logs.toString());
		}
		*/
    }

 /*   @Override
    public void onReceive(Context context, Intent intent) 
    {
    	Bundle extras = intent.getExtras();
	    if (extras != null) 
	    {
	        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) 
	        {
	            // Phone number 
	            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
	            // Ringing state
	            // This code will execute when the phone has an incoming call
	        } 
	        else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)
	                || intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) 
	        {
	            // This code will execute when the call is answered or disconnected
	    		StringBuffer sb = new StringBuffer();
	    		Cursor managedCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
	    		int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
	    		int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
	    		int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
	    		int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
	    		int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
	    		sb.append("Call Log :");
	    		
	    		managedCursor.moveToFirst();
				String contactName = managedCursor.getString(name);
				if(contactName == null)
					contactName = "UnKnown";
				
				String phNumber = managedCursor.getString(number);
				String callType = managedCursor.getString(type);
				String callDate = managedCursor.getString(date);
				Date callDayTime = new Date(Long.valueOf(callDate));
				String callDuration = managedCursor.getString(duration);
				String dir = null;
			
				int dircode = Integer.parseInt(callType);
				switch (dircode) 
				{
				case CallLog.Calls.OUTGOING_TYPE:
					dir = "OUTGOING";
					break;
	
				case CallLog.Calls.INCOMING_TYPE:
					dir = "INCOMING";
					break;
	
				case CallLog.Calls.MISSED_TYPE:
					dir = "MISSED";
					break;
				}
				sb.append("<br><b>Name:</b> " + contactName + "<br><b>Phone Number:</b> " + phNumber + " <br><b>Call Type:</b> "
						+ dir + " <br><b>Call Date:</b> " + callDayTime
						+ " <br><b>Call duration in sec</b> : " + callDuration + "<br>----------------------------------<br><br>");
	    		// managedCursor.close();
	    		String logs = sb.toString();
	    		Log.d("Call Detail", ""+Html.fromHtml(logs));
	        }
	    }
    }*/

    public void WriteToFile() {
        try {
            File mFileold = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MsgLogOld.txt");
            File mFilenew = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MsgLogNew.txt");
            FileOutputStream overWrite = new FileOutputStream(mFilenew);
            overWrite.write(Html.fromHtml(str_call_detail).toString().getBytes());
            overWrite.flush();
            overWrite.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

/*	private void appendData(File fileold, File filenew) 
	{
		StringBuilder text = new StringBuilder();
		try
		{
		    BufferedReader br = new BufferedReader(new FileReader(fileold));
		    String line;

		    while ((line = br.readLine()) != null) 
		    {
		        text.append(line);
		        text.append('\n');
		    }
		    
		    FileOutputStream mOutputStream = new FileOutputStream(filenew,true);
		    mOutputStream.write(text.toString().getBytes());
		    mOutputStream.flush();
		    mOutputStream.close();
		}
		catch (IOException e)
		{
		    //You'll need to add proper error handling here
		}
	}*/
}