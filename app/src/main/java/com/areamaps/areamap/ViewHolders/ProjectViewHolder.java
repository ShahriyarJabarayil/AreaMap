package com.areamaps.areamap.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.areamaps.areamap.Interface.ItemClickListener;
import com.areamaps.areamap.R;

public class ProjectViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    public ImageView imgProjects;
    public TextView txtName;
    public TextView txtRegion;
    public TextView txtDate;
    public TextView txtPower;


    private ItemClickListener itemClickListener;

    public ProjectViewHolder(@NonNull View itemView) {
        super(itemView);

        imgProjects=itemView.findViewById(R.id.img_project);
        txtName=itemView.findViewById(R.id.txt_name);
        txtRegion=itemView.findViewById(R.id.txt_region);
        txtDate=itemView.findViewById(R.id.txt_date);
        txtPower=itemView.findViewById(R.id.txt_power);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener (ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;

    }


    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v,(int)getAdapterPosition(),false);
    }
}
