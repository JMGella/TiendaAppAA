package com.svalero.tiendaapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.widget.Toolbar;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.svalero.tiendaapp.R;

public class UserLocationView extends MainActivity {

    private String userName;
    private int latitude;
    private int longitude;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_location_view);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.location));
        setActivityTitle(getString(R.string.location));


        if (getIntent().hasExtra("USER_NAME ")) {
            userName = String.valueOf(getIntent().getIntExtra("USER_NAME", -1));
        }
        if (getIntent().hasExtra("USER_LATITUDE")) {
            latitude = getIntent().getIntExtra("USER_LATITUDE", -1);
        }

        if (getIntent().hasExtra("USER_LONGITUDE")) {
            longitude = getIntent().getIntExtra("USER_LONGITUDE", -1);
        }

        mapView = findViewById(R.id.mapView);

        initializeMapView();
        initializePointAnnotationManager();
        addMarker(latitude, longitude);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_users, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_user) {
            Intent intent = new Intent(this, AddUserView.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeMapView() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);


    }

    private void addMarker(double latitude, double longitude) {
//        Bitmap iconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_marker);
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_marker));

        pointAnnotationManager.create(pointAnnotationOptions);

    }


}