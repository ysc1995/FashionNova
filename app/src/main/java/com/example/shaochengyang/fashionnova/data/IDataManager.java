package com.example.shaochengyang.fashionnova.data;

import com.example.shaochengyang.fashionnova.data.network.INetworkHelper;
import com.example.shaochengyang.fashionnova.data.network.model.Collection;
import com.example.shaochengyang.fashionnova.ui.collection.CollectionPresenter;

import java.util.List;

public interface IDataManager extends INetworkHelper {

    interface OnCollectionListener{
        void passCollection(List<Collection> collectionList);
    }
}