package com.example.shaochengyang.fashionnova.data;

import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.NetworkHelper;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionActivity;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;

public class DataManager implements IDataManager {
    INetworkHelper networkHelper;

    public DataManager(CollectionActivity collectionActivity) {
        //this is a problem that need to pass the collectionActivity
        networkHelper = new NetworkHelper();

    }

    @Override
    public void getCollection(OnCollectionListener onCollectionListener) {
        networkHelper.getCollection(onCollectionListener);
    }
}
