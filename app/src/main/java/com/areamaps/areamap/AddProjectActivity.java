package com.areamaps.areamap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.areamaps.areamap.Models.Projects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AddProjectActivity extends AppCompatActivity {
    Button btnOk,btnCancel;
    EditText proName,proDate,proPar1,proPar2,proPar3,proPar4,proPar5,proDate2,proLat,proLong;

    //Firebase ile bagli tanitmalar
    FirebaseDatabase database;
    DatabaseReference projectReference;
    FirebaseStorage storage;
    StorageReference photoPath;
    Context context=this;
    List<String> listtest;

    Projects project;
    List<Projects> projectsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);


        database=FirebaseDatabase.getInstance();
        projectReference=database.getReference("Projects");

        btnOk=(Button)findViewById(R.id.btn_ok);
        btnCancel=(Button)findViewById(R.id.btn_cancel);

        proName=(EditText)findViewById(R.id.et_pr_name);
        proDate=(EditText)findViewById(R.id.et_start_date);
        proPar1=(EditText)findViewById(R.id.et_par1);
        proPar2=(EditText)findViewById(R.id.et_par2);
        proPar3=(EditText)findViewById(R.id.et_par3);
        proPar4=(EditText)findViewById(R.id.et_par4);
        proPar5=(EditText)findViewById(R.id.et_par5);
        proDate2=(EditText)findViewById(R.id.et_date2);
        proLat=(EditText)findViewById(R.id.et_geo_latitude);
        proLong=(EditText)findViewById(R.id.et_geo_longitude);

        try {
            getProjectList();
        }
        catch (Exception e)
        {}

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=proName.getText().toString();
                String Date1=proDate.getText().toString();
                String Par1=proPar1.getText().toString();
                String Par2=proPar2.getText().toString();
                String Par3=proPar3.getText().toString();
                String Par4=proPar4.getText().toString();
                String Par5=proPar5.getText().toString();

                String Latitudu=proLat.getText().toString();
                String Longitudu=proLong.getText().toString();
                String Photo="mmma";

                project=new Projects(name,Date1,Par1,Par2,Par3,Par4,Par5,Latitudu,Longitudu,Photo);


                if (project!=null){
                    projectReference.push().setValue(project);
                    //Toast.makeText(context,name+" layihesi əlavə edildi",Toast.LENGTH_SHORT).show();


                }
                proName.setText("");
                proDate.setText("");
                proPar1.setText("");
                proPar2.setText("");
                proPar3.setText("");
                proPar4.setText("");
                proPar5.setText("");
                proDate2.setText("");
                proLat.setText("");
                proLong.setText("");



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getProjectList();

                if(getProjectList()==null)
                    Toast.makeText(context,"Bosdu",Toast.LENGTH_SHORT).show();
                else {

                  //  Toast.makeText(context,  projectsList.size()+" Lahiye sayi", Toast.LENGTH_SHORT).show();

                        String a="";
                        for (int i=0;i<projectsList.size();i++)
                        {

                           a=a+ " "+getProjectList().get(i).getProjectName().toString();


                        }
                    Toast.makeText(context, getProjectList().get(1).getProjectName()+" layihesi əlavə edildi", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private  ArrayList<Projects> getProjectList()
    {
        projectReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projectsList.clear();
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Projects proje=postSnapshot.getValue(Projects.class);
                    projectsList.add(proje);
                }
               // Toast.makeText(context,projectsList.size()+"  aa",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return (ArrayList<Projects>) projectsList;
    }
}
