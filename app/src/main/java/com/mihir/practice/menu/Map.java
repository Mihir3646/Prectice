package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mihir.practice.R;

public class Map extends Activity {
    // Google Map
    private GoogleMap googleMap;
    private TextView tv_mapviews = null;
    // latitude and longitude
    private static LatLng hitech_latlong = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        hitech_latlong = new LatLng(23.04461, 72.52788);
        tv_mapviews = (TextView) findViewById(R.id.tv_mapviews);
        //		registerForContextMenu(tv_mapviews);

        tv_mapviews.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //				openContextMenu(tv_mapviews);
                openOptionsMenu();
            }
        });

        try {
            // Loading map
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * function to load map. If map is not created it will create it for you
     */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            } else {
                //				googleMap.getUiSettings().setZoomControlsEnabled(false); // true to enable
                //				googleMap.getUiSettings().setZoomGesturesEnabled(false);
                //				googleMap.getUiSettings().setCompassEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setRotateGesturesEnabled(true);

                // create marker
                MarkerOptions marker = new MarkerOptions()
                        .position(hitech_latlong)
                        .title("Hi-Tech House");
                //					.snippet("Hi-Tech House, Behind V-Murti Complex, Near Gurukul Tower Sushil Nagar Society, Gurukul, Ahmedabad, Gujarat 380052 <br />079 4000 3000 <br /><a href=" + "hitechos.com" + ">hitechos.com</a>‎");
                // adding marker
                googleMap.addMarker(marker);

                // Setting a custom info window adapter for the google map
                googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
                    // Use default InfoWindow frame
                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    // Defines the contents of the InfoWindow
                    @Override
                    public View getInfoContents(Marker arg0) {
                        // Getting view from the layout file info_window_layout
                        View v = getLayoutInflater().inflate(R.layout.map_custom_disc, null);

                        TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
                        TextView tv_address = (TextView) v.findViewById(R.id.tv_address);
                        TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
                        TextView tv_website = (TextView) v.findViewById(R.id.tv_website);
                        //			            ImageView iv_icon = (ImageView) v.findViewById(R.id.iv_icon);

                        tv_title.setText("Hi-Tech House");
                        tv_address.setText("Behind V-Murti Complex, Near Gurukul Tower Sushil Nagar Society, Gurukul, Ahmedabad, Gujarat 380052");
                        tv_phone.setText("079 4000 3000");
                        tv_website.setText("hitechos.com");
                        Linkify.addLinks(tv_website, Linkify.WEB_URLS);

                        // Returning the view containing InfoWindow contents
                        return v;
                    }
                });

                //target position with zoom and focus
                CameraPosition cameraPosition = new CameraPosition.Builder().target(hitech_latlong).zoom(7).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal_view:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                tv_mapviews.setText(getResources().getString(R.string.normal_view));
                break;
            case R.id.satellite_view:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                tv_mapviews.setText(getResources().getString(R.string.satellite_view));
                break;
            case R.id.hybrid_view:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                tv_mapviews.setText(getResources().getString(R.string.hybrid_view));
                break;
            case R.id.terrain_view:
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                tv_mapviews.setText(getResources().getString(R.string.terrain_view));
                break;
        }
        return true;
    }
}