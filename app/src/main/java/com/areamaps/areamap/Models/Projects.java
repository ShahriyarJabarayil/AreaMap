package com.areamaps.areamap.Models;

public class Projects {
    String projectName;
    String prDate1;
    String prPar1;
    String prPar2;
    String prPar3;
    String prPar4;
    String prPar5;
    String latitudu;
    String longitudu;
    String prPhoto;


    public Projects() {
    }

    public Projects(String projectName, String prDate1, String prPar1, String prPar2, String prPar3,
                    String prPar4, String prPar5, String latitudu, String longitudu, String prPhoto)
    {
        this.projectName = projectName;
        this.prDate1 = prDate1;
        this.prPar1 = prPar1;
        this.prPar2 = prPar2;
        this.prPar3 = prPar3;
        this.prPar4 = prPar4;
        this.prPar5 = prPar5;
        this.latitudu = latitudu;
        this.longitudu = longitudu;
        this.prPhoto = prPhoto;

    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPrDate1() {
        return prDate1;
    }

    public void setPrDate1(String prDate1) {
        this.prDate1 = prDate1;
    }

    public String getPrPar1() {
        return prPar1;
    }

    public void setPrPar1(String prPar1) {
        this.prPar1 = prPar1;
    }

    public String getPrPar2() {
        return prPar2;
    }

    public void setPrPar2(String prPar2) {
        this.prPar2 = prPar2;
    }

    public String getPrPar3() {
        return prPar3;
    }

    public void setPrPar3(String prPar3) {
        this.prPar3 = prPar3;
    }

    public String getPrPar4() {
        return prPar4;
    }

    public void setPrPar4(String prPar4) {
        this.prPar4 = prPar4;
    }

    public String getPrPar5() {
        return prPar5;
    }

    public void setPrPar5(String prPar5) {
        this.prPar5 = prPar5;
    }

    public String getLatitudu() {
        return latitudu;
    }

    public void setLatitudu(String latitudu) {
        this.latitudu = latitudu;
    }

    public String getLongitudu() {
        return longitudu;
    }

    public void setLongitudu(String longitudu) {
        this.longitudu = longitudu;
    }

    public String getPrPhoto() {
        return prPhoto;
    }

    public void setPrPhoto(String prPhoto) {
        this.prPhoto = prPhoto;
    }




}