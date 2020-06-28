package com.example.notdefteri.NotListesi.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.NotEkle.view.INotEkleView;
import com.example.notdefteri.NotEkle.view.NotEkleActivity;
import com.example.notdefteri.NotListesi.view.INotListeleView;
import com.example.notdefteri.NotListesi.view.NotlarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotListesiPresenterComply implements NotListesiPresenter {
    INotListeleView iNotListeleView;
    FirebaseFirestore db;
    String TAG= "TAG";
    FirebaseAuth auth;

    public NotListesiPresenterComply(NotlarActivity iNotListeleView) {
        this.iNotListeleView = iNotListeleView;
        db = FirebaseFirestore.getInstance();

    }
    @Override
    public void clear() {
        iNotListeleView.onClearText();
    }
    @Override
    public void notListele() {

// Add a new document with a generated ID
        auth= FirebaseAuth.getInstance();
        String kullanici_id = auth.getUid();
        db.collection("data").document(kullanici_id).collection("notlar")
                .get()
       .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
           @Override
           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               if (queryDocumentSnapshots.isEmpty()) {
                   Log.d(TAG, "onSuccess: LIST EMPTY");
                   return;
               } else {
                   List<NotModel> notList = queryDocumentSnapshots.toObjects(NotModel.class);
                   iNotListeleView.onNotListeleResult(true, notList);
                   Log.i(TAG, "onSuccess:"+notList.size());
               }
           }
       });

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iNotListeleView.onSetProgressBarVisibility(visiblity);

    }
   /*public void initNot(){
        not =new NotModel("","","");
    }*/
}
