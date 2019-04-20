package com.example.tg_novo;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tg_novo.database.service.ServiceDatabase;
import com.example.tg_novo.maps.MapsAction;
import com.example.tg_novo.maps.interfaces.MapsActionInterf;
import com.example.tg_novo.models.Notification;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private volatile GoogleMap mMap;
    private PopupWindow popupWindow;
    private volatile  View popupView;
    private CoordinatorLayout coordinatorLayout;
    private ServiceDatabase fireBaseInterf;
    private MapsActionInterf mapsAction;
    private static volatile TextView historicoNotificatios;
    FloatingActionButton btnAjudar;
    FloatingActionButton btnCancelarAjuda;

    EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinator);
        mapsAction = MapsAction.getInstance();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        FirebaseApp.initializeApp(this);

        nome = findViewById(R.id.name);
        btnCancelarAjuda = findViewById(R.id.btnCancelarAjuda);
        btnAjudar = findViewById(R.id.btnAjudar);


        btnAjudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().equalsIgnoreCase("")){
                    System.out.println("Digite antes um nome");
                    Toast.makeText(getApplicationContext(), "Digite um nome antes", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("Ola "+nome.getText().toString());
                    String usuario = nome.getText().toString();
                    Notification notification = new Notification(usuario, usuario +" está indo socorrer.\n");
                    fireBaseInterf.saveNotificationsData(notification);
                    Toast.makeText(getApplicationContext(), "Notificação enviada", Toast.LENGTH_SHORT).show();
                }
            }
        } );

        btnCancelarAjuda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(nome.getText().toString().equalsIgnoreCase("")){
                    System.out.println("Digite antes um nome");
                    Toast.makeText(getApplicationContext(), "Digite um nome antes", Toast.LENGTH_SHORT).show();
                }else{
                    System.out.println("Ola "+nome.getText().toString());
                    String usuario = nome.getText().toString();
                    Notification notification = new Notification(usuario, usuario +" está cancelando a ajuda.\n");
                    fireBaseInterf.saveNotificationsData(notification);
                    Toast.makeText(getApplicationContext(), "Notificação enviada", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        TextView historicoNotificatios = findViewById(R.id.historicoNotificacoes);
//        System.out.println("Aqui : "+historicoNotificatios);
////        historicoNotificatios.clearComposingText();



        final Display display = getWindowManager().getDefaultDisplay();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup,null);

        popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        Button btnClose = (Button)popupView.findViewById(R.id.btnClose);

        historicoNotificatios = popupView.findViewById(R.id.historicoNotificacoes);
//        System.out.println("Vazio : "+historicoNotificatios);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    popupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER, 0,0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapsAction.setGoogleMap(mMap);
        fireBaseInterf = ServiceDatabase.getInstance();
        fireBaseInterf.buildConfiguration();
//        fireBaseInterf.setHistoricoNotificatios(popupView.findViewById(R.id.historicoNotificacoes));
    }

    public static TextView getTextViewHistoricoNotificacoes(){
        return historicoNotificatios;
    }
}
