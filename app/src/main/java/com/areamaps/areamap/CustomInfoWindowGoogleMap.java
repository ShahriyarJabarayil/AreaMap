package com.areamaps.areamap;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.areamaps.areamap.Interface.OnInfoWindowElemTouchListener;
import com.areamaps.areamap.Models.Parseinfowindow;
import com.areamaps.areamap.Models.ProjectModel;
import com.areamaps.areamap.Models.Projects;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.PropertyResourceBundle;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {
    //Firebase ile bagli tanitmalar
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference projectReference=database.getReference("Projects");
    private OnInfoWindowElemTouchListener infoButtonListener;
    private ArrayList<ProjectModel> projectModels;



    private Marker marker;
    private boolean pressed = false;

    private Context context;
    public CustomInfoWindowGoogleMap(Context ctx)
    {
        context=ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {


        final View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.layout_infowindow, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(700, ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageView img = view.findViewById(R.id.ciw_img);
        ImageButton closeButton=view.findViewById(R.id.id_close_infoview);


        TextView namePro = view.findViewById(R.id.ciw_name);
        TextView locationPro = view.findViewById(R.id.ciw_location);
        TextView powerPro = view.findViewById(R.id.ciw_power);

        //namePro.setText(marker.getTitle());
        locationPro.setText(marker.getSnippet());
        Parseinfowindow parseinfowindow=new Parseinfowindow();


       /* if (getprojects()!=null)
            Toast.makeText(context,parseinfowindow.setListparse().size(),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"hec bir melumat gelmedi",Toast.LENGTH_SHORT).show();*/





       /* infoButtonListener = new OnInfoWindowElemTouchListener(closeButton){
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                Toast.makeText(context, "click on button 2", Toast.LENGTH_LONG).show();
            }
        };
        closeButton.setOnTouchListener(infoButtonListener);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "click on button 2", Toast.LENGTH_LONG).show();
            }
        });*/
       closeButton.setOnClickListener(new ImageButton.OnClickListener(){
          @Override
           public void onClick(View v)
          {
              Toast.makeText(context, "click on button 2", Toast.LENGTH_LONG).show();
          }
       });


        if(marker.getTitle()!=null&&marker.getTitle().length()>5)
        Picasso.with(context).load(marker.getTitle()).into(img);
        else
            Toast.makeText(context,"xəta, contentə uyğun şəkil tapilmadı",Toast.LENGTH_SHORT).show();

        /*Projects infoWindowData = (Projects) marker.getTag();

        int imageId = context.getResources().getIdentifier(infoWindowData.getPrPhoto().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);*/

     /*   hotel_tv.setText(infoWindowData.getHotel());
        food_tv.setText(infoWindowData.getFood());
        transport_tv.setText(infoWindowData.getTransport());*/

        return view;
    }

    public ArrayList<ProjectModel> getprojects()
    {
        projectReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //elave olunanlar


                projectModels.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProjectModel project = postSnapshot.getValue(ProjectModel.class);
                    projectModels.add(project);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return (ArrayList<ProjectModel>) projectModels;
    }


}
