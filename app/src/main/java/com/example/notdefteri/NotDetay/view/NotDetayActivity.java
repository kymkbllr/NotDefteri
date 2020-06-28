package com.example.notdefteri.NotDetay.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notdefteri.NotDetay.presenter.NotDetayPresenter;
import com.example.notdefteri.NotDetay.presenter.NotDetayPresenterComply;
import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.NotEkle.presenter.NotEklePresenter;
import com.example.notdefteri.R;

public class NotDetayActivity extends AppCompatActivity implements INotDetayView {
    public NotDetayPresenter notDetayPresenter;

     EditText baslikk;
    EditText aciklamaa;
    TextView tarihh;
    Button guncelle_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_detay);
        Intent intent = getIntent();
        final String id =intent.getStringExtra("Key_Id");
        String baslik = intent.getStringExtra("Key_Baslik");
        String aciklama = intent.getStringExtra("Key_Aciklama");
        String tarih = intent.getStringExtra("Key_Tarih");
        baslikk=findViewById(R.id.baslik_et);
        aciklamaa=findViewById(R.id.aciklama_et);
        tarihh=findViewById(R.id.tarih_tv);
        guncelle_btn=findViewById(R.id.guncelle_btn);
        notDetayPresenter =new NotDetayPresenterComply(this);
        notDetayPresenter.notDetay(baslik,aciklama,tarih);
        guncelle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String baslik=baslikk.getText().toString();
              String  aciklama=aciklamaa.getText().toString();
              notDetayPresenter.notGuncelle(id,baslik,aciklama);
            }
        });


    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onNotDetayResult(Boolean result,String mesaj, Not not) {
      if (result){
          baslikk.setText(not.getBaslik());
          aciklamaa.setText(not.getAciklama());
          tarihh.setText(not.getTarih());
      }
    }

    @Override
    public void onNotGuncelleResult(Boolean result, String mesaj) {
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}