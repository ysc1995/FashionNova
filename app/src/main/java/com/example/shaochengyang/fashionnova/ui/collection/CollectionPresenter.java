package com.example.shaochengyang.fashionnova.ui.collection;

import com.example.shaochengyang.fashionnova.data.DataManager;
import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;

import java.util.List;

public class CollectionPresenter implements ICollectionPresenter, IDataManager.OnCollectionListener {
    ICollectionView view;
    IDataManager dataManager;

    public CollectionPresenter(CollectionActivity collectionActivity) {
        dataManager = new DataManager(collectionActivity);
        view = collectionActivity;
    }

    @Override
    public void readCollectionFromServer() {
        dataManager.getCollection(this);

    }

    @Override
    public void passCollection(List<Collection> collectionList) {
        view.showCollectionList(collectionList);
    }
}
