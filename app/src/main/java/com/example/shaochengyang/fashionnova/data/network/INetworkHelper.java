package com.example.shaochengyang.fashionnova.data.network;


import com.example.shaochengyang.fashionnova.data.IDataManager;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;

public interface INetworkHelper {
    void getCollection(IDataManager.OnCollectionListener onCollectionListener);

}
