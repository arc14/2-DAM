package iesm.pmdm.pmdm_maps_02;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import iesm.pmdm.pmdm_maps_02.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Asociacion de escuchadores
        mMap.setOnMapClickListener(this);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        Marker cadiz = mMap.addMarker(
                new MarkerOptions().position(
                        new LatLng(36.52, -6.28)));

        Marker huelva = mMap.addMarker(
                new MarkerOptions().position(
                        new LatLng(37.26, -6.94))
                        .title("Huelva"));

        Marker sevilla = mMap.addMarker(
                new MarkerOptions().position(
                        new LatLng(37.3914, -5.982))
                        .title("Sevilla")
                        .snippet("\uD83D\uDC83")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));

        //Muestra el identificativo por defecto del marcador
        sevilla.showInfoWindow();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sevilla.getPosition(), 10));

    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        //Toast.makeText(this, "Coordenadas: " + latLng.longitude + " - " + latLng.latitude, Toast.LENGTH_SHORT).show();
        //mMap.addMarker(new MarkerOptions().position(latLng));

        //Geocoding de la posicion
        Geocoder geocoder = new Geocoder(this);

        //1. Obtener las posiciones respecto a la direccion marcada
        try {
            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            //2. Sacar los datos
            Address direccion = direcciones.get(0);
            String pais = direccion.getCountryName();
            String ciudad = direccion.getLocality();
            String calle = direccion.getAddressLine(0);
            String cp = direccion.getPostalCode();

            //3.

            Toast.makeText(this, "Dirección: " + calle + ", " + ciudad + ", " + pais, Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se ha indicado una posicion correcta", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {


        return false;
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }
}