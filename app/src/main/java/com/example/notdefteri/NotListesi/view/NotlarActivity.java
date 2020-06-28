package com.example.notdefteri.NotListesi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.NotEkle.presenter.NotEklePresenterComply;
import com.example.notdefteri.NotEkle.view.NotEkleActivity;
import com.example.notdefteri.NotListesi.presenter.NotListesiPresenter;
import com.example.notdefteri.NotListesi.presenter.NotListesiPresenterComply;
import com.example.notdefteri.R;

import java.util.ArrayList;
import java.util.List;

public class NotlarActivity extends AppCompatActivity implements INotListeleView {
    RecyclerView recyclerView ;
   NotlarAdapter adapter;
   Button button;
    LinearLayoutManager layoutManager;
    private NotListesiPresenter notListesiPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notlar);

        recyclerView =findViewById(R.id.recylerview_rv);
        button =findViewById(R.id.not_ekle_btn);
        notListesiPresenter =new NotListesiPresenterComply(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new NotlarAdapter(new ArrayList<NotModel>(),this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NotlarActivity.this,NotEkleActivity.class);
                startActivity(intent);


            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        notListesiPresenter.notListele();
    }

    @Override
    public void onClearText() {
    }

    @Override
    public void onNotListeleResult(Boolean result , List<NotModel> notList) {
       adapter.setNotlist(notList);
       adapter.notifyDataSetChanged();

    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}