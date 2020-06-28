package com.example.notdefteri.NotDetay.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.notdefteri.NotDetay.view.INotDetayView;
import com.example.notdefteri.NotDetay.view.NotDetayActivity;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.NotListesi.view.NotlarActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class NotDetayPresenterComply implements NotDetayPresenter {
    INotDetayView iNotDetayView;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String TAG="TAG";

    public NotDetayPresenterComply(NotDetayActivity iNotDetayView) {
        this.iNotDetayView=iNotDetayView;
        db=FirebaseFirestore.getInstance();
        
    }

    @Override
    public void notDetay( String baslik ,String aciklama ,String tarih){
       NotModel notModel =new NotModel("",baslik,aciklama,tarih);


        if(aciklama.isEmpty()){
            iNotDetayView.onNotDetayResult(false,"açıklamanız boş kalmış",null);
            return;
        }
        iNotDetayView.onNotDetayResult(true,"",notModel);

    }

    @Override
    public void notGuncelle(String id, String baslik, String aciklama) {
        Date date = Calendar.getInstance().getTime();
        NotModel notModel=new NotModel(id,baslik,aciklama,date.toString());
        auth= FirebaseAuth.getInstance();
        String kullanici_id = auth.getUid();
        db.collection("data").document(kullanici_id).collection("notlar")
                .document(notModel.getNotId())
                .set(notModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot updated with ID: ");
                        iNotDetayView.onNotGuncelleResult(true,"notunuz güncellenmiştir");
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error update document", e);
                        iNotDetayView.onNotGuncelleResult(false,"notunuz güncellenmedi");
                    }
                });
    }
}
