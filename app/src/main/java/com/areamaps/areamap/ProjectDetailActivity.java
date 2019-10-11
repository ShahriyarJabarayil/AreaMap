package com.areamaps.areamap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.areamaps.areamap.Models.ProjectModel;
import com.areamaps.areamap.Models.Projects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectDetailActivity extends AppCompatActivity {
    TextView txtName, txtPower, txtYear, txtRegion, txtInstallPower,txtNetPower,txtProduction,txtGas,txtCO2,txtNote;
    ImageView imgProject;


    //Firebase ile bagli tanitmalar
    FirebaseDatabase database;
    DatabaseReference projectPath;
    FirebaseStorage storage;
    StorageReference photoPath;
    Context context = this;

    String projectName = null;
    List<String> getlist;

    ProjectModel modelprojects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        database = FirebaseDatabase.getInstance();
        projectPath = database.getReference("Project");//edit
        storage = FirebaseStorage.getInstance();
        photoPath = storage.getReference();


        txtName = findViewById(R.id.txt_name_detail);
        txtRegion = findViewById(R.id.txt_pro_region);
        txtYear = findViewById(R.id.txt_exploitation);
        txtPower = findViewById(R.id.txt_power);
        txtInstallPower = findViewById(R.id.txt_install_power);
        txtNetPower = findViewById(R.id.txt_net_power);
        txtProduction=findViewById(R.id.txt_production);
        txtGas=findViewById(R.id.txt_gas_saving);
        txtCO2=findViewById(R.id.txt_co2);
        txtNote=findViewById(R.id.txt_note);

        imgProject = findViewById(R.id.img_project_detail);

        if (getIntent() != null) {
            projectName = getIntent().getStringExtra("ProjectId");
        }
        if (!projectName.isEmpty()) {
            getProjects(projectName);
        }


    }

    private void getProjects(final String proid) {


        projectPath.child(proid).addValueEventListener(
                new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelprojects = dataSnapshot.getValue(ProjectModel.class);


                    txtName.setText(modelprojects.getProName());
                    txtRegion.setText(modelprojects.getRegion());
                    txtYear.setText(modelprojects.getProExploitation());
                    txtPower.setText(modelprojects.getProPower());
                    txtInstallPower.setText(modelprojects.getInstallPower());
                    txtNetPower.setText(modelprojects.getNetworkPower());
                    txtProduction.setText(modelprojects.getProProduction());
                    txtGas.setText(modelprojects.getGasSaving());
                    txtCO2.setText(modelprojects.getProCO2());
                    txtNote.setText(modelprojects.getProNote());

                try
                {
                    Picasso.with(getBaseContext()).load(modelprojects.getProPhoto()).into(imgProject);
                }
                catch (Exception e)
                {
                    return;
                }
                    //Toast.makeText(context,getlist.size(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}