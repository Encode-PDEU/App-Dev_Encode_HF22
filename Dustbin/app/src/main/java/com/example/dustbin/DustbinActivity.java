package com.example.dustbin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DustbinActivity extends AppCompatActivity {
    Spinner spinner;
    Button find;
    SupportMapFragment supportMapFragment;
    GoogleMap Map;
    FusedLocationProviderClient fusedLocationProviderClient;
    Double currentLat = (double) 0;
    Double currentLong = (double) 0;

    ArrayList<LatLng> arrayList = new ArrayList<>();
    LatLng ad1 = new LatLng(22.9952031,72.6035613);
    LatLng ad2 = new LatLng(23.039579, 72.630937);
    LatLng ad3 = new LatLng(23.049906, 72.670508);
    LatLng ad4 = new LatLng(23.046060, 72.530132);
    LatLng vd1 = new LatLng(22.294151, 73.194624);
    LatLng vd2 = new LatLng(22.283371, 73.232127);
    LatLng vd3 = new LatLng(22.309233, 73.187942);
    LatLng vd4 = new LatLng(22.340704, 73.201061);
    LatLng gd1 = new LatLng(23.196180, 72.642463);
    LatLng gd2 = new LatLng(23.158980, 72.663693);
    LatLng gd3 = new LatLng(23.258233, 72.652633);
    LatLng gd4 = new LatLng(23.188537, 72.629945);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dustbin);

        spinner = findViewById(R.id.sp_type);
        find = findViewById(R.id.find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        arrayList.add(ad1);arrayList.add(ad2);arrayList.add(ad3);arrayList.add(ad4);
        arrayList.add(vd1);arrayList.add(vd2);arrayList.add(vd3);arrayList.add(vd4);
        arrayList.add(gd1);arrayList.add(gd2);arrayList.add(gd3);arrayList.add(gd4);

        String[] place= {"Ahmedabad","Vadodara","Gandhinagar"};
        String[] spPlace = {"Ahmedabad","Vadodara","Gandhinagar"};

        spinner.setAdapter(new ArrayAdapter<>(DustbinActivity.this, android.R.layout.simple_spinner_dropdown_item, spPlace));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(DustbinActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(DustbinActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = spinner.getSelectedItemPosition();
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                        "?location=" + currentLat + "," + currentLong + "&radius=5000" + "&types=" + place[i] +
                        "&sensor=true" + "&key=" + getResources().getString(R.string.google_map_key);
                new PlaceTask().execute(url);
                DisplayTrack(place[i]);
            }
        });

    }
//    public String getAddress(LatLng latLng) throws IOException {
//        double latitude=latLng.latitude;
//        double longitude=latLng.longitude;
//        Geocoder myLocation = new Geocoder(DustbinActivity.this, Locale.getDefault());
//        String addressStr = "";
//        try {
//            List<Address> myList = myLocation.getFromLocation(latitude, longitude, 1);
//            Address address = myList.get(0);
//            addressStr += address.getAddressLine(0);
////            addressStr += address.getAddressLine(1) + ", ";
////            addressStr += address.getAddressLine(2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return addressStr;
//    }
    private void DisplayTrack(String ar) {
        try {
            Uri uri=null;
            if(ar.equals("Ahmedabad")) {
//                String s2=getAddress(ad1);
//                String s3=getAddress(ad2);
//                String s4=getAddress(ad3);
//                String s1=getAddress(ad4);
                uri = Uri.parse("https://www.google.co.in/maps/dir/Himalaya-Mall/maninagar/bapunagar/nikol");
            }else if(ar.equals("Vadodara")) {
                uri = Uri.parse("https://www.google.co.in/maps/dir/KubereshwarMarg/LukshmiVilasPalace/KalaGhodaCircle/SamaLake");
            }else{
                uri = Uri.parse("https://www.google.co.in/maps/dir/PDEU/DAIICT/Ch0/Samarpancircle");
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/detail?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private BitmapDescriptor BitmapFromVector(int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(DustbinActivity.this, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(@NonNull Location location) {
                if (location != null) {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            Map = googleMap;
                            for (int i = 0; i < arrayList.size(); i++) {
                                if(i%2==0) {
                                    Map.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Dry Dustbin").snippet("SUKA").icon(BitmapFromVector(R.drawable.ic_artboard_1_copy_3)));
                                }else{
                                    Map.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Wet Dustbin").snippet("GILA").icon(BitmapFromVector(R.drawable.ic_artboard_1_copy)));
                                }
                                Map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                                Map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                            }

//                            Map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                                @Override
//                                public boolean onMarkerClick(Marker marker) {
//                                    Intent i = new Intent(DustbinActivity.this, temp.class);
//                                    startActivity(i);
//                                    return false;
//                                }
//                            });
                        }
                    });
                }
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadurl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }
    }

    private String downloadurl(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        reader.close();
        return data;
    }
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser jsonParser = new JsonParser();
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            for (int i = 0; i < hashMaps.size(); i++) {
                HashMap<String, String> hashMapList = hashMaps.get(i);
                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                Map.addMarker(options);
            }
        }
    }

}