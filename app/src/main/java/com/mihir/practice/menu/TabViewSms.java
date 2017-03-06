package com.mihir.practice.menu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.mihir.practice.R;
import com.mihir.practice.TabSmsInbox;
import com.mihir.practice.TabSmsSend;

@SuppressWarnings("deprecation")
public class TabViewSms extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        TabHost tabHost = getTabHost();

        // Tab for Inbox
        TabSpec inboxspec = tabHost.newTabSpec("Inbox");
        // setting Title and Icon for the Tab
        inboxspec.setIndicator("Inbox", getResources().getDrawable(android.R.drawable.ic_menu_compass));
        Intent inboxIntent = new Intent(this, TabSmsInbox.class);
        inboxspec.setContent(inboxIntent);

        // Tab for Send
        TabSpec sendspec = tabHost.newTabSpec("Send");
        sendspec.setIndicator("Send", getResources().getDrawable(android.R.drawable.ic_menu_send));
        Intent sendIntent = new Intent(this, TabSmsSend.class);
        sendspec.setContent(sendIntent);


        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxspec); // Adding inbox tab
        tabHost.addTab(sendspec); // Adding send tab
    }
}
