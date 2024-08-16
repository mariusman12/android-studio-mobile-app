package com.example.myapplication;


public class Produs {
    private int id;
    private String nume;
    private double pret;
    private String descriere;

    public Produs(int id, String nume, double pret, String descriere) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
    }

    //Getteri si Setteri pentru fiecare variabila
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
