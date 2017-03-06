package com.mihir.practice;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TabPhotos extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_photos);

        final ListView mLv_account = (ListView) findViewById(R.id.lv_account);
        final ArrayList<String> mArrAccounts = new ArrayList<>();

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(TabPhotos.this).getAccounts();

        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                mArrAccounts.add(account.name);
            }
        }

        mLv_account.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrAccounts));
    }
}	