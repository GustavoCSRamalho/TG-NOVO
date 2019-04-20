package com.example.tg_novo.database.service;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.tg_novo.MainActivity;
import com.example.tg_novo.maps.MapsAction;
import com.example.tg_novo.maps.interfaces.MapsActionInterf;
import com.example.tg_novo.models.Coordenate;
import com.example.tg_novo.models.Notification;
import com.example.tg_novo.utils.SupportDataFireDB;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ServiceDatabase {
    private static ServiceDatabase fireBaseService;

    private static  SupportDataFireDB supportData;

    private DatabaseReference dataBaseRefCoordenate;
    private DatabaseReference dataBaseRefNofications;


//    private DatabaseReference dataBaseRefPedidos;
//
//    private DatabaseReference dataBaseRefUsuarios;

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database;

    public volatile TextView historicoNotificatios;


    private List<Coordenate> coordenatelist;

    private List<Notification> notificationList;

    private MapsActionInterf mapsActivity;

    protected ServiceDatabase() {
        mapsActivity = MapsAction.getInstance();
    }

    public static ServiceDatabase getInstance() {
        if (fireBaseService == null) {
            fireBaseService = new ServiceDatabase ();
        }

        return fireBaseService;
    }


//    public void saveCoordenateData(Coordenate coordenate) {
//
//        fireBaseService.dataBaseRefCoordenate.push().setValue(coordenate);
//
//    }

    public void saveNotificationsData(Notification notification) {
//
        fireBaseService.dataBaseRefNofications.push().setValue(notification);
//
    }

    public void buildConfiguration() {
        supportData = new SupportDataFireDB();
//        database = FirebaseDatabase.getInstance();


        setDataBaseRefCoordenate();
        setDataBaseRefNotifications();
//        setDataBaseRefPedidos();
//        setDataBaseRefUsuarios();

//        setFireBaseAuth();

//        setAllCoordenateData();
        setListenerToCoordenateDataBase();
        setListenerToNoficationsDataBase();
    }

    public FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }


//
    public void setAllCoordenateData() {
        DatabaseReference coordenate = getDataBaseRefCoordenate();
        System.out.println("REF : "+ MainActivity.getTextViewHistoricoNotificacoes());
        historicoNotificatios = MainActivity.getTextViewHistoricoNotificacoes();
        historicoNotificatios.append("Uhuu");
        historicoNotificatios.clearComposingText();
        historicoNotificatios.setText("");
        coordenate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mapsActivity.clear();
                coordenatelist = new ArrayList<>();
                System.out.println("Aqui");
                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    System.out.println("Valor : "+String.valueOf(dsp.getValue()));
                    Coordenate c = new Gson().fromJson(String.valueOf(dsp.getValue()), Coordenate.class);
                    coordenatelist.add(c); //add result into array list

                    historicoNotificatios.append(c.getusuario()+"\n");

                    System.out.println(c.getusuario());


                }
//                for (Coordenate coordenate : coordenatelist) {
//                    mapsActivity.addMarker(new LatLng(coordenate.getLatitude(), coordenate.getLongitude()), coordenate);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    @Override
//    public List getAllCoordenateData() {
//        return coordenatelist;
//    }

    public void setListenerToCoordenateDataBase() {
        final DatabaseReference coordenate = getDataBaseRefCoordenate();
//        historicoNotificatios = MainActivity.getTextViewHistoricoNotificacoes();
//        historicoNotificatios.clearComposingText();
        coordenate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mapsActivity.clear();
                if(coordenatelist == null){
                    coordenatelist = new ArrayList<>();
                }
                coordenatelist.clear();
//                historicoNotificatios.clearComposingText();
//                historicoNotificatios.setText("");

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    System.out.println(String.valueOf(dsp.getValue()));
                    Coordenate c = new Gson().fromJson(String.valueOf(dsp.getValue()), Coordenate.class);
                    coordenatelist.add(c); //add result into array list
                    System.out.println("1 : "+c.getusuario());
//                    historicoNotificatios.append(c.getusuario()+"\n");

                }
//                for (Coordenate coordenate : coordenatelist) {
//                    mapsActivity.addMarker(new LatLng(coordenate.getLatitude(), coordenate.getLongitude()), coordenate);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setListenerToNoficationsDataBase() {
        final DatabaseReference notification = getDataBaseRefNotifications();
        historicoNotificatios = MainActivity.getTextViewHistoricoNotificacoes();
        historicoNotificatios.clearComposingText();
        notification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mapsActivity.clear();
                if(notificationList == null){
                    notificationList = new ArrayList<>();
                }
                notificationList.clear();
                historicoNotificatios.clearComposingText();
                historicoNotificatios.setText("");

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    System.out.println(String.valueOf(dsp.getValue()));
                    Gson gson = new Gson();
                    String data = gson.toJson(dsp.getValue());
                    System.out.println("DATA : "+data);

                    Notification c = new Gson().fromJson(data, Notification.class);
                    notificationList.add(c); //add result into array list
//                    System.out.println("1 : "+c.getusuario());
                    historicoNotificatios.append("Usuario : "+c.getUsuario()+" Mensagem : "+c.getMensagem());

                }
//                for (Coordenate coordenate : coordenatelist) {
//                    mapsActivity.addMarker(new LatLng(coordenate.getLatitude(), coordenate.getLongitude()), coordenate);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    @Override
    public void setDataBaseRefCoordenate() {
        FirebaseDatabase database = getFirebaseDatabase();
        fireBaseService.dataBaseRefCoordenate = database.getReference(supportData.getDatabaseNameCoordenate());

    }

    public void setDataBaseRefNotifications() {
        FirebaseDatabase database = getFirebaseDatabase();
        fireBaseService.dataBaseRefNofications = database.getReference(supportData.getDatabaseNameNotifications());

    }

//    @Override
//    public void setDataBaseRefPedidos() {
//        FirebaseDatabase database = getFirebaseDatabase();
//        fireBaseService.dataBaseRefPedidos = database.getReference(supportData.getDatabaseNamePedidos());
//
//
//    }

//    @Override
//    public void setDataBaseRefUsuarios() {
//        FirebaseDatabase database = getFirebaseDatabase();
//        fireBaseService.dataBaseRefUsuarios = database.getReference("usuarios");
//    }

//    @Override
    public DatabaseReference getDataBaseRefCoordenate() {
        FirebaseDatabase database = getFirebaseDatabase();
        if(dataBaseRefCoordenate == null){
            dataBaseRefCoordenate= database.getReference(supportData.getDatabaseNameCoordenate());
        }
        return dataBaseRefCoordenate;
    }

    public DatabaseReference getDataBaseRefNotifications() {
        FirebaseDatabase database = getFirebaseDatabase();
        if(dataBaseRefNofications == null){
            dataBaseRefNofications= database.getReference(supportData.getDatabaseNameNotifications());
        }
        return dataBaseRefNofications;
    }

//    @Override
//    public DatabaseReference getDataBaseRefPedidos() {
//        FirebaseDatabase database = getFirebaseDatabase();
//        if(dataBaseRefPedidos == null){
//            dataBaseRefPedidos = database.getReference(supportData.getDatabaseNamePedidos());
//        }
//        return dataBaseRefPedidos;
//    }

//    @Override
//    public DatabaseReference getDataBaseRefUsuarios() {
//        FirebaseDatabase database = getFirebaseDatabase();
//        if(dataBaseRefUsuarios == null){
//            dataBaseRefUsuarios = database.getReference("usuarios");
//        }
//        return dataBaseRefUsuarios;
//    }

//    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        if(database == null){
            database = FirebaseDatabase.getInstance();
        }
        return database;
    }

//    @Override
    public void setFireBaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
    }


//    public void setHistoricoNotificatios(TextView obj) {
//        this.historicoNotificatios = obj;
//    }
}