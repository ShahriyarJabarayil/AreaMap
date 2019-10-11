package com.areamaps.areamap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.areamaps.areamap.Models.Projects;
import com.areamaps.areamap.ViewHolders.ProjectViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;

public class AREAProjectList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Firebase ile bagli tanitmalar
    FirebaseDatabase database;
    DatabaseReference projectPath;
    FirebaseStorage storage;
    StorageReference photoPath;
    Context context=this;

    RecyclerView recyclerView_Projects;
    RecyclerView.LayoutManager layoutManager;

    //Adapter tanimlamasi
    FirebaseRecyclerAdapter<Projects, ProjectViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areaproject_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        //Elave edilenler
        database=FirebaseDatabase.getInstance();
        projectPath=database.getReference("Projects");
        storage= FirebaseStorage.getInstance();
        photoPath=storage.getReference();

       // recyclerView_Projects=(RecyclerView) findViewById(R.id.recyclerview_projects);
        recyclerView_Projects.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(context);
        recyclerView_Projects.setLayoutManager(layoutManager);

        LoadProjects();

    }

    private void LoadProjects() {
        FirebaseRecyclerOptions<Projects> options=new FirebaseRecyclerOptions.Builder<Projects>()
                .setQuery(projectPath,Projects.class)
                .build();

        //RecyclerView ucun adapterin alacagi data-lar
        adapter=new FirebaseRecyclerAdapter<Projects, ProjectViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProjectViewHolder holder, int position, @NonNull Projects model) {


                holder.txtName.setText(model.getProjectName());
                holder.txtRegion.setText(model.getPrPar1());
                holder.txtDate.setText(model.getPrDate1());
                holder.txtPower.setText(model.getPrPar3());

                //Picasso.with(getBaseContext()).load(model.getMealPhoto()).into(holder.imgMealPhoto);

                /*  final Projects onclikKind=model;

                    holder.setItemClickListener(new ItemClickListener() {

                    //Her bir item e klik edildikde bas verecek actionlar
                    @Override
                    public void OnClick(View v, int position, boolean isLongClick) {

                        Intent intent=new Intent(context,ReklamActivity.class);
                        intent.putExtra("MealId",adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });*/
            }

            @NonNull
            @Override
            public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView= LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.row_line_projects,viewGroup,false);
                return new ProjectViewHolder(itemView);

            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView_Projects.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.areaproject_list, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_map) {
            Intent intent=new Intent(context,MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
