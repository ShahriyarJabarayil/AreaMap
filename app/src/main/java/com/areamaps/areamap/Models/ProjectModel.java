package com.areamaps.areamap.Models;

public class ProjectModel
{
    public String proId;
    public String proName;
    public String region;
    public String proExploitation;
    public String proYear;
    public String proKindStation;
    public String proPower;
    public String installPower;
    public String networkPower;
    public String proProduction;
    public String gasSaving;
    public String proCO2;
    public String proPhoto;
    public String geoLatitude;
    public String geoLongitude;
    public String proNote;

    public ProjectModel() { }

    public ProjectModel(String proId, String proName, String region, String proExploitation, String proYear, String proKindStation,
                        String proPower, String installPower, String networkPower, String proProduction,
                        String gasSaving, String proCO2, String proPhoto, String geoLatitude, String geoLongitude, String proNote) {
        this.proId = proId;
        this.proName = proName;
        this.region = region;
        this.proExploitation = proExploitation;
        this.proYear = proYear;
        this.proKindStation = proKindStation;
        this.proPower = proPower;
        this.installPower = installPower;
        this.networkPower = networkPower;
        this.proProduction = proProduction;
        this.gasSaving = gasSaving;
        this.proCO2 = proCO2;
        this.proPhoto = proPhoto;
        this.geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
        this.proNote = proNote;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProExploitation() {
        return proExploitation;
    }

    public void setProExploitation(String proExploitation) {
        this.proExploitation = proExploitation;
    }

    public String getProYear() { return proYear;  }

    public void setProYear(String proYear) {
        this.proYear = proYear;
    }

    public String getProKindStation() {
        return proKindStation;
    }

    public void setProKindStation(String proKindStation) {
        this.proKindStation = proKindStation;
    }

    public String getProPower() {
        return proPower;
    }

    public void setProPower(String proPower) {
        this.proPower = proPower;
    }

    public String getInstallPower() {
        return installPower;
    }

    public void setInstallPower(String installPower) {
        this.installPower = installPower;
    }

    public String getNetworkPower() {
        return networkPower;
    }

    public void setNetworkPower(String networkPower) {
        this.networkPower = networkPower;
    }

    public String getProProduction() {
        return proProduction;
    }

    public void setProProduction(String proProduction) {
        this.proProduction = proProduction;
    }

    public String getGasSaving() {
        return gasSaving;
    }

    public void setGasSaving(String gasSaving) {
        this.gasSaving = gasSaving;
    }

    public String getProCO2() {
        return proCO2;
    }

    public void setProCO2(String proCO2) {
        this.proCO2 = proCO2;
    }

    public String getProPhoto() {
        return proPhoto;
    }

    public void setProPhoto(String proPhoto) {
        this.proPhoto = proPhoto;
    }

    public String getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(String geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public String getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(String geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public String getProNote() {
        return proNote;
    }

    public void setProNote(String proNote) {
        this.proNote = proNote;
    }
}
