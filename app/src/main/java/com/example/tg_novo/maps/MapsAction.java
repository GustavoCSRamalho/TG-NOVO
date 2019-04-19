package com.example.tg_novo.maps;

import com.example.tg_novo.maps.interfaces.MapsActionInterf;
import com.example.tg_novo.models.Coordenate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsAction implements MapsActionInterf {

    private static MapsAction mapsAction;

    private volatile GoogleMap mMap;

    protected MapsAction() {
    }

    public static MapsAction getInstance() {
        if (mapsAction == null) {
            mapsAction = new MapsAction();
        }
        return mapsAction;
    }

    @Override
    public void setGoogleMap(GoogleMap map) {
        mMap = map;
    }

    @Override
    public void addMarker(LatLng latLng, Coordenate coordenate) {
        mMap.addMarker(new MarkerOptions().position(latLng).title(coordenate.getusuario()));
    }

    @Override
    public void clear(){
        mMap.clear();
    }

}