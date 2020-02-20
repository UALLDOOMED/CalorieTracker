package com.example.assignmentapp;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap map;
    private Button showNearby;
    ArrayList<String> latList = new ArrayList<>();
    ArrayList<String> lngList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
                ShowNearbyParks showNearbyParks = new ShowNearbyParks();
                showNearbyParks.execute();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("geocode",0);
        Double lat = Double.valueOf(sharedPreferences.getString("latitude",String.valueOf(0)));
        Double lng = Double.valueOf(sharedPreferences.getString("longitude",String.valueOf(0)));
        LatLng home = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(home).title("Your Home"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 10));
    }
    private class ShowNearbyParks extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("geocode", 0);
            Double lat = Double.valueOf(sharedPreferences.getString("latitude",String.valueOf(0)));
            Double lng = Double.valueOf(sharedPreferences.getString("longitude",String.valueOf(0)));
            String result = GoogleMapAPI.showNearby(lat,lng);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray addressList = jsonObject.getJSONArray("results");
                if (jsonObject != null && addressList.length()>0) {
                    for (int i = 0;i<addressList.length();i++) {
                        latList.add(i,((JSONArray)jsonObject.get("results")).getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lat").toString());
                        lngList.add(i,((JSONArray)jsonObject.get("results")).getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lng").toString());
                        nameList.add(i,((JSONArray)jsonObject.get("results")).getJSONObject(i).getString("name"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  "ok";
        }
        @Override
        protected void onPostExecute(String result) {
            for (int i = 0; i < latList.size(); i++) {
                LatLng marker = new LatLng(Double.valueOf(latList.get(i)), Double.valueOf(lngList.get(i)));
                map.addMarker(new MarkerOptions().position(marker).title(nameList.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }
        }
    }
}
