package com.example.shaochengyang.fashionnova.ui.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shaochengyang.fashionnova.R;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;

import java.util.List;

public class CollectionActivity extends AppCompatActivity implements ICollectionView {

    ICollectionPresenter iCollectionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        iCollectionPresenter = new CollectionPresenter(this);
        iCollectionPresenter.readCollectionFromServer();
    }


    @Override
    public void showCollectionList(List<Collection> collectionList) {
        for(int i = 0 ; i < collectionList.size();i++){
            String cid = collectionList.get(i).getCid();
            Toast.makeText(this, ""+cid, Toast.LENGTH_SHORT).show();
            Collection collection = collectionList.get(i);
            String co = collection.getCdiscription();

        }
    }
}
