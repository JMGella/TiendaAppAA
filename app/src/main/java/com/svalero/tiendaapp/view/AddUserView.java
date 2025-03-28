package com.svalero.tiendaapp.view;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.textfield.TextInputEditText;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.tiendaapp.R;
import com.svalero.tiendaapp.contract.AddUserContract;
import com.svalero.tiendaapp.domain.User;
import com.svalero.tiendaapp.presenter.AddUserPresenter;
import com.svalero.tiendaapp.util.DateUtil;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;


public class AddUserView extends MainActivity implements AddUserContract.View,  OnMapClickListener {

    private AddUserContract.Presenter presenter;
    private TextInputEditText etName, etEmail, etPhone, etBirthDate, etAddress;
    private EditText etLatitude, etLongitude;
    private SwitchCompat swActive;
    private Button btnSave, btnSelectLocation, btnGetCurrentLocation;
    private MapView mapView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private PointAnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;
    private DateUtil dateUtil = new DateUtil();
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_user);

        presenter = new AddUserPresenter(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etBirthDate = findViewById(R.id.etBirthDate);
        etBirthDate.setFocusable(false);
        etBirthDate.setKeyListener(null);
        etBirthDate.setOnClickListener(v -> showDatePickerDialog());
        etAddress = findViewById(R.id.etAddress);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        swActive = findViewById(R.id.swActive);
        btnSave = findViewById(R.id.btnSave);
//        btnSelectLocation = findViewById(R.id.btnSelectLocation);
//        btnSelectLocation.setOnClickListener(v -> {
//            etLatitude.setText(latitude);
//            etLongitude.setText(longitude);
//        });
        mapView = findViewById(R.id.mapView);


        btnSave.setOnClickListener(v -> {saveUser();});



        initializeMapView();
        initializePointAnnotationManager();
        initializeGesturesPlugin();

    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String formattedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    etBirthDate.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }


    public void saveUser()  {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String birthdate = String.valueOf(LocalDate.parse(etBirthDate.getText().toString()));
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String  creationDate = LocalDate.now().toString();
        String latitude = String.valueOf(Double.parseDouble(etLatitude.getText().toString()));
        String longitude = String.valueOf((Double.parseDouble(etLongitude.getText().toString())));
        boolean active = swActive.isChecked();
        User user = new User(name, email, birthdate, active, address, phone, creationDate, latitude, longitude);
        presenter.saveUser(user);

    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateLocationFields(String latitude, String longitude) {
        etLatitude.setText(latitude);
        etLongitude.setText(longitude);

    }



    private void initializeMapView() {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
    }

    private void initializePointAnnotationManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);


    }

    private void initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);

    }
    private void addMarker(double latitude, double longitude) {
//        Bitmap iconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_marker);
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_marker));

        pointAnnotationManager.create(pointAnnotationOptions);

    }




    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude());
        longitude = String.valueOf(point.longitude());
        latitude = String.valueOf(point.latitude());
        updateLocationFields(latitude, longitude);
        return true;
    }


}