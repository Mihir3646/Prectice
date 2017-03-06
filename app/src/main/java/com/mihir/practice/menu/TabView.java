package com.mihir.practice.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.mihir.practice.R;
import com.mihir.practice.TabPhotos;
import com.mihir.practice.TabSongs;
import com.mihir.practice.TabVideos;

@SuppressWarnings("deprecation")
public class TabView extends android.app.TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        TabHost tabHost = getTabHost();

        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Photos");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Photos", getResources().getDrawable(R.drawable.photos_dark));
        Intent photosIntent = new Intent(this, TabPhotos.class);
        photospec.setContent(photosIntent);

        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Songs");
        songspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, TabSongs.class);
        songspec.setContent(songsIntent);

        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Videos");
        videospec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, TabVideos.class);
        videospec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}
