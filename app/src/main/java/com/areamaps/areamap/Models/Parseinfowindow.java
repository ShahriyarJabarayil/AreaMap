package com.areamaps.areamap.Models;

import com.areamaps.areamap.MapsActivity;

import java.util.List;

public class Parseinfowindow {
    List <String> listparse;
    public List<String> parseList(ProjectModel model)
    {
        listparse.add(model.getProPhoto());
        return listparse;
    }

    public List<String> setListparse() {
        MapsActivity mp=new MapsActivity();
        mp.getProjectList();
        for (int k = 0; k<mp.getProjectList().size(); k++)
        {
            listparse.add(mp.getProjectList().get(k).toString());

        }
        return listparse;
    }
}
