package com.mihir.practice;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TabSmsInbox extends Activity {
    private ListView lv_friends_list = null;
    private ArrayList<String> arrListContactNo = null, arrListBody = null, arrListTime = null;

    private FriendListAdapter mFriendListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_inbox);

        lv_friends_list = (ListView) findViewById(R.id.lv_friends_list);

        arrListContactNo = new ArrayList<String>();
        arrListBody = new ArrayList<String>();
        arrListTime = new ArrayList<String>();

        fetchMsgs();

        //adeptor declaration
        mFriendListAdapter = new FriendListAdapter();
        lv_friends_list.setAdapter(mFriendListAdapter);
    }

    /**
     * Get contact list from phonebook
     */
    public void fetchMsgs() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String senderNumber = cursor.getString(cursor.getColumnIndex("address"));
                String smsBody = cursor.getString(cursor.getColumnIndex("body"));
                String dateTime = cursor.getString(cursor.getColumnIndex("date"));

                arrListContactNo.add(senderNumber);
                arrListBody.add(smsBody);
                arrListTime.add(dateTime);
            }
        }
    }

    /**
     * @author Hitech
     * @description Adapte the listview data
     */
    public class FriendListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater = null;

        public FriendListAdapter() {
            mLayoutInflater = LayoutInflater.from(TabSmsInbox.this);
        }

        @Override
        public int getCount() {
            return arrListContactNo.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.row_inbox, null);
            }

            TextView tv_contact_name = (TextView) convertView.findViewById(R.id.tv_contact_name);
            TextView tv_body = (TextView) convertView.findViewById(R.id.tv_body);
            //    		TextView tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            //    		ImageView iv_contact = (ImageView)convertView.findViewById(R.id.iv_contact);

            //    		if(arrPhotoList.get(position) != null)
            //    		{
            //    			iv_contact.setImageURI(Uri.parse(arrPhotoList.get(position)));
            //    		}
            //    		else
            //    		{
            //    			iv_contact.setImageResource(R.drawable.ic_contact_picture);
            //    		}

            tv_contact_name.setText(arrListContactNo.get(position));
            tv_body.setText(arrListBody.get(position));
            //			tv_time.setText(arrListTime.get(position));

            return convertView;
        }
    }
}