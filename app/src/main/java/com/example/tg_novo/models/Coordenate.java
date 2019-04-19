package com.example.tg_novo.models;

public class Coordenate {

    public Coordenate(){}

    public Coordenate(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double longitude;

    private double latitude;

    private String usuario;



    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }
}