package com.example.shaochengyang.fashionnova.ui.category;

import android.content.Context;

import com.example.shaochengyang.fashionnova.data.DataManager;
import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;

import java.util.List;

public class CollectionPresenter implements ICollectionPresenter, IDataManager.OnCollectionListener {
    ICollectionView view;
    IDataManager dataManager;

    public CollectionPresenter(Context context) {
        dataManager = new DataManager(context);
        view = (ICollectionView) context;
    }

    @Override
    public void readCollectionFromServer(String api_key, String user_id) {
        dataManager.getCollection(this,api_key,user_id);

    }

    @Override
    public void passCollection(List<Collection> collectionList) {
        view.showCollectionList(collectionList);
    }
}
