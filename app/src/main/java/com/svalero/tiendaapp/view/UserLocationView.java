package com.svalero.tiendaapp.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.widget.Toolbar;

import com.mapbox.geojson.Point;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.tiendaapp.R;

public class UserLocationView extends MainActivity {

    private String userName;
    private double latitude;
    private double longitude;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent().hasExtra("USER_NAME ")) {
            userName = String.valueOf(getIntent().getIntExtra("USER_NAME", -1));
        }
        if (getIntent().hasExtra("USER_LATITUDE")) {
            latitude = Double.parseDouble(getIntent().getStringExtra("USER_LATITUDE"));
        }

        if (getIntent().hasExtra("USER_LONGITUDE")) {
            longitude = Double.parseDouble(getIntent().getStringExtra("USER_LONGITUDE"));
        }

        mapView = findViewById(R.id.mapView);

        initializeMapView();
        initializePointAnnotationManager();
        addMarker(latitude, longitude);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
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

        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_marker));

        pointAnnotationManager.create(pointAnnotationOptions);

    }


}