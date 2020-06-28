package com.example.notdefteri.NotListesi.view;

import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;

import java.util.List;

public interface INotListeleView {
    public void onClearText();
    public void onNotListeleResult(Boolean result , List<NotModel> notList);
    public void onSetProgressBarVisibility(int visibility);
}
