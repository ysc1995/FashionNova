package com.example.shaochengyang.fashionnova.ui.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.Toast;

import com.example.shaochengyang.fashionnova.R;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;

import java.util.List;

public class CollectionActivity extends AppCompatActivity implements ICollectionView {

    RecyclerView recyclerView;
    Adapter adapter;

    ICollectionPresenter iCollectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        recyclerView = findViewById(R.id.collectionrecyclerview);
        iCollectionPresenter = new CollectionPresenter(this);
        iCollectionPresenter.readCollectionFromServer();
    }


    @Override
    public void showCollectionList(List<Collection> collectionList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CollectionListAdapter(collectionList);
        recyclerView.setAdapter(adapter);

        /*for(int i = 0 ; i < collectionList.size();i++){
            String cid = collectionList.get(i).getCid();
            Toast.makeText(this, ""+cid, Toast.LENGTH_SHORT).show();
            Collection collection = collectionList.get(i);
            String co = collection.getCdiscription();

        }*/
    }
}
