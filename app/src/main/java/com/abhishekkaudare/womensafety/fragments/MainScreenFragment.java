package com.abhishekkaudare.womensafety.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.abhishekkaudare.womensafety.activities.SplashActivity.Staticated;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.abhishekkaudare.womensafety.helpers.DatabaseHandler;
import com.abhishekkaudare.womensafety.helpers.DefaultConstructorMarker;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.abhishekkaudare.womensafety.R;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class MainScreenFragment extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {
    @Nullable
    private ImageButton video;
    @Nullable
    private ImageButton message;
    @Nullable
    private ImageButton call;
    @Nullable
    private ImageButton mail;
    @Nullable
    private ImageButton police;
    private Location lastLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private boolean locationUpdateState;
    @NotNull
    private String text = "";
    @NotNull
    private String text_2 = "";
    @Nullable
    private Activity myActivity;
    private MapView mMap;
    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_CHECK_SETTINGS = 2;
    private static final int PLACE_PICKER_REQUEST = 3;
//    public static final MainScreenFragment.Companion Companion = new MainScreenFragment.Companion((DefaultConstructorMarker)null);
    private HashMap _$_findViewCache;

    public boolean onMarkerClick(@Nullable Marker p0) {
        return false;
    }

    @Nullable
    public final ImageButton getVideo() {
        return this.video;
    }

    public final void setVideo(@Nullable ImageButton ib) {
        this.video = ib;
    }

    @Nullable
    public final ImageButton getMessage() {
        return this.message;
    }

    public final void setMessage(@Nullable ImageButton ib) {
        this.message = ib;
    }

    @Nullable
    public final ImageButton getCall() {
        return this.call;
    }

    public final void setCall(@Nullable ImageButton ib) {
        this.call = ib;
    }

    @Nullable
    public final ImageButton getMail() {
        return this.mail;
    }

    public final void setMail(@Nullable ImageButton ib) {
        this.mail = ib;
    }

    @Nullable
    public final ImageButton getPolice() {
        return this.police;
    }

    public final void setPolice(@Nullable ImageButton ib) {
        this.police = ib;
    }

    @NotNull
    public final String getText_I() {
        return this.text;
    }

    public final void setText_I(@NotNull String str) {
        SplashActivity.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }

    @NotNull
    public final String getText_II() {
        return this.text_2;
    }

    public final void setText_II(@NotNull String str) {
        SplashActivity.checkParameterIsNotNull(str, "<set-?>");
        this.text_2 = str;
    }

    @Nullable
    public final Activity getMyActivity() {
        return this.myActivity;
    }

    public final void setMyActivity(@Nullable Activity act) {
        this.myActivity = act;
    }

    public void onSaveInstanceState(@NotNull Bundle outState) {
        SplashActivity.checkParameterIsNotNull(outState, "outState");
        super.onSaveInstanceState(outState);
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onSaveInstanceState(outState);
        }

    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashActivity.checkParameterIsNotNull(inflater, "inflater");
        final View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        this.video = view != null ? (ImageButton)view.findViewById(R.id.videorecording) : null;
        this.message = view != null ? (ImageButton)view.findViewById(R.id.message) : null;
        this.call = view != null ? (ImageButton)view.findViewById(R.id.call) : null;
        this.mail = view != null ? (ImageButton)view.findViewById(R.id.sendemail) : null;
        this.police = view != null ? (ImageButton)view.findViewById(R.id.discretion) : null;
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        SharedPreferences prefs = temp1.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);

        final String FirstMail = String.valueOf(prefs != null ? prefs.getString("mail1", "Failed to fetch Data") : null);

        final String SecondMail = String.valueOf(prefs != null ? prefs.getString("mail2", "Failed to fetch Data") : null);

        final String ThirdMail = String.valueOf(prefs != null ? prefs.getString("mail3", "Failed to fetch Data") : null);

        assert prefs != null;
        final String FirstNumber = String.valueOf(prefs.getLong("phone1", 0));

        final String SecondNumber = String.valueOf(prefs.getLong("phone2", 0));

        final String ThirdNumber = String.valueOf(prefs.getLong("phone3", 0));
        DatabaseHandler dbh = new DatabaseHandler(this.myActivity);
        Activity temp2 = this.myActivity;
        if (temp2 == null) {
            SplashActivity.throwJavaNpe();
        }


        ImageButton var14 = this.message;
        if (var14 == null) {
            SplashActivity.throwJavaNpe();
        }

        message.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                View temp1 = view;
                TextView temp5 = (temp1 != null) ? (TextView) temp1.findViewById(R.id.latitude_textview) : null;
                if (temp5 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                } else {
                    TextView tv1 = temp5;
                    temp1 = view;
                    temp5 = temp1.findViewById(R.id.longitude_textview);
                    if (temp5 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                    } else {
                        MainScreenFragment.this.setText_I(tv1.getText().toString());
                        MainScreenFragment.this.setText_II(temp5.getText().toString());
                        Intent smsIntent = new Intent("android.intent.action.VIEW");
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", FirstNumber + " , " + SecondNumber + " , " + ThirdNumber);
                        Log.e("Phone Numbers", FirstNumber + " , " + SecondNumber + " , " + ThirdNumber);
                        smsIntent.putExtra("sms_body", "I am unsafe. Please help me. My location is  https://maps.google.com/maps?daddr=" + MainScreenFragment.this.getText_I() + ',' + MainScreenFragment.this.getText_II());
                        MainScreenFragment.this.startActivity(smsIntent);
                    }
                }
            }
        });


        assert call != null;
        call.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                final String[] items = new String[]{"Emergency Contact 1", "Emergency Contact 2", "Emergency Contact 3", "Call Police", "Cancel"};

                Builder builder = new Builder(MainScreenFragment.this.getMyActivity());
//                boolean var4 = false;
//                boolean temp5 = false;
//                int var7 = false;
                builder.setTitle("List of Items");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent call;
                        switch (items[which]) {
                            case "Emergency Contact 1":
                                call = new Intent("android.intent.action.CALL", Uri.parse("tel:" + FirstNumber));
                                startActivity(call);
                                break;
                            case "Emergency Contact 2":
                                call = new Intent("android.intent.action.CALL", Uri.parse("tel:" + SecondNumber));
                                startActivity(call);
                                break;
                            case "Emergency Contact 3":
                                call = new Intent("android.intent.action.CALL", Uri.parse("tel:" + ThirdNumber));
                                startActivity(call);
                                break;
                            case "Call Police":
                                call = new Intent("android.intent.action.CALL", Uri.parse("tel:100"));
                                startActivity(call);
                                break;
                            case "Cancel":
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
//        var14 = this.mail;
//        if (var14 == null) {
//            SplashActivity.throwJavaNpe();
//        }

        assert mail != null;
        mail.setOnClickListener(new OnClickListener(){
            @SuppressLint("IntentReset")
            public final void onClick(View it) {
                View temp1;
                temp1 = view;
                TextView var7 = (temp1 != null) ? (TextView)temp1.findViewById(R.id.latitude_textview) : null;
                if (var7 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                } else {
                    TextView tv1 = var7;
                    temp1 = view;
                    var7 = temp1.findViewById(R.id.longitude_textview);
                    if (var7 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                    } else {
                        MainScreenFragment.this.setText_I(tv1.getText().toString());
                        MainScreenFragment.this.setText_II(var7.getText().toString());
                        Intent intentemail = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
                        Log.e("Mail Data", "" + FirstMail + "" + SecondMail + "" + ThirdMail);
                        intentemail.setType("text/plain");
                        intentemail.putExtra("android.intent.extra.EMAIL", new String[]{FirstMail, SecondMail, ThirdMail});
                        intentemail.putExtra("android.intent.extra.SUBJECT", "Please help me ");
                        intentemail.putExtra("android.intent.extra.TEXT", "I am Unsafe.Please help me. My Location is  https://maps.google.com/maps?daddr=" + MainScreenFragment.this.getText_I() + ',' + MainScreenFragment.this.getText_II());
                        MainScreenFragment.this.startActivity(intentemail);

                        try {
                            MainScreenFragment.this.startActivity(Intent.createChooser(intentemail, "Choose an email client from..."));
                        } catch (ActivityNotFoundException var6) {
                            Toast.makeText(MainScreenFragment.this.getMyActivity(), "No email client installed.", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });
//        var14 = this.police;
//        if (var14 == null) {
//            SplashActivity.throwJavaNpe();
//        }

        assert police != null;
        police.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                View temp1 = view;
                TextView var6 = temp1 != null ? (TextView)temp1.findViewById(R.id.latitude_textview) : null;
                if (var6 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                } else {
                    TextView tv1 = var6;
                    temp1 = view;
                    var6 = temp1.findViewById(R.id.longitude_textview);
                    if (var6 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                    } else {
                        MainScreenFragment.this.setText_I(tv1.getText().toString());
                        MainScreenFragment.this.setText_II(var6.getText().toString());
                        Uri gmmIntentUri = Uri.parse("geo:" + MainScreenFragment.this.getText_I() + ',' + MainScreenFragment.this.getText_II() + "?q=police&");
                        Intent mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        Log.e("Location Data", "" + MainScreenFragment.this.getText_I() + "" + MainScreenFragment.this.getText_II());
                        MainScreenFragment.this.startActivity(mapIntent);
                    }
                }
            }
        });
        MapView temp3 = view != null ? (MapView)view.findViewById(R.id.map) : null;
        if (temp3 == null) {
            throw new ClassCastException("null cannot be cast to non-null type com.google.android.gms.maps.MapView");
        } else {
            this.mMap = temp3;
            MapView var17 = this.mMap;
            var17.onCreate(savedInstanceState);

            var17 = this.mMap;


            if (var17 != null) {
                var17.getMapAsync(this);
            }

            Activity var15 = this.myActivity;
            if (var15 == null) {
                SplashActivity.throwJavaNpe();
            }

            FusedLocationProviderClient var16 = LocationServices.getFusedLocationProviderClient(var15);
            SplashActivity.checkExpressionValueIsNotNull(var16, "LocationServices.getFuse…Client(this.myActivity!!)");
            this.fusedLocationClient = var16;
            this.locationCallback = new LocationCallback() {
                public void onLocationResult(@NotNull LocationResult p0) {
                    SplashActivity.checkParameterIsNotNull(p0, "p0");
                    super.onLocationResult(p0);
                    MainScreenFragment temp1 = MainScreenFragment.this;
                    Location temp3 = p0.getLastLocation();
                    SplashActivity.checkExpressionValueIsNotNull(temp3, "p0.lastLocation");
                    temp1.lastLocation = temp3;
                    MainScreenFragment.this.placeMarkerOnMap(new LatLng(MainScreenFragment.this.lastLocation.getLatitude(), MainScreenFragment.this.lastLocation.getLongitude()));
                }
            };
            this.createLocationRequest();
            return view;
        }
    }

    public void onResume() {
        super.onResume();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onResume();
        }

        if (!this.locationUpdateState) {
            this.startLocationUpdates();
        }

    }

    public void onPause() {
        super.onPause();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onPause();
        }

        FusedLocationProviderClient var1 = this.fusedLocationClient;


        LocationCallback temp3 = this.locationCallback;

        var1.removeLocationUpdates(temp3);
    }

    public void onStart() {
        super.onStart();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onStart();
        }

    }

    public void onStop() {
        super.onStop();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onStop();
        }

    }

    public void onDestroy() {
        super.onDestroy();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onDestroy();
        }

    }

    public void onLowMemory() {
        super.onLowMemory();
        MapView temp1 = this.mMap;
        if (temp1 != null) {
            temp1.onLowMemory();
        }

    }

    public void onAttach(@NotNull Context context) {
        SplashActivity.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        this.myActivity = (Activity)context;
    }

    @Override
    public void onAttach(@NotNull Activity activity) {
        SplashActivity.checkParameterIsNotNull(activity, "activity");
        super.onAttach(activity);
        this.myActivity = activity;
    }

    public void onMapReady(@NotNull GoogleMap googleMap) {
        SplashActivity.checkParameterIsNotNull(googleMap, "googleMap");
        this.map = googleMap;
        this.setUpMap();
        GoogleMap temp1 = this.map;

        temp1.setMyLocationEnabled(true);
        FusedLocationProviderClient var2 = this.fusedLocationClient;


        Task var3 = var2.getLastLocation();
        Activity temp3 = this.myActivity;
        if (temp3 == null) {
            SplashActivity.throwJavaNpe();
        }

        var3.addOnSuccessListener(temp3, new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((Location)var1);
            }

            public final void onSuccess(Location location) {
                if (location != null) {
                    MainScreenFragment.this.lastLocation = location;
                    LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MainScreenFragment.this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17.0F));
                }

            }
        });
    }

    private void placeMarkerOnMap(LatLng location) {
        MarkerOptions markerOptions = (new MarkerOptions()).position(location);
        String titleStr = this.getAddress(location);
        markerOptions.title(titleStr);
        View temp1 = this.getView();
        TextView var6 = (temp1 != null) ? (TextView) temp1.findViewById(R.id.latitude_textview) : null;
        if (var6 == null) {
            throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
        } else {
            TextView mLatitudeTextView = var6;
            temp1 = this.getView();
            var6 = temp1 != null ? (TextView)temp1.findViewById(R.id.longitude_textview) : null;
            if (var6 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
            } else {
                TextView mLongitudeTextView = var6;

                mLatitudeTextView.setText(String.valueOf(location.latitude));

                mLongitudeTextView.setText(String.valueOf(location.longitude));
                GoogleMap var7 = this.map;

                var7.addMarker(markerOptions);
            }
        }
    }

    private void setUpMap() {
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        if (ActivityCompat.checkSelfPermission(temp1, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            temp1 = this.myActivity;
            if (temp1 == null) {
                SplashActivity.throwJavaNpe();
            }

            ActivityCompat.requestPermissions(temp1, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else {
            GoogleMap var1 = this.map;
//            if (var1 == null) {
//                SplashActivity.throwUninitializedPropertyAccessException("map");
//            }

            var1.setMyLocationEnabled(true);
            FusedLocationProviderClient var2 = this.fusedLocationClient;
//            if (var2 == null) {
//                SplashActivity.throwUninitializedPropertyAccessException("fusedLocationClient");
//            }

            Task var3 = var2.getLastLocation();
            Activity temp3 = this.myActivity;
            if (temp3 == null) {
                SplashActivity.throwJavaNpe();
            }

            var3.addOnSuccessListener(temp3, new OnSuccessListener() {
                // $FF: synthetic method
                // $FF: bridge method
                public void onSuccess(Object var1) {
                    this.onSuccess((Location)var1);
                }

                public final void onSuccess(Location location) {
                    if (location != null) {
                        MainScreenFragment.this.lastLocation = location;
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MainScreenFragment.this.placeMarkerOnMap(currentLatLng);
                        MainScreenFragment.this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17.0F));
                    }

                }
            });
        }
    }

    private final String getAddress(LatLng latLng) {

        Geocoder geocoder = new Geocoder(this.myActivity);
        List addresses = null;
        Address address = null;
        StringBuilder addressText = new StringBuilder();

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                address = (Address)addresses.get(0);
                int i = 0;

                for(int var7 = address.getMaxAddressLineIndex(); i < var7; ++i) {
                    addressText.append(i == 0 ? address.getAddressLine(i) : "\n" + address.getAddressLine(i));
                }
            }
        } catch (IOException var8) {
            Log.e("MapsActivity", Objects.requireNonNull(var8.getLocalizedMessage()));
        }

        return addressText.toString();
    }

    private void startLocationUpdates() {
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        if (ActivityCompat.checkSelfPermission(temp1, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            temp1 = this.myActivity;
            if (temp1 == null) {
                SplashActivity.throwJavaNpe();
            }

            ActivityCompat.requestPermissions(temp1, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } else {
            FusedLocationProviderClient var1 = this.fusedLocationClient;

            LocationRequest temp3 = this.locationRequest;

            LocationCallback temp2 = this.locationCallback;

            var1.requestLocationUpdates(temp3, temp2, null);
        }
    }

    private void createLocationRequest() {
        this.locationRequest = new LocationRequest();
        LocationRequest temp1 = this.locationRequest;

        temp1.setInterval(5000L);
        temp1 = this.locationRequest;

        temp1.setFastestInterval(2000L);
        temp1 = this.locationRequest;

        temp1.setPriority(100);
        com.google.android.gms.location.LocationSettingsRequest.Builder var4 = new com.google.android.gms.location.LocationSettingsRequest.Builder();
        LocationRequest temp3 = this.locationRequest;

        com.google.android.gms.location.LocationSettingsRequest.Builder builder = var4.addLocationRequest(temp3);
        Activity temp5 = this.myActivity;
        if (temp5 == null) {
            SplashActivity.throwJavaNpe();
        }

        SettingsClient client = LocationServices.getSettingsClient(temp5);
        Task task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(new OnSuccessListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onSuccess(Object var1) {
                this.onSuccess((LocationSettingsResponse)var1);
            }

            public final void onSuccess(LocationSettingsResponse it) {
                MainScreenFragment.this.locationUpdateState = true;
                MainScreenFragment.this.startLocationUpdates();
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            public final void onFailure(@NotNull Exception e) {
                SplashActivity.checkParameterIsNotNull(e, "e");
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException temp1 = (ResolvableApiException)e;
                        Activity temp3 = MainScreenFragment.this.getMyActivity();
                        if (temp3 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        temp1.startResolutionForResult(temp3, 2);
                    } catch (SendIntentException var3) {
                    }
                }

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == -1) {
            this.locationUpdateState = true;
            this.startLocationUpdates();
        }

    }



}
