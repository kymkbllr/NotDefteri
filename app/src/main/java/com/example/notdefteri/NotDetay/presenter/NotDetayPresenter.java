package com.example.notdefteri.NotDetay.presenter;

import com.example.notdefteri.NotEkle.model.NotModel;

public interface NotDetayPresenter {

    void notDetay( String baslik ,String aciklama,String tarih);
    void notGuncelle(String id ,String baslik ,String aciklama);
}
