package com.areamaps.areamap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.areamaps.areamap.Interface.ItemClickListener;
import com.areamaps.areamap.Models.ProjectModel;
import com.areamaps.areamap.ViewHolders.ProjectViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


//Appcompat activity deyisib Fragment etdim search bar ucun
public class MainActivity extends FragmentActivity {

    BottomNavigationView bottomNavigationView;

    //Firebase ile bagli tanitmalar
    FirebaseDatabase database;
    DatabaseReference projectPath;
    FirebaseStorage storage;
    StorageReference photoPath;
    Context context=this;

    RecyclerView recyclerView_Projects;
    RecyclerView.LayoutManager layoutManager;

    MaterialSearchBar materialSearchBar;
    List<String> searchlist= new ArrayList<String>();

    //Adapter tanimlamasi
    //FirebaseRecyclerAdapter<Projects, ProjectViewHolder> adapter;

    FirebaseRecyclerAdapter<ProjectModel, ProjectViewHolder> newadapter;
    FirebaseRecyclerAdapter<ProjectModel, ProjectViewHolder> searchadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Elave edilenler
        database=FirebaseDatabase.getInstance();
        projectPath=database.getReference("Project");//edit
        storage= FirebaseStorage.getInstance();
        photoPath=storage.getReference();

        recyclerView_Projects=(RecyclerView) findViewById(R.id.recyclerview_pro);
        recyclerView_Projects.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(context);
        recyclerView_Projects.setLayoutManager(layoutManager);


        materialSearchBar=(MaterialSearchBar)findViewById(R.id.search_bar_meal);
        materialSearchBar.setCardViewElevation(10);
       // materialSearchBar.setHint("Axtaris edin ...");


       LoadProjects();


        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> blank=new ArrayList<>();
                for (String search:searchlist)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText()))
                    {
                        blank.add(search);
                    }
                    materialSearchBar.setLastSuggestions(blank);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyclerView_Projects.setAdapter(newadapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                //axtaris edildikde axtaris adaptirini teyini
                startSearchText(text);

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_hom:
                        LoadProjects();
                        break;
                    case R.id.nav_maps:
                        Intent intent=new Intent(context,MapsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_projects:
                        Intent y=new Intent(context,ChartActivity.class);
                        startActivity(y);
                        break;
                    case R.id.nav_web_page:
                        Intent i=new Intent(context,WebPageActivity.class);
                        startActivity(i);
                        break;
                    case R.id.main_moreMenu:
                        PopupMenu popup = new PopupMenu(context, findViewById(R.id.main_moreMenu));
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.main_context_menu, popup.getMenu());
                        popup.show();

                    /*Intent itent6=new Intent(MainActivity.this,more.class);
                startActivity(itent6);*/
                        break;
                }

                return true;
            }
        });

    }



    private void LoadProjects() {
        FirebaseRecyclerOptions<ProjectModel> options=new FirebaseRecyclerOptions.Builder<ProjectModel>()
                .setQuery(projectPath,ProjectModel.class)
                .build();

        //RecyclerView ucun adapterin alacagi data-lar
        newadapter=new FirebaseRecyclerAdapter<ProjectModel, ProjectViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProjectViewHolder holder, int position, @NonNull final ProjectModel model) {


                holder.txtName.setText(model.getProName());
                holder.txtRegion.setText( "Rayon: "+model.getRegion());
                holder.txtDate.setText("İstismar ili:  "+model.getProYear());
                holder.txtPower.setText("Gücü  (KVt):  "+model.getProPower());


                if (model.getProPhoto()!=null&&model.getProPhoto().length()>5)
                    Picasso.with(getBaseContext()).load(model.getProPhoto()).into(holder.imgProjects);


             /*   try {
                    Picasso.with(getBaseContext()).load(model.getProPhoto()).into(holder.imgProjects);
                }
                catch (Exception e)
                {
                    return;
                }*/


                    final ProjectModel clickproject=model;

                    holder.setItemClickListener(new ItemClickListener() {

                    //Her bir item e klik edildikde bas verecek actionlar
                    @Override
                    public void OnClick(View v, int position, boolean isLongClick) {

                        Intent intent=new Intent(context,ProjectDetailActivity.class);
                        intent.putExtra("ProjectId", newadapter.getRef(position).getKey());
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView= LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.row_line_projects,viewGroup,false);
                return new ProjectViewHolder(itemView);

            }
        };
        newadapter.startListening();
        newadapter.notifyDataSetChanged();
        recyclerView_Projects.setAdapter(newadapter);
        Toast.makeText(context,"Yükləmə başa çatdı",Toast.LENGTH_LONG).show();
    }

    private void startSearchText(CharSequence text) {
        Query searchquery = projectPath.orderByChild("proName").startAt(text.toString()).endAt(text.toString()+"\uf8ff");
        FirebaseRecyclerOptions optionProjectName = new FirebaseRecyclerOptions.Builder<ProjectModel>()
                .setQuery(searchquery, ProjectModel.class).build();

        searchadapter = new FirebaseRecyclerAdapter<ProjectModel, ProjectViewHolder>(optionProjectName) {
            @Override
            protected void onBindViewHolder(@NonNull ProjectViewHolder holder, int position, @NonNull ProjectModel model) {
                holder.txtName.setText(model.getProName());
                //Picasso.with(getBaseContext()).load(model.getProName()).into(holder.imgProjects);

                ProjectModel itemclick = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {

                        Intent detailIntent = new Intent(context, ProjectDetailActivity.class);
                        detailIntent.putExtra("ProjectId", searchadapter.getRef(position).getKey());
                        startActivity(detailIntent);
                    }
                });

            }

            @NonNull
            @Override
            public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_line_projects, viewGroup, false);
                return new ProjectViewHolder(view);
            }
        };

        searchadapter.startListening();
        recyclerView_Projects.setAdapter(searchadapter);
    }
}

