package com.example.notdefteri.NotEkle.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.NotEkle.view.INotEkleView;
import com.example.notdefteri.NotEkle.view.NotEkleActivity;
import com.example.notdefteri.login.ILoginView;
import com.example.notdefteri.login.model.IUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class NotEklePresenterComply implements NotEklePresenter {
    INotEkleView iNotEkleView;
    Not not;
    FirebaseFirestore db;
    String TAG= "TAG";
    FirebaseAuth auth;


    public NotEklePresenterComply(NotEkleActivity iNotEkleView) {
        this.iNotEkleView = iNotEkleView;
        initNot();
      db = FirebaseFirestore.getInstance();


    }

    @Override
    public void clear() {
        iNotEkleView.onClearText();


    }

    @Override
    public void notEkle(String baslik, String aciklama) {
        Date date = Calendar.getInstance().getTime();

        if(baslik.equals("")){
          iNotEkleView.onNotEkleResult(false ,"başlık boş");
          return;
      }
      if (aciklama.equals("")){
          iNotEkleView.onNotEkleResult(false,"açıklama boş");
          return;
      }
      not=new NotModel(UUID.randomUUID().toString(),baslik ,aciklama, date.toString());

// Add a new document with a generated ID
        auth= FirebaseAuth.getInstance();
      String kullanici_id = auth.getUid();
        db.collection("data").document(kullanici_id).collection("notlar")
                .document(not.getNotId())
                .set(not)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot added with ID: ");
                        iNotEkleView.onNotEkleResult(true ,"not eklendi");
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        iNotEkleView.onNotEkleResult(false,"not eklenemedi");
                    }
                });

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iNotEkleView.onSetProgressBarVisibility(visiblity);

    }
    public void initNot(){
        not =new NotModel();
    }
}
