package com.example.notdefteri.NotEkle.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notdefteri.NotEkle.presenter.NotEklePresenter;
import com.example.notdefteri.NotEkle.presenter.NotEklePresenterComply;
import com.example.notdefteri.R;

public class NotEkleActivity extends AppCompatActivity implements INotEkleView {
    EditText yeni_not , yeni_not_aciklama;
    Button button;
    private NotEklePresenter notEklePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);
        yeni_not=findViewById(R.id.baslik_et);
        yeni_not_aciklama=findViewById(R.id.aciklama_et);
        button=findViewById(R.id.ekle_btn);
        notEklePresenter =new NotEklePresenterComply(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          String baslik = yeni_not.getText().toString();
          String aciklama =yeni_not_aciklama.getText().toString();
          notEklePresenter.notEkle(baslik ,aciklama);
            }
        });

    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onNotEkleResult(Boolean result,String mesaj) {

        Toast.makeText(this,mesaj, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}