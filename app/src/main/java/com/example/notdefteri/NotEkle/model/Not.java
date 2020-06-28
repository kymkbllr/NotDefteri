package com.example.notdefteri.NotEkle.model;

import java.util.Date;

public interface Not {
    String getNotId();
    String getBaslik();
    String getAciklama();
    String getTarih();
    int checkNotValidity(String baslik, String aciklama, Date tarih);
}
