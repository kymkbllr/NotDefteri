package com.example.notdefteri.NotDetay.view;

import com.example.notdefteri.NotEkle.model.Not;

public interface INotDetayView {
    public void onClearText();
    public void onNotDetayResult(Boolean result,String mesaj, Not not );
    public void onNotGuncelleResult(Boolean result ,String mesaj);
    public void onSetProgressBarVisibility(int visibility);

}
