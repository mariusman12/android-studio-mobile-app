package com.example.myapplication;

public class FacturaModelClass {
    Integer id;
    String societate;
    String facturant;
    String serie;
    String pret;

    public FacturaModelClass(String societate, String facturant, String serie, String pret){

            this.societate=societate;
            this.facturant=facturant;
            this.serie=serie;
            this.pret=pret;
        }
    public FacturaModelClass(Integer id,String societate, String facturant, String serie, String pret){
            this.id=id;
            this.societate=societate;
            this.facturant=facturant;
            this.serie=serie;
            this.pret=pret;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSocietate() {
        return societate;
    }

    public void setSocietate(String societate) {
        this.societate = societate;
    }

    public String getFacturant() {
        return facturant;
    }

    public void setFacturant(String facturant) {
        this.facturant = facturant;
    }


    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

}
