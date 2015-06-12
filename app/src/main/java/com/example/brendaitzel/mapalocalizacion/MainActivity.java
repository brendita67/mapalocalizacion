package com.example.brendaitzel.mapalocalizacion;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener {


    private final LatLng CASA = new LatLng(18.465096, -100.577027);

    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        //Tipo de mapa que se mostrara
        mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(CASA, 15));

        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMyLocationButtonEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.getUiSettings().setRotateGesturesEnabled(true);
        mapa.getUiSettings().setZoomGesturesEnabled(true);
        mapa.addMarker(new MarkerOptions().position(CASA).title("CASA").snippet("UNNAMED RD").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }
    //Efecto de camara al ir al marcador CASA
    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(CASA));
    }

    //Metododo para obtener las coordenadas de la ubicacion geografica actual
    public void animateCamera(View view) {
        if (mapa.getMyLocation()!= null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapa.getMyLocation().getLatitude(),mapa.getMyLocation().getLongitude()),15));
    }

    //Metodo para agregar un marcador a la ubicacion central de la pantalla
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(new LatLng(mapa.getCameraPosition().target.latitude, mapa.getCameraPosition().target.longitude)));
    }

    //Metodo para agregar un marcador a la ubicacion seleccionada
    @Override
    public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
//Mostrar el mapa de tipo TERRENO
        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        //Mostrar el mapa de tipo HIBRIDO
        if (id == R.id.action_settings2) {
            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        //Mostrar el mapa de tipo SATELITE
        if (id == R.id.action_settings3) {
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        //Mostrar el mapa de tipo NORMAL
        if (id == R.id.action_settings4) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        //Mostrar el mapa de tipo NADA
        if (id == R.id.action_settings5) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
