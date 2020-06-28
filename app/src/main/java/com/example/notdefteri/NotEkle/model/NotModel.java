package com.example.notdefteri.NotEkle.model;

import java.io.Serializable;
import java.util.Date;

public class NotModel implements Not, Serializable {
    String notId;
    String baslik;
    String aciklama;
   String tarih;

    public NotModel( String not_id ,String baslik ,String aciklama ,String  tarih ){
        this.notId=not_id;
        this.baslik =baslik;
        this.aciklama=aciklama;
        this.tarih =tarih;
    }

    public NotModel() {
    }

    @Override
    public String getNotId() {
        return notId;
    }

    @Override
    public String getBaslik() {
        return baslik;
    }

    @Override
    public String getAciklama() {
        return aciklama;
    }

    @Override
    public String getTarih() {
        return tarih;
    }

    @Override
    public int checkNotValidity(String baslik, String aciklama, Date tarih) {
        if (baslik == null || aciklama == null ||  tarih == null || baslik.equals(getBaslik()) || !aciklama.equals(getAciklama()) || !tarih.equals(getTarih())) {
            return -1;
        }
        return 0;
    }
}
