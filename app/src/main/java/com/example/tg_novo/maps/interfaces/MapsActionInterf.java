package com.example.tg_novo.maps.interfaces;

import com.example.tg_novo.models.Coordenate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public interface MapsActionInterf {

    void setGoogleMap(GoogleMap map);

    void addMarker(LatLng latLng, Coordenate coordenate);

    void clear();
}