package com.areamaps.areamap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.areamaps.areamap.Models.Parseinfowindow;
import com.areamaps.areamap.Models.ProjectModel;
import com.areamaps.areamap.Models.Projects;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {

    private GoogleMap mMap;
    Boolean onClickCheck = false;
    String ErrorMessage="Xəta baş verdi";
    static final LatLng PGES = new LatLng(40.469018, 50.344796);
    static final LatLng QHES = new LatLng(40.540169, 48.919290);
    static final LatLng SGES = new LatLng(40.433278, 50.056203);
    static final LatLng ShKSES = new LatLng(41.231982, 47.181835);
    static final LatLng SAEYK = new LatLng(40.836024, 46.344626);
    static final LatLng AKABOEMS = new LatLng(40.361981, 50.073013);
    static final LatLng birEbirES = new LatLng(39.718691, 48.028732);
    static final LatLng BKABOEMS = new LatLng(39.749666, 47.531794);
    LinearLayout linearLayout;


    //MarkerOptions markerOptions=new MarkerOptions().position(new LatLng(39.5,40)) .title("Alma");


    //Firebase ile bagli tanitmalar
    FirebaseDatabase database;
    DatabaseReference projectReference;
    FirebaseStorage storage;
    StorageReference photoPath;

    FirebaseAuth auth;


    Context context = this;
    List<String> listtest;
    SupportMapFragment spmpfrgmnt;
    String name = "";
    float color;


    Toolbar toolbar;
    Projects project;

   // List<Projects> projectsList = new ArrayList<>();
    List<MarkerOptions> markerOptionsList = new ArrayList();
    List<ProjectModel> projectsListmodel = new ArrayList<>();
    List<LatLng> longlatList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        spmpfrgmnt = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);


        this.
        //Firebase Path cagirilmasi
        database = FirebaseDatabase.getInstance();
        projectReference = database.getReference("Project");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        try {
            getProjectList();
            //yeni
            LoadProjects(0);


        } catch (Exception e) {
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }

        auth = FirebaseAuth.getInstance();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (getProjectList() == null) {
            getProjectList();
            Toast.makeText(context,ErrorMessage,Toast.LENGTH_SHORT).show();
        }
        mMap = googleMap;

        getProjectList();

        final CustomInfoWindowGoogleMap customInfoWindowGoogleMap = new CustomInfoWindowGoogleMap(context);

       /* mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(context,"tiklandi",Toast.LENGTH_SHORT).show();
            }
        });*/


        if (mMap != null) {
            mMap.setInfoWindowAdapter(customInfoWindowGoogleMap);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ShKSES, 8));
            mMap.addMarker(new MarkerOptions().position(QHES)).setTitle("Qobustan Hibrid Elektrik Stansiyası");
            mMap.addMarker(new MarkerOptions().position(SGES)).setTitle("Suraxanı Günəş Elektrik Stansiyası");
            mMap.addMarker(new MarkerOptions().position(ShKSES)).setTitle("Şəki Kiçik Su Elektrik Stansiyası");
            mMap.addMarker(new MarkerOptions().position(SAEYK).snippet("Adi: " + " /n/Stanisyalar haqqinda")
                    .title("Samux Aqro Enerji Yaşayış Kompleksi"));
            mMap.addMarker(new MarkerOptions().position(AKABOEMS)).setTitle("Abşeron Kiçik ABOEM Stansiyası");
            mMap.addMarker(new MarkerOptions().position(birEbirES)).setTitle("1 Ev-1 Elektrik stansiyası");
            mMap.addMarker(new MarkerOptions().position(BKABOEMS)).setTitle("Beyləqan Rayonu Kiçik ABOEM Stansiyası");



            if (onClickCheck) {
                mMap.clear();
                for (int i = 0; i < markerOptionsList.size(); i++) {

                    mMap.addMarker(markerOptionsList.get(i).icon(BitmapDescriptorFactory.defaultMarker(color)));
                  //  PolylineOptions polylineOptions=new PolylineOptions().add(longlatList.get(i));
                }


            }




        }

       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.isZoomControlsEnabled();
    }




    public ArrayList<ProjectModel> getProjectList() {

        projectReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //elave olunanlar


                projectsListmodel.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProjectModel project = postSnapshot.getValue(ProjectModel.class);
                    projectsListmodel.add(project);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return (ArrayList<ProjectModel>) projectsListmodel;

    }

    public void LoadProjects(int year) {


        markerOptionsList.clear();
        longlatList.clear();


        MarkerOptions markerOptions = new MarkerOptions();
        for (int i = 0; i < projectsListmodel.size(); i++) {
            Double lat = Double.parseDouble(projectsListmodel.get(i).getGeoLatitude());
            Double lng = Double.parseDouble(projectsListmodel.get(i).getGeoLongitude());

            markerOptions = new MarkerOptions().position(new LatLng(lat, lng))
                    .snippet("Layihənin adı: "+projectsListmodel.get(i).getProName()+"\n"+
                            "Ərazi: "+projectsListmodel.get(i).getRegion()+"\n"+
                            "İstehsal gücü: "+projectsListmodel.get(i).getProProduction()+"\n")
                    .title(projectsListmodel.get(i).getProPhoto());


            //markerOptionsList.add(markerOptions);


            switch (year){
                case 0:
                    markerOptionsList.add(markerOptions);
                break;
                case 44:
                    if(projectsListmodel.get(i).getProName().toUpperCase().contains(String.valueOf("SES"))){
                    markerOptionsList.add(markerOptions);
                    }
                    break;
                case 55:
                    if(projectsListmodel.get(i).getProName().toUpperCase().contains(String.valueOf("KES"))){
                        markerOptionsList.add(markerOptions);
                    }
                    break;
                case 66:
                    if(projectsListmodel.get(i).getProName().toUpperCase().contains(String.valueOf("GES"))){
                        markerOptionsList.add(markerOptions);
                        //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_solar_logo1))
                    }
                    break;
                    default:
                        if(projectsListmodel.get(i).getProYear().contains(String.valueOf(year))) {
                            markerOptionsList.add(markerOptions);
                        }

                        break;

            }
        }


        //Toast.makeText(context,markerOptionsList.size(),Toast.LENGTH_SHORT).show();
    }


    public void showPopupMenu(View view) {
        PopupMenu popup=new PopupMenu(context,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.map_context_menu);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pro2010:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(2010);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_YELLOW;

                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }

                return true;
            case R.id.pro2030:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(2030);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_GREEN;

                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }

                return true;

            case R.id.allprojects:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(0);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_BLUE;

                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }
                return true;


            case R.id.SES_station:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(44);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_CYAN;
                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }
                return true;

            case R.id.KES_station:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(55);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_MAGENTA;

                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }

                return true;
            case R.id.GES_station:

                getProjectList();
                if (getProjectList() == null) {
                    Toast.makeText(context, ErrorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    LoadProjects(66);

                    onClickCheck = true;
                    color = BitmapDescriptorFactory.HUE_YELLOW;

                    spmpfrgmnt.getMapAsync(MapsActivity.this);
                }
                return true;

            case R.id.app_logout:

                auth.signOut();
                Intent intent=new Intent(context,LoginActivity.class);
                startActivity(intent);
                finish();
                return true;

                default:return false;

        }
    }

}

